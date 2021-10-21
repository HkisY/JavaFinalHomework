package com.jhomew.service.daoService.impl;

import com.jhomew.entity.Cart;
import com.jhomew.mapper.CartMapper;
import com.jhomew.service.daoService.CartService;
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
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

}
