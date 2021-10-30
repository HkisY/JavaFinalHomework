package com.jhomew.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jhomew.entity.User;
import com.jhomew.model.exception.LoginAndRegisterException;
import com.jhomew.model.request.LoginRequest;
//import com.jhomew.model.response.LoginResponse;
import com.jhomew.model.result.ResultModel;
import com.jhomew.model.result.login.LoginModelRequest;
import com.jhomew.service.businessService.loginService.LoginService;
import com.jhomew.service.daoService.UserService;
import com.mysql.cj.xdevapi.JsonArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
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


//    @PostMapping("/register")
//    @ResponseBody
//    public Boolean registerUser(@RequestBody User user){
//        Date Date = Date.now();
//        user.setCreateTime(Date);
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

