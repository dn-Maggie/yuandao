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
				<td class="inputLabelTd">科目编号：</td>
				<td class="inputTd">${accountFinance.accountNo}</td>
				<td class="inputLabelTd">科目名称：</td>
				<td class="inputTd">${accountFinance.accountName}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">科目类型：</td>
				<td class="inputTd"><select name="accountType"
					disabled="disabled">
						<option
							<c:if test="${accountFinance.accountType eq 1}" >selected</c:if>>借[收入]</option>
						<option
							<c:if test="${accountFinance.accountType eq 2}" >selected</c:if>>贷[支出]</option>
				</select></td>
				<td class="inputLabelTd">科目说明：</td>
				<td class="inputTd">${accountFinance.intro}</td>
			</tr>
			<tr>
				<td class="inputTd" colspan="4" style="text-align: center;"><input
					class="ti_bottom" type="button" id="close_button" value="关闭" /></td>
			</tr>
		</table>
	</div>
</body>
</html>
