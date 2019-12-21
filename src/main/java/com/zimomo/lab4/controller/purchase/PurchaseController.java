package com.zimomo.lab4.controller.purchase;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zimomo.lab4.entity.Item;
import com.zimomo.lab4.entity.order.Order;
import com.zimomo.lab4.entity.purchase.Purchase;
import com.zimomo.lab4.service.ItemService;
import com.zimomo.lab4.service.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

    @Autowired
    ItemService itemService;

    @RequestMapping("/salesManager/getAllPurchase")
    public String getAllPurchase(Model model, int pageNum){
        PageHelper.startPage(pageNum,5);
        List<Purchase> list = purchaseService.getAllPurchase();
        PageInfo<Purchase> pageInfo = new PageInfo<Purchase>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "purchase_List";
    }

    @RequestMapping("/salesManager/addPurchasePage")
    public String addPurchasePage(Model model) {
        List<Item> list = itemService.getAllItem();
        model.addAttribute("itemlist", list);
        return "purchase_Add";
    }

    @RequestMapping("/salesManager/addPurchase")
    public String addPurchase(Model model, String order_id, String date, String item_id, String quantity) throws Exception {
        int signal;
        try{
            signal = purchaseService.addPurchase(order_id, date, item_id, quantity);
        }catch (RuntimeException e){
            signal=5;   //添加失败
        }

        switch (signal) {
            case 0:
                model.addAttribute("order_error","订单编号不存在！请重新输入");
                break;
            case 1:
                model.addAttribute("select_error", "请选择商品！");
                break;
            case 2:
                model.addAttribute("quantity_error", "数量格式错误！请重新输入");
                break;
            case 3:
                model.addAttribute("repeat_error", "请勿重复选择商品！");
                break;
            case 4:
                model.addAttribute("result","添加成功！");
                break;
            case 5:
                model.addAttribute("result", "　添加失败！请重试");
                break;
        }

        List<Item> list = itemService.getAllItem();
        model.addAttribute("itemlist", list);
        return "purchase_Add";
    }

    @RequestMapping("salesManager/confirmPurchase")
    @ResponseBody
    public void confirmPurchase(String purchase_id) {
        purchaseService.confirmPurchase(purchase_id);
    }

    @RequestMapping("salesManager/refreshPurchaseList")
    public String refreshPurchaseList(Model model, int pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Purchase> list = purchaseService.getAllPurchase();
        PageInfo<Purchase> pageInfo = new PageInfo<Purchase>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "purchase_List::refresh";
    }
}
