package com.jhomew.mapper;

import com.jhomew.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Hxin
 * @since 2021-10-10
 */
public interface UserMapper extends BaseMapper<User> {
    @Update("UPDATE `user` SET state= if(state=1,0,1) WHERE id=#{id}")
    boolean changeStateById(String id);
    
    @Select("SELECT * from user WHERE username =  #{username}")
    User searchByName(String username);

}
