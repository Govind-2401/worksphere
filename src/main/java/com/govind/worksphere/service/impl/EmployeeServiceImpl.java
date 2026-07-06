package com.govind.worksphere.service.impl;

import com.govind.worksphere.entity.Employee;
import com.govind.worksphere.exception.EmployeeNotFoundException;
import com.govind.worksphere.repository.EmployeeRepository;
import com.govind.worksphere.service.EmployeeService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));

        existingEmployee.setEmployeeCode(employee.getEmployeeCode());
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhone(employee.getPhone());
        existingEmployee.setGender(employee.getGender());
        existingEmployee.setJoiningDate(employee.getJoiningDate());
        existingEmployee.setEmploymentStatus(employee.getEmploymentStatus());

        return employeeRepository.save(existingEmployee);
    }
}