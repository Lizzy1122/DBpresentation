/**
 * 功能：
 * 作者：yixiangxi
 * 日期：2023/11/29 21:10
 **/
package com.lizzy.demo.login.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {
    @JsonProperty(value =  "UserID")
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
