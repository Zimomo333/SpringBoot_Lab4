package com.zimomo.lab4.entity.purchase;

import java.util.Date;
import java.util.List;

public class Purchase {
    private int Order_Id;
    private int Purchase_Id;
    private List<Purchase_Item> purchase_itemList;
    private Date date;
    private Boolean Finish;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Purchase_Item> getPurchase_itemList() {
        return purchase_itemList;
    }

    public void setPurchase_itemList(List<Purchase_Item> purchase_itemList) {
        this.purchase_itemList = purchase_itemList;
    }

    public int getOrder_Id() {
        return Order_Id;
    }

    public void setOrder_Id(int order_Id) {
        Order_Id = order_Id;
    }

    public int getPurchase_Id() {
        return Purchase_Id;
    }

    public void setPurchase_Id(int purchase_Id) {
        Purchase_Id = purchase_Id;
    }

    public Boolean getFinish() {
        return Finish;
    }

    public void setFinish(Boolean finish) {
        Finish = finish;
    }
}
