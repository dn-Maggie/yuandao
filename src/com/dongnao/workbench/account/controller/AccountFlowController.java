package com.dongnao.workbench.account.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongnao.workbench.common.bean.ResultMoney;
import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.Utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.common.util.StringUtil;
import com.dongnao.workbench.account.model.AccountFinance;
import com.dongnao.workbench.account.model.AccountFlow;
import com.dongnao.workbench.account.model.AccountSubject;
import com.dongnao.workbench.account.service.AccountFinanceService;
import com.dongnao.workbench.account.service.AccountFlowService;
import com.dongnao.workbench.account.service.AccountSubjectService;
import com.dongnao.workbench.basic.model.Subject;
import com.dongnao.workbench.basic.model.UserInfo;
import com.dongnao.workbench.basic.service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 描述：财务流水模块controller类，负责页面分发与跳转
 * 
 * @author cjw
 * @version 1.0 2016-05-01
 */
 
@Controller
@RequestMapping("accountFlow")
public class AccountFlowController{
    @Resource
	private AccountFlowService accountFlowService;
    @Resource
 	private AccountSubjectService accountSubjectService;
    @Resource
 	private AccountFinanceService accountFinanceService;
    @Resource
	private SubjectService subjectService;
	 
 	/**
 	* 进入新增页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddAccountFlow")
	public ModelAndView toAdd(){
		return new ModelAndView("WEB-INF/jsp/account/accountFlow/addAccountFlow");
	}
	
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowAccountFlow")
	public ModelAndView toShow(String key){
		AccountFlow entity = accountFlowService.getByPrimaryKey(key);
		Map<String,String> accountFlow = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/account/accountFlow/showAccountFlow","accountFlow",accountFlow );
	}
	
	/**
	 * 新增方法
	 * @param response HttpServletResponse
	 * @param accountFlow AccountFlow:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addAccountFlow")
	public void add(AccountFlow accountFlow,HttpServletRequest request,HttpServletResponse response){
	accountFlow.setId(Utils.generateUniqueID());
	accountFlow.setIsAccount(1);
	AjaxUtils.sendAjaxForObjectStr(
				response,accountFlowService.add(accountFlow));		
	}
	
	/**
	 * 删除方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteAccountFlow")
	public void deleteByKey(String key,HttpServletResponse response){
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			accountFlowService.deleteByKey(str[i]);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 获取总额方法
	 */
	@RequestMapping("/getTotal")
	public void getTotal(@RequestBody String param,HttpServletRequest request,HttpServletResponse response){
		if(param == null||param=="") {
			return;
		}else{
			JSONArray ja = JSONArray.fromObject(param);
			Map<String,Object>money =new HashMap<String, Object>();
			for(Object o : ja) {
				JSONObject jo = (JSONObject) o;
				AccountFlow accountFlow = new AccountFlow();
				
				accountFlow.setStartDate(jo.getString("startDate"));
				accountFlow.setEndDate(jo.getString("endDate"));
				accountFlow.setSubjectName(jo.getString("subjectName"));
				accountFlow.setAccountName(jo.getString("accountName"));
				accountFlow.setAccountNameExcept(jo.getString("accountNameExcept"));
 				accountFlow.setAccountNameInclude(jo.getString("accountNameInclude"));
				
				money.put("money", accountFlowService.getCountMoney(accountFlow));
			}
			AjaxUtils.sendAjaxForMap(response, money);
		}
	}
	
	/**
	 * 获取学费详细方法
	 */
	@RequestMapping("/getXFTotal")
	public void getXFTotal(HttpServletResponse response){
		Map<String,Object>xfmoney =new HashMap<String, Object>();
		ResultMoney data = accountFlowService.getXFMoney(null);
		xfmoney.put("xfmoney", data);
		AjaxUtils.sendAjaxForMap(response, xfmoney);
	}
	
	/**
	 * 进入列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListAccountFlow")
	public ModelAndView toList(String term){
		Map<String,Object> model = new HashMap<String,Object>();
		Calendar calendar = Calendar.getInstance();
		
		Map<String,List> er = new HashMap<String, List>();
 		List<Subject> list = subjectService.listByCondition(new Subject());
 		
 		er.put("subject", list);
 		model.put("er", er);
		
		if(term!=null){
 			switch (term) {
			case "monthcost":
			case "monthxftk":
			case "monthactualPay":
			case "monthmajorIncome":
				model.put("condition", term);
	 			model.put("currDate", calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-01");
				break;
			case "yearcost":
			case "yearxftk":
			case "majorIncome":
			case "yearactualPay":
			case "QTSR":
			case "majorCost":
			case "QT":
			case "allCost":
				model.put("condition", term);
	 			model.put("currDate", calendar.get(Calendar.YEAR)+"-01-01");
				break;
			default:
				break;
			}
		}
		return new ModelAndView("WEB-INF/jsp/account/accountFlow/listAccountFlow",model);
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param accountFlow AccountFlow：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listAccountFlow")
	public void listByCondition(AccountFlow accountFlow,HttpServletRequest request,
			HttpServletResponse response, Page page){
		accountFlow.setPage(page);
		List<AccountFlow> list = accountFlowService.listByCondition(accountFlow);
		
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param accountFlow AccountFlow：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listAccountFlowCost")
	public void listCostByCondition(AccountFlow accountFlow,HttpServletRequest request,
			HttpServletResponse response, Page page){
		accountFlow.setPage(page);
		List<AccountFlow> list = accountFlowService.listCostByCondition(accountFlow);
		
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	
	/**
	 * 进入修改页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toEditAccountFlow")
	public ModelAndView toEdit(String key){
		AccountFlow entity = accountFlowService.getByPrimaryKey(key);
		Map<String,String> accountFlow = FormatEntity.getObjectValue(entity);
		return  new ModelAndView("WEB-INF/jsp/account/accountFlow/editAccountFlow","accountFlow",accountFlow );
	}
	
	/**
	 * 修改方法
	 * @param accountFlow AccountFlow：实体对象
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/updateAccountFlow")
	public void update(AccountFlow accountFlow,HttpServletRequest request,HttpServletResponse response){
		AjaxUtils.sendAjaxForObjectStr(
				response,accountFlowService.update(accountFlow));	
	}
	
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toCountAll")
	public ModelAndView toCount(){
		ModelAndView mv = new ModelAndView(
				"WEB-INF/jsp/account/accountFlow/countAll");
		Calendar calendar = Calendar.getInstance();
		Integer m =(Integer)((calendar.get(Calendar.MONTH))+1);
		mv.addObject("curr", accountFlowService.listByAccountName(calendar.get(Calendar.YEAR)+""+m));
		
		List<AccountFlow> all= accountFlowService.listByAccountName(calendar.get(Calendar.YEAR)+"");
		mv.addObject("all",all);
		return mv;
	}
}