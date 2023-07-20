package com.huilian.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Student {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;
    private String age;
    private String no;
    private String fujian;


    @TableField(exist = false)
    private MultipartFile file;

    private String uname;

    private String pwd;
}
