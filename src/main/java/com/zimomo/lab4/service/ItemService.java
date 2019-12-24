package com.zimomo.lab4.service;

import com.zimomo.lab4.dao.ItemDao;
import com.zimomo.lab4.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemDao itemDao;

    public List<Item> getAllItem(){
        return itemDao.getAllItem();
    }

    public int addItem(String itemname,String itemprice){
        itemDao.addItem(itemname,Double.parseDouble(itemprice));
        return 1;
    }

    public Item findItemById(String item_id){
        return itemDao.findItemById(Integer.parseInt(item_id));
    }
}
