package com.dongnao.workbench.school.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongnao.workbench.basic.model.Course;
import com.dongnao.workbench.basic.model.Org;
import com.dongnao.workbench.basic.model.UserInfo;
import com.dongnao.workbench.basic.service.OrgService;
import com.dongnao.workbench.common.excel.ExcelExpUtils;
import com.dongnao.workbench.common.excel.ExpParamBean;
import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.school.model.EmpPerformance;
import com.dongnao.workbench.school.model.Employee;
import com.dongnao.workbench.school.model.RecentTwoMonthEmpPerf;
import com.dongnao.workbench.school.model.Standard;
import com.dongnao.workbench.school.service.EmpPerformanceService;
import com.dongnao.workbench.school.service.EmployeeService;
import com.dongnao.workbench.school.service.StandardService;
import com.dongnao.workbench.student.model.VipStudent;
import com.dongnao.workbench.student.service.VipStudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 描述：员工绩效信息表模块controller类，负责页面分发与跳转
 * 
 * @author maggie
 * @version 1.0 2016-07-24
 */
 
@Controller
@RequestMapping("empPerformance")
public class EmpPerformanceController{
         @Resource
         private EmpPerformanceService empPerformanceService;
         @Resource
     	 private StandardService standardService;
         @Resource
     	 private EmployeeService employeeService;
         @Resource
         private OrgService orgService;
         @Resource
     	 private VipStudentService vipStudentService;
 	/**
 	* 进入新增业绩页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddEmpPerformance")
	public ModelAndView toAdd(){
 		ModelAndView mv = new ModelAndView(
 				"WEB-INF/jsp/school/empPerformance/addEmpPerformance");
 		
 		List<Standard> standard= standardService.getAllResourceId();
 		mv.addObject("standard", standard);
 		
 		List<Standard> followers= standardService.getAllFollowerId();
 		mv.addObject("followers", followers);

 		List<Employee> tutor= employeeService.listByCondition(new Employee());
 		mv.addObject("tutor", tutor);
		return mv;
	}
	
 	/**
 	* 进入补录页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddEmp")
	public ModelAndView toAddEmp(){
 		ModelAndView mv = new ModelAndView(
 				"WEB-INF/jsp/school/empPerformance/addNewEmp");
 		
 		List<Standard> standard= standardService.getAllResourceId();
 		mv.addObject("standard", standard);
 		
 		List<Standard> followers= standardService.getAllFollowerId();
 		mv.addObject("followers", followers);

 		List<Employee> tutor= employeeService.listByCondition(new Employee());
 		mv.addObject("tutor", tutor);
		return mv;
	}
 	
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowEmpPerformance")
	public ModelAndView toShow(String key){
		Map<String,Object> model = new HashMap<String,Object>();
		
		EmpPerformance entity = empPerformanceService.getByPrimaryKey(key);
		Map<String,String> empPerformance = FormatEntity.getObjectValue(entity);
		
		String stuid = entity.getStuId();
		VipStudent vipStudent = vipStudentService.getByPrimaryKey(stuid);
		
		model.put("empPerformance", empPerformance);
		model.put("vipStudent", vipStudent);
		return new ModelAndView("WEB-INF/jsp/school/empPerformance/showEmpPerformance",model);
	}
	
	/**
	 * 新增方法员工贡献绩效
	 * @param response HttpServletResponse
	 * @param empPerformance EmpPerformance:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addEmpPerformance")
	public void add(EmpPerformance empPerformance,HttpServletRequest request,HttpServletResponse response){
	empPerformance.setId(Utils.generateUniqueID());
	AjaxUtils.sendAjaxForObjectStr(
				response,empPerformanceService.add(empPerformance));		
	}
	
	/**
	 * 删除员工贡献绩效方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteEmpPerformance")
	public void deleteByKey(String key,HttpServletResponse response){
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			empPerformanceService.deleteByKey(str[i]);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 根据学生Id删除员工贡献绩效
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteEmpPerformanceByStuId")
	public void deleteByStuId(EmpPerformance empPerformance,HttpServletResponse response){
		//删掉emp_performance(贡献绩效总额)中的数据
		empPerformanceService.deleteByStuId(empPerformance);
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 进入员工绩效界面列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListEmpPerformance")
	public ModelAndView toList(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/school/empPerformance/listEmpPerformance");
		//获取用户信息
		UserInfo user = Utils.getLoginUserInfo(request);
		Employee emp = employeeService.getByPrimaryKey(Utils.getLoginUserInfoId(request));
		mv.addObject("user",user);
		String userName = user.getUserAccount();
		String roleId = user.getRoleId();
		//如果是如下几种角色，赋予管理员级别查看权限（可以查看所有人业绩）
		if("cce57309-c36a-4b2b-8596-4bc3ea008e88".equals(roleId)||//总经理
				"fcbb3b89-6aa8-428e-86e4-05f2ff8631da".equals(roleId)||//股东
				Utils.isSuperAdmin(request))
		{
			mv.addObject("isAdmin",true);
		}
		
		
		Org org = new Org();
		//如果是以下权限，赋予部门负责人权限（可以查看整个部门的业绩）
		if("06b4f5f2-ff20-446b-9c9a-05623c0bb76a".equals(roleId)||//部门负责人
				"ad07dcf7-336b-4874-b574-d1eec4c21dba".equals(roleId)//部门营销负责人
			)
		{
			mv.addObject("leader",true);
			org.setOrgNo(emp.getDeptNo());
		}
		mv.addObject("org",orgService.listByCondition(org));
		mv.addObject("followers", standardService.getAllFollowerId());
		return mv;
	}
	
	/**
	 * 进入选择补录业绩列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toSelectStudent")
	public ModelAndView toSelect(String type){
		Map<String,Object> model = new HashMap<String,Object>();
		if(("addNewEmp").equals(type)){
			return new ModelAndView("WEB-INF/jsp/school/empPerformance/selectOldVipinfo",model);
		}else{
			return new ModelAndView("WEB-INF/jsp/school/empPerformance/selectStudent",model);
		}
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param empPerformance EmpPerformance：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listEmpPerformance")
	public void listByCondition(EmpPerformance empPerformance,HttpServletRequest request,
			HttpServletResponse response, Page page){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		empPerformance.setPage(page);	
		List<EmpPerformance> list = empPerformanceService.listByCondition(empPerformance);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 查询讲师成本与业绩
	 */
	@RequestMapping("/listEmpBonusCost")
	public void listEmpBonusCost(EmpPerformance empPerformance,HttpServletRequest request,
			HttpServletResponse response, Page page){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		empPerformance.setPage(page);	
		List<EmpPerformance> list = empPerformanceService.listEmpBonusCost(empPerformance);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	
	/**
	 * 查看员工贡献绩效总额
	 * @param empPerformance EmpPerformance：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listByEmployee")
	public void listByEmployee(EmpPerformance empPerformance,HttpServletRequest request,
			HttpServletResponse response, Page page){
		empPerformance.setPage(page);	
		List<EmpPerformance> list = empPerformanceService.listByEmployee(empPerformance);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	/**
	 * 查看员工奖金总额
	 * @param empPerformance EmpPerformance：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listBonusByEmployee")
	public void listBonusByEmployee(EmpPerformance empPerformance,HttpServletRequest request,
			HttpServletResponse response, Page page){
		empPerformance.setPage(page);	
		List<EmpPerformance> list = empPerformanceService.listBonusByEmployee(empPerformance);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 进入修改员工贡献绩效总额页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toEditEmpPerformance")
	public ModelAndView toEdit(String key){
		ModelAndView mv = new ModelAndView(
				"WEB-INF/jsp/school/empPerformance/editEmpPerformance");
		
		EmpPerformance empPerformance = empPerformanceService.getByPrimaryKey(key);
		mv.addObject("empPerformance", empPerformance);
		
		List<Employee> tutor= employeeService.listByCondition(new Employee());
 		mv.addObject("tutor", tutor);
		
 		List<Standard> followers= standardService.getAllFollowerId();
 		mv.addObject("followers", followers);
 		
 		List<Standard> standard= standardService.getAllResourceId();
 		mv.addObject("standard", standard);
 		
 		String stuid = empPerformance.getStuId();
		VipStudent vipStudent = vipStudentService.getByPrimaryKey(stuid);
		mv.addObject("vipStudent", vipStudent);
 		
		return mv;
		
		
	}
	
	/**
	 * 修改员工贡献绩效总额方法
	 * @param empPerformance EmpPerformance：实体对象
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/updateEmpPerformance")
	public void update(EmpPerformance empPerformance,HttpServletRequest request,HttpServletResponse response){
		AjaxUtils.sendAjaxForObjectStr(
				response,empPerformanceService.update(empPerformance));	
	}
	
 	/**
	 * 根据学生来源改变参与岗位选项
	 */
	@RequestMapping("/getPositionList")
	public void getPositionList(String parentId, String subId ,HttpServletResponse response){
		Standard s=new Standard();
 		s.setParentId(parentId);
 		s.setSubId(subId);
 		List<Standard>  list= standardService.listByCondition(s);
		Map<String, List> map = new HashMap<String, List>();
		map.put("followers", list);
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 进入财务清算页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toSettleEmpPerformance")
	public ModelAndView toSettle(String key){
		ModelAndView mv = new ModelAndView(
				"WEB-INF/jsp/school/empPerformance/assetSettlement");
		
		EmpPerformance entity = empPerformanceService.getByPrimaryKey(key);
		Map<String,String> empPerformance = FormatEntity.getObjectValue(entity);
		mv.addObject("empPerformance", empPerformance);
		
		List<Employee> tutor= employeeService.listByCondition(new Employee());
 		mv.addObject("tutor", tutor);
		
 		List<Standard> followers= standardService.getAllFollowerId();
 		mv.addObject("followers", followers);
 		
		return mv;
	}
	
	/**
	 * 批量清算方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/settleAllEmpPerformance")
	public void settleAllEmpPerformance(String key,HttpServletResponse response){
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			empPerformanceService.settleByKey(str[i]);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 导出业绩总额方法
	 */
	@RequestMapping("/exportExcel")
	public void exportExcel(EmpPerformance empPerformance, ExpParamBean epb,
			HttpServletRequest request, HttpServletResponse response, Page page)
			throws Exception {		
		int expType = Integer.parseInt(request.getParameter("expType"));
		if (expType == 1) {
			empPerformance.setPage(page);
		}
		List<EmpPerformance> list = empPerformanceService.listByCondition(empPerformance);
		ExcelExpUtils.exportListToExcel(list, response, epb.getFieldlist(),"业绩分配信息列表", "业绩分配信息列表");
	}
	
	/**
	 * 查询最近两个月每个员工的不同岗位（转化、推广、讲师授课、客服等）的营收总额
	 * */
	@RequestMapping("/recentTwoMonthEmpRevenue")
	public void recentTwoMonthEmpRevenue(EmpPerformance empPerformance,HttpServletRequest request,
			HttpServletResponse response, Page page){
		empPerformance.setPage(page);
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = (now.get(Calendar.MONTH) + 1);
		List<RecentTwoMonthEmpPerf> list = empPerformanceService.recentTwoMonthEmpRevenue(empPerformance);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
}