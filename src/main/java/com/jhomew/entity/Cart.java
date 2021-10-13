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
    public class Cart implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     *  主键
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 用户id
     */
      private Integer userId;

      /**
     * 商品id
     */
      private Integer productId;

      /**
     * 数量
     */
      private Integer quantity;

      /**
     * 是否选择，1：已选，2：未选
     */
      private Integer checked;

      /**
     * 创建时间
     */
      private LocalDate createTime;

      /**
     * 更新时间
     */
      private LocalDate updateTime;

      /**
     * 标记删除，0：删除，1：存在
     */
      private Integer state;


}