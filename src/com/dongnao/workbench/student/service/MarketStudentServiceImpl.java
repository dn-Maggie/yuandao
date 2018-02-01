package com.dongnao.workbench.student.service;
import javax.annotation.Resource;
import java.util.List;

import com.dongnao.workbench.student.dao.MarketStudentMapper;
import com.dongnao.workbench.student.model.MarketStudent;
import com.dongnao.workbench.student.service.MarketStudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.util.AjaxUtils;
 
/**
 * 描述：录入学员信息模块service接口实现类，实现service接口方法
 *
 * @author fan.yang
 * @version 1.0 2016-05-01
 */
@Service("marketStudentService")
public class MarketStudentServiceImpl implements MarketStudentService{
        @Resource
	private MarketStudentMapper marketStudentMapper;
	
 
	/**
	 * 新增录入学员信息方法
	 * @param marketStudent:实体类
	 */	
	public ResultMessage add(MarketStudent marketStudent){
		marketStudentMapper.add(marketStudent);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 根据主键查找录入学员信息实体方法
	 * @param key String 实体主键
	 * @return MarketStudent 实体对象
	 */
	public MarketStudent getByPrimaryKey(String key){
		return marketStudentMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除录入学员信息方法
	 * @param key String 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		marketStudentMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找录入学员信息列表方法
	 * @param marketStudent MarketStudent 实体对象（查询条件）
	 * @return List<MarketStudent> 实体对象的list
	 */
	public List<MarketStudent> listByCondition(MarketStudent marketStudent){
		return marketStudentMapper.listByCondition(marketStudent);
	}
	
	/**
	 * 修改录入学员信息方法
	 * @param marketStudent MarketStudent 实体对象
	 */	
	public ResultMessage update(MarketStudent marketStudent){
		marketStudentMapper.update(marketStudent);
		return AjaxUtils.getSuccessMessage();
	}

	public MarketStudent getMarkStuCount(String month) {
		return marketStudentMapper.getMarkStuCount(month);
	}
	/**
	 * 获取录入信息统计
	 */
	@Override
	public List<MarketStudent> getMarketStatistic(MarketStudent ms) {
		return marketStudentMapper.getMarketStatistic(ms);
	}
}