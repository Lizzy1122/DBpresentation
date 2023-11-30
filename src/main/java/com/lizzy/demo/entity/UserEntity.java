package com.lizzy.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;

//import lombok.Data;
//使用lombok插件就不用写get、set、toString了
//@Data
@Getter
@TableName("user")
public class UserEntity {
    @TableId(type = IdType.AUTO, value = "userid")
//    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userid;
    private String username;
    private String password;
    private String address;
    private String email;
    private String phone;
    private int usertype;

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", usertype=" + usertype +
                '}';
    }
}
