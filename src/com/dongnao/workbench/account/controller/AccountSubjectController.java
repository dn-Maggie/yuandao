package com.dongnao.workbench.account.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.account.model.AccountSubject;
import com.dongnao.workbench.account.service.AccountSubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 描述：会计科目信息模块controller类，负责页面分发与跳转
 * 
 * @author maggie
 * @version 1.0 2016-08-10
 */
 
@Controller
@RequestMapping("accountSubject")
public class AccountSubjectController{
         @Resource
	private AccountSubjectService accountSubjectService;
	 
 	/**
 	* 进入新增页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddAccountSubject")
	public ModelAndView toAdd(){
		return new ModelAndView("WEB-INF/jsp/account/accountSubject/addAccountSubject");
	}
	
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowAccountSubject")
	public ModelAndView toShow(String key){
		AccountSubject entity = accountSubjectService.getByPrimaryKey(key);
		Map<String,String> accountSubject = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/account/accountSubject/showAccountSubject","accountSubject",accountSubject );
	}
	
	/**
	 * 新增方法
	 * @param response HttpServletResponse
	 * @param accountSubject AccountSubject:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addAccountSubject")
	public void add(AccountSubject accountSubject,HttpServletRequest request,HttpServletResponse response){
	accountSubject.setId(Utils.generateUniqueID());
	AjaxUtils.sendAjaxForObjectStr(
				response,accountSubjectService.add(accountSubject));		
	}
	
	/**
	 * 删除方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteAccountSubject")
	public void deleteByKey(String key,HttpServletResponse response){
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			accountSubjectService.deleteByKey(str[i]);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 进入列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListAccountSubject")
	public ModelAndView toList(){
		return new ModelAndView("WEB-INF/jsp/account/accountSubject/listAccountSubject");
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param accountSubject AccountSubject：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listAccountSubject")
	public void listByCondition(AccountSubject accountSubject,HttpServletRequest request,
			HttpServletResponse response, Page page){
		accountSubject.setPage(page);	
		List<AccountSubject> list = accountSubjectService.listByCondition(accountSubject);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 进入修改页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toEditAccountSubject")
	public ModelAndView toEdit(String key){
		AccountSubject entity = accountSubjectService.getByPrimaryKey(key);
		Map<String,String> accountSubject = FormatEntity.getObjectValue(entity);
		
		return new ModelAndView("WEB-INF/jsp/account/accountSubject/editAccountSubject","accountSubject",accountSubject );
	}
	
	/**
	 * 修改方法
	 * @param accountSubject AccountSubject：实体对象
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/updateAccountSubject")
	public void update(AccountSubject accountSubject,HttpServletRequest request,HttpServletResponse response){
		AjaxUtils.sendAjaxForObjectStr(
				response,accountSubjectService.update(accountSubject));	
	}
	
}