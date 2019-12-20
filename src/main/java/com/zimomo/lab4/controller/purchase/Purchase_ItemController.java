package com.zimomo.lab4.controller.purchase;

import com.zimomo.lab4.service.purchase.Purchase_ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Purchase_ItemController {
    @Autowired
    Purchase_ItemService purchase_itemService;
}
