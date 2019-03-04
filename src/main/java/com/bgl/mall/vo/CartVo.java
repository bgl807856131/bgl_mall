package com.bgl.mall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Bu Guoliang
 * @date 2018/02/24 22:16
 * @since 1.8
 */
@Data
public class CartVo {

    private List<CartProductVo> cartProductVoList;

    /** 购物车总价 */
    private BigDecimal cartTotalPrice;

    /** 是否全部勾选 */
    private Boolean allChecked;

    private String imageHost;
}
