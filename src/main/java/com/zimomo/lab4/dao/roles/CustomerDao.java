package com.zimomo.lab4.dao.roles;

import com.zimomo.lab4.entity.roles.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CustomerDao {

    @Select("SELECT * FROM customer")
    List<Customer> getAllCustomer();

    @Select("SELECT * FROM customer WHERE customer_id=#{customer_id}")
    Customer findCustomerById(int customer_id);
}
