package com.dongnao.workbench.basic.service;
import javax.annotation.Resource;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongnao.workbench.basic.dao.SubjectMapper;
import com.dongnao.workbench.basic.model.Subject;
import com.dongnao.workbench.basic.service.SubjectService;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.util.AjaxUtils;
 
/**
 * 描述：学科表模块service接口实现类，实现service接口方法
 *
 * @author fan.yang
 * @version 1.0 2016-05-01
 */
@Service("subjectService")
public class SubjectServiceImpl implements SubjectService{
        @Resource
	private SubjectMapper subjectMapper;
	
 
	/**
	 * 新增学科表方法
	 * @param subject:实体类
	 */	
	public ResultMessage add(Subject subject){
		subjectMapper.add(subject);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 根据主键查找学科表实体方法
	 * @param key String 实体主键
	 * @return Subject 实体对象
	 */
	public Subject getByPrimaryKey(String key){
		return subjectMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除学科表方法
	 * @param key String 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		subjectMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找学科表列表方法
	 * @param subject Subject 实体对象（查询条件）
	 * @return List<Subject> 实体对象的list
	 */
	public List<Subject> listByCondition(Subject subject){
		return subjectMapper.listByCondition(subject);
	}
	
	/**
	 * 修改学科表方法
	 * @param subject Subject 实体对象
	 */	
	public ResultMessage update(Subject subject){
		subjectMapper.update(subject);
		return AjaxUtils.getSuccessMessage();
	}

	/* (non-Javadoc)
	 * 查询总业绩目标方法
	 */
	public int queryPerfTarget() {
		return subjectMapper.queryPerfTarget();
	}
}