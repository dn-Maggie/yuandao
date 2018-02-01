<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html  >
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<base href="<%=basePath%>">

<%@ include file="../../common/header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/styles/extends/css/fixedAsset.css" />
<script type="text/javascript">
	$(function() {
		//绑定提交按钮click事件
		$("#close_button").click(function() {
			window.parent.closeShow();
		});
	});
</script>
</head>

<body>

	<div id="editDialog">
		<form id="fixedAssetFormEdit">
			<div class="wrap">
				<div class="top_head">
					<h2 class="top_name">公司资源</h2>
					<div class="time_bg"
						style="top: 40px; right: 0px; height: 25px; line-height: 25px; position: absolute;">
						<span>录入日期&nbsp;&nbsp;</span> <input id="edit_currdate"
							type="text" class="search_time150" name="currdate"
							mainid="currdate" style="height: 25px;"
							value="${fixedAsset.currdate}" readonly> <i
							class="search_time_ico2" style="top: 6px;"></i>
					</div>

				</div>
				<input type="hidden" id="edit_id" name="id" type="text"
					value="${fixedAsset.id}" />
				<table class="table">
					<tr>
						<td colspan="4" class="cut"><i class="icon_bg icon_plan"></i>
							基本信息</td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>资产编号：</td>
						<td class="inputTd"><label id="edit_assetNo">${fixedAsset.assetNo}</label>
						</td>
						<td class="inputLabelTd">会计期间：</td>
						<td class="inputTd"><label id="edit_enterDate">${fixedAsset.enterDate}</label>
							<input name="enterDate" id="enterDate" type="hidden" class="text"
							value="${fixedAsset.enterDate}" /></td>
						<td class="inputLabelTd">增加方式：</td>
						<td class="inputTd"><label id="edit_addType">${fixedAsset.addType}</label>
						</td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>资产名称：</td>
						<td class="inputTd"><label id="edit_assetName">${fixedAsset.assetName}</label>
						</td>
						<td class="inputLabelTd"><span class="required">*</span>资产项目名称：</td>
						<td class="inputTd"><label id="edit_assetType">${fixedAsset.assetItemId}</label>
						</td>
						<td class="inputLabelTd">规格型号：</td>
						<td class="inputTd"><label id="edit_model">${fixedAsset.model}</label>
						</td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>开始使用日期：</td>
						<td class="inputTd"><label id="edit_beginDate">${fixedAsset.beginDate}</label>
						</td>
						<td class="inputLabelTd"><span class="required">*</span>使用部门：</td>
						<td class="inputTd"><label id="edit_useOrg">${fixedAsset.useOrg}</label>
						</td>
						<td class="inputLabelTd">使用人：</td>
						<td class="inputTd"><label id="edit_workNumber">${fixedAsset.workNumber}</label>
						</td>
					</tr>
					<tr>
						<td colspan="4" class="cut"><i class="icon_bg icon_plan"></i>
							折旧方式</td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>折旧方法：</td>
						<td class="inputTd"><c:choose>
								<c:when test="${fixedAsset.depreMethod=='1'}">
									<label>平均年限法</label>
								</c:when>
								<c:otherwise>
									<label>不折旧</label>
								</c:otherwise>
							</c:choose></td>
						<td class="inputLabelTd"><span class="required">*</span>当期是否折旧：</td>
						<td class="inputTd"><c:choose>
								<c:when test="${fixedAsset.isDeprenow=='是'}">
									<label>是</label>
								</c:when>
								<c:otherwise>
									<label>否</label>
								</c:otherwise>
							</c:choose></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>累计折旧科目：</td>
						<td class="inputTd"><label id="edit_ljzjSubject">累计折旧</label>
						</td>
						<td class="inputLabelTd"><span class="required">*</span>折旧费用科目：</td>
						<td class="inputTd"><label id="edit_zjfySubject">${fixedAsset.zjfySubject}</label>
						</td>
						<td class="inputLabelTd"><span class="required">*</span>资产清理科目：</td>
						<td class="inputTd"><label id="edit_zichanClear">固定资产清理</label>
						</td>
					</tr>
					<tr>
						<td colspan="4" class="cut"><i class="icon_bg icon_plan"></i>
							资产数据</td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>资产原值：</td>
						<td class="inputTd"><label id="edit_initialValue">${fixedAsset.initialValue}</label>
						</td>
						<td class="inputLabelTd"><span class="required">*</span>残值率：</td>
						<td class="inputTd"><label id="edit_remainRatio">${fixedAsset.remainRatio}</label>&nbsp;%
						</td>
						<td class="inputLabelTd">预计残值：</td>
						<td class="inputTd"><label id="edit_remainValue"
							name="remainValue">${fixedAsset.remainValue}</label></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>预计使用月份：</td>
						<td class="inputTd"><label id="edit_usePeriod"
							name="usePeriod">${fixedAsset.usePeriod}</label></td>
						<td class="inputLabelTd"><span class="required">*</span>已折旧月份：</td>
						<td class="inputTd"><label id="edit_depredMonth"
							name="depredMonth">${fixedAsset.depredMonth}</label></td>
						<td class="inputLabelTd">剩余使用月份：</td>
						<td class="inputTd"><label id="edit_remainUseMonth"
							name="remainUseMonth">${fixedAsset.remainUseMonth}</label></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>累计折旧：</td>
						<td class="inputTd"><label id="edit_usedDepre"
							name="usedDepre">${fixedAsset.usedDepre}</label></td>
						<td class="inputLabelTd"><span class="required">*</span>每月折旧额：</td>
						<td class="inputTd"><label id="edit_perDepred"
							name="perDepred">${fixedAsset.perDepred}</label></td>
						<td class="inputLabelTd">资产状态：</td>
						<td class="inputTd"><c:choose>
								<c:when test="${fixedAsset.propertyState == '1'}">
									<label>使用中</label>
								</c:when>
								<c:when test="${fixedAsset.propertyState == '2'}">
									<label>维修中</label>
								</c:when>
								<c:when test="${fixedAsset.propertyState == '4'}">
									<label>停用中</label>
								</c:when>
								<c:otherwise>
									<label>已报废</label>
								</c:otherwise>
							</c:choose></td>
					</tr>
					<tr>
						<td class="inputTd" colspan="6" style="text-align: center;">
							<input id="reset" type="button" class="ti_bottom" value="关闭"
							onclick="window.parent.closeShow();" />
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>
</html>
