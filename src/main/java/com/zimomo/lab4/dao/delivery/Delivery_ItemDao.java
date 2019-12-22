package com.zimomo.lab4.dao.delivery;

import com.zimomo.lab4.entity.delivery.Delivery_Item;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Delivery_ItemDao {
    @Select("SELECT * FROM delivery_item WHERE delivery_id=#{delivery_id}")
    @Results({
            @Result(property = "Item_Id",column = "item_id"),
            @Result(property = "Item",column="item_id",one = @One(select = "com.zimomo.lab4.dao.ItemDao.findItemById"))
    })
    public List<Delivery_Item> findDeliveryItemById(String delivery_id);

    @Insert("INSERT INTO delivery_item VALUES(#{delivery_id},#{item_id},#{quantity})")
    void addDeliveryItem(int delivery_id,int item_id,int quantity);
}
