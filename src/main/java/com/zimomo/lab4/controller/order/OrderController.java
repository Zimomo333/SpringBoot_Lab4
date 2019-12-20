package com.zimomo.lab4.controller.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zimomo.lab4.entity.Item;
import com.zimomo.lab4.entity.delivery.Delivery;
import com.zimomo.lab4.entity.delivery.Delivery_Item;
import com.zimomo.lab4.entity.order.Order;
import com.zimomo.lab4.service.ItemService;
import com.zimomo.lab4.service.order.OrderService;
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

    @RequestMapping("/salesManager/getAllOrder")
    public String getAllContract(Model model, int pageNum){
        PageHelper.startPage(pageNum,5);
        List<Order> list = orderService.getAllOrder();
        PageInfo<Order> pageInfo = new PageInfo<Order>(list);
//        List<HashMap<String,Integer>> hashMapList=new ArrayList<HashMap<String,Integer>>();
//        for(Order order:list){
//            HashMap<String,Integer> hashMap = new HashMap<String,Integer>();
//            for (Delivery delivery : order.getDeliveryList()) {
//                for(Delivery_Item delivery_item : delivery.getDelivery_itemList()){
//                    hashMap.put(Integer.toString(delivery_item.getItem_Id()),delivery_item.getQuantity());
//                }
//            }
//            hashMapList.add(hashMap);
//        }
//        model.addAttribute("hashMapList",hashMapList);
        model.addAttribute("pageInfo",pageInfo);
        return "order_List";
    }

    @RequestMapping("/salesManager/addOrderPage")
    public String addContractPage(Model model) {
        List<Item> list = itemService.getAllItem();
        model.addAttribute("itemlist", list);
        return "order_Add";
    }

    @RequestMapping("/salesManager/addOrder")
    public String addContract(Model model, String contract_id, String date, String item_id, String quantity) throws Exception {
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
            case 5:
                model.addAttribute("result","添加成功！");
                break;
            case 6:
                model.addAttribute("result", "　添加失败！请重试");
                break;
        }

        List<Item> list = itemService.getAllItem();
        model.addAttribute("itemlist", list);
        return "order_Add";
    }

}
