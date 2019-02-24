package com.bgl.mall.controller.portal;

import com.bgl.mall.common.Constant;
import com.bgl.mall.common.ResponseCode;
import com.bgl.mall.common.ServerResponse;
import com.bgl.mall.pojo.User;
import com.bgl.mall.service.ICartService;
import com.bgl.mall.util.JsonUtil;
import com.bgl.mall.vo.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Author Bu Guoliang
 * @Date 2018/02/24 20:36
 * @Since 1.8
 */
@RestController
@RequestMapping("/cart/")
@Slf4j
public class CartController {

    @Autowired
    private ICartService iCartService;

    @PostMapping("add.do")
    public ServerResponse add(HttpSession session, Integer count, Integer productId){
        log.info("【cart/add】 request params ==> product = {}, count = {}", productId, count);
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        ServerResponse<CartVo> response = iCartService.add(user.getId(), productId, count);
        log.info("【cart/add】 response data ==> {}", JsonUtil.obj2String(response));
        return response;
    }
}
