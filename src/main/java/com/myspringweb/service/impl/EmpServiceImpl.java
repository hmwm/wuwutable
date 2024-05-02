package com.myspringweb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.myspringweb.mapper.EmpMapper;
import com.myspringweb.pojo.Emp;
import com.myspringweb.pojo.PageBean;
import com.myspringweb.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    EmpMapper empMapper;
    @Override
    public List<Emp> list() {
        return empMapper.listEmp();
    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        //PageHelper设置分页参数
        PageHelper.startPage(page, pageSize);
        //执行查询
        List<Emp> empList = empMapper.page(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) empList;
        //封装分页结果和总数并返回
        return new PageBean(p.getTotal(), p.getResult());
    }

    @Override
    public void delete(Integer[] ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreatedTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.save(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }


}
