package com.govind.worksphere.controller;

import com.govind.worksphere.dto.DepartmentRequestDTO;
import com.govind.worksphere.dto.DepartmentResponseDTO;
import com.govind.worksphere.dto.common.ApiResponse;
import com.govind.worksphere.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<DepartmentResponseDTO>> saveDepartment(
            @Valid @RequestBody DepartmentRequestDTO departmentRequestDTO) {

        DepartmentResponseDTO department =
                departmentService.saveDepartment(departmentRequestDTO);

        ApiResponse<DepartmentResponseDTO> response =
                ApiResponse.<DepartmentResponseDTO>builder()
                        .success(true)
                        .message("Department created successfully.")
                        .data(department)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get All Departments
    @Operation(summary = "Get all departments")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','HR','EMPLOYEE')")
    public ResponseEntity<ApiResponse<Page<DepartmentResponseDTO>>> getAllDepartments(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "departmentName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Page<DepartmentResponseDTO> departments =
                departmentService.getAllDepartments(page, size, sortBy, sortDir);

        ApiResponse<Page<DepartmentResponseDTO>> response =
                ApiResponse.<Page<DepartmentResponseDTO>>builder()
                        .success(true)
                        .message("Departments fetched successfully.")
                        .data(departments)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    // Get Department By ID
    @Operation(summary = "Get department by ID")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','HR','EMPLOYEE')")
    public ResponseEntity<ApiResponse<DepartmentResponseDTO>> getDepartmentById(
            @PathVariable Long id) {

        DepartmentResponseDTO department =
                departmentService.getDepartmentById(id);

        ApiResponse<DepartmentResponseDTO> response =
                ApiResponse.<DepartmentResponseDTO>builder()
                        .success(true)
                        .message("Department fetched successfully.")
                        .data(department)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    // Update Department
    @Operation(summary = "Update department")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<DepartmentResponseDTO>> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequestDTO departmentRequestDTO) {

        DepartmentResponseDTO department =
                departmentService.updateDepartment(id, departmentRequestDTO);

        ApiResponse<DepartmentResponseDTO> response =
                ApiResponse.<DepartmentResponseDTO>builder()
                        .success(true)
                        .message("Department updated successfully.")
                        .data(department)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    // Delete Department
    @Operation(summary = "Delete department")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteDepartment(
            @PathVariable Long id) {

        departmentService.deleteDepartment(id);

        ApiResponse<Void> response =
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Department deleted successfully.")
                        .data(null)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    // Search Departments
    @Operation(summary = "Search departments")
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','HR','EMPLOYEE')")
    public ResponseEntity<ApiResponse<List<DepartmentResponseDTO>>> searchDepartments(
            @RequestParam String keyword) {

        List<DepartmentResponseDTO> departments =
                departmentService.searchDepartments(keyword);

        ApiResponse<List<DepartmentResponseDTO>> response =
                ApiResponse.<List<DepartmentResponseDTO>>builder()
                        .success(true)
                        .message("Departments fetched successfully.")
                        .data(departments)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }
}