package com.jhomew.model.response;

import com.jhomew.entity.Product;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class GetProductListByCategoryIdResponse implements Serializable {
    private static final long serialVersionUID=1L;


    List<GetProductListByCategoryIdProductInfo> productInfoList;

}
