package com.zimomo.lab4.service.roles;

import com.zimomo.lab4.dao.roles.LoginUserDao;
import com.zimomo.lab4.entity.roles.LoginUser;
import com.zimomo.lab4.entity.security.LoginUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginUserService {
    @Autowired
    private LoginUserDao loginUserDao;

    public int changePassword(String oldpassword,String newpassword,String confirmpassword){
        LoginUserDetails loginUserDetails = (LoginUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LoginUser loginUser= loginUserDetails.getLoginUser();
        if(!oldpassword.equals(loginUser.getPassword()))
            return 0;       //旧密码错误

        if(!newpassword.equals(confirmpassword))
            return 1;       //两次输入密码不一致

        loginUserDao.changePassword(newpassword,loginUser.getEmployee_Id());
        return 2;       //修改成功
    }
}
