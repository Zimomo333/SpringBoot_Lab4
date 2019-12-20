package com.zimomo.lab4.controller.order;

import com.zimomo.lab4.service.order.Order_ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Order_ItemController {
    @Autowired
    Order_ItemService order_itemService;
}
