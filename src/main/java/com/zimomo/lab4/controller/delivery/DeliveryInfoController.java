package com.zimomo.lab4.controller.delivery;

import com.zimomo.lab4.service.delivery.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeliveryInfoController {
    @Autowired
    DeliveryService deliveryService;

    @RequestMapping("keeper/addDeliveryInfoPage")
    public String addDeliveryInfo(Model model, String delivery_id){
        model.addAttribute("delivery_id",delivery_id);
        return "deliveryInfo_Add";
    }
}
