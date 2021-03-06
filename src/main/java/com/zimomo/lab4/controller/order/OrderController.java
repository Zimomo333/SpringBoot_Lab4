package com.zimomo.lab4.controller.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.zimomo.lab4.entity.Item;
import com.zimomo.lab4.entity.contract.Contract;
import com.zimomo.lab4.entity.delivery.Delivery;
import com.zimomo.lab4.entity.delivery.Delivery_Item;
import com.zimomo.lab4.entity.order.Order;
import com.zimomo.lab4.entity.roles.Customer;
import com.zimomo.lab4.service.ItemService;
import com.zimomo.lab4.service.contract.ContractService;
import com.zimomo.lab4.service.order.OrderService;
import com.zimomo.lab4.service.roles.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    ItemService itemService;

    @Autowired
    ContractService contractService;

    @Autowired
    CustomerService customerService;

    @RequestMapping("/salesManager/getAllOrder")
    public String getAllOrder(Model model, int pageNum){
        PageHelper.startPage(pageNum,5);
        List<Order> list = orderService.getAllOrder();
        orderService.OrderRest(list);
        PageInfo<Order> pageInfo = new PageInfo<Order>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "order_List";
    }

    @RequestMapping("/salesManager/addOrderPage")
    public String addOrderPage(Model model) {
        List<Item> list = itemService.getAllItem();
        model.addAttribute("itemlist", list);
        return "order_Add";
    }

    @RequestMapping("/salesManager/addOrder")
    public String addOrder(Model model, String contract_id, String date, String item_id, String quantity) throws Exception {
        int signal;
        try{
            signal = orderService.addOrder(contract_id, date, item_id, quantity);
        }catch (RuntimeException e){
            signal=6;   //添加失败
        }

        switch (signal) {
            case 0:
                model.addAttribute("contract_error","合同编号不存在！请重新输入");
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
                model.addAttribute("result", "添加失败！采购数量超出合同订购数量！");
                break;
            case 5: {
                model.addAttribute("result", "添加成功！");
                contractService.changeEdit(contract_id);    //合同变为不可编辑状态
                break;
            }
            case 6:
                model.addAttribute("result", "　添加失败！请重试");
                break;
        }

        List<Item> list = itemService.getAllItem();
        model.addAttribute("itemlist", list);
        return "order_Add";
    }

    @RequestMapping("/salesManager/dateAnalysePage")
    public String dateAnalysePage(Model model){
        return "dateAnalyse";
    }

    @RequestMapping("/salesManager/dateAnalyse")
    public String dateAnalyse(Model model,String date_begin, String date_end){
        double total = orderService.dateAnalyse(date_begin,date_end);
        if(total==-1){
            model.addAttribute("date_error", "日期格式错误");
        } else {
            model.addAttribute("total", total);
            model.addAttribute("date_begin", date_begin);
            model.addAttribute("date_end", date_end);
        }
        return "dateAnalyse";
    }

    @RequestMapping("/salesManager/customerAnalysePage")
    public String customerAnalysePage(Model model){
        return "customerAnalyse";
    }

    @RequestMapping("/salesManager/customerAnalyse")
    public String customerAnalyse(Model model,String customer_id){
        double total=orderService.customerAnalyse(customer_id);
        if(total==-1){
            model.addAttribute("customer_error", "客户编号无效！");
        } else {
            model.addAttribute("total", total);
            Customer customer = customerService.findCustomerById(Integer.parseInt(customer_id));
            model.addAttribute("name", customer.getName());
        }
        return "customerAnalyse";
    }

    @RequestMapping("/salesManager/itemAnalysePage")
    public String itemAnalysePage(Model model){
        List<Item> list = itemService.getAllItem();
        model.addAttribute("itemlist", list);
        return "itemAnalyse";
    }

    @RequestMapping("/salesManager/itemAnalyse")
    public String itemAnalyse(Model model,String item_id){
        double total=orderService.itemAnalyse(item_id);
        if(total==-1){
            model.addAttribute("item_error", "请选择商品！");
        } else {
            model.addAttribute("total", total);
            List<Item> list = itemService.getAllItem();
            model.addAttribute("itemlist", list);
            Item item = itemService.findItemById(item_id);
            model.addAttribute("itemname", item.getItemName());
        }
        return "itemAnalyse";
    }

    @RequestMapping("/salesman/myAnalysePage")
    public String myAnalysePage(Model model){
        return "myAnalyse";
    }

    @RequestMapping("/salesman/myAnalyse")
    public String myAnalyse(Model model,String date_begin, String date_end){
        double total = orderService.myAnalyse(date_begin,date_end);
        if(total==-1){
            model.addAttribute("date_error", "日期格式错误");
        } else {
            model.addAttribute("total", total);
            model.addAttribute("date_begin", date_begin);
            model.addAttribute("date_end", date_end);
        }
        return "myAnalyse";
    }
}
