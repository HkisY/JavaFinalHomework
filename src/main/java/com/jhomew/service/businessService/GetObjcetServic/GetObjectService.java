package com.jhomew.service.businessService.GetObjcetServic;

import com.jhomew.model.request.GetProductDetailRequest;
import com.jhomew.model.response.GetProductDetailByProductIdResponse;
import com.jhomew.model.result.GetObject.GetProductDetailByProductIdModelRequest;
import com.jhomew.model.result.ResultModel;
import org.springframework.web.bind.annotation.RequestBody;

public interface GetObjectService {

    public ResultModel<GetProductDetailByProductIdResponse> getProductDetailByProductId(@RequestBody GetProductDetailByProductIdModelRequest request);
}
