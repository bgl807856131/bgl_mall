package com.bgl.mall.controller.backend;

import com.bgl.mall.common.Constant;
import com.bgl.mall.common.ServerResponse;
import com.bgl.mall.pojo.User;
import com.bgl.mall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

//import IUserService;

/**
 * Created by BGL on 2017/5/21.
 */
@RestController
@RequestMapping("/manage/user")
public class UserManageController {

    @Autowired
    private IUserService iUserService;

    @PostMapping(value="login.do")
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> response = iUserService.login(username, password);
        if(response.isSuccess()){
            User user = response.getData();
            if(user.getRole().equals(Constant.Role.ROLE_ADMIN)){
                session.setAttribute(Constant.CURRENT_USER, user);
                return response;
            }
        }else{
            return ServerResponse.createByErrorMessage("登录失败");
        }
        return response;
    }

}
