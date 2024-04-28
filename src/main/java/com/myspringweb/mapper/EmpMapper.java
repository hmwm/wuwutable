package com.myspringweb.mapper;

import com.myspringweb.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    @Select("select * from springweb.emp")
    List<Emp> listEmp();
    //@Delete("delete from springweb.emp where id = #{id}")
    void delete(Integer[] ids);

    /*查询总记录数*/
    /*@Select("select count(*) from springweb.emp")
    Integer count();*/

    /*查询page,原始方式
    @Select("select * from springweb.emp limit #{start}, #{pageSize}")
    List<Emp> page(Integer start, Integer pageSize);*/

/*    @Select("select * from springweb.emp")
    List<Emp> page();*/

    List<Emp> page(String name, Short gender, LocalDate begin, LocalDate end);

    void save(Emp emp);

    @Select("select * from springweb.emp where id = #{id}")
    Emp getById(Integer id);

    void update(Emp emp);
}
