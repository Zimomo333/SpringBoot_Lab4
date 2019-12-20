package com.zimomo.lab4.service.roles;

import com.zimomo.lab4.dao.roles.WarehouseKeeperDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseKeeperService {
    @Autowired
    WarehouseKeeperDao warehouseKeeperDao;
}
