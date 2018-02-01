package com.dongnao.workbench.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import com.dongnao.workbench.common.Constant;
import com.dongnao.workbench.system.model.ModuleLocation;
import com.dongnao.workbench.system.model.SettingInfo;
import com.dongnao.workbench.system.service.DictInfoService;
import com.dongnao.workbench.system.service.ModuleService;
import com.dongnao.workbench.system.service.RoleRightService;
import com.dongnao.workbench.system.service.SettingInfoService;


/**
 * 描述：Init Servlet，利用servlet，在系统启动时，对系统参数进行初始化
 * @author joan.xiong
 * @version 1.0 2013-12-17
 */
public class InitServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    
    @Override
    public void init(){
    	  
     	//初始化需要进行页面页面定位的URL列表
    	ModuleService moduleService = (ModuleService)Utils.getBean("moduleService");
    	List<ModuleLocation> moduleLocationList = moduleService.listModuleLocationList();
    	if(moduleLocationList!=null){
    		Map<String,ModuleLocation>  midMap=new HashMap<String,ModuleLocation>();
    		Map<String,ModuleLocation>  urlMap=new HashMap<String,ModuleLocation>();
    		for(ModuleLocation ml:moduleLocationList){
    			midMap.put(ml.getMid(), ml);
    			if(ml.getMurl()!=null){
    				urlMap.put(ml.getMurl(), ml);
    			}

    		}
    		this.getServletContext().setAttribute(Constant.MODULE_LOCATION_MID_MAP, midMap);
    		this.getServletContext().setAttribute(Constant.MODULE_LOCATION_URL_MAP, urlMap);
    	}
    	//初始化需要进行页面权限控制的URL列表
    	RoleRightService roleRightService = (RoleRightService)Utils.getBean("roleRightService");
    	List<String> allRightUrl = roleRightService.getAllRightUrl();
    	this.getServletContext().setAttribute(Constant.APP_RIGHT_KEY, allRightUrl);
    	
    	//初始化系统配置参数
    	SettingInfoService settingInfoService = (SettingInfoService) Utils.getBean("settingInfoService");
    	List<SettingInfo> settingInfos = settingInfoService.getSettingValue();
    	for(SettingInfo s:settingInfos){
    		this.getServletContext().setAttribute(s.getVariableName(), s.getVariableValue());
    	}
    	//初始化字典信息放入上下文
    	DictInfoService dictInfoService = (DictInfoService)Utils.getBean("dictInfoService");
    	Map<String,String> dictMap = dictInfoService.getAllDictInfo();
    	this.getServletContext().setAttribute(Constant.APP_DICT_KEY, dictMap);
    	
    }
}

