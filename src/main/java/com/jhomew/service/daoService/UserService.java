package com.jhomew.service.daoService;

import com.jhomew.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jhomew.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Hxin
 * @since 2021-10-10
 */
public interface UserService extends IService<User> {

    boolean changeStateById(String id);
    
    User searchByName(String username);

}
