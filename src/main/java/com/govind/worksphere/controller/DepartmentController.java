package com.govind.worksphere.controller;

import com.govind.worksphere.dto.DepartmentRequestDTO;
import com.govind.worksphere.dto.DepartmentResponseDTO;
import com.govind.worksphere.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "Department Management",
        description = "Department CRUD APIs"
)
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // Create Department
    @Operation(summary = "Create a new department")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public DepartmentResponseDTO saveDepartment(
            @Valid @RequestBody DepartmentRequestDTO departmentRequestDTO) {

        return departmentService.saveDepartment(departmentRequestDTO);
    }

    // Get All Departments
    @Operation(summary = "Get all departments")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','HR','EMPLOYEE')")
    public Page<DepartmentResponseDTO> getAllDepartments(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "departmentName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        return departmentService.getAllDepartments(page, size, sortBy, sortDir);
    }

    // Get Department By Id
    @Operation(summary = "Get department by ID")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','HR','EMPLOYEE')")
    public DepartmentResponseDTO getDepartmentById(@PathVariable Long id) {

        return departmentService.getDepartmentById(id);
    }

    // Update Department
    @Operation(summary = "Update department")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public DepartmentResponseDTO updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequestDTO departmentRequestDTO) {

        return departmentService.updateDepartment(id, departmentRequestDTO);
    }

    // Delete Department
    @Operation(summary = "Delete department")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDepartment(@PathVariable Long id) {

        departmentService.deleteDepartment(id);
    }

    // Search Departments
    @Operation(summary = "Search departments")
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','HR','EMPLOYEE')")
    public List<DepartmentResponseDTO> searchDepartments(
            @RequestParam String keyword) {

        return departmentService.searchDepartments(keyword);
    }
}