package com.zimomo.lab4.entity.roles;

public class SalesManager {
    private int Manager_Id;
    private String Name;
    private String Sex;
    private String Telephone;
    private String Email;

    public int getManager_Id() {
        return Manager_Id;
    }

    public void setManager_Id(int manager_Id) {
        Manager_Id = manager_Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
