package com.zimomo.lab4.service.delivery;

import com.zimomo.lab4.dao.delivery.Delivery_ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Delivery_ItemService {
    @Autowired
    Delivery_ItemDao delivery_itemDao;
}
