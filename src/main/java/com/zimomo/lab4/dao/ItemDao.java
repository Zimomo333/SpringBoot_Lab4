package com.zimomo.lab4.dao;

import com.zimomo.lab4.entity.Item;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ItemDao {
    @Select("SELECT * FROM item WHERE item_id=#{item_id}")
    Item findItemById(int item_id);

    @Select("SELECT * FROM item")
    List<Item> getAllItem();

    @Update("UPDATE item SET resquantity = resquantity - #{quantity} WHERE item_id=#{item_id}")
    void deliveryItem(int item_id,double quantity);

    @Update("UPDATE item SET resquantity = resquantity + #{quantity} WHERE item_id=#{item_id}")
    void purchaseItem(int item_id,double quantity);

    @Insert("INSERT INTO item (itemname,itemprice,resquantity) VALUES(#{itemname},#{itemprice},0)")
    void addItem(String itemname,double itemprice);
}
