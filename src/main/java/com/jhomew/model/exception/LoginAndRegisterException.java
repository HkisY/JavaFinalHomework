package com.jhomew.model.exception;

/**
 * 登录注册相关异常类
 * @author Hxin
 * @version 1.0
 * @since 2021/10/16 8:27 下午
 */
public class LoginAndRegisterException extends RuntimeException{

    //构造函数
    public LoginAndRegisterException(String message){
        super(message);
    }
}
