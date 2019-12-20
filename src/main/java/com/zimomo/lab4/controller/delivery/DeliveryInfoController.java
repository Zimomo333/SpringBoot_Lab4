package com.zimomo.lab4.controller.delivery;

import com.zimomo.lab4.service.delivery.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DeliveryInfoController {
    @Autowired
    DeliveryService deliveryService;
}
