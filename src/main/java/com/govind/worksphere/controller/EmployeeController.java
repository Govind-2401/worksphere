package com.govind.worksphere.controller;

import com.govind.worksphere.dto.EmployeeRequestDTO;
import com.govind.worksphere.dto.EmployeeResponseDTO;
import com.govind.worksphere.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Create Employee
    @PostMapping
    public EmployeeResponseDTO saveEmployee(
            @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {

        return employeeService.saveEmployee(employeeRequestDTO);
    }

    // Get All Employees
    @GetMapping
    public List<EmployeeResponseDTO> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    // Get Employee By ID
    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployeeById(@PathVariable Long id) {

        return employeeService.getEmployeeById(id);
    }

    // Update Employee
    @PutMapping("/{id}")
    public EmployeeResponseDTO updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {

        return employeeService.updateEmployee(id, employeeRequestDTO);
    }

    // Delete Employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return "Employee deleted successfully.";
    }
}