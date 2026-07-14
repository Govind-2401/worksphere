package com.govind.worksphere.service;

import com.govind.worksphere.dto.DashboardResponseDTO;
import com.govind.worksphere.dto.DepartmentEmployeeCountDTO;
import com.govind.worksphere.dto.EmployeeResponseDTO;
import com.govind.worksphere.dto.GenderDistributionDTO;
import com.govind.worksphere.dto.StatusDistributionDTO;
import java.util.List;

public interface DashboardService {

    DashboardResponseDTO getDashboardSummary();

    List<DepartmentEmployeeCountDTO> getDepartmentWiseEmployeeCount();

    List<EmployeeResponseDTO> getRecentEmployees();

    List<GenderDistributionDTO> getGenderDistribution();

    List<StatusDistributionDTO> getStatusDistribution();

}