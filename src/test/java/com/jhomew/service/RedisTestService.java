//package com.jhomew.service;
//
//import com.jhomew.utils.RedisUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * @author Hxin
// * @version 1.0
// * @since 2021/10/15 9:59 下午
// */
//
//@Component("redisTestService")
//public class RedisTestService {
//    @Autowired
//    RedisUtil redisUtil;
//
//    public boolean setValue(String key,String value) {
//        try {
//            redisUtil.set(key, value);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public String getValue(String key) {
//        try {
//            String value = (String) redisUtil.get(key);
//            return value;
//        } catch (Exception e) {
//            return "读取缓存出错。。。";
//        }
//    }
//}
