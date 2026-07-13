package com.govind.worksphere.controller;

import com.govind.worksphere.dto.EmployeeRequestDTO;
import com.govind.worksphere.dto.EmployeeResponseDTO;
import com.govind.worksphere.dto.common.ApiResponse;
import com.govind.worksphere.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import com.govind.worksphere.service.FileStorageService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(
        name = "Employee Management",
        description = "Employee CRUD APIs"
)
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final FileStorageService fileStorageService;

    public EmployeeController(EmployeeService employeeService,
                              FileStorageService fileStorageService) {

        this.employeeService = employeeService;
        this.fileStorageService = fileStorageService;
    }

    // Create Employee
    @Operation(summary = "Create a new employee")
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> saveEmployee(
            @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {

        EmployeeResponseDTO employee =
                employeeService.saveEmployee(employeeRequestDTO);

        ApiResponse<EmployeeResponseDTO> response =
                ApiResponse.<EmployeeResponseDTO>builder()
                        .success(true)
                        .message("Employee created successfully.")
                        .data(employee)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get All Employees
    @Operation(summary = "Get all employees")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','HR','EMPLOYEE')")
    public ResponseEntity<ApiResponse<Page<EmployeeResponseDTO>>> getAllEmployees(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Page<EmployeeResponseDTO> employees =
                employeeService.getAllEmployees(page, size, sortBy, sortDir);

        ApiResponse<Page<EmployeeResponseDTO>> response =
                ApiResponse.<Page<EmployeeResponseDTO>>builder()
                        .success(true)
                        .message("Employees fetched successfully.")
                        .data(employees)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    // Get Employee By ID
    @Operation(summary = "Get employee by ID")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','HR','EMPLOYEE')")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> getEmployeeById(
            @PathVariable Long id) {

        EmployeeResponseDTO employee =
                employeeService.getEmployeeById(id);

        ApiResponse<EmployeeResponseDTO> response =
                ApiResponse.<EmployeeResponseDTO>builder()
                        .success(true)
                        .message("Employee fetched successfully.")
                        .data(employee)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    // Update Employee
    @Operation(summary = "Update employee")
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {

        EmployeeResponseDTO employee =
                employeeService.updateEmployee(id, employeeRequestDTO);

        ApiResponse<EmployeeResponseDTO> response =
                ApiResponse.<EmployeeResponseDTO>builder()
                        .success(true)
                        .message("Employee updated successfully.")
                        .data(employee)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    // Delete Employee
    @Operation(summary = "Delete employee")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        ApiResponse<Void> response =
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Employee deleted successfully.")
                        .data(null)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    // Search Employees
    @Operation(summary = "Search employees")
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','HR','EMPLOYEE')")
    public ResponseEntity<ApiResponse<List<EmployeeResponseDTO>>> searchEmployees(
            @RequestParam String keyword) {

        List<EmployeeResponseDTO> employees =
                employeeService.searchEmployees(keyword);

        ApiResponse<List<EmployeeResponseDTO>> response =
                ApiResponse.<List<EmployeeResponseDTO>>builder()
                        .success(true)
                        .message("Employees fetched successfully.")
                        .data(employees)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Upload employee profile image")
    @PostMapping(
            value = "/{id}/upload/profile-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    public String uploadProfileImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {

        return fileStorageService.uploadProfileImage(id, file);
    }

    @Operation(summary = "Upload employee resume")
    @PostMapping(
            value = "/{id}/upload/resume",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    public String uploadResume(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {

        return fileStorageService.uploadResume(id, file);
    }

    @Operation(summary = "View employee profile image")
    @GetMapping("/{id}/profile-image")
    @PreAuthorize("hasAnyRole('ADMIN','HR','EMPLOYEE')")
    public ResponseEntity<Resource> viewProfileImage(@PathVariable Long id) {

        Resource resource = fileStorageService.downloadProfileImage(id);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }

    @Operation(summary = "Download employee resume")
    @GetMapping("/{id}/resume")
    @PreAuthorize("hasAnyRole('ADMIN','HR','EMPLOYEE')")
    public ResponseEntity<Resource> downloadResume(@PathVariable Long id) {

        Resource resource = fileStorageService.downloadResume(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"resume.pdf\"")
                .body(resource);
    }
}