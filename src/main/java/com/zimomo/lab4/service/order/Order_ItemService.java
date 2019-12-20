package com.zimomo.lab4.service.order;

import com.zimomo.lab4.dao.order.Order_ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Order_ItemService {
    @Autowired
    Order_ItemDao order_itemDao;
}