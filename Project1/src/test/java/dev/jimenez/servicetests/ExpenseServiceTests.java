package dev.jimenez.servicetests;

import dev.jimenez.daos.ExpenseDAO;
import dev.jimenez.entities.Expense;
import dev.jimenez.services.ExpenseServiceImpl;
import dev.jimenez.services.ExpenseServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTests {

    @Mock
    ExpenseDAO expenseDAO = null;

    private ExpenseServices expenseServices = null;
    private static Map<Integer,Expense> expenseTable = new HashMap<Integer, Expense>();

    @BeforeEach
    void setUp(){

        Expense expense1 = new Expense(1,2,10.75,"gas","pending",0,0,"N/A");
        Expense expense2 = new Expense(2,3,100,"hotel","pending",0,0,"N/A");
        Expense expense3 = new Expense(3,5,78.25,"food","pending",0,0,"N/A");
        Expense expense4 = new Expense(4,5,11.10,"snacks","pending",0,0,"N/A");

        Set<Expense> allExpenses = new HashSet<Expense>(expenseTable.values());
        allExpenses.add(expense1);
        allExpenses.add(expense2);
        allExpenses.add(expense3);
        allExpenses.add(expense4);

        for(Expense e : allExpenses){
            if(e.getEmployeeId()==2){
                expenseTable.put(expense1.getExpenseId(),expense1);
            }
        }
        Mockito.when(expenseDAO.getExpenseById(expense1.getEmployeeId(),expense1.getExpenseId())).thenReturn(expense1);
        Mockito.when(expenseDAO.updateExpense(expense1.getEmployeeId(),expense1)).thenReturn(expense1);
        this.expenseServices = new ExpenseServiceImpl(this.expenseDAO);

    }

    @Test
    void approve_expense(){
        Expense expense = this.expenseServices.approveExpense(2,1);
        System.out.println(expense);
        Assertions.assertEquals("Approved",expense.getStatus());
    }
    @Test
    void deny_expense(){
        Expense expense = this.expenseServices.denyExpense(2,1);
        System.out.println(expense);
        Assertions.assertEquals("Denied",expense.getStatus());
    }

    @Test
    void set_status_reason(){
        Expense expense = this.expenseServices.addStatusReason(2,1,"We cover gas expenses");
        System.out.println(expense);
        Assertions.assertNotEquals("N/A",expense.getStatusReason());
    }
}
