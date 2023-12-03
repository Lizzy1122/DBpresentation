package com.lizzy.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizzy.demo.entity.ProductEntity;
import com.lizzy.demo.entity.UserEntity1;
import com.lizzy.demo.mapper.CartMapper;
import com.lizzy.demo.mapper.ProductMapper;
import com.lizzy.demo.mapper.UserMapper;
import com.lizzy.demo.mapper.UserMapper1;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService1 extends ServiceImpl<UserMapper1, UserEntity1> {

    @Resource
    UserMapper1 userMapper1;
}
