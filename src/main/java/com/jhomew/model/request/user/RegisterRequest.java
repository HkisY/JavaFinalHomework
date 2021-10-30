package com.jhomew.model.request.user;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/10/18 9:20 下午
 */
@Data
public class RegisterRequest implements Serializable {
    private static final long serialVersionUID=1L;

    /**
     * 用户名，唯一索引
     */
    private String username;

    /**
     * 用户密码,加密
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 角色，0：管理员，1：商家，2-顾客
     */
    private Integer role;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后一次更新时间
     */
    private Date updateTime;

    /**
     * 标记，1表示存在，0表示删除
     */
    private Integer state;
}
