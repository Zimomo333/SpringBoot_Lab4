package com.zimomo.lab4.service.delivery;

import com.zimomo.lab4.dao.delivery.DeliveryInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryInfoService {
    @Autowired
    DeliveryInfoDao deliveryInfoDao;
}
