package com.zimomo.lab4.controller.delivery;

import com.zimomo.lab4.service.delivery.Delivery_ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Delivery_ItemController {
    @Autowired
    Delivery_ItemService delivery_itemService;
}
