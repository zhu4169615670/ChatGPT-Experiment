package com.huilian.entity;

import lombok.Data;

import java.util.Date;

@Data
public class DaFen {

    private Integer id;


    /**
     * 学生姓名
     */
    private String name;


    /**
     * 论文附件
     */
    private String fujian;


    /**
     * 0.未打分  1.已打分
     */
    private Integer flag = 0;


    /**
     * 分数
     */
    private Double soucre;


    /**
     * 打分时间
     */
    private Date ctime;
}
