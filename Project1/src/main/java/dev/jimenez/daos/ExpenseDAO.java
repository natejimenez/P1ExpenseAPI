package dev.jimenez.daos;

import dev.jimenez.entities.Expense;

import java.util.Set;

public interface ExpenseDAO {

    Expense createExpense(int employeeId,Expense expense);
    Set <Expense> getAllExpenses();
    Set<Expense> getAllExpensesByEmployeeId(int employeeId);
    Expense getExpenseById(int employeeId, int expenseId);
    Expense updateExpense(int employeeId, Expense expense);
    boolean deleteExpense(int employeeId, int expenseId);
}
