package com.lizzy.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;


import javax.persistence.*;

@Data
//@Entity
@TableName("cart")
public class CartEntity {

    @TableId(type = IdType.AUTO)
    private Integer CartID;
    private Integer AddToCartQuantity;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Timestamp AddToCartTime;
    private Integer ProductID;
    private Integer UserID;

    public Integer getCartID() {
        return CartID;
    }

    public void setCartID(Integer cartID) {
        CartID = cartID;
    }

    public Integer getAddToCartQuantity() {
        return AddToCartQuantity;
    }

    public void setAddToCartQuantity(Integer addToCartQuantity) {
        AddToCartQuantity = addToCartQuantity;
    }

    public Timestamp getAddToCartTime() {
        return AddToCartTime;
    }

    public void setAddToCartTime(Timestamp addToCartTime) {
        AddToCartTime = addToCartTime;
    }

    public Integer getProductID() {
        return ProductID;
    }

    public void setProductID(Integer productID) {
        ProductID = productID;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    @Override
    public String toString() {
        return "CartEntity{" +
                "CartID=" + CartID +
                ", AddToCartQuantity=" + AddToCartQuantity +
                ", AddToCartTime=" + AddToCartTime +
                ", ProductID=" + ProductID +
                ", UserID=" + UserID +
                '}';
    }
}



