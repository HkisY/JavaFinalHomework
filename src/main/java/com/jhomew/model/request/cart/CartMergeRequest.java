package com.jhomew.model.request.cart;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/10/30 5:18 下午
 */
@Data
public class CartMergeRequest implements Serializable {
    private String loginToken;
    private String cartToken;
}
