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

import com.dongnao.workbench.account.model.AccountSubject;
import com.dongnao.workbench.account.model.AssetItem;
import com.dongnao.workbench.account.model.FixedAsset;
import com.dongnao.workbench.account.service.AccountSubjectService;
import com.dongnao.workbench.account.service.FixedAssetService;
import com.dongnao.workbench.basic.model.Org;
import com.dongnao.workbench.basic.service.OrgService;
import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.school.model.Employee;
import com.dongnao.workbench.school.service.EmployeeService;


/**
 * 描述：固定资产信息模块controller类，负责页面分发与跳转
 * 
 * @author maggie
 * @version 1.0 2016-08-12
 */
 
@Controller
@RequestMapping("fixedAsset")
public class FixedAssetController{
         @Resource
         private FixedAssetService fixedAssetService;
         @Resource
     	 private OrgService orgService;
         @Resource
      	 private EmployeeService employeeService;	
         @Resource
      	 private AccountSubjectService accountSubjectService;
    /**
 	* 进入新增页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddFixedAsset")
	public ModelAndView toAdd(){
		ModelAndView mv = new ModelAndView(
				"WEB-INF/jsp/account/fixedAsset/addFixedAsset");
		//查询所有部门
		Org orgEntity = new Org();
		orgEntity.setOrgClass(3);
 		List<Org> org= orgService.listByCondition(orgEntity);
 		mv.addObject("org", org);
 		//查询资产项目列表
 		AssetItem ai = new AssetItem();
 		List<AssetItem> assetItem = fixedAssetService.listAssetItem(ai);
 		mv.addObject("assetItem", assetItem);
 		//查询会计科目
 		AccountSubject entity = new AccountSubject();
 		entity.setParentId(6);
 		List<AccountSubject> zjSubjectList = accountSubjectService.listByCondition(entity);
 		mv.addObject("zjSubjectList", zjSubjectList);
 		mv.addObject("emp", employeeService.listByCondition(new Employee()));
 		return mv;
	}
	
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowFixedAsset")
	public ModelAndView toShow(String key){
		FixedAsset entity = fixedAssetService.getByPrimaryKey(key);
		Map<String,String> fixedAsset = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/account/fixedAsset/showFixedAsset","fixedAsset",fixedAsset );
	}
	
	/**
	 * 新增公司资产方法
	 * @param response HttpServletResponse
	 * @param fixedAsset FixedAsset:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addFixedAsset")
	public void add(FixedAsset fixedAsset,HttpServletRequest request,HttpServletResponse response){
	AjaxUtils.sendAjaxForObjectStr(
				response,fixedAssetService.add(fixedAsset));
	}
	
	/**
	 * 新增资产项目方法
	 * @param response HttpServletResponse
	 * @param assetItem AssetItem:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addAssetItem")
	public void add(AssetItem assetItem,HttpServletRequest request,HttpServletResponse response){
	AjaxUtils.sendAjaxForObjectStr(
				response,fixedAssetService.addAssetItem(assetItem));		
	}
	
	/**
	 * 删除方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteFixedAsset")
	public void deleteByKey(String key,HttpServletResponse response){
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			fixedAssetService.deleteByKey(str[i]);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 进入列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListFixedAsset")
	public ModelAndView toList(){
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/account/fixedAsset/listFixedAsset");
		//查询资产项目列表
 		AssetItem ai = new AssetItem();
 		List<AssetItem> assetItem = fixedAssetService.listAssetItem(ai);
 		mv.addObject("assetItem", assetItem);
 		return mv;
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param fixedAsset FixedAsset：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listFixedAsset")
	public void listByCondition(FixedAsset fixedAsset,HttpServletRequest request,
			HttpServletResponse response, Page page){
		fixedAsset.setPage(page);	
		List<FixedAsset> list = fixedAssetService.listByCondition(fixedAsset);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 进入修改页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toEditFixedAsset")
	public ModelAndView toEdit(String key){
		ModelAndView mv = new ModelAndView(
				"WEB-INF/jsp/account/fixedAsset/editFixedAsset");
		FixedAsset entity = fixedAssetService.getByPrimaryKey(key);
		Map<String,String> fixedAsset = FormatEntity.getObjectValue(entity);
		
		Org orgEntity = new Org();
		orgEntity.setOrgClass(3);
 		mv.addObject("org", orgService.listByCondition(orgEntity));
 		
 		//查询资产项目列表
 		AssetItem ai = new AssetItem();
 		List<AssetItem> assetItem = fixedAssetService.listAssetItem(ai);
 		mv.addObject("assetItem", assetItem);
 		
 		AccountSubject entitysub = new AccountSubject();
 		entitysub.setParentId(6);
 		mv.addObject("zjSubjectList", accountSubjectService.listByCondition(entitysub));
		mv.addObject("fixedAsset",fixedAsset);
		mv.addObject("emp", employeeService.listByCondition(new Employee()));
		return mv;
	}
	
	/**
	 * 修改方法
	 * @param fixedAsset FixedAsset：实体对象
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/updateFixedAsset")
	public void update(FixedAsset fixedAsset,HttpServletRequest request,HttpServletResponse response){
		AjaxUtils.sendAjaxForObjectStr(
				response,fixedAssetService.update(fixedAsset));	
	}
	
	/**
	 * 进入添加资产项目页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toAddAssetItem")
	public ModelAndView toAddAssetItem(){
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/account/fixedAsset/addAssetItem");
		//查询资产项目列表
 		AssetItem ai = new AssetItem();
 		List<AssetItem> assetItem = fixedAssetService.listAssetItem(ai);
 		mv.addObject("assetItem", assetItem);
 		return mv;
	}
	
	/**
	 * 进入资源回收页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toRecovery")
	public ModelAndView toRecovery(){
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/account/fixedAsset/recovery");
 		return mv;
	}
	
	/**
	 * 查询待回收资源列表方法
	 * @param fixedAsset FixedAsset：实体对象（查询条件）
	 */
	@RequestMapping("/listRecovery")
	public void listRecovery(FixedAsset fixedAsset,HttpServletRequest request,
			HttpServletResponse response, Page page){
		fixedAsset.setPage(page);	
		List<FixedAsset> list = fixedAssetService.listRecovery(fixedAsset);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 回收资源方法(停用离职员工所用资源)
	 * @param fixedAsset FixedAsset：实体对象（查询条件）
	 */
	@RequestMapping("/recovery")
	public void recovery(HttpServletResponse response){
		FixedAsset fixedAsset = new FixedAsset();
		fixedAsset.setWorkNumber("");//回收的资源全部绑定到行政账号下
		fixedAsset.setPropertyState(4);//设置为停用状态
		fixedAssetService.recovery(fixedAsset);
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param assetItem AssetItem：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listAssetItem")
	public void listAssetItem(AssetItem assetItem,HttpServletRequest request,
			HttpServletResponse response, Page page){
		assetItem.setPage(page);	
		List<AssetItem> list = fixedAssetService.listAssetItem(assetItem);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
}