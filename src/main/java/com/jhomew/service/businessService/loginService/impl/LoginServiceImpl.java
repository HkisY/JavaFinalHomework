package com.jhomew.service.businessService.loginService.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jhomew.DTO.LoginUserDTO;
import com.jhomew.entity.User;
import com.jhomew.model.request.LoginRequest;
import com.jhomew.model.result.ResultModel;
import com.jhomew.model.result.login.LoginModelRequest;
import com.jhomew.service.businessService.loginService.LoginService;
import com.jhomew.service.daoService.UserService;
import com.jhomew.utils.RedisUtil;
import com.jhomew.utils.StringUtil;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

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
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public ResultModel<String> login(LoginRequest request) {
        //若后台接收前端参数需要多余填充数据，则进行实际赋值,如下注释行
        LoginModelRequest loginModelRequest = new LoginModelRequest();
        BeanUtils.copyProperties(request,loginModelRequest);
        //loginModelRequest.setImg("asdsadsda");

        //QueryWrapper 自定义查询条件应该放到daoService中 此处为了演示暂不做处理
        //Register 功能实现下各操作为完整流程
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username","password")
                .eq("username",loginModelRequest.getUsername());
        //此实体按标准应该封装为DTO，Emmmmm想了想 太繁琐了。我们直接在这里进行业务处理吧
        //以下对DTO操作 仅是演示其作用
        User one = userService.getOne(queryWrapper);
        if (Objects.isNull(one)){
            return ResultModel.error("无用户名");
        }
        LoginUserDTO loginUserDTO = new LoginUserDTO();
        BeanUtils.copyProperties(one,loginUserDTO);
        //loginUserDTO.setImg("此处为在后台需要多余填充的数据");
        //对查询出的数据 根据实际需要对 返回前端的model 进行赋值
        if (loginUserDTO.getPassword().equals(loginModelRequest.getPassword())){
            //生成token
            JwtBuilder jwtBuilder = Jwts.builder().setId(String.valueOf(loginUserDTO.getId()))
                    .setSubject(loginUserDTO.getUsername())
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256,"Hxin");
            String token = jwtBuilder.compact();

            String redisKey = StringUtil.tokenContact(token);

            RedisUtil.save(redisTemplate,redisKey,loginUserDTO);
            //登录成功，向前端返回token
            return ResultModel.success("登陆成功",token);
        }
        return ResultModel.error("用户名错误");
    }
}
