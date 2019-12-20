package com.zimomo.lab4.entity.roles;

public class Salesman {
    private int Sales_Id;
    private String Name;
    private String Sex;
    private String Telephone;
    private String Email;

    public int getSales_Id() {
        return Sales_Id;
    }

    public void setSales_Id(int sales_Id) {
        Sales_Id = sales_Id;
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
