package com.dongnao.workbench.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongnao.workbench.common.Constant;
import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.common.util.StringUtil;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.system.model.SettingInfo;
import com.dongnao.workbench.system.service.SettingInfoService;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述：系统配置管理(settingInfo)模块controller类，负责页面分发与跳转
 * 主要实现：整个系统运行的全局系统参数配置，比如：上传文件根路径，上传文件大小，session失效时间等等。
 * @author wff
 * @version 1.0  2013-11-21
 */
 
@Controller
@RequestMapping("settingInfo")
public class SettingInfoController{
	private SettingInfoService settingInfoService;
	
	/**
	 * 设置service
	 * @param settingInfoService SettingInfoService
	 */
	@Autowired
	public void setSettingInfoService(SettingInfoService settingInfoService) {
		this.settingInfoService = settingInfoService;
	}
 
 	/**
 	* 进入新增页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddSettingInfo")
	public ModelAndView toAdd(){
		return new ModelAndView("WEB-INF/jsp/system/settingInfo/addSettingInfo");
	}
	
 	/**
 	 * 进入设置session失效时间页面
 	 * @param request HttpServletRequest
 	 * @return ModelAndView 返回到设置session失效时间页面
 	 */
 	@RequestMapping("/toSetSessionTime")
 	public ModelAndView toSetSessionTime(HttpServletRequest request){
 		String sessionValue = settingInfoService.getSessionValueByUserId(Utils.getLoginUser(request));
 		if(StringUtil.isEmptyOrNull(sessionValue)){
			sessionValue = (String) request.getSession().getServletContext()
					.getAttribute(Constant.DEFAULT_SESSION_TIME);
 		}
 		ModelAndView view = new ModelAndView();
 		view.addObject("sessionValue", sessionValue);
 		view.setViewName("WEB-INF/jsp/system/settingInfo/setSessionTime");
 		return view;
 	}
 	
	/**
	 * 进入查看页面方法
	 * @param id String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowSettingInfo")
	public ModelAndView toShow(String id){
		SettingInfo entity = settingInfoService.getByPrimaryKey(id);
		Map<String,String> settingInfo = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/system/settingInfo/showSettingInfo","settingInfo",settingInfo );
	}
	
	/**
	 * 新增方法
	 * @param response HttpServletResponse
	 * @param settingInfo SettingInfo:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addSettingInfo")
	public void add(SettingInfo settingInfo,HttpServletResponse response){
		settingInfo.setId(Utils.generateUniqueID());
		settingInfoService.add(settingInfo);
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", Utils.getI18n("settingInfo.success", null));
		AjaxUtils.sendAjaxForMap(response, map);
		
	}
	
	/**
	 * 删除方法
	 * @param response HttpServletResponse
	 * @param ids String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteSettingInfo")
	public void deleteByKey(String ids,HttpServletResponse response){
		String[] str = ids.split(",");
		for(int i=0;i<str.length;i++){
			settingInfoService.deleteByKey(str[i]);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", Utils.getI18n("settingInfo.success", null));
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 进入列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListSettingInfo")
	public ModelAndView toList(){
		return new ModelAndView("WEB-INF/jsp/system/settingInfo/listSettingInfo");
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param settingInfo SettingInfo：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listSettingInfo")
	public void listByCondition(SettingInfo settingInfo,HttpServletRequest request,
			HttpServletResponse response, Page page){
		settingInfo.setOrderBy(page.getOrderFields());
		settingInfo.setOrderType(page.getOrder());
		settingInfo.setPage(page);
		List<SettingInfo> list = settingInfoService.listByCondition(settingInfo);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 进入修改页面方法
	 * @param id String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toUpdateSettingInfo")
	public ModelAndView toUpdate(String id){
		SettingInfo entity = settingInfoService.getByPrimaryKey(id);
		Map<String,String> settingInfo = FormatEntity.getObjectValue(entity);
		
		return new ModelAndView("WEB-INF/jsp/system/settingInfo/updateSettingInfo","settingInfo",settingInfo );
	}
	
	/**
	 * 修改方法
	 * @param settingInfo SettingInfo：实体对象
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/updateSettingInfo")
	public void update(SettingInfo settingInfo,HttpServletResponse response){
		settingInfoService.update(settingInfo);
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", Utils.getI18n("settingInfo.success", null));
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 新增操作时，判断输入的功能名称是否已存在
	 * @param variableName 功能名称
	 * @param response HttpServletResponse
	 */
	@RequestMapping("/getCheckByName")
	public void getCheckByName(String variableName,HttpServletResponse response){
		boolean flag = settingInfoService.getCheckByName(variableName);
		JSONObject result = new JSONObject();
		result.put("flag", flag);
		AjaxUtils.sendAjaxForMap(response, result);
	}
	
	/**
	 * 更新用户的session时间
	 * @param sessionValue session时间
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	@RequestMapping("/updateSessionTime")
	public void updateSessionTime(String sessionValue,HttpServletRequest request,HttpServletResponse response){
		String userId = Utils.getLoginUser(request);
		settingInfoService.updateSessionValueByName(sessionValue, userId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", Utils.getI18n("settingInfo.success", null));
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
}