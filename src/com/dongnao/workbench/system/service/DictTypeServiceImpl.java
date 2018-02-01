package com.dongnao.workbench.system.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongnao.workbench.common.Constant;
import com.dongnao.workbench.common.util.DateUtil;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.system.dao.DictInfoMapper;
import com.dongnao.workbench.system.dao.DictTypeMapper;
import com.dongnao.workbench.system.model.DictInfo;
import com.dongnao.workbench.system.model.DictType;

 
/**
 * 描述：dictType模块service接口实现类，实现service接口方法
 * @author zhou.zheng
 *
 * @version 1.0 2013-11-07
 */
@Service("dictTypeService")
public class DictTypeServiceImpl implements DictTypeService{

	
	private DictTypeMapper dictTypeMapper;
	
	private DictInfoMapper dictInfoMapper;
	
	
	/**
	 * 设置dictInfoMapper
	 * @param dictInfoMapper
	 */
	@Autowired
	public void setDictInfoMapper(DictInfoMapper dictInfoMapper) {
		this.dictInfoMapper = dictInfoMapper;
	}
	
	/**
	 * 设置Mapper
	 * @param dictTypeMapper DictTypeMapper
	 */
	@Autowired
	public void setDictTypeMapper(DictTypeMapper dictTypeMapper) {
		this.dictTypeMapper = dictTypeMapper;
	}
	
	@Override
	public String initDictTypeTree(String typeName) {
		JSONArray dictTypeTree = new JSONArray();
		//根节点数据
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", "0");
		jsonObj.put("name", Utils.getI18n("system.title", null));
		jsonObj.put("open", "true");
		jsonObj.put("isParent", "true");
		dictTypeTree.add(jsonObj);
		
		DictType dictType = new DictType();
		dictType.setTypeName(typeName);
		dictType.setIsActive(Constant.ISDELETE_FALSE);
		dictType.setStates(Constant.ENABLE_VALUE);
		dictType.setOrder("asc");
		dictType.setOrderFields("TYPE_NAME");
		List<DictType> dictTypes = dictTypeMapper.listByCondition(dictType);
		for (DictType dt : dictTypes) {
			jsonObj = new JSONObject();
			jsonObj.put("id", dt.getTypeCode());
			jsonObj.put("pId", 0);
			jsonObj.put("name", dt.getTypeName());
			dictTypeTree.add(jsonObj);
		}
		return dictTypeTree.toString();
	}
 
	@Override	
	public int add(DictType dictType, HttpServletRequest request){
		DictType dictTypeInfo = getByTypeCode(dictType.getTypeCode());
		if(dictTypeInfo != null){//新编码若存在
			return -1;
		}
		
		String userId = Utils.getLoginUser(request);
		dictType.setCreatorId(userId);
		dictType.setUpdaterId(userId);
		dictType.setCreateTime(DateUtil.now());
		dictType.setId(Utils.generateUniqueID());
		dictType.setIsActive(Constant.ISDELETE_FALSE);
		dictType.setStates(Constant.ENABLE_VALUE);
		dictType.setUpdateTime(DateUtil.now());
		return dictTypeMapper.add(dictType);
	}
	
	@Override
	public DictType getByTypeCode(String typeCode){
		return dictTypeMapper.getByTypeCode(typeCode);
	}
	
	@Override
	public DictType getByPrimaryKey(String key) {
		return dictTypeMapper.getByPrimaryKey(key);
	}
	
	@Override
	public int deleteByTypeCode(String typeCode, HttpServletRequest request){
		DictInfo dictInfo=new DictInfo();
		dictInfo.setDictTypeCode(typeCode);
		dictInfo.setIsActive(Constant.ISDELETE_FALSE);
		List<DictInfo> list=dictInfoMapper.listByCondition(dictInfo);
		if (list.size()!=0) {
			return -1;
		}
		DictType dictType = new DictType();
		dictType.setTypeCode(typeCode);
		dictType.setUpdaterId(Utils.getLoginUser(request));
		dictType.setUpdateTime(DateUtil.now());
		dictType.setIsActive(Constant.ISDELETE_TRUE);
		dictType.setStates(Constant.DISABLE_VALUE);
		return dictTypeMapper.updateByTypeCode(dictType);
	}
	
	@Override
	public List<DictType> listByCondition(DictType dictType){
		dictType.setIsActive(Constant.ISDELETE_FALSE);
		dictType.setStates(Constant.ENABLE_VALUE);
		return dictTypeMapper.listByCondition(dictType);
	}
	
	@Override	
	public int update(DictType dictType, HttpServletRequest request){
		dictType.setUpdaterId(Utils.getLoginUser(request));
		dictType.setUpdateTime(DateUtil.now());
		return dictTypeMapper.update(dictType);
	}
	
	@Override
	public List<String> getDictTypeForSelect(DictType dictType){
		List<DictType> dictList = dictTypeMapper.listByCondition(dictType);
		List<String> list = new ArrayList<String>();
		String option = "";
		for(DictType d : dictList){
			option = "\"name\":\""+d.getTypeName()+"\",\"value\":\""+d.getTypeCode()+"\""; 
			list.add(option);
		}
		return list;
	}

}