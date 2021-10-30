package com.jhomew.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jhomew.entity.User;
import com.jhomew.model.constant.CookieConstant;
import com.jhomew.model.exception.LoginAndRegisterException;
import com.jhomew.model.request.cart.CartMergeRequest;
import com.jhomew.model.request.user.LoginRequest;
import com.jhomew.model.request.user.RegisterRequest;
import com.jhomew.model.response.LoginResponse;
import com.jhomew.model.response.RegisterResponse;
import com.jhomew.model.result.ResultModel;
import com.jhomew.model.result.login.LoginModelRequest;
import com.jhomew.model.result.login.RegisterModelRequest;
import com.jhomew.service.businessService.cartService.CartOperateService;
import com.jhomew.service.businessService.loginService.LoginService;
import com.jhomew.service.daoService.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 用户前端控制器
 * </p>
 *
 * @author Hxin
 * @since 2021-10-10
 */
@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService service;

    @Autowired
    private LoginService loginService;
    @Autowired
    private CartOperateService cartOperateService;

    /**
     * 登出
     *
     * @param token token
     * @return ResultModel
     */
    @GetMapping("/logOut")
    @ResponseBody
    public ResultModel logOut(String token) {
        try {
            if (StringUtils.isBlank(token)) {
                throw new LoginAndRegisterException("token is null or the parameter transformation is wrong");
            }
        } catch (LoginAndRegisterException e) {
            return ResultModel.error(e.getMessage());
        }
        return loginService.logOut(token);
    }

    /**
     * 验证是否登录，返回redis中的数据
     *
     * @param token token
     * @return ResultModel<LoginResponse>
     */
    @GetMapping("/checkLogin")
    @ResponseBody
    public ResultModel<LoginResponse> checkLogin(@RequestParam("token") String token) {
        try {
            if (StringUtils.isBlank(token)) {
                throw new LoginAndRegisterException("token is null or the parameter transformation is wrong");
            }
        } catch (LoginAndRegisterException e) {
            return ResultModel.error(e.getMessage());
        }
        return loginService.checkLogin(token);
    }

    /**
     * 登录
     *
     * @param request LoginRequest
     * @return ResultModel<LoginResponse>
     */
    @PostMapping("/login")
    @ResponseBody
    public ResultModel<LoginResponse> login(@RequestBody LoginRequest request,
                                            HttpServletRequest httpServletRequest) {
        try {
            if (Objects.isNull(request) || StringUtils.isBlank(request.getUsername())) {
                throw new LoginAndRegisterException("-----username is null or the frontside has incorrect parameter transformation");
            }
        } catch (LoginAndRegisterException e) {
            return ResultModel.error(e.getMessage());
        }
        //若后台接收前端参数需要多余填充数据，则进行实际赋值,如下注释行
        LoginModelRequest loginModelRequest = new LoginModelRequest();
        BeanUtils.copyProperties(request, loginModelRequest);
        //loginModelRequest.setImg("asdsadsda");
        ResultModel<LoginResponse> resultModel = loginService.login(loginModelRequest);
        //向后端传参cart_token && login_token
        CartMergeRequest cartMergeRequest = new CartMergeRequest();

        cartMergeRequest.setLoginToken(resultModel.getData().getToken());
        Cookie[] cookies = httpServletRequest.getCookies();
        if (Objects.nonNull(cookies)&&cookies.length>0){
            for (Cookie cookie : cookies) {
                if (CookieConstant.CART_TOKEN.equals(cookie.getName())) {
                    cartMergeRequest.setCartToken(cookie.getValue());
                    break;
                }
            }
        }
        //调用购物车的合并接口
        cartOperateService.merge(cartMergeRequest);
        //购物车在登录后要合并，到时候传参从cookie变为JWT token 故此处删除cookie

        return resultModel;
    }

    /**
     * 注册
     *
     * @param request RegisterRequest
     * @return ResultModel<RegisterResponse>
     */
    @PostMapping("/register")
    @ResponseBody
    public ResultModel<RegisterResponse> register(@RequestBody RegisterRequest request) {
        //此三项可在前端校验，此处的作用为防止前端传参出错
        try {
            if (Objects.isNull(request) || StringUtils.isBlank(request.getPassword()) || StringUtils.isBlank(request.getUsername())) {
                throw  new LoginAndRegisterException("-----message is null or the frontside has incorrect parameter transformation");
            }
        } catch (LoginAndRegisterException e) {
            return ResultModel.error(e.getMessage());
        }
        RegisterModelRequest registerRequest = new RegisterModelRequest();
        BeanUtils.copyProperties(request, registerRequest);
        registerRequest.setCreateTime(new Date());
        return loginService.register(registerRequest);
    }


    @RequestMapping("/list")
    @ResponseBody
    public List<User> list() {
        return service.list();
    }

    @PostMapping("/create")
    @ResponseBody
    public String create(@RequestBody User user) {
        boolean flag = service.save(user);
        String jsonArray = "";
        if (flag) {
            jsonArray = "" +
                    "{\n" +
                    "            \"code\": 20000, \"data\": \"success\"\n" +
                    "        } ";
        } else {
            jsonArray = "" +
                    "{\n" +
                    "            \"code\": -1, \"data\": \"false\"\n" +
                    "        } ";
        }

        System.out.println(jsonArray);

        return jsonArray;
    }

    @PostMapping("/update")
    @ResponseBody
    public String update(@RequestBody User user) {
        boolean flag = service.updateById(user);
        String jsonArray = "";
        if (flag) {
            jsonArray = "" +
                    "{\n" +
                    "            \"code\": 20000, \"data\": \"success\"\n" +
                    "        } ";
        } else {
            jsonArray = "" +
                    "{\n" +
                    "            \"code\": -1, \"data\": \"false\"\n" +
                    "        } ";
        }

        System.out.println(jsonArray);

        return jsonArray;
    }

    /**
     * 通过id删除数据
     *
     * @param id
     * @return
     */
    @PostMapping("/change")
    @ResponseBody
    public String deleteById(@RequestParam(name = "id") String id) {
        System.out.println(id);
        boolean flag = service.changeStateById(id);
        String jsonArray = "";
        if (flag) {
            jsonArray = "" +
                    "{\n" +
                    "            \"code\": 20000, \"data\": \"success\"\n" +
                    "        } ";
        } else {
            jsonArray = "" +
                    "{\n" +
                    "            \"code\": -1, \"data\": \"false\"\n" +
                    "        } ";
        }

        System.out.println(jsonArray);

        return jsonArray;
    }


    /**
     * 搜索功能，未完成
     *
     * @param username 用户名
     * @return
     */
    @GetMapping("/search")
    @ResponseBody
    @Nullable
    public User searchByUsername(@RequestParam(name = "username") String username) {
        System.out.println(username);
        User user = new User();
        return Objects.equals(user.getUsername(), "") ? null : user;
    }
}

