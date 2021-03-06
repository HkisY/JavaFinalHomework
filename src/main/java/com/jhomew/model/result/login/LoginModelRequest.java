package com.jhomew.model.result.login;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/10/16 9:22 下午
 */
@Data
public class LoginModelRequest implements Serializable {
    private String username;
    private String password;
}
