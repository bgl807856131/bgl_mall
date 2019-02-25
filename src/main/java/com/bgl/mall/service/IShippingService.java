package com.bgl.mall.service;

import com.bgl.mall.common.ServerResponse;
import com.bgl.mall.pojo.Shipping;
import com.github.pagehelper.PageInfo;

/**
 * Created by BGL on 2017/8/15.
 */
public interface IShippingService {

    ServerResponse add(Integer userId, Shipping shipping);

    ServerResponse<String> delete(Integer userId, Integer shippingId);

    ServerResponse update(Integer userId, Shipping shipping);

    ServerResponse<Shipping> select(Integer userId, Integer shippingId);

    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
}
