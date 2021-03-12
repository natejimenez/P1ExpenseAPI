package dev.jimenez.daos;

import dev.jimenez.entities.Employee;
import dev.jimenez.utils.ConnectionUtil;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class EmployeeDaoPostgres implements EmployeeDAO {

    @Override
    public Employee createEmployee(Employee employee) {

        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into employee (first_name,last_name,username,pass_word) values (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,employee.getFirstName());
            ps.setString(2,employee.getLastName());
            ps.setString(3, employee.getUsername());
            ps.setString(4,employee.getPassword());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int key = rs.getInt("employee_id");
            employee.setEmployeeID(key);
            return employee;

        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return null;

        }
    }

    @Override
    public Set<Employee> getAllEmployees() {

        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from employee";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            Set<Employee> employees = new HashSet<Employee>();

            while(rs.next()){
                Employee employee = new Employee();
                employee.setEmployeeID(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setUsername(rs.getString("username"));
                employee.setPassword(rs.getString("pass_word"));

                employees.add(employee);
            }
            return employees;

        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }

    }

    @Override
    public Employee getEmployeeById(int employee_id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from employee where employee_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,employee_id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Employee employee = new Employee();
            employee.setEmployeeID(rs.getInt("employee_id"));
            employee.setFirstName(rs.getString("first_name"));
            employee.setLastName(rs.getString("last_name"));
            employee.setUsername(rs.getString("username"));
            employee.setPassword(rs.getString("pass_word"));
            return employee;

        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }

    }

    @Override
    public Employee updateEmployee(Employee employee) {

        try(Connection conn = ConnectionUtil.createConnection()){

            String sql = "update employee set first_name = ?,last_name = ?,username = ?,pass_word = ? where employee_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getFirstName());
            ps.setString(2,employee.getLastName());
            ps.setString(3,employee.getUsername());
            ps.setString(4,employee.getPassword());
            ps.setInt(5,employee.getEmployeeID());
            ps.executeUpdate();
            return employee;
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean deleteClientById(int employee_id) {

        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "delete from employee where employee_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,employee_id);
            ps.execute();
            return true;
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return false;
        }

    }
}
