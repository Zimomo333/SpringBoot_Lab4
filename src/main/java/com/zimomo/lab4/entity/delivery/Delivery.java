package com.zimomo.lab4.entity.delivery;

import java.util.Date;
import java.util.List;

public class Delivery {
    private int Order_Id;
    private int Delivery_Id;
    private List<Delivery_Item> delivery_itemList;
    private Date date;
    private int State;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Delivery_Item> getDelivery_itemList() {
        return delivery_itemList;
    }

    public void setDelivery_itemList(List<Delivery_Item> delivery_itemList) {
        this.delivery_itemList = delivery_itemList;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public int getOrder_Id() {
        return Order_Id;
    }

    public void setOrder_Id(int order_Id) {
        Order_Id = order_Id;
    }

    public int getDelivery_Id() {
        return Delivery_Id;
    }

    public void setDelivery_Id(int delivery_Id) {
        Delivery_Id = delivery_Id;
    }
}
