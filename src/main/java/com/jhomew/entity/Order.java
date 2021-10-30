package com.jhomew.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 订单号
     */
    private Long orderNo;

    /**
     * 地址
     */
    private Integer shippingId;

    /**
     * 实际付款金额，单位：元，保留两位小数
     */
    private BigDecimal payment;

    /**
     * 支付类型，1-在线支付，2-货到付款
     */
    private Integer paymentType;

    /**
     * 运费
     */
    private Integer postage;

    /**
     * 订单状态，0-已取消，1-未支付，2-已付款，3-已发货，4-交易成功，5-交易关闭
     */
    private Integer status;

    /**
     * 支付时间
     */
    private Date paymentTime;

    /**
     * 发货时间
     */
    private Date sendTime;

    /**
     * 交易完成时间
     */
    private Date endTime;

    /**
     * 交易关闭时间
     */
    private Date closeTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 标记删除，0表示删除，1表示存在
     */
    private Integer state;


}
