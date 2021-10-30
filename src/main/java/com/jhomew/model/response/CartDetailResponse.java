package com.jhomew.model.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/10/29 9:06 下午
 */
@Data
public class CartDetailResponse implements Serializable {
    /**
     * 购物车中商品的数量
     */
    private Integer count;

    //--------------------------------------

    /**
     * 商品id
     */
    private Integer id;

    /**
     * 分类id,对应category表中的主键
     */
    private Integer categoryId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品副标题
     */
    private String subtittle;

    /**
     * 商品图片地址
     */
    private String images;

    /**
     * 商品详情
     */
    private String detail;

    /**
     * 价格，单位：元，保留两位小数
     */
    private BigDecimal price;

    /**
     * 库存数量
     */
    private Integer stock;

    /**
     * 商品状态，1-在售，2-下架，3-删除
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDate createTime;

    /**
     * 更新时间
     */
    private LocalDate updateTime;

    /**
     * 标记商品：1表示在售，0表示删除
     */
    private Integer state;
}
