package com.zimomo.lab4.dao.purchase;

import com.zimomo.lab4.entity.purchase.Purchase;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface PurchaseDao {

    @Select("SELECT * FROM purchase")
    @Results({
            @Result(property="Purchase_Id",column="purchase_id"),
            @Result(property = "purchase_itemList",column ="purchase_id", many = @Many(select = "com.zimomo.lab4.dao.purchase.Purchase_ItemDao.findPurchaseItem"))
    })
    List<Purchase> getAllPurchase();

    @Insert("INSERT INTO purchase (order_id, date,finish) VALUES(#{order_id},#{date},false)")
    void addPurchase(int order_id, Date date);

    @Select("SELECT LAST_INSERT_ID()")
    int getLastInsertId();

    @Select("SELECT * FROM purchase WHERE purchase_id=#{purchase_id}")
    @Results({
            @Result(property="Purchase_Id",column="purchase_id"),
            @Result(property = "purchase_itemList",column ="purchase_id", many = @Many(select = "com.zimomo.lab4.dao.purchase.Purchase_ItemDao.findPurchaseItem"))
    })
    Purchase findPurchaseById(int purchase_id);

    @Update("UPDATE purchase SET finish = true WHERE purchase_id=#{purchase_id}")
    void confirmPurchase(int purchase_id);
}
