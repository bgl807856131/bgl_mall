package com.bgl.mall.controller.backend;

import com.bgl.mall.common.Constant;
import com.bgl.mall.common.ServerResponse;
//import IUserService;
import com.bgl.mall.pojo.User;
import com.bgl.mall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by BGL on 2017/5/21.
 */
@Controller
@RequestMapping("/manage/user")
public class UserManageController {
    @Autowired
    private IUserService iUserService;

    @RequestMapping(value="login.do",method = RequestMethod.POST)
    @ResponseBody
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
