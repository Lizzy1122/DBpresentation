package com.lizzy.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizzy.demo.entity.ProductEntity;
import com.lizzy.demo.entity.UserEntity1;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper1 extends BaseMapper<UserEntity1> {
}
