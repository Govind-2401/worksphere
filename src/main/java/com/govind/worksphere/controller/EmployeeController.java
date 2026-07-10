package com.govind.worksphere.controller;

import com.govind.worksphere.dto.EmployeeRequestDTO;
import com.govind.worksphere.dto.EmployeeResponseDTO;
import com.govind.worksphere.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "Employee Management",
        description = "Employee CRUD APIs"
)
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Create Employee
    @Operation(summary = "Create a new employee")
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    public EmployeeResponseDTO saveEmployee(
            @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {

        return employeeService.saveEmployee(employeeRequestDTO);
    }

    // Get All Employees
    @Operation(summary = "Get all employees")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','HR','EMPLOYEE')")
    public Page<EmployeeResponseDTO> getAllEmployees(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size,

            @RequestParam(defaultValue = "id") String sortBy,

            @RequestParam(defaultValue = "asc") String sortDir
    ) {

        return employeeService.getAllEmployees(
                page,
                size,
                sortBy,
                sortDir
        );
    }

    // Get Employee By ID
    @Operation(summary = "Get employee by ID")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','HR','EMPLOYEE')")
    public EmployeeResponseDTO getEmployeeById(@PathVariable Long id) {

        return employeeService.getEmployeeById(id);
    }

    // Update Employee
    @Operation(summary = "Update employee")
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    public EmployeeResponseDTO updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {

        return employeeService.updateEmployee(id, employeeRequestDTO);
    }

    // Delete Employee
    @Operation(summary = "Delete employee")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteEmployee(@PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return "Employee deleted successfully.";
    }

    // Search Employees
    @Operation(summary = "Search employees")
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','HR','EMPLOYEE')")
    public List<EmployeeResponseDTO> searchEmployees(
            @RequestParam String keyword) {

        return employeeService.searchEmployees(keyword);
    }
}