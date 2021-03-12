package dev.jimenez.services;

import dev.jimenez.daos.ExpenseDAO;
import dev.jimenez.entities.Expense;

import java.util.Set;

public class ExpenseServiceImpl implements ExpenseServices {

    private ExpenseDAO exdao;
    public ExpenseServiceImpl(ExpenseDAO expenseDAO){this.exdao=expenseDAO;}

    @Override
    public Expense createExpense(int employeeId, Expense expense) {
        expense.setDateSubmitted(System.currentTimeMillis()/1000);
        expense.setStatus("Pending");
        expense.setStatusReason("N/A");
        this.exdao.createExpense(employeeId,expense);
        return expense;
    }

    @Override
    public Set<Expense> getAllExpenses() {
        return this.exdao.getAllExpenses();
    }

    @Override
    public Set<Expense> getAllExpensesByEmployee(int employeeId) {
        return this.exdao.getAllExpensesByEmployeeId(employeeId);
    }

    @Override
    public Expense getExpenseById(int employeeId, int expenseId) {
        return this.exdao.getExpenseById(employeeId,expenseId);
    }

    @Override
    public Expense updateExpense(int employeeId, Expense expense) {
        expense.setDateSubmitted(System.currentTimeMillis()/1000);
        expense.setStatus("Pending");
        return this.exdao.updateExpense(employeeId,expense);
    }

    @Override
    public Expense approveExpense(int employeeId, int expenseId) {
        Expense expense = this.exdao.getExpenseById(employeeId,expenseId);

        expense.setStatus("Approved");
        expense.setDateApproved(System.currentTimeMillis()/1000);
        return this.exdao.updateExpense(employeeId,expense);
    }

    @Override
    public Expense denyExpense(int employeeId, int expenseId) {
        Expense expense = this.exdao.getExpenseById(employeeId,expenseId);
        expense.setStatus("Denied");
        expense.setDateApproved(System.currentTimeMillis()/1000);
        return this.exdao.updateExpense(employeeId,expense);
    }

    @Override
    public Expense addStatusReason(int employeeId, int expenseId, String reason) {
        Expense expense = this.exdao.getExpenseById(employeeId,expenseId);
        expense.setStatusReason(reason);

        return this.exdao.updateExpense(employeeId,expense);
    }

    @Override
    public boolean deleteExpense(int employeeId, int expenseId) {


        return this.exdao.deleteExpense(employeeId,expenseId);
    }
}
