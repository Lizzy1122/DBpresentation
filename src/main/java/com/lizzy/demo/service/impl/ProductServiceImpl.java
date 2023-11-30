//package com.lizzy.demo.service.impl;
////Impl是实现类
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.lizzy.demo.entity.ProductEntity;
//import com.lizzy.demo.mapper.ProductMapper;
//import com.lizzy.demo.req.ProductReq;
//import com.lizzy.demo.req.ProductSaveReq;
//import com.lizzy.demo.resp.PageResp;
//import com.lizzy.demo.service.ProductService;
//import com.lizzy.demo.utils.CopyUtil;
//import com.lizzy.demo.utils.SnowFlake2;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.ObjectUtils;
//
//import javax.annotation.Resource;
//
//@Service
//public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductEntity> implements ProductService {
//
//    @Resource
//    private ProductMapper productMapper;
//
//
//    public ProductEntity selectByProductName(String ProductName){
//        QueryWrapper<ProductEntity> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("ProductName",ProductName);
//        return getOne(queryWrapper);
//    }
//
//}
