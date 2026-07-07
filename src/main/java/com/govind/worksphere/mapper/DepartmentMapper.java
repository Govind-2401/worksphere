package com.govind.worksphere.mapper;

import com.govind.worksphere.dto.DepartmentRequestDTO;
import com.govind.worksphere.dto.DepartmentResponseDTO;
import com.govind.worksphere.entity.Department;

public class DepartmentMapper {

    // RequestDTO -> Entity
    public static Department toEntity(DepartmentRequestDTO dto) {

        return Department.builder()
                .departmentCode(dto.getDepartmentCode())
                .departmentName(dto.getDepartmentName())
                .description(dto.getDescription())
                .build();
    }

    // Entity -> ResponseDTO
    public static DepartmentResponseDTO toResponseDTO(Department department) {

        return DepartmentResponseDTO.builder()
                .id(department.getId())
                .departmentCode(department.getDepartmentCode())
                .departmentName(department.getDepartmentName())
                .description(department.getDescription())
                .build();
    }
}