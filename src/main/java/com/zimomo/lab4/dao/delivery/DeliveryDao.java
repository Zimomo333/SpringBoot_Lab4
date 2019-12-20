package com.zimomo.lab4.dao.delivery;

import com.zimomo.lab4.entity.delivery.Delivery;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface DeliveryDao {

    @Select("SELECT * FROM delivery")
    @Results({
            @Result(property="Delivery_Id",column="delivery_id"),
            @Result(property = "delivery_itemList",column ="delivery_id", many = @Many(select = "com.zimomo.lab4.dao.delivery.Delivery_ItemDao.findDeliveryItem"))
    })
    List<Delivery> getAllDelivery();

    @Insert("INSERT INTO delivery (order_id, date,receiver,telephone,location,finish) VALUES(#{order_id},#{date},#{receiver},#{telephone},#{location},false)")
    void addDelivery(int order_id, Date date,String receiver,int telephone,String location);

    @Select("SELECT * FROM delivery WHERE order_id=#{order_id}")
    @Results({
            @Result(property="Delivery_Id",column="delivery_id"),
            @Result(property = "delivery_itemList",column ="delivery_id", many = @Many(select = "com.zimomo.lab4.dao.delivery.Delivery_ItemDao.findDeliveryItem"))
    })
    List<Delivery> findDeliveryByOrderId(int order_id);

    @Select("SELECT LAST_INSERT_ID()")
    int getLastInsertId();
}
