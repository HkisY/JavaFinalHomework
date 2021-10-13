package com.jhomew.controller;


import com.jhomew.entity.User;
import com.jhomew.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hxin
 * @since 2021-10-10
 */
@Controller
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService service;

    @RequestMapping("/list")
    @ResponseBody
    public User json(){
        logger.debug("----------123-1-312312313123-----");
        System.out.println("asdadsadsadada");
        return service.getById(1);
    }
}

