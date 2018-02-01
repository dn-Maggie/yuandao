<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
</head>
<body>
	<div class="ui-table ui-widget ui-corner-all ui-border">
		<table class="table forview">
			<tr>
				<td class="inputLabelTd">申请员工ID：</td>
				<td class="inputTd">${leaveApply.empId}</td>
				<td class="inputLabelTd">审核者ID：</td>
				<td class="inputTd">${leaveApply.checkId}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">请假事由：</td>
				<td class="inputTd">${leaveApply.content}</td>
				<td class="inputLabelTd">请假日期始：</td>
				<td class="inputTd">${leaveApply.startDate}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">请假日期止：</td>
				<td class="inputTd">${leaveApply.endDate}</td>
				<td class="inputLabelTd">请假天数：</td>
				<td class="inputTd">${leaveApply.leaveDate}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">申请日期：</td>
				<td class="inputTd">${leaveApply.createDate}</td>
				<td class="inputLabelTd">审核日期：</td>
				<td class="inputTd">${leaveApply.checkDate}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">审核标志：</td>
				<td class="inputTd">${leaveApply.checkFlag}</td>
				<td class="inputLabelTd">请假扣款：</td>
				<td class="inputTd">${leaveApply.costSalary}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">扣款标志：</td>
				<td class="inputTd">${leaveApply.isCost}</td>
				<td class="inputLabelTd">请假类型：</td>
				<td class="inputTd">${leaveApply.leaveType}</td>
			</tr>
			<tr>
				<td class="inputTd" colspan="4" style="text-align: center;"><input
					class="ti_bottom" type="button" id="close_button" value="关闭" /></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
		$(function() {
			//绑定提交按钮click事件
			$("#close_button").click(function() {
				window.parent.closeShow();
			});
		});
	</script>
</body>
</html>
