package com.myspringweb.service;


import com.myspringweb.pojo.Dept;

import java.awt.*;
import java.util.List;

public interface DeptService {

    List<Dept> list();

    void delete(Integer id);

    void add(Dept dept);

    void alter(Integer id, Dept dept);
}
