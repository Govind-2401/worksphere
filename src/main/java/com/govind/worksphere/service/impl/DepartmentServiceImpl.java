package com.govind.worksphere.service.impl;

import com.govind.worksphere.dto.DepartmentRequestDTO;
import com.govind.worksphere.dto.DepartmentResponseDTO;
import com.govind.worksphere.entity.Department;
import com.govind.worksphere.exception.DepartmentNotFoundException;
import com.govind.worksphere.exception.DuplicateResourceException;
import com.govind.worksphere.mapper.DepartmentMapper;
import com.govind.worksphere.repository.DepartmentRepository;
import com.govind.worksphere.service.DepartmentService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentResponseDTO saveDepartment(DepartmentRequestDTO departmentRequestDTO) {

        // Duplicate Validation

        if (departmentRepository.existsByDepartmentCode(departmentRequestDTO.getDepartmentCode())) {
            throw new DuplicateResourceException("Department Code already exists.");
        }

        if (departmentRepository.existsByDepartmentName(departmentRequestDTO.getDepartmentName())) {
            throw new DuplicateResourceException("Department Name already exists.");
        }

        Department department = DepartmentMapper.toEntity(departmentRequestDTO);

        Department savedDepartment = departmentRepository.save(department);

        return DepartmentMapper.toResponseDTO(savedDepartment);
    }

    @Override
    public Page<DepartmentResponseDTO> getAllDepartments(
            int page,
            int size,
            String sortBy,
            String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return departmentRepository.findAll(pageable)
                .map(DepartmentMapper::toResponseDTO);
    }

    @Override
    public DepartmentResponseDTO getDepartmentById(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException("Department not found with id: " + id));

        return DepartmentMapper.toResponseDTO(department);
    }

    @Override
    public DepartmentResponseDTO updateDepartment(Long id,
                                                  DepartmentRequestDTO departmentRequestDTO) {

        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException("Department not found with id: " + id));

        // Duplicate Validation (Ignore Current Department)

        if (!existingDepartment.getDepartmentCode().equals(departmentRequestDTO.getDepartmentCode())
                && departmentRepository.existsByDepartmentCode(departmentRequestDTO.getDepartmentCode())) {

            throw new DuplicateResourceException("Department Code already exists.");
        }

        if (!existingDepartment.getDepartmentName().equals(departmentRequestDTO.getDepartmentName())
                && departmentRepository.existsByDepartmentName(departmentRequestDTO.getDepartmentName())) {

            throw new DuplicateResourceException("Department Name already exists.");
        }

        existingDepartment.setDepartmentCode(departmentRequestDTO.getDepartmentCode());
        existingDepartment.setDepartmentName(departmentRequestDTO.getDepartmentName());
        existingDepartment.setDescription(departmentRequestDTO.getDescription());

        Department updatedDepartment = departmentRepository.save(existingDepartment);

        return DepartmentMapper.toResponseDTO(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException("Department not found with id: " + id));

        departmentRepository.delete(department);
    }

    @Override
    public List<DepartmentResponseDTO> searchDepartments(String keyword) {

        return departmentRepository
                .findByDepartmentCodeContainingIgnoreCaseOrDepartmentNameContainingIgnoreCase(
                        keyword,
                        keyword
                )
                .stream()
                .map(DepartmentMapper::toResponseDTO)
                .toList();
    }
}