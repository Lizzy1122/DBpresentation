package com.lizzy.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
@TableName("product")
public class ProductEntity {
//    @Id
    @TableId(type=IdType.AUTO)
    private Integer ProductID;
    private String ProductName;
    private String Description;
    private Float Price;
    private Integer StockQuantity;
    private String Properties;
    private String ProductReview;
    private String ProductImages;
    private Integer SellerID;

    @TableField(exist = false)//表示数据库中没有该字段
    private String token;

    public Integer getProductID() {
        return ProductID;
    }

    public void setProductID(Integer productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


    public Float getPrice() {
        return Price;
    }

    public void setPrice(Float price) {
        Price = price;
    }

    public Integer getStockQuantity() {
        return StockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        StockQuantity = stockQuantity;
    }

    public String getProperties() {
        return Properties;
    }

    public void setProperties(String properties) {
        Properties = properties;
    }

    public String getProductReview() {
        return ProductReview;
    }

    public void setProductReview(String productReview) {
        ProductReview = productReview;
    }

    public String getProductImages() {
        return ProductImages;
    }

    public void setProductImages(String productImages) {
        ProductImages = productImages;
    }

    public Integer getSellerID() {
        return SellerID;
    }

    public void setSellerID(Integer sellerID) {
        SellerID = sellerID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "ProductID=" + ProductID +
                ", ProductName='" + ProductName + '\'' +
                ", Description='" + Description + '\'' +
                ", Price=" + Price +
                ", StockQuantity=" + StockQuantity +
                ", Properties='" + Properties + '\'' +
                ", ProductReview='" + ProductReview + '\'' +
                ", ProductImages='" + ProductImages + '\'' +
                ", SellerID=" + SellerID +
                ", token='" + token + '\'' +
                '}';
    }
}
