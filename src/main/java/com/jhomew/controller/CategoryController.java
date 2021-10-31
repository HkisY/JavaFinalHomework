package com.jhomew.controller;


import com.jhomew.model.exception.LoginAndRegisterException;
import com.jhomew.model.request.GetProductListRequest;
import com.jhomew.model.response.GetCategoryInfoListResponse;
import com.jhomew.model.response.GetProductListByCategoryIdResponse;
import com.jhomew.model.result.GetList.GetProductListByCategoryIDModelRequest;
import com.jhomew.model.result.ResultModel;
import com.jhomew.service.businessService.GetListService.GetListService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    GetListService getListService;




    @PostMapping("/list/productList")
    @ResponseBody
    public ResultModel<GetProductListByCategoryIdResponse> GetProductListByCategoryID(@RequestBody GetProductListRequest request){
        if (Objects.isNull(request)){
            try {
                LoginAndRegisterException exception = new LoginAndRegisterException("分类id为空");
            }catch(Exception e){
                ResultModel.error(e.getMessage());
            }
        }
        GetProductListByCategoryIDModelRequest getProductListByCategoryIDModelRequest = new GetProductListByCategoryIDModelRequest();
        BeanUtils.copyProperties(request,getProductListByCategoryIDModelRequest);
        return getListService.getProductListByCategoryID(getProductListByCategoryIDModelRequest);
    }

    @PostMapping("/list")
    @ResponseBody
    public ResultModel<GetCategoryInfoListResponse>  GetCategoryInfoList(){
        return getListService.GetCategoryInfoList();
    }

}

