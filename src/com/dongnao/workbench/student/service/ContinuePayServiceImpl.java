package com.dongnao.workbench.student.service;
import javax.annotation.Resource;
import java.util.List;

import com.dongnao.workbench.student.dao.ContinuePayMapper;
import com.dongnao.workbench.student.model.ContinuePay;
import com.dongnao.workbench.student.model.StatisticalCP;
import com.dongnao.workbench.student.service.ContinuePayService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.util.AjaxUtils;
 
/**
 * 描述：补款管理模块service接口实现类，实现service接口方法
 *
 * @author yang.liu
 * @version 1.0 2016-05-02
 */
@Service("continuePayService")
public class ContinuePayServiceImpl implements ContinuePayService{
        @Resource
	private ContinuePayMapper continuePayMapper;
	
 
	/**
	 * 新增补款管理方法
	 * @param continuePay:实体类
	 */	
	public ResultMessage add(ContinuePay continuePay){
		continuePayMapper.add(continuePay);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 根据主键查找补款管理实体方法
	 * @param key String 实体主键
	 * @return ContinuePay 实体对象
	 */
	public ContinuePay getByPrimaryKey(String key){
		return continuePayMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除补款管理方法
	 * @param key String 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		continuePayMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找补款管理列表方法
	 * @param continuePay ContinuePay 实体对象（查询条件）
	 * @return List<ContinuePay> 实体对象的list
	 */
	public List<ContinuePay> listByCondition(ContinuePay continuePay){
		return continuePayMapper.listByCondition(continuePay);
	}
	
	/**
	 * 修改补款管理方法
	 * @param continuePay ContinuePay 实体对象
	 */	
	public ResultMessage update(ContinuePay continuePay){
		continuePayMapper.update(continuePay);
		return AjaxUtils.getSuccessMessage();
	}
	
	
	/**
	 * 计算所有补款金额
	 */
	public List<ContinuePay> getStatistical(ContinuePay continuePay){
		return continuePayMapper.getStatistical(continuePay);
	}

}