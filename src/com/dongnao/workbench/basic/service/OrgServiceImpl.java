package com.dongnao.workbench.basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongnao.workbench.basic.dao.OrgMapper;
import com.dongnao.workbench.basic.model.Org;
import com.dongnao.workbench.basic.model.UserInfo;
import com.dongnao.workbench.common.Constant;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.enums.OrgClass;
import com.dongnao.workbench.common.enums.OrgType;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.StringUtil;
import com.dongnao.workbench.common.util.Utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 描述：组织机构模块service接口实现类，实现service接口方法
 * 
 * @author yao.su
 * @version 1.0 2016-03-21
 */
@Service("orgService")
public class OrgServiceImpl implements OrgService {

	private OrgMapper orgMapper;

	@Autowired
	public void setOrgMapper(OrgMapper orgMapper) {
		this.orgMapper = orgMapper;
	}

	/**
	 * 新增组织机构方法
	 * 
	 * @param org
	 *            :实体类
	 */
	public ResultMessage add(Org org) {
		if(orgMapper.add(org)>0){
			return AjaxUtils.getSuccessMessage();
		}else{
			return AjaxUtils.getFailureMessage("插入数据失败");
		}
	}

	/**
	 * 构造组织机构树
	 * 
	 * @param org
	 * @return
	 */
	/*
	 * public String initOrgTree(Org org){ org.setIsActive(Constant.ISACTIVE_1);
	 * JSONArray jArray=new JSONArray(); JSONObject jObject=new JSONObject();
	 * jObject.put("id", "0"); jObject.put("name","组织机构"); jObject.put("open",
	 * "true"); jObject.put("isParent", "true"); jArray.add(jObject);
	 * List<Org>list=orgMapper.listByCondition(org); for (Org o : list) {
	 * jObject=new JSONObject(); jObject.put("id", o.getId());
	 * jObject.put("name",o.getOrgName()); jObject.put("pId",
	 * o.getParentOrgId()); jObject.put("isParent", "true");
	 * jArray.add(jObject); } return jArray.toString(); }
	 */
	/**
	 * 根据主键查找组织机构实体方法
	 * 
	 * @param key
	 *            String 实体主键
	 * @return Org 实体对象
	 */
	public Org getByPrimaryKey(String key) {
		return orgMapper.getByPrimaryKey(key);
	}

	/**
	 * 删除组织机构方法
	 * 
	 * @param key
	 *            String 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key) {
		orgMapper.deleteByKey(key);
	}

	/**
	 * 根据条件查找组织机构列表方法
	 * 
	 * @param org
	 *            Org 实体对象（查询条件）
	 * @return List<Org> 实体对象的list
	 */
	public List<Org> listByCondition(Org org) {
		return orgMapper.listByCondition(org);
	}

	/**
	 * 修改组织机构方法
	 * 
	 * @param org
	 *            Org 实体对象
	 */
	public ResultMessage update(Org org) {
		
		if(orgMapper.update(org)>0){
			return AjaxUtils.getSuccessMessage();
		}else{
			return AjaxUtils.getFailureMessage("插入数据失败");
		}
	}

	/*
	 * (非 Javadoc) <p>Title: initOrgTree</p> <p>Description: </p>
	 * 
	 * @param orgName
	 * 
	 * @return
	 * 
	 * @see com.dongnao.workbench.basic.service.OrgService#initOrgTree(java.lang.String)
	 */
	public String initOrgTree(String orgName, UserInfo userInfo) {
		JSONArray orgTree = new JSONArray();
		Org org = new Org();
		org.setOrgName(orgName);
		org.setIsActive(Constant.ISDELETE_FALSE);
		/*org.setOrgType(OrgType.UNIT.getValue());*/
		org.setOrgState(1);
		if (OrgClass.GROUP.getValue().equals(userInfo.getOrgClass())) {
			org.setId(userInfo.getOrgId());
		} else if (OrgClass.PROVINCIAL.getValue().equals(userInfo.getOrgClass())) {
			org.setId(userInfo.getOrgId());
			org.setParentOrgId(userInfo.getOrgId());
		} else if (OrgClass.BRANCH.getValue().equals(userInfo.getOrgClass())) {
			org.setId(userInfo.getOrgId());
		}

		List<Org> orgs = orgMapper.listByCondition(org);
		JSONObject jsonObj;
		for (Org o : orgs) {
			jsonObj = new JSONObject();
			jsonObj.put("open", "true");
			jsonObj.put("isParent", "true");
			jsonObj.put("id", o.getId());
			jsonObj.put("pId", o.getParentOrgId());
			jsonObj.put("name", o.getOrgName());
			jsonObj.put("orgClass", o.getOrgClass());
			orgTree.add(jsonObj);
		}
		return orgTree.toString();
	}


	@Override
	public List<Org> listJoinStockOrgs(Org org) {
		return orgMapper.listJoinStockOrgs(org);
	}

}