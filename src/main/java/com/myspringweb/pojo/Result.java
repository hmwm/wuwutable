package com.myspringweb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code; // 响应码 1代表成功，0代表失败
    private String massage; //响应信息，描述字符串
    private Object data; //返回的数据
    //增删改，响应成功
    public static Result success() {
        return new Result(1, "success", null);
    }
    //查询，响应成功
    public static Result success(Object data) {
        return new Result(1, "success", data);
    }
    // 响应失败
    public static Result error(String massage) {
        return new Result(0, massage, null);
    }
    //后两个参数为社么这么设置，设置为静态的目的？
}
