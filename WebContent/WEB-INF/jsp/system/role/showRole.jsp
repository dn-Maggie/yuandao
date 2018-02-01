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
				<td class="inputLabelTd"><m:message code="role.startTime" />:</td>
				<td class="inputTd">${role.startTime}</td>
				<td class="inputLabelTd"><m:message code="role.roleDesc" />:</td>
				<td class="inputTd">${role.roleDesc}</td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message code="role.roleId" />:</td>
				<td class="inputTd">${role.roleId}</td>
				<td class="inputLabelTd"><m:message code="role.createTime" />:</td>
				<td class="inputTd">${role.createTime}</td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message code="role.memo" />:</td>
				<td class="inputTd">${role.memo}</td>
				<td class="inputLabelTd"><m:message code="role.updateTime" />:</td>
				<td class="inputTd">${role.updateTime}</td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message code="role.creatorId" />:</td>
				<td class="inputTd">${role.creatorId}</td>
				<td class="inputLabelTd"><m:message code="role.states" />:</td>
				<td class="inputTd">${role.states}</td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message code="role.roleName" />:</td>
				<td class="inputTd">${role.roleName}</td>
				<td class="inputLabelTd"><m:message code="role.roleCode" />:</td>
				<td class="inputTd">${role.roleCode}</td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message code="role.endTime" />:</td>
				<td class="inputTd">${role.endTime}</td>
				<td class="inputLabelTd"><m:message code="role.updaterId" />:</td>
				<td class="inputTd" colspan="0">${role.updaterId}</td>
			</tr>
			<tr>
				<td class="inputTd" colspan="4" style="text-align: center;"><input
					class="ti_bottom" type="button" id="close_button"
					value="<m:message code='button.close' />" /></td>
			</tr>
		</table>
	</div>
</body>
</html>
