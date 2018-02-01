package com.dongnao.workbench.account.controller;

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
import com.dongnao.workbench.account.service.AccountFinanceService;
import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.common.util.Utils;


/**
 * 描述：财务科目模块controller类，负责页面分发与跳转
 * 
 * @author cjw
 * @version 1.0 2016-05-01
 */
 
@Controller
@RequestMapping("accountFinance")
public class AccountFinanceController{
         @Resource
	private AccountFinanceService accountFinanceService;
	 
 	/**
 	* 进入新增页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddAccountFinance")
	public ModelAndView toAdd(){
		return new ModelAndView("WEB-INF/jsp/account/accountFinance/addAccountFinance");
	}
	
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowAccountFinance")
	public ModelAndView toShow(String key){
		AccountFinance entity = accountFinanceService.getByPrimaryKey(key);
		Map<String,String> accountFinance = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/account/accountFinance/showAccountFinance","accountFinance",accountFinance );
	}
	
	/**
	 * 新增方法
	 * @param response HttpServletResponse
	 * @param accountFinance AccountFinance:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addAccountFinance")
	public void add(AccountFinance accountFinance,HttpServletRequest request,HttpServletResponse response){
	accountFinance.setId(Utils.generateUniqueID());
	AjaxUtils.sendAjaxForObjectStr(
				response,accountFinanceService.add(accountFinance));		
	}
	
	/**
	 * 删除方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteAccountFinance")
	public void deleteByKey(String key,HttpServletResponse response){
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			accountFinanceService.deleteByKey(str[i]);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 进入列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListAccountFinance")
	public ModelAndView toList(){
		return new ModelAndView("WEB-INF/jsp/account/accountFinance/listAccountFinance");
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param accountFinance AccountFinance：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listAccountFinance")
	public void listByCondition(AccountFinance accountFinance,HttpServletRequest request,
			HttpServletResponse response, Page page){
		accountFinance.setPage(page);	
		List<AccountFinance> list = accountFinanceService.listByCondition(accountFinance);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 异步ajax通过科目编号模糊查询科目编号返回
	 * @param term String：科目编号
	 */	
 	@RequestMapping("/getFinanceNoByStockCode")
 	public void getFinanceNoByStockCode(String term,HttpServletResponse response){
 		AccountFinance accountFinance = new AccountFinance();
 		accountFinance.setAccountNo(term);
 		AjaxUtils.sendAjaxForListStr(response, accountFinanceService.listByCondition(accountFinance));
 	}
	
	/**
	 * 进入修改页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toEditAccountFinance")
	public ModelAndView toEdit(String key){
		AccountFinance entity = accountFinanceService.getByPrimaryKey(key);
		Map<String,String> accountFinance = FormatEntity.getObjectValue(entity);
		
		return new ModelAndView("WEB-INF/jsp/account/accountFinance/editAccountFinance","accountFinance",accountFinance );
	}
	
	/**
	 * 修改方法
	 * @param accountFinance AccountFinance：实体对象
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/updateAccountFinance")
	public void update(AccountFinance accountFinance,HttpServletRequest request,HttpServletResponse response){
		AjaxUtils.sendAjaxForObjectStr(
				response,accountFinanceService.update(accountFinance));	
	}
	
}