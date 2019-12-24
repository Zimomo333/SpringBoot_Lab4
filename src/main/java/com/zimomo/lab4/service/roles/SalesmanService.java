package com.zimomo.lab4.service.roles;

import com.zimomo.lab4.dao.roles.LoginUserDao;
import com.zimomo.lab4.dao.roles.SalesmanDao;
import com.zimomo.lab4.entity.roles.LoginUser;
import com.zimomo.lab4.entity.roles.Salesman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SalesmanService {
    @Autowired
    SalesmanDao salesmanDao;

    @Autowired
    LoginUserDao loginUserDao;

    public List<Salesman> getAllSalesman(){
        return salesmanDao.getAllSalesman();
    }

    public Salesman findSalesmanById(int sales_id){return  salesmanDao.findSalesmanById(sales_id);}

    public int addSalesman(String name,String username,String sex,String telephone,String email){
        LoginUser loginUser =loginUserDao.findByUsername(username);
        if(loginUser!=null)
            return 0;

        salesmanDao.addSalesman(name,sex,telephone,email);
        int sales_id=salesmanDao.getLastInsertId();
        loginUserDao.addLoginuser(sales_id,username);
        return 1;
    }

    public void delSalesman(String sales_id){
        salesmanDao.delSalesman(Integer.parseInt(sales_id));
        loginUserDao.delLoginUser(Integer.parseInt(sales_id));
    }

    public int editSalesman(String sales_id,String name,String sex,String telephone,String email){
        salesmanDao.editSalesman(Integer.parseInt(sales_id),name,sex,telephone,email);
        return 1;
    }
}
