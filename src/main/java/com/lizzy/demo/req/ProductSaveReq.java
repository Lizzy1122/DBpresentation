package com.lizzy.demo.req;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

//import lombok.Data;
//使用lombok插件就不用写get、set、toString了
//@Data
@Data
@TableName("product")
public class ProductSaveReq {

    private Integer ProductID;
    private String ProductName;
    private String Description;
    private String Price;
    private Integer StockQuantity;
    private String Properties;
    private String ProductReview;
    private String ProductImages;
    private Integer SellerID;

}
