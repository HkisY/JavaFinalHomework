package com.jhomew.model.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetProductListByCategoryIdProductInfo {
    private static final long serialVersionUID=1L;


    private String name;

    private String subtittle;

    private String images;

    private String detail;

    private BigDecimal price;

    private Integer stock;
}
