package com.zimomo.lab4.controller.contract;

import com.zimomo.lab4.entity.contract.Contract_Item;
import com.zimomo.lab4.service.contract.Contract_ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class Contract_ItemController {
    @Autowired
    Contract_ItemService contract_itemService;

    @RequestMapping("/findContractItem")
    public String findContractItemList(Model model,int contract_id){
        List<Contract_Item> list = contract_itemService.findContractItem(contract_id);
        return null;
    }
}
