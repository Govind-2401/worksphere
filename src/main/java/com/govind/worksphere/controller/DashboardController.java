package com.govind.worksphere.controller;

import com.govind.worksphere.dto.DashboardResponseDTO;
import com.govind.worksphere.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.govind.worksphere.dto.DepartmentEmployeeCountDTO;
import com.govind.worksphere.dto.EmployeeResponseDTO;
import com.govind.worksphere.dto.GenderDistributionDTO;
import com.govind.worksphere.dto.StatusDistributionDTO;
import java.util.List;

@Tag(
        name = "Dashboard Management",
        description = "Dashboard Summary APIs"
)
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @Operation(summary = "Get Dashboard Summary")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    public DashboardResponseDTO getDashboardSummary() {

        return dashboardService.getDashboardSummary();
    }

    @Operation(summary = "Get Department-wise Employee Count")
    @GetMapping("/department-wise")
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    public List<DepartmentEmployeeCountDTO> getDepartmentWiseEmployeeCount() {

        return dashboardService.getDepartmentWiseEmployeeCount();
    }

    @Operation(summary = "Get Recently Joined Employees")
    @GetMapping("/recent-employees")
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    public List<EmployeeResponseDTO> getRecentEmployees() {

        return dashboardService.getRecentEmployees();
    }

    @Operation(summary = "Get Gender Distribution")
    @GetMapping("/gender-distribution")
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    public List<GenderDistributionDTO> getGenderDistribution() {

        return dashboardService.getGenderDistribution();
    }

    @Operation(summary = "Get Employment Status Distribution")
    @GetMapping("/status-distribution")
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    public List<StatusDistributionDTO> getStatusDistribution() {

        return dashboardService.getStatusDistribution();
    }
}