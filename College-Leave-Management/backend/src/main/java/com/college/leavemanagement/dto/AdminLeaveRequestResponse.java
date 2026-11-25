
package com.college.leavemanagement.dto;

import java.time.LocalDate;

public record AdminLeaveRequestResponse(
    Long id,
    String studentEmail,
    String leaveType,
    LocalDate startDate,
    LocalDate endDate,
    String reason,
    String status
) {}
