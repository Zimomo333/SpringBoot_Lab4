package com.zimomo.lab4.dao.order;

import com.zimomo.lab4.entity.order.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface OrderDao {

    @Select("SELECT * FROM ordertable")
    @Results({
            @Result(property="Order_Id",column="order_id"),
            @Result(property = "order_itemList",column ="order_id", many = @Many(select = "com.zimomo.lab4.dao.order.Order_ItemDao.findOrderItem")),
            @Result(property = "deliveryList",column = "order_id",many = @Many(select = "com.zimomo.lab4.dao.delivery.DeliveryDao.findDeliveryByOrderId"))
    })
    List<Order> getAllOrder();

    @Insert("INSERT INTO ordertable (contract_id, date, totalprice, finish) VALUES(#{contract_id},#{date},0,false)")
    void addOrder(int contract_id, Date date);

    @Update("UPDATE ordertable SET totalprice = #{totalprice} WHERE order_id=#{order_id}")
    void updateTotalprice(int order_id,double totalprice);

    @Update("UPDATE ordertable SET finish = true WHERE order_id=#{order_id}")
    void finishOrder(int order_id);

    @Select("SELECT * FROM ordertable WHERE order_id=#{order_id}")
    @Results({
            @Result(property="Order_Id",column="order_id"),
            @Result(property = "order_itemList",column ="order_id", many = @Many(select = "com.zimomo.lab4.dao.order.Order_ItemDao.findOrderItem")),
            @Result(property = "deliveryList",column = "order_id",many = @Many(select = "com.zimomo.lab4.dao.delivery.DeliveryDao.findDeliveryByOrderId"))

    })
    Order findOrderById(int order_id);

    @Select("SELECT * FROM ordertable WHERE contract_id=#{contract_id}")
    @Results({
            @Result(property="Order_Id",column="order_id"),
            @Result(property = "order_itemList",column ="order_id", many = @Many(select = "com.zimomo.lab4.dao.order.Order_ItemDao.findOrderItem")),
    })
    List<Order> findOrderByContractId(int contract_id);

    @Select("SELECT LAST_INSERT_ID()")
    int getLastInsertId();
}
