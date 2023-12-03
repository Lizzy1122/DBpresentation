/**
 * 功能：
 * 作者：yixiangxi
 * 日期：2023/12/3 0:22
 **/
package com.lizzy.demo.login.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lizzy.demo.common.Result;
import com.lizzy.demo.entity.ProductEntity;
import com.lizzy.demo.login.Service.UserServices;
import com.lizzy.demo.login.entity.User;
import com.lizzy.demo.login.mapper.LoginUserMappers;
import com.lizzy.demo.service.ProductService1;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/seller")
public class SellerController {
    @Resource
    private ProductService1 productService1;

    @Resource
    private UserServices userservices2;

    @Resource
    private LoginUserMappers loginUserMappers;

    /**
     * 新增商品信息
     */
    @PostMapping("/add")
    public Result add(@RequestBody ProductEntity req) {
        try {
            productService1.save(req);
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
    public Result update(@RequestBody ProductEntity req) {
        try {
            productService1.updateById(req);
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
        productService1.removeById(id);
        return Result.success();
    }


    /**
     * 批量删除用户信息
     */
    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> ids) {  //  [7, 8]
        productService1.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询全部用户信息
     */
    @GetMapping("/selectAll")
    public Result selectAll() {
        List<ProductEntity> productEntityList = productService1.list(new QueryWrapper<ProductEntity>().orderByDesc("productID"));  // select * from user order by id desc
        return Result.success(productEntityList);
    }

    /**
     * 根据ID查询用户信息
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        ProductEntity product = productService1.getById(id);
        return Result.success(product);
    }

    /**
     * 多条件模糊查询用户信息
     * pageNum 当前的页码
     * pageSize 每页查询的个数
     */
    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String ProductName,
                               @RequestParam String Description) {

        QueryWrapper<ProductEntity> queryWrapper = new QueryWrapper<ProductEntity>().orderByDesc("productID");
        queryWrapper.lambda().orderByDesc(ProductEntity::getProductID);
        queryWrapper.like(StringUtils.isNotBlank(ProductName), "productName", ProductName);
        queryWrapper.like(StringUtils.isNotBlank(Description), "description", Description);
        // select * from user where ProductName like '%#{ProductName}%' and Description like '%#{Description}%'
        Page<ProductEntity> page = productService1.page(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.success(page);
    }

//    public Result selectProduct(@RequestBody Map<String, Object> req) {
//        Integer pageNum = Integer.valueOf(req.get("pageNum").toString());
//        Integer pageSize = Integer.valueOf(req.get("pageSize").toString());
//        String Username = String.valueOf(req.get("Username").toString());
//        Integer UserType = Integer.valueOf(req.get("UserType").toString());

    @GetMapping("/product")
    public Result selectProduct(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam (required = false) String Username,
                                @RequestParam (required = false) Integer UserType) {

        User dbuser = userservices2.selectByUsernameType(Username, UserType);
        System.out.println(dbuser.getUserID());
        Integer UserID = dbuser.getUserID();
        QueryWrapper<ProductEntity> queryWrapper = new QueryWrapper<ProductEntity>().orderByDesc("ProductID");
        queryWrapper.lambda().eq(ProductEntity::getSellerID, UserID);
//        queryWrapper.lambda().orderByDesc(ProductEntity::getProductID);
        Page<ProductEntity> page = productService1.page(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.success(page);
    }
}
