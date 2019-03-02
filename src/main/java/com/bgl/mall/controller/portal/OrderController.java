package com.bgl.mall.controller.portal;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.bgl.mall.common.Constant;
import com.bgl.mall.common.ResponseCode;
import com.bgl.mall.common.ServerResponse;
import com.bgl.mall.pojo.User;
import com.bgl.mall.service.IOrderService;
import com.bgl.mall.util.JsonUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Bu Guoliang
 * @date 2019/03/02 00:04
 * @since 1.8
 */
@RestController
@RequestMapping("/order/")
@Slf4j
public class OrderController {

    @Autowired
    private IOrderService iOrderService;

    @PostMapping("pay.do")
    public ServerResponse pay(HttpSession session, Long orderNo, HttpServletRequest request) {
        log.info("【order/pay】 request params ==> orderNo = {}", orderNo);
        User currentUser = (User)session.getAttribute(Constant.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        String path = request.getSession().getServletContext().getRealPath("upload");
        log.info("【order/pay】 path : {}", path);
        ServerResponse response = iOrderService.pay(currentUser.getId(), orderNo, path);
        log.info("【order/pay】 response data ==> {}", JsonUtil.obj2String(response));
        return response;
    }

    @RequestMapping("alipay_callback.do")
    public Object alipayCallback(HttpServletRequest request) {
        Map<String, String> paramMap = Maps.newHashMap();
        //获取支付宝的回调参数
        Map<String, String[]> requestParams = request.getParameterMap();
        requestParams.forEach((key, value) -> {
            String valueStr = "";
            for (int i = 0; i < value.length; i++) {
                valueStr = (i == value.length - 1) ? valueStr + value[i] : valueStr + value[i] + ",";
            }
            paramMap.put(key, valueStr);
        });
        log.info("支付宝回调，sign：{}，trade_status：{}，参数：{}", paramMap.get("sign"), paramMap.get("trade_status"), paramMap.toString());

        //验证回调的正确性，判断是否是支付宝的回调
        paramMap.remove("sign_type");
        try {
            boolean alipayRSACheckedV2 = AlipaySignature.rsaCheckV2(paramMap, Configs.getAlipayPublicKey(), "utf-8", Configs.getSignType());
            if (!alipayRSACheckedV2) {
                return ServerResponse.createByErrorMessage("非法请求,验证不通过,再恶意请求我就报警找网警了");
            }
        } catch (AlipayApiException e) {
            log.error("支付宝验证回调异常",e);
        }
        //TODO 验证各种数据

        //处理回调，将数据入库
        ServerResponse response = iOrderService.alipayCallback(paramMap);
        if (response.isSuccess()) {
            return ServerResponse.createBySuccess(Constant.aliCallbck.RESPONSE_SUCCESS);
        }
        return ServerResponse.createBySuccess(Constant.aliCallbck.RESPONSE_FAILED);
    }

    @PostMapping("query_order_pay_status.do")
    public ServerResponse<Boolean> queryOrderPayStatus(HttpSession session, Long orderNo) {
        log.info("【order/queryOrderPayStatus】 request params ==> orderNo = {}", orderNo);
        User currentUser = (User)session.getAttribute(Constant.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        ServerResponse response = iOrderService.queryOrderPayStatus(currentUser.getId(), orderNo);
        if (response.isSuccess()) {
            return ServerResponse.createBySuccess(true);
        }
        return ServerResponse.createBySuccess(false);
    }
}
