package com.govind.worksphere.service;

import com.govind.worksphere.entity.Employee;
import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

}