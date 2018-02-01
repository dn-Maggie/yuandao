package com.dongnao.workbench.school.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dongnao.workbench.basic.model.Duty;
import com.dongnao.workbench.basic.model.DutyLevel;
import com.dongnao.workbench.basic.model.Org;
import com.dongnao.workbench.basic.service.DutyLevelService;
import com.dongnao.workbench.basic.service.DutyService;
import com.dongnao.workbench.basic.service.OrgService;
import com.dongnao.workbench.common.bean.ChinaArea;
import com.dongnao.workbench.common.bean.Nation;
import com.dongnao.workbench.common.excel.ExcelExpUtils;
import com.dongnao.workbench.common.excel.ExpParamBean;
import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.school.model.Employee;
import com.dongnao.workbench.school.service.EmployeeService;
import com.dongnao.workbench.system.service.DictInfoService;


/**
 * 描述：员工模块controller类，负责页面分发与跳转
 * 
 * @author maggie
 * @version 1.0 2016-07-15
 */
 
@Controller
@RequestMapping("employee")
public class EmployeeController{
    @Resource
	private EmployeeService employeeService;
    @Resource
    private OrgService orgService;
    @Resource
    private DictInfoService dictInfoService;
    @Resource
	private DutyService dutyService;
    @Resource
	private DutyLevelService dutyLevelService;
 	/**
 	* 进入新增页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddEmployee")
	public ModelAndView toAdd(){
 		ModelAndView mv = new ModelAndView(
 				"WEB-INF/jsp/school/employee/addEmployee");
 		
 		List<Nation> nation= employeeService.getAllNation();
 		mv.addObject("nation",nation);
 		
 		Org org = new Org();
 		mv.addObject("org",orgService.listByCondition(org));
 		
 		Map<String,List> area = new HashMap<String, List>();
 		List<ChinaArea> list = employeeService.loadAreaByParent(0);
 		List<ChinaArea> list2 =new ArrayList<ChinaArea>();
 		if(list!=null && list.size()>0){
 	 		list2=employeeService.loadAreaByParent(list.get(0).getId());
 		}
 		area.put("parent",list);
 		area.put("child",list2);
 		mv.addObject("area",area);
 		
 		mv.addObject("nowEmpNo", employeeService.getNowEmpNo());
		List<Employee> tutor= employeeService.listByCondition(new Employee());
 		mv.addObject("tutor", tutor);
		return mv;
	}
 	/**
	 * 地区二级联动
	 */
	@RequestMapping("/getAreaList")
	public void getAreaList(Integer key,HttpServletResponse response){
 		List<ChinaArea> chinaArea = employeeService.loadAreaByParent(key);
		Map<String, List> map = new HashMap<String, List>();
		map.put("ca",chinaArea);
		AjaxUtils.sendAjaxForMap(response,map);
	}
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowEmployee")
	public ModelAndView toShow(String key){
		Employee entity = employeeService.getByPrimaryKey(key);
		Map<String,String> employee = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/school/employee/showEmployee","employee",employee );
	}
	
