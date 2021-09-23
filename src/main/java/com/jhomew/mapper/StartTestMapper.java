package com.jhomew.mapper;

import com.jhomew.entity.StartTest;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Hxin
 * @version 1.0
 * @since 2021/9/23 7:24 下午
 */
@Mapper
public interface StartTestMapper {

    StartTest selectStartTestByNum(Integer num);

}
