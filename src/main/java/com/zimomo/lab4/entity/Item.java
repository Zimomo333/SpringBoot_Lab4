package com.zimomo.lab4.entity;

public class Item {
    int Item_Id;
    String ItemName;
    double ItemPrice;
    int ResQuantity;

    public int getItem_Id() {
        return Item_Id;
    }

    public void setItem_Id(int item_Id) {
        Item_Id = item_Id;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public double getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(double itemPrice) {
        ItemPrice = itemPrice;
    }

    public int getResQuantity() {
        return ResQuantity;
    }

    public void setResQuantity(int resQuantity) {
        ResQuantity = resQuantity;
    }
}
