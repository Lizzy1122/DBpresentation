package com.lizzy.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lizzy.demo.common.Result;

import com.lizzy.demo.entity.OrderEntity;
import com.lizzy.demo.service.CartService;
import com.lizzy.demo.service.OrderService;
import com.lizzy.demo.service.ProductService1;
import com.lizzy.demo.service.UserService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/purchase")
public class OrderController {

    @Resource
    private OrderService orderService;
//    @Resource
//    private ProductService1 productService1;
//    @Resource
//    private UserService userService;


    @PostMapping("/add")
    public Result add(@RequestBody OrderEntity req) {
        try {
            orderService.save(req);
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
    public Result update(@RequestBody OrderEntity req) {
        try{
            orderService.updateById(req);}
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
        orderService.removeById(id);
        return Result.success();
    }
    @GetMapping("/selectAll")
    public Result selectAll() {
        List<OrderEntity> orderEntityList = orderService.list(new QueryWrapper<OrderEntity>().orderByDesc("orderID"));  // select * from user order by id desc
        return Result.success(orderEntityList);
    }

    /**
     * 批量删除用户信息
     */
    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> ids) {  //  [7, 8]
        orderService.removeBatchByIds(ids);
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
                               @RequestParam (required = false)Integer OrderID,
                               @RequestParam (required = false)Integer PurchaserID,
                               @RequestParam (required = false)Integer ProductID,
                               @RequestParam (required = false)String ShippingAddress,
                               @RequestParam (required = false)Float TotalAmount) {

        QueryWrapper<OrderEntity> queryWrapperOrder = new QueryWrapper<OrderEntity>().orderByDesc("orderID");
        queryWrapperOrder.lambda().orderByDesc(OrderEntity::getOrderID);
        queryWrapperOrder.like(StringUtils.isNotBlank(ShippingAddress), "shippingAddress", ShippingAddress);
        if (!ObjectUtils.isNull(OrderID)) {
            queryWrapperOrder.eq("orderID", OrderID);
        }
        if (!ObjectUtils.isNull(PurchaserID)) {
            queryWrapperOrder.eq("purchaserID", PurchaserID);
        }
        if (!ObjectUtils.isNull(ProductID)) {
            queryWrapperOrder.eq("productID", ProductID);
        }
        if (!ObjectUtils.isNull(TotalAmount)) {
            // 使用 >= 和 <= 进行时间范围匹配
            queryWrapperOrder.ge("totalAmount",TotalAmount - TotalAmount/10);
            queryWrapperOrder.le("totalAmount", TotalAmount + TotalAmount/10);
        }

        Page<OrderEntity> page = orderService.page(new Page<>(pageNum, pageSize), queryWrapperOrder);
        return Result.success(page);
    }

}
