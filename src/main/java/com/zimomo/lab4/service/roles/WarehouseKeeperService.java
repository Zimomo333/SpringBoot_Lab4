package com.zimomo.lab4.service.roles;

import com.zimomo.lab4.dao.roles.WarehouseKeeperDao;
import com.zimomo.lab4.entity.roles.WarehouseKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseKeeperService {
    @Autowired
    WarehouseKeeperDao warehouseKeeperDao;

    public WarehouseKeeper findKeeperById(int keeper_id){
        return warehouseKeeperDao.findKeeperById(keeper_id);
    }
}
