package com.lizzy.demo.req;

//import lombok.Data;
//使用lombok插件就不用写get、set、toString了
//@Data
public class UserReq extends PageReq {

    private String username;

    private String gender;
    private String addr;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "User{" +
                ", username=" + username +
                ", gender=" + gender +
                ", addr=" + addr +
                '}';
    }
}
