package dev.jimenez.daotests;


import dev.jimenez.daos.EmployeeDAO;
import dev.jimenez.daos.EmployeeDaoPostgres;
import dev.jimenez.entities.Employee;
import org.junit.jupiter.api.*;

import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDaoTest {

    private static EmployeeDAO edao = new EmployeeDaoPostgres();
    private static Employee testEmployee = null;

    @Test
    @Order(1)
    void create_employee(){
        Employee employee1 = new Employee(0,"Nate","Jimenez","njimenez","000");
        edao.createEmployee(employee1);
        System.out.println(employee1);
        testEmployee = employee1;
        Assertions.assertNotEquals(0,employee1.getEmployeeID());

    }
    @Test
    @Order(2)
    void get_employee_by_id(){
        int id = testEmployee.getEmployeeID();
        Employee employee = edao.getEmployeeById(id);
        Assertions.assertEquals(testEmployee.getFirstName(),employee.getFirstName());
        System.out.println("The employee retrieved was: "+employee);
    }
    @Test
    @Order(3)
    void update_employee(){
        Employee employee = edao.getEmployeeById(testEmployee.getEmployeeID());
        employee.setLastName("Smith");
        edao.updateEmployee(employee);
        Employee updatedEmployee = edao.getEmployeeById(testEmployee.getEmployeeID());
        System.out.println(updatedEmployee);
        Assertions.assertEquals("Smith",updatedEmployee.getLastName());
    }
    @Test
    @Order(4)
    void get_all_employees(){
        Employee e1 = new Employee(0,"Bob","Mills","bobby","mills");
        Employee e2 = new Employee(0,"Adam","Ranieri","reston","virginia");
        Employee e3 = new Employee(0,"John","Jones","footballjon","one");
        edao.createEmployee(e1);
        edao.createEmployee(e2);
        edao.createEmployee(e3);

        Set<Employee> allEmployees = edao.getAllEmployees();
        System.out.println(allEmployees);
        Assertions.assertTrue(allEmployees.size()>2);

    }
    @Test
    @Order(5)
    void delete_employee(){
        int id = testEmployee.getEmployeeID();
        boolean result = edao.deleteClientById(id);
        Assertions.assertTrue(result);
    }
}
