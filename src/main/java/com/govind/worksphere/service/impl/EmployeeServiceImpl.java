package com.govind.worksphere.service.impl;

import com.govind.worksphere.dto.EmployeeRequestDTO;
import com.govind.worksphere.dto.EmployeeResponseDTO;
import com.govind.worksphere.entity.Employee;
import com.govind.worksphere.exception.DuplicateResourceException;
import com.govind.worksphere.exception.EmployeeNotFoundException;
import com.govind.worksphere.mapper.EmployeeMapper;
import com.govind.worksphere.repository.EmployeeRepository;
import com.govind.worksphere.service.EmployeeService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponseDTO saveEmployee(EmployeeRequestDTO employeeRequestDTO) {

        // Duplicate Validation
        if (employeeRepository.existsByEmployeeCode(employeeRequestDTO.getEmployeeCode())) {
            throw new DuplicateResourceException("Employee Code already exists.");
        }

        if (employeeRepository.existsByEmail(employeeRequestDTO.getEmail())) {
            throw new DuplicateResourceException("Email already exists.");
        }

        if (employeeRepository.existsByPhone(employeeRequestDTO.getPhone())) {
            throw new DuplicateResourceException("Phone number already exists.");
        }

        Employee employee = EmployeeMapper.toEntity(employeeRequestDTO);

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.toResponseDTO(savedEmployee);
    }

    @Override
    public Page<EmployeeResponseDTO> getAllEmployees(
            int page,
            int size,
            String sortBy,
            String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return employeeRepository.findAll(pageable)
                .map(EmployeeMapper::toResponseDTO);
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee not found with id: " + id));

        return EmployeeMapper.toResponseDTO(employee);
    }

    @Override
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO employeeRequestDTO) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee not found with id: " + id));

        // Duplicate Validation (Ignore Current Employee)

        if (!existingEmployee.getEmployeeCode().equals(employeeRequestDTO.getEmployeeCode())
                && employeeRepository.existsByEmployeeCode(employeeRequestDTO.getEmployeeCode())) {
            throw new DuplicateResourceException("Employee Code already exists.");
        }

        if (!existingEmployee.getEmail().equals(employeeRequestDTO.getEmail())
                && employeeRepository.existsByEmail(employeeRequestDTO.getEmail())) {
            throw new DuplicateResourceException("Email already exists.");
        }

        if (!existingEmployee.getPhone().equals(employeeRequestDTO.getPhone())
                && employeeRepository.existsByPhone(employeeRequestDTO.getPhone())) {
            throw new DuplicateResourceException("Phone number already exists.");
        }

        existingEmployee.setEmployeeCode(employeeRequestDTO.getEmployeeCode());
        existingEmployee.setFirstName(employeeRequestDTO.getFirstName());
        existingEmployee.setLastName(employeeRequestDTO.getLastName());
        existingEmployee.setEmail(employeeRequestDTO.getEmail());
        existingEmployee.setPhone(employeeRequestDTO.getPhone());
        existingEmployee.setGender(employeeRequestDTO.getGender());
        existingEmployee.setJoiningDate(employeeRequestDTO.getJoiningDate());
        existingEmployee.setEmploymentStatus(employeeRequestDTO.getEmploymentStatus());
        existingEmployee.setDepartment(employeeRequestDTO.getDepartment());

        Employee updatedEmployee = employeeRepository.save(existingEmployee);

        return EmployeeMapper.toResponseDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee not found with id: " + id));

        employeeRepository.delete(employee);
    }

    @Override
    public List<EmployeeResponseDTO> searchEmployees(String keyword) {

        return employeeRepository
                .findByEmployeeCodeContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                        keyword,
                        keyword,
                        keyword,
                        keyword
                )
                .stream()
                .map(EmployeeMapper::toResponseDTO)
                .toList();
    }
}