package com.jhomew.service.businessService.GetListService.impl;

import com.jhomew.entity.Category;
import com.jhomew.entity.Product;
import com.jhomew.mapper.CategoryMapper;
import com.jhomew.model.response.GetCategoryInfoListCategoryInfo;
import com.jhomew.model.response.GetCategoryInfoListResponse;
import com.jhomew.model.response.GetProductListByCategoryIdResponse;
import com.jhomew.model.result.GetList.GetProductListByCategoryIDModelRequest;
import com.jhomew.model.result.ResultModel;
import com.jhomew.service.businessService.GetListService.GetListService;
import com.jhomew.service.daoService.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetListServiceImpl implements GetListService {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public ResultModel<GetProductListByCategoryIdResponse> getProductListByCategoryID(GetProductListByCategoryIDModelRequest request) {
        Map<String,Object> map = new HashMap<>();
        map.put("category_id",request.getCategoryId());
        //在售
        map.put("status",1);
        List<Product> productList =productService.listByMap(map);
        if (productList.size() == 0){
            return ResultModel.error("没有商品");
        }
        GetProductListByCategoryIdResponse getProductListByCategoryIdResponse = new GetProductListByCategoryIdResponse();
        BeanUtils.copyProperties(productList,getProductListByCategoryIdResponse.getProductInfoList());
        return ResultModel.success("查询成功",getProductListByCategoryIdResponse);
    }

    @Override
    public ResultModel<GetCategoryInfoListResponse> GetCategoryInfoList() {
        List<Category> categoryList = categoryMapper.selectList(null);
        GetCategoryInfoListResponse  getCategoryInfoListResponse= new GetCategoryInfoListResponse();
        BeanUtils.copyProperties(categoryList,getCategoryInfoListResponse.getCategoryInfoList());
        for(GetCategoryInfoListCategoryInfo categoryInfo:getCategoryInfoListResponse.getCategoryInfoList()){
            Map<String,Object> map = new HashMap<>();
            map.put("id",categoryInfo.getId());
            BeanUtils.copyProperties(productService.listByMap(map),categoryInfo.getProductInfoList());
        }
        return ResultModel.success("查询成功",getCategoryInfoListResponse);
    }
}
