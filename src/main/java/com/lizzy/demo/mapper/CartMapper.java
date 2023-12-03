package com.lizzy.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizzy.demo.entity.CartEntity;
import com.lizzy.demo.entity.ProductEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CartMapper extends BaseMapper<CartEntity> {

    @Insert("insert into cart (UserID) values (#{UserID})")
    void AddUserIDtoCart1(@Param("UserID") Integer UserID);

    @Insert("insert into cart (ProductID, AddToCartQuantity, UserID) values (#{ProductID},#{AddToCartQuantity},#{UserID})")
    void AddToCart(@Param("ProductID") Integer ProductID,
                   @Param("AddToCartQuantity") Integer AddToCartQuantity,
                   @Param("UserID")Integer UserID);

}
