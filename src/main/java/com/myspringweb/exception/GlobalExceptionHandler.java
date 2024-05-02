package com.myspringweb.exception;

import com.myspringweb.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.rmi.server.ExportException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handleGlobalException(Exception e) {
        e.printStackTrace();
        return Result.error("出错了，请联系管理员");
    }
}
