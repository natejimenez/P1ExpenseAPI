package dev.jimenez.daotests;

import dev.jimenez.daos.ExpenseDAO;
import dev.jimenez.daos.ExpenseDaoPostgres;
import dev.jimenez.entities.Expense;
import org.junit.jupiter.api.*;

import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseDaoTest {

    private static ExpenseDAO exdao = new ExpenseDaoPostgres();
    private static Expense testExpense = null;

    @Test
    @Order(1)
    void create_expense(){

        Expense expense1 = new Expense(0,2,1.5,"gas","pending",0,0,"N/A");
        exdao.createExpense(expense1.getEmployeeId(),expense1);
        System.out.println(expense1);
        testExpense = expense1;
        Assertions.assertNotEquals(0,expense1.getExpenseId());

    }

    @Test
    @Order(2)
    void get_all_expenses_by_employee_id(){
        Expense expense1 = new Expense(0,2,100.50,"company party","pending",0,0,"N/A");
        Expense expense2 = new Expense(0,2,75.25,"hotel","pending",0,0,"N/A");
        Expense expense3 = new Expense(0,2,10.10,"gas","pending",0,0,"N/A");

        exdao.createExpense(expense1.getEmployeeId(),expense1);
        exdao.createExpense(expense2.getEmployeeId(),expense2);
        exdao.createExpense(expense3.getEmployeeId(),expense3);
        Set<Expense> allEmployeeExpenses = exdao.getAllExpensesByEmployeeId(2);
        Assertions.assertTrue(allEmployeeExpenses.size()>2);

    }

    @Test
    @Order(3)
    void get_one_expense(){
//        Expense expense1 = new Expense(0,1,100.50,"company party","pending",0,0,"N/A");
//        Expense expense2 = new Expense(0,1,75.25,"hotel","pending",0,0,"N/A");
//        Expense expense3 = new Expense(0,1,10.10,"gas","pending",0,0,"N/A");
//
//        exdao.createExpense(expense1.getEmployeeId(),expense1);
//        exdao.createExpense(expense2.getEmployeeId(),expense2);
//        exdao.createExpense(expense3.getEmployeeId(),expense3);
//        testExpense = expense1;

        int expenseId = testExpense.getExpenseId();
        int employeeId = testExpense.getEmployeeId();
        Expense foundExpense = exdao.getExpenseById(employeeId,expenseId);

        Assertions.assertEquals(testExpense.getReason(),foundExpense.getReason());
    }

    @Test
    @Order(4)
    void update_expense(){
//        Expense expense1 = new Expense(0,1,100.50,"company party","pending",0,0,"N/A");
//        Expense expense2 = new Expense(0,1,75.25,"hotel","pending",0,0,"N/A");
//        Expense expense3 = new Expense(0,1,10.10,"gas","pending",0,0,"N/A");
//
//        exdao.createExpense(expense1.getEmployeeId(),expense1);
//        exdao.createExpense(expense2.getEmployeeId(),expense2);
//        exdao.createExpense(expense3.getEmployeeId(),expense3);
//        testExpense = expense1;

        Expense expense = exdao.getExpenseById(testExpense.getEmployeeId(), testExpense.getExpenseId());
        expense.setReason("gas money");
        exdao.updateExpense(testExpense.getEmployeeId(), expense);

        Expense updatedExpense = exdao.getExpenseById(testExpense.getEmployeeId(), testExpense.getExpenseId());
        Assertions.assertEquals("gas money",updatedExpense.getReason());

    }

    @Test
    @Order(5)
    void delete_expense(){
//        Expense expense1 = new Expense(0,1,100.50,"company party","pending",0,0,"N/A");
//        testExpense = expense1;
        int employeeId = testExpense.getEmployeeId();
        int expenseId = testExpense.getExpenseId();
        boolean result = exdao.deleteExpense(employeeId,expenseId);
        Assertions.assertTrue(result);
    }

}
