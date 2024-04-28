package com.myspringweb;

import com.myspringweb.mapper.EmpMapper;
import com.myspringweb.pojo.Emp;
import com.myspringweb.utils.AliyunOSSProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.util.List;

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


}
