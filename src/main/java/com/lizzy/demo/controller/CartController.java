package com.lizzy.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lizzy.demo.common.Result;
import com.lizzy.demo.entity.CartEntity;
import com.lizzy.demo.entity.ProductEntity;
import com.lizzy.demo.entity.UserEntity;
import com.lizzy.demo.service.CartService;
import com.lizzy.demo.service.ProductService1;
import com.lizzy.demo.service.UserService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    private CartService cartService;
    @Resource
    private ProductService1 productService1;
    @Resource
    private UserService userService;

    /**
     * 新增购物车信息
     */
    @PostMapping("/add")
    public Result add(@RequestBody CartEntity req) {
        try {
            cartService.save(req);
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
    public Result update(@RequestBody CartEntity req) {
        try{
            cartService.updateById(req);}
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
        cartService.removeById(id);
        return Result.success();
    }
    @GetMapping("/selectAll")
    public Result selectAll() {
        List<CartEntity> cartEntityList = cartService.list(new QueryWrapper<CartEntity>().orderByDesc("cartID"));  // select * from user order by id desc
        return Result.success(cartEntityList);
    }

    /**
     * 批量删除用户信息
     */
    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> ids) {  //  [7, 8]
        cartService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 多条件模糊查询用户信息
     * pageNum 当前的页码
     * pageSize 每页查询的个数
     */
    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam (required = false)Integer UserID,
                               @RequestParam (required = false)Integer ProductID,
                               @RequestParam (required = false)Integer AddToCartQuantity,
                               @RequestParam (required = false)Timestamp AddToCartTime) {

        QueryWrapper<CartEntity> queryWrapperCart = new QueryWrapper<CartEntity>().orderByDesc("cartID");
        queryWrapperCart.lambda().orderByDesc(CartEntity::getCartID);
        if (!ObjectUtils.isNull(AddToCartQuantity)) {
            queryWrapperCart.like("addToCartQuantity", AddToCartQuantity.toString());
        }
        if (!ObjectUtils.isNull(AddToCartTime)) {
            // 使用 >= 和 <= 进行时间范围匹配
//            queryWrapperCart.ge("addToCartTime", AddToCartTime);
            queryWrapperCart.ge("addToCartTime", new Timestamp(AddToCartTime.getTime() - 24 * 60 * 60 * 1000));
            queryWrapperCart.le("addToCartTime", new Timestamp(AddToCartTime.getTime() + 24 * 60 * 60 * 1000)); // 假设匹配一天的数据
        }
        if (!ObjectUtils.isNull(UserID)) {
            queryWrapperCart.eq("userID", UserID);
        }
        if (!ObjectUtils.isNull(ProductID)) {
            queryWrapperCart.eq("productID", ProductID);
        }
        Page<CartEntity> page = cartService.page(new Page<>(pageNum, pageSize), queryWrapperCart);
        return Result.success(page);
    }

}
