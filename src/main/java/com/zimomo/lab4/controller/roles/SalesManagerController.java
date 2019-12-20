package com.zimomo.lab4.controller.roles;

import com.zimomo.lab4.service.roles.SalesManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SalesManagerController {
    @Autowired
    SalesManagerService salesManagerService;

}
