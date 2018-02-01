package com.dongnao.workbench.school.service;
import javax.annotation.Resource;
import java.util.List;
import com.dongnao.workbench.school.dao.EmpNoticeMapper;
import com.dongnao.workbench.school.model.EmpNotice;
import com.dongnao.workbench.school.service.EmpNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.util.AjaxUtils;
 
/**
 * 描述：员工公告通知模块service接口实现类，实现service接口方法
 *
 * @author maggie
 * @version 1.0 2016-12-22
 */
@Service("empNoticeService")
public class EmpNoticeServiceImpl implements EmpNoticeService{
        @Resource
	private EmpNoticeMapper empNoticeMapper;
	
 
	/**
	 * 新增员工公告通知方法
	 * @param empNotice:实体类
	 */	
	public ResultMessage add(EmpNotice empNotice){
		empNoticeMapper.add(empNotice);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 根据主键查找员工公告通知实体方法
	 * @param key String 实体主键
	 * @return EmpNotice 实体对象
	 */
	public EmpNotice getByPrimaryKey(String key){
		return empNoticeMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除员工公告通知方法
	 * @param key String 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		empNoticeMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找员工公告通知列表方法
	 * @param empNotice EmpNotice 实体对象（查询条件）
	 * @return List<EmpNotice> 实体对象的list
	 */
	public List<EmpNotice> listByCondition(EmpNotice empNotice){
		return empNoticeMapper.listByCondition(empNotice);
	}
	
	/**
	 * 修改员工公告通知方法
	 * @param empNotice EmpNotice 实体对象
	 */	
	public ResultMessage update(EmpNotice empNotice){
		empNoticeMapper.update(empNotice);
		return AjaxUtils.getSuccessMessage();
	}
}