package dev.jimenez.services;

import dev.jimenez.entities.Employee;
import dev.jimenez.entities.Manager;

import java.util.Set;

public interface ManagerServices {

    Manager createManager(Manager manager);
    public Set<Manager> getAllManagers();
    public Manager getManagerById(int managerId);
    public Manager getManagerByCredentials(String username,String password);
    Manager updateManager(Manager manager);
    boolean deleteManager(int managerId);
}
