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
</head>

<body>
	<div class="ui-table ui-widget ui-corner-all ui-border">
		<table class="table forview">
			<tr>
				<td class="inputLabelTd">退款学员姓名：</td>
				<td class="inputTd">${vipRefund.stuName}</td>
				<td class="inputLabelTd">退款学员QQ：</td>
				<td class="inputTd">${vipStudent.qq}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">报名学科：</td>
				<td class="inputTd">${vipStudent.subjectName}</td>
				<td class="inputLabelTd">报名课程：</td>
				<td class="inputTd">${vipStudent.courseName}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">报名日期：</td>
				<td class="inputTd">${vipStudent.joinTime}</td>
				<td class="inputLabelTd">退款时间：</td>
				<td class="inputTd">${vipRefund.time}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">退款金额：</td>
				<td class="inputTd">${vipRefund.refund}</td>
				<td class="inputLabelTd">退款原因：</td>
				<td class="inputTd">${vipRefund.reason}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">录入人：</td>
				<td class="inputTd">${vipRefund.operator}</td>
				<td class="inputLabelTd">备注：</td>
				<td class="inputTd">${vipStudent.notes}</td>
			</tr>
			<tr>
				<td class="inputTd" colspan="4" style="text-align: center;"><input
					class="ti_bottom" type="button" id="close_button"
					onclick="window.parent.closeShow();" value="关闭" /></td>
			</tr>
		</table>
	</div>
</body>
</html>
