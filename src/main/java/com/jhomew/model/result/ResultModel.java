package com.jhomew.model.result;


import java.io.Serializable;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/10/16 8:29 下午
 */

public class ResultModel<T> implements Serializable {

    /**
     *信息
     */
    private String message;
    /**
     * 返回的状态码
     */
    private Integer code;

    /**
     *数据信息
     */
    private T data;

    private static final ResultModel<String> resultModel = new ResultModel<>();

    public static ResultModel<String> success(){
        return null;
    }
    public static ResultModel<String> success(String message){
        resultModel.setMessage(message);
        resultModel.setData(null);
        resultModel.setCode(500);
        return resultModel;
    }
    public static ResultModel<String> success(String message,Object data){
        return null;
    }

    public static ResultModel<String> error(String message){
        resultModel.setMessage(message);
        resultModel.setData(null);
        resultModel.setCode(500);
        return resultModel;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
