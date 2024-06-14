package com.myspringweb.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.myspringweb.pojo.Result;
import com.myspringweb.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override // 目标资源方法运行前运行，返回true放行；返回false不放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        log.info("请求URL: {}", url);

        String authorizationHeader = request.getHeader("Authorization");
        log.info("Authorization Header: {}", authorizationHeader);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            log.info("令牌不存在或格式不正确");
            Result error = Result.error("NO_LOGIN");
            String noLogin = JSONObject.toJSONString(error);
            response.getWriter().write(noLogin);
            //不放行
            return false;
        }

        String jwt = authorizationHeader.substring(7); // 去掉 "Bearer " 前缀
        log.info("JWT Token: {}", jwt);

        if (jwt == null || jwt.trim().isEmpty()) {
            log.info("JWT字符串为空");
            Result error = Result.error("NO_LOGIN");
            String noLogin = JSONObject.toJSONString(error);
            response.getWriter().write(noLogin);
            //不放行
            return false;
        }

        try {
            JwtUtils.parseJwt(jwt);
        } catch (Exception e) {
            log.info("jwt解析错误，返回未登录信息: {}", e.getMessage());
            Result error = Result.error("NO_LOGIN");
            String noLogin = JSONObject.toJSONString(error);
            response.getWriter().write(noLogin);
            //不放行
            return false;
        }

        log.info("jwt解析成功！");
        //放行
        return true;
    }

    @Override //目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override //视图渲染完毕后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

//@Component
//public class LoginCheckInterceptor implements HandlerInterceptor {
//    @Override // 目标资源方法运行前运行，返回true放行；返回false不放行
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String url = request.getRequestURI();
//        String jwt = request.getHeader("token");
//        if (!StringUtils.hasLength(jwt)) {
//            log.info("令牌不存在");
//            Result error = Result.error("NO_LOGIN");
//            String noLogin = JSONObject.toJSONString(error);
//            response.getWriter().write(noLogin);
//            //不放行
//            return false;
//        }
//
//        try {
//            JwtUtils.parseJwt(jwt);
//        } catch (Exception e) {
//            log.info("jwt解析错误，返回未登录信息");
//            Result error = Result.error("NO_LOGIN");
//            String noLogin = JSONObject.toJSONString(error);
//            response.getWriter().write(noLogin);
//            //不放行
//            return false;
//        }
//
//        log.info("jwt解析成功！");
//        //放行
//        return true;
//    }
//
//    @Override //目标资源方法运行后运行
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override //视图渲染完毕后运行
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
//}
