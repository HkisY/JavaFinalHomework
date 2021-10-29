package com.jhomew.service.businessService.GetListService;

import com.jhomew.model.response.GetCategoryInfoListResponse;
import com.jhomew.model.response.GetProductListByCategoryIdResponse;
import com.jhomew.model.result.GetProductList.GetProductListByCategoryIDModelRequest;
import com.jhomew.model.result.ResultModel;

import java.util.List;

public interface GetListService {

    public ResultModel<GetProductListByCategoryIdResponse> getProductListByCategoryID(GetProductListByCategoryIDModelRequest request);

    public ResultModel<GetCategoryInfoListResponse> GetCategoryInfoList();
}
