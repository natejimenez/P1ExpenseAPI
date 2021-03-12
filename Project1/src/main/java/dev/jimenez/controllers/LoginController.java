package dev.jimenez.controllers;

import com.google.gson.Gson;
import dev.jimenez.daos.EmployeeDaoPostgres;
import dev.jimenez.daos.ManagerDaoPostgres;
import dev.jimenez.entities.Employee;
import dev.jimenez.entities.Manager;
import dev.jimenez.services.EmployeeServiceImpl;
import dev.jimenez.services.EmployeeServices;
import dev.jimenez.services.ManagerServiceImpl;
import dev.jimenez.services.ManagerServices;
import dev.jimenez.utils.JwtUtil;
import io.javalin.http.Handler;

public class LoginController {

    private EmployeeServices employeeServices = new EmployeeServiceImpl(new EmployeeDaoPostgres());
    private ManagerServices managerService = new ManagerServiceImpl(new ManagerDaoPostgres());

    public Handler loginHandler = ctx ->{
        String body = ctx.body();
        Gson gson = new Gson();
        Manager manager = gson.fromJson(body,Manager.class);
        if(manager == null){
            ctx.status(400);
            return;
        }
        manager = this.managerService.getManagerByCredentials(manager.getUsername(),manager.getPassword());
        Employee employee = gson.fromJson(body,Employee.class);
        if(employee == null){
            ctx.status(400);
            return;
        }
        employee = this.employeeServices.getEmployeeByCredentials(employee.getUsername(),employee.getPassword());

        if(manager != null){
            String token = JwtUtil.generate(manager.getManagerID(),"manager",manager.getUsername());
            ctx.cookie("Authorization",token,86400);
            ctx.result(token);
            ctx.status(200);

        }
        else if (employee != null){
            String token = JwtUtil.generate(employee.getEmployeeID(),"employee",employee.getUsername());
            ctx.cookie("Authorization",token,86400);
            ctx.result(token);
            ctx.status(200);
        }else{
            ctx.result("Not found");
            ctx.status(404);
        }
    };

}
