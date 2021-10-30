package com.jhomew.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Hxin
 * @since 2021-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单号
     */
    private Integer userId;

    /**
     * 订单号
     */
    private Long orderNo;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 商品名称
     */
    private BigDecimal productName;

    /**
     * 商品图片地址
     */
    private String productImage;

    /**
     * 生成订单时的商品单价，单位：元，保留两位小数
     */
    private Integer currentUnitType;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 商品总价，单位：元，保留两位小数
     */
    private BigDecimal totalPrice;

    /**
     * 邮政编码
     */
    private Integer orderZip;

    /**
     * 收货地址
     */
    private String orderAddress;

    /**
     * 备注
     */
    private String orderRemark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 标记删除，0表示删除，1表示存在
     */
    private Integer state;


}
