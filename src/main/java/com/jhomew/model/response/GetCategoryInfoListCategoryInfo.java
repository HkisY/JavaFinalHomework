package com.jhomew.model.response;

import lombok.Data;

import java.util.List;

@Data
public class GetCategoryInfoListCategoryInfo {
    private Integer id;


    private String name;

    private List<GetCategoryInfoListProductInfo> productInfoList;
}
