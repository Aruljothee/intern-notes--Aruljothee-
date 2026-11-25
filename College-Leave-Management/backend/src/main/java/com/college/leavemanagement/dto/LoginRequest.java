package com.college.leavemanagement.dto;

public record LoginRequest(
    String email,
    String password,
    String role
) {}