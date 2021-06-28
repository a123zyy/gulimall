package com.zyy.gulimall.produt.exception;

import com.zyy.gulimall.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice(basePackages = "com.zyy.gulimall.produt.controller")
public class GuliMallControllerAdvice {


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleVaildExpection(MethodArgumentNotValidException e){
        log.error("数据出现异常{},异常类型:{}",e.getMessage(),e.getClass());
        BindingResult bindResult = e.getBindingResult();
        Map<String,String> errorMap =   new HashMap<>();
        bindResult.getFieldErrors().forEach((filedError)->{
            errorMap.put(filedError.getField(),filedError.getDefaultMessage());
        });
        return R.error(400,"数据校验出现问题").put("data",errorMap);
    }

//    @ExceptionHandler(value = Exception.class)
//    public R handleExpection(Exception e){
//        return R.error(500,"服务器出现异常");
//    }

  }
