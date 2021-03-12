package dev.jimenez.services;

import dev.jimenez.entities.Expense;

import java.util.Set;

public interface ExpenseServices {

    Expense createExpense(int employeeId, Expense expense);
    Set<Expense> getAllExpenses();
    Set<Expense> getAllExpensesByEmployee(int employeeId);

    Expense getExpenseById(int employeeId,int expenseId);
    Expense updateExpense(int employeeId,Expense expense);
    Expense approveExpense(int employeeId,int expenseId);
    Expense denyExpense(int employeeId,int expenseId);
    Expense addStatusReason(int employeeId,int expenseId,String reason);
    boolean deleteExpense(int employeeId,int expenseId);

}
