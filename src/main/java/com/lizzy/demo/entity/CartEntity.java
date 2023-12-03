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
    private Integer ProductID;
    private Integer UserID;
    private String ProductName;
    private Integer AddToCartQuantity;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Timestamp AddToCartTime;


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

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    @Override
    public String toString() {
        return "CartEntity{" +
                "CartID=" + CartID +
                ", ProductID=" + ProductID +
                ", UserID=" + UserID +
                ", ProductName='" + ProductName + '\'' +
                ", AddToCartQuantity=" + AddToCartQuantity +
                ", AddToCartTime=" + AddToCartTime +
                '}';
    }
}



