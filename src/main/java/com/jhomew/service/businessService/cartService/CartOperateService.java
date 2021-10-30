package com.jhomew.service.businessService.cartService;

import com.jhomew.model.request.cart.CartMergeRequest;
import com.jhomew.model.response.CartDetailResponse;
import com.jhomew.model.result.ResultModel;
import com.jhomew.model.result.cart.AddCartModelRequest;

import java.util.List;

/**
 * 购物车功能业务逻辑
 * @author Lee
 * @version 1.0
 * @since 2021/10/29 4:27 下午
 */
public interface CartOperateService {
    ResultModel<String> addCartWithoutLogin(AddCartModelRequest addCartModelRequest);

    ResultModel<List<CartDetailResponse>> getCartDetail(AddCartModelRequest addCartModelRequest);

    ResultModel cartUpdate(AddCartModelRequest addCartModelRequest);

    ResultModel<String> addCartWithLogin(AddCartModelRequest addCartModelRequest);

    ResultModel<List<CartDetailResponse>> getLoginCartDetail(AddCartModelRequest addCartModelRequest);

    ResultModel merge(CartMergeRequest token);
}
