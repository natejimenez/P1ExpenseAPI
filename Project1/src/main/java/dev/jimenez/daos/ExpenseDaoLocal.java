package dev.jimenez.daos;

import dev.jimenez.entities.Expense;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ExpenseDaoLocal implements ExpenseDAO{
    private static Map<Integer,Expense> expenseTable = new HashMap<Integer, Expense>();
    private static int idMaker=0;

    @Override
    public Expense createExpense(int employeeId, Expense expense) {
        expense.setEmployeeId(employeeId);
        expense.setExpenseId(++idMaker);
        expenseTable.put(expense.getExpenseId(),expense);
        return expense;
    }

    @Override
    public Set<Expense> getAllExpenses() {
        Set<Expense> allExpenses = new HashSet<Expense>(expenseTable.values());

        return allExpenses;

    }

    @Override
    public Set<Expense> getAllExpensesByEmployeeId(int employeeId) {
        Set<Expense> allExpenses = new HashSet<Expense>(expenseTable.values());
        Set<Expense> selectedExpenses = new HashSet<Expense>();

        for(Expense e : allExpenses){
            if(e.getEmployeeId()==employeeId){
                selectedExpenses.add(e);
            }
        }
        return selectedExpenses;

    }

    @Override
    public Expense getExpenseById(int employeeId, int expenseId) {

        Expense expense = new Expense();
        Set<Expense> allExpenses = new HashSet<Expense>(expenseTable.values());
        for(Expense e : allExpenses){

            if(e.getEmployeeId()==employeeId && e.getExpenseId()==expenseId){
                expense = e;
            }
        }
        return expense;
    }

    @Override
    public Expense updateExpense(int employeeId, Expense expense) {
        Set<Expense> allExpenses = new HashSet<Expense>(expenseTable.values());

        for(Expense e : allExpenses){
            if(e.getEmployeeId()==employeeId){
                expenseTable.put(expense.getExpenseId(),expense);
            }
        }
        return expense;
    }

    @Override
    public boolean deleteExpense(int employeeId, int expenseId) {
        Set<Expense> allExpenses = new HashSet<Expense>(expenseTable.values());

        for(Expense e : allExpenses){

            if(e.getEmployeeId()==employeeId){
                Expense expense = expenseTable.remove(expenseId);
            }
        }

        return true;
    }
}
