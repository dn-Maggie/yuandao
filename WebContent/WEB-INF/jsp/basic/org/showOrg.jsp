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
	<div class="ui-table ui-widget ui-corner-all ui-border">
		<table class="table forview">
			<tr>
				<td class="inputLabelTd">上级机构：</td>
				<td class="inputTd">${org.parentOrgName}</td>
				<td class="inputLabelTd">机构类型</td>
				<td class="inputTd"><c:if test="${org.orgType eq 1}">公司</c:if>
					<c:if test="${org.orgType eq 2}">部门</c:if></td>

			</tr>
			<tr>
				<td class="inputLabelTd">机构编码：</td>
				<td class="inputTd">${org.orgNo}</td>
				<td class="inputLabelTd">机构名称：</td>
				<td class="inputTd">${org.orgName}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">机构全称：</td>
				<td class="inputTd">${org.orgFullName}</td>
				<td class="inputLabelTd">拼音码：</td>
				<td class="inputTd">${org.pinyin}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">单位描述：</td>
				<td class="inputTd">${org.orgDesc}</td>
				<td class="inputLabelTd">单位状态：</td>
				<td class="inputTd"><c:if test="${org.orgState eq 1}">启用</c:if>
					<c:if test="${org.orgState eq 0}">停用</c:if></td>
			</tr>
			<tr>
				<td class="inputLabelTd">机构级别：</td>
				<td class="inputTd"><c:if test="${org.orgClass eq 3}">分公司级</c:if>
					<c:if test="${org.orgClass eq 2}">省级</c:if> <c:if
						test="${org.orgClass eq 1}">集团级</c:if></td>
				<td class="inputLabelTd">机构性质：</td>
				<td class="inputTd"><c:if test="${org.orgKind eq 2}">经营</c:if>
					<c:if test="${org.orgKind eq 1}">管理</c:if></td>
			</tr>

			<tr>

				<td class="inputLabelTd">所属地区：</td>
				<td colspan="3" class="inputTd">
					${org.areaProvinceName}--${org.areaRegionName}--${org.areaCityName}
				</td>

			</tr>

			<tr>
				<td class="inputLabelTd">办公地址：</td>
				<td colspan="3" class="inputTd">${org.addr}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">电话：</td>
				<td class="inputTd">${org.telephone}</td>
				<td class="inputLabelTd">传真：</td>
				<td class="inputTd">${org.fax}</td>

			</tr>
			<tr>
				<td class="inputLabelTd">电子邮件地址：</td>
				<td class="inputTd">${org.email}</td>
				<td class="inputLabelTd">负责人：</td>
				<td class="inputTd">${org.corpMan}</td>

			</tr>
			<tr>
				<td class="inputTd" colspan="4" style="text-align: center;"><input
					class="ti_bottom" type="button" id="close_button" value="关闭" /></td>
			</tr>
		</table>
	</div>
</body>
</html>
