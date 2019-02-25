package com.bgl.mall.controller.portal;

import com.bgl.mall.common.Constant;
import com.bgl.mall.common.ResponseCode;
import com.bgl.mall.common.ServerResponse;
import com.bgl.mall.pojo.Shipping;
import com.bgl.mall.pojo.User;
import com.bgl.mall.service.IShippingService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by BGL on 2017/8/15.
 */
@RestController
@RequestMapping("/shipping/")
@Slf4j
public class ShippingController {

    @Autowired
    private IShippingService iShippingService;

    @PostMapping("add.do")
    public ServerResponse add(HttpSession session, Shipping shipping){
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iShippingService.add(user.getId(), shipping);
    }

    @PostMapping("del.do")
    public ServerResponse delete(HttpSession session, Integer shippingId){
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录，需要强制登录status=10");
        }
        return iShippingService.delete(user.getId(), shippingId);
    }

    @PostMapping("update.do")
    public ServerResponse update(HttpSession session, Shipping shipping){
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录，需要强制登录status=10");
        }
        return iShippingService.update(user.getId(), shipping);
    }

    @PostMapping("select.do")
    public ServerResponse select(HttpSession session, Integer shippingId){
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录，需要强制登录status=10");
        }
        return iShippingService.select(user.getId(), shippingId);
    }

    @PostMapping("list.do")
    public ServerResponse<PageInfo> list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                                         HttpSession session){
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录，需要强制登录status=10");
        }
        return iShippingService.list(user.getId(), pageNum, pageSize);
    }
}
