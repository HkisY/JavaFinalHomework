package com.jhomew.service.impl;

import com.jhomew.entity.Order;
import com.jhomew.mapper.OrderMapper;
import com.jhomew.service.OrderService;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
