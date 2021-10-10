package com.jhomew.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
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
    public class PayInfo implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 主键
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
     * 支付平台，1-支付宝，2-微信
     */
      private Integer payPaltform;

      /**
     * 支付流水号
     */
      private String platformNumber;

      /**
     * 支付状态
     */
      private String platformStatus;

      /**
     * 创建时间
     */
      private LocalDate createTime;

      /**
     * 更新时间
     */
      private LocalDate updateTime;

      /**
     * 标记删除，0：删除，1:存在
     */
      private Integer state;


}
