package com.zimomo.lab4.entity.purchase;

import com.zimomo.lab4.entity.Item;

public class Purchase_Item {
    private int Purchase_Id;
    private int Item_Id;
    private int Quantity;
    private Item Item;

    public Item getItem() {
        return Item;
    }

    public void setItem(Item item) {
        Item = item;
    }

    public int getPurchase_Id() {
        return Purchase_Id;
    }

    public void setPurchase_Id(int purchase_Id) {
        Purchase_Id = purchase_Id;
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
