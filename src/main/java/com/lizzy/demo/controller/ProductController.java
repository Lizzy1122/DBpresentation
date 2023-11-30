package com.lizzy.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lizzy.demo.common.Result;
import com.lizzy.demo.entity.ProductEntity;
import com.lizzy.demo.entity.UserEntity;
import com.lizzy.demo.req.UserReq;
import com.lizzy.demo.resp.CommonResp;
import com.lizzy.demo.resp.PageResp;
import com.lizzy.demo.service.ProductService1;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {


    @Resource
    private ProductService1 productService1;


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
        try{
        productService1.updateById(req);}
        catch (Exception e) {
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


}