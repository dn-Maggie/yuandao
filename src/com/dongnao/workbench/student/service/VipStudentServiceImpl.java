package com.dongnao.workbench.student.service;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dongnao.workbench.basic.model.UserInfo;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.bean.ResultMoney;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.school.model.StudentBarData;
import com.dongnao.workbench.student.dao.VipStudentMapper;
import com.dongnao.workbench.student.model.Statistical;
import com.dongnao.workbench.student.model.VipStudent;
 
/**
 * 描述：会员信息管理表模块service接口实现类，实现service接口方法
 *
 * @author cheng.mo
 * @version 1.0 2016-05-02
 */
@Service("vipStudentService")
public class VipStudentServiceImpl implements VipStudentService{
    @Resource
	private VipStudentMapper vipStudentMapper;
	
 
	/**
	 * 新增会员信息管理表方法
	 * @param vipStudent:实体类
	 */	
	public ResultMessage add(VipStudent vipStudent){
		vipStudentMapper.add(vipStudent);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 根据主键查找会员信息管理表实体方法
	 * @param key String 实体主键
	 * @return VipStudent 实体对象
	 */
	public VipStudent getByPrimaryKey(String key){
		return vipStudentMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除会员信息管理表方法
	 * @param key String 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		vipStudentMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找会员信息管理表列表方法
	 * @param vipStudent VipStudent 实体对象（查询条件）
	 * @return List<VipStudent> 实体对象的list
	 */
	public List<VipStudent> listByCondition(VipStudent vipStudent){
		return vipStudentMapper.listByCondition(vipStudent);
	}
	
	/**
	 * 修改会员信息管理表方法
	 * @param vipStudent VipStudent 实体对象
	 */	
	public ResultMessage update(VipStudent vipStudent){
		vipStudentMapper.update(vipStudent);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 修改会员欠费信息方法
	 * @param vipStudent VipStudent 实体对象
	 */	
	public ResultMessage updateOwe(VipStudent vipStudent){
		vipStudentMapper.updateOwe(vipStudent);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 修改员工绩效信息方法
	 * @param vipStudent VipStudent 实体对象
	 */	
	public ResultMessage updateEmpPerform(VipStudent vipStudent){
		vipStudentMapper.updateEmpPerform(vipStudent);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 获得统计数据
	 * @return: 统计数据map
	 */
	public Statistical getStatistical(VipStudent vipStudent){
		return vipStudentMapper.getStatistical(vipStudent);
	}
	
	
	/**
	 * 获得当日统计数据
	 * @return: 统计数据map
	 */
	public Statistical getStatisticaltoday(String today){
		return vipStudentMapper.getStatisticaltoday(today);
	}
	
	/**
	 * 分科目获得统计数据
	 * @return: 统计数据map
	 */
	public List<Statistical> getStatisticalBySubject(String month){
		return vipStudentMapper.getStatisticalBySubject(month);
	}
	
	/**
	 * 获取所有欠费学员方法
	 * @return: return List<VipStudent> 实体对象的list
	 */
	public List<VipStudent> listByOwn(){
		return vipStudentMapper.listByOwn();
		
	}

	/**
	 * 获取用户信息是否重复
	 * 
	 * @param qq/subjectId/courseId
	 * @return List<VipStudent> 实体对象的list
	 */
	public List<VipStudent> getByStu(VipStudent vipStudent) {
		return vipStudentMapper.getByStu(vipStudent);
	}
	
	/**
	 * 根据课程分类查找列表方法
	 * @return vipStudent 实体
	 */
	public List<VipStudent> orderBySubject(String subjectId) {
		return vipStudentMapper.orderBySubject(subjectId);
	}

	/**
	 * 查找所有学员列表方法
	 * @return List<VipStudent> 实体对象的list
	 */
	public List<VipStudent> listCntByCondition(VipStudent vipStudent) {
		return vipStudentMapper.listCntByCondition(vipStudent);
	}

	/**
	 * 统计列表
	 * @return List<VipStudent> 实体对象的list
	 */
	public List<VipStudent> sumAllmoney(VipStudent vipStudent) {
		return vipStudentMapper.sumAllmoney(vipStudent);
	}
	/**
	 * 获取学员表所有学员数量、应收款项、实收款项和欠款
	 * @param vipStudent
	 * @return
	 */
	public List<Statistical> getTotal(VipStudent vipStudent) {
		return vipStudentMapper.getTotal(vipStudent);
	}

	/* (non-Javadoc)
	 * @see com.dongnao.workbench.vipStudent.service.VipStudentService#getStudentBarData(com.dongnao.workbench.school.model.StudentBarData)
	 */
	@Override
	public StudentBarData getStudentBarData(StudentBarData studentBarData) {
		 return vipStudentMapper.getStudentBarData(studentBarData);
	}

	/**
	 * 从意向过来报名的学员
	 */
	public List<VipStudent> listVipStudentFromMarket(VipStudent vipStudent) {
		return vipStudentMapper.listVipStudentFromMarket(vipStudent);
	}

}