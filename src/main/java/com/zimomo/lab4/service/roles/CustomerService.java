package com.zimomo.lab4.service.roles;

import com.zimomo.lab4.dao.roles.CustomerDao;
import com.zimomo.lab4.entity.roles.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerDao customerDao;

    public List<Customer> getAllCustomer(){
        return customerDao.getAllCustomer();
    }

    public Customer findCustomerById(int customer_id){return customerDao.findCustomerById(customer_id);}

    public int addCustomer(String name,String sex,String telephone,String email,String location){
        customerDao.addCustomer(name,sex,telephone,email,location);
        return 1;
    }

    public void delCustomer(String customer_id){
        customerDao.delCustomer(Integer.parseInt(customer_id));
    }

    public int editCustomer(String customer_id,String name,String sex,String telephone,String email,String location){
        customerDao.editCustomer(Integer.parseInt(customer_id),name,sex,telephone,email,location);
        return 1;
    }
}
