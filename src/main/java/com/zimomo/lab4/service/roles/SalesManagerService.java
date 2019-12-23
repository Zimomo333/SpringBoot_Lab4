package com.zimomo.lab4.service.roles;

import com.zimomo.lab4.dao.roles.SalesManagerDao;
import com.zimomo.lab4.entity.roles.SalesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesManagerService {
    @Autowired
    SalesManagerDao salesManagerDao;

    public SalesManager findSalesManagerById(int manager_id){
        return salesManagerDao.findSalesManagerById(manager_id);
    }
}
