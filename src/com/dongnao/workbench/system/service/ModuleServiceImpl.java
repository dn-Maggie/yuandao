package com.dongnao.workbench.system.service;

import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.taskdefs.Redirector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.dongnao.workbench.common.Constant;
import com.dongnao.workbench.common.util.StringUtil;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.system.dao.ModuleMapper;
import com.dongnao.workbench.system.dao.ModuleResMapper;
import com.dongnao.workbench.system.model.Module;
import com.dongnao.workbench.system.model.ModuleLocation;
import com.dongnao.workbench.system.model.ModuleRes;

/**
 * 描述：module模块service接口实现类，实现service接口方法
 * 
 * @author joan.xiong
 * @version 1.0 2016-03-11
 */
@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {

	private ModuleMapper moduleMapper;
	private ModuleResMapper moduleResMapper;

	/**
	 * 设置数据库操作mapper
	 * 
	 * @param moduleMapper
	 *            ModuleMapper
	 */
	@Autowired
	public void setModuleMapper(ModuleMapper moduleMapper) {
		this.moduleMapper = moduleMapper;
	}

	/**
	 * 设置数据库操作mapper
	 * 
	 * @param moduleResMapper
	 *            ModuleResMapper
	 */
	@Autowired
	public void setModuleResMapper(ModuleResMapper moduleResMapper) {
		this.moduleResMapper = moduleResMapper;
	}

	/**
	 * 新增方法
	 * 
	 * @param module
	 *            Module:实体类
	 */
	// @CacheEvict(value =
	// MemcacheService.BEAN_ID,key="'"+ModuleService.BEAN_ID+".*'")
	public void add(Module module) {
		Date d = new Date();
		module.setId(Utils.generateUniqueID());
		module.setCreateTime(d);
		module.setUpdateTime(d);
		moduleMapper.add(module);
		if (StringUtil.isNotBlank(module.getNavurl())) {
			Utils.updateRightUrl();
		}
	}

	/**
	 * 根据主键查找实体方法
	 * 
	 * @param key
	 *            String：实体主键
	 * @return 实体对象
	 */
	public Module getByPrimaryKey(String key) {
		return moduleMapper.getByPrimaryKey(key);
	}

	/**
	 * 删除方法
	 * 
	 * @param key
	 *            String:多个由“，”分割开的id字符串
	 */
	// @CacheEvict(value =
	// MemcacheService.BEAN_ID,key="'"+ModuleService.BEAN_ID+".*'")
	public void deleteByKey(String key) {
		moduleMapper.deleteByKey(key);
	}

	/**
	 * 根据条件查找列表方法
	 * 
	 * @param module
	 *            Module：实体对象（查询条件）
	 * @return 实体对象的list
	 */
	// @Cacheable(value =
	// MemcacheService.BEAN_ID,key="'"+ModuleService.BEAN_ID+".listByCondition'")
	public List<Module> listByCondition(Module module) {
		return moduleMapper.listByCondition(module);
	}

	/**
	 * 修改方法
	 * 
	 * @param module
	 *            Module：实体对象
	 */
	/*
	 * @Caching(evict = {
	 * 
	 * @CacheEvict(value =
	 * MemcacheService.BEAN_ID,key="'"+ModuleService.BEAN_ID+".*'") ,
	 * 
	 * @CacheEvict(value =
	 * MemcacheService.BEAN_ID,key="'"+ModuleService.BEAN_ID+".*'") })
	 */
	/*
	 * @CacheEvict(value =
	 * MemcacheService.BEAN_ID,key="'"+ModuleService.BEAN_ID+".*'" , condition =
	 * "#root.target.canEvict(#root.caches[0].get('"+ModuleService.BEAN_ID+
	 * ".getMenuJsonForAdmin').get(), #module.navurl, #module.moduleName)" ,
	 * beforeInvocation = true)
	 */
	// @CacheEvict(value =
	// MemcacheService.BEAN_ID,key="'"+ModuleService.BEAN_ID+".*'")
	public void update(Module module) {
		Date d = new Date();
		module.setUpdateTime(d);
		moduleMapper.update(module);
		if (StringUtil.isNotBlank(module.getNavurl())) {
			Utils.updateRightUrl();
		}
	}

	@Override
	// @Cacheable(value =
	// MemcacheService.BEAN_ID,key="'"+ModuleService.BEAN_ID+".getMenuJsonForAdmin'")
	public String getMenuJsonForAdmin() {
		JSONArray menuList = new JSONArray();
		// 获取所有菜单
		List<Module> listModule = moduleMapper.queryForMenuByAdmin();
		// 根据菜单ID获取所拥有的资源
		List<ModuleRes> listModuleRes = moduleResMapper.queryForMenuByAdmin();
		if (listModule != null && listModule.size() > 0) {
			for (Module menu : listModule) {
				// 构造菜单资源对象
				JSONObject menuObj = JSONObject.fromObject(menu);
				String muuid = menu.getId();
				if (listModuleRes != null && listModuleRes.size() > 0) {
					JSONArray moduleResList = new JSONArray();
					for (ModuleRes mr : listModuleRes) {
						if (muuid.equals(mr.getMuuid())) {
							moduleResList.add(JSONObject.fromObject(mr));
						}
					}
					menuObj.put("resItems", moduleResList);
				}
				menuList.add(menuObj);
			}
			return menuList.toString();
		}
		return null;
	}

	@Override
	// @Cacheable(value =
	// MemcacheService.BEAN_ID,key="'"+ModuleService.BEAN_ID+".getMenuJsonForAdmin'")
	public List<Module> getMenuListForAdmin() {
		// 获取所有菜单
		List<Module> listModule = moduleMapper.queryForMenuByAdmin();
		// 根据菜单ID获取所拥有的资源
		List<ModuleRes> listModuleRes = moduleResMapper.queryForMenuByAdmin();
		if (listModule != null && listModule.size() > 0) {
			for (Module menu : listModule) {

				String muuid = menu.getId();
				if (listModuleRes != null && listModuleRes.size() > 0) {
					List<ModuleRes> listmr = new ArrayList<ModuleRes>();
					for (ModuleRes mr : listModuleRes) {
						if (muuid.equals(mr.getMuuid())) {
							listmr.add(mr);
						}
					}
					menu.setListModuleRes(listmr);
				}
			}
		}
		return listModule;
	}

	@Override
	// @Cacheable(value =
	// MemcacheService.BEAN_ID,key="'"+ModuleService.BEAN_ID+".getMenuJsonByPcode.'+#pcode")
	public List<Module> getMenuListByPcode(String pcode) {
		List<Module> listModule = moduleMapper.queryForMenuByPcode(pcode);
		List<ModuleRes> listModuleRes = moduleResMapper
				.queryForMenuByPcode(pcode);
		ModuleRes moduleRes = moduleResMapper.queryFoRoleStatesByPcode(pcode);
		if (listModule != null && listModule.size() > 0
				&& !moduleRes.getRoleStatus().equals("0")) {
			for (Module menu : listModule) {
				String muuid = menu.getId();
				if (listModuleRes != null && listModuleRes.size() > 0) {
					List<ModuleRes> listmr = new ArrayList<ModuleRes>();
					for (ModuleRes mr : listModuleRes) {
						if (muuid.equals(mr.getMuuid())) {
							listmr.add(mr);
						}
					}
					menu.setListModuleRes(listmr);
				}
			}
		}
		return listModule;
	}
	
	
	@Override
	// @Cacheable(value =
	// MemcacheService.BEAN_ID,key="'"+ModuleService.BEAN_ID+".getMenuJsonByPcode.'+#pcode")
	public String getMenuJsonByPcode(String pcode) {
		List<Module> listModule = moduleMapper.queryForMenuByPcode(pcode);
		List<ModuleRes> listModuleRes = moduleResMapper
				.queryForMenuByPcode(pcode);
		String menuIcon = null;
		ModuleRes moduleRes = moduleResMapper.queryFoRoleStatesByPcode(pcode);

		JSONArray menuList = new JSONArray();
		if (listModule != null && listModule.size() > 0
				&& !moduleRes.getRoleStatus().equals("0")) {
			for (Module menu : listModule) {
				menuIcon = menu.getResourceFilePath();
				if (StringUtils.isNotBlank(menuIcon)) {
					try {
						menuIcon = java.net.URLEncoder.encode(menuIcon,
								"iso-8859-1");
					} catch (UnsupportedEncodingException e) {
						menuIcon = "";
					}
					menu.setResourceFilePath(menuIcon);
				}
				// 构造菜单资源对象
				JSONObject menuObj = JSONObject.fromObject(menu);
				String muuid = menu.getId();
				if (listModuleRes != null && listModuleRes.size() > 0) {
					JSONArray moduleResList = new JSONArray();
					for (ModuleRes mr : listModuleRes) {
						if (muuid.equals(mr.getMuuid())) {
							moduleResList.add(JSONObject.fromObject(mr));
						}
					}
					menuObj.put("resItems", moduleResList);
				}
				menuList.add(menuObj);
			}
			return menuList.toString();
		}
		return null;
	}

	/**
	 * 根据URL查询对应的模块路径集合
	 * 
	 * @return List<ModuleLocation>: 实体对象的list
	 */
	public List<ModuleLocation> listModuleLocationList() {
		return moduleMapper.listModuleLocationList();
	}

	/**
	 * 根据URL查询对应的模块路径（如果没有传，默认返回首页）
	 * 
	 * @param murl
	 * @return
	 */
	public String getModuleLocationStrByUrl(String murl) {
		StringBuffer str = new StringBuffer();
		Map<String, ModuleLocation> urlMap = Utils.getModuleLocationMap("url");
		ModuleLocation tmp1 = null;
		if (urlMap == null) {
			str.append("首页");
			return str.toString();
		}
		tmp1 = urlMap.get(murl);
		if (tmp1 == null) {
			str.append("首页");
			return str.toString();
		}
		if (tmp1.getMtype().equals(1)) {// 如果是一级菜单
			str.append(tmp1.getMname());
		} else if (tmp1.getMtype().equals(2)) {// 如果是二级菜单
			Map<String, ModuleLocation> pidMap = Utils
					.getModuleLocationMap("mid");
			str.append(pidMap.get(tmp1.getPid()).getMname() + "->"
					+ tmp1.getMname());
		} else if (tmp1.getMtype().equals(3)) {// 如果是二级和三级，那么久要另外实现
			Map<String, ModuleLocation> pidMap = Utils
					.getModuleLocationMap("mid");
			ModuleLocation t3 = tmp1;
			ModuleLocation t2 = (ModuleLocation) pidMap.get(tmp1.getPid());
			ModuleLocation t1 = (ModuleLocation) pidMap.get(t2.getPid());
			str.append(t1.getMname() + "->" + t2.getMname() + "->"
					+ t3.getMname());
		}
		return str.toString();
	}

	public boolean canEvict(Object obj, String url, String name) {
		return false;
	}
}