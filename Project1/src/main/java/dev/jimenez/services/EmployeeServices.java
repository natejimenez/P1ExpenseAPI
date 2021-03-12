package dev.jimenez.services;

import dev.jimenez.entities.Employee;

import java.util.Set;

public interface EmployeeServices {

    Employee createEmployee(Employee employee);
    Set<Employee> getAllEmployees();
    Employee getEmployeeByCredentials(String username,String password);
    Employee getEmployeeById(int employeeId);
    Employee updateEmployee(Employee employee);
    boolean deleteEmployee(int employeeId);
}
