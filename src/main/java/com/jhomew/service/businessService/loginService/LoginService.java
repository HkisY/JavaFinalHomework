package com.jhomew.service.businessService.loginService;

import com.jhomew.model.response.LoginResponse;
import com.jhomew.model.result.ResultModel;
import com.jhomew.model.result.login.LoginModelRequest;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/10/16 9:10 下午
 */

public interface LoginService {

    ResultModel<LoginResponse> login(LoginModelRequest loginModelRequest);
}
