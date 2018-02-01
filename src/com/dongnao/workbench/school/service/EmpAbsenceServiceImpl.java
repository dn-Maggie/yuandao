package com.dongnao.workbench.school.service;
import javax.annotation.Resource;
import java.util.List;
import com.dongnao.workbench.school.dao.EmpAbsenceMapper;
import com.dongnao.workbench.school.model.EmpAbsence;
import com.dongnao.workbench.school.service.EmpAbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.util.AjaxUtils;
 
/**
 * 描述：员工签卡模块service接口实现类，实现service接口方法
 *
 * @author maggie
 * @version 1.0 2016-12-12
 */
@Service("empAbsenceService")
public class EmpAbsenceServiceImpl implements EmpAbsenceService{
        @Resource
	private EmpAbsenceMapper empAbsenceMapper;
	
 
	/**
	 * 新增员工签卡方法
	 * @param empAbsence:实体类
	 */	
	public ResultMessage add(EmpAbsence empAbsence){
		empAbsenceMapper.add(empAbsence);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 根据主键查找员工签卡实体方法
	 * @param key String 实体主键
	 * @return EmpAbsence 实体对象
	 */
	public EmpAbsence getByPrimaryKey(String key){
		return empAbsenceMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除员工签卡方法
	 * @param key String 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		empAbsenceMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找员工签卡列表方法
	 * @param empAbsence EmpAbsence 实体对象（查询条件）
	 * @return List<EmpAbsence> 实体对象的list
	 */
	public List<EmpAbsence> listByCondition(EmpAbsence empAbsence){
		return empAbsenceMapper.listByCondition(empAbsence);
	}
	
	/**
	 * 修改员工签卡方法
	 * @param empAbsence EmpAbsence 实体对象
	 */	
	public ResultMessage update(EmpAbsence empAbsence){
		empAbsenceMapper.update(empAbsence);
		return AjaxUtils.getSuccessMessage();
	}
	/**
	 * 从emp_absence表中获取所有直接负责人方法
	 * @param empAbsence EmpAbsence 实体对象
	 */	
	public List<EmpAbsence> getStraightLeader(){
		return empAbsenceMapper.getStraightLeader();
	}
}