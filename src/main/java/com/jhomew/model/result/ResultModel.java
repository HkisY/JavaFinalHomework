package com.jhomew.model.result;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/10/16 8:29 下午
 */
@Data
public class ResultModel<T> implements Serializable {
    /**
     * 成功标志
     */
    private Boolean success;
    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    /**
     * 失败信息
     */
    private String message;
    /**
     * 返回的状态码
     */
    private Integer code;

    /**
     * 数据信息
     */
    private T data;

    private static final ResultModel resultModel = new ResultModel();

    public static ResultModel success(){
        resultModel.setSuccess(true);
        //code : 200 成功
        resultModel.setCode(200);
        resultModel.setTimestamp(new Date().getTime());
        resultModel.setMessage("success");
        resultModel.setData(null);
        return resultModel;
    }

    public static ResultModel success(String message){
        resultModel.setSuccess(true);
        //code : 200 成功
        resultModel.setCode(200);
        resultModel.setTimestamp(new Date().getTime());
        resultModel.setMessage(message);
        resultModel.setData(null);
        return resultModel;
    }

    public static ResultModel success(String message,Object obj){
        resultModel.setSuccess(true);
        //code : 200 成功
        resultModel.setCode(200);
        resultModel.setTimestamp(new Date().getTime());
        resultModel.setMessage(message);
        resultModel.setData(obj);
        return resultModel;
    }
    public static ResultModel success(Object obj){
        resultModel.setSuccess(true);
        //code : 200 成功
        resultModel.setCode(200);
        resultModel.setTimestamp(new Date().getTime());
        resultModel.setMessage("success");
        resultModel.setData(obj);
        return resultModel;
    }

    public static ResultModel error(){
        resultModel.setSuccess(false);
        //code : 500 失败
        resultModel.setCode(500);
        resultModel.setTimestamp(new Date().getTime());
        resultModel.setMessage("error");
        resultModel.setData(null);
        return resultModel;
    }

    public static ResultModel error(String message){
        resultModel.setSuccess(false);
        //code : 500 失败
        resultModel.setCode(500);
        resultModel.setTimestamp(new Date().getTime());
        resultModel.setMessage(message);
        resultModel.setData(null);
        return resultModel;
    }
}
