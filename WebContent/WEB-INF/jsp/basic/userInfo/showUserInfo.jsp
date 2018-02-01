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
		<table class="table">
			<tr>
				<td class="inputLabelTd"><span class="required">*</span>登录账号：</td>
				<td class="inputTd">${userInfo.userAccount}</td>
				<td class="inputLabelTd"><span class="required">*</span>用户姓名：</td>
				<td class="inputTd">${userInfo.fullName}</td>
			</tr>
			<tr>
				<td class="inputLabelTd"><span class="required">*</span>组织机构：</td>
				<td class="inputTd">${userInfo.orgName}</td>
				<td class="inputLabelTd"><span class="required">*</span>所属岗位：</td>
				<td class="inputTd">${userInfo.dutyName}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">备注：</td>
				<td class="inputTd">${userInfo.memo}</td>
				<td class="inputLabelTd">启用日期：</td>
				<td class="inputTd">${userInfo.created}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">状态：</td>
				<td class="inputTd"><c:if test="${userInfo.states eq 1}">启用</c:if>
					<c:if test="${userInfo.states eq 0}">停用</c:if></td>
				<td class="inputLabelTd">角色</td>
				<td class="inputTd"><c:forEach var="role" items="${roleList}">
						<c:if test="${role.roleId eq roleId}">${role.roleName}</c:if>
					</c:forEach></td>
			</tr>
			<tr>
				<td class="inputTd" colspan="5" style="text-align: center;"><input
					class="ti_bottom" type="button" id="close_button" value="关闭" /></td>
			</tr>
		</table>
	</div>
</body>
</html>
