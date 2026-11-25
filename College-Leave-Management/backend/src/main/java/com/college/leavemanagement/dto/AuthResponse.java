package com.college.leavemanagement.dto;

public record AuthResponse(
    String message,
    String email,
    String role
) {}