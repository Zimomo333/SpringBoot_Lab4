package com.zimomo.lab4.dao.roles;

import com.zimomo.lab4.entity.roles.Customer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CustomerDao {

    @Select("SELECT * FROM customer")
    List<Customer> getAllCustomer();

    @Select("SELECT * FROM customer WHERE customer_id=#{customer_id}")
    Customer findCustomerById(int customer_id);

    @Insert("INSERT INTO customer (name,sex,telephone,email,location) VALUES(#{name},#{sex},#{telephone},#{email},#{location})")
    void addCustomer(String name,String sex,String telephone,String email,String location);

    @Delete("DELETE FROM customer WHERE customer_id=#{customer_id}")
    void delCustomer(int customer_id);

    @Update("UPDATE customer SET name = #{name},sex=#{sex},telephone=#{telephone},email=#{email},location=#{location} WHERE customer_id=#{customer_id}")
    void editCustomer(int customer_id,String name,String sex,String telephone,String email,String location);

}
