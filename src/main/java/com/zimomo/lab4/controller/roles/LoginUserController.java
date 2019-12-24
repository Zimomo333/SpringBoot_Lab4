package com.zimomo.lab4.controller.roles;

import com.zimomo.lab4.entity.roles.LoginUser;
import com.zimomo.lab4.entity.security.LoginUserDetails;
import com.zimomo.lab4.service.roles.LoginUserService;
import com.zimomo.lab4.service.roles.SalesManagerService;
import com.zimomo.lab4.service.roles.SalesmanService;
import com.zimomo.lab4.service.roles.WarehouseKeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginUserController {
    @Autowired
    LoginUserService loginUserService;

    @Autowired
    SalesManagerService salesManagerService;

    @Autowired
    WarehouseKeeperService warehouseKeeperService;

    @Autowired
    SalesmanService salesmanService;

    @RequestMapping("/")
    public String index(Model model){
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model){
        return "login";
    }

    @RequestMapping("/information")
    public String information(Model model){
        LoginUserDetails loginUserDetails = (LoginUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LoginUser loginUser= loginUserDetails.getLoginUser();
        Object person=null;
        if(loginUser.getRoleName().equals("ROLE_SalesManager")){
            person = salesManagerService.findSalesManagerById(loginUser.getEmployee_Id());
        }else if(loginUser.getRoleName().equals("ROLE_Salesman")){
            person = salesmanService.findSalesmanById(loginUser.getEmployee_Id());
        }else if(loginUser.getRoleName().equals("ROLE_Keeper")){
            person = warehouseKeeperService.findKeeperById(loginUser.getEmployee_Id());
        }
        model.addAttribute("person",person);
        return "information";
    }

    @RequestMapping("/changePasswordPage")
    public String changePasswordPage(Model model){
        return "changePassword";
    }

    @RequestMapping("/changePassword")
    public String changePassword(Model model,String oldpassword,String newpassword,String confirmpassword){
        int signal;
        try {
            signal=loginUserService.changePassword(oldpassword,newpassword,confirmpassword);
        }catch (RuntimeException e){
            signal=3;
        }

        switch (signal){
            case 0:
                model.addAttribute("old_error","旧密码不正确！");
                break;
            case 1:
                model.addAttribute("confirm_error","两次输入密码不一致！");
                break;
            case 2:
                model.addAttribute("result","修改成功！");
                break;
            case 3:
                model.addAttribute("result","修改失败！请重试！");
                break;
        }
        return "changePassword";
    }
}
