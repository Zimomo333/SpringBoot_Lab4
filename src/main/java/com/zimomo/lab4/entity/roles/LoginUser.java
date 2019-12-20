package com.zimomo.lab4.entity.roles;

public class LoginUser {
    private String Username;
    private String Password;
    private int Employee_Id;
    private String RoleName;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getEmployee_Id() {
        return Employee_Id;
    }

    public void setEmployee_Id(int employee_Id) {
        Employee_Id = employee_Id;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }
}
