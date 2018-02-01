package com.dongnao.workbench.account.dao;

import java.util.List;

import com.dongnao.workbench.account.model.AssetItem;
import com.dongnao.workbench.account.model.FixedAsset;
/**
 * 描述：固定资产信息模块dao接口，提供数据库操作方法
 *
 * @author maggie
 * @version 1.0 2016-08-12
 */
public interface FixedAssetMapper  {

	/**
	 * 新增固定资产信息方法
	 * @param fixedAsset FixedAsset:实体类
	 */
	void add(FixedAsset fixedAsset);
	
	/**
	 * 新增资产项目信息方法
	 * @param assetItem AssetItem:实体类
	 */
	void addAssetItem(AssetItem assetItem);
	/**
	 * 删除固定资产信息方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	/**
	 * 根据主键查找固定资产信息实体方法
	 * @param key String：实体主键（查询条件）
	 * @return FixedAsset: 实体
	 */
	public FixedAsset getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找固定资产信息列表方法
	 * @param FixedAsset fixedAsset：实体对象（查询条件）
	 * @return List<FixedAsset>: 实体对象的list
	 */
	public List<FixedAsset>  listByCondition(FixedAsset fixedAsset);
	
	
	/**
	 *  查询待回收资源
	 * @param FixedAsset fixedAsset：实体对象（查询条件）
	 * @return List<FixedAsset>: 实体对象的list
	 */
	public List<FixedAsset>  listRecovery(FixedAsset fixedAsset);
	/**
	 * 根据条件查找资产项目信息列表方法
	 * @param AssetItem assetItem：实体对象（查询条件）
	 * @return List<AssetItem>: 实体对象的list
	 */
	public List<AssetItem>  listAssetItem(AssetItem assetItem);
	
	/**
	 * 修改固定资产信息方法
	 * @param fixedAsset FixedAsset：实体对象
	 */	
	public void update(FixedAsset fixedAsset);
	
	/**
	 * 回收资源方法
	 * @param fixedAsset FixedAsset：实体对象
	 */	
	public void recovery(FixedAsset fixedAsset);
}