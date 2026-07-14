package com.govind.worksphere.service.impl;

import com.govind.worksphere.dto.DashboardResponseDTO;
import com.govind.worksphere.entity.enums.EmploymentStatus;
import com.govind.worksphere.repository.DepartmentRepository;
import com.govind.worksphere.repository.EmployeeRepository;
import com.govind.worksphere.service.DashboardService;
import org.springframework.stereotype.Service;
import com.govind.worksphere.dto.DepartmentEmployeeCountDTO;
import java.util.List;
import com.govind.worksphere.dto.EmployeeResponseDTO;
import com.govind.worksphere.entity.Employee;
import com.govind.worksphere.mapper.EmployeeMapper;
import java.time.LocalDate;
import com.govind.worksphere.dto.GenderDistributionDTO;
import com.govind.worksphere.dto.StatusDistributionDTO;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public DashboardServiceImpl(EmployeeRepository employeeRepository,
                                DepartmentRepository departmentRepository) {

        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DashboardResponseDTO getDashboardSummary() {

        return DashboardResponseDTO.builder()

                .totalEmployees(employeeRepository.count())

                .activeEmployees(
                        employeeRepository.countByEmploymentStatus(
                                EmploymentStatus.ACTIVE))

                .inactiveEmployees(
                        employeeRepository.countByEmploymentStatus(
                                EmploymentStatus.INACTIVE))

                .onLeaveEmployees(
                        employeeRepository.countByEmploymentStatus(
                                EmploymentStatus.ON_LEAVE))

                .resignedEmployees(
                        employeeRepository.countByEmploymentStatus(
                                EmploymentStatus.RESIGNED))

                .totalDepartments(departmentRepository.count())

                .build();
    }

    @Override
    public List<DepartmentEmployeeCountDTO> getDepartmentWiseEmployeeCount() {

        return employeeRepository.getDepartmentWiseEmployeeCount();
    }

    @Override
    public List<EmployeeResponseDTO> getRecentEmployees() {

        LocalDate date = LocalDate.now().minusDays(30);

        List<Employee> employees =
                employeeRepository.findByJoiningDateGreaterThanEqual(date);

        return employees.stream()
                .map(EmployeeMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<GenderDistributionDTO> getGenderDistribution() {

        return employeeRepository.getGenderDistribution();
    }

    @Override
    public List<StatusDistributionDTO> getStatusDistribution() {

        return employeeRepository.getStatusDistribution();
    }
}