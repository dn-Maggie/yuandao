package com.dongnao.workbench.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.dongnao.workbench.common.Constant;
import com.dongnao.workbench.common.util.DateUtil;
import com.dongnao.workbench.common.util.SpringInit;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.system.dao.DictInfoMapper;
import com.dongnao.workbench.system.model.DictInfo;
import com.dongnao.workbench.system.model.DictType;

/**
 * 描述：dictInfo模块service接口实现类，实现service接口方法
 * 
 * @author zhou.zheng
 * 
 * @version 1.0 2013-11-04
 */
@Service("dictInfoService")
public class DictInfoServiceImpl implements DictInfoService {

	private DictInfoMapper dictInfoMapper;
	private DictTypeService dictTypeService;

	/**
	 * 设置
	 * 
	 * @param dictInfoMapper
	 *            DictInfoMapper
	 */
	@Autowired
	public void setDictInfoMapper(DictInfoMapper dictInfoMapper) {
		this.dictInfoMapper = dictInfoMapper;
	}

	/**
	 * 设置
	 * 
	 * @param dictTypeService
	 *            DictTypeService
	 */
	@Autowired
	public void setDictTypeService(DictTypeService dictTypeService) {
		this.dictTypeService = dictTypeService;
	}

	@Override
	public int add(DictInfo dictInfo, HttpServletRequest request) {
		List<DictInfo> checkDictInfo = getByDictCode(dictInfo);
		boolean b = false;
		for (DictInfo info : checkDictInfo) {
			if (!info.getId().equals(dictInfo.getId())) {
				b = true;
				break;
			}
		}
		if (b) {
			return -1;
		}
		String userId = Utils.getLoginUser(request);
		dictInfo.setId(Utils.generateUniqueID());
		dictInfo.setCreatorId(userId);
		dictInfo.setCreateTime(DateUtil.now());
		dictInfo.setStates(Constant.ENABLE_VALUE);
		dictInfo.setIsActive(Constant.ISDELETE_FALSE);
		int result = dictInfoMapper.add(dictInfo);
		updateDictAttribute();
		return result;
	}

	@Override
	public List<DictInfo> getByDictCode(DictInfo dictInfo) {

		return dictInfoMapper.getByDictCode(dictInfo);
	}

	@Override
	public DictInfo getByPrimaryKey(String key) {
		return dictInfoMapper.getByPrimaryKey(key);
	}

	@Override
	public int deleteByKey(String key, HttpServletRequest request) {
		int result = 0;
		String[] str = key.split(",");
		for (int i = 0; i < str.length; i++) {
			DictInfo dictInfo = new DictInfo();
			dictInfo.setId(str[i]);
			dictInfo.setUpdaterId(Utils.getLoginUser(request));
			dictInfo.setUpdateTime(DateUtil.now());
			dictInfo.setStates(Constant.DISABLE_VALUE);
			dictInfo.setIsActive(Constant.ISDELETE_TRUE);
			result += dictInfoMapper.update(dictInfo);
		}
		updateDictAttribute();
		return result;
	}

	@Override
	public List<DictInfo> listByCondition(DictInfo dictInfo) {
		dictInfo.setStates(Constant.ENABLE_VALUE);
		dictInfo.setIsActive(Constant.ISDELETE_FALSE);
		return dictInfoMapper.listByCondition(dictInfo);
	}

	@Override
	public int update(DictInfo dictInfo, HttpServletRequest request) {
		List<DictInfo> checkDictInfo = getByDictCode(dictInfo);
		boolean b = false;
		for (DictInfo info : checkDictInfo) {
			if (!info.getId().equals(dictInfo.getId())) {
				b = true;
				break;
			}
		}
		if (b) {
			return -1;
		}
		dictInfo.setUpdaterId(Utils.getLoginUser(request));
		dictInfo.setUpdateTime(DateUtil.now());
		int result = dictInfoMapper.update(dictInfo);
		updateDictAttribute();
		return result;
	}

