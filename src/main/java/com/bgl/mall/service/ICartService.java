package com.bgl.mall.service;

import com.bgl.mall.common.ServerResponse;
import net.sf.jsqlparser.schema.Server;

/**
 * @author Bu Guoliang
 * @date 2019/02/24 20:43
 * @since 1.8
 */
public interface ICartService {

    public ServerResponse add(Integer userId, Integer productId, Integer count);
}
