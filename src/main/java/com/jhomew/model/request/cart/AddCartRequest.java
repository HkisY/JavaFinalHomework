package com.jhomew.model.request.cart;

import lombok.Data;

import java.io.Serializable;

/**
 * 封装前端传回参数
 * @author Hxin
 * @version 1.0
 * @since 2021/10/29 5:06 下午
 */
@Data
public class AddCartRequest implements Serializable {
    /**
     * 登录状态传回用户login:token  未登录传回cart:token
     */
    private String token;
    /**
     * 商品Id
     */
    private Long productId;
    /**
     * 添加购物车的商品的数量
     */
    private int count;
}
