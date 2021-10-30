package com.jhomew.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jhomew.entity.Product;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Hxin
 * @since 2021-10-10
 */
public interface ProductMapper extends BaseMapper<Product> {
    @Select("SELECT name,images,create_time from product ORDER BY create_time DESC LIMIT 9")
    List<Product> ListByDate();

}
