package com.govind.worksphere.repository;

import com.govind.worksphere.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import com.govind.worksphere.entity.enums.EmploymentStatus;
import com.govind.worksphere.dto.DepartmentEmployeeCountDTO;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;
import com.govind.worksphere.dto.GenderDistributionDTO;
import org.springframework.data.jpa.repository.Query;
import com.govind.worksphere.dto.StatusDistributionDTO;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Search API
    List<Employee> findByEmployeeCodeContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String employeeCode,
            String firstName,
            String lastName,
            String email
    );

    // Duplicate Validation
    boolean existsByEmployeeCode(String employeeCode);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    long countByEmploymentStatus(EmploymentStatus employmentStatus);

    @Query("""
        SELECT new com.govind.worksphere.dto.DepartmentEmployeeCountDTO(
            d.departmentName,
            COUNT(e)
        )
        FROM Employee e
        JOIN e.department d
        GROUP BY d.departmentName
        ORDER BY COUNT(e) DESC
        """)
    List<DepartmentEmployeeCountDTO> getDepartmentWiseEmployeeCount();
    List<Employee> findByJoiningDateGreaterThanEqual(LocalDate joiningDate);

    @Query("""
        SELECT new com.govind.worksphere.dto.GenderDistributionDTO(
            CAST(e.gender AS string),
            COUNT(e)
        )
        FROM Employee e
        GROUP BY e.gender
        ORDER BY COUNT(e) DESC
        """)
    List<GenderDistributionDTO> getGenderDistribution();

    @Query("""
        SELECT new com.govind.worksphere.dto.StatusDistributionDTO(
            CAST(e.employmentStatus AS string),
            COUNT(e)
        )
        FROM Employee e
        GROUP BY e.employmentStatus
        ORDER BY COUNT(e) DESC
        """)
    List<StatusDistributionDTO> getStatusDistribution();
}