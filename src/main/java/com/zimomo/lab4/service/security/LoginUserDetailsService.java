package com.zimomo.lab4.service.security;

import com.zimomo.lab4.dao.roles.LoginUserDao;
import com.zimomo.lab4.entity.roles.LoginUser;
import com.zimomo.lab4.entity.security.LoginUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class LoginUserDetailsService implements UserDetailsService {
    @Autowired
    LoginUserDao loginUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        try{
            LoginUser loginUser = loginUserDao.findByUsername(username);
            if(loginUser!=null){
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                SimpleGrantedAuthority grant = new SimpleGrantedAuthority(loginUser.getRoleName());
                authorities.add(grant);
                //封装自定义UserDetails类
                userDetails = new LoginUserDetails(loginUser, authorities);
            } else {
                throw new UsernameNotFoundException("该用户不存在！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDetails;
    }
}
