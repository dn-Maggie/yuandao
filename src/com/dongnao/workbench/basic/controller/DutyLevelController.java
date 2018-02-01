package com.dongnao.workbench.basic.controller;

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
import com.dongnao.workbench.basic.model.Duty;
import com.dongnao.workbench.basic.model.DutyLevel;
import com.dongnao.workbench.basic.service.DutyLevelService;
import com.dongnao.workbench.basic.service.DutyService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 描述：岗位级别表模块controller类，负责页面分发与跳转
 * 
 * @author maggie
 * @version 1.0 2016-12-20
 */
 
@Controller
@RequestMapping("dutyLevel")
public class DutyLevelController{
    @Resource
	private DutyLevelService dutyLevelService;
    @Resource
	private DutyService dutyService;
 	/**
 	* 进入新增页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddDutyLevel")
	public ModelAndView toAdd(HttpServletRequest request,HttpServletResponse response,String dutyId){
 		ModelAndView mv=new ModelAndView("WEB-INF/jsp/basic/dutyLevel/addDutyLevel");
 		String dutyid = StringUtils.defaultIfEmpty(request.getParameter("dutyId"), StringUtils.EMPTY);
 		mv.addObject("duty",dutyService.getByPrimaryKey(dutyid));
 		mv.addObject("dutys",dutyService.listByCondition(new Duty()));
		return mv;
	}
	
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowDutyLevel")
	public ModelAndView toShow(String key){
		DutyLevel entity = dutyLevelService.getByPrimaryKey(key);
		Map<String,String> dutyLevel = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/basic/dutyLevel/showDutyLevel","dutyLevel",dutyLevel );
	}
	
	/**
	 * 新增方法
	 * @param response HttpServletResponse
	 * @param dutyLevel DutyLevel:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addDutyLevel")
	public void add(DutyLevel dutyLevel,HttpServletRequest request,HttpServletResponse response){
	dutyLevel.setId(Utils.generateUniqueID());
	AjaxUtils.sendAjaxForObjectStr(
				response,dutyLevelService.add(dutyLevel));		
	}
	
	/**
	 * 删除方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteDutyLevel")
	public void deleteByKey(String key,HttpServletResponse response){
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			dutyLevelService.deleteByKey(str[i]);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 进入列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListDutyLevel")
	public ModelAndView toList(){
		return new ModelAndView("WEB-INF/jsp/basic/dutyLevel/listDutyLevel");
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param dutyLevel DutyLevel：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listDutyLevel")
	public void listByCondition(DutyLevel dutyLevel,HttpServletRequest request,
			HttpServletResponse response, Page page){
		dutyLevel.setPage(page);	
		List<DutyLevel> list = dutyLevelService.listByCondition(dutyLevel);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 进入修改页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toEditDutyLevel")
	public ModelAndView toEdit(String key){
		DutyLevel entity = dutyLevelService.getByPrimaryKey(key);
		Map<String,String> dutyLevel = FormatEntity.getObjectValue(entity);
		
		return new ModelAndView("WEB-INF/jsp/basic/dutyLevel/editDutyLevel","dutyLevel",dutyLevel );
	}
	
	/**
	 * 修改方法
	 * @param dutyLevel DutyLevel：实体对象
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/updateDutyLevel")
	public void update(DutyLevel dutyLevel,HttpServletRequest request,HttpServletResponse response){
		AjaxUtils.sendAjaxForObjectStr(
				response,dutyLevelService.update(dutyLevel));	
	}
	
}