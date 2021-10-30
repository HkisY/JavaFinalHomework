package com.jhomew.service.daoService.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhomew.entity.Order;
import com.jhomew.mapper.OrderMapper;
import com.jhomew.service.daoService.OrderService;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
