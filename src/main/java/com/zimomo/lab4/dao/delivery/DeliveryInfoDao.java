package com.zimomo.lab4.dao.delivery;

import com.zimomo.lab4.entity.delivery.DeliveryInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DeliveryInfoDao {
    @Select("SELECT * FROM deliveryinfo WHERE delivery_id=#{delivery_id}")
    DeliveryInfo findDeliveryInfoById(int delivery_id);

    @Insert("INSERT INTO deliveryinfo VALUES(#{delivery_id},#{postman},#{telephone})")
    void addDeliveryInfo(int delivery_id,String postman,String telephone);
}
