package com.zimomo.lab4.controller.delivery;

import com.zimomo.lab4.service.delivery.Delivery_ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Delivery_ItemController {
    @Autowired
    Delivery_ItemService delivery_itemService;
}
