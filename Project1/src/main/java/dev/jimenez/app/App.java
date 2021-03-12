package dev.jimenez.app;

import dev.jimenez.controllers.EmployeeController;
import dev.jimenez.controllers.ExpenseController;
import dev.jimenez.controllers.LoginController;
import io.javalin.Javalin;

public class App {

    public static void main(String[] args) {

        Javalin app = Javalin.create(
                config ->{
                    config.enableCorsForAllOrigins();
                    // allows the server to process JavaScript requests from anywhere
                });

        EmployeeController employeeController = new EmployeeController();
        ExpenseController expenseController = new ExpenseController();
        LoginController loginController = new LoginController();
        // GET/employees returns all employees
        app.get("/employees",employeeController.getAllEmployeesHandler);

        //GET /employee/12 gets an employee with ID of 12
        app.get("/employees/:eid",employeeController.getEmployeeByIdHandler);

        //POST /employees should create a new employee
        app.post("/employees",employeeController.createEmployeeHandler);

        // PUT/employees/12 should update employee 12
        app.put("/employees/:eid",employeeController.updateEmployeeHandler);

        //DELETE /employees/12 should delete employee 12
        app.delete("employees/:eid",employeeController.deleteEmployeeHandler);

        // POST/employees/5/expenses should create a new expense for employee 5

        app.post("/employees/:eid/expenses",expenseController.createExpenseHandler);

        // GET /expenses gets all expenses, regardless of employee
        app.get("/expenses",expenseController.getAllExpensesHandler);

        //GET /employees/5/expenses gets all expenses for employee 5
        app.get("/employees/:eid/expenses",expenseController.getAllExpensesByEmployeeHandler);

        //GET /employees/5/expenses/2 gets expense 2 for employee 5
        app.get("/employees/:eid/expenses/:exid",expenseController.getExpenseByIdHandler);

        //PUT /employees/5/expenses/2 updates expense 2 for employee 5
        app.put("/employees/:eid/expenses/:exid",expenseController.updateExpenseHandler);

        // DELETE /employees/5/expenses/2 deletes expense 2 for employee 5
        app.delete("/employees/:eid/expenses/:exid",expenseController.deleteExpenseHandler);

        // PUT /employees/5/expenses/2/approve approves expense 2 for employee 5
        app.put("/employees/:eid/expenses/:exid/approve",expenseController.approveExpenseHandler);

        // PUT /employees/5/expenses/2/deny denies expense 2 for employee 5
        app.put("/employees/:eid/expenses/:exid/deny",expenseController.denyExpenseHandler);

        app.post("/login",loginController.loginHandler);

        app.start();
        // starts web server


    }
}
