package com.bgl.mall.common;

import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.Set;

/**
 * Created by BGL on 2017/5/18.
 */
public class Constant {
    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";

    public static final String USERNAME = "username";

    public interface Role{
        int ROLE_CUSTOMER = 0;//普通用户
        int ROLE_ADMIN = 1;//管理员用户
    }

    public interface ProductListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc", "price_asc");
    }

    @Getter
    public enum  ProductStatusEnum{
        ON_SALE(1, "在线");

        private int code;

        private String value;

        ProductStatusEnum(int code, String value){
            this.code = code;
            this.value = value;
        }
    }

    public interface Cart{
        int CHECKED = 1;//表示选中状态
        int UN_CHECKED = 0;//表示未选中状态

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_FAIL";
    }

    @Getter
    public enum OrderStatusEnum{
        CANCELED(0, "已取消"),
        NO_PAY(10, "未支付"),
        PAID(20, "已付款"),
        SHIPPED(40, "已发货"),
        ORDER_SUCCESS(50, "订单完成"),
        ORDER_CLOSE(60, "订单关闭");

        private final int code;
        private final String value;

        OrderStatusEnum(int code, String value){
            this.code = code;
            this.value = value;
        }
    }

    public interface aliCallbck{
        String TRADE_STATUS_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
        String TRADE_STATUS_TRADE_SUCCESS = "TRADE_SUCCESS";

        String RESPONSE_SUCCESS = "success";
        String RESPONSE_FAILED = "failed";
    }

    @Getter
    public enum PayPlatformEnum{
        ALIPAY(1, "支付宝");

        private final int code;
        private final String value;

        PayPlatformEnum(int code, String value){
            this.code = code;
            this.value = value;
        }
    }
}
