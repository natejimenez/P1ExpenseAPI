package dev.jimenez.services;

import dev.jimenez.daos.EmployeeDAO;
import dev.jimenez.entities.Employee;

import java.util.Set;

public class EmployeeServiceImpl implements EmployeeServices{

    private EmployeeDAO edao;
    public EmployeeServiceImpl(EmployeeDAO employeeDAO){this.edao = employeeDAO;}


    @Override
    public Employee createEmployee(Employee employee) {
        this.edao.createEmployee(employee);
        return employee;
    }

    @Override
    public Set<Employee> getAllEmployees() {
        return this.edao.getAllEmployees();
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        return this.edao.getEmployeeById(employeeId);
    }

    @Override
    public Employee getEmployeeByCredentials(String username, String password) {
        Employee e  = null;
        Set<Employee> allEmployees = this.edao.getAllEmployees();
        for(Employee employee : allEmployees){
            if(employee.getUsername().equals(username) && employee.getPassword().equals(password)){
                e = employee;
            }
        }
        return e;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return this.edao.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployee(int employeeId) {
        return this.edao.deleteClientById(employeeId);
    }
}
