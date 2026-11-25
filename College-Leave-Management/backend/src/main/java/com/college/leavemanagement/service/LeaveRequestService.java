
package com.college.leavemanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.college.leavemanagement.entity.AppUser;
import com.college.leavemanagement.entity.LeaveRequest;
import com.college.leavemanagement.repository.LeaveRequestRepository;

@Service
public class LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;

    public LeaveRequestService(
            LeaveRequestRepository leaveRequestRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
    }

    @Transactional
    public LeaveRequest submitLeaveRequest(
            AppUser student,
            java.time.LocalDate startDate,
            java.time.LocalDate endDate,
            String leaveType,
            String reason) {

        if (student == null) {
            throw new IllegalArgumentException("Student is required");
        }

        if (leaveType == null || leaveType.isBlank()) {
            throw new IllegalArgumentException("Leave type is required");
        }

        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException(
                    "Start and end dates are required");
        }

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException(
                    "End date cannot be before start date");
        }

        if (reason == null || reason.isBlank()) {
            throw new IllegalArgumentException("Reason is required");
        }

        LeaveRequest request = new LeaveRequest();
        request.setLeaveType(leaveType);
        request.setStartDate(startDate);
        request.setEndDate(endDate);
        request.setReason(reason.trim());
        request.setStatus("PENDING");
        request.setStudent(student);

        return leaveRequestRepository.save(request);
    }

    @Transactional(readOnly = true)
    public List<LeaveRequest> getStudentLeaveRequests(Long studentId) {
        return leaveRequestRepository.findByStudentId(studentId);
    }

    @Transactional(readOnly = true)
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

    @Transactional
    public LeaveRequest updateLeaveStatus(Long requestId, String status) {
        if (requestId == null) {
            throw new IllegalArgumentException(
                    "Leave request ID is required");
        }

        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status is required");
        }

        String normalizedStatus = status.trim().toUpperCase();

        if (!normalizedStatus.equals("APPROVED")
                && !normalizedStatus.equals("REJECTED")) {
            throw new IllegalArgumentException(
                    "Status must be APPROVED or REJECTED");
        }

        LeaveRequest request = leaveRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Leave request not found"));

        request.setStatus(normalizedStatus);

        return leaveRequestRepository.save(request);
    }
}
