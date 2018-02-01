package com.dongnao.workbench.account.controller;

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

import com.dongnao.workbench.account.model.AccountFlow;
import com.dongnao.workbench.account.model.ExpenseAccount;
import com.dongnao.workbench.account.service.AccountFlowService;
import com.dongnao.workbench.account.service.ExpenseAccountService;
import com.dongnao.workbench.basic.model.Org;
import com.dongnao.workbench.basic.model.UserInfo;
import com.dongnao.workbench.basic.service.OrgService;
import com.dongnao.workbench.basic.service.UserInfoService;
import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.school.model.Employee;
import com.dongnao.workbench.school.service.EmployeeService;


/**
 * 描述：报销单模块controller类，负责页面分发与跳转
 * 
 * @author maggie
 * @version 1.0 2016-08-18
 */
 
@Controller
@RequestMapping("expenseAccount")
public class ExpenseAccountController{
         @Resource
         private ExpenseAccountService expenseAccountService;
         @Resource
      	 private UserInfoService userInfoService;
         @Resource
     	 private EmployeeService employeeService;
         @Resource
     	 private OrgService orgService;
         @Resource
     	 private AccountFlowService accountFlowService;
 	/**
 	* 进入新增页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddExpenseAccount")
	public ModelAndView toAdd(HttpServletRequest request){
 		ModelAndView mv = new ModelAndView(
 				"WEB-INF/jsp/account/expenseAccount/addExpenseAccount");
 		UserInfo user = Utils.getLoginUserInfo(request);
 		Employee entity = employeeService.getByPrimaryKey(user.getId());
 		mv.addObject("employee",entity);
 		
		List<Employee> tutor= employeeService.listByCondition(new Employee());
 		mv.addObject("tutor", tutor);
 		
 		List<Org> org= orgService.listByCondition(new Org());
 		org.remove(0);
 		mv.addObject("org", org);
 		return mv;
	}
	
	/**
	 * 进入报销详情查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowExpenseAccount")
	public ModelAndView toShow(String key){
		ExpenseAccount entity = expenseAccountService.getByPrimaryKey(key);
		Map<String,String> expenseAccount = FormatEntity.getObjectValue(entity);//讲对象转换成map，工具类格式化实体
		return new ModelAndView("WEB-INF/jsp/account/expenseAccount/detailInfo","expenseAccount",expenseAccount );
	}
	
	/**
	 * 新增方法
	 * @param response HttpServletResponse
	 * @param expenseAccount ExpenseAccount:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addExpenseAccount")
	public void add(ExpenseAccount expenseAccount,HttpServletRequest request,HttpServletResponse response){
	expenseAccount.setId(Utils.generateUniqueID());
	AjaxUtils.sendAjaxForObjectStr(
				response,expenseAccountService.add(expenseAccount));		
	}
	
	/**
	 * 删除方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteExpenseAccount")
	public void deleteByKey(String key,HttpServletResponse response){
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			expenseAccountService.deleteByKey(str[i]);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}

	
	/**
	 * 进入申请页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListExpenseAccount")
	public ModelAndView toList(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/account/expenseAccount/applyExpenseAccount");
		UserInfo user = Utils.getLoginUserInfo(request);
		mv.addObject("user",user);
		return mv;
	}
	
	/**
	 * 进入会计审核列表页面（审核发放页面）
	 * @return ModelAndView
	 */
	@RequestMapping("/toListExpenseAccountByAudit")// 审核发放
	public ModelAndView toListByAudit(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/account/expenseAccount/listExpenseAccount");
		return mv;
	}
	
	/**
	 * 进入总计详细列表页面(部门报销总计页面)
	 * @return ModelAndView
	 */	
	@RequestMapping("/toListAllExpenseAccount")// 详细列表
	public ModelAndView toListAll(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/account/expenseAccount/showExpenseAccount");
		UserInfo user = Utils.getLoginUserInfo(request);
		mv.addObject("user",user);
		return mv;
	}
	
