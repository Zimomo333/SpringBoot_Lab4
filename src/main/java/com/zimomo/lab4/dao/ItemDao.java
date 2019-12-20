package com.zimomo.lab4.dao;

import com.zimomo.lab4.entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ItemDao {
    @Select("SELECT * FROM item WHERE item_id=#{item_id}")
    public Item findItemById(int item_id);

    @Select("SELECT * FROM item")
    public List<Item> getAllItem();
}
