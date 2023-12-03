package com.lizzy.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizzy.demo.entity.CartEntity;
import com.lizzy.demo.entity.ProductEntity;
import com.lizzy.demo.entity.UserEntity;
import com.lizzy.demo.mapper.CartMapper;
import com.lizzy.demo.mapper.ProductMapper;
import com.lizzy.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductService1 extends ServiceImpl<ProductMapper,ProductEntity> {

    @Resource
    ProductMapper productMapper;
    @Resource
    CartMapper cartMapper;
    @Resource
    UserMapper userMapper;

    @Override
    public boolean save(ProductEntity entity) {
        if (StringUtils.isBlank(entity.getDescription())){//设置默认描述为商品名，还可以设置默认密码之类的
            entity.setDescription(entity.getProductName());
        }
        if (ObjectUtils.isEmpty(entity.getPrice())){
            entity.setPrice((float)999);
        }
        return super.save(entity);
    }

    public ProductEntity selectByProductName(String ProductName){
        QueryWrapper<ProductEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ProductName",ProductName);

        return getOne(queryWrapper);
    }

//    public ProductEntity addProductTocart1(Integer productID) {
//        ProductEntity product = new ProductEntity();
//        product = productMapper.selectById(productID);
//
//        CartEntity cart = new CartEntity();
//        cart = cartMapper.selectByUserId();
//        cartMapper.AddProductToCart(product,);
//    }

//    public ProductEntity addtocart(Integer productID,Integer userID) {
//
//        ProductEntity product = new ProductEntity();
//        product = productMapper.selectById(productID);
//        UserEntity user = new UserEntity();
//        user = userMapper.getUserID()
//        cartMapper.insertByID(product,user);
//    }
}
