package com.zimomo.lab4.service.roles;

import com.zimomo.lab4.dao.roles.SalesmanDao;
import com.zimomo.lab4.entity.roles.Salesman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesmanService {
    @Autowired
    SalesmanDao salesmanDao;

    public List<Salesman> getAllSalesman(){
        return salesmanDao.getAllSalesman();
    }

    public Salesman findSalesmanById(int sales_id){return  salesmanDao.findSalesmanById(sales_id);}
}
