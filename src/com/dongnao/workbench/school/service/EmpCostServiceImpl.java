package com.dongnao.workbench.school.service;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.school.dao.EmpCostMapper;
import com.dongnao.workbench.school.model.EmpCost;
 
/**
 * 描述：员工工资表模块service接口实现类，实现service接口方法
 *
 * @author maggie
 * @version 1.0 2016-12-14
 */
@Service("EmpCostService")
public class EmpCostServiceImpl implements EmpCostService{
        @Resource
	private EmpCostMapper EmpCostMapper;
	
 
	/**
	 * 新增员工工资表方法
	 * @param EmpCost:实体类
	 */	
	public ResultMessage add(EmpCost EmpCost){
		EmpCostMapper.add(EmpCost);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 根据主键查找员工工资表实体方法
	 * @param key String 实体主键
	 * @return EmpCost 实体对象
	 */
	public EmpCost getByPrimaryKey(String key){
		return EmpCostMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除员工工资表方法
	 * @param key String 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		EmpCostMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找员工工资表列表方法
	 * @param EmpCost EmpCost 实体对象（查询条件）
	 * @return List<EmpCost> 实体对象的list
	 */
	public List<EmpCost> listByCondition(EmpCost EmpCost){
		return EmpCostMapper.listByCondition(EmpCost);
	}
	
	/**
	 * 修改员工工资表方法
	 * @param EmpCost EmpCost 实体对象
	 */	
	public ResultMessage update(EmpCost EmpCost){
		EmpCostMapper.update(EmpCost);
		return AjaxUtils.getSuccessMessage();
	}


	
}