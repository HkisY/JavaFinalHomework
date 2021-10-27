package com.jhomew.service.businessService.loginService.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jhomew.DTO.LoginUserDTO;
import com.jhomew.entity.User;
import com.jhomew.model.constant.JwtConstant;
import com.jhomew.model.exception.LoginAndRegisterException;
import com.jhomew.model.response.LoginResponse;
import com.jhomew.model.response.RegisterResponse;
import com.jhomew.model.result.ResultModel;
import com.jhomew.model.result.login.LoginModelRequest;
import com.jhomew.model.result.login.RegisterModelRequest;
import com.jhomew.service.businessService.loginService.LoginService;
import com.jhomew.service.daoService.UserService;
import com.jhomew.utils.RedisUtil;
import com.jhomew.utils.StringUtil;
import io.jsonwebtoken.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/10/16 9:26 下午
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String, Long> redisTemplate;

    /**
     * 注册服务
     *
     * @param request 入参
     * @return LoginResponse
     */
    @Override
    public ResultModel<LoginResponse> login(LoginModelRequest request) {
        //QueryWrapper 自定义查询条件应该放到daoService中 此处为了演示暂不做处理
        //Register 功能实现下各操作为完整流程
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select()
                .eq("username", request.getUsername());
        //此实体按标准应该封装为DTO，Emmmmm想了想 太繁琐了。我们直接在这里进行业务处理吧
        //以下对DTO操作 仅是演示其作用
        User one = userService.getOne(queryWrapper);
        if (Objects.isNull(one)){
            return ResultModel.error("无用户名");
        }
        LoginUserDTO loginUserDTO = new LoginUserDTO();
        BeanUtils.copyProperties(one, loginUserDTO);
        //loginUserDTO.setImg("此处为在后台需要多余填充的数据");
        //对查询出的数据 根据实际需要对 返回前端的model 进行赋值
        if (loginUserDTO.getPassword().equals(request.getPassword())) {
            //生成token
            JwtBuilder jwtBuilder = Jwts.builder().setId(String.valueOf(loginUserDTO.getId()))
                    .setSubject(loginUserDTO.getUsername())
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, JwtConstant.SIGN_KEY);
            String token = jwtBuilder.compact();
            String redisKey = StringUtil.tokenContact(String.valueOf(loginUserDTO.getId()));
            RedisUtil.save(redisTemplate, redisKey, loginUserDTO);
            //登录成功，向前端返回信息
            LoginResponse loginResponse = new LoginResponse();
            BeanUtils.copyProperties(loginUserDTO, loginResponse);
            loginResponse.setFrontState(1);
            loginResponse.setToken(token);
            return ResultModel.success("登陆成功", loginResponse);
        }
        return ResultModel.error("密码错误");
    }

    /**
     * 注册服务
     *
     * @param request 入参
     * @return RegisterResponse
     */
    @Override
    public ResultModel<RegisterResponse> register(RegisterModelRequest request) {
        //判断用户名是否重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username")
                .eq("username", request.getUsername());
//        queryWrapper.and(wrapper-> wrapper.eq("state",1));
        User one = userService.getOne(queryWrapper);
        if (!Objects.isNull(one)) {
            return ResultModel.error("用户名重复");
        }
        //此处不进行DTO赋值，简化操作
        User user = new User();
        BeanUtils.copyProperties(request, user);
        //为了安全不提供注册成功免登录功能
        try {
            userService.save(user);
            LoginAndRegisterException exception = new LoginAndRegisterException("数据存储过程出错");
        } catch (Exception e) {
            return ResultModel.error(e.getMessage());
        }
        RegisterResponse response = new RegisterResponse();
        response.setFrontState(1);
        return ResultModel.success("注册成功", response);
    }

    /**
     * 验证是否已登录
     *
     * @param token token
     * @return LoginResponse
     */
    @Override
    public ResultModel<LoginResponse> checkLogin(String token) {
        LoginUserDTO loginDto = null;
        LoginResponse loginResponse = new LoginResponse();
        try {
            //对JWT进行解析
            Claims claims = Jwts.parser().setSigningKey(JwtConstant.SIGN_KEY).parseClaimsJws(token).getBody();
            String id = claims.getId();
            //去redis中拿数据
            loginDto = (LoginUserDTO) RedisUtil.get(redisTemplate, StringUtil.tokenContact(String.valueOf(id)));
            //jwt未超时，但是redis中无数据，如修改密码、注销等操作
            if (Objects.isNull(loginDto)){
                loginResponse.setMessage("用户登录已过期");
                //表示查询失败
                loginResponse.setFrontState(0);
                //静态方法error未规定携带参数
                //静态方法success表示后端业务流程畅通
                return ResultModel.success("用户登录已超时",loginResponse);
            }
            BeanUtils.copyProperties(loginDto,loginResponse);
            loginResponse.setFrontState(1);
            loginResponse.setMessage("查询成功");
            return ResultModel.success("success",loginResponse);
        } catch (Exception e) {
            loginResponse.setFrontState(0);
            return ResultModel.success("用户登录已过期",loginResponse);
        }
    }

    @Override
    public ResultModel logOut(String token) {
        LoginUserDTO loginDto = null;
        LoginResponse loginResponse = new LoginResponse();
        try {
            //对JWT进行解析
            Claims claims = Jwts.parser().setSigningKey(JwtConstant.SIGN_KEY).parseClaimsJws(token).getBody();
            String id = claims.getId();
            //去redis中删除数据
            String redisKey = StringUtil.tokenContact(id);
            RedisUtil.delete(redisTemplate,redisKey);

            return ResultModel.success("success");
        } catch (Exception e) {
            loginResponse.setFrontState(1);
            return ResultModel.success("注销成功");
        }
    }
}
