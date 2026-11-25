
package com.college.leavemanagement.dto;

public record LeaveRequestCreatedResponse(
    String message,
    Long id,
    String leaveType,
    String startDate,
    String endDate,
    String reason,
    String status
) {}
