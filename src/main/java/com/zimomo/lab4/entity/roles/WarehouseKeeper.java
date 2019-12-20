package com.zimomo.lab4.entity.roles;

public class WarehouseKeeper {
    private int Keeper_Id;
    private String Name;
    private String Sex;
    private String Telephone;
    private String Email;

    public int getKeeper_Id() {
        return Keeper_Id;
    }

    public void setKeeper_Id(int keeper_Id) {
        Keeper_Id = keeper_Id;
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
