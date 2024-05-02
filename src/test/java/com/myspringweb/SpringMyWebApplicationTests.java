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
	@Autowired
	private EmpMapper empMapper;

	@Test
	public void testListEmp() {
		List<Emp> empList = empMapper.listEmp();
		empList.forEach(System.out::println);
	}
	@Test
	void contextLoads() {
	}

	@Test
	public void testGenJwt() {
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", 1);
		claims.put("name", "HSong");
		String jwt = Jwts.builder()
				.signWith(SignatureAlgorithm.HS256, "HsJwt")
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
				.compact();
		System.out.println(jwt);
	}

 	@Test
	public void testParseJwt() {
		Claims claims = Jwts.parser()
				.setSigningKey("HsJwt")
				.parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiSFNvbmciLCJpZCI6MSwiZXhwIjoxNzE0NjQ4OTU3fQ.V8XgJV65UFdrVKOYYni1joOeTL4FFrcPlgj71tzLAh0")
				.getBody();
		System.out.println(claims);
	}
}
