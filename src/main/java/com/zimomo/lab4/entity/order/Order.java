package com.zimomo.lab4.entity.order;

import com.zimomo.lab4.entity.delivery.Delivery;

import java.util.Date;
import java.util.List;

public class Order {
    private int Contract_Id;
    private int Order_Id;
    private List<Order_Item> order_itemList;
    private Date Date;
    private double TotalPrice;
    private Boolean Finish;
    private List<Delivery> deliveryList;

    public List<Delivery> getDeliveryList() {
        return deliveryList;
    }

    public void setDeliveryList(List<Delivery> deliveryList) {
        this.deliveryList = deliveryList;
    }

    public List<Order_Item> getOrder_itemList() {
        return order_itemList;
    }

    public void setOrder_itemList(List<Order_Item> order_itemList) {
        this.order_itemList = order_itemList;
    }

    public int getContract_Id() {
        return Contract_Id;
    }

    public void setContract_Id(int contract_Id) {
        Contract_Id = contract_Id;
    }

    public int getOrder_Id() {
        return Order_Id;
    }

    public void setOrder_Id(int order_Id) {
        Order_Id = order_Id;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public Boolean getFinish() {
        return Finish;
    }

    public void setFinish(Boolean finish) {
        Finish = finish;
    }
}