	/**
	 * 新增方法
	 * @param response HttpServletResponse
	 * @param employee Employee:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addEmployee")
	public void add(Employee employee,HttpServletRequest request,HttpServletResponse response){
	employee.setId(Utils.generateUniqueID());
		try {
			AjaxUtils.sendAjaxForObjectStr(
					response,employeeService.add(employee));	
		} catch (Exception e) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "添加失败");
			AjaxUtils.sendAjaxForMap(response, map);
		}
		
	}
	
	/**
	 * 删除方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteEmployee")
	public void deleteByKey(String key,HttpServletResponse response){
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			employeeService.deleteByKey(str[i]);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 进入列表页面
	 * @return ModelAndView
	 */
	@SuppressWarnings("null")
	@RequestMapping("/toListEmployee")
	public ModelAndView toList(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/school/employee/listEmployee");
		String userRole = Utils.getLoginUserInfo(request).getRoleId();
		if(userRole!=null||userRole.length()>0){
			Pattern HRRole = Pattern.compile("46f0d3f8-350f-486d-b221-cb5879019c8f");
			Matcher isHR = HRRole.matcher(userRole);
			if(isHR.find()){
				mv.addObject("isHR",true);
			}
		}
		mv.addObject("duty", dutyService.listByCondition(new Duty()));
		Org org = new Org();
 		mv.addObject("org",orgService.listByCondition(org));
		return mv;
		
		
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param employee Employee：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listEmployee")
	public void listByCondition(Employee employee,HttpServletRequest request,
			HttpServletResponse response, Page page){
		employee.setPage(page);	
		List<Employee> list = employeeService.listByCondition(employee);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param employee Employee：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listEmployeeByCondition")
	public void listEmployeeByOrg(Employee employee,HttpServletRequest request,HttpServletResponse response){
		List<Employee> list = employeeService.listByCondition(employee);
		AjaxUtils.sendAjaxForListStr(response, list);
	}
	
	
	/**
	 * 计算员工工资方法
	 * @param employee Employee：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/countEmployeeSal")
	public void countEmployeeSal(Employee employee,HttpServletRequest request,
			HttpServletResponse response, Page page){
		employee.setPage(page);	
		List<Employee> list = employeeService.countEmployeeSal(employee);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 进入修改页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toEditEmployee")
	public ModelAndView toEdit(String key){
		ModelAndView mv = new ModelAndView(
				"WEB-INF/jsp/school/employee/editEmployee");
		
		Employee entity = employeeService.getByPrimaryKey(key);
		Map<String,String> employee = FormatEntity.getObjectValue(entity);
		mv.addObject("employee", employee);
		
		List<Nation> nation= employeeService.getAllNation();
 		mv.addObject("nation", nation);
 		
 		mv.addObject("org",orgService.listByCondition(new Org()));
 		mv.addObject("dutyLevel", dutyLevelService.listByCondition(new DutyLevel()));
 		
 		Map<String,List> area = new HashMap<String, List>();
 		List<ChinaArea> list = employeeService.loadAreaByParent(0);
 		List<ChinaArea> list2 =new ArrayList<ChinaArea>();
 		if(list!=null && list.size()>0){
 	 		list2=employeeService.loadAreaByParent(list.get(0).getId());
 		}
 		area.put("parent",list);
 		area.put("child",list2);
 		mv.addObject("area",area);
 		
 		List<Employee> tutor= employeeService.listByCondition(new Employee());
 		mv.addObject("tutor", tutor);
		return mv;
	}
	
	/**
	 * 修改方法
	 * @param employee Employee：实体对象
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/updateEmployee")
	public void update(Employee employee,HttpServletRequest request,HttpServletResponse response){
		/*if(employee.getIsAssess()!=1){
			employee.setCheckName("");
			employee.setCheckStanderd(0);
		}*/
		AjaxUtils.sendAjaxForObjectStr(response,employeeService.update(employee));	
	}
	/**
	 * 异步ajax通过员工姓名模糊查询员工id返回
	 * @param term String：科目编号
	 */	
 	@RequestMapping("/getFollowerIdByStockCode")
 	public void getFollowerIdByStockCode(String name,HttpServletResponse response){
 		Employee emp = new Employee();
 		emp.setName(name);
 		Employee empout = employeeService.listByCondition(emp).get(0);
		
		if (empout != null) {
			AjaxUtils.sendAjaxForObject(response, empout);
		}
 	}
 	
	/**
	 * ajax验证工号是否存在
	 * 
	 * @param empno
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	@RequestMapping("/ajaxGetEmpInfo")
	public void ajaxGetEmpInfo(Employee employee,
			HttpServletRequest request, HttpServletResponse response) {		
		List<Employee> list = employeeService.listByCondition(employee);
		boolean b = false;
		if (list.size()>0) {
			b = true;
		}
		AjaxUtils.sendAjaxForObject(response, b);
	}
 	
 	@RequestMapping("/exportExcel")
	public void exportExcel(Employee employee, ExpParamBean epb,
			HttpServletRequest request, HttpServletResponse response, Page page)
			throws Exception {		
		int expType = Integer.parseInt(request.getParameter("expType"));
		if (expType == 1) {
			employee.setPage(page);
		}
		List<Employee> list = employeeService.listByCondition(employee);
		ExcelExpUtils.exportListToExcel(list, response, epb.getFieldlist(),
				"员工信息列表", "员工信息列表");
	}
 	
	/**
	 * 考核人输入框数据验证方法
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/checkNameValidation")
	public void checkNameValidation(String checkName,HttpServletResponse response){
		String num = String.valueOf(employeeService.checkNameValidation(checkName));
		Map<String, String> map = new HashMap<String, String>();
		map.put("flag", num);
		AjaxUtils.sendAjaxForMap(response, map);
	}
}