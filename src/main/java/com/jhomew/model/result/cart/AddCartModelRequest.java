package com.jhomew.model.result.cart;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/10/29 5:14 下午
 */
@Data
public class AddCartModelRequest implements Serializable {
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
