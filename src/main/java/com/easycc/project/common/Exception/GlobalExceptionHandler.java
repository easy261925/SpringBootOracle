package com.easycc.project.common.Exception;

import com.easycc.project.common.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public ResponseResult error(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return ResponseResult.error().message("请求接口异常");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public ResponseResult error(ArithmeticException e) {
        e.printStackTrace();
        return ResponseResult.error().message("执行了ArithmeticException异常处理..");
    }

    //自定义异常
    @ExceptionHandler(CustomException.class)
    @ResponseBody //为了返回数据
    public ResponseResult error(CustomException e) {
        log.error(e.getMessage());
        e.printStackTrace();

        return ResponseResult.error().code(e.getCode()).message(e.getMsg());
    }

}
