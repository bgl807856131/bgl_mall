package com.bgl.mall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Bu Guoliang
 * @date 2019/03/04 21:10
 * @since 1.8
 */
@Data
public class OrderProductVo {

    private List<OrderItemVo> orderItemVoList;

    private BigDecimal productTotalPrice;

    private String imageHost;
}
