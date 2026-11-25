
package com.college.leavemanagement.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.college.leavemanagement.dto.AdminLeaveRequestResponse;
import com.college.leavemanagement.dto.LeaveRequestCreateRequest;
import com.college.leavemanagement.dto.LeaveRequestCreatedResponse;
import com.college.leavemanagement.dto.LeaveRequestResponse;
import com.college.leavemanagement.dto.LeaveStatusUpdateRequest;
import com.college.leavemanagement.dto.StatusUpdateResponse;
import com.college.leavemanagement.entity.AppUser;
import com.college.leavemanagement.entity.LeaveRequest;
import com.college.leavemanagement.repository.AppUserRepository;
import com.college.leavemanagement.service.LeaveRequestService;

@RestController
@RequestMapping("/api/leaves")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;
    private final AppUserRepository appUserRepository;

    public LeaveRequestController(
            LeaveRequestService leaveRequestService,
            AppUserRepository appUserRepository) {
        this.leaveRequestService = leaveRequestService;
        this.appUserRepository = appUserRepository;
    }

    // 1. Submit leave request
    @PostMapping
    public ResponseEntity<LeaveRequestCreatedResponse> submitLeaveRequest(
            @RequestBody LeaveRequestCreateRequest request,
            Authentication authentication) {

        String email = authenticatedEmail(authentication);
        AppUser student = findStudent(email);

        String leaveType = request.leaveType();
        String reason = request.reason();

        if (leaveType == null || leaveType.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Leave type is required");
        }

        if (request.startDate() == null || request.endDate() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Start date and end date are required");
        }

        if (reason == null || reason.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Reason for leave is required");
        }

        if (reason.trim().length() > 1000) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Reason must not exceed 1000 characters");
        }

        LocalDate startDate = request.startDate();
        LocalDate endDate = request.endDate();

        if (endDate.isBefore(startDate)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "End date cannot be before the start date");
        }

        LeaveRequest saved = leaveRequestService.submitLeaveRequest(
                student,
                startDate,
                endDate,
                leaveType.trim(),
                reason.trim());

        LeaveRequestCreatedResponse response =
                new LeaveRequestCreatedResponse(
                        "Leave request submitted successfully",
                        saved.getId(),
                        saved.getLeaveType(),
                        saved.getStartDate().toString(),
                        saved.getEndDate().toString(),
                        saved.getReason(),
                        saved.getStatus());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 2. Get student leave requests
    @GetMapping
    public ResponseEntity<List<LeaveRequestResponse>>
            getStudentLeaveRequests(Authentication authentication) {

        String email = authenticatedEmail(authentication);
        AppUser student = findStudent(email);

        List<LeaveRequestResponse> result =
                leaveRequestService
                        .getStudentLeaveRequests(student.getId())
                        .stream()
                        .map(leave -> new LeaveRequestResponse(
                                leave.getId(),
                                leave.getLeaveType(),
                                leave.getStartDate(),
                                leave.getEndDate(),
                                leave.getReason(),
                                leave.getStatus()))
                        .toList();

        return ResponseEntity.ok(result);
    }

    // 3. Get all leave requests (Admin)
        // 3. Get all leave requests (Admin)
@GetMapping("/admin")
public ResponseEntity<List<AdminLeaveRequestResponse>>
        getAllLeaveRequests() {

    try {
        List<LeaveRequest> requests =
                leaveRequestService.getAllLeaveRequests();

        List<AdminLeaveRequestResponse> result =
                requests.stream()
                        .map(leave -> new AdminLeaveRequestResponse(
                                leave.getId(),
                                leave.getStudent().getEmail(),
                                leave.getLeaveType(),
                                leave.getStartDate(),
                                leave.getEndDate(),
                                leave.getReason(),
                                leave.getStatus()))
                        .toList();

        return ResponseEntity.ok(result);

    } catch (Exception e) {
        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Unable to fetch leave requests");
    }
}

    // 4. Update leave status (Admin)
    @PutMapping("/{id}/status")
    public ResponseEntity<StatusUpdateResponse> updateLeaveStatus(
            @PathVariable Long id,
            @RequestBody LeaveStatusUpdateRequest request) {

        if (request.status() == null || request.status().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Status is required");
        }

        try {
            LeaveRequest updated =
                    leaveRequestService.updateLeaveStatus(
                            id, request.status());

            StatusUpdateResponse response =
                    new StatusUpdateResponse(
                            "Leave status updated successfully",
                            updated.getId(),
                            updated.getStatus());

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    // 5. Get authenticated email from Keycloak JWT
    private String authenticatedEmail(Authentication authentication) {

        if (!(authentication instanceof JwtAuthenticationToken jwtAuth)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "A valid Keycloak access token is required");
        }

        String email = jwtAuth.getToken().getClaimAsString("email");

        if (email == null || email.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Your Keycloak account must have an email address");
        }

        return email.trim().toLowerCase();
    }

    // 6. Find student in database
    private AppUser findStudent(String email) {

        AppUser student = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.FORBIDDEN,
                        "No matching student account exists in the database"));

        if (!"STUDENT".equalsIgnoreCase(student.getRole())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "This account is not registered as a student");
        }

        return student;
    }
}
