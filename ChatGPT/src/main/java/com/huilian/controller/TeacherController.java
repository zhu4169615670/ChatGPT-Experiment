package com.huilian.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huilian.entity.*;
import com.huilian.mapper.StudentDao;
import com.huilian.mapper.StudentScoureDao;
import com.huilian.mapper.TeacherDao;
import com.huilian.service.UserService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * 教师
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private StudentScoureDao studentScoureDao;

	@RequestMapping("toAdd")
	public String toAdd(Teacher teacher, HttpServletRequest request) {
		return "addTeacher";
	}

	@RequestMapping("toUpdate")
	public String toUpdate(Teacher teacher, HttpServletRequest request) {

		Teacher teacher1 = teacherDao.selectById(teacher.getId());

		request.setAttribute("list",teacher1);

		return "updateTeacher";
	}


	@RequestMapping("doUpdate")
	public String doUpdate(Teacher teacher, HttpServletRequest request) {


		teacherDao.updateById(teacher);


		return "redirect:/teacher/findAll";
	}

	@RequestMapping("doAdd")
	public String doAdd(Teacher teacher, HttpServletRequest request) {


		teacherDao.insert(teacher);


		return "redirect:/teacher/findAll";
	}

	@RequestMapping("del")
	public String del(Teacher teacher, HttpServletRequest request) {


		teacherDao.deleteById(teacher.getId());


		return "redirect:/teacher/findAll";
	}

	/**
	 * 查询所有字段
	 * @param req
	 * @return
	 */
	@RequestMapping("/findAll")
	public ModelAndView findAll(HttpServletRequest req, @RequestParam(name = "no",required = false) String no, @RequestParam(required = false,name = "currentPageNo",defaultValue = "1") int currentPageNo, @RequestParam(required = false,name = "pageSize",defaultValue = "5") int pageSize, @RequestParam(required = false,name = "pageIndex",defaultValue = "1") Integer pageIndex) {

		//注意，此处需要写一个查询数据总数的方法
		QueryWrapper<Teacher> objectQueryWrapper = new QueryWrapper<>();

		if(!StringUtils.isEmpty(no)){
			objectQueryWrapper.eq("no",no);
		}
		int totalConut = teacherDao.selectCount(objectQueryWrapper);
		//设置页面大小，和初始页码为1
		int totalPageCount =((int)(totalConut/pageSize))+1;//总共有几页
//		还需要对传入的数据进行判断，防止出错
		if (currentPageNo < 1){
			currentPageNo = 1;
		}else if (currentPageNo>totalPageCount){
			currentPageNo =totalPageCount;
		}
		if (pageIndex!=null){
			currentPageNo =pageIndex;
		}
		ModelAndView modelAndView = new ModelAndView();


		List<Teacher> teachers = teacherDao.selectList(objectQueryWrapper);

		modelAndView.addObject("list",teachers);
		modelAndView.addObject("no",no);
//把这些必要的数据传给jsp
		modelAndView.addObject("totalCount",totalConut);//数据总数
		modelAndView.addObject("currentPageNo",currentPageNo);//当前页
		modelAndView.addObject("totalPageCount",totalPageCount);//总页数
		modelAndView.setViewName("listteacher");


		return modelAndView;
	}

	/**
	 * 评分列表
	 * @param req
	 * @param currentPageNo
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	@RequestMapping("/pinfenAll")
	public ModelAndView pinfenAll(HttpServletRequest req, @RequestParam(required = false,name = "currentPageNo",defaultValue = "1") int currentPageNo, @RequestParam(required = false,name = "pageSize",defaultValue = "5") int pageSize, @RequestParam(required = false,name = "pageIndex",defaultValue = "1") Integer pageIndex) {

		Teacher user = (Teacher) req.getSession().getAttribute("USER_SESSION");


		ModelAndView modelAndView = new ModelAndView();

		List<DaFen> list = new ArrayList<>();

		List<Student> students = studentDao.selectList(new QueryWrapper<Student>().isNotNull("fujian"));




		if(!CollectionUtils.isEmpty(students)){

			for (Student stuSource : students) {
				DaFen  daFen = new DaFen();
				daFen.setName(stuSource.getName());
				daFen.setFujian(stuSource.getFujian());
				daFen.setId(stuSource.getId());

				List<StuSource> stuSources = studentScoureDao.selectList(new QueryWrapper<StuSource>().eq("teaid", user.getId()).eq("stuid", stuSource.getId()));
				if(!CollectionUtils.isEmpty(stuSources)){
					StuSource stuSource1 = stuSources.get(0);
					if(!ObjectUtils.isEmpty(stuSource1)){
						if(!ObjectUtils.isEmpty(stuSource1.getSoure())){
							daFen.setFlag(1);
						}

						daFen.setCtime(stuSource1.getCratetime());
						daFen.setSoucre(stuSource1.getSoure());
					}
				}
				list.add(daFen);
			}
		}


		modelAndView.addObject("list",list);
//把这些必要的数据传给jsp
		modelAndView.setViewName("listpinfen");


		return modelAndView;
	}

	/**
	 * 打分了
	 * @param currentPageNo
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */

	@RequestMapping("/toDafen")
	public ModelAndView dafen(Integer id,HttpServletRequest request, @RequestParam(required = false,name = "currentPageNo",defaultValue = "1") int currentPageNo, @RequestParam(required = false,name = "pageSize",defaultValue = "5") int pageSize, @RequestParam(required = false,name = "pageIndex",defaultValue = "1") Integer pageIndex) {
		Teacher user = (Teacher) request.getSession().getAttribute("USER_SESSION");


		Student student1 = studentDao.selectById(id);


		ModelAndView modelAndView = new ModelAndView();


		modelAndView.addObject("list",student1);
		modelAndView.addObject("id",id);
		modelAndView.addObject("teaid",user.getId());
		modelAndView.addObject("stuid",student1.getId());
//把这些必要的数据传给jsp
		modelAndView.setViewName("addPinfen");


		return modelAndView;
	}


	@RequestMapping("/doPingFen")
	public String doPingFen(StuSource stuSource,HttpServletRequest request, @RequestParam(required = false,name = "currentPageNo",defaultValue = "1") int currentPageNo, @RequestParam(required = false,name = "pageSize",defaultValue = "5") int pageSize, @RequestParam(required = false,name = "pageIndex",defaultValue = "1") Integer pageIndex) {
		Teacher user = (Teacher) request.getSession().getAttribute("USER_SESSION");


	   studentScoureDao.insert(stuSource);


		return "redirect:/teacher/pinfenAll";
	}




}
