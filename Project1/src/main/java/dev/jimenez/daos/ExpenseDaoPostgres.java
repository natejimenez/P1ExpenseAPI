package dev.jimenez.daos;

import dev.jimenez.entities.Expense;
import dev.jimenez.utils.ConnectionUtil;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ExpenseDaoPostgres implements ExpenseDAO{

    @Override
    public Expense createExpense(int employeeId, Expense expense) {

        try(Connection conn = ConnectionUtil.createConnection()){

            String sql = "insert into expense (employee_id,amount,reason,status,date_submitted,date_approved,status_reason) values (?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1,expense.getEmployeeId());
            ps.setDouble(2,expense.getAmount());
            ps.setString(3, expense.getReason());
            ps.setString(4, expense.getStatus());
            ps.setLong(5,expense.getDateSubmitted());
            ps.setLong(6,expense.getDateApproved());
            ps.setString(7,expense.getStatusReason());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int key = rs.getInt("expense_id");
            expense.setExpenseId(key);
            return expense;

        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }



    }

    @Override
    public Set<Expense> getAllExpensesByEmployeeId(int employeeId) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from expense where employee_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,employeeId);
            ResultSet rs = ps.executeQuery();
            Set<Expense> expenses = new HashSet<Expense>();

            while(rs.next()){
                Expense expense = new Expense();
                expense.setEmployeeId(rs.getInt("employee_id"));
                expense.setAmount(rs.getFloat("amount"));
                expense.setExpenseId(rs.getInt("expense_id"));
                expense.setReason(rs.getString("reason"));
                expense.setStatus(rs.getString("status"));
                expense.setDateApproved(rs.getLong("date_approved"));
                expense.setDateSubmitted(rs.getLong("date_submitted"));
                expense.setStatusReason(rs.getString("status_reason"));
                expenses.add(expense);
            }
            return expenses;


        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return null;

        }

    }


    @Override
    public Set<Expense> getAllExpenses() {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from expense";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Set<Expense> expenses = new HashSet<Expense>();

            while(rs.next()){
                Expense expense = new Expense();
                expense.setEmployeeId(rs.getInt("employee_id"));
                expense.setAmount(rs.getFloat("amount"));
                expense.setExpenseId(rs.getInt("expense_id"));
                expense.setReason(rs.getString("reason"));
                expense.setStatus(rs.getString("status"));
                expense.setDateApproved(rs.getLong("date_approved"));
                expense.setDateSubmitted(rs.getLong("date_submitted"));
                expense.setStatusReason(rs.getString("status_reason"));
                expenses.add(expense);
            }
            return expenses;


        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return null;

        }

    }

    @Override
    public Expense getExpenseById(int employeeId, int expenseId) {

        try(Connection conn = ConnectionUtil.createConnection()){

            String sql = "select * from expense where employee_id = ? and expense_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,employeeId);
            ps.setInt(2,expenseId);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Expense expense = new Expense();
            expense.setEmployeeId(rs.getInt("employee_id"));
            expense.setAmount(rs.getFloat("amount"));
            expense.setExpenseId(rs.getInt("expense_id"));
            expense.setReason(rs.getString("reason"));
            expense.setStatus(rs.getString("status"));
            expense.setDateApproved(rs.getLong("date_approved"));
            expense.setDateSubmitted(rs.getLong("date_submitted"));
            expense.setStatusReason(rs.getString("status_reason"));
            return expense;

        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }

    }

    @Override
    public Expense updateExpense(int employeeId, Expense expense) {

        try(Connection conn = ConnectionUtil.createConnection()){

            String sql = "update expense set employee_id = ?,amount = ?,reason = ?,status = ?,date_submitted = ?,date_approved = ?,status_reason = ? where expense_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,expense.getEmployeeId());
            ps.setDouble(2,expense.getAmount());
            ps.setString(3,expense.getReason());
            ps.setString(4,expense.getStatus());
            ps.setLong(5,expense.getDateSubmitted());
            ps.setLong(6,expense.getDateApproved());
            ps.setString(7,expense.getStatusReason());
            ps.setInt(8,expense.getExpenseId());

            ps.executeUpdate();
            return expense;

        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return expense;
        }

    }

    @Override
    public boolean deleteExpense(int employeeId, int expenseId) {

        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "delete from expense where employee_id = ? and expense_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,employeeId);
            ps.setInt(2,expenseId);
            ps.execute();
            return true;

        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return false;

        }

    }
}
