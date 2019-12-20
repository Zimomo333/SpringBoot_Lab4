package com.zimomo.lab4.controller.roles;

import com.zimomo.lab4.service.roles.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginUserController {
    @Autowired
    private LoginUserService loginUserService;

//    @RequestMapping("/insert_loginuser")
//    public void insert(LoginUser loginUser){
//        loginUserService.insert(loginUser);
//    }

    @RequestMapping("/")
    public String index(Model model){
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model){
        return "login";
    }
}
