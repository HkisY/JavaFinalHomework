package com.jhomew.utils;

import com.jhomew.model.constant.RedisConstant;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/10/16 10:30 下午
 */
public class StringUtil {
    /**
     * 链接redis的key
     */
    public static String tokenContact(String token) {
        StringBuffer sb = new StringBuffer(RedisConstant.LOGIN_TOKEN_PRE);
        sb.append(token);
        return sb.toString();
    }
}
