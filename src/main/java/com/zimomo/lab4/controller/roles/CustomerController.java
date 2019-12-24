package com.zimomo.lab4.controller.roles;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zimomo.lab4.entity.roles.Customer;
import com.zimomo.lab4.service.roles.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/salesManager/addCustomerPage")
    public String addCustomerPage(Model model){
        return "customer_Add";
    }

    @RequestMapping("/salesManager/delCustomer")
    @ResponseBody
    public void delCustomer(Model model,String customer_id){
        customerService.delCustomer(customer_id);
    }

    @RequestMapping("/salesManager/addCustomer")
    public String addCustomer(Model model,String name,String sex,String telephone,String email,String location){
        int signal;
        try {
            signal=customerService.addCustomer(name,sex,telephone,email,location);
        }catch (RuntimeException e){
            signal=0;
        }
        if (signal==1){
            model.addAttribute("result","添加成功！");
        }else  if(signal==0){
            model.addAttribute("result","添加失败！请重试！");
        }
        return "customer_Add";
    }

    @RequestMapping("/salesManager/editCustomerPage")
    public String editCustomerPage(Model model,String customer_id) {
        Customer customer = customerService.findCustomerById(Integer.parseInt(customer_id));
        model.addAttribute("customer_id",customer.getCustomer_Id());
        model.addAttribute("name",customer.getName());
        model.addAttribute("telephone",customer.getTelephone());
        model.addAttribute("email",customer.getEmail());
        model.addAttribute("location",customer.getLocation());
        return "customer_Edit";
    }

    @RequestMapping("/salesManager/editCustomer")
    public String editCustomer(Model model,String customer_id,String name,String sex,String telephone,String email,String location){
        int signal;
        try {
            signal=customerService.editCustomer(customer_id,name,sex,telephone,email,location);
        }catch (RuntimeException e){
            signal=0;
        }
        if (signal==1){
            model.addAttribute("result","修改成功！");
        }else  if(signal==0){
            model.addAttribute("result","修改失败！请重试！");
        }
        return "customer_Edit";
    }
}
