package com.lizzy.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizzy.demo.entity.CartEntity;
import com.lizzy.demo.entity.ProductEntity;
import com.lizzy.demo.mapper.CartMapper;
import com.lizzy.demo.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CartService extends ServiceImpl<CartMapper, CartEntity> {


    @Resource
    CartMapper cartMapper;

//    @Override
//    public boolean save(CartEntity entity) {
//        if (StringUtils.isBlank(entity.getDescription())) {//设置默认描述为商品名，还可以设置默认密码之类的
//            entity.setDescription(entity.getProductName());
//        }
//        if (StringUtils.isBlank(entity.getPrice())) {
//            entity.setPrice("1");
//        }1
//        return super.save(entity);
//    }

//    public ProductEntity selectByProductName(String ProductName) {
//        QueryWrapper<ProductEntity> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("ProductName", ProductName);
//        return getOne(queryWrapper);
//    }

}
