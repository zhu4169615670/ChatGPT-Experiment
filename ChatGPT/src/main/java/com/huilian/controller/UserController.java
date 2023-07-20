package com.huilian.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huilian.entity.Student;
import com.huilian.entity.Teacher;
import com.huilian.entity.User;
import com.huilian.mapper.StudentDao;
import com.huilian.mapper.TeacherDao;
import com.huilian.service.UserService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UserController {
	//注入userService
	@Autowired
	private UserService userService;

	@Autowired
	private TeacherDao teacherDao;
	
	@Autowired
	private StudentDao studentDao;

	@RequestMapping("toLogin")
	public String toLogin(User user, HttpServletRequest request) {
		return "login";
	}

	@RequestMapping("toManagerIndex")
	public String toManagerIndex(User user, HttpServletRequest request) {
		return "managerIndex";
	}
	@RequestMapping("toTeaIndex")
	public String toTeaIndex(User user, HttpServletRequest request) {
		return "teaIndex";
	}
	@RequestMapping("toStuIndex")
	public String toStuIndex(User user, HttpServletRequest request) {
//		Student student = (Student) request.getSession().getAttribute("USER_SESSION");
//		request.setAttribute("USER_SESSION",student);
		return "stuIndex";
	}
	@RequestMapping("toReg")
	public String toReg(User user, HttpServletRequest request) {
		return "reg";
	}
	
	//用户登录
	@RequestMapping("login")
	public String login(User user, HttpServletRequest request,HttpServletResponse response) {
		try {
			//管理员
			if(user.getRoleid()==4){
				User u=userService.login(user);
				if(u==null){

					//用户名不存在
					response.setCharacterEncoding("utf-8");

					response.setContentType("text/html;charset=utf-8;");

					PrintWriter writer = response.getWriter();
					writer.write(
							"<script type='text/javascript'>alert('用户名或密码错误！');setInterval('',1000);parent.location.href='/toLogin';</script>");
					writer.flush();
					writer.close();
				}else {
					request.getSession().setAttribute("USER_SESSION", u);
					return "redirect:/toManagerIndex";//redirect:重定向
				}
				//老师
			}else if(user.getRoleid()==3){
				List<Teacher> teachers = teacherDao.selectList(new QueryWrapper<Teacher>().eq("uname", user.getNo()).eq("pwd", user.getPassword()));
				if(CollectionUtils.isEmpty(teachers)){

					//用户名不存在
					response.setCharacterEncoding("utf-8");

					response.setContentType("text/html;charset=utf-8;");

					PrintWriter writer = response.getWriter();
					writer.write(
							"<script type='text/javascript'>alert('用户名或密码错误！');setInterval('',1000);parent.location.href='/toLogin';</script>");
					writer.flush();
					writer.close();
				}else {
					request.getSession().setAttribute("USER_SESSION", teachers.get(0));
					return "redirect:/toTeaIndex";//redirect:重定向
				}
			}else if(user.getRoleid()==2){
				//学生
				List<Student> students = studentDao.selectList(new QueryWrapper<Student>().eq("uname", user.getNo()).eq("pwd", user.getPassword()));
				if(CollectionUtils.isEmpty(students)){

					//用户名不存在
					response.setCharacterEncoding("utf-8");

					response.setContentType("text/html;charset=utf-8;");

					PrintWriter writer = response.getWriter();
					writer.write(
							"<script type='text/javascript'>alert('用户名或密码错误！');setInterval('',1000);parent.location.href='/toLogin';</script>");
					writer.flush();
					writer.close();
				}else {
					request.getSession().setAttribute("USER_SESSION", students.get(0));
					return "redirect:/toStuIndex";//redirect:重定向
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "系统错误");
			return "forward:/toLogin";
		}
		return "forward:/toLogin";
	}


	//用户注册
	@RequestMapping("reg")
	public String reg(User user, HttpServletRequest request, HttpServletResponse response) {
		try {
			//管理员
			if(user.getRoleid()==4){
				User u=userService.login(user);
				if(u!=null){

					//用户名存在
					response.setCharacterEncoding("utf-8");

					response.setContentType("text/html;charset=utf-8;");

					PrintWriter writer = response.getWriter();
					writer.write(
							"<script type='text/javascript'>alert('用户名已存在！');setInterval('',1000);parent.location.href='/toReg';</script>");
					writer.flush();
					writer.close();
				}else {
					if(!StringUtils.isEmpty(user.getNo())){
						user.setName(user.getNo().replace(",",""));
					}
					userService.save(user);
					//ZHUCE C成功
					response.setCharacterEncoding("utf-8");

					response.setContentType("text/html;charset=utf-8;");

					PrintWriter writer = response.getWriter();
					writer.write(
							"<script type='text/javascript'>alert('注册成功，请登录！');setInterval('',1000);parent.location.href='/toLogin';</script>");
					writer.flush();
					writer.close();
				}
				//老师
			}else if(user.getRoleid()==3){
				List<Teacher> teachers = teacherDao.selectList(new QueryWrapper<Teacher>().eq("uname", user.getNo()).eq("pwd", user.getPassword()));
				if(!CollectionUtils.isEmpty(teachers)){

					//用户名存在
					response.setCharacterEncoding("utf-8");

					response.setContentType("text/html;charset=utf-8;");

					PrintWriter writer = response.getWriter();
					writer.write(
							"<script type='text/javascript'>alert('用户名已存在！');setInterval('',1000);parent.location.href='/toReg';</script>");
					writer.flush();
					writer.close();
				}else {
					Teacher teacher = new Teacher();
					teacher.setUname(user.getNo());
					teacher.setPwd(user.getPassword());
					if(!StringUtils.isEmpty(user.getAge())){
						teacher.setAge(user.getAge().replace(",",""));
					}
					if(!StringUtils.isEmpty(user.getName())){
						teacher.setName(user.getName().replace(",",""));
					}
					if(!StringUtils.isEmpty(user.getBianhao())){
						teacher.setNo(user.getBianhao().replace(",",""));
					}
					teacherDao.insert(teacher);
					//ZHUCE C成功
					response.setCharacterEncoding("utf-8");

					response.setContentType("text/html;charset=utf-8;");

					PrintWriter writer = response.getWriter();
					writer.write(
							"<script type='text/javascript'>alert('注册成功，请登录！');setInterval('',1000);parent.location.href='/toLogin';</script>");
					writer.flush();
					writer.close();
				}
			}else if(user.getRoleid()==2){
				//学生
				List<Student> students = studentDao.selectList(new QueryWrapper<Student>().eq("uname", user.getNo()).eq("pwd", user.getPassword()));
				if(!CollectionUtils.isEmpty(students)){

					//用户名不存在
					response.setCharacterEncoding("utf-8");

					response.setContentType("text/html;charset=utf-8;");

					PrintWriter writer = response.getWriter();
					writer.write(
							"<script type='text/javascript'>alert('用户名已存在！');setInterval('',1000);parent.location.href='/toReg';</script>");
					writer.flush();
					writer.close();
				}else {
					Student student = new Student();
					student.setUname(user.getNo());
					student.setPwd(user.getPassword());
					if(!StringUtils.isEmpty(user.getAge())){
						student.setAge(user.getAge().replace(",",""));
					}
					if(!StringUtils.isEmpty(user.getName())){
						student.setName(user.getName().replace(",",""));
					}
					if(!StringUtils.isEmpty(user.getBianhao())){
						student.setNo(user.getBianhao().replace(",",""));
					}
					studentDao.insert(student);
					//ZHUCE C成功
					response.setCharacterEncoding("utf-8");

					response.setContentType("text/html;charset=utf-8;");

					PrintWriter writer = response.getWriter();
					writer.write(
							"<script type='text/javascript'>alert('注册成功，请登录！');setInterval('',1000);parent.location.href='/toLogin';</script>");
					writer.flush();
					writer.close();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "系统错误");
			return "forward:/toReg";
		}
		return "forward:/toReg";
	}
	
	//用户注销
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		try {
			HttpSession session=request.getSession();//获取当前会话对象
			session.invalidate();
			return "forward:/toLogin";
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "系统错误");
			return "forward:/toLogin";
		}
	}



	

}
