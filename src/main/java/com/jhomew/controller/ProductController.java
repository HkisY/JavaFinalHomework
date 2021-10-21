package com.jhomew.controller;


import com.jhomew.entity.Product;
import com.jhomew.service.daoService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Hxin
 * @since 2021-10-10
 */
@Controller
@RequestMapping("//product")
public class ProductController {
    @Autowired
    private ProductService service;

    @RequestMapping("/listByDate")
    @ResponseBody
    public List<Product> ListByDate() {
        return service.ListByDate();
    }

    @RequestMapping("/good")
    @ResponseBody
    public Product findProductById(@RequestParam(name = "id") String id) {
        return service.getById(id);
    }

}

