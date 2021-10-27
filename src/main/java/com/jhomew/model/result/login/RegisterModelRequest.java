package com.jhomew.model.result.login;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/10/24 1:41 下午
 */
@Data
public class RegisterModelRequest implements Serializable {
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
}
