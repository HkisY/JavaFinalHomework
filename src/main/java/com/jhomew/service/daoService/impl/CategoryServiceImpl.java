package com.jhomew.service.daoService.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhomew.entity.Category;
import com.jhomew.mapper.CategoryMapper;
import com.jhomew.service.daoService.CategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
