package com.lizzy.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
//@Entity
@TableName("purchase")
public class OrderEntity {

    @TableId(type = IdType.AUTO)
    private Integer OrderID;
    private Integer PurchaserID;
    private Integer ProductID;
    private String ShippingAddress;
    private Float TotalAmount;
    private Float UnitPrice;
    private Integer OrdersQuantity;

    public Integer getOrderID() {
        return OrderID;
    }

    public void setOrderID(Integer orderID) {
        OrderID = orderID;
    }

    public Integer getPurchaserID() {
        return PurchaserID;
    }

    public void setPurchaserID(Integer purchaserID) {
        PurchaserID = purchaserID;
    }

    public Integer getProductID() {
        return ProductID;
    }

    public void setProductID(Integer productID) {
        ProductID = productID;
    }

    public String getShippingAddress() {
        return ShippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        ShippingAddress = shippingAddress;
    }

    public Float getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        TotalAmount = totalAmount;
    }

    public Float getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        UnitPrice = unitPrice;
    }

    public Integer getOrdersQuantity() {
        return OrdersQuantity;
    }

    public void setOrdersQuantity(Integer ordersQuantity) {
        OrdersQuantity = ordersQuantity;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "OrderID=" + OrderID +
                ", PurchaserID=" + PurchaserID +
                ", ProductID=" + ProductID +
                ", ShippingAddress='" + ShippingAddress + '\'' +
                ", TotalAmount=" + TotalAmount +
                ", UnitPrice=" + UnitPrice +
                ", OrdersQuantity=" + OrdersQuantity +
                '}';
    }
}
