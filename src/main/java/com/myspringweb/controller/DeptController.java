package com.myspringweb.controller;

import com.myspringweb.annotations.Log;
import com.myspringweb.pojo.Dept;
import com.myspringweb.pojo.Result;
import com.myspringweb.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*部门信息请求*/
@Slf4j
@RestController
public class DeptController {
    //查询部门信息请求
    //private static Logger log = LoggerFactory.getLogger(DeptController.class);
    //@RequestMapping(value = "/dept", method = RequestMethod.GET)
    @Autowired
    private DeptService deptService;
    @GetMapping("/dept")
    public Result deptList() {
        log.info("查询部门全部数据");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }
    @Log
    @DeleteMapping("/dept/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除一个部门");
        deptService.delete(id);
        return Result.success();
    }

    @Log
    @PostMapping("/dept")
    public Result add(@RequestBody Dept dept) {
        log.info("新增一个部门");
        deptService.add(dept);
        return Result.success();
    }
    @Log
    @PatchMapping("/dept/{id}")
    public Result alter(@PathVariable Integer id, @RequestBody Dept dept) {
        log.info("一条部门信息已修改");
       // dept.setId(id);
        deptService.alter(id, dept);
        return Result.success();
    }
}
