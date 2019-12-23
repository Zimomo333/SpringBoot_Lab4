package com.zimomo.lab4.entity.delivery;

public class DeliveryInfo {
    private int Delivery_Id;
    private String Postman;
    private String Telephone;

    public int getDelivery_Id() {
        return Delivery_Id;
    }

    public void setDelivery_Id(int delivery_Id) {
        Delivery_Id = delivery_Id;
    }

    public String getPostman() {
        return Postman;
    }

    public void setPostman(String postman) {
        Postman = postman;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }
}
