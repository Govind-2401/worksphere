package com.govind.worksphere.controller;

import com.govind.worksphere.dto.DepartmentRequestDTO;
import com.govind.worksphere.dto.DepartmentResponseDTO;
import com.govind.worksphere.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // Create Department
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentResponseDTO saveDepartment(
            @Valid @RequestBody DepartmentRequestDTO departmentRequestDTO) {

        return departmentService.saveDepartment(departmentRequestDTO);
    }

    // Get All Departments
    @GetMapping
    public Page<DepartmentResponseDTO> getAllDepartments(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "departmentName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        return departmentService.getAllDepartments(page, size, sortBy, sortDir);
    }

    // Get Department By Id
    @GetMapping("/{id}")
    public DepartmentResponseDTO getDepartmentById(@PathVariable Long id) {

        return departmentService.getDepartmentById(id);
    }

    // Update Department
    @PutMapping("/{id}")
    public DepartmentResponseDTO updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequestDTO departmentRequestDTO) {

        return departmentService.updateDepartment(id, departmentRequestDTO);
    }

    // Delete Department
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable Long id) {

        departmentService.deleteDepartment(id);
    }

    // Search Department
    @GetMapping("/search")
    public List<DepartmentResponseDTO> searchDepartments(
            @RequestParam String keyword) {

        return departmentService.searchDepartments(keyword);
    }
}