package com.jhomew.service.daoService.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhomew.entity.OrderItem;
import com.jhomew.mapper.OrderItemMapper;
import com.jhomew.service.daoService.OrderItemService;
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
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}
