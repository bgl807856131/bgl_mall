package com.bgl.mall.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by BGL
 */
@Slf4j
public class PropertiesUtil {

    private static Properties props;

    static {
        String fileName = "bglmall.properties";
        props = new Properties();
        try {
            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName), "utf-8"));
//            props.load(new InputStreamReader(ClassLoader.getSystemResourceAsStream(fileName), "utf-8"));
        } catch (IOException e) {
            log.error("读取配置发生错误", e);
        }
    }

    public static String getProperty(String key){
        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            return null;
        }
        return value.trim();
    }

    public static String getProperty(String key, String defaultValue){
        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            value = defaultValue;
        }
        return value.trim();
    }

    public static void main(String[] args) {
        System.out.println(props.get("password.salt"));
    }
}
