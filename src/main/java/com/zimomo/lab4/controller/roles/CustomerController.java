package com.zimomo.lab4.controller.roles;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zimomo.lab4.entity.roles.Customer;
import com.zimomo.lab4.service.roles.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping("/salesManager/getAllCustomer")
    public String getAllCustomer(Model model,int pageNum){
        PageHelper.startPage(pageNum,5);
        List<Customer> list = customerService.getAllCustomer();
        PageInfo<Customer> pageInfo = new PageInfo<Customer>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "customer_List";
    }
}
