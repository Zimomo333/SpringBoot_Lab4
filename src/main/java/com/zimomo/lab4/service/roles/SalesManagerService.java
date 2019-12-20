package com.zimomo.lab4.service.roles;

import com.zimomo.lab4.dao.roles.SalesManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesManagerService {
    @Autowired
    SalesManagerDao salesManagerDao;
}
