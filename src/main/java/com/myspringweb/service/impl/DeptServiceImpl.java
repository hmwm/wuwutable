package com.myspringweb.service.impl;

import com.myspringweb.mapper.DeptMapper;
import com.myspringweb.mapper.EmpMapper;
import com.myspringweb.pojo.Dept;
import com.myspringweb.service.DeptService;
import com.myspringweb.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        try {
            deptMapper.deleteById(id);
            //添加异常已验证事务回滚
            //int a = 1/0;

            empMapper.deleteByDeptId(id);
        } finally {
            //用于测试事务传播行为
            System.out.println("数据库日志");
        }
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }
    /*更新部门信息*/
    @Override
    public void alter(Integer id, Dept dept) {
        dept.setId(id);
        dept.setUpdateTime(LocalDateTime.now());
        System.out.println(dept);
        deptMapper.alter(dept);
    }
}