	/**
	 * 进入统计列表页面
	 * @return ModelAndView
	 */	
	@RequestMapping("/toListTotalExpenseAccount")// 报销汇总
	public ModelAndView toListTotal(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/account/expenseAccount/staticTotal");
		return mv;
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param expenseAccount ExpenseAccount：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listExpenseAccount")
	public void listByCondition(ExpenseAccount expenseAccount,HttpServletRequest request,
			HttpServletResponse response, Page page){
		expenseAccount.setPage(page);	
		UserInfo user = Utils.getLoginUserInfo(request);
		String userRole = user.getRoleId();
		List<ExpenseAccount> list = expenseAccountService.listByCondition(expenseAccount);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 进入报销记录页面方法
	 * @param request HttpServletRequest
	 */
	@RequestMapping("/tolistexpenseRecords")
	public ModelAndView toexpenseRecords(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/account/expenseAccount/listexpenseRecords");
		return mv;
	}
	
	/**
	 * 查询报销记录详情数据的方法
	 * @param expenseAccount ExpenseAccount：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listexpenseRecords")
	public void expenseRecords(ExpenseAccount expenseAccount,HttpServletRequest request,
			HttpServletResponse response, Page page){
		expenseAccount.setPage(page);	
		expenseAccount.setCheckFlag(2);//设置筛选条件为“2”即审核已通过
		List<ExpenseAccount> list = expenseAccountService.listByCondition(expenseAccount);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	
	/**
	 * 进入修改页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toEditExpenseAccount")
	public ModelAndView toEdit(String key){
		ModelAndView mv = new ModelAndView(
				"WEB-INF/jsp/account/expenseAccount/editExpenseAccount");
		
		ExpenseAccount entity = expenseAccountService.getByPrimaryKey(key);
		Map<String,String> expenseAccount = FormatEntity.getObjectValue(entity);
		mv.addObject("expenseAccount", expenseAccount);
		
		List<Employee> tutor= employeeService.listByCondition(new Employee());
 		mv.addObject("tutor", tutor);
 		
 		List<Org> org= orgService.listByCondition(new Org());
 		org.remove(0);
 		mv.addObject("org", org);
 		
 		return mv;
	}
	
	/**
	 * 修改方法
	 * @param expenseAccount ExpenseAccount：实体对象
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/updateExpenseAccount")
	public void update(ExpenseAccount expenseAccount,HttpServletRequest request,HttpServletResponse response){
		AjaxUtils.sendAjaxForObjectStr(
				response,expenseAccountService.update(expenseAccount));	
	}
	
	
	/**
 	* 进入审核页面
 	* @return ModelAndView 返回到审核页面
 	*/
 	@RequestMapping("/toAuditExpenseAccount")
	public ModelAndView toAudit(String key,HttpServletRequest request){
 		ModelAndView mv = new ModelAndView("WEB-INF/jsp/account/expenseAccount/auditExpenseAccount");
 		UserInfo user=Utils.getLoginUserInfo(request);
 		
 		ExpenseAccount entity = expenseAccountService.getByPrimaryKey(key);
		Map<String,String> expenseAccount = FormatEntity.getObjectValue(entity);
		mv.addObject("expenseAccount", expenseAccount);
		
 		List<UserInfo> userInfo = userInfoService.listByCondition(null);
		mv.addObject("userInfo", userInfo);

 		mv.addObject("user", user);
 		
		return mv;
	}
	
 	
 	/**
	 * 批量审核方法（初审）
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/auditExpenseAccount")
	public void auditAccounting(String key, HttpServletRequest request, HttpServletResponse response) {
		UserInfo user = Utils.getLoginUserInfo(request);
		String checkPid = user.getId();
		String checkName = user.getFullName();
		Calendar calendar = Calendar.getInstance();
		Map<String, String> map = new HashMap<String, String>();
		String[] str = key.split(",");
		for (int i = 0; i < str.length; i++) {
			ExpenseAccount entity = expenseAccountService.getByPrimaryKey(str[i]);
			int flag = entity.getCheckFlag();
			if (flag == 1) {
				entity.setCheckFlag(2);
				entity.setCheckPid(checkPid);
				entity.setCheckName(checkName);
				entity.setCheckDate(calendar.getTime());
				expenseAccountService.auditByKey(entity);
				map.put("msg", "审核完成！");
			} else {
				map.put("msg", "该报销单已审核通过！");
				continue;
			} 
			/*if (flag == 1) {有双重审核时放开
				entity.setCheckFlag(3);
				entity.setCheckPid(checkPid);
				entity.setCheckName(checkName);
				entity.setCheckDate(calendar.getTime());
				expenseAccountService.auditByKey(entity);
				map.put("msg", "初审审核完成！");
			} else if (flag == 3) {
				map.put("msg", "该报销单已经通过了初审！");
				continue;
			} else {
				map.put("msg", "该报销单已经审核通过！");
				continue;
			}*/
		}
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 批量审核方法（复审）
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/rauditExpenseAccount")
	public void recheckauditAccounting(String key, HttpServletRequest request, HttpServletResponse response) {
		UserInfo user = Utils.getLoginUserInfo(request);
		String checkPid = user.getId();
		String checkName = user.getFullName();
		Calendar calendar = Calendar.getInstance();

		Map<String, String> map = new HashMap<String, String>();
		String[] str = key.split(",");
		for (int i = 0; i < str.length; i++) {
			ExpenseAccount entity = expenseAccountService.getByPrimaryKey(str[i]);
			int flag = entity.getCheckFlag();
			if (flag == 3) {
				entity.setCheckFlag(2);
				entity.setCheckPid(checkPid);
				entity.setCheckName(checkName);
				entity.setCheckDate(calendar.getTime());
				expenseAccountService.auditByKey(entity);
				map.put("msg", "复审审核完成！");
			} else if (flag == 1) {
				map.put("msg", "该报销单还没有通过初审！");
				continue;
			} else {
				map.put("msg", "该报销单已经审核通过！");
				continue;
			}
		}
		AjaxUtils.sendAjaxForMap(response, map);
	}

	/**
	 * 单条审核方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/singaleaudit")
	public void singaleaudit(ExpenseAccount expenseAccount, HttpServletRequest request, HttpServletResponse response) {
		UserInfo user = Utils.getLoginUserInfo(request);
		String checkPid = user.getId();
		String checkName = user.getFullName();
		Calendar calendar = Calendar.getInstance();
		Map<String, String> map = new HashMap<String, String>();
			ExpenseAccount entity = expenseAccountService.getByPrimaryKey(expenseAccount.getId());
			int flag = entity.getCheckFlag();
			if (flag == 1) {
				entity.setCheckFlag(2);
				entity.setCheckPid(checkPid);
				entity.setCheckName(checkName);
				entity.setCheckDate(calendar.getTime());
				entity.setCheckPizhu(expenseAccount.getCheckPizhu());
				expenseAccountService.auditByKey(entity);
				map.put("msg", "审核完成！");
			} else {
				map.put("msg", "该报销单已审核通过！");
			} 
			/*if (flag == 1) {有双重审核时放开
				entity.setCheckFlag(3);
				entity.setCheckPid(checkPid);
				entity.setCheckName(checkName);
				entity.setCheckDate(calendar.getTime());
				expenseAccountService.auditByKey(entity);
				map.put("msg", "初审审核完成！");
			} else if (flag == 3) {
				map.put("msg", "该报销单已经通过了初审！");
				continue;
			} else {
				map.put("msg", "该报销单已经审核通过！");
				continue;
			}*/
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 报销资金发放方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/assignExpenseAccount")
	public void assignAccounting(String key,HttpServletRequest request,HttpServletResponse response){
		Calendar calendar = Calendar.getInstance();
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			ExpenseAccount entity = expenseAccountService.getByPrimaryKey(str[i]);
			int flag = entity.getAssignFlag();
			if(flag!=2){
				expenseAccountService.assignByKey(entity);
				//添加财务流水信息
				AccountFlow accountFlow = new AccountFlow();
				accountFlow.setId(Utils.generateUniqueID());
				accountFlow.setIsAccount(1);
				accountFlow.setCreateDate(calendar.getTime());
				accountFlow.setMoney(entity.getExpenseMoney());
				accountFlow.setAccountId("d1554e08-3767-4fea-a16c-fa2dd7d59e97");
				accountFlow.setAccountNo("YGBX");
				accountFlow.setAccountName("员工报销");
				accountFlow.setAccountType(2);
				accountFlow.setNote("报销费用");
				accountFlow.setEmpId(entity.getEnterPid());
				accountFlowService.add(accountFlow);
			}else{
				Map<String, String> map = new HashMap<String, String>();
				map.put("msg", "该报销单已发放金额，无需重复发放");
				AjaxUtils.sendAjaxForMap(response, map);
				return;
			}
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	
	/**
	 * 根据员工统计方法
	 * @param empPerformance EmpPerformance：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listByEmployee")
	public void listByEmployee(ExpenseAccount expenseAccount,HttpServletRequest request,
			HttpServletResponse response, Page page){
		expenseAccount.setPage(page);	
		List<ExpenseAccount> list = expenseAccountService.listByEmployee(expenseAccount);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	//报销详细
	@RequestMapping("/sumAllExpense")
	public void sumAllExpense(ExpenseAccount expenseAccount,HttpServletRequest request,
			HttpServletResponse response, Page page){	
		expenseAccount.setPage(page);
		List<ExpenseAccount> list = expenseAccountService.sumAllExpense(expenseAccount);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	
	//报销详细子列表
	@RequestMapping("/subgridlist")
	public void subgridlist(String id,String startDate,String endDate,HttpServletRequest request,
			HttpServletResponse response, Page page){	
		ExpenseAccount entity = expenseAccountService.getByPrimaryKey(id);
		entity.setPage(page);
		List<ExpenseAccount> list = expenseAccountService.subgridlist(entity,startDate,endDate);
		AjaxUtils.sendAjaxForPage(request, response, page, list);	
	}
	
	
	//报销汇总
	@RequestMapping("/staticExpenseAccount")
	public void staticExpenseAccount(ExpenseAccount expenseAccount,HttpServletResponse response){
		String startDate ="";String endDate="";
		try{
			startDate = expenseAccount.getPropsMap().get("startDate").toString();
			endDate = expenseAccount.getPropsMap().get("endDate").toString();
		}catch(Exception e){
			
		}
		if(startDate.length()>0||endDate.length()>0){
			expenseAccount.setStartDate(startDate);
			expenseAccount.setEndDate(endDate);
		}
		Map<String,Object> model =new HashMap<String, Object>();
		model.put("statics", expenseAccountService.getStatistical(expenseAccount));
		AjaxUtils.sendAjaxForMap(response, model);
	}
}