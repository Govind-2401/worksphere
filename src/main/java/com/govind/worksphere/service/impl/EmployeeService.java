package com.govind.worksphere.service;

import com.govind.worksphere.dto.EmployeeRequestDTO;
import com.govind.worksphere.dto.EmployeeResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDTO saveEmployee(EmployeeRequestDTO employeeRequestDTO);

    Page<EmployeeResponseDTO> getAllEmployees(
            int page,
            int size,
            String sortBy,
            String sortDir
    );

    EmployeeResponseDTO getEmployeeById(Long id);

    EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO employeeRequestDTO);

    void deleteEmployee(Long id);

    List<EmployeeResponseDTO> searchEmployees(String keyword);
}