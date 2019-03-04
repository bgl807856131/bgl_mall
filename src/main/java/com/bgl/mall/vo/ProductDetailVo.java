package com.bgl.mall.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by BGL on 2017/6/12.
 */
@Getter
@Setter
@NoArgsConstructor
public class ProductDetailVo {

    private Integer id ;

    private Integer categoryId;

    private String name;

    private String subtitle;

    private String mainImage;

    private String subImages;

    private String detail;

    private BigDecimal price;

    private Integer stock;

    private Integer status;

    private String imageHost;

    private Integer parentCategoryId;

    private String createTime;

    private String updateTime;
}
