package com.zimomo.lab4.service.purchase;

import com.zimomo.lab4.dao.purchase.Purchase_ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Purchase_ItemService {
    @Autowired
    Purchase_ItemDao purchase_itemDao;
}
