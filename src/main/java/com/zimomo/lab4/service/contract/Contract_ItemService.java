package com.zimomo.lab4.service.contract;

import com.zimomo.lab4.dao.contract.Contract_ItemDao;
import com.zimomo.lab4.entity.contract.Contract_Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Contract_ItemService {
    @Autowired
    Contract_ItemDao contract_itemDao;

    public List<Contract_Item> findContractItem(int contract_id){
        return contract_itemDao.findContractItem(contract_id);
    }
}
