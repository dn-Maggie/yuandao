package com.dongnao.workbench.basic.model;
import com.dongnao.workbench.common.bean.Model;

/**
 * 描述：组织机构模块实体类，负责页面与后台数据传输功能
 *
 * @author yao.su
 * @version 1.0 2016-04-06
 */
public class Org extends Model {

	/**
	 * 主键ID
	 **/
	private String id;

	/**
	 * 组织机构编码
	 **/
	private String orgNo;

	/**
	 * 组织机构名称
	 **/
	private String orgName;

	/**
	 * 组织机构全称
	 **/
	private String orgFullName;

	/**
	 * 单位简称
	 **/
	private String orgShortName;

	/**
	 * 机构拼音码
	 **/
	private String pinyin;

	/**
	 * 组织机构类型（1单位，2部门）
	 **/
	private String orgType;

	/**
	 * 单位描述
	 **/
	private String orgDesc;

	/**
	 * 单位状态：1启用、0停用
	 **/
	private Integer orgState;

	/**
	 * 1集团,2省公司,3分公司
	 **/
	private Integer orgClass;

	/**
	 * 网点性质1管里，2经营
	 **/
	private Integer orgKind;

	/**
	 * 父级机构
	 **/
	private String parentOrgId;

	/**
	 * 父级机构名称
	 **/
	private String parentOrgName;

	/**
	 * 省ID
	 **/
	private String areaProvinceId;

	/**
	 * 省名称
	 **/
	private String areaProvinceName;

	/**
	 * 市ID
	 **/
	private String areaRegionId;

	/**
	 * 市名称
	 **/
	private String areaRegionName;

	/**
	 * 县洲区ID
	 **/
	private String areaCityId;

	/**
	 * 县洲区名称
	 **/
	private String areaCityName;

	/**
	 * 地址
	 **/
	private String addr;

	/**
	 * 电话
	 **/
	private String telephone;

	/**
	 * 传真
	 **/
	private String fax;

	/**
	 * 电子邮件地址
	 **/
	private String email;

	/**
	 * 负责人
	 **/
	private String corpMan;

	/**
	 * 是否已经创建股票1是，0否
	 **/
	private Integer isCreateStock;

	/**
	 * 获取 主键ID
	 * 
	 * @return String this.id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置 主键ID
	 * 
	 * @param String
	 *            id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 组织机构编码
	 * 
	 * @return String this.orgNo
	 */
	public String getOrgNo() {
		return this.orgNo;
	}

	/**
	 * 设置 组织机构编码
	 * 
	 * @param String
	 *            orgNo
	 */
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	/**
	 * 获取 组织机构名称
	 * 
	 * @return String this.orgName
	 */
	public String getOrgName() {
		return this.orgName;
	}

	/**
	 * 设置 组织机构名称
	 * 
	 * @param String
	 *            orgName
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 获取 组织机构全称
	 * 
	 * @return String this.orgFullName
	 */
	public String getOrgFullName() {
		return this.orgFullName;
	}

	/**
	 * 设置 组织机构全称
	 * 
	 * @param String
	 *            orgFullName
	 */
	public void setOrgFullName(String orgFullName) {
		this.orgFullName = orgFullName;
	}

	/**
	 * 获取 单位简称
	 * 
	 * @return String this.orgShortName
	 */
	public String getOrgShortName() {
		return this.orgShortName;
	}

	/**
	 * 设置 单位简称
	 * 
	 * @param String
	 *            orgShortName
	 */
	public void setOrgShortName(String orgShortName) {
		this.orgShortName = orgShortName;
	}

	/**
	 * 获取 机构拼音码
	 * 
	 * @return String this.pinyin
	 */
	public String getPinyin() {
		return this.pinyin;
	}

	/**
	 * 设置 机构拼音码
	 * 
	 * @param String
	 *            pinyin
	 */
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	/**
	 * 获取 组织机构类型（1单位，2部门）
	 * 
	 * @return String this.orgType
	 */
	public String getOrgType() {
		return this.orgType;
	}

	/**
	 * 设置 组织机构类型（1单位，2部门）
	 * 
	 * @param String
	 *            orgType
	 */
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	/**
	 * 获取 单位描述
	 * 
	 * @return String this.orgDesc
	 */
	public String getOrgDesc() {
		return this.orgDesc;
	}

	/**
	 * 设置 单位描述
	 * 
	 * @param String
	 *            orgDesc
	 */
	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	/**
	 * 获取 单位状态：1启用、0停用
	 * 
	 * @return Integer this.orgState
	 */
	public Integer getOrgState() {
		return this.orgState;
	}

	/**
	 * 设置 单位状态：1启用、0停用
	 * 
	 * @param Integer
	 *            orgState
	 */
	public void setOrgState(Integer orgState) {
		this.orgState = orgState;
	}

	/**
	 * 获取 1集团,2省公司,3分公司
	 * 
	 * @return Integer this.orgClass
	 */
	public Integer getOrgClass() {
		return this.orgClass;
	}

