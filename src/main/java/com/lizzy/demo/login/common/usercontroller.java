/**
 * 功能：
 * 作者：yixiangxi
 * 日期：2023/11/29 21:07
 **/
package com.lizzy.demo.login.common;

import cn.hutool.core.util.StrUtil;
import com.lizzy.demo.login.Service.UserServices;
import com.lizzy.demo.login.entity.Result;
import com.lizzy.demo.login.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.rmi.ServerException;

@RestController
public class usercontroller {
    @Resource
    UserServices userService;
    @GetMapping("/add")
    public Result hello() {
        return Result.success("success");
    }
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        System.out.println("control"+user.getUsername());
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())) {
            return Result.error("数据输入不合法");
        }
        try {
            user = userService.login(user);
            return Result.success(user);
        } catch (ServerException e) {
            return Result.error("用户不存在或者密码错误");
        }
    }
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())) {
            return Result.error("数据输入不合法");
        }

        user = userService.register(user);
        return Result.success(user);
    }

}
