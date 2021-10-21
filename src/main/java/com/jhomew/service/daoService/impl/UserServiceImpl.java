package com.jhomew.service.daoService.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhomew.entity.User;
import com.jhomew.mapper.UserMapper;
import com.jhomew.service.daoService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Hxin
 * @since 2021-10-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper mapper;

    @Override
    public boolean changeStateById(String id) {
        return mapper.changeStateById(id);
    }

    @Override
    public User searchByName(String username) {
        return mapper.searchByName(username);
    }
}
