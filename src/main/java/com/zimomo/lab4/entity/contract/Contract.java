package com.zimomo.lab4.entity.contract;

import com.zimomo.lab4.entity.order.Order;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Contract {
    private int Contract_Id;
    private int Sales_Id;
    private int Customer_Id;
    private Date Date_Begin;
    private Date Date_End;
    private Boolean Edit;
    private Boolean Finish;
    private List<Contract_Item> contract_itemList;
    private List<Order> orderList;
    private HashMap<String,Integer> hashMap;

    public HashMap<String, Integer> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<String, Integer> hashMap) {
        this.hashMap = hashMap;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Boolean getEdit() {
        return Edit;
    }

    public void setEdit(Boolean edit) {
        Edit = edit;
    }

    public int getContract_Id() {
        return Contract_Id;
    }

    public void setContract_Id(int contract_Id) {
        Contract_Id = contract_Id;
    }

    public int getSales_Id() {
        return Sales_Id;
    }

    public void setSales_Id(int sales_Id) {
        Sales_Id = sales_Id;
    }

    public int getCustomer_Id() {
        return Customer_Id;
    }

    public void setCustomer_Id(int customer_Id) {
        Customer_Id = customer_Id;
    }

    public Date getDate_Begin() {
        return Date_Begin;
    }

    public void setDate_Begin(Date date_Begin) {
        Date_Begin = date_Begin;
    }

    public Date getDate_End() {
        return Date_End;
    }

    public void setDate_End(Date date_End) {
        Date_End = date_End;
    }

    public Boolean getFinish() {
        return Finish;
    }

    public void setFinish(Boolean finish) {
        Finish = finish;
    }

    public List<Contract_Item> getContract_itemList() {
        return contract_itemList;
    }

    public void setContract_itemList(List<Contract_Item> contract_itemList) {
        this.contract_itemList = contract_itemList;
    }
}
