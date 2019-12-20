package com.zimomo.lab4.controller.contract;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zimomo.lab4.entity.Item;
import com.zimomo.lab4.entity.contract.Contract;
import com.zimomo.lab4.service.ItemService;
import com.zimomo.lab4.service.contract.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ContractController {
    @Autowired
    ContractService contractService;

    @Autowired
    ItemService itemService;

    @RequestMapping("/salesManager/getAllContract")
    public String getAllContract(Model model, int pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Contract> list = contractService.getAllContract();
        PageInfo<Contract> pageInfo = new PageInfo<Contract>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "contract_List";
    }

    @RequestMapping("/salesManager/addContractPage")
    public String addContractPage(Model model) {
        List<Item> list = itemService.getAllItem();
        model.addAttribute("itemlist", list);
        return "contract_Add";
    }

    @RequestMapping("/salesManager/addContract")
    public String addContract(Model model, String sales_id, String customer_id, String date_begin, String date_end, String item_id, String quantity) throws Exception {
        int signal;
        try{
            signal = contractService.addConotract(sales_id, customer_id, date_begin, date_end, item_id, quantity);
        }catch (RuntimeException e){
            signal=7;   //添加失败
        }

        switch (signal) {
            case 0:
                model.addAttribute("sales_error","销售编号不存在！请重新输入");
                break;
            case 1:
                model.addAttribute("customer_error","客户编号不存在！请重新输入");
                break;
            case 2:
                model.addAttribute("date_error", "签订日期大于有效日期！请重新输入");
                break;
            case 3:
                model.addAttribute("select_error", "请选择商品！");
                break;
            case 4:
                model.addAttribute("quantity_error", "数量格式错误！请重新输入");
                break;
            case 5:
                model.addAttribute("repeat_error", "请勿重复选择商品！");
                break;
            case 6:
                model.addAttribute("result","添加成功！");
                break;
            case 7:
                model.addAttribute("result", "　添加失败！请重试");
                break;
        }

        List<Item> list = itemService.getAllItem();
        model.addAttribute("itemlist", list);
        return "contract_Add";
    }
}
