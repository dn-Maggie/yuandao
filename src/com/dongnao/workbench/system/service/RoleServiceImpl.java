package com.dongnao.workbench.system.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.dongnao.workbench.common.Constant;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.util.DateUtil;
import com.dongnao.workbench.common.util.StringUtil;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.system.dao.ModuleResMapper;
import com.dongnao.workbench.system.dao.PersonroleMapper;
import com.dongnao.workbench.system.dao.ResActMapper;
import com.dongnao.workbench.system.dao.RoleMapper;
import com.dongnao.workbench.system.dao.RoleRightMapper;
import com.dongnao.workbench.system.model.ModuleRes;
import com.dongnao.workbench.system.model.Personrole;
import com.dongnao.workbench.system.model.ResAct;
import com.dongnao.workbench.system.model.Role;
import com.dongnao.workbench.system.model.RoleRight;
 
/**
 * 描述：角色管理模块service接口实现类，实现service接口方法
 *
 * @author yang.bin
 * @version 1.0 2016-03-06
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService{

	private RoleMapper roleMapper;
	private ModuleResMapper moduleResMapper;
	private ResActMapper resActMapper;
	private RoleRightMapper roleRightMapper;
	private PersonroleMapper personroleMapper;
	
	
	/**
	 * 设置数据库操作mapper
	 * @param personroleMapper PersonroleMapper
	 */
	@Autowired
	public void setPersonroleMapper(PersonroleMapper personroleMapper) {
		this.personroleMapper = personroleMapper;
	}
	/**
	 * 设置数据库操作mapper
	 * @param roleMapper RoleMapper
	 */
	@Autowired
	public void setRoleMapper(RoleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}
	
	/**
	 * 设置数据库操作mapper
	 * @param moduleResMapper ModuleResMapper
	 */
	@Autowired
	public void setModuleResMapper(ModuleResMapper moduleResMapper) {
		this.moduleResMapper = moduleResMapper;
	}
	/**
	 * 设置数据库操作mapper
	 * @param resActMapper ResActMapper
	 */
	@Autowired
	public void setResActMapper(ResActMapper resActMapper) {
		this.resActMapper = resActMapper;
	}
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
	 * @param role 实体类
	 * @return 是否成功
	 */	
	public ResultMessage add(Role role){
		ResultMessage rm=new ResultMessage();
		String vName =roleMapper.verifyNameAdd(role.getRoleName());
		if (StringUtil.isNotBlank(vName)) {
			rm.setStatus(Constant.RESULTMESSAGE_STATUS_0);
			rm.setMessage("角色名称不能为空");
			return rm;
		}
		String vCode=roleMapper.verifyCodeAdd(role.getRoleCode());
		if (StringUtil.isNotBlank(vCode)) {
			rm.setStatus(Constant.RESULTMESSAGE_STATUS_0);
			rm.setMessage("角色编码不能为空");
			return rm;
		}
		role.setCreateTime(DateUtil.nowSqlTimestamp());
		role.setUpdateTime(DateUtil.nowSqlTimestamp());
		roleMapper.add(role);
		rm.setStatus(Constant.RESULTMESSAGE_STATUS_1);
		return rm;
	
	}
	
	/**
	 * 根据主键查找实体方法
	 * @param key String 实体主键
	 * @return Role 实体对象
	 */
	public Role getByPrimaryKey(String key){
		return roleMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除方法
	 * @param key 多个由“，”分割开的id字符串
	 * @param updaterId 更新人UUID
	 */
	public void deleteByKey(String key,String updaterId){
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			roleRightMapper.deleteByRoleId(str[i]);
			roleMapper.deleteByKey(str[i]);
		}
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param role Role 实体对象（查询条件）
	 * @return List<Role> 实体对象的list
	 */
	public List<Role> listByCondition(Role role){
		return roleMapper.listByCondition(role);
	}
	
	/**
	 * 修改方法
	 * @param newRole Role 实体对象
	 */	
	public ResultMessage update(Role newRole){
		ResultMessage rm=new ResultMessage();
		String vName =roleMapper.verifyNameUpdate(newRole);
		if (StringUtil.isNotBlank(vName)) {
			rm.setStatus(Constant.RESULTMESSAGE_STATUS_0);
			rm.setMessage("角色名称不能为空");
			return rm;
		}
		String vCode=roleMapper.verifyCodeUpdate(newRole);
		if (StringUtil.isNotBlank(vCode)) {
			rm.setStatus(Constant.RESULTMESSAGE_STATUS_0);
			rm.setMessage("角色编码不能为空");
			return rm;
		}
		newRole.setUpdateTime(DateUtil.nowSqlTimestamp());
		roleMapper.update(newRole);
		rm.setStatus(Constant.RESULTMESSAGE_STATUS_1);
		return rm;
		
			
	}

	/**
	 * 获取菜单树所有菜单JSON字符串
	 * @param resType 资源类型
	 * @return String JSON字符串
	 */
	public String getAllmenuTreeJson(String resType){
		JSONArray resTreeArr = new JSONArray();
		//根节点数据
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", "0");
		jsonObj.put("name", "系统菜单");
		jsonObj.put("open", "true");
		resTreeArr.add(jsonObj);
		
		//获取所有资源列表
		ModuleRes moduleRes = new ModuleRes();
		moduleRes.setStatus(Constant.ENABLE_VALUE);
		moduleRes.setNeedright(Constant.ENABLE_VALUE);
		List<ModuleRes> listModuleRes =new ArrayList<ModuleRes>();
		if(!"".equals(resType)){
			moduleRes.setResType(resType);
			listModuleRes = moduleResMapper.listByCondition(moduleRes);
			moduleRes.setResType(Constant.PUBLIC_RESOURCES_RT002);
			listModuleRes.addAll(moduleResMapper.listByCondition(moduleRes));
		}else{
			listModuleRes = moduleResMapper.listWithoutSysRes(moduleRes);
		}
		
		//获取所有操作列表
		ResAct resAct=new ResAct();
		resAct.setStates(Constant.ENABLE_VALUE);
		resAct.setNeedRight(Constant.ENABLE_VALUE);
		List<ResAct> listResact = resActMapper.listByCondition(resAct);
		String resuuid =null;
		for(ModuleRes mr:listModuleRes){
			jsonObj = new JSONObject();
			resuuid = mr.getId();
			jsonObj.put("id", resuuid);
			jsonObj.put("pId", "0");
			jsonObj.put("name", mr.getResourceName()+"_"+mr.getResTypeName());
			jsonObj.put("type", "2");
			jsonObj.put("open", "true");
			resTreeArr.add(jsonObj);
		}
		for(ResAct ra:listResact){
			for(int i=0;i<listModuleRes.size();i++){
				if(ra.getResuuId().equals(listModuleRes.get(i).getId())){
					jsonObj = new JSONObject();
					jsonObj.put("id", ra.getId());
					jsonObj.put("pId", ra.getResuuId());
					jsonObj.put("name", ra.getActName());
					jsonObj.put("type", "3");
					jsonObj.put("actcode", ra.getActCode());
					resTreeArr.add(jsonObj);
				}
			}
		}
		return resTreeArr.toString();
	}
	/**
	 * 根据角色ID获取已有权限树
	 * @param roleRid 角色ID
	 * @return json字符串
	 */
	public String getMenuTreeJsonByRole(String roleRid){
		List<Map<String,Object>> resultList=roleMapper.getForRoleTree(roleRid);
		List<ModuleRes> listModuleRes =new ArrayList<ModuleRes>();
		List<ResAct> listResact = new ArrayList<ResAct>();
		Map<String,Object> obj;
		String actId = null;
		String resuuid = "";
		if(resultList !=null && resultList.size()>0){
			for(int i=0,s=resultList.size();i<s;i++){
				obj = resultList.get(i);
				if(!resuuid.equals(obj.get("RESUUID").toString())){
					resuuid = obj.get("RESUUID").toString();
					ModuleRes res=moduleResMapper.getByPrimaryKey(resuuid);
					if(res!=null){
						listModuleRes.add(res);
					}
				}
				if(obj.get("ACTID") != null){
					actId = obj.get("ACTID").toString();
					ResAct act=resActMapper.getByPrimaryKey(actId);
					if(act!=null){
						listResact.add(act);
					}
				}
			}
		}
		
		JSONArray resTreeArr = new JSONArray();
		//根节点数据
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", "0");
		jsonObj.put("name", "系统菜单");
		jsonObj.put("open", "true");
		resTreeArr.add(jsonObj);
		for(ModuleRes mr:listModuleRes){
			jsonObj = new JSONObject();
			jsonObj.put("id", mr.getId());
			jsonObj.put("pId", "0");
			jsonObj.put("name", mr.getResourceName());
			jsonObj.put("type", "2");
			jsonObj.put("open", "true");
			resTreeArr.add(jsonObj);
		}
		for(ResAct ra:listResact){
			jsonObj = new JSONObject();
			jsonObj.put("id", ra.getId());
			jsonObj.put("pId", ra.getResuuId());
			jsonObj.put("name", ra.getActName());
			jsonObj.put("type", "3");
			jsonObj.put("actcode", ra.getActCode());
			resTreeArr.add(jsonObj);
		}
		return resTreeArr.toString();
	}
	/**
	 * 根据角色ID，获取菜单树数据
	 * @param roleRid String:角色ID
	 * @return String JSON字符串
	 */
	public String getMenuTreeIdsByRoleId(String roleRid) {
		StringBuffer roleTreeSb=new StringBuffer();
		List<Map<String,Object>> resultList=roleMapper.getForRoleTree(roleRid);
		
		Map<String,Object> obj;
		Object actId = null;
		String resuuid = "";
		if(resultList !=null && resultList.size()>0){
			for(int i=0,s=resultList.size();i<s;i++){
				obj = resultList.get(i);
				if(!resuuid.equals(obj.get("RESUUID").toString())){
					resuuid = obj.get("RESUUID").toString();
					if(roleTreeSb.length()<1){
						roleTreeSb.append(obj.get("RESUUID"));
					}else{
						roleTreeSb.append(",").append(obj.get("RESUUID"));
					}
				}
				actId = obj.get("ACTID");
				if(actId != null){
					roleTreeSb.append(",").append(actId);
				}
			}
		}
		return roleTreeSb.toString();
	}
	
	/**
	 * 保存角色权限关联
	 * @param roleRid 角色ID
	 * @param ids 操作权限ID，多个ID以","分隔
	 * @return null
	 */
	
	public String saveRoleRight(String roleRid, String ids) {
        roleRightMapper.deleteByRoleId(roleRid);
		Timestamp dtNow = new Timestamp(System.currentTimeMillis());
		RoleRight roleright = null;
		if(null!=ids && !"".equals(ids)){
			JSONArray resArr = JSONArray.fromObject(ids);
			JSONObject resObj = null;
			for(int i=0,s=resArr.size();i<s;i++){
				resObj = resArr.getJSONObject(i);
				roleright=new RoleRight();
				roleright.setId(Utils.generateUniqueID());
				roleright.setRoleid(roleRid);
				roleright.setResuuid(resObj.getString("resuuid"));
				roleright.setActcodes(resObj.getString("actCodes"));
				roleright.setCreateTime(dtNow);
				roleRightMapper.add(roleright);
			}
		}
		return null;
	}
	/**
	 * 为页面下拉框获取角色列表
	 * @param role 角色条件
	 * @return List<String>，元素为name:XX,value:XX 的字符串
	 */
	public List<String> getRoleListForSelect(Role role) {
		List<Role> roles=listByCondition(role);
		if(role.getKey()!=null&&!"".equals(role.getKey())){
			Personrole personrole=new Personrole();
			personrole.setUserId(role.getKey());
			List<Personrole> personroles=personroleMapper.listByCondition(personrole);
			if(personroles!=null&&personroles.size()>0){
				personrole=personroles.get(0);
				boolean b=false;
				for(int i=0;i<roles.size();i++){
					if(roles.get(i).getRoleId().equals(personrole.getRoleId())){
						b=true;
						break;
					}
				}
				if(!b){
					Role role2=getByPrimaryKey(personrole.getRoleId());
					if(role2!=null){
						roles.add(role2);
					}
				}
			}
		}
		List<String> list = new ArrayList<String>();
		String option = "";
		for(Role r : roles){
			option = "\"name\":\""+r.getRoleName()+"\",\"value\":\""+r.getRoleId()+"\""; 
			list.add(option);
		}
		return list;
	}
}