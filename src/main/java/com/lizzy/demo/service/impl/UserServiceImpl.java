package com.lizzy.demo.service.impl;
//Impl是实现类

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizzy.demo.entity.UserEntity;
import com.lizzy.demo.mapper.UserMapper;
import com.lizzy.demo.req.UserReq;
import com.lizzy.demo.req.UserSaveReq;
import com.lizzy.demo.resp.PageResp;
import com.lizzy.demo.service.UserService;
import com.lizzy.demo.utils.CopyUtil;

//import com.lizzy.demo.utils.SnowFlake;
import com.lizzy.demo.utils.SnowFlake2;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Resource
    private UserMapper userMapper;

//    @Resource
//    private SnowFlake snowFlake;

    @Resource
    private SnowFlake2 snowFlake2;

    @Override
    public PageResp<UserEntity> getList(UserReq userReq) {

        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        //搜索全部的问题，要把用户类型改成Integar
        if (ObjectUtils.isEmpty(userReq.getUsertype())) {
            if (!ObjectUtils.isEmpty(userReq.getUsername())) {
                queryWrapper.lambda().eq(UserEntity::getUsername, userReq.getUsername());
            }
            if (!ObjectUtils.isEmpty(userReq.getAddress())) {
                queryWrapper.lambda().eq(UserEntity::getAddress, userReq.getAddress());
            }
            if (!ObjectUtils.isEmpty(userReq.getEmail())) {
                queryWrapper.lambda().eq(UserEntity::getEmail, userReq.getEmail());
            }
            if (!ObjectUtils.isEmpty(userReq.getPhone())) {
                queryWrapper.lambda().eq(UserEntity::getPhone, userReq.getPhone());
            }
        } else {
            queryWrapper.lambda().eq(UserEntity::getUsertype, userReq.getUsertype());
            if (!ObjectUtils.isEmpty(userReq.getUsername())) {
                queryWrapper.lambda().eq(UserEntity::getUsername, userReq.getUsername());
            }
            if (!ObjectUtils.isEmpty(userReq.getAddress())) {
                queryWrapper.lambda().eq(UserEntity::getAddress, userReq.getAddress());
            }
            if (!ObjectUtils.isEmpty(userReq.getEmail())) {
                queryWrapper.lambda().eq(UserEntity::getEmail, userReq.getEmail());
            }
            if (!ObjectUtils.isEmpty(userReq.getPhone())) {
                queryWrapper.lambda().eq(UserEntity::getPhone, userReq.getPhone());
            }
        }


        Page<UserEntity> page = new Page<>(userReq.getPage(), userReq.getSize());
        Page<UserEntity> userEntityPage = userMapper.selectPage(page, queryWrapper);
//        IPage<UserEntity> userEntityIPage = userMapper.selectPage(page, queryWrapper);
        PageResp<UserEntity> pageResp = new PageResp<>();
        pageResp.setTotal(userEntityPage.getTotal());
        pageResp.setList(userEntityPage.getRecords());
        return pageResp;
    }


    @Override
    public void Delete(Long id) {
        userMapper.deleteById(id);
    }

//    @Override
//    public void Create(UserSaveReq req) {
//        UserEntity entity = CopyUtil.copy(req, UserEntity.class);
//        if(!ObjectUtils.isEmpty(req.getId())){
//            entity.setId(snowFlake.nextId());
//            userMapper.insert(entity);
//        }
//        else {
//            userMapper.updateById(entity);
//        }
//    }

    @Override
    public void Create(UserSaveReq req) {
        UserEntity entity = CopyUtil.copy(req, UserEntity.class);
        if (!ObjectUtils.isEmpty(req.getUserid())) {
            entity.setUserid(snowFlake2.nextId());
            userMapper.insert(entity);
        } else {
            userMapper.updateById(entity);
        }
    }
}
