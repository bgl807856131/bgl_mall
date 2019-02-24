package com.bgl.mall.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Bu Guoliang
 * @date 2018/02/24 22:09
 * @since 1.8
 */
@Data
public class CartProductVo {

    private Integer id;

    private Integer userId;

    private Integer productId;

    /** 商品数量 */
    private Integer quantity;

    private String productName;

    /** 商品子标题 */
    private String productSubtitle;

    /** 商品主图 */
    private String productMainImage;

    private BigDecimal productPrice;

    private Integer productStatus;

    /** 商品小计 = productPrice * quantity */
    private BigDecimal productTotalPrice;

    private Integer productStock;

    private Integer productChecked;

    /** 限制数量的一个返回结果 */
    private String limitQuantity;
}
