package com.huilian.entity;

import lombok.Data;

import java.util.Date;

@Data
public class StuPinVo {


    /**
     * 教师名字
     */
    private String name;


    /**
     * 分数
     */
    private Double source;


    private Date date;

}
