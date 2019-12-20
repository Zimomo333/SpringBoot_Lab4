package com.zimomo.lab4.controller.roles;

import com.zimomo.lab4.service.roles.WarehouseKeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class WarehouseKeeperController {
    @Autowired
    WarehouseKeeperService warehouseKeeperService;
}
