package com.zimomo.lab4.controller.delivery;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zimomo.lab4.entity.Item;
import com.zimomo.lab4.entity.delivery.Delivery;
import com.zimomo.lab4.service.ItemService;
import com.zimomo.lab4.service.contract.ContractService;
import com.zimomo.lab4.service.delivery.DeliveryService;
import com.zimomo.lab4.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;

    @Autowired
    ItemService itemService;

    @Autowired
    OrderService orderService;

    @Autowired
    ContractService contractService;

    @RequestMapping("/salesManager_keeper/getAllDelivery")
    public String getAllDelivery(Model model, int pageNum){
        PageHelper.startPage(pageNum,5);
        List<Delivery> list = deliveryService.getAllDelivery();
        PageInfo<Delivery> pageInfo = new PageInfo<Delivery>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "delivery_List";
    }

    @RequestMapping("/salesManager/addDeliveryPage")
    public String addDeliveryPage(Model model) {
        List<Item> list = itemService.getAllItem();
        model.addAttribute("itemlist", list);
        return "delivery_Add";
    }

    @RequestMapping("/salesManager/addDelivery")
    public String addDelivery(Model model, String order_id, String date, String receiver,String telephone,String location, String item_id, String quantity) throws Exception {
        int signal;
        try{
            signal = deliveryService.addDelivery(order_id, date, receiver,telephone, location, item_id, quantity);
        }catch (RuntimeException e){
            signal=7;   //添加失败
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
                model.addAttribute("result", "添加失败！发货数量超出库存数量！");
                break;
            case 5:
                model.addAttribute("result", "添加失败！发货数量超出订单采购数量！");
                break;
            case 6: {
                model.addAttribute("result", "添加成功！");
                orderService.checkFinishOrder(order_id);    //每次添加发货单成功都检查订单是否完成
                contractService.checkFinishContract(order_id);      //每次添加发货单成功都检查合同是否完成
                break;
            }
            case 7:
                model.addAttribute("result", "　添加失败！请重试");
                break;
        }

        List<Item> list = itemService.getAllItem();
        model.addAttribute("itemlist", list);
        return "delivery_Add";
    }

    @RequestMapping("keeper/confirmDelivery")
    public String confirmDelivery(Model model,String delivery_id,String postman,String telephone) {
        int signal;
//        try {
            signal = deliveryService.confirmDelivery(delivery_id, postman, telephone);
//        } catch (RuntimeException e) {
//            signal = 2;   //确认失败
//        }

        switch (signal) {
            case 0:
                model.addAttribute("delivery_error", "发货单编号不存在！请重新输入");
                break;
            case 1:
                model.addAttribute("result", "确认发货成功！");
                break;
            case 2:
                model.addAttribute("result", "确认发货失败！请重试！");
                break;
        }
        return "deliveryInfo_Add";
    }
}
