/**
 * 功能：
 * 作者：yixiangxi
 * 日期：2023/11/29 23:07
 **/
package com.lizzy.demo.login.exception;

import com.lizzy.demo.login.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(ServiceException.class)//用于捕获异常
    @ResponseBody//手动指定将返回的数据转化为json对象
    public Result serviceException(ServiceException e){
        return Result.error("500",e.getMessage());//获取自定义的异常数据
    }
}
