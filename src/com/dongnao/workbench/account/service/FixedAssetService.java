package com.dongnao.workbench.account.service;

import java.util.List;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.account.model.AssetItem;
import com.dongnao.workbench.account.model.FixedAsset;

/**
 * 描述：固定资产信息模块service接口，提供controller操作所需方法
 *
 * @author maggie
 * @version 1.0 2016-08-12
 */
public interface FixedAssetService  {

	/**
	 * 新增固定资产信息方法
	 * @param fixedAsset FixedAsset:实体类
	 */
	public ResultMessage add(FixedAsset fixedAsset);
	
	/**
	 * 删除固定资产信息方法
	 * @param key:多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key);
	
	/**
	 * 根据主键查找固定资产信息实体方法
	 * @param key String：实体主键
	 * @return fixedAsset FixedAsset 实体对象
	 */
	public FixedAsset getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找固定资产信息列表方法
	 * @param fixedAsset FixedAsset 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<FixedAsset> listByCondition(FixedAsset fixedAsset);
	
	
	/**
	 * 查询待回收资源
	 * @param fixedAsset FixedAsset 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<FixedAsset> listRecovery(FixedAsset fixedAsset);
	/**
	 * 修改固定资产信息方法
	 * @param fixedAsset FixedAsset 实体对象
	 */	
	public ResultMessage update(FixedAsset fixedAsset);
	
	/**
	 * 回收资源方法
	 * @param fixedAsset FixedAsset 实体对象
	 */	
	public ResultMessage recovery(FixedAsset fixedAsset);
	
	/**
	 * 新增资产项目信息方法
	 * @param assetItem AssetItem:实体类
	 */
	public ResultMessage addAssetItem(AssetItem assetItem);
	
	/**
	 * 根据条件查找资产项目信息列表方法
	 * @param assetItem AssetItem 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<AssetItem> listAssetItem(AssetItem assetItem);
}