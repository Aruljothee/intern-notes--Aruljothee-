
package com.college.leavemanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.college.leavemanagement.dto.AuthResponse;
import com.college.leavemanagement.dto.LoginRequest;
import com.college.leavemanagement.dto.RegisterRequest;
import com.college.leavemanagement.entity.AppUser;
import com.college.leavemanagement.service.AppUserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AppUserService appUserService;

    public AuthController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    // 1. Register a student account
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest request) {

        try {
            String email = request.email();
            String password = request.password();

            AppUser user = appUserService.registerUser(
                    email, password, "STUDENT");

            AuthResponse response = new AuthResponse(
                    "Account created successfully",
                    user.getEmail(),
                    user.getRole()
            );

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(response);

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // 2. Login an existing student or admin
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request) {

        String email = request.email();
        String password = request.password();
        String role = request.role();

        if (email == null || email.isBlank()
                || password == null || password.isBlank()
                || role == null || role.isBlank()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Email, password, and role are required");
        }

        if (!role.equalsIgnoreCase("STUDENT")
                && !role.equalsIgnoreCase("ADMIN")) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid account role");
        }

        try {
            AppUser user = appUserService.loginUser(
                    email, password, role);

            AuthResponse response = new AuthResponse(
                    "Login successful",
                    user.getEmail(),
                    user.getRole()
            );

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
