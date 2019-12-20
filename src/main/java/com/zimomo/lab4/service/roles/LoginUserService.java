package com.zimomo.lab4.service.roles;

import com.zimomo.lab4.dao.roles.LoginUserDao;
import com.zimomo.lab4.entity.roles.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginUserService {
    @Autowired
    private LoginUserDao loginUserDao;


    public LoginUser getSingle(int employee_id){
        return loginUserDao.getSingle(employee_id);
    }

    public void insert(LoginUser loginUser){
        loginUserDao.insert(loginUser);
    }

    public void update(String password,int employee_id){
        loginUserDao.update(password,employee_id);
    }

    public void delete(int employee_id){
        loginUserDao.delete(employee_id);
    }
}
