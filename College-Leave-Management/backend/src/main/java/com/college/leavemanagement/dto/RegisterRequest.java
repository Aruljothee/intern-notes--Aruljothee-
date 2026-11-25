package com.college.leavemanagement.dto;

public record RegisterRequest(
    String email,
    String password
) {}