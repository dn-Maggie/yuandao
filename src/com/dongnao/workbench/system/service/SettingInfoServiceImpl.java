package com.dongnao.workbench.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.dongnao.workbench.common.util.SpringInit;
import com.dongnao.workbench.common.util.StringUtil;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.system.dao.SettingInfoMapper;
import com.dongnao.workbench.system.model.SettingInfo;
 
/**
 * 描述：settingInfo模块service接口实现类，实现service接口方法
 * @author wff
 * @version 1.0  2013-11-21
 */
@Service("settingInfoService")
public class SettingInfoServiceImpl implements SettingInfoService{
	private SettingInfoMapper settingInfoMapper;
	
	/**
	 * 注入 SettingInfoMapper 
	 * @param settingInfoMapper SettingInfoMapper
	 */
	@Autowired
	public void setSettingInfoMapper(SettingInfoMapper settingInfoMapper) {
		this.settingInfoMapper = settingInfoMapper;
	}
 
	/**
	 * 新增方法
	 * @param settingInfo 实体类
	 */	
	public void add(SettingInfo settingInfo){
		//增加系统配置时，同步在上下文中增加配置的名称和值
		final WebApplicationContext app = (WebApplicationContext) SpringInit.getApplicationContext();
		app.getServletContext().setAttribute(settingInfo.getVariableName(), settingInfo.getVariableValue());
		settingInfoMapper.add(settingInfo);
	}
	
	/**
	 * 根据主键查找实体方法
	 * @param id 实体主键
	 * @return 实体对象
	 */
	public SettingInfo getByPrimaryKey(String id){
		return settingInfoMapper.getByPrimaryKey(id);
	}
	
	/**
	 * 删除方法
	 * @param id 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String id){
		settingInfoMapper.deleteByKey(id);
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param settingInfo 实体对象（查询条件）
	 * @return 实体对象的list
	 */
	public List<SettingInfo> listByCondition(SettingInfo settingInfo){
		return settingInfoMapper.listByCondition(settingInfo);
	}
	
	/**
	 * 修改方法
	 * @param settingInfo 实体对象
	 */	
	public void update(SettingInfo settingInfo){
		//更新系统配置时，同步更新上下文保存的值
		Utils.setConfigValue(settingInfo.getVariableName(), settingInfo.getVariableValue());
		settingInfoMapper.update(settingInfo);
	}
	
	/**
	 * 新增操作时，判断功能名称是否已存在
	 * @param variableName 功能名称
	 * @return boolean 
	 */	
	public boolean getCheckByName(String variableName){
		List<SettingInfo> settingInfos = settingInfoMapper.getCheckByName(variableName);
		if(settingInfos.size() > 0 ){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取系统配置集合
	 * @return List<SettingInfo>
	 */
	public List<SettingInfo> getSettingValue(){
		return settingInfoMapper.getSettingValue();
	}
	
	/**
	 * 根据用户id获取当前用户设置的session过期时间
	 * @param userId 用户id
	 * @return session过期时间
	 */
	public String getSessionValueByUserId(String userId){
		return settingInfoMapper.getSessionValueByUserId(userId);
	}
	/**
	 * 用户自定义session失效时间
	 * @param sessionValue session值
	 * @param userId 用户id
	 */
	public void updateSessionValueByName(String sessionValue, String userId){
		String sessionTime = settingInfoMapper.getSessionValueByUserId(userId);
		//如果当前用户是第一次设置session时间
		if(StringUtil.isEmptyOrNull(sessionTime)){
			Map<String,String> sessionMap = new HashMap<String, String>();
			sessionMap.put("id", Utils.generateUniqueID());
			sessionMap.put("userId", userId);
			sessionMap.put("lostTime", sessionValue);
			settingInfoMapper.addSessionInfo(sessionMap);
		}else{
			//更新session失效时间
			Map<String, String> map = new HashMap<String, String>();
			map.put("userId", userId);
			map.put("lostTime", sessionValue);
			settingInfoMapper.updateSessionValueByName(map);
		}
	}
}