	/**
	 * 根据类别获取字典信息
	 * 
	 * @param type
	 *            String 字典类别
	 * @return List<String>
	 */
	public List<String> getDictInfoByType(String type) {
		DictInfo dictInfo = new DictInfo();
		dictInfo.setIsActive(Constant.ISDELETE_FALSE);
		dictInfo.setStates(Constant.ENABLE_VALUE);
		dictInfo.setDictTypeCode(type);
		List<DictInfo> dictList = dictInfoMapper.listByCondition(dictInfo);
		List<String> list = new ArrayList<String>();
		String option = "";
		for (DictInfo d : dictList) {
			option = "\"name\":\"" + d.getDictName() + "\",\"value\":\""
					+ d.getDictCode() + "\"";
			list.add(option);
		}
		return list;
	}

	/**
	 * 根据类别获取字典信息
	 * 
	 * @param type
	 *            String 字典类别
	 * @return List<DictInfo>
	 */
	public List<DictInfo> getDictInfoListByType(String type) {
		DictInfo dictInfo = new DictInfo();
		dictInfo.setIsActive(Constant.ISDELETE_FALSE);
		dictInfo.setStates(Constant.ENABLE_VALUE);
		dictInfo.setDictTypeCode(type);
		List<DictInfo> dictList = dictInfoMapper.listByCondition(dictInfo);
		return dictList;
	}

	/**
	 * 根据字典类型编码查询字典数据( select )
	 * 
	 * @param dictTypeCode
	 *            String
	 * @return List<String>
	 */
	public String getDictByTypeCode(String dictTypeCode) {
		List<DictInfo> dictInfoList = dictInfoMapper
				.getDictByTypeCode(dictTypeCode);
		JSONArray arr = new JSONArray();
		JSONObject obj = null;
		for (DictInfo d : dictInfoList) {
			obj = new JSONObject();
			obj.put("name", d.getDictName());
			obj.put("value", d.getDictCode());
			arr.add(obj);
		}
		return arr.toString();
	}

	/**
	 * 根据字典类型编码查询字典数据(radio & checkbox )
	 * 
	 * @param dictTypeCode
	 *            String
	 * @return List<String>
	 */
	public List<String> getDictForRadio(String dictTypeCode) {
		List<DictInfo> dictInfoList = dictInfoMapper
				.getDictByTypeCode(dictTypeCode);
		List<String> list = new ArrayList<String>();
		String option = "";
		for (DictInfo d : dictInfoList) {
			option = "\"name\":\"" + dictTypeCode + "\",\"value\":\""
					+ d.getDictCode() + "\",\"label\":\"" + d.getDictName()
					+ "\"";
			list.add(option);
		}
		return list;
	}

	public Map<String, String> getAllDictInfo() {
		List<DictType> dictTypes = dictTypeService
				.listByCondition(new DictType());
		List<DictInfo> dictInfos = listByCondition(new DictInfo());
		Map<String, String> dictMap = new HashMap<String, String>();
		JSONArray infoArr = null;
		JSONObject infoObj = null;
		for (DictType dt : dictTypes) {
			infoArr = new JSONArray();
			for (DictInfo d : dictInfos) {
				if (d.getDictTypeCode().equals(dt.getTypeCode())) {
					infoObj = new JSONObject();
					infoObj.put("name", d.getDictName());
					infoObj.put("value", d.getDictCode());
					infoArr.add(infoObj);
				}
			}
			dictMap.put(dt.getTypeCode(), infoArr.toString());
		}
		return dictMap;
	}

	/**
	 * 更新应用上下文中的数据字典信息
	 */
	public void updateDictAttribute() {
		// 增加系统配置时，同步在上下文中增加配置的名称和值
		final WebApplicationContext app = (WebApplicationContext) SpringInit
				.getApplicationContext();
		app.getServletContext().setAttribute(Constant.APP_DICT_KEY,
				getAllDictInfo());
	}

}