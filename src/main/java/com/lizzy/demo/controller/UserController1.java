package com.lizzy.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lizzy.demo.common.Result;
import com.lizzy.demo.entity.ProductEntity;
import com.lizzy.demo.entity.UserEntity1;
import com.lizzy.demo.service.ProductService1;
import com.lizzy.demo.service.UserService1;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user1")
public class UserController1 {

    @Resource
    private UserService1 userService1;

    @PostMapping("/add")
    public Result add(@RequestBody UserEntity1 req) {
        try {
            userService1.save(req);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return Result.error("插入数据库错误");
            } else {
                return Result.error("系统错误");
            }
        }
        return Result.success();
    }

    /**
     * 修改用户信息
     */
    @PutMapping("/update")
    public Result update(@RequestBody UserEntity1 req) {
        try {
            userService1.updateById(req);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return Result.error("更新数据库失败");
            } else {
                return Result.error("系统错误");
            }
        }

        return Result.success();
    }

    /**
     * 删除用户信息
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        userService1.removeById(id);
        return Result.success();
    }


    /**
     * 批量删除用户信息
     */
    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> ids) {  //  [7, 8]
        userService1.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询全部用户信息
     */
    @GetMapping("/selectAll")
    public Result selectAll() {
        List<UserEntity1> userEntity1 = userService1.list(new QueryWrapper<UserEntity1>().orderByDesc("userID"));  // select * from user order by id desc
        return Result.success(userEntity1);
    }

    /**
     * 根据ID查询用户信息
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        UserEntity1 user = userService1.getById(id);
        return Result.success(user);
    }

    /**
     * 多条件模糊查询用户信息
     * pageNum 当前的页码
     * pageSize 每页查询的个数
     */
    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(required = false) Integer UserID,
                               @RequestParam(required = false) String Username,
                               @RequestParam(required = false) Integer UserType,
                               @RequestParam(required = false) String Email,
                               @RequestParam(required = false) String Address,
                               @RequestParam(required = false) String Phone) {

        QueryWrapper<UserEntity1> queryWrapper = new QueryWrapper<UserEntity1>().orderByDesc("userID");
        queryWrapper.lambda().orderByDesc(UserEntity1::getUserID);
        queryWrapper.like(StringUtils.isNotBlank(Username), "username", Username);
        queryWrapper.like(StringUtils.isNotBlank(Email), "email", Email);
        queryWrapper.like(StringUtils.isNotBlank(Address), "address", Address);
        queryWrapper.like(StringUtils.isNotBlank(Phone), "phone", Phone);
        queryWrapper.eq(UserID != null, "userID", UserID);
        Page<UserEntity1> page = userService1.page(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.success(page);
    }

}
