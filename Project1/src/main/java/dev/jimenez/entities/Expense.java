package dev.jimenez.entities;

public class Expense {

    private int expenseId;
    private int employeeId;
    private double amount;
    private String reason;
    private String status;
    private long dateSubmitted;
    private long dateApproved;
    private String statusReason;

    public Expense(){}

    public Expense(int expenseId, int employeeId, double amount, String reason, String status, long dateSubmitted, long dateApproved, String statusReason) {
        this.expenseId = expenseId;
        this.employeeId = employeeId;
        this.amount = amount;
        this.reason = reason;
        this.status = status;
        this.dateSubmitted = dateSubmitted;
        this.dateApproved = dateApproved;
        this.statusReason = statusReason;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(long dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public long getDateApproved() {
        return dateApproved;
    }

    public void setDateApproved(long dateApproved) {
        this.dateApproved = dateApproved;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", employeeId=" + employeeId +
                ", amount=" + amount +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                ", dateSubmitted=" + dateSubmitted +
                ", dateApproved=" + dateApproved +
                ", statusReason='" + statusReason + '\'' +
                '}';
    }
}

