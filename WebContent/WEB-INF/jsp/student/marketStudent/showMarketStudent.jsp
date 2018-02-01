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
				<td class="inputLabelTd">QQ号码：</td>
				<td class="inputTd">${marketStudent.qq}</td>
				<td class="inputLabelTd">微信号：</td>
				<td class="inputTd">${marketStudent.subjectId}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">录入人：</td>
				<td class="inputTd">${marketStudent.fullname}</td>
				<td class="inputLabelTd">录入时间：</td>
				<td class="inputTd">${marketStudent.time}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">店铺名：</td>
				<td class="inputTd" colspan="3">${marketStudent.notes}</td>
			</tr>
			<tr>
				<td colspan="4" class="inputTd"><img alt="图片"
					style="height: 600px; width: 100%" src="${marketStudent.fileUrl}">
				</td>
			</tr>
			<tr>
				<td class="inputTd" colspan="4" style="text-align: center;"><input
					class="ti_bottom" type="button" id="close_button" value="关闭" /></td>
			</tr>
		</table>
	</div>
</body>
</html>
