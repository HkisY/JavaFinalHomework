package com.jhomew.service.daoService;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jhomew.entity.User;

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
