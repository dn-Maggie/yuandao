<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html  >
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
				<td class="inputLabelTd">职务名称：</td>
				<td class="inputTd">${duty.dutyName}</td>
				<td class="inputLabelTd">职务级别：</td>
				<td class="inputTd"><c:if test="${duty.dutySort eq 1}">集团级</c:if>
					<c:if test="${duty.dutySort eq 2}">省级</c:if> <c:if
						test="${duty.dutySort eq 3}">分公司级</c:if></td>
			</tr>
			<tr>
				<td class="inputLabelTd">职务类型：</td>
				<td class="inputTd">${duty.dutyTypeName}</td>
				<td class="inputLabelTd">职务描述：</td>
				<td class="inputTd">${duty.dutyDesc}</td>
			</tr>
			<tr>
				<td class="inputTd" colspan="4" style="text-align: center;"><input
					class="ti_bottom" type="button" id="close_button" value="关闭" /></td>
			</tr>
		</table>
	</div>
</body>
</html>
