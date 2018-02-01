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
			window.parent.closeCount();
		});
	});
</script>
</head>

<body>
	<div class="ui-table ui-widget ui-corner-all ui-border">
		<table class="table" style="width: 50%; float: left">
			<tr>
				<td colspan="2" style="text-align: center;">本月合计</td>
				<td></td>
			</tr>
			<c:forEach var="curr" items="${curr}">
				<tr>
					<td class="inputLabelTd">${curr.accountName}</td>
					<td class="inputTd">${curr.money}</td>
				</tr>
			</c:forEach>
		</table>
		<table class="table" style="width: 50%">
			<tr>
				<td colspan="2" style="text-align: center;">本年合计</td>
				<td></td>
			</tr>
			<c:forEach var="all" items="${all}">
				<tr>
					<td class="inputLabelTd">${all.accountName}</td>
					<td class="inputTd">${all.money}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
