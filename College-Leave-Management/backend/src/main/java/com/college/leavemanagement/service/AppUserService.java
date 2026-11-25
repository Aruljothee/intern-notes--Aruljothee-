
package com.college.leavemanagement.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.college.leavemanagement.entity.AppUser;
import com.college.leavemanagement.repository.AppUserRepository;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register a new student
    public AppUser registerUser(
            String email,
            String rawPassword,
            String role) {

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "Email is required");
        }

        if (rawPassword == null || rawPassword.isBlank()) {
            throw new IllegalArgumentException(
                    "Password is required");
        }

        String normalizedEmail =
                email.trim().toLowerCase();

        if (appUserRepository.existsByEmail(normalizedEmail)) {
            throw new IllegalArgumentException(
                    "Email is already registered");
        }

        if (!"STUDENT".equalsIgnoreCase(role)) {
            throw new IllegalArgumentException(
                    "Only student registration is allowed");
        }

        AppUser user = new AppUser();
        user.setEmail(normalizedEmail);
        user.setPassword(
                passwordEncoder.encode(rawPassword));
        user.setRole("STUDENT");

        return appUserRepository.save(user);
    }

    // Log in an existing user
    public AppUser loginUser(
            String email,
            String rawPassword,
            String role) {

        if (email == null || email.isBlank()
                || rawPassword == null || rawPassword.isBlank()) {
            throw new IllegalArgumentException(
                    "Email and password are required");
        }

        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException(
                    "Role is required");
        }

        String normalizedEmail =
                email.trim().toLowerCase();

        AppUser user = appUserRepository
                .findByEmail(normalizedEmail)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid email or password"));

        if (!passwordEncoder.matches(
                rawPassword, user.getPassword())) {
            throw new IllegalArgumentException(
                    "Invalid email or password");
        }

        if (!user.getRole().equalsIgnoreCase(role)) {
            throw new IllegalArgumentException(
                    "Invalid account role");
        }

        return user;
    }
}
