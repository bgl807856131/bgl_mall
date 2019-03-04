package com.bgl.mall.controller.backend;

import com.bgl.mall.common.Constant;
import com.bgl.mall.common.ResponseCode;
import com.bgl.mall.common.ServerResponse;
import com.bgl.mall.pojo.User;
import com.bgl.mall.service.IOrderService;
import com.bgl.mall.service.IUserService;
import com.bgl.mall.util.JsonUtil;
import com.bgl.mall.vo.OrderVo;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Bu Guoliang
 * @date 2019/03/04 22:47
 * @since 1.8
 */
@RestController
@RequestMapping("/manage/order/")
@Slf4j
public class OrderManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IOrderService iOrderService;

    @PostMapping("list.do")
    public ServerResponse orderList(HttpSession session,
                                    @RequestParam(value = "pageNum", defaultValue = "1")int pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10")int pageSize) {
        User user = (User)session.getAttribute(Constant.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //填充业务
            ServerResponse response = iOrderService.manageList(pageNum, pageSize);
            log.info("【/manage/order/orderList】 response data ==> {}", JsonUtil.obj2String(response));
            return response;
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @PostMapping("detail.do")
    public ServerResponse<OrderVo> orderDetail(HttpSession session, Long orderNo) {
        log.info("【/manage/order/orderDetail】 request params ==> orderNo = {}", orderNo);
        User user = (User)session.getAttribute(Constant.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            ServerResponse response = iOrderService.manageDetail(orderNo);
            log.info("【/manage/order/orderDetail】 response data ==> {}", JsonUtil.obj2String(response));
            return response;
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @PostMapping("search.do")
    public ServerResponse<PageInfo> orderSearch(HttpSession session, Long orderNo,
                                                @RequestParam(value = "pageNum", defaultValue = "1")int pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "10")int pageSize) {
        log.info("【manage/order/orderSearch】 request params ==> orderNo = {}", orderNo);
        User user = (User)session.getAttribute(Constant.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            ServerResponse response = iOrderService.manageSearch(orderNo, pageNum, pageSize);
            log.info("【/manage/order/orderSearch】 response data ==> {}", JsonUtil.obj2String(response));
            return response;
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @PostMapping("send_goods.do")
    public ServerResponse<String> orderSendGoods(HttpSession session, Long orderNo) {
        log.info("【/manage/order/orderSendGoods】 request params ==> orderNo = {}", orderNo);
        User user = (User)session.getAttribute(Constant.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            ServerResponse response = iOrderService.manageSendGoods(orderNo);
            log.info("【/manage/order/orderSendGoods】 response data ==> {}", JsonUtil.obj2String(response));
            return response;
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }
}
