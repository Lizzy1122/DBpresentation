package com.lizzy.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizzy.demo.entity.OrderEntity;
import com.lizzy.demo.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderService extends ServiceImpl<OrderMapper, OrderEntity> {

    @Resource
    OrderMapper orderMapper;



}
