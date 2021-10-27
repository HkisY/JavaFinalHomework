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
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 类别状态，1-正常，2-已废弃
     */
    private Integer status;

    /**
     * 排序编号，同类展示顺序
     */
    private Integer sortOrder;

    /**
     * 创建时间
     */
    private LocalDate createTime;

    /**
     * 标记删除，0表示删除，1表示存在
     */
    private Integer state;


}
