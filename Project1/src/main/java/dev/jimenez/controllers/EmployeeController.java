package dev.jimenez.controllers;

import com.google.gson.Gson;
import dev.jimenez.daos.EmployeeDaoPostgres;
import dev.jimenez.entities.Employee;
import dev.jimenez.services.EmployeeServiceImpl;
import dev.jimenez.services.EmployeeServices;
import io.javalin.http.Handler;
import org.apache.log4j.Logger;

import java.util.Set;

public class EmployeeController {

    private EmployeeServices employeeServices = new EmployeeServiceImpl(new EmployeeDaoPostgres());
    private static Logger logger = Logger.getLogger(EmployeeServiceImpl.class.getName());

    public Handler getAllEmployeesHandler = ctx -> {
        Set<Employee> allEmployees = this.employeeServices.getAllEmployees();
        Gson gson = new Gson();
        String employeesJson = gson.toJson(allEmployees);
        ctx.result(employeesJson);
        ctx.status(200);
        logger.info("All employees were retrieved");

    };

    public Handler getEmployeeByIdHandler = ctx -> {
        int employeeId = Integer.parseInt(ctx.pathParam("eid"));
        Employee employee = this.employeeServices.getEmployeeById(employeeId);

        if(employee == null){
            ctx.result("Employee not found");
            ctx.status(404);
            logger.error("404: Employee was not found");
        }else{
            Gson gson = new Gson();
            String employeeJson = gson.toJson(employee);
            ctx.result(employeeJson);
            ctx.status(200);
            logger.info("The employee with ID of "+employeeId+" was retrieved.");
        }
    };

    public Handler createEmployeeHandler = ctx -> {
        String body = ctx.body();
        Gson gson = new Gson();

        Employee employee = gson.fromJson(body,Employee.class);

        this.employeeServices.createEmployee(employee);
        String json = gson.toJson(employee);
        ctx.result(json);
        ctx.status(201);
        logger.info("An employee was created for "+employee.getFirstName()+" "+employee.getLastName());
    };

    public Handler updateEmployeeHandler = ctx -> {

        int employeeId = Integer.parseInt(ctx.pathParam("eid"));
        Employee employee = this.employeeServices.getEmployeeById(employeeId);

        if(employee == null){
            ctx.result("Employee not found");
            ctx.status(404);
            logger.error("404: Update attempted but employee not found");

        }else{
            String body = ctx.body();
            Gson gson = new Gson();
            employee = gson.fromJson(body,Employee.class);
            employee.setEmployeeID(employeeId);
            this.employeeServices.updateEmployee(employee);
            String json = gson.toJson(employee);
            ctx.result(json);
            ctx.status(200);
            logger.info("The employee "+employee.getFirstName()+" "+employee.getLastName()+" was updated.");

        }
    };

    public Handler deleteEmployeeHandler = ctx -> {

        int employeeId = Integer.parseInt(ctx.pathParam("eid"));
        Employee employee = this.employeeServices.getEmployeeById(employeeId);

        if(employee == null){
            ctx.result("Employee not found, unable to delete.");
            ctx.status(404);
            logger.error("404: Employee was unable to be deleted, not found");
        }else{
            boolean result = this.employeeServices.deleteEmployee(employeeId);
            if(result == true){
                ctx.result("Employee was successfully deleted.");
                ctx.status(200);
                logger.info("The employee with the ID of "+employeeId+" was deleted.");

            }else{
                ctx.result("Unable to delete client successfully.");
                logger.error("Unable to delete client");
            }
        }
    };
}
