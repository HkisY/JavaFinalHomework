package com.jhomew.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jhomew.model.exception.LoginAndRegisterException;
import com.jhomew.model.request.LoginRequest;
import com.jhomew.model.result.ResultModel;
import com.jhomew.model.result.login.LoginModelRequest;
import com.jhomew.service.businessService.loginService.LoginService;
import com.jhomew.service.daoService.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Objects;

/**
 * <p>
 *  前端控制器
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

    @PostMapping("/login")
    public ResultModel<String> login(@RequestBody LoginRequest request){
        if (Objects.isNull(request)|| StringUtils.isBlank(request.getUsername())){
            try {
                LoginAndRegisterException exception = new LoginAndRegisterException("用户名为空");
            }catch(Exception e){
                ResultModel.error(e.getMessage());
            }
        }

        return loginService.login(null);
    }

//    @PostMapping("/register")
//    @ResponseBody
//    public Boolean registerUser(@RequestBody User user){
//        LocalDate localDate = LocalDate.now();
//        user.setCreateTime(localDate);
//        return service.save(user);
//    }
//
//    @PostMapping("/login")
//    @ResponseBody
//    public Boolean login(@RequestBody User user){
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.select("username","password")
//                .eq("username",user.getUsername());
//        User one = service.getOne(queryWrapper);
//        if (one != null){
//            if (user.getPassword().equals(one.getPassword())){
//                return true;
//            }else {
//                return false;
//            }
//        }else {
//            return false;
//        }
//    }
}

