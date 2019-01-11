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
}
