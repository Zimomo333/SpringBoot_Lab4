package com.zimomo.lab4.entity.contract;

import com.zimomo.lab4.entity.Item;

public class Contract_Item {
    private int Contract_Id;
    private int Item_Id;
    private int Quantity;
    private Item Item;

    public Item getItem() {
        return Item;
    }

    public void setItem(Item item) {
        Item = item;
    }

    public int getContract_Id() {
        return Contract_Id;
    }

    public void setContract_Id(int contract_Id) {
        Contract_Id = contract_Id;
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
