package com.jhomew.model.response;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/10/17 8:19 下午
 */
@Data
public class LoginResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

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
    private LocalDate createTime;

    /**
     * 最后一次更新时间
     */
    private LocalDate updateTime;

    /**
     * 标记，1表示存在，0表示删除
     */
    private Integer state;

    /**
     * 假设这里有后台需要添加的数据
     * 头像图片
     */
    private String img;

    private String token;

    /**
     * 前端检测参数
     */
    private int frontState;

    private String message;
}
