package com.myspringweb;

import com.myspringweb.mapper.EmpMapper;
import com.myspringweb.pojo.Emp;
import com.myspringweb.utils.AliyunOSSProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SpringMyWebApplicationTests {
	@Test
	void contextLoads() {
	}

}
