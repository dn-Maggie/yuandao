package com.dongnao.workbench.basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongnao.workbench.basic.dao.DutyMapper;
import com.dongnao.workbench.basic.model.Duty;
import com.dongnao.workbench.basic.model.DutyLevel;
import com.dongnao.workbench.common.Constant;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.Utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

 
/**
 * 描述：岗位信息模块service接口实现类，实现service接口方法
 *
 * @author yao.su
 * @version 1.0 2016-03-13
 */
@Service("dutyService")
public class DutyServiceImpl implements DutyService{

	private DutyMapper dutyMapper;
	
	@Autowired
	public void setDutyMapper(DutyMapper dutyMapper) {
		this.dutyMapper = dutyMapper;
	}
 
	/**
	 * 新增岗位信息方法
	 * @param duty:实体类
	 */	
	public ResultMessage add(Duty duty){
		dutyMapper.add(duty);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 根据主键查找岗位信息实体方法
	 * @param key String 实体主键
	 * @return Duty 实体对象
	 */
	public Duty getByPrimaryKey(String key){
		return dutyMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除岗位信息方法
	 * @param key String 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		dutyMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找岗位信息列表方法
	 * @param duty Duty 实体对象（查询条件）
	 * @return List<Duty> 实体对象的list
	 */
	public List<Duty> listByCondition(Duty duty){
		return dutyMapper.listByCondition(duty);
	}
	
	/**
	 * 初始化岗位树
	 * @param duty
	 * @return
	 */
	public String initDutyTree(Duty duty){
		duty.setIsActive(Constant.ISACTIVE_1);
		List<Duty>dutyList=dutyMapper.listByCondition(duty);
		JSONArray jArray=new JSONArray();
		JSONObject jObject;
		for (Duty d : dutyList) {
			jObject=new JSONObject();
			jObject.put("id", d.getId());
			jObject.put("pId", d.getPid());
			jObject.put("name", d.getDutyName());
			jArray.add(jObject);
		}
		return jArray.toString();
	}
	
	/**
	 * 修改岗位信息方法
	 * @param duty Duty 实体对象
	 */	
	public void update(Duty duty){
		duty.setIsActive(Constant.ISACTIVE_1);
		dutyMapper.update(duty);
	}

}