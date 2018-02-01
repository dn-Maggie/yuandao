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
				<td class="inputLabelTd"><m:message code="personrole.roleId" />:</td>
				<td class="inputTd">${personrole.roleId}</td>
				<td class="inputLabelTd"><m:message
						code="personrole.createTime" />:</td>
				<td class="inputTd">${personrole.createTime}</td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message code="personrole.userId" />:</td>
				<td class="inputTd" colspan="3">${personrole.userId}</td>
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
