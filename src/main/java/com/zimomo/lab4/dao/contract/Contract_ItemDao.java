package com.zimomo.lab4.dao.contract;

import com.zimomo.lab4.entity.contract.Contract_Item;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Contract_ItemDao {
    @Select("SELECT * FROM contract_item WHERE contract_id=#{contract_id}")
    @Results({
            @Result(property = "Item_Id",column = "item_id"),
            @Result(property = "Item",column="item_id",one = @One(select = "com.zimomo.lab4.dao.ItemDao.findItemById"))
    })
    List<Contract_Item> findContractItem(int contract_id);

    @Insert("INSERT INTO contract_item VALUES(#{contract_id},#{item_id},#{quantity})")
    void addContractItem(int contract_id,int item_id,int quantity);
}
