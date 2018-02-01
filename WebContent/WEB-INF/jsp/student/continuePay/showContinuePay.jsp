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
				<td class="inputLabelTd">学生姓名：</td>
				<td class="inputTd">${vipStudent.name}</td>
				<td class="inputLabelTd">学生QQ：</td>
				<td class="inputTd">${vipStudent.qq}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">报名日期：</td>
				<td class="inputTd">${vipStudent.joinTime}</td>
				<td class="inputLabelTd">目前状态：</td>
				<td class="inputTd">${vipStudent.currStatus}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">报名学科：</td>
				<td class="inputTd">${vipStudent.subjectName}</td>
				<td class="inputLabelTd">报名课程：</td>
				<td class="inputTd">${vipStudent.courseName}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">补款金额：</td>
				<td class="inputTd">${continuePay.money}</td>
				<td class="inputLabelTd">补款时间：</td>
				<td class="inputTd">${continuePay.time}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">应缴学费：</td>
				<td class="inputTd">${vipStudent.shouldPay}</td>
				<td class="inputLabelTd">实缴学费：</td>
				<td class="inputTd">${vipStudent.actualPay}</td>
			</tr>



			<tr>
				<td class="inputLabelTd">录入人：</td>
				<td class="inputTd">${continuePay.operator}</td>
				<td class="inputLabelTd">备注：</td>
				<td class="inputTd">${continuePay.notes}</td>
			</tr>
			<tr>
				<td class="inputTd" colspan="4" style="text-align: center;"><input
					class="ti_bottom" type="button" id="close_button" value="关闭" /></td>
			</tr>
		</table>
	</div>
</body>
</html>
