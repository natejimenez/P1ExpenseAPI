package dev.jimenez.daos;

import dev.jimenez.entities.Employee;

import java.util.Set;

public interface EmployeeDAO {

    Employee createEmployee(Employee employee);
    Set<Employee> getAllEmployees();
    Employee getEmployeeById(int employee_id);
    Employee updateEmployee(Employee employee);
    boolean deleteClientById(int employee_id);

}
