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
}
