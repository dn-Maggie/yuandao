package com.dongnao.workbench.school.service;
import javax.annotation.Resource;
import java.util.List;
import com.dongnao.workbench.school.dao.StandardMapper;
import com.dongnao.workbench.school.model.Standard;
import com.dongnao.workbench.school.service.StandardService;
import com.dongnao.workbench.student.model.Statistical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.util.AjaxUtils;
 
/**
 * 描述：考核标准表模块service接口实现类，实现service接口方法
 *
 * @author maggie
 * @version 1.0 2016-07-18
 */
@Service("standardService")
public class StandardServiceImpl implements StandardService{
        @Resource
	private StandardMapper standardMapper;
	
 
	/**
	 * 新增考核标准表方法
	 * @param standard:实体类
	 */	
	public ResultMessage add(Standard standard){
		standardMapper.add(standard);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 根据主键查找考核标准表实体方法
	 * @param key String 实体主键
	 * @return Standard 实体对象
	 */
	public Standard getByPrimaryKey(String key){
		return standardMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除考核标准表方法
	 * @param key String 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		standardMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找考核标准表列表方法
	 * @param standard Standard 实体对象（查询条件）
	 * @return List<Standard> 实体对象的list
	 */
	public List<Standard> listByCondition(Standard standard){
		return standardMapper.listByCondition(standard);
	}
	
	/**
	 * 修改考核标准表方法
	 * @param standard Standard 实体对象
	 */	
	public ResultMessage update(Standard standard){
		standardMapper.update(standard);
		return AjaxUtils.getSuccessMessage();
	}

	/**
	 * 查询所有流量来源方法
	 * @param standard Standard 实体对象
	 */	
	public List<Standard> getAllResourceId() {
		return standardMapper.getAllResourceId();
	}
	
	/**
	 * 查询所有转化人类型方法
	 * @param standard Standard 实体对象
	 */	
	public List<Standard> getAllFollowerId(){
		return standardMapper.getAllFollowerId();
	}

	/* (non-Javadoc)
	 * @see com.dongnao.workbench.school.service.StandardService#getAllSubResourceId()
	 */
	@Override
	public List<Standard> getAllSubResourceId() {
		return standardMapper.getAllSubResourceId();
	}
}