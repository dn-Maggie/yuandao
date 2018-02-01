package com.dongnao.workbench.account.model;

import java.util.Date;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import com.dongnao.workbench.common.bean.Model;

/**
 * 描述：固定资产信息模块实体类，负责页面与后台数据传输功能
 *
 * @author maggie
 * @version 1.0 2016-08-12
 */
public class FixedAsset extends Model {
	/**
	 * 主键id
	 **/
	
	private int id;

	/**
	 * 资产编号
	 **/
	private String assetNo;

	/**
	 * 资产名称
	 **/
	private String assetName;
	/**
	 * 录入期间
	 **/
	@DateTimeFormat(pattern = "yyyy-MM")
	private Date enterDate;
	/**
	 * 规格型号
	 **/
	private String model;
	/**
	 * 增加方式
	 **/
	private String addType;

	/**
	 * 资产类别id
	 **/
	private String assetItemId;

	/**
	 * 开始使用日期
	 **/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date beginDate;

	/**
	 * 使用部门
	 **/
	private String useOrg;

	/**
	 * 使用人工号
	 **/
	private String workNumber;

	/**
	 * 折旧方法
	 **/
	private String depreMethod;

	/**
	 * 当期是否折旧
	 **/
	private String isDeprenow;

	/**
	 * 累计折旧科目
	 **/
	private String ljzjSubject;

	/**
	 * 折旧费用科目
	 **/
	private String zjfySubject;

	/**
	 * 资产清理科目
	 **/
	private String zichanClear;

	/**
	 * 资产原值
	 **/
	private String initialValue;

	/**
	 * 残值率
	 **/
	private Integer remainRatio;

	/**
	 * 预计残值
	 **/
	private String remainValue;

	/**
	 * 预计使用月份
	 **/
	private Integer usePeriod;

	/**
	 * 已折旧月份
	 **/
	private Integer depredMonth;

	/**
	 * 剩余使用月份
	 **/
	private Integer remainUseMonth;

	/**
	 * 累计折旧
	 **/
	private String usedDepre;

	/**
	 * 当期录入时间
	 **/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date currdate;

	/**
	 * 每月折旧额
	 **/
	private String perDepred;

	/**
	 * 资产状态
	 **/
	private Integer propertyState;

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAssetNo() {
		return assetNo;
	}

	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}

	public Date getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAddType() {
		return addType;
	}

	public void setAddType(String addType) {
		this.addType = addType;
	}

	public String getAssetItemId() {
		return assetItemId;
	}

	public void setAssetItemId(String assetItemId) {
		this.assetItemId = assetItemId;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public String getUseOrg() {
		return useOrg;
	}

	public void setUseOrg(String useOrg) {
		this.useOrg = useOrg;
	}



	public String getWorkNumber() {
		return workNumber;
	}

	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}

	public String getDepreMethod() {
		return depreMethod;
	}

	public void setDepreMethod(String depreMethod) {
		this.depreMethod = depreMethod;
	}

	public String getIsDeprenow() {
		return isDeprenow;
	}

	public void setIsDeprenow(String isDeprenow) {
		this.isDeprenow = isDeprenow;
	}

	public String getLjzjSubject() {
		return ljzjSubject;
	}

	public void setLjzjSubject(String ljzjSubject) {
		this.ljzjSubject = ljzjSubject;
	}

	public String getZjfySubject() {
		return zjfySubject;
	}

	public void setZjfySubject(String zjfySubject) {
		this.zjfySubject = zjfySubject;
	}

	public String getZichanClear() {
		return zichanClear;
	}

	public void setZichanClear(String zichanClear) {
		this.zichanClear = zichanClear;
	}

	public String getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(String initialValue) {
		this.initialValue = initialValue;
	}

	public Integer getRemainRatio() {
		return remainRatio;
	}

	public void setRemainRatio(Integer remainRatio) {
		this.remainRatio = remainRatio;
	}

	public String getRemainValue() {
		return remainValue;
	}

	public void setRemainValue(String remainValue) {
		this.remainValue = remainValue;
	}

	public Integer getUsePeriod() {
		return usePeriod;
	}

	public void setUsePeriod(Integer usePeriod) {
		this.usePeriod = usePeriod;
	}

	public Integer getDepredMonth() {
		return depredMonth;
	}

	public void setDepredMonth(Integer depredMonth) {
		this.depredMonth = depredMonth;
	}

	public Integer getRemainUseMonth() {
		return remainUseMonth;
	}

	public void setRemainUseMonth(Integer remainUseMonth) {
		this.remainUseMonth = remainUseMonth;
	}

	public String getUsedDepre() {
		return usedDepre;
	}

	public void setUsedDepre(String usedDepre) {
		this.usedDepre = usedDepre;
	}

	public Date getCurrdate() {
		return currdate;
	}

	public void setCurrdate(Date currdate) {
		this.currdate = currdate;
	}

	public String getPerDepred() {
		return perDepred;
	}

	public void setPerDepred(String perDepred) {
		this.perDepred = perDepred;
	}

	public Integer getPropertyState() {
		return propertyState;
	}

	public void setPropertyState(Integer propertyState) {
		this.propertyState = propertyState;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

}