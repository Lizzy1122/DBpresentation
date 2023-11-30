package com.lizzy.demo.req;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;

//和ENtity的区别：只保留需要查询的属性
@Data
@TableName("product")
public class ProductReq extends PageReq {

    private String ProductName;
    private String Description;
    private String Price;
    private Integer StockQuantity;
    private String Properties;
    private String ProductReview;
    private String ProductImages;
    private Integer SellerID;


}
