package com.myspringweb.mapper;

import com.myspringweb.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogMapper {
    @Insert("insert into springweb.operate_log(operate_user, operate_time, class_name, method_name, method_params, return_value, cost_time)" +
            "values (#{operateUser}, #{operateTimes}, #{className}, #{methodName}, #{methodParams}, #{returnValues}, #{costTime})")
    void insert(OperateLog log);
}
