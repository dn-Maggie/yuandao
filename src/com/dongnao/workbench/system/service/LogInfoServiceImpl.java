package com.dongnao.workbench.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongnao.workbench.system.dao.LogInfoMapper;
import com.dongnao.workbench.system.model.LogInfo;
import com.dongnao.workbench.system.service.LogInfoService;

/**
 * 描述：logInfo模块service接口实现类，实现service接口方法
 * @author wff
 * @version 1.0  2016-03-20
 */
@Service("logInfoService")
public class LogInfoServiceImpl implements LogInfoService{
	private LogInfoMapper logInfoMapper;
	
	/**
	 * 注入  LogInfoMapper
	 * @param logInfoMapper LogInfoMapper
	 */
	@Autowired
	public void setLogInfoMapper(LogInfoMapper logInfoMapper) {
		this.logInfoMapper = logInfoMapper;
	}
 
	/**
	 * 新增方法
	 * @param logInfo 实体类
	 */	
	public void add(LogInfo logInfo){
		logInfoMapper.add(logInfo);
	}
	
	/**
	 * 根据主键查找实体方法
	 * @param id 实体主键
	 * @return 实体对象
	 */
	public LogInfo getByPrimaryKey(String id){
		return logInfoMapper.getByPrimaryKey(id);
	}
	
	/**
	 * 删除方法
	 * @param id 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String id){
		logInfoMapper.deleteByKey(id);
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param logInfo 实体对象（查询条件）
	 * @return 实体对象的list
	 */
	public List<LogInfo> listByCondition(LogInfo logInfo){
		return logInfoMapper.listByCondition(logInfo);
	}
	
	/**
	 * 修改方法
	 * @param logInfo 实体对象
	 */	
	public void update(LogInfo logInfo){
		logInfoMapper.update(logInfo);
	}
}