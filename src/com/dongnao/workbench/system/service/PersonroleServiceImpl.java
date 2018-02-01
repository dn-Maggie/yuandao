package com.dongnao.workbench.system.service;

import java.util.Date;
import java.util.List;

import com.dongnao.workbench.system.dao.PersonroleMapper;
import com.dongnao.workbench.system.model.Personrole;
import com.dongnao.workbench.system.service.PersonroleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
/**
 * 描述：用户角色模块service接口实现类，实现service接口方法
 *
 * @author yang.bin
 * @version 1.0 2016-03-06
 */
@Service("personroleService")
public class PersonroleServiceImpl implements PersonroleService{

	private PersonroleMapper personroleMapper;
	
	/**
	 * 设置用户角色关联Mapper
	 * @param personroleMapper PersonroleMapper
	 */
	@Autowired
	public void setPersonroleMapper(PersonroleMapper personroleMapper) {
		this.personroleMapper = personroleMapper;
	}
 
	/**
	 * 新增方法
	 * @param personrole 实体类
	 */	
	public void add(Personrole personrole){
		personrole.setCreateTime(new Date());
		personroleMapper.add(personrole);
	}
	
	/**
	 * 根据主键查找实体方法
	 * @param key String 实体主键
	 * @return Personrole 实体对象
	 */
	public Personrole getByPrimaryKey(String key){
		return personroleMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除方法
	 * @param key String 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		personroleMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param personrole Personrole 实体对象（查询条件）
	 * @return List<Personrole> 实体对象的list
	 */
	public List<Personrole> listByCondition(Personrole personrole){
		return personroleMapper.listByCondition(personrole);
	}
	
	/**
	 * 修改方法
	 * @param personrole Personrole 实体对象
	 */	
	public void update(Personrole personrole){
		personroleMapper.update(personrole);
	}
}