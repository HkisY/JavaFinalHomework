package com.jhomew.model.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GetCategoryInfoListResponse implements Serializable {
    private static final long serialVersionUID=1L;

    List<GetCategoryInfoListCategoryInfo> categoryInfoList;
}
