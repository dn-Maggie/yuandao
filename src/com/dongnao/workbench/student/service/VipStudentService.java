package com.dongnao.workbench.student.service;

import java.util.List;

import com.dongnao.workbench.basic.model.UserInfo;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.bean.ResultMoney;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.school.model.StudentBarData;
import com.dongnao.workbench.student.model.Statistical;
import com.dongnao.workbench.student.model.VipStudent;

/**
 * 描述：会员信息管理表模块service接口，提供controller操作所需方法
 *
 * @author cheng.mo
 * @version 1.0 2016-05-02
 */
public interface VipStudentService{

	/**
	 * 新增会员信息管理表方法
	 * @param vipStudent VipStudent:实体类
	 */
	public ResultMessage add(VipStudent vipStudent);
	
	/**
	 * 删除会员信息管理表方法
	 * @param key:多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key);
	
	/**
	 * 根据主键查找会员信息管理表实体方法
	 * @param key String：实体主键
	 * @return vipStudent VipStudent 实体对象
	 */
	public VipStudent getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找会员信息管理表列表方法
	 * @param vipStudent VipStudent 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<VipStudent> listByCondition(VipStudent vipStudent);
	
	/**
	 * 获得统计数据
	 * @return: 统计数据map
	 */
	public Statistical getStatistical(VipStudent vipStudent);
	
	
	/**
	 * 获得当日统计数据
	 */
	public Statistical getStatisticaltoday(String today);
	
	/**
	 * 获得学生人数统计数据
	 */
	public StudentBarData getStudentBarData(StudentBarData studentBarData);
	
	/**
	 * 分科目获得统计数据
	 * @return: 统计数据map
	 */
	public List<Statistical> getStatisticalBySubject(String month);
	
	/**
	 * 修改会员信息管理表方法
	 * @param vipStudent VipStudent 实体对象
	 */	
	public ResultMessage update(VipStudent vipStudent);
	
	/**
	 * 修改会员欠费信息方法
	 * @param vipStudent VipStudent 实体对象
	 */	
	public ResultMessage updateOwe(VipStudent vipStudent);
	/**
	 * 修改员工绩效
	 * */
	public ResultMessage updateEmpPerform(VipStudent vipStudent);

	/**
	 * 获取所有欠费学员方法
	 * @param vipStudent VipStudent 实体对象
	 */	
	public List<VipStudent> listByOwn();
	
	
	/**
	 * 查找是否重复
	 * @param qq/subjectId/courseId
	 * @return List<VipStudent> 实体对象的list
	 */
	public  List<VipStudent> getByStu(VipStudent vipStudent);
	
	/**
	 * 根据课程分类查找列表方法
	 * @return vipStudent 实体
	 */
	public List<VipStudent> orderBySubject(String subjectId);
	/**
	 * 查找所有学员列表方法
	 * @return List<VipStudent> 实体对象的list
	 */
	public List<VipStudent> listCntByCondition(VipStudent vipStudent);
	/**
	 * 统计列表
	 * @return List<VipStudent> 实体对象的list
	 */
	public List<VipStudent> sumAllmoney(VipStudent vipStudent);
	/**
	 * 获取学员表所有学员数量、应收款项、实收款项和欠款
	 * @param vipStudent
	 * @return
	 */
	public List<Statistical> getTotal(VipStudent vipStudent);
	/**
	 * 从意向过来报名的学员
	 */
	public List<VipStudent> listVipStudentFromMarket(VipStudent vipStudent);
	
}