package com.govind.worksphere.service.impl;

import com.govind.worksphere.dto.EmployeeRequestDTO;
import com.govind.worksphere.dto.EmployeeResponseDTO;
import com.govind.worksphere.entity.Employee;
import com.govind.worksphere.exception.EmployeeNotFoundException;
import com.govind.worksphere.mapper.EmployeeMapper;
import com.govind.worksphere.repository.EmployeeRepository;
import com.govind.worksphere.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponseDTO saveEmployee(EmployeeRequestDTO employeeRequestDTO) {

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