package com.zimomo.lab4.entity.order;

import com.zimomo.lab4.entity.Item;

public class Order_Item {
    private int Order_Id;
    private int Item_Id;
    private int Quantity;
    private Item Item;

    public Item getItem() {
        return Item;
    }

    public void setItem(Item item) {
        Item = item;
    }

    public int getOrder_Id() {
        return Order_Id;
    }

    public void setOrder_Id(int order_Id) {
        Order_Id = order_Id;
    }

    public int getItem_Id() {
        return Item_Id;
    }

    public void setItem_Id(int item_Id) {
        Item_Id = item_Id;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
