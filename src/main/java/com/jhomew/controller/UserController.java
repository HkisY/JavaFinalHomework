package com.jhomew.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jhomew.entity.User;
import com.jhomew.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.sql.Wrapper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

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

    @PostMapping("/register")
    @ResponseBody
    public Boolean registerUser(@RequestBody User user){
        LocalDate localDate = LocalDate.now();
        user.setCreateTime(localDate);
        return service.save(user);
    }

    @PostMapping("/login")
    @ResponseBody
    public Boolean login(@RequestBody User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username","password")
                .eq("username",user.getUsername());
        User one = service.getOne(queryWrapper);
        if (one != null){
            if (user.getPassword().equals(one.getPassword())){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
}

