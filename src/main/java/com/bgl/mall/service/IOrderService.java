package com.bgl.mall.service;

import com.bgl.mall.common.ServerResponse;

import java.util.Map;

/**
 * @author Bu Guoliang
 * @date 2019/03/02 00:10
 * @since 1.8
 */
public interface IOrderService {

    ServerResponse pay(Integer userId, Long orderNo, String path);

    ServerResponse alipayCallback(Map<String, String> paramMap);

    ServerResponse queryOrderPayStatus(Integer userId, Long orderNo);
}
