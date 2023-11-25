package com.lizzy.demo.service.impl;
//Impl是实现类

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizzy.demo.entity.UserEntity;
import com.lizzy.demo.mapper.UserMapper;
import com.lizzy.demo.req.UserReq;
import com.lizzy.demo.resp.PageResp;
import com.lizzy.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public PageResp<UserEntity> getList(UserReq userReq) {

        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(userReq.getUsername())){
            queryWrapper.lambda().eq(UserEntity::getUsername,userReq.getUsername());
        }
        if(!ObjectUtils.isEmpty(userReq.getAddr())){
            queryWrapper.lambda().eq(UserEntity::getAddr,userReq.getAddr());
        }
        if(!ObjectUtils.isEmpty(userReq.getGender())){
            queryWrapper.lambda().eq(UserEntity::getGender,userReq.getGender());
        }

        Page<UserEntity> page = new Page<>(userReq.getPage(), userReq.getSize());
        Page<UserEntity> userEntityPage = userMapper.selectPage(page, queryWrapper);
        PageResp<UserEntity> pageResp = new PageResp<>();
        pageResp.setTotal(userEntityPage.getTotal());
        pageResp.setList(userEntityPage.getRecords());
        return pageResp;
    }

    @Override
    public void Delete(Long id) {
        userMapper.deleteById(id);
    }
}
