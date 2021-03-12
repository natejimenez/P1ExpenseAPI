package dev.jimenez.daos;

import dev.jimenez.entities.Employee;
import dev.jimenez.entities.Manager;
import dev.jimenez.utils.ConnectionUtil;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ManagerDaoPostgres implements ManagerDAO{

    @Override
    public Manager createManager(Manager manager) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into manager (first_name,last_name,username,pass_word) values (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,manager.getFirstName());
            ps.setString(2,manager.getLastName());
            ps.setString(3, manager.getUsername());
            ps.setString(4,manager.getPassword());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int key = rs.getInt("employee_id");
            manager.setManagerID(key);
            return manager;

        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return null;

        }


    }

    @Override
    public Set<Manager> getAllManagers() {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from manager";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            Set<Manager> managers = new HashSet<Manager>();

            while(rs.next()){
                Manager manager = new Manager();
                manager.setManagerID(rs.getInt("manager_id"));
                manager.setFirstName(rs.getString("first_name"));
                manager.setLastName(rs.getString("last_name"));
                manager.setUsername(rs.getString("username"));
                manager.setPassword(rs.getString("pass_word"));

                managers.add(manager);
            }
            return managers;

        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }

    }

    @Override
    public Manager getManagerById(int manager_id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from manager where manager_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,manager_id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Manager manager = new Manager();
            manager.setManagerID(rs.getInt("manager_id"));
            manager.setFirstName(rs.getString("first_name"));
            manager.setLastName(rs.getString("last_name"));
            manager.setUsername(rs.getString("username"));
            manager.setPassword(rs.getString("pass_word"));
            return manager;

        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public Manager updateManager(Manager manager) {
        try(Connection conn = ConnectionUtil.createConnection()){

            String sql = "update manager set first_name = ?,last_name = ?,username = ?,pass_word = ? where manager_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, manager.getFirstName());
            ps.setString(2,manager.getLastName());
            ps.setString(3,manager.getUsername());
            ps.setString(4,manager.getPassword());
            ps.setInt(5,manager.getManagerID());
            ps.executeUpdate();
            return manager;
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean deleteManagerById(int manager_id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "delete from manager where manager_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,manager_id);
            ps.execute();
            return true;
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return false;
        }
    }
}
