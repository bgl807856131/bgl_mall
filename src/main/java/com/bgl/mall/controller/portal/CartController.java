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

    @PostMapping("list.do")
    public ServerResponse<CartVo> list(HttpSession session){
        log.info("【cart/list】 request params ==> session = {}", JsonUtil.obj2String(session));
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        ServerResponse<CartVo> response = iCartService.list(user.getId());
        log.info("【cart/list】 response data ==> {}", JsonUtil.obj2String(response));
        return response;
    }

    @PostMapping("add.do")
    public ServerResponse<CartVo> add(HttpSession session, Integer count, Integer productId){
        log.info("【cart/add】 request params ==> product = {}, count = {}", productId, count);
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        ServerResponse<CartVo> response = iCartService.add(user.getId(), productId, count);
        log.info("【cart/add】 response data ==> {}", JsonUtil.obj2String(response));
        return response;
    }

    @PostMapping("update.do")
    public ServerResponse<CartVo> update(HttpSession session, Integer count, Integer productId){
        log.info("【cart/update】 request params ==> product = {}, count = {}", productId, count);
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        ServerResponse<CartVo> response = iCartService.update(user.getId(), productId, count);
        log.info("【cart/update】 response data ==> {}", JsonUtil.obj2String(response));
        return response;
    }

    @PostMapping("delete_product.do")
    public ServerResponse<CartVo> deleteProduct(HttpSession session, String productIds){
        log.info("【cart/deleteProduct】 request params ==> product = {}", productIds);
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        ServerResponse<CartVo> response = iCartService.deleteProduct(user.getId(), productIds);
        log.info("【cart/deleteProduct】 response data ==> {}", JsonUtil.obj2String(response));
        return response;
    }

    @PostMapping("select_all.do")
    public ServerResponse<CartVo> selectAll(HttpSession session){
        log.info("【cart/selectAll】 request params ==> session = {}", JsonUtil.obj2String(session));
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        ServerResponse<CartVo> response = iCartService.selectOrUnSelect(user.getId(), null, Constant.Cart.CHECKED);
        log.info("【cart/selectAll】 response data ==> {}", JsonUtil.obj2String(response));
        return response;
    }

    @PostMapping("un_select_all.do")
    public ServerResponse<CartVo> unSelectAll(HttpSession session){
        log.info("【cart/unSelectAll】 request params ==> session = {}", JsonUtil.obj2String(session));
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        ServerResponse<CartVo> response = iCartService.selectOrUnSelect(user.getId(), null, Constant.Cart.UN_CHECKED);
        log.info("【cart/unSelectAll】 response data ==> {}", JsonUtil.obj2String(response));
        return response;
    }

    @PostMapping("select.do")
    public ServerResponse<CartVo> select(HttpSession session, Integer productId){
        log.info("【cart/select】 request params ==> productId = {}", productId);
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        ServerResponse<CartVo> response = iCartService.selectOrUnSelect(user.getId(), productId, Constant.Cart.CHECKED);
        log.info("【cart/select】 response data ==> {}", JsonUtil.obj2String(response));
        return response;
    }

    @PostMapping("un_select.do")
    public ServerResponse<CartVo> unSelect(HttpSession session, Integer productId){
        log.info("【cart/unSelect】 request params ==> productId = {}", productId);
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        ServerResponse<CartVo> response = iCartService.selectOrUnSelect(user.getId(), productId, Constant.Cart.UN_CHECKED);
        log.info("【cart/unSelect】 response data ==> {}", JsonUtil.obj2String(response));
        return response;
    }

    @PostMapping("get_cart_product_count.do")
    public ServerResponse<Integer> getCartProductCount(HttpSession session){
        log.info("【cart/getCartProductCount】 request params ==> session = {}", JsonUtil.obj2String(session));
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createBySuccess(0);
        }
        ServerResponse<Integer> response = iCartService.getCartProductCount(user.getId());
        log.info("【cart/getCartProductCount】 response data ==> {}", JsonUtil.obj2String(response));
        return response;
    }
}
