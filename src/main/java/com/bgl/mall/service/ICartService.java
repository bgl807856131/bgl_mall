package com.bgl.mall.service;

import com.bgl.mall.common.ServerResponse;
import com.bgl.mall.vo.CartVo;

/**
 * @author Bu Guoliang
 * @date 2019/02/24 20:43
 * @since 1.8
 */
public interface ICartService {

    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> deleteProduct(Integer userId, String productIds);

    ServerResponse<CartVo> list(Integer userId);

    ServerResponse<CartVo> selectOrUnSelect(Integer userId, Integer productId, Integer checked);

    ServerResponse<Integer> getCartProductCount(Integer userId);
}
