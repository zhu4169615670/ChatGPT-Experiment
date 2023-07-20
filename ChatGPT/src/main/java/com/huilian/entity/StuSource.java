package com.huilian.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class StuSource {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer stuid;
    private Integer teaid;
    private Double soure;

    private Date cratetime;
}
