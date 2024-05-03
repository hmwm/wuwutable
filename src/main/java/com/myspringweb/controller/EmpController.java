package com.myspringweb.controller;

import com.myspringweb.annotations.Log;
import com.myspringweb.pojo.Emp;
import com.myspringweb.pojo.PageBean;
import com.myspringweb.pojo.Result;
import com.myspringweb.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Slf4j
@RestController
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    EmpService empService;
    /*@GetMapping("/emp")
    public Result empList() {
        log.info("查询员工全部信息");
        List<Emp> empList = empService.list();
        return Result.success(empList);
    }*/

    @GetMapping()
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name,
                       Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询，参数：{}, {}, {}, {}, {}, {}",page, pageSize, name, gender, begin, end);
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }
    @Log
    @DeleteMapping("/{ids}")
    public Result deleteEmp(@PathVariable Integer[] ids) {
        log.info("删除部分员工信息");
        empService.delete(ids);
        return Result.success();
    }
    //新增员工
    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("插入员工Emp:{}",emp);
        empService.save(emp);
        return Result.success();
    }
    //查询回显员工数据
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询员工id为{}的信息", id);
        Emp empInfo = empService.getById(id);
        return Result.success(empInfo);
    }
    //在回显下，修改员工数据
    @Log
    @PutMapping
    public Result updateEmp(@RequestBody Emp emp) {
        log.info("更新员工信息:{}",emp);
        empService.update(emp);
        return Result.success();
    }

}
