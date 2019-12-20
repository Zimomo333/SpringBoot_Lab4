package com.zimomo.lab4.dao.roles;

import com.zimomo.lab4.entity.roles.Salesman;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SalesmanDao {

    @Select("SELECT * FROM salesman")
    List<Salesman>getAllSalesman();

    @Select("SELECT * FROM salesman WHERE sales_id=#{sales_id}")
    Salesman findSalesmanById(int sales_id);
}
