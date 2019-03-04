package com.bgl.mall.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Bu Guoliang
 * @date 2019/03/04 20:17
 * @since 1.8
 */
@Data
public class OrderItemVo {

    private Long orderNo;

    private Integer productId;

    private String productName;

    private String productImage;

    private BigDecimal currentUnitPrice;

    private Integer quantity;

    private BigDecimal totalPrice;

    private String createTime;
}
