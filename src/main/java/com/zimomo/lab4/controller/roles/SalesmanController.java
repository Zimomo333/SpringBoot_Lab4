package com.zimomo.lab4.controller.roles;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zimomo.lab4.entity.roles.Salesman;
import com.zimomo.lab4.service.roles.SalesmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
