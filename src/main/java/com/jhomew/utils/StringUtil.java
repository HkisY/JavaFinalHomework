package com.jhomew.utils;

import com.jhomew.model.constant.RedisConstant;

import java.util.UUID;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/10/16 10:30 下午
 */
public class StringUtil {
    /**
     * 链接redis的key，登录用
     */
    public static String tokenContact(String token) {
        StringBuffer sb = new StringBuffer(RedisConstant.LOGIN_TOKEN_PRE);
        sb.append(token);
        return sb.toString();
    }

    /**
     * 链接redis的key，购物车用
     */
    //TODO: 和上面的方法可以重构为一个方法
    public static String cartTokenContact(String token) {
        StringBuffer sb = new StringBuffer(RedisConstant.CART_TOKEN_PRE);
        sb.append(token);
        return sb.toString();
    }

    /**
     * 对UUID进行处理
     * @return uuid
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
