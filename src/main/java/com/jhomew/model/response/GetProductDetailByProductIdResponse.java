package com.jhomew.model.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetProductDetailByProductIdResponse {
    private Integer id;
    private Integer categoryId;
    private String name;
    private String subtittle;
    private String images;
    private String detail;
    private BigDecimal price;
    private Integer stock;
}
