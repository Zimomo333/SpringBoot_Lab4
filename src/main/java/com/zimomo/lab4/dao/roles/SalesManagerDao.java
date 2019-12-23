package com.zimomo.lab4.dao.roles;

import com.zimomo.lab4.entity.roles.SalesManager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SalesManagerDao {
    @Select("SELECT * FROM salesmanager WHERE manager_id=#{manager_id}")
    SalesManager findSalesManagerById(int manager_id);
}