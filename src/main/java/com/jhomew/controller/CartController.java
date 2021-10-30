package com.jhomew.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jhomew.model.constant.CookieConstant;
import com.jhomew.model.constant.JwtConstant;
import com.jhomew.model.exception.CartException;
import com.jhomew.model.request.cart.AddCartRequest;
import com.jhomew.model.response.CartDetailResponse;
import com.jhomew.model.response.LoginResponse;
import com.jhomew.model.result.ResultModel;
import com.jhomew.model.result.cart.AddCartModelRequest;
import com.jhomew.service.businessService.cartService.CartOperateService;
import com.jhomew.service.businessService.loginService.LoginService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 购物车前端控制器
 * </p>
 *
 * @author Hxin
 * @since 2021-10-10
 */
@Controller
@RequestMapping("//cart")
public class CartController {

    @Autowired
    private CartOperateService cartOperateService;

    /**
     * 进行用户是否登录检查需要用到loginService中的checkLogin服务
     */
    @Autowired
    private LoginService loginService;

    /**
     * 购物车中商品数量更新
     * @param request AddCartRequest
     * @param servletRequest HttpServletRequest
     * @return ResultModel
     */
    @PostMapping("/update")
    @ResponseBody
    public ResultModel cartUpdate(@RequestBody AddCartRequest request,
                                  HttpServletRequest servletRequest) {
        try {
            if (Objects.isNull(request)) {
                throw new CartException("Req parameter is null or productID is null or parameter transformation is wrong!");
            }
        } catch (CartException e) {
            return ResultModel.error(e.getMessage());
        }

        AddCartModelRequest addCartModelRequest = new AddCartModelRequest();
        BeanUtils.copyProperties(request, addCartModelRequest);

        //TODO 可重构为service层进行token内容检验，使上层代码仅关注于传参
        if (!StringUtils.isBlank(request.getToken())){
            //登录状态传参
            ResultModel<LoginResponse> resultModel = loginService.checkLogin(request.getToken());
            if (Objects.nonNull(resultModel.getData()) && resultModel.getData().getFrontState() ==1){
                //对JWT进行解析
                Claims claims = Jwts.parser().setSigningKey(JwtConstant.SIGN_KEY).parseClaimsJws(request.getToken()).getBody();
                String id = claims.getId();
                addCartModelRequest.setToken(id);
            }
        }else {
            //非登录状态传参
            final Cookie[] cookies = servletRequest.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (CookieConstant.CART_TOKEN.equals(cookie.getName())) {
                        addCartModelRequest.setToken(cookie.getValue());
                        break;
                    }
                }
            }
        }
        return cartOperateService.cartUpdate(addCartModelRequest);
    }

    /**
     * 查看购物车
     * @param request AddCartRequest
     * @param servletRequest HttpServletRequest
     * @return ResultModel<List<CartDetailResponse>>
     */
    @GetMapping("/detail")
    @ResponseBody
    public ResultModel<List<CartDetailResponse>> cartDetail(@RequestBody AddCartRequest request,
                                                            HttpServletRequest servletRequest) {
        try {
            if (Objects.isNull(request)) {
                throw new CartException("Req parameter is null or productID is null or parameter transformation is wrong!");
            }
        } catch (CartException e) {
            return ResultModel.error(e.getMessage());
        }

        AddCartModelRequest addCartModelRequest = new AddCartModelRequest();
        BeanUtils.copyProperties(request, addCartModelRequest);
        if (StringUtils.isBlank(request.getToken())){
            //未登录查看购物车
            final Cookie[] cookies = servletRequest.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (CookieConstant.CART_TOKEN.equals(cookie.getName())) {
                        addCartModelRequest.setToken(cookie.getValue());
                        break;
                    }
                }
            }
            //TODO 上游进行token识别,下游代码过于繁琐 可统一传token在下游进行checkLogin方法对redis查询区分token为UUID还是UserId
            //未登录状态下token为Cookie
            return cartOperateService.getCartDetail(addCartModelRequest);
        }else{
            //已登录request中携带有token（JWT）
            return cartOperateService.getLoginCartDetail(addCartModelRequest);
        }
    }

    /**
     * 添加商品到购物车
     * @param request         登录状态request中有token
     * @param servletRequest  登录状态无cookie
     * @param servletResponse 非登录状态携带cookie信息返回
     * @return ResultModel<String> 返回token
     */
    @PostMapping("/add")
    @ResponseBody
    public ResultModel<String> addCart(@RequestBody AddCartRequest request,
                                       HttpServletRequest servletRequest,
                                       HttpServletResponse servletResponse) {
        try {
            if (Objects.isNull(request) || Objects.isNull(request.getProductId())) {
                throw new CartException("Req parameter is null or productID is null or parameter transformation is wrong!");
            }
        } catch (CartException e) {
            return ResultModel.error(e.getMessage());
        }
        ResultModel<String> resultModel = null;
        AddCartModelRequest addCartModelRequest = new AddCartModelRequest();
        BeanUtils.copyProperties(request, addCartModelRequest);
        //若未登录 获取cookie中未绑定用户的购物车token

        if (!StringUtils.isBlank(addCartModelRequest.getToken())) {
            ResultModel<LoginResponse> loginResult = loginService.checkLogin(addCartModelRequest.getToken());
            if (Objects.isNull(loginResult.getData()) || loginResult.getData().getFrontState() == 1) {
                //已登录,不返回token
                resultModel = cartOperateService.addCartWithLogin(addCartModelRequest);
            }
        } else {
            //未登录
            final Cookie[] cookies = servletRequest.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (CookieConstant.CART_TOKEN.equals(cookie.getName())) {
                        addCartModelRequest.setToken(cookie.getValue());
                        break;
                    }
                }
            }
            resultModel = cartOperateService.addCartWithoutLogin(addCartModelRequest);
            //同时返回前端cookie
            Cookie cookie = new Cookie(CookieConstant.CART_TOKEN, resultModel.getData());
            cookie.setPath("/");
            cookie.setMaxAge(7 * 24 * 60 * 60);
            servletResponse.addCookie(cookie);
        }
        return resultModel;
    }
}

