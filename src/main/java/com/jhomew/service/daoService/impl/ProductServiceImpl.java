package com.jhomew.service.daoService.impl;

import com.jhomew.entity.Product;
import com.jhomew.mapper.ProductMapper;
import com.jhomew.service.daoService.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
