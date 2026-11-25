package com.college.leavemanagement.dto;

import java.time.LocalDate;

public record LeaveRequestCreateRequest(
    String leaveType,
    LocalDate startDate,
    LocalDate endDate,
    String reason
) {}
