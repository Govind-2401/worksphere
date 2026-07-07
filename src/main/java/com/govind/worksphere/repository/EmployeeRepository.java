package com.govind.worksphere.repository;

import com.govind.worksphere.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;



public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByEmployeeCodeContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String employeeCode,
            String firstName,
            String lastName,
            String email
    );
}