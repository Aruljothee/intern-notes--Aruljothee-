
package com.college.leavemanagement.dto;

import java.time.LocalDate;

public record LeaveRequestResponse(
    Long id,
    String leaveType,
    LocalDate startDate,
    LocalDate endDate,
    String reason,
    String status
) {}
