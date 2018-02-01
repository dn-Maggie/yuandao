package com.dongnao.workbench.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongnao.workbench.common.util.StringUtil;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.system.dao.ModuleResMapper;
import com.dongnao.workbench.system.model.ModuleRes;

/**
 * 描述：菜单资源模块service接口实现类，实现service接口方法
 * @author joan.xiong
 * @version 1.0 2016-03-11
 */
@Service("moduleResService")
public class ModuleResServiceImpl implements ModuleResService{

	private ModuleResMapper moduleResMapper;

	/**
	 * 设置数据库操作mapper
	 * @param moduleResMapper ModuleResMapper
	 */
	@Autowired
	public void setModuleResMapper(ModuleResMapper moduleResMapper) {
		this.moduleResMapper = moduleResMapper;
	}
	
	/**
	 * 新增方法
	 * @param moduleRes ModuleRes:实体类
	 */	
	/*@Caching(evict = {
				@CacheEvict(value = MemcacheService.BEAN_ID,key="'"+ModuleResService.BEAN_ID+".*'")
				, @CacheEvict(value = MemcacheService.BEAN_ID,key="'"+ModuleService.BEAN_ID+".*'") })*/
	public void add(ModuleRes moduleRes){
		moduleRes.setId(Utils.generateUniqueID());
		moduleResMapper.add(moduleRes);
		if(StringUtil.isNotBlank(moduleRes.getResurl())){
	    	Utils.updateRightUrl();
		}
	}
	
	/**
	 * 根据主键查找实体方法
	 * @param key String：实体主键
	 * @return 实体对象
	 */
	public ModuleRes getByPrimaryKey(String key){
		return moduleResMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	/*@Caching(evict = {
			@CacheEvict(value = MemcacheService.BEAN_ID,key="'"+ModuleResService.BEAN_ID+".*'")
			, @CacheEvict(value = MemcacheService.BEAN_ID,key="'"+ModuleService.BEAN_ID+".*'") })*/
	public void deleteByKey(String key){
		moduleResMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param moduleRes ModuleRes：实体对象（查询条件）
	 * @return List<ModuleRes> 实体对象的list
	 */
	public List<ModuleRes> listByCondition(ModuleRes moduleRes){
		return moduleResMapper.listByCondition(moduleRes);
	}
	
	/**
	 * 修改方法
	 * @param moduleRes ModuleRes：实体对象
	 */	
	/*@Caching(evict = {
			@CacheEvict(value = MemcacheService.BEAN_ID,key="'"+ModuleResService.BEAN_ID+".*'")
			, @CacheEvict(value = MemcacheService.BEAN_ID,key="'"+ModuleService.BEAN_ID+".*'") })*/
	public void update(ModuleRes moduleRes){
		moduleResMapper.update(moduleRes);
		if(StringUtil.isNotBlank(moduleRes.getResurl())){
	    	Utils.updateRightUrl();
		}
	}

	@Override
	/*@Caching(evict = {
			@CacheEvict(value = MemcacheService.BEAN_ID,key="'"+ModuleResService.BEAN_ID+".*'")
			, @CacheEvict(value = MemcacheService.BEAN_ID,key="'"+ModuleService.BEAN_ID+".*'") })*/
	public void updateMrMuuid(String resuuids, String muuid) {
		String[] idArr = resuuids.split(",");
		List<String> list = new ArrayList<String>();
		for(int i=0,s=idArr.length;i<s;i++){
			list.add(idArr[i]);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("muuid", muuid);
		moduleResMapper.updateMrMuuid(map);
	}
	
	
}