	/**
	 * 设置 1集团,2省公司,3分公司
	 * 
	 * @param Integer
	 *            orgClass
	 */
	public void setOrgClass(Integer orgClass) {
		this.orgClass = orgClass;
	}

	/**
	 * 获取 网点性质1管里，2经营
	 * 
	 * @return Integer this.orgKind
	 */
	public Integer getOrgKind() {
		return this.orgKind;
	}

	/**
	 * 设置 网点性质1管里，2经营
	 * 
	 * @param Integer
	 *            orgKind
	 */
	public void setOrgKind(Integer orgKind) {
		this.orgKind = orgKind;
	}

	/**
	 * 获取 父级机构
	 * 
	 * @return String this.parentOrgId
	 */
	public String getParentOrgId() {
		return this.parentOrgId;
	}

	/**
	 * 设置 父级机构
	 * 
	 * @param String
	 *            parentOrgId
	 */
	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	/**
	 * 获取 父级机构名称
	 * 
	 * @return String this.parentOrgName
	 */
	public String getParentOrgName() {
		return this.parentOrgName;
	}

	/**
	 * 设置 父级机构名称
	 * 
	 * @param String
	 *            parentOrgName
	 */
	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}

	/**
	 * 获取 省ID
	 * 
	 * @return String this.areaProvinceId
	 */
	public String getAreaProvinceId() {
		return this.areaProvinceId;
	}

	/**
	 * 设置 省ID
	 * 
	 * @param String
	 *            areaProvinceId
	 */
	public void setAreaProvinceId(String areaProvinceId) {
		this.areaProvinceId = areaProvinceId;
	}

	/**
	 * 获取 省名称
	 * 
	 * @return String this.areaProvinceName
	 */
	public String getAreaProvinceName() {
		return this.areaProvinceName;
	}

	/**
	 * 设置 省名称
	 * 
	 * @param String
	 *            areaProvinceName
	 */
	public void setAreaProvinceName(String areaProvinceName) {
		this.areaProvinceName = areaProvinceName;
	}

	/**
	 * 获取 市ID
	 * 
	 * @return String this.areaRegionId
	 */
	public String getAreaRegionId() {
		return this.areaRegionId;
	}

	/**
	 * 设置 市ID
	 * 
	 * @param String
	 *            areaRegionId
	 */
	public void setAreaRegionId(String areaRegionId) {
		this.areaRegionId = areaRegionId;
	}

	/**
	 * 获取 市名称
	 * 
	 * @return String this.areaRegionName
	 */
	public String getAreaRegionName() {
		return this.areaRegionName;
	}

	/**
	 * 设置 市名称
	 * 
	 * @param String
	 *            areaRegionName
	 */
	public void setAreaRegionName(String areaRegionName) {
		this.areaRegionName = areaRegionName;
	}

	/**
	 * 获取 县洲区ID
	 * 
	 * @return String this.areaCityId
	 */
	public String getAreaCityId() {
		return this.areaCityId;
	}

	/**
	 * 设置 县洲区ID
	 * 
	 * @param String
	 *            areaCityId
	 */
	public void setAreaCityId(String areaCityId) {
		this.areaCityId = areaCityId;
	}

	/**
	 * 获取 县洲区名称
	 * 
	 * @return String this.areaCityName
	 */
	public String getAreaCityName() {
		return this.areaCityName;
	}

	/**
	 * 设置 县洲区名称
	 * 
	 * @param String
	 *            areaCityName
	 */
	public void setAreaCityName(String areaCityName) {
		this.areaCityName = areaCityName;
	}

	/**
	 * 获取 地址
	 * 
	 * @return String this.addr
	 */
	public String getAddr() {
		return this.addr;
	}

	/**
	 * 设置 地址
	 * 
	 * @param String
	 *            addr
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * 获取 电话
	 * 
	 * @return String this.telephone
	 */
	public String getTelephone() {
		return this.telephone;
	}

	/**
	 * 设置 电话
	 * 
	 * @param String
	 *            telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * 获取 传真
	 * 
	 * @return String this.fax
	 */
	public String getFax() {
		return this.fax;
	}

	/**
	 * 设置 传真
	 * 
	 * @param String
	 *            fax
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * 获取 电子邮件地址
	 * 
	 * @return String this.email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * 设置 电子邮件地址
	 * 
	 * @param String
	 *            email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取 负责人
	 * 
	 * @return String this.corpMan
	 */
	public String getCorpMan() {
		return this.corpMan;
	}

	/**
	 * 设置 负责人
	 * 
	 * @param String
	 *            corpMan
	 */
	public void setCorpMan(String corpMan) {
		this.corpMan = corpMan;
	}

	/**
	 * 获取 是否已经创建股票1是，0否
	 * 
	 * @return Integer this.isCreateStock
	 */
	public Integer getIsCreateStock() {
		return this.isCreateStock;
	}

	/**
	 * 设置 是否已经创建股票1是，0否
	 * 
	 * @param Integer
	 *            isCreateStock
	 */
	public void setIsCreateStock(Integer isCreateStock) {
		this.isCreateStock = isCreateStock;
	}

}