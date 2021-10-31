package com.jhomew.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetProductListRequest implements Serializable {
    private static final long serialVersionUID=1L;

    private  Integer categoryId;
}
