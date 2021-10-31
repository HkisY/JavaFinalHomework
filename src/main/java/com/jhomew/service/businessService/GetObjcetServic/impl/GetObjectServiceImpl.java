package com.jhomew.service.businessService.GetObjcetServic.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jhomew.entity.Product;
import com.jhomew.model.response.GetProductDetailByProductIdResponse;
import com.jhomew.model.result.GetObject.GetProductDetailByProductIdModelRequest;
import com.jhomew.model.result.ResultModel;

import com.jhomew.service.businessService.GetObjcetServic.GetObjectService;
import com.jhomew.service.daoService.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class GetObjectServiceImpl implements GetObjectService {

    @Autowired
    ProductService productService;
    @Override
    public ResultModel<GetProductDetailByProductIdResponse> getProductDetailByProductId(GetProductDetailByProductIdModelRequest request) {
        GetProductDetailByProductIdResponse getProductDetailByProductIdResponse = new GetProductDetailByProductIdResponse();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id",request.getProductId());
        Product product = productService.getOne(wrapper);
        if(product.equals(null)){
            return ResultModel.error("没有此产品");
        }
        BeanUtils.copyProperties(product,getProductDetailByProductIdResponse);
        return ResultModel.success("查询成功",getProductDetailByProductIdResponse);
    }
}
