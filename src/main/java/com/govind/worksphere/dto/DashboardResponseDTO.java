package com.govind.worksphere.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponseDTO {

    private long totalEmployees;

    private long activeEmployees;

    private long inactiveEmployees;

    private long onLeaveEmployees;

    private long resignedEmployees;

    private long totalDepartments;
}