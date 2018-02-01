package com.dongnao.workbench.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dongnao.workbench.common.Constant;
import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.system.model.DictType;
import com.dongnao.workbench.system.service.DictTypeService;
/**
 * 描述：数据字典类别模块controller类，负责页面分发与跳转
 * @author zhou.zheng
 *
 * @version 1.0 2013-11-07
 */
 
@Controller
@RequestMapping("dictType")
public class DictTypeController{ 

	
	private DictTypeService dictTypeService;
	
	/**
	 * 设置service
	 * @param dictTypeService DictTypeService
	 */
	@Autowired
	public void setDictTypeService(DictTypeService dictTypeService) {
		this.dictTypeService = dictTypeService;
	}
	
	/**
 	* 进入维护页面
 	* @return ModelAndView 进入维护页面
 	*/
 	@RequestMapping("/toListDict")
	public ModelAndView toListDict(){
		return new ModelAndView("WEB-INF/jsp/system/dict/listDict");
	}
 	
	/**
	 * 初始化树
	 * @param typeName 
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	@RequestMapping("/initDictTypeTree")
	public void initDictTypeTree(String typeName, HttpServletRequest request,
			HttpServletResponse response){
		String jsonData = dictTypeService.initDictTypeTree(typeName);
		AjaxUtils.sendAjaxForObject(response, jsonData);
	}
 
 	/**
 	* 进入新增页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddDictType")
	public ModelAndView toAdd(){
		return new ModelAndView("WEB-INF/jsp/system/dict/addDictType");
	}
	
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowDictType")
	public ModelAndView toShow(String key){
		DictType entity = dictTypeService.getByPrimaryKey(key);
		Map<String,String> dictType = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/system/dict/showDictType","dictType",dictType );
	}
	
	/**
	 * 新增方法
	 * @param request 请求
	 * @param response 响应
	 * @param dictType 实体类
	 */
	@RequestMapping("/addDictType")
	public void add(DictType dictType, HttpServletRequest request, HttpServletResponse response){
		Map<String, String> map = new HashMap<String, String>();
		int result = dictTypeService.add(dictType, request);
		switch(result){
			case -1:
				map.put("msg", Utils.getI18n("Validate.code.exist", null));
				map.put("rs", Constant.FLAG_FAILURE);
				break;
			case 0:
				map.put("msg", Utils.getI18n("oper.failed", null));
				map.put("rs", Constant.FLAG_FAILURE);
				break;
			default:
				map.put("msg", Utils.getI18n("add.success", null));
				map.put("rs", Constant.FLAG_SUCCESS);
				break;
		}
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 删除方法
	 * @param request 请求
	 * @param response 响应
	 * @param typeCode 编码
	 */
	@RequestMapping("/deleteDictType")
	public void deleteByKey(String typeCode, HttpServletRequest request, HttpServletResponse response){
		Map<String, String> map = new HashMap<String, String>();
		int result = dictTypeService.deleteByTypeCode(typeCode, request);
		if (result>0) {
			map.put("msg", Utils.getI18n("delete.success", null));
			map.put("rs", Constant.FLAG_SUCCESS);
		}else if (result==-1) {
			map.put("msg", "字典类型下有数据,不能删除.");
			map.put("rs", Constant.FLAG_FAILURE);
		}else {
			map.put("msg", Utils.getI18n("oper.failed", null));
			map.put("rs", Constant.FLAG_FAILURE);
		}
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 进入列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListDictType")
	public ModelAndView toList(){
		return new ModelAndView("WEB-INF/jsp/system/dict/listDictType");
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param dictType DictType：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listDictType")
	public void listByCondition(DictType dictType,HttpServletRequest request,
			HttpServletResponse response, Page page){ 
		dictType.setSortBy(page.getOrder());
		dictType.setSortFild(page.getOrderFields());
		dictType.setPage(page);
		List<DictType> list = dictTypeService.listByCondition(dictType);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 进入修改页面方法
	 * @param typeCode 编码
	 * @return ModelAndView 
	 */	
	@RequestMapping("/toUpdateDictType")
	public ModelAndView toUpdate(String typeCode){
		DictType entity = dictTypeService.getByTypeCode(typeCode);
		Map<String,String> dictType = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/system/dict/updateDictType", "dictType", dictType );
	}
	
	/**
	 * 修改方法
	 * @param dictType 实体对象
	 * @param response 响应
	 * @param request 请求
	 */	
	@RequestMapping("/updateDictType")
	public void update(DictType dictType, HttpServletResponse response, HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();
		int result = dictTypeService.update(dictType, request);
		if(result > 0){
			map.put("msg", Utils.getI18n("update.success", null));
			map.put("rs", Constant.FLAG_SUCCESS);
		} else {
			map.put("msg", Utils.getI18n("oper.failed", null));
			map.put("rs", Constant.FLAG_FAILURE);
		}
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 为页面下拉框提供字典类别信息
	 * @param typeCode 字典类型编码
	 * @param response HttpServletResponse
	 */
	@RequestMapping("/getDictTypeForSelect")
	public void getDictTypeForSelect(String typeCode, HttpServletResponse response){
		DictType dictType = new DictType();
		dictType.setIsActive(Constant.ISDELETE_FALSE);
		dictType.setTypeCode(typeCode);
		List<String> list = dictTypeService.getDictTypeForSelect(dictType);
		AjaxUtils.sendAjaxForSelect(response, list);
	}
	
}