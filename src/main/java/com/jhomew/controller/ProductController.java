package com.jhomew.controller;


import com.jhomew.model.exception.LoginAndRegisterException;
import com.jhomew.model.request.GetProductDetailRequest;
import com.jhomew.model.response.GetProductDetailByProductIdResponse;
import com.jhomew.model.result.GetObject.GetProductDetailByProductIdModelRequest;
import com.jhomew.model.result.ResultModel;
import com.jhomew.service.businessService.GetObjcetServic.GetObjectService;
import com.jhomew.service.daoService.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hxin
 * @since 2021-10-10
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    GetObjectService getObjectService;

    @PostMapping("/productDetail")
    @ResponseBody
    public ResultModel<GetProductDetailByProductIdResponse> getProductDetailByProductId(@RequestBody GetProductDetailRequest request){
        if (Objects.isNull(request)){
            try {
                LoginAndRegisterException exception = new LoginAndRegisterException("商品id为空");
            }catch(Exception e){
                ResultModel.error(e.getMessage());
            }
        }
        GetProductDetailByProductIdModelRequest getProductDetailByProductIdModelRequest = new GetProductDetailByProductIdModelRequest();
        BeanUtils.copyProperties(request,getProductDetailByProductIdModelRequest);
        return getObjectService.getProductDetailByProductId(getProductDetailByProductIdModelRequest);

    }

}

