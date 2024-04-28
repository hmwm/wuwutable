package com.myspringweb.service.impl;

import com.myspringweb.mapper.DeptMapper;
import com.myspringweb.pojo.Dept;
import com.myspringweb.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);
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
