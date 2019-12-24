package com.zimomo.lab4.controller.roles;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zimomo.lab4.entity.roles.Salesman;
import com.zimomo.lab4.service.roles.SalesmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SalesmanController {
    @Autowired
    SalesmanService salesmanService;

    @RequestMapping("/salesManager/getAllSalesman")
    public String getAllSalesman(Model model, int pageNum){
        PageHelper.startPage(pageNum,5);
        List<Salesman> list = salesmanService.getAllSalesman();
        PageInfo<Salesman> pageInfo = new PageInfo<Salesman>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "salesman_List";
    }

    @RequestMapping("/salesManager/addSalesmanPage")
    public String addSalesman(Model model){
        return "salesman_Add";
    }

    @RequestMapping("/salesManager/addSalesman")
    public String addSalesman(Model model,String name,String username,String sex,String telephone,String email){
        int signal;
        try {
            signal=salesmanService.addSalesman(name,username,sex,telephone,email);
        }catch (RuntimeException e){
            signal=2;
        }
        if(signal==0){
            model.addAttribute("username_error","用户名已存在！");
        }else if (signal==1){
            model.addAttribute("result","添加成功！");
        }else  if(signal==2){
            model.addAttribute("result","添加失败！请重试！");
        }
        return "salesman_Add";
    }

    @RequestMapping("/salesManager/delSalesman")
    @ResponseBody
    public void delSalesman(Model model,String sales_id){
        salesmanService.delSalesman(sales_id);
    }

    @RequestMapping("/salesManager/editSalesmanPage")
    public String editSalesmanPage(Model model,String sales_id) {
        Salesman salesman = salesmanService.findSalesmanById(Integer.parseInt(sales_id));
        model.addAttribute("sales_id",salesman.getSales_Id());
        model.addAttribute("name",salesman.getName());
        model.addAttribute("telephone",salesman.getTelephone());
        model.addAttribute("email",salesman.getEmail());
        return "salesman_Edit";
    }

        @RequestMapping("/salesManager/editSalesman")
    public String editSalesman(Model model,String sales_id,String name,String sex,String telephone,String email){
        int signal;
        try {
            signal=salesmanService.editSalesman(sales_id,name,sex,telephone,email);
        }catch (RuntimeException e){
            signal=0;
        }
        if (signal==1){
            model.addAttribute("result","修改成功！");
        }else  if(signal==0){
            model.addAttribute("result","修改失败！请重试！");
        }
        return "salesman_Edit";
    }
}
