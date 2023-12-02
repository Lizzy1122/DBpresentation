package com.lizzy.demo.login.mapper;

import com.lizzy.demo.login.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper//注入容器
public interface LoginUserMappers {
    @Insert("insert into user(Username,Password,UserType,UserID) values(#{Username},#{Password},#{UserType},#{UserID})")
    void insert(@Param("Username")String Username,@Param("UserType")int UserType
            ,@Param("Password")String Password,@Param("UserID")long UserID);
    @Select("select * from user where Username = #{Username}  and UserType = #{UserType}")
    User selectByUsername(@Param("Username")String Username,@Param("UserType")int UserType);
    @Select("select * from user where UserID = #{UserID}")
    User selecetByUserID(int UserID);
}
