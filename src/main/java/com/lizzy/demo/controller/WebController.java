package com.lizzy.demo.controller;

import com.lizzy.demo.common.Result;
import com.lizzy.demo.resp.PageResp;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//功能：提供接口返回数据
@RestController
@RequestMapping("/web")
public class WebController {

    @GetMapping("/gettest")//表示这个方法是请求数据的，默认访问地址是 "/"
    public Result gettest(){
        return Result.success("hellolzz");
    }
    @PostMapping("/posttest")
    public Result posttest(@RequestBody Obj obj){//@RequestBody把前端的json变成java对象
        return  Result.success(obj);
    }
    @PutMapping("/puttest")
    public Result puttest(@RequestBody Obj obj){
        return  Result.success(obj);
    }
    @DeleteMapping("deletetest/{id}")
    public Result deletetest(@PathVariable Integer id){
        return  Result.success(id);
    }
    @DeleteMapping("deletetest2")
    public Result deletetest2(@RequestBody List<Integer> ids){
        return  Result.success(ids);
    }
}
