package com.dongnao.workbench.account.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.dongnao.workbench.common.bean.Model;

public class AssetItem extends Model {

	/**
	 * 主键
	 **/
	private int id;

	/**
	 * 资产项目名称
	 **/
	private String assetName;

	/**
	 * 资产类别
	 **/
	private String assetType;




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}


}
