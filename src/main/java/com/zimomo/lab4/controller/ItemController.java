package com.zimomo.lab4.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zimomo.lab4.entity.Item;
import com.zimomo.lab4.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ItemController {
    @Autowired
    ItemService itemService;

    @RequestMapping("/keeper/getAllItem")
    public String getAllItem(Model model,int pageNum){
        PageHelper.startPage(pageNum, 5);
        List<Item> list = itemService.getAllItem();
        PageInfo<Item> pageInfo = new PageInfo<Item>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "item_List";
    }

    @RequestMapping("/keeper/addItemPage")
    public String addItemPage(Model model){
        return "item_Add";
    }

    @RequestMapping("/keeper/addItem")
    public String addItem(Model model,String itemname,String itemprice){
        int signal;
        try{
            signal = itemService.addItem(itemname,itemprice);
        }catch (RuntimeException e){
            signal=0;   //添加失败
        }

        if (signal==1)
            model.addAttribute("result","添加成功！");
        else if(signal==0)
            model.addAttribute("result","添加失败！请重试！");

        return "item_Add";
    }
}
