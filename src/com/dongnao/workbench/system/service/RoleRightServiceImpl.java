package com.dongnao.workbench.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongnao.workbench.basic.model.UserInfo;
import com.dongnao.workbench.common.Constant;
import com.dongnao.workbench.common.util.StringUtil;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.system.dao.RoleRightMapper;
import com.dongnao.workbench.system.model.RoleRight;

 
/**
 * 描述：roleRight模块service接口实现类，实现service接口方法
 * @author joan.xiong
 * @version 1.0 2016-03-11
 */
@Service("roleRightService")
public class RoleRightServiceImpl implements RoleRightService{

	
	private RoleRightMapper roleRightMapper;
	/**
	 * 设置数据库操作mapper
	 * @param roleRightMapper RoleRightMapper
	 */
	@Autowired
	public void setRoleRightMapper(RoleRightMapper roleRightMapper) {
		this.roleRightMapper = roleRightMapper;
	}
 
	
	/**
	 * 新增方法
	 * @param roleRight RoleRight:实体类
	 */	
	public void add(RoleRight roleRight){
		roleRightMapper.add(roleRight);
	}
	
	/**
	 * 根据主键查找实体方法
	 * @param key String：实体主键
	 * @return 实体对象
	 */
	public RoleRight getByPrimaryKey(String key){
		return roleRightMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		roleRightMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param roleRight RoleRight：实体对象（查询条件）
	 * @return List<RoleRight> 实体对象的list
	 */
	public List<RoleRight> listByCondition(RoleRight roleRight){
		return roleRightMapper.listByCondition(roleRight);
	}
	
	/**
	 * 修改方法
	 * @param roleRight RoleRight：实体对象
	 */	
	public void update(RoleRight roleRight){
		roleRightMapper.update(roleRight);
	}

	@Override
	public List<String> getAllRightUrl() {
		List<HashMap<String,String>> resultList = roleRightMapper.getAllRightUrl();
		List<String> urlList = new ArrayList<String>();
		for(HashMap<String,String> result:resultList){
			urlList.add(result.get("LINKURL"));
		}
		return urlList;
	}

	@Override
	public boolean checkAndSetRight(HttpServletRequest request) {
		boolean rightFlag = false;
		//取得请求路径
		String path = request.getRequestURI();
		path = path.replace(request.getContextPath(), "");
		//获取当前登陆人信息
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.LOGIN_USER_KEY);
		//验证用户权限
		if (userInfo != null) {
			List<HashMap<String, String>> resultList = null;
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("reqUrl", path);
			boolean isAdmin=false;
			if(userInfo.getUserAccount().equals(Constant.SUPER_ADMIN)){
				resultList = roleRightMapper.checkRightForAdmin(params);
				isAdmin=true;
			}else{
				params.put("userId", userInfo.getId().toString());
				resultList = roleRightMapper.checkRightForUser(params);
			}
			request.setAttribute("isAdmin", isAdmin);
			if(resultList!=null && resultList.size()>0){
				rightFlag = true;
				String actCode = null;
				for(HashMap<String, String> result:resultList){
					actCode = result.get("ACT_CODE");
					if(!StringUtil.isEmptyOrNull(actCode)){
						request.setAttribute(actCode, true);
					}
				}
			}
		}
		return rightFlag;
	}
	
}