package com.zimomo.lab4.entity.delivery;

import com.zimomo.lab4.entity.Item;

public class Delivery_Item {
    private int Delivery_Id;
    private int Item_Id;
    private int Quantity;
    private Item Item;

    public Item getItem() {
        return Item;
    }

    public void setItem(Item item) {
        Item = item;
    }

    public int getDelivery_Id() {
        return Delivery_Id;
    }

    public void setDelivery_Id(int delivery_Id) {
        Delivery_Id = delivery_Id;
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
