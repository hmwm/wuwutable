package com.myspringweb.mapper;

import com.myspringweb.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from springweb.dept")
    List<Dept> list();
    @Delete("delete from springweb.dept where id = #{id}")
    void deleteById(Integer id);
    @Insert("insert into springweb.dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);
    @Update("update springweb.dept set create_time = #{createTime}, name = #{name} ,update_time = #{updateTime} where id = #{id}")
    void alter(Dept dept);
}
