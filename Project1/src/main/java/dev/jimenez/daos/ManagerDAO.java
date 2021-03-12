package dev.jimenez.daos;

import dev.jimenez.entities.Employee;
import dev.jimenez.entities.Manager;

import java.util.Set;

public interface ManagerDAO {
    Manager createManager(Manager manager);
    Set<Manager> getAllManagers();
    Manager getManagerById(int manager_id);
    Manager updateManager(Manager manager);
    boolean deleteManagerById(int manager_id);

}
