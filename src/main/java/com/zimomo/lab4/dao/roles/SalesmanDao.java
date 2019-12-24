package com.zimomo.lab4.dao.roles;

import com.zimomo.lab4.entity.roles.Salesman;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SalesmanDao {

    @Select("SELECT * FROM salesman")
    List<Salesman>getAllSalesman();

    @Select("SELECT * FROM salesman WHERE sales_id=#{sales_id}")
    Salesman findSalesmanById(int sales_id);

    @Insert("INSERT INTO salesman (name,sex,telephone,email) VALUES(#{name},#{sex},#{telephone},#{email})")
    void addSalesman(String name,String sex,String telephone,String email);

    @Select("SELECT LAST_INSERT_ID()")
    int getLastInsertId();

    @Delete("DELETE FROM salesman WHERE sales_id=#{sales_id}")
    void delSalesman(int sales_id);

    @Update("UPDATE salesman SET name = #{name},sex=#{sex},telephone=#{telephone},email=#{email} WHERE sales_id=#{sales_id}")
    void editSalesman(int sales_id,String name,String sex,String telephone,String email);
}
