package dev.jimenez.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import dev.jimenez.daos.EmployeeDaoPostgres;
import dev.jimenez.daos.ExpenseDaoPostgres;
import dev.jimenez.entities.Employee;
import dev.jimenez.entities.Expense;
import dev.jimenez.services.EmployeeServiceImpl;
import dev.jimenez.services.EmployeeServices;
import dev.jimenez.services.ExpenseServiceImpl;
import dev.jimenez.services.ExpenseServices;
import dev.jimenez.utils.JwtUtil;
import io.javalin.http.Handler;
import org.apache.log4j.Logger;

import java.util.Set;

public class ExpenseController {

    private ExpenseServices expenseServices = new ExpenseServiceImpl(new ExpenseDaoPostgres());
    private EmployeeServices employeeServices = new EmployeeServiceImpl(new EmployeeDaoPostgres());
    private static Logger logger = Logger.getLogger(ExpenseServiceImpl.class.getName());

    public Handler getAllExpensesByEmployeeHandler = ctx -> {

        int employeeId = Integer.parseInt(ctx.pathParam("eid"));
        Set<Expense> allExpensesByEmployee = this.expenseServices.getAllExpensesByEmployee(employeeId);
        Employee employee = this.employeeServices.getEmployeeById(employeeId);

        if (employee == null) {
            ctx.result("Employee not found");
            ctx.status(404);

        }else{
            Gson gson = new Gson();
            String expensesJson = gson.toJson(allExpensesByEmployee);
            ctx.result(expensesJson);
            ctx.status(200);
            logger.info("All expenses for employee "+employeeId+" were retrieved");

        }
    };

    public Handler getAllExpensesHandler = ctx -> {

        Set<Expense> allExpenses = this.expenseServices.getAllExpenses();

            Gson gson = new Gson();
            String expensesJson = gson.toJson(allExpenses);
            ctx.result(expensesJson);
            ctx.status(200);
            logger.info("All expenses were retrieved");


    };


    public Handler getExpenseByIdHandler = ctx -> {
        int employeeId = Integer.parseInt(ctx.pathParam("eid"));
        int expenseId = Integer.parseInt(ctx.pathParam("exid"));
        Expense expense = this.expenseServices.getExpenseById(employeeId,expenseId);

        if(expense == null){
            ctx.result("Expense was not found");
            ctx.status(404);
        }else{
            Gson gson = new Gson();
            String expenseJson = gson.toJson(expense) ;
            ctx.result(expenseJson);
            ctx.status(200);
            logger.info("Expense from employee "+employeeId+" and expense ID "+expenseId+" was retrieved");

        }
    };

    public Handler createExpenseHandler = ctx -> {
        int employeeId = Integer.parseInt(ctx.pathParam("eid"));
        String body = ctx.body();
        Gson gson = new Gson();

        Expense expense = gson.fromJson(body,Expense.class);
        this.expenseServices.createExpense(employeeId,expense);
        String json = gson.toJson(expense);
        ctx.result(json);
        ctx.status(201);
        logger.info("A new expense was created for employee ID "+employeeId+".");


    };

    public Handler updateExpenseHandler = ctx -> {
        int employeeId = Integer.parseInt(ctx.pathParam("eid"));
        int expenseId = Integer.parseInt(ctx.pathParam("exid"));
        Expense expense = this.expenseServices.getExpenseById(employeeId,expenseId);

        if (expense == null) {
            ctx.result("Expense was not found");
            ctx.status(404);
        }else{
            String body = ctx.body();
            Gson gson = new Gson();
            expense = gson.fromJson(body,Expense.class);
            expense.setExpenseId(expenseId);
            this.expenseServices.updateExpense(employeeId,expense);
            String json = gson.toJson(expense);
            ctx.result(json);
            ctx.status(200);
            logger.info("Expense "+expenseId+" for employee "+employeeId+" was successfully updated");

        }
    };

    public Handler deleteExpenseHandler = ctx -> {

        int employeeId = Integer.parseInt(ctx.pathParam("eid"));
        int expenseId = Integer.parseInt(ctx.pathParam("exid"));
        Expense expense = this.expenseServices.getExpenseById(employeeId,expenseId);

        if(expense == null){
            ctx.result("Expense was not found.");
            ctx.status(404);
        }else{
            boolean result = this.expenseServices.deleteExpense(employeeId,expenseId);
            if(result == true){
                ctx.result("Expense was successfully deleted.");
                ctx.status(200);
                logger.info("Expense "+expenseId+" for employee "+employeeId+"was successfully deleted.");

            }else{
                ctx.result("Unable to delete expense successfully.");
            }

        }


    };
    public Handler approveExpenseHandler = ctx -> {
        int employeeId = Integer.parseInt(ctx.pathParam("eid"));
        int expenseId = Integer.parseInt(ctx.pathParam("exid"));
        Expense expense = this.expenseServices.getExpenseById(employeeId,expenseId);

        try {
            String jwt = ctx.header("Authorization");
            DecodedJWT decodedJWT = JwtUtil.isValidJWT(jwt);
            if (decodedJWT.getClaim("role").asString().equals("manager")) {

                if (expense == null) {
                    ctx.result("Expense was not found");
                    ctx.status(404);
                } else {
                    expense = this.expenseServices.approveExpense(employeeId, expenseId);
                    Gson gson = new Gson();
                    String json = gson.toJson(expense);
                    ctx.result(json);
                    ctx.status(200);
                    logger.info("Expense " + expenseId + " for employee " + employeeId + " was successfully approved");

                }
            }
        }catch(Exception e){
            e.printStackTrace();
            ctx.status(403);
            ctx.result("Missing authorization or improper token");
        }
    };
    public Handler denyExpenseHandler = ctx -> {
        int employeeId = Integer.parseInt(ctx.pathParam("eid"));
        int expenseId = Integer.parseInt(ctx.pathParam("exid"));
        Expense expense = this.expenseServices.getExpenseById(employeeId,expenseId);
        try {

            String jwt = ctx.header("Authorization");
            DecodedJWT decodedJWT = JwtUtil.isValidJWT(jwt);
            if (decodedJWT.getClaim("role").asString().equals("manager")) {

                if (expense == null) {
                    ctx.result("Expense was not found");
                    ctx.status(404);
                } else {
                    Expense expense1 = this.expenseServices.denyExpense(employeeId, expenseId);
                    Gson gson = new Gson();

                    String json = gson.toJson(expense1);
                    ctx.result(json);
                    ctx.status(200);
                    logger.info("Expense " + expenseId + " for employee " + employeeId + " was successfully denied");

                }
            }
        }catch(Exception e){
            e.printStackTrace();
            ctx.status(403);
            ctx.result("Missing Authorization or improper token");
        }
    };

}
