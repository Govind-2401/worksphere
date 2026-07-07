package com.govind.worksphere.mapper;

import com.govind.worksphere.dto.EmployeeRequestDTO;
import com.govind.worksphere.dto.EmployeeResponseDTO;
import com.govind.worksphere.entity.Employee;

public class EmployeeMapper {

    // Convert RequestDTO -> Entity
    public static Employee toEntity(EmployeeRequestDTO dto) {

        Employee employee = new Employee();

        employee.setEmployeeCode(dto.getEmployeeCode());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setGender(dto.getGender());
        employee.setJoiningDate(dto.getJoiningDate());
        employee.setEmploymentStatus(dto.getEmploymentStatus());

        return employee;
    }

    // Convert Entity -> ResponseDTO
    public static EmployeeResponseDTO toResponseDTO(Employee employee) {

        return EmployeeResponseDTO.builder()
                .id(employee.getId())
                .employeeCode(employee.getEmployeeCode())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .gender(employee.getGender())
                .joiningDate(employee.getJoiningDate())
                .employmentStatus(employee.getEmploymentStatus())
                .departmentId(
                        employee.getDepartment() != null
                                ? employee.getDepartment().getId()
                                : null
                )
                .departmentName(
                        employee.getDepartment() != null
                                ? employee.getDepartment().getDepartmentName()
                                : null
                )
                .build();
    }
}