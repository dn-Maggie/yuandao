package com.dongnao.workbench.student.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dongnao.workbench.account.model.AccountFinance;
import com.dongnao.workbench.account.model.AccountFlow;
import com.dongnao.workbench.account.service.AccountFinanceService;
import com.dongnao.workbench.account.service.AccountFlowService;
import com.dongnao.workbench.basic.model.Course;
import com.dongnao.workbench.basic.model.Org;
import com.dongnao.workbench.basic.model.Subject;
import com.dongnao.workbench.basic.model.UserInfo;
import com.dongnao.workbench.basic.service.CourseService;
import com.dongnao.workbench.basic.service.SubjectService;
import com.dongnao.workbench.basic.service.UserInfoService;
import com.dongnao.workbench.common.bean.ResultMoney;
import com.dongnao.workbench.common.excel.ExcelExpUtils;
import com.dongnao.workbench.common.excel.ExpParamBean;
import com.dongnao.workbench.common.excel.IValueFormatter;
import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.school.model.Employee;
import com.dongnao.workbench.school.model.Standard;
import com.dongnao.workbench.school.service.EmployeeService;
import com.dongnao.workbench.school.service.StandardService;
import com.dongnao.workbench.student.model.Statistical;
import com.dongnao.workbench.student.model.VipStudent;
import com.dongnao.workbench.student.service.VipStudentService;

/**
 * 描述：会员信息管理表模块controller类，负责页面分发与跳转
 * 
 * @author cheng.mo
 * @version 1.0 2016-05-02
 */

@Controller
@RequestMapping("vipStudent")
public class VipStudentController {
	@Resource
	private VipStudentService vipStudentService;
	@Resource
	private SubjectService subjectService;
	@Resource
 	private CourseService courseService;
	private UserInfoService userInfoService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private StandardService standardService;
	@Resource
	private AccountFinanceService accountFinanceService;
	/**
	 * 进入新增页面
	 * 
	 * @return ModelAndView 返回到新增页面
	 */
	@RequestMapping("/toAddVipStudent")
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView(
				"WEB-INF/jsp/student/vipStudent/addVipStudent");
		
		Employee emp = new Employee();
		emp.setPosition("讲师");
		emp.setIsActive(1);
		List<Employee> teacher= employeeService.listByCondition(emp);
 		mv.addObject("teacher", teacher);
 		
 		emp.setPosition("班主任");
 		List<Employee> tutor= employeeService.listByCondition(emp);
 		mv.addObject("tutor", tutor);
 		
 		List<Standard> standard= standardService.getAllResourceId();
 		mv.addObject("standard", standard);
 		
 		List<Standard> followers= standardService.getAllFollowerId();
 		mv.addObject("followers", followers);
		
		Map<String,List> er = new HashMap<String, List>();
 		List<Subject> list = subjectService.listByCondition(new Subject());
 		List<Course> list2 =new ArrayList<Course>();
 		if(list!=null && list.size()>0){
 			Course c=new Course();
 	 		c.setSubjectId(list.get(0).getId());
 	 		list2=courseService.listByCondition(c);
 		}
 		er.put("subject", list);
 		er.put("course", list2);
 		mv.addObject("er", er);
 		
