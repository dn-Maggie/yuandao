package com.dongnao.workbench.school.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongnao.workbench.account.model.ExpenseAccount;
import com.dongnao.workbench.basic.model.Org;
import com.dongnao.workbench.basic.model.UserInfo;
import com.dongnao.workbench.basic.service.OrgService;
import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.DateUtil;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.school.model.Employee;
import com.dongnao.workbench.school.model.LeaveApply;
import com.dongnao.workbench.school.service.EmployeeService;
import com.dongnao.workbench.school.service.LeaveApplyService;
import com.sun.corba.se.spi.orbutil.fsm.State;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 描述：员工请假管理模块controller类，负责页面分发与跳转
 * 
 * @author maggie
 * @version 1.0 2016-09-12
 */
 
@Controller
@RequestMapping("leaveApply")
public class LeaveApplyController{
         @Resource
         private LeaveApplyService leaveApplyService;
         @Resource
      	 private EmployeeService employeeService;	
         @Resource
         private OrgService orgService;
	 
 	/**
 	* 进入新增页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddLeaveApply")
	public ModelAndView toAdd(HttpServletRequest request){
 		 ModelAndView mv =  new ModelAndView("WEB-INF/jsp/school/empLeaveApply/addLeaveApply");
 		 String userid = Utils.getLoginUserInfoId(request);
 		 mv.addObject("user", employeeService.getByPrimaryKey(userid));
 		 mv.addObject("emp", employeeService.listByCondition(new Employee()));
		 return mv;
	}
	
	/**
	 * 进入查看单个详细页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowLeaveApply")
	public ModelAndView toShow(String key){
		LeaveApply entity = leaveApplyService.getByPrimaryKey(key);
		Map<String,String> leaveApply = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/school/empLeaveApply/showLeaveApply","leaveApply",leaveApply );
	}
	
	/**
	 * 新增方法
	 * @param response HttpServletResponse
	 * @param leaveApply LeaveApply:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addLeaveApply")
	public void add(LeaveApply leaveApply,HttpServletRequest request,HttpServletResponse response){
	leaveApply.setId(Utils.generateUniqueID());
	AjaxUtils.sendAjaxForObjectStr(
				response,leaveApplyService.add(leaveApply));		
	}
	
	/**
	 * 删除方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteLeaveApply")
	public void deleteByKey(String key,HttpServletResponse response){
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			leaveApplyService.deleteByKey(str[i]);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 进入申请者查看列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toApplyLeaveApply")
	public ModelAndView toList(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/school/empLeaveApply/listLeaveApplyByApply");
		if(!Utils.isSuperAdmin(request)){
			 mv.addObject("isNotAdmin", true);
		}
		
		mv.addObject("user",employeeService.getByPrimaryKey(Utils.getLoginUserInfoId(request)));
		return mv;
	}
	/**
	 * 进入审核者查看列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListLeaveApplyByAudit")
	public ModelAndView toListByAudit(HttpServletRequest request){
		ModelAndView mv =new ModelAndView("WEB-INF/jsp/school/empLeaveApply/listLeaveApplyByAudit");
		String userRole = Utils.getLoginUserInfo(request).getRoleId();
		Pattern deptLeaderRole = Pattern.compile("fcbb3b89-6aa8-428e-86e4-05f2ff8631da");
		Pattern headLeaderRole = Pattern.compile("cce57309-c36a-4b2b-8596-4bc3ea008e88");
		Pattern commonRole = Pattern.compile("3bf41c9b-5af1-46fd-9142-7d075c79e17a");
		Matcher deptLeader = deptLeaderRole.matcher(userRole);
		Matcher headLeader = headLeaderRole.matcher(userRole);
		Matcher common = commonRole.matcher(userRole);
		if(deptLeader.find()){
			mv.addObject("deptLeader",true);
		}else if(headLeader.find()){
			mv.addObject("headLeader",true);
		}else if(common.find()){
			mv.addObject("common",true);
		}
		
		List<LeaveApply> list = leaveApplyService.getStraightLeader();
		for(int i = 0;i<list.size();i++){
			if(list.get(i)!=null && list.get(i).getStraightLeader().equals(Utils.getLoginUserInfoId(request))){
				 mv.addObject("straightLeader", true);
			}
		}
		
		Org org = new Org();
		org.setParentOrgId("1");
 		mv.addObject("org",orgService.listByCondition(org));
		
		if(Utils.isSuperAdmin(request)){
			 mv.addObject("isAdmin", true);
		}
		 mv.addObject("user",employeeService.getByPrimaryKey(Utils.getLoginUserInfoId(request)));
		 return mv;
	}
	/**
	 * 进入考勤详细列表汇总页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListAllLeaveApply")
	public ModelAndView toListAll(HttpServletRequest request){
		return new ModelAndView("WEB-INF/jsp/school/empLeaveApply/allLeaveApply");
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param leaveApply LeaveApply：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listLeaveApply")
	public void listByCondition(LeaveApply leaveApply,HttpServletRequest request,
			HttpServletResponse response, Page page){
		leaveApply.setPage(page);	
		List<LeaveApply> list = leaveApplyService.listByCondition(leaveApply);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 进入修改页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toEditLeaveApply")
	public ModelAndView toEdit(String key,HttpServletRequest request){
		ModelAndView mv =new ModelAndView("WEB-INF/jsp/school/empLeaveApply/editLeaveApply");
		mv.addObject("leaveApply", FormatEntity.getObjectValue(leaveApplyService.getByPrimaryKey(key)));
		mv.addObject("user", employeeService.getByPrimaryKey(Utils.getLoginUserInfoId(request)));
		mv.addObject("emp", employeeService.listByCondition(new Employee()));
		return mv ;
	}
	
	/**
	 * 修改方法
	 * @param leaveApply LeaveApply：实体对象
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/updateLeaveApply")
	public void update(LeaveApply leaveApply,HttpServletRequest request,HttpServletResponse response){
		AjaxUtils.sendAjaxForObjectStr(
				response,leaveApplyService.update(leaveApply));	
	}
	/**
	 * 进入单个审核页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toAuditLeaveApply")
	public ModelAndView toAudit(String key,String empId,HttpServletRequest request){
		 ModelAndView mv =new ModelAndView("WEB-INF/jsp/school/empLeaveApply/auditLeaveApply");
		 UserInfo user = Utils.getLoginUserInfo(request);
		 Employee entity = employeeService.getByPrimaryKey(empId);
		 Map<String,String> employee = FormatEntity.getObjectValue(entity);
		 LeaveApply leav = leaveApplyService.getByPrimaryKey(key);
		 Map<String, String> leaveApply = FormatEntity.getObjectValue(leav);
		 mv.addObject("user",user);
		 mv.addObject("leaveApply",leaveApply);
		 mv.addObject("employee",employee);
		 return mv;
	}
	/**
	 * 审核方法
	 */
	@RequestMapping("/auditLeaveApply")
	public void auditLeaveApply(String key,HttpServletRequest request,HttpServletResponse response){
		UserInfo user=Utils.getLoginUserInfo(request);
		String checkId = user.getId();
		Map<String, String> map = new HashMap<String, String>();
		Calendar calendar = Calendar.getInstance();
		
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			LeaveApply entity = leaveApplyService.getByPrimaryKey(str[i]);
			int flag = entity.getCheckFlag();
			if(flag==1){
				entity.setCheckId(checkId);
				entity.setCheckFlag(2);
				entity.setCheckDate(calendar.getTime());
				leaveApplyService.update(entity);
			}else{
				continue;
			}
		}
		map.put("msg", "审核成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}
	/**
	 * 驳回审核方法
	 */
	@RequestMapping("/cancelAuditLeaveApply")
	public void cancelAuditLeaveApply(String key,HttpServletRequest request,HttpServletResponse response){
		UserInfo user=Utils.getLoginUserInfo(request);
		String checkId = user.getId();
		Map<String, String> map = new HashMap<String, String>();
		Calendar calendar = Calendar.getInstance();
		
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			LeaveApply entity = leaveApplyService.getByPrimaryKey(str[i]);
			int flag = entity.getCheckFlag();
			if(flag!=3){
			entity.setCheckId(checkId);
			entity.setCheckFlag(3);
			entity.setCheckDate(calendar.getTime());
			leaveApplyService.update(entity);
			}else{
				continue;
			}
		}
		map.put("msg", "审核驳回成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}
	/**
	 * 统计所有方法
	 */
	@RequestMapping("/sumAllLeave")
	public void sumAllLeave(LeaveApply leaveApply,HttpServletRequest request,
			HttpServletResponse response, Page page){	
		leaveApply.setPage(page);
		List<LeaveApply> list = leaveApplyService.sumAllLeave(leaveApply);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	/**
	 * 子列表展开方法
	 */
	@RequestMapping("/subgridlist")
	public void subgridlist(String id,String startDate,String endDate,HttpServletRequest request,
			HttpServletResponse response, Page page){	
		LeaveApply entity = leaveApplyService.getByPrimaryKey(id);
		entity.setPage(page);
		Date startdate = DateUtil.parseStringToyyyyMMddHHmmss(startDate);
		Date enddate = DateUtil.parseStringToyyyyMMddHHmmss(endDate);
		List<LeaveApply> list = leaveApplyService.subgridlist(entity,startdate,enddate);
		AjaxUtils.sendAjaxForPage(request, response, page, list);	
	}
}