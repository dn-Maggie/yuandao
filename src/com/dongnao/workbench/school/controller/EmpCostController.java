package com.dongnao.workbench.school.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dongnao.workbench.account.service.AccountFlowService;
import com.dongnao.workbench.basic.model.Org;
import com.dongnao.workbench.basic.service.OrgService;
import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.DateUtil;
import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.school.model.EmpCost;
import com.dongnao.workbench.school.model.Employee;
import com.dongnao.workbench.school.service.EmpCostService;
import com.dongnao.workbench.school.service.EmployeeService;


/**
 * 描述：员工成本表模块controller类，负责页面分发与跳转
 * 
 * @author maggie
 * @version 1.0 2016-12-14
 */
 
@Controller
@RequestMapping("empCost")
public class EmpCostController{
    @Resource
	private EmployeeService employeeService;
    @Resource
	private AccountFlowService accountFlowService;
    @Resource
	private EmpCostService empCostService;
    @Resource
    private OrgService orgService;
 	/**
 	* 进入新增页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddEmpCost")
	public ModelAndView toAdd(){
 		ModelAndView mv =  new ModelAndView("WEB-INF/jsp/school/empCost/addEmpCost");
 		mv.addObject("empList",employeeService.listByCondition(new Employee()));
 		List<Org> org= orgService.listByCondition(new Org());
 		mv.addObject("org", org);
 		return mv;
 	}
 	
 	
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowEmpCost")
	public ModelAndView toShow(String key){
		EmpCost entity = empCostService.getByPrimaryKey(key);
		Map<String,String> empCost = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/school/empCost/showEmpCost","empCost",empCost );
	}
	
	
	/**
	 * 新增方法
	 * @param response HttpServletResponse
	 * @param empCost EmpCost:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addEmpCost")
	public void add(EmpCost empCost,HttpServletRequest request,HttpServletResponse response){
		empCost.setId(Utils.generateUniqueID());
		empCost.setMonth(DateUtil.now());
		empCost.setStatus(1);
		AjaxUtils.sendAjaxForObjectStr(response,empCostService.add(empCost));		
	}
	
	/**
	 * 删除方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteEmpCost")
	public void deleteByKey(String key,HttpServletResponse response){
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			empCostService.deleteByKey(str[i]);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}
	/**
	 * 进入列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListEmpCost")
	public ModelAndView toList(){
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/school/empCost/listEmpCost");
		Org org = new Org();
 		mv.addObject("org",orgService.listByCondition(org));
		return mv;
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param empCost EmpCost：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listEmpCost")
	public void listByCondition(EmpCost empCost,HttpServletRequest request,
			HttpServletResponse response, Page page){
		empCost.setPage(page);	
		List<EmpCost> list = empCostService.listByCondition(empCost);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 进入修改页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toEditEmpCost")
	public ModelAndView toEdit(String key){
		ModelAndView mv =  new ModelAndView("WEB-INF/jsp/school/empCost/editEmpCost");
		EmpCost entity = empCostService.getByPrimaryKey(key);
		Map<String,String> empCost = FormatEntity.getObjectValue(entity);
		mv.addObject("empCost",empCost);
 		mv.addObject("empList",employeeService.listByCondition(new Employee()));
 		List<Org> org= orgService.listByCondition(new Org());
 		mv.addObject("org", org);
 		return mv;
	}
	
	/**
	 * 修改方法
	 * @param empCost EmpCost：实体对象
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/updateEmpCost")
	public void update(EmpCost empCost,HttpServletRequest request,HttpServletResponse response){
		AjaxUtils.sendAjaxForObjectStr(response,empCostService.update(empCost));	
	}
}