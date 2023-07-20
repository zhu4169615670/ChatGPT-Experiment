package com.huilian.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huilian.entity.*;
import com.huilian.mapper.StudentDao;
import com.huilian.mapper.StudentScoureDao;
import com.huilian.mapper.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 学生
 */
@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private StudentScoureDao studentScoureDao;

	@Autowired
	private TeacherDao teacherDao;

	@RequestMapping("toAdd")
	public String toAdd(Student teacher, HttpServletRequest request) {
		return "addStudent";
	}

	@RequestMapping("toUpdate")
	public String toUpdate(Student teacher, HttpServletRequest request) {

        Student teacher1 = studentDao.selectById(teacher.getId());

		request.setAttribute("list",teacher1);

		return "updateStudent";
	}


	@RequestMapping("doUpdate")
	public String doUpdate(Student teacher, HttpServletRequest request) {


		studentDao.updateById(teacher);


		return "redirect:/student/findAll";
	}

	/**
	 * 这里 换成自己得路径
	 */
	private static final String path = "D:\\project\\pro\\3-30交付\\user-manager\\src\\main\\resources\\static\\upload\\";
	private static final String path1 = "D:\\project\\pro\\3-30交付\\user-manager\\target\\classes\\static\\upload\\";

	/**
	 * 将MultipartFile转换为File
	 *
	 *
	 * @param outFilePath 参数
	 * @param multiFile 参数
	 * @return 执行结果
	 */
	public static File multipartFileToFile(String outFilePath, MultipartFile multiFile) {
		// 获取文件名
		if (null == multiFile) {
			return null;
		}
		String fileName = multiFile.getOriginalFilename();
		if (null == fileName) {
			return null;
		}
		try {
			File toFile;
			InputStream ins;
			ins = multiFile.getInputStream();
			//指定存储路径
			toFile = new File(outFilePath.concat(File.separator).concat(multiFile.getOriginalFilename()));
			inputStreamToFile(ins, toFile);
			return toFile;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	private static void inputStreamToFile(InputStream ins, File file) {
		try (OutputStream os = new FileOutputStream(file)) {
			int bytesRead;
			int bytes = 8192;
			byte[] buffer = new byte[bytes];
			while ((bytesRead = ins.read(buffer, 0, bytes)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 查看频分信息
	 * @param request
	 * @return
	 */
	@RequestMapping("pinfen")
	public ModelAndView pinfen(Student student, HttpServletRequest request) {

		List<StuSource> stuid = studentScoureDao.selectList(new QueryWrapper<StuSource>().eq("stuid", student.getId()));

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("studentpinfen");
		List<StuPinVo> list = new ArrayList<>();
		double sum = 0;
		if(!CollectionUtils.isEmpty(stuid)){
			modelAndView.addObject("flag",1);
			for (StuSource stuSource : stuid) {
				StuPinVo stuPinVo = new StuPinVo();
				QueryWrapper<Teacher> wrapper = new QueryWrapper<Teacher>().eq("id", stuSource.getTeaid());
				stuPinVo.setName(teacherDao.selectOne(wrapper).getName());
				stuPinVo.setSource(stuSource.getSoure());
				stuPinVo.setDate(stuSource.getCratetime());
				list.add(stuPinVo);
				sum+=stuSource.getSoure();
			}
			//计算平均数
			modelAndView.addObject("avg",new BigDecimal(sum).divide(new BigDecimal(stuid.size())).setScale(2, RoundingMode.HALF_UP));
			modelAndView.addObject("count",stuid.size());
		}else {
			modelAndView.addObject("flag",0);
		}
		modelAndView.addObject("list",list);

		return modelAndView;
	}


	@RequestMapping("doAdd")
	@Transactional
	public String doAdd(Student teacher,  HttpServletRequest request) throws IOException {
//
//		MultipartFile file = teacher.getFile();
//		MultipartFile file1 = teacher.getFile();
//		File file2 = new File(path+file.getOriginalFilename());
//		if(file2.exists()){
//			file2.delete();
//		}
//		file2.createNewFile();
//		File file3 = new File(path1+file.getOriginalFilename());
//		if(file3.exists()){
//			file3.delete();
//		}
//		file3.createNewFile();
//		FileCopyUtils.copy(file.getInputStream(), Files.newOutputStream(file2.toPath()));
//		FileCopyUtils.copy(file1.getInputStream(), Files.newOutputStream(file3.toPath()));
//		teacher.setFujian(file.getOriginalFilename());
		studentDao.insert(teacher);


		return "redirect:/student/findAll";
	}

	@RequestMapping("del")
	public String del(Teacher teacher, HttpServletRequest request) {


		studentDao.deleteById(teacher.getId());


		return "redirect:/student/findAll";
	}

	/**
	 * 查询所有字段
	 * @param req
	 * @return
	 */
	@RequestMapping("/findAll")
	public ModelAndView findAll(HttpServletRequest req, @RequestParam(name = "no",required = false) String no, @RequestParam(required = false,name = "currentPageNo",defaultValue = "1") int currentPageNo, @RequestParam(required = false,name = "pageSize",defaultValue = "5") int pageSize, @RequestParam(required = false,name = "pageIndex",defaultValue = "1") Integer pageIndex) {

		//注意，此处需要写一个查询数据总数的方法
		QueryWrapper<Student> objectQueryWrapper = new QueryWrapper<>();

		if(!StringUtils.isEmpty(no)){
			objectQueryWrapper.eq("no",no);
		}
		int totalConut = studentDao.selectCount(objectQueryWrapper);
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


		List<Student> teachers = studentDao.selectList(objectQueryWrapper);

		modelAndView.addObject("list",teachers);
		modelAndView.addObject("no",no);
		modelAndView.addObject("totalCount",totalConut);//数据总数
		modelAndView.addObject("currentPageNo",currentPageNo);//当前页
		modelAndView.addObject("totalPageCount",totalPageCount);//总页数
		modelAndView.setViewName("liststudent");


		return modelAndView;
	}


	/**
	 * 上传论文
	 * @param request
	 * @return
	 */
	@RequestMapping("findMe")
	public String findMe(HttpServletRequest request) {

		Student user = (Student) request.getSession().getAttribute("USER_SESSION");

		request.setAttribute("user",user);



		return "listlunwen";
	}


	@RequestMapping("toLunwen")
	public String toLunwen(HttpServletRequest request, HttpServletResponse response) throws IOException {

		Student user = (Student) request.getSession().getAttribute("USER_SESSION");

		request.setAttribute("user",user);


		return "addLunWen";


	}



	@RequestMapping("doLunWen")
	@Transactional
	public String doLunWen(HttpServletRequest request,MultipartFile file) throws IOException {
		Student user = (Student) request.getSession().getAttribute("USER_SESSION");

		File file2 = new File(path+file.getOriginalFilename());
		if(file2.exists()){
			file2.delete();
		}
		file2.createNewFile();
		File file3 = new File(path1+file.getOriginalFilename());
		if(file3.exists()){
			file3.delete();
		}
		file3.createNewFile();
		FileCopyUtils.copy(file.getInputStream(), Files.newOutputStream(file2.toPath()));
		FileCopyUtils.copy(file.getInputStream(), Files.newOutputStream(file3.toPath()));
		user.setFujian(file.getOriginalFilename());

		studentDao.updateById(user);


		return "redirect:/student/findMe";
	}





}
