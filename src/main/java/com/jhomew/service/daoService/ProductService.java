package com.jhomew.service.daoService;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jhomew.entity.Product;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Hxin
 * @since 2021-10-10
 */
public interface ProductService extends IService<Product> {
    public List<Product> ListByDate();

}
