package com.lizzy.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
@TableName("user")
public class UserEntity1 {
        @TableId(type= IdType.AUTO)
        private  Integer UserID;
        @JsonProperty(value =  "Username")
        private  String Username;
        @JsonProperty(value =  "Password")
        private  String Password;
        @JsonProperty(value =  "Email")
        private  String Email;
        @JsonProperty(value =  "Phone")
        private  String Phone;
        @JsonProperty(value =  "UserType")
        private  Integer UserType;
        @JsonProperty(value =  "Address")
        private  String Address;
}

