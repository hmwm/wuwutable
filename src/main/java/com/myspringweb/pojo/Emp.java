package com.myspringweb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private short gender;
    private String image;
    private short job;
    private LocalDate entryDate;
    private Integer deptId;
    private LocalDateTime createdTime;
    private LocalDateTime updateTime;
}