 		AccountFinance accountFlow = accountFinanceService.getByPrimaryKey("67250e52-e356-44a7-bb0e-f073360fb732");
 		mv.addObject("accountFlow", accountFlow);
		return mv;
	}
	
	/**
	 * 根据报名学科改变报名课程列表
	 */
	@RequestMapping("/getCourseList")
	public void getCourseList(String key,HttpServletResponse response){
		Course c=new Course();
 		c.setSubjectId(key);
 		List<Course>  list=courseService.listByCondition(c);
		Map<String, List> map = new HashMap<String, List>();
		map.put("course", list);
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 进入查看页面方法
	 * 
	 * @param key
	 *            String：实体id
	 * @return ModelAndView: 查询实体
	 */
	@RequestMapping("/toShowVipStudent")
	public ModelAndView toShow(String key) {
		VipStudent entity = vipStudentService.getByPrimaryKey(key);
		Map<String, String> vipStudent = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/student/vipStudent/showVipStudent", "vipStudent", vipStudent);
	}

	/**
	 * 新增方法
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param vipStudent
	 *            VipStudent:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addVipStudent")
	public void add(VipStudent vipStudent, HttpServletRequest request, HttpServletResponse response) {		
		vipStudent.setId(Utils.generateUniqueID());
		vipStudent.setEnterEmp(Utils.getLoginUserInfoId(request));
		vipStudentService.add(vipStudent);
		AjaxUtils.sendAjaxForObjectStr(response,vipStudentService.getByPrimaryKey(vipStudent.getId()));
	}

	/**
	 * 删除方法
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param key
	 *            String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteVipStudent")
	public void deleteByKey(String key, HttpServletResponse response) {
		String[] str = key.split(",");
		for (int i = 0; i < str.length; i++) {
			vipStudentService.deleteByKey(str[i]);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}

	/**
	 * 进入列表页面
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("/toListVipStudent")
	public ModelAndView toList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		
		Calendar calendar = Calendar.getInstance();
		Map<String,List> er = new HashMap<String, List>();
 		List<Subject> list = subjectService.listByCondition(new Subject());
 		er.put("subject", list);
 		model.put("er", er);
 		String term = request.getParameter("term");
 		if(term!=null){
 			switch (term) {
			case "vipCnt":
			case "orderCnt":
				model.put("condition", term);
				break;
			case "monthshouldPay":
			case "monthowePay":
			case "month":
				model.put("condition", term);
	 			model.put("currDate", calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-01");
				break;
			case "yearshouldPay":
			case "yearowePay":
			case "year":
				model.put("condition", term);
	 			model.put("currDate", calendar.get(Calendar.YEAR)+"-01-01");
				break;
			default:
				break;
			}
		}
 		//获取用户信息
 				UserInfo user = Utils.getLoginUserInfo(request);
 				Employee emp = employeeService.getByPrimaryKey(Utils.getLoginUserInfoId(request));
 				model.put("user",user);
 				model.put("emp",emp);
 		String roleId = user.getRoleId();
		//如果是如下几种角色，赋予管理员级别查看权限（可以查看所有人业绩）
		if("cce57309-c36a-4b2b-8596-4bc3ea008e88".equals(roleId)||//总经理
				"fcbb3b89-6aa8-428e-86e4-05f2ff8631da".equals(roleId)||//股东
				"7cea8feb-5408-4fea-ae04-5b55b69ec5ea".equals(roleId)||//班主任
				Utils.isSuperAdmin(request))
		{
			model.put("isAdmin",true);
		}
 		
		return new ModelAndView("WEB-INF/jsp/student/vipStudent/listVipStudent",model);
	}
	/**
	 * 根据条件查找列表方法
	 * 
	 * @param vipStudent
	 *            VipStudent：实体对象（查询条件）
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param page
	 *            Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listVipStudent")
	public void listByCondition(VipStudent vipStudent, HttpServletRequest request, HttpServletResponse response,
			Page page) {
		vipStudent.setPage(page);
		List<VipStudent> list = vipStudentService.listByCondition(vipStudent);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	/**
	 * 从意向过来报名的学员
	 * @param vipStudent
	 * @param request
	 * @param response
	 * @param page
	 */
	@RequestMapping("/listVipStudentFromMarket")
	public void listVipStudentFromMarket(VipStudent vipStudent, HttpServletRequest request, HttpServletResponse response,
			Page page) {
		vipStudent.setPage(page);
		List<VipStudent> list = vipStudentService.listVipStudentFromMarket(vipStudent);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 根据条件查找学生列表方法
	 * 
	 * @param vipStudent
	 *            VipStudent：实体对象（查询条件）
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param page
	 *            Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listVipStudentCnt")
	public void listCntByCondition(VipStudent vipStudent, HttpServletRequest request, HttpServletResponse response,
			Page page) {
		vipStudent.setPage(page);
		List<VipStudent> list = vipStudentService.listCntByCondition(vipStudent);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	
	/**
	 * 根据课程分类查找列表方法
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/orderBySubject")
	public void orderBySubject(VipStudent vipStudent, HttpServletRequest request, HttpServletResponse response,
			Page page) {
		List<VipStudent> list = vipStudentService.orderBySubject(null);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	/**
	 * 进入修改页面方法
	 * 
	 * @param key
	 *            String：实体id
	 * @return ModelAndView: 查询实体
	 */
	@RequestMapping("/toEditVipStudent")
	public ModelAndView toEdit(String key) {
		ModelAndView mv = new ModelAndView(
				"WEB-INF/jsp/student/vipStudent/editVipStudent");
		VipStudent entity = vipStudentService.getByPrimaryKey(key);
		Map<String, String> vipStudent = FormatEntity.getObjectValue(entity);
		mv.addObject("vipStudent", vipStudent);

		Map<String,List> er = new HashMap<String, List>();
 		List<Subject> list = subjectService.listByCondition(new Subject());
 		List<Course> list2 =new ArrayList<Course>();
 		if(list!=null && list.size()>0){
 			Course c=new Course();
 	 		c.setSubjectId(list.get(0).getId());
 	 		list2=courseService.listByCondition(c);
 		}
 		er.put("subject", list);
 		er.put("course", list2);
 		mv.addObject("er", er);
		
 		Employee emp = new Employee();
		emp.setDutyId("81e91c14-958e-4fd2-9641-aab8f9037d2c");
		List<Employee> teacher= employeeService.listByCondition(emp);
 		mv.addObject("teacher", teacher);
 		
 		emp.setPosition("班主任");
 		List<Employee> tutor= employeeService.listByCondition(emp);
 		mv.addObject("tutor", tutor);
 		

 		List<Standard> standard= standardService.getAllResourceId();
 		mv.addObject("standard", standard);
 		
 		List<Standard> followers= standardService.getAllFollowerId();
 		mv.addObject("followers", followers);
 		
 		
 		AccountFinance xftz = accountFinanceService.getByPrimaryKey("22d85e78-7e8c-4230-92d0-947cd8fc0f75");
 		mv.addObject("xftz", xftz);
 		
 		AccountFinance xftj = accountFinanceService.getByPrimaryKey("80575e8d-91ca-4a74-a160-cffaafa8bbf9");
 		mv.addObject("xftj", xftj);
		
		return mv;
	}

	/**
	 * 修改方法
	 * 
	 * @param vipStudent
	 *            VipStudent：实体对象
	 * @param response
	 *            HttpServletResponse
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/updateVipStudent")
	public void update(VipStudent vipStudent, HttpServletRequest request, HttpServletResponse response) {
		AjaxUtils.sendAjaxForObjectStr(response, vipStudentService.update(vipStudent));
	}
	
	/**
	 * 修改方法
	 * 
	 * @param vipStudent
	 *            VipStudent：实体对象
	 * @param response
	 *            HttpServletResponse
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/updateEmpPerform")
	public void updateEmpPerform(VipStudent vipStudent, HttpServletRequest request, HttpServletResponse response) {
		AjaxUtils.sendAjaxForObjectStr(response, vipStudentService.updateEmpPerform(vipStudent));
	}
	
	/**
	 * 修改欠费信息方法
	 * 
	 * @param vipStudent
	 *            VipStudent：实体对象
	 * @param response
	 *            HttpServletResponse
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/updateOweVipStudent")
	public void updateOwe(VipStudent vipStudent, HttpServletRequest request, HttpServletResponse response) {
		AjaxUtils.sendAjaxForObjectStr(response, vipStudentService.updateOwe(vipStudent));
	}
	
	
	
	
	/**
	 * ajax验证VIP是否存在
	 * 
	 * @param serialNo
	 *            交易号或流水号
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	@RequestMapping("/ajaxGetVipInfoByStu")
	public void ajaxGetVipInfoByStu(VipStudent vipstudent,
			HttpServletRequest request, HttpServletResponse response) {
		List<VipStudent> vipStudent = vipStudentService.listByCondition(vipstudent);
		boolean b = false;
		if (vipStudent.size()>0) {
			b = true;
		}
		AjaxUtils.sendAjaxForObject(response, b);
	}

	/**
	 * 进入业绩分配界面
	 */	
	@RequestMapping("/toManagePerformance")
	public ModelAndView toManagePerformance(String key){
		ModelAndView mv = new ModelAndView(
				"WEB-INF/jsp/student/vipStudent/managePerformance");
		
		VipStudent entity = vipStudentService.getByPrimaryKey(key);
		Map<String, String> vipStudent = FormatEntity.getObjectValue(entity);
		mv.addObject("vipStudent", vipStudent);
		Map<String,List> er = new HashMap<String, List>();
 		List<Subject> list = subjectService.listByCondition(new Subject());
 		List<Course> list2 =new ArrayList<Course>();
 		if(list!=null && list.size()>0){
 			Course c=new Course();
 	 		c.setSubjectId(list.get(0).getId());
 	 		list2=courseService.listByCondition(c);
 		}
 		er.put("subject", list);
 		er.put("course", list2);
 		mv.addObject("er", er);
		
		List<Standard> standard= standardService.getAllResourceId();
 		mv.addObject("standard", standard);
 		
 		List<Standard> subResource= standardService.getAllSubResourceId();
 		mv.addObject("subResource", subResource);
 		
 		List<Standard> followers= standardService.getAllFollowerId();
 		mv.addObject("followers", followers);
		
 		List<Employee> tutor= employeeService.listByCondition(new Employee());
 		mv.addObject("tutor", tutor);
 		
		return mv;
	}
	
	/**
	 * 进入兼职班主任查看列表页面
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("/toListAVipStudent")
	public ModelAndView toListA(HttpServletRequest request) {
		Map<String,Object> model = new HashMap<String,Object>();	
		Map<String,List> er = new HashMap<String, List>();
 		List<Subject> list = subjectService.listByCondition(new Subject());
 		
 		er.put("subject", list);
 		model.put("er", er);
		
		return new ModelAndView("WEB-INF/jsp/student/vipStudent/listAVipStudent",model);
	}
	
	
	@RequestMapping("/exportExcel")
	public void exportExcel(VipStudent vipStudent, ExpParamBean epb,
			HttpServletRequest request, HttpServletResponse response, Page page)
			throws Exception {		
		int expType = Integer.parseInt(request.getParameter("expType"));
		if (expType == 1) {
			vipStudent.setPage(page);
		}
		List<VipStudent> list = vipStudentService.listByCondition(vipStudent);
		ExcelExpUtils.exportListToExcel(list, response, epb.getFieldlist(),
				"vip学员信息列表", "vip学员信息列表");
	}
	
	@RequestMapping("/sumAllMoney")
	public void sumAllMoney(VipStudent vipStudent,HttpServletRequest request,
			HttpServletResponse response, Page page){	
		vipStudent.setPage(page);
		List<VipStudent> list = vipStudentService.sumAllmoney(vipStudent);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
}