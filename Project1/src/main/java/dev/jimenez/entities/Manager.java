package dev.jimenez.entities;

public class Manager {

    private int managerID;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public Manager(){}

    public Manager(int managerID, String username, String password) {
        this.managerID = managerID;
        this.username = username;
        this.password = password;
        this.firstName = "";
        this.lastName = "";
    }

    public Manager(int managerID, String firstName, String lastName, String username, String password) {
        this.managerID = managerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerID=" + managerID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
