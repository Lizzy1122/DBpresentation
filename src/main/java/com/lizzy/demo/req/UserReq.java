package com.lizzy.demo.req;

//import lombok.Data;
//使用lombok插件就不用写get、set、toString了
//@Data
public class UserReq extends PageReq {


    private String username;
    private String address;
    private String email;
    private String phone;
    private int usertype;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    @Override
    public String toString() {
        return "UserReq{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", usertype=" + usertype +
                '}';
    }
}
