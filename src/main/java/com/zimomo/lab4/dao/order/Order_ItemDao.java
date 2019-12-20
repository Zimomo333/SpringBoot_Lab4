package com.zimomo.lab4.dao.order;

import com.zimomo.lab4.entity.order.Order_Item;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Order_ItemDao {
    @Select("SELECT * FROM order_item WHERE order_id=#{order_id}")
    @Results({
            @Result(property = "Item_Id",column = "item_id"),
            @Result(property = "Item",column="item_id",one = @One(select = "com.zimomo.lab4.dao.ItemDao.findItemById"))
    })
    List<Order_Item> findOrderItem(int order_id);

    @Insert("INSERT INTO order_item VALUES(#{order_id},#{item_id},#{quantity})")
    void addOrderItem(int order_id,int item_id,int quantity);
}
