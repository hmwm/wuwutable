package com.myspringweb.controller;

import com.myspringweb.pojo.Emp;
import com.myspringweb.pojo.Result;
import com.myspringweb.service.EmpService;
import com.myspringweb.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    EmpService empService;
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("员工信息,{}", emp);
        Emp e = empService.login(emp);

        //生成jwt令牌
        if (e != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name", e.getName());
            claims.put("username", e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        return Result.success("用户名或密码错误");
    }
}
