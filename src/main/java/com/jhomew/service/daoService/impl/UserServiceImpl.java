package com.jhomew.service.daoService.impl;

import com.jhomew.entity.User;
import com.jhomew.mapper.UserMapper;
import com.jhomew.service.daoService.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hxin
 * @since 2021-10-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
