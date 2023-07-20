package com.huilian.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("admin")
public class User {

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;//id
	private String name;//名字
	private String password;//密码
	/**
	 *  <option value="2">学生</option>
	 *                             <option value="3">老师</option>
	 *                             <option value="4">管理员</option>
	 */
	@TableField(exist = false)
	private Integer roleid;

	private String no;

	@TableField(exist = false)
	private String bianhao;

	@TableField(exist = false)
	private String age;
}
