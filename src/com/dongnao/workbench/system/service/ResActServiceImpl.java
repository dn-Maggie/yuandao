package com.dongnao.workbench.system.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongnao.workbench.common.util.StringUtil;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.system.dao.ResActMapper;
import com.dongnao.workbench.system.model.ResAct;
import com.dongnao.workbench.system.service.ResActService;

 
/**
 * 描述：resAct模块service接口实现类，实现service接口方法
 * @author joan.xiong
 * @version 1.0 2016-03-11
 */
@Service("resActService")
public class ResActServiceImpl implements ResActService{

	
	private ResActMapper resActMapper;
	
	/**
	 * 设置数据库操作mapper
	 * @param resActMapper ResActMapper
	 */
	@Autowired
	public void setResActMapper(ResActMapper resActMapper) {
		this.resActMapper = resActMapper;
	}
 
	
	/**
	 * 新增方法
	 * @param resAct ResAct:实体类
	 */	
	public void add(ResAct resAct){
		Date d = new Date();
		resAct.setId(Utils.generateUniqueID());
		resAct.setCreateTime(d);
		resAct.setUpdateTime(d);
		resActMapper.add(resAct);
		if(StringUtil.isNotBlank(resAct.getActUrl())){
	    	Utils.updateRightUrl();
		}
	}
	
	/**
	 * 根据主键查找实体方法
	 * @param key String：实体主键
	 * @return 实体对象
	 */
	public ResAct getByPrimaryKey(String key){
		return resActMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		resActMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param resAct ResAct：实体对象（查询条件）
	 * @return 实体对象的list
	 */
	public List<ResAct> listByCondition(ResAct resAct){
		return resActMapper.listByCondition(resAct);
	}
	
	/**
	 * 修改方法
	 * @param resAct ResAct：实体对象
	 */	
	public void update(ResAct resAct){
		Date d = new Date();
		resAct.setUpdateTime(d);
		resActMapper.update(resAct);
		if(StringUtil.isNotBlank(resAct.getActUrl())){
	    	Utils.updateRightUrl();
		}
	}
	
}