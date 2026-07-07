package com.govind.worksphere.repository;

import com.govind.worksphere.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Search API
    List<Department> findByDepartmentCodeContainingIgnoreCaseOrDepartmentNameContainingIgnoreCase(
            String departmentCode,
            String departmentName
    );

    // Duplicate Validation
    boolean existsByDepartmentCode(String departmentCode);

    boolean existsByDepartmentName(String departmentName);
}