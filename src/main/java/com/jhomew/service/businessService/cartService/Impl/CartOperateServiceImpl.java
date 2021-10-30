package com.jhomew.service.businessService.cartService.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jhomew.entity.Product;
import com.jhomew.model.constant.JwtConstant;
import com.jhomew.model.request.cart.CartMergeRequest;
import com.jhomew.model.response.CartDetailResponse;
import com.jhomew.model.result.ResultModel;
import com.jhomew.model.result.cart.AddCartModelRequest;
import com.jhomew.service.businessService.cartService.CartOperateService;
import com.jhomew.service.daoService.ProductService;
import com.jhomew.utils.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Lee
 * @version 1.0
 * @since 2021/10/29 5:16 下午
 */
@Service
public class CartOperateServiceImpl implements CartOperateService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ProductService productService;


    /**
     * 未登录状态下添加商品到购物车
     *
     * @param request 入参AddCartModelRequest
     * @return ResultModel
     */
    @Override
    public ResultModel<String> addCartWithoutLogin(AddCartModelRequest request) {
        final String token = request.getToken();
        //未登录状态下无购物车
        if (StringUtils.isBlank(token)) {
            //初始化的未登录状态购物车token
            final String cartToken = StringUtil.getUUID();
            final String redisKey = StringUtil.cartTokenContact(cartToken);
            //TODO 工具类添加基于hash的redis存储工具类
            //向redis中储存购物车
            //TODO put 方法 HashKey需为String类型 故前端传参的AddCartModelRequest 中数据productId 提前转化为String 比较好此处为演示作用
            redisTemplate.opsForHash().put(redisKey, String.valueOf(request.getProductId()), request.getCount());
            //将token返回给前端
            return ResultModel.success(cartToken);
        }
        String redisKey = StringUtil.cartTokenContact(token);
        Map<Object, Object> carts = null;
        //
        try {
            carts = redisTemplate.opsForHash().entries(redisKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Objects.isNull(carts) || carts.size() == 0) {
            //购物车进行初始化
            redisTemplate.opsForHash().put(redisKey, String.valueOf(request.getProductId()), request.getCount());
            return ResultModel.success(token);
        }
        if (!carts.containsKey(String.valueOf(request.getProductId()))) {
            //购物车中无重复商品
            redisTemplate.opsForHash().put(redisKey, String.valueOf(request.getProductId()), request.getCount());
            return ResultModel.success(token);
        }
        int sum = (int) carts.get(String.valueOf(request.getProductId())) + request.getCount();
        redisTemplate.opsForHash().put(redisKey, String.valueOf(request.getProductId()), sum);
        return ResultModel.success(token);
    }

    /**
     * 为登录状态下查看购物车
     *
     * @param request AddCartModelRequest
     * @return ResultModel<List < CartDetailResponse>>
     */
    @Override
    public ResultModel<List<CartDetailResponse>> getCartDetail(AddCartModelRequest request) {
        String token = request.getToken();
        if (StringUtils.isBlank(token)) {
            //没有token 未登录状态下没有购物车
            return ResultModel.success("没有购物车", null);
        }
        String redisKey = StringUtil.cartTokenContact(token);
        Map<String, Integer> cart = null;
        try {
            cart = redisTemplate.opsForHash().entries(redisKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Objects.isNull(cart) && cart.size() == 0) {
            return ResultModel.success("没有购物车", null);
        }
        //有购物车
        List<CartDetailResponse> list = new ArrayList<>();
        //Mybatis plus 查询语句
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            CartDetailResponse response = new CartDetailResponse();
            QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
            queryWrapper.select()
                    .eq("id", entry.getKey());
            Product product = productService.getOne(queryWrapper);
            if (Objects.nonNull(product)) {
                BeanUtils.copyProperties(product, response);
                response.setCount(entry.getValue());
            }
            list.add(response);
        }
        return ResultModel.success("success", list);
    }

    /**
     * 在购物车里面加减数量的业务逻辑
     *
     * @param request AddCartModelRequest
     * @return ResultModel
     */
    @Override
    public ResultModel cartUpdate(AddCartModelRequest request) {
        String token = request.getToken();
        if (StringUtils.isBlank(token)) {
            //没有token 未登录状态下没有购物车
            return ResultModel.success("没有购物车", null);
        }
        String redisKey = StringUtil.cartTokenContact(token);
        Map<String, Integer> cart = null;
        try {
            cart = redisTemplate.opsForHash().entries(redisKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Objects.isNull(cart) || cart.size() == 0) {
            //有token 没有购物车
            return ResultModel.success("没有购物车", null);
        }
        redisTemplate.opsForHash().put(redisKey, String.valueOf(request.getProductId()), request.getCount());
        return ResultModel.success("更新成功", null);
    }

    /**
     * 已登录状态下添加购物车
     *
     * @param request AddCartModelRequest 已登录状态下携带JWT的token
     * @return ResultModel<String> token
     */
    @Override
    public ResultModel<String> addCartWithLogin(AddCartModelRequest request) {
        //TODO 未登录和已登录有很多重复代码  重构的时候可以抽象为一个方法
        String token = request.getToken();
        //对JWT进行解析
        Claims claims = Jwts.parser().setSigningKey(JwtConstant.SIGN_KEY).parseClaimsJws(token).getBody();
        String id = claims.getId();
        String redisKey = StringUtil.cartTokenContact(id);
        Map<String, Integer> carts = redisTemplate.opsForHash().entries(redisKey);
        if (Objects.isNull(carts) || carts.size() == 0) {
            //购物车进行初始化
            redisTemplate.opsForHash().put(redisKey, String.valueOf(request.getProductId()), request.getCount());
            return ResultModel.success();
        }
        if (!carts.containsKey(request.getProductId())) {
            //购物车中无重复商品
            redisTemplate.opsForHash().put(redisKey, String.valueOf(request.getProductId()), request.getCount());
            return ResultModel.success();
        }
        int sum = (int) carts.get(request.getProductId()) + request.getCount();
        redisTemplate.opsForHash().put(redisKey, String.valueOf(request.getProductId()), sum);
        return ResultModel.success();
    }

    /**
     * 查看登录状态下购物车
     *
     * @param request AddCartModelRequest
     * @return ResultModel<List < CartDetailResponse>>
     */
    @Override
    public ResultModel<List<CartDetailResponse>> getLoginCartDetail(AddCartModelRequest request) {
        //TODO 重构需求 未登录和登录状态下有大量重复代码
        String token = request.getToken();
        if (StringUtils.isBlank(token)) {
            //没有token 未登录状态下没有购物车
            return ResultModel.success("没有购物车", null);
        }
        //对JWT进行解析
        Claims claims = Jwts.parser().setSigningKey(JwtConstant.SIGN_KEY).parseClaimsJws(token).getBody();
        String id = claims.getId();

        String redisKey = StringUtil.cartTokenContact(id);
        Map<String, Integer> cart = null;
        //TODO 控制好上游参数传递，此处查询可不捕获异常
        try {
            cart = redisTemplate.opsForHash().entries(redisKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Objects.isNull(cart) && cart.size() == 0) {
            return ResultModel.success("没有购物车", null);
        }
        //有购物车
        List<CartDetailResponse> list = new ArrayList<>();
        //Mybatis plus 查询语句
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            CartDetailResponse response = new CartDetailResponse();
            QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
            queryWrapper.select()
                    .eq("id", entry.getKey());
            Product product = productService.getOne(queryWrapper);
            if (Objects.nonNull(product)) {
                BeanUtils.copyProperties(product, response);
                response.setCount(entry.getValue());
            }
            list.add(response);
        }
        return ResultModel.success("success", list);
    }

    /**
     * 登录动作发生后合并未登录状态购物车和登录状态下的购物车
     *
     * @param token 已登录的JWT的token
     * @return ResultModel
     */
    @Override
    public ResultModel merge(CartMergeRequest token) {
        //获取已登录状态的购物车
        //对JWT进行解析
        Claims claims = Jwts.parser().setSigningKey(JwtConstant.SIGN_KEY).parseClaimsJws(token.getLoginToken()).getBody();
        String id = claims.getId();
        String loginRedisKey = StringUtil.cartTokenContact(id);
        //严格控制上游传参，此处redis操作不进行异常抛出
        Map<String, Integer> loginCart = redisTemplate.opsForHash().entries(loginRedisKey);
        //获取未登录状态购物车
        String cartRedisKey = StringUtil.cartTokenContact(token.getCartToken());
        Map<String, Integer> unLoginCart = redisTemplate.opsForHash().entries(cartRedisKey);
        //未登录状态下无购物车
        if (Objects.isNull(unLoginCart) || unLoginCart.size() == 0) {
            return ResultModel.success();
        }
        //未登录状态下有购物车，登录状态下无购物车
        if (Objects.isNull(loginCart) || loginCart.size() == 0 ) {
            redisTemplate.opsForHash().putAll(loginRedisKey,unLoginCart);
            redisTemplate.delete(cartRedisKey);
            return ResultModel.success("合并成功",null);
        }
        //未登录状态有购物车，登录状态也有购物车，涉及到购物车里是否有相同商品的逻辑
        for (Map.Entry<String, Integer> cart : unLoginCart.entrySet()) {
            String productId = cart.getKey();
            Integer unLoginProductCount = cart.getValue();
            if (!loginCart.containsKey(productId)){
                redisTemplate.opsForHash().put(loginRedisKey,String.valueOf(productId),unLoginProductCount);
                continue;
            }
            Integer loginProductCount = loginCart.get(productId);
            int sum = loginProductCount+unLoginProductCount;
            redisTemplate.opsForHash().put(loginRedisKey,String.valueOf(productId),sum);
        }
        redisTemplate.delete(cartRedisKey);
        return ResultModel.success("合并成功",null);
    }
}
