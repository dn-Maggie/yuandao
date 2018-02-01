package com.dongnao.workbench.system.controller;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.dongnao.workbench.system.model.DictInfo;
import com.dongnao.workbench.system.model.DictType;
import com.dongnao.workbench.system.service.DictInfoService;


/**
 * 描述：数据字典信息模块controller类，负责页面分发与跳转
 * @author zhou.zheng
 *
 * @version 1.0 2013-11-04
 */
 
@Controller
@RequestMapping("dictInfo")
public class DictInfoController{

	private DictInfoService dictInfoService;
	
	/**
	 * 设置service
	 * @param dictInfoService DictInfoService
	 */
	@Autowired
	public void setDictInfoService(DictInfoService dictInfoService) {
		this.dictInfoService = dictInfoService;
	}
	
 	/**
 	* 进入新增页面
 	* @param typeCode 
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddDictInfo")
	public ModelAndView toAdd(String typeCode){
 		ModelAndView mv = new ModelAndView("WEB-INF/jsp/system/dict/addDictInfo");
 		Map<String, Object> map = mv.getModelMap();
 		map.put("typeCode", typeCode);
		return mv;
	}
	
	/**
	 * 新增方法
	 * @param request 请求
	 * @param response 响应
	 * @param dictInfo 实体类
	 */
	@RequestMapping("/addDictInfo")
	public void add(DictInfo dictInfo,HttpServletRequest request,HttpServletResponse response){
		Map<String, String> map = new HashMap<String, String>();
		int result = dictInfoService.add(dictInfo, request);
		switch(result){
			case -1:
				map.put("msg",Utils.getI18n("Validate.code.exist", null));
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
	 * @param key 多个由“，”分割开的id字符串
	 */
	@RequestMapping("/deleteDictInfo")
	public void deleteByKey(String key, HttpServletRequest request, HttpServletResponse response){
		Map<String, String> map = new HashMap<String, String>();
		int result = dictInfoService.deleteByKey(key, request);
		if(result > 0){
			map.put("msg",Utils.getI18n("delect.success", null));
			map.put("rs", Constant.FLAG_SUCCESS);
		} else {
			map.put("msg", Utils.getI18n("oper.failed", null));
			map.put("rs", Constant.FLAG_FAILURE);
		}
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 进入列表页面
	 * @param response HttpServletResponse
	 * @return ModelAndView
	 */
	@RequestMapping("/toListDictInfo")
	public ModelAndView toList(HttpServletResponse response){
		return new ModelAndView("WEB-INF/jsp/system/dict/listDictInfo");
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param dictInfo 实体对象（查询条件）
	 * @param dictTypeCode 类型编码
	 * @param request 请求
	 * @param response 响应
	 * @param page 分页对象
	 */
	@RequestMapping("/listDictInfo")
	public void listByCondition(DictInfo dictInfo, String dictTypeCode, HttpServletRequest request,
			HttpServletResponse response, Page page){
		dictInfo.setDictTypeCode(dictTypeCode);
		dictInfo.setSortBy(page.getOrder());
		dictInfo.setSortFild(page.getOrderFields());
		dictInfo.setPage(page);
		List<DictInfo> list = dictInfoService.listByCondition(dictInfo);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 进入修改页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toUpdateDictInfo")
	public ModelAndView toUpdate(String key){
		DictInfo entity = dictInfoService.getByPrimaryKey(key);
		Map<String,String> dictInfo = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/system/dict/updateDictInfo","dictInfo",dictInfo );
	}
	
	/**
	 * 修改方法
	 * @param dictInfo 实体对象
	 * @param request 请求
	 * @param response 响应
	 */	
	@RequestMapping("/updateDictInfo")
	public void update(DictInfo dictInfo, HttpServletRequest request, HttpServletResponse response){
		Map<String, String> map = new HashMap<String, String>();
		int result = dictInfoService.update(dictInfo, request);
		switch(result){
		case -1:
			map.put("msg", Utils.getI18n("Validate.code.exist", null));
			map.put("rs", Constant.FLAG_FAILURE);
			break;
		case 0:
			map.put("msg",Utils.getI18n("oper.failed", null));
			map.put("rs", Constant.FLAG_FAILURE);
			break;
		default:
			map.put("msg",Utils.getI18n("update.success", null));
			map.put("rs", Constant.FLAG_SUCCESS);
			break;
	}
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowDictInfo")
	public ModelAndView toShow(String key){
		DictInfo entity = dictInfoService.getByPrimaryKey(key);
		Map<String,String> dictInfo = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/system/dict/showDictInfo","dictInfo",dictInfo );
	}
	
	
	/**
	 * 根据类别查询字典信息为页面下拉框提供字典信息
	 * @param type String 类别编码
	 * @param response HttpServletResponse
	 */
	@RequestMapping("/getDictInfoByType")
	public void getDictInfoByType(HttpServletResponse response,String type){
		DictType dictType = new DictType();
		dictType.setIsActive(Constant.ISDELETE_FALSE);
		List<String> list = dictInfoService.getDictInfoByType(type);
		AjaxUtils.sendAjaxForSelect(response, list);
	}

	
	/**
	 * 根据字典类型编码查询字典数据
	 * @param dictTypeCode String
	 * @param response HttpServletResponse
	 */
	@RequestMapping("/getDictByTypeCode")
	public void getDictByTypeCode(String dictTypeCode,HttpServletResponse response){
		AjaxUtils.sendAjaxForObject(response, Utils.getDictInfo(dictTypeCode, true));	
	}
	
	/**
	 * 根据字典类型编码查询字典数据(radio & checkbox )
	 * @param dictTypeCode String
	 * @param response HttpServletResponse
	 */
	@RequestMapping("/getDictForRadio")
	public void getDictForRadio(String dictTypeCode,HttpServletResponse response){
		/*List<String> list = dictInfoService.getDictForRadio(dictTypeCode);
		AjaxUtils.sendAjaxForSelect(response, list);*/	
		AjaxUtils.sendAjaxForObject(response, Utils.getDictInfo(dictTypeCode, false));	
	}
	
	/**
	 * 获取入学年份下拉框
	 */
	@RequestMapping("/getAdmissionYear")
	public void getAdmissionYear(HttpServletResponse response){
		List<String> list = new ArrayList<String>();
		 Calendar cal = Calendar.getInstance();
		 int year = cal.get(Calendar.YEAR);
		 String option = "";
		 for(int i=0;i<6;i++){
			 option = "\"name\":\""+(year-i)+"级"+"\",\"value\":\""+(year-i)+"\""; 
				list.add(option);
		 }
		 AjaxUtils.sendAjaxForSelect(response, list);	
	}
}