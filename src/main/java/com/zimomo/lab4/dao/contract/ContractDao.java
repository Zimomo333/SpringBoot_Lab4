package com.zimomo.lab4.dao.contract;

import com.zimomo.lab4.entity.contract.Contract;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface ContractDao {

    @Select("SELECT * FROM contract")
    @Results({
            @Result(property="Contract_Id",column="contract_id"),
            @Result(property = "contract_itemList",column ="contract_id", many = @Many(select = "com.zimomo.lab4.dao.contract.Contract_ItemDao.findContractItem")),
            @Result(property = "orderList",column = "contract_id",many=@Many(select = "com.zimomo.lab4.dao.order.OrderDao.findOrderByContractId"))
    })
    List<Contract> getAllContract();

    @Insert("INSERT INTO contract (sales_id, customer_id, date_begin, date_end, edit, finish) VALUES(#{sales_id},#{customer_id},#{date_begin},#{date_end},true,false)")
    void addContract(int sales_id, int customer_id, Date date_begin, Date date_end);

    @Select("SELECT LAST_INSERT_ID()")
    int getLastInsertId();

    @Select("SELECT * FROM contract WHERE contract_id=#{contract_id}")
    @Results({
            @Result(property="Contract_Id",column="contract_id"),
            @Result(property = "contract_itemList",column ="contract_id", many = @Many(select = "com.zimomo.lab4.dao.contract.Contract_ItemDao.findContractItem")),
            @Result(property = "orderList",column = "contract_id",many=@Many(select = "com.zimomo.lab4.dao.order.OrderDao.findOrderByContractId"))

    })
    Contract findContractById(int contract_id);

    @Update("UPDATE contract SET finish = true WHERE contract_id=#{contract_id}")
    void finishContract(int contract_id);
}

