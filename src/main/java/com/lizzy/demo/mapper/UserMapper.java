package com.lizzy.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizzy.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<UserEntity>{
    UserEntity getUserID();

//    List<UserEntity> selectALL();
//    UserEntity selectByID(int id);

}
