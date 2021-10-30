package com.jhomew.service.businessService.loginService;

import com.jhomew.model.response.LoginResponse;
import com.jhomew.model.response.RegisterResponse;
import com.jhomew.model.result.ResultModel;
import com.jhomew.model.result.login.LoginModelRequest;
import com.jhomew.model.result.login.RegisterModelRequest;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/10/16 9:10 下午
 */

public interface LoginService {

    ResultModel<LoginResponse> login(LoginModelRequest loginModelRequest);

    ResultModel<RegisterResponse> register(RegisterModelRequest registerRequest);

    ResultModel<LoginResponse> checkLogin(String token);

    ResultModel logOut(String token);
}
