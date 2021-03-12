package dev.jimenez.services;

import dev.jimenez.daos.EmployeeDAO;
import dev.jimenez.daos.ManagerDAO;
import dev.jimenez.entities.Employee;
import dev.jimenez.entities.Manager;

import java.util.Set;

public class ManagerServiceImpl implements ManagerServices{

    private ManagerDAO mdao;
    public ManagerServiceImpl(ManagerDAO managerDAO){this.mdao = managerDAO;}

    @Override
    public Manager createManager(Manager manager) {
        this.mdao.createManager(manager);
        return manager;
    }

    @Override
    public Set<Manager> getAllManagers() {
        return this.mdao.getAllManagers();
    }

    @Override
    public Manager getManagerById(int managerId) {
        return this.mdao.getManagerById(managerId);
    }

    @Override
    public Manager getManagerByCredentials(String username, String password) {
        Manager m  = null;
        Set<Manager> allManagers = this.mdao.getAllManagers();
        for(Manager manager : allManagers){
            if(manager.getUsername().equals(username) && manager.getPassword().equals(password)){
                m = manager;
            }
        }
        return m;

    }

    @Override
    public Manager updateManager(Manager manager) {
        return this.mdao.updateManager(manager);
    }

    @Override
    public boolean deleteManager(int managerId) {
        return this.mdao.deleteManagerById(managerId);
    }
}
