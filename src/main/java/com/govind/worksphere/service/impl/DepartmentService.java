package com.govind.worksphere.service;

import com.govind.worksphere.dto.DepartmentRequestDTO;
import com.govind.worksphere.dto.DepartmentResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DepartmentService {

    DepartmentResponseDTO saveDepartment(DepartmentRequestDTO departmentRequestDTO);

    Page<DepartmentResponseDTO> getAllDepartments(
            int page,
            int size,
            String sortBy,
            String sortDir);

    DepartmentResponseDTO getDepartmentById(Long id);

    DepartmentResponseDTO updateDepartment(
            Long id,
            DepartmentRequestDTO departmentRequestDTO);

    void deleteDepartment(Long id);

    List<DepartmentResponseDTO> searchDepartments(String keyword);
}