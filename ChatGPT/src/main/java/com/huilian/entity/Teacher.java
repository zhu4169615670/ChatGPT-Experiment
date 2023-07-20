package com.huilian.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Teacher {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;
    private String age;
    private String no;
    private String habby;

    private String uname;

    private String pwd;
}
