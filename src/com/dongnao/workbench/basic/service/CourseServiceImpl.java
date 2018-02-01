package com.dongnao.workbench.basic.service;
import javax.annotation.Resource;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongnao.workbench.basic.dao.CourseMapper;
import com.dongnao.workbench.basic.model.Course;
import com.dongnao.workbench.basic.service.CourseService;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.util.AjaxUtils;
 
/**
 * 描述：课程表模块service接口实现类，实现service接口方法
 *
 * @author cheng.mo
 * @version 1.0 2016-05-01
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService{
        @Resource
	private CourseMapper courseMapper;
	
 
	/**
	 * 新增课程表方法
	 * @param course:实体类
	 */	
	public ResultMessage add(Course course){
		courseMapper.add(course);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 根据主键查找课程表实体方法
	 * @param key String 实体主键
	 * @return Course 实体对象
	 */
	public Course getByPrimaryKey(String key){
		return courseMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除课程表方法
	 * @param key String 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		courseMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找课程表列表方法
	 * @param course Course 实体对象（查询条件）
	 * @return List<Course> 实体对象的list
	 */
	public List<Course> listByCondition(Course course){
		return courseMapper.listByCondition(course);
	}
	
	/**
	 * 修改课程表方法
	 * @param course Course 实体对象
	 */	
	public ResultMessage update(Course course){
		courseMapper.update(course);
		return AjaxUtils.getSuccessMessage();
	}
}