package com.myspringweb.aop;

import com.google.gson.Gson;
import com.myspringweb.mapper.OperateLogMapper;
import com.myspringweb.pojo.OperateLog;
import com.myspringweb.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {
    @Autowired
    private Gson gson;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.myspringweb.annotations.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 操作人ID
        String authorizationHeader = request.getHeader("Authorization");
        log.info("Authorization Header: {}", authorizationHeader);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            log.error("JWT token is missing or does not start with 'Bearer '");
            throw new IllegalArgumentException("JWT token is missing or does not start with 'Bearer '");
        }

        String jwt = authorizationHeader.substring(7); // 去掉 "Bearer " 前缀
        log.info("JWT Token: {}", jwt);

        Claims claims = JwtUtils.parseJwt(jwt); // Claims是map集合
        Integer operateUser = (Integer) claims.get("id"); // 根据key取值
        log.info("Operate User ID: {}", operateUser);

        // 操作时间
        LocalDateTime operateTimes = LocalDateTime.now();

        // 操作类名
        String className = joinPoint.getTarget().getClass().getName();

        // 操作方法名
        String methodName = joinPoint.getSignature().getName();

        // 操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        // 调用原始目标方法运行
        Long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        Long end = System.currentTimeMillis();

        // 方法返回值, 对象转字符串
        String returnValue = gson.toJson(result);

        // 操作耗时
        Long costTime = end - begin;

        // 记录操作日志
        OperateLog operateLog = new OperateLog(null, operateUser, operateTimes, className,
                methodName, methodParams, returnValue, costTime);
        operateLogMapper.insert(operateLog);
        log.info("使用AOP技术记录操作日志: {}", operateLog);

        return result;
    }
}
