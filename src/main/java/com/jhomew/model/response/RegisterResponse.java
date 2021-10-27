package com.jhomew.model.response;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/10/18 9:18 下午
 */
@Data
public class RegisterResponse implements Serializable {
    private static final long serialVersionUID=1L;
    /**
     * 前端检测参数
     */
    private int frontState;

    private String message;
}
