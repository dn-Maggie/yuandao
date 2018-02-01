package com.dongnao.workbench.account.service;
import javax.annotation.Resource;
import java.util.List;
import com.dongnao.workbench.account.dao.FixedAssetMapper;
import com.dongnao.workbench.account.model.AssetItem;
import com.dongnao.workbench.account.model.FixedAsset;
import com.dongnao.workbench.account.service.FixedAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.util.AjaxUtils;
 
/**
 * 描述：固定资产信息模块service接口实现类，实现service接口方法
 *
 * @author maggie
 * @version 1.0 2016-08-12
 */
@Service("fixedAssetService")
public class FixedAssetServiceImpl implements FixedAssetService{
        @Resource
	private FixedAssetMapper fixedAssetMapper;
	
 
	/**
	 * 新增固定资产信息方法
	 * @param fixedAsset:实体类
	 */	
	public ResultMessage add(FixedAsset fixedAsset){
		fixedAssetMapper.add(fixedAsset);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 根据主键查找固定资产信息实体方法
	 * @param key String 实体主键
	 * @return FixedAsset 实体对象
	 */
	public FixedAsset getByPrimaryKey(String key){
		return fixedAssetMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除固定资产信息方法
	 * @param key String 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		fixedAssetMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找固定资产信息列表方法
	 * @param fixedAsset FixedAsset 实体对象（查询条件）
	 * @return List<FixedAsset> 实体对象的list
	 */
	public List<FixedAsset> listByCondition(FixedAsset fixedAsset){
		return fixedAssetMapper.listByCondition(fixedAsset);
	}
	
	/**
	 * 修改固定资产信息方法
	 * @param fixedAsset FixedAsset 实体对象
	 */	
	public ResultMessage update(FixedAsset fixedAsset){
		fixedAssetMapper.update(fixedAsset);
		return AjaxUtils.getSuccessMessage();
	}

	/**
	 * 新增资产项目信息方法
	 * @param assetItem AssetItem:实体类
	 */
	public ResultMessage addAssetItem(AssetItem assetItem) {
		fixedAssetMapper.addAssetItem(assetItem);
		return AjaxUtils.getSuccessMessage();
	}

	@Override
	public List<AssetItem> listAssetItem(AssetItem assetItem) {
		return fixedAssetMapper.listAssetItem(assetItem);
	}


	@Override
	public List<FixedAsset> listRecovery(FixedAsset fixedAsset) {
		return fixedAssetMapper.listRecovery(fixedAsset);
	}

	@Override
	public ResultMessage recovery(FixedAsset fixedAsset) {
		fixedAssetMapper.recovery(fixedAsset);
		return AjaxUtils.getSuccessMessage();
	}
}