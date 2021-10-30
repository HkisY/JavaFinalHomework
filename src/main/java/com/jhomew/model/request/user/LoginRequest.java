package com.jhomew.model.request.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/10/16 8:39 下午
 */
@Data
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

}
