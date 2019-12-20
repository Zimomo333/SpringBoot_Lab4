package com.zimomo.lab4.dao.purchase;

import com.zimomo.lab4.entity.purchase.Purchase_Item;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Purchase_ItemDao {
    @Select("SELECT * FROM purchase_item WHERE purchase_id=#{purchase_id}")
    @Results({
            @Result(property = "Item_Id",column = "item_id"),
            @Result(property = "Item",column="item_id",one = @One(select = "com.zimomo.lab4.dao.ItemDao.findItemById"))
    })
    public List<Purchase_Item> findPurchaseItem(String purchase_id);

    @Insert("INSERT INTO purchase_item VALUES(#{purchase_id},#{item_id},#{quantity})")
    void addPurchaseItem(int purchase_id,int item_id,int quantity);
}
