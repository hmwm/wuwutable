package com.myspringweb.filter;


import com.alibaba.fastjson.JSONObject;
import com.myspringweb.pojo.Result;
import com.myspringweb.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURI().toString();
        log.info("获取到的url{}", url);

        if (url.contains("login")) {
            log.info("登录操作，放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String jwt = request.getHeader("token");
        //这里判断兼顾了jwt为空串和null的判断
        if (!StringUtils.hasLength(jwt)) {
            Result error = Result.error("NO_LOGIN");
            //Filter不属于Controller，也没有相应的返回转json注解，需要引入第三方实现
            //手动转换json对象...alibaba
            String noLogin = JSONObject.toJSONString(error);
            response.getWriter().write(noLogin);
            return;
        }

        try {
            JwtUtils.parseJwt(jwt);
        } catch (Exception e) {
            log.info("令牌解析失败,返回未登录错误信息");
            Result error = Result.error("NO_LOGIN");
            String noLogin = JSONObject.toJSONString(error);
            response.getWriter().write(noLogin);
            return;
        }

        //令牌解析成功放行
        log.info("令牌校验成功，放行");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
