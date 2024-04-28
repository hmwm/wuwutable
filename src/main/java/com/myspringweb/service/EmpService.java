package com.myspringweb.service;

import com.myspringweb.pojo.Emp;
import com.myspringweb.pojo.PageBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EmpService {
    List<Emp> list();

    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void delete(Integer[] ids);

    void save(Emp emp);

    Emp getById(Integer id);

    void update(Emp emp);
}
