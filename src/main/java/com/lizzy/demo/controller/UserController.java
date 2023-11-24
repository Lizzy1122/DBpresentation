package com.lizzy.demo.controller;


import com.lizzy.demo.entity.UserEntity;
import com.lizzy.demo.req.UserReq;
import com.lizzy.demo.resp.CommonResp;
import com.lizzy.demo.resp.PageResp;
import com.lizzy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    private UserService userService;

    @GetMapping("/getList")
    public CommonResp getList(UserReq userReq){

        CommonResp<PageResp<UserEntity>> resp = new CommonResp<>();
        PageResp<UserEntity> list = userService.getList(userReq);
        resp.setContent(list);
        return resp;

    }
}
