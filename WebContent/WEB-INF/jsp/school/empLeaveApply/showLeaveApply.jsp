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
				<td class="inputLabelTd">所属部门：</td>
				<td class="inputTd">${leaveApply.empDept}</td>
				<td class="inputLabelTd">申请人：</td>
				<td class="inputTd">${leaveApply.empName}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">请假事由：</td>
				<td class="inputTd">${leaveApply.content}</td>
				<td class="inputLabelTd">请假天数：</td>
				<td class="inputTd">${leaveApply.leaveDate}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">请假日期始：</td>
				<td class="inputTd">${leaveApply.startDate}</td>
				<td class="inputLabelTd">请假日期止：</td>
				<td class="inputTd">${leaveApply.endDate}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">申请日期：</td>
				<td class="inputTd">${leaveApply.createDate}</td>
				<td class="inputLabelTd">审核日期：</td>
				<td class="inputTd">${leaveApply.checkDate}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">请假类型：</td>
				<td class="inputTd">
					<c:if test="${leaveApply.leaveType=='1'}">事假</c:if>
					<c:if test="${leaveApply.leaveType=='2'}">公假</c:if>
					<c:if test="${leaveApply.leaveType=='3'}">病假</c:if>
					<c:if test="${leaveApply.leaveType=='4'}">婚假</c:if>
				</td>
				<td class="inputLabelTd">审核标志：</td>
				<td class="inputTd">
					<c:if test="${leaveApply.checkFlag =='1'}">待审核</c:if>
					<c:if test="${leaveApply.checkFlag =='2'}">已审核</c:if>
					<c:if test="${leaveApply.checkFlag =='3'}">已驳回</c:if>
				</td>
			</tr>
			<c:if test="${not empty  leaveApply.fileUrl}">
			<tr>
				<td class="inputTd" colspan="4">
					<div id="preview" style="width: 100%; margin-top: 5px; margin-bottom: 5px; text-align: center;">
						<img alt="病历证明" style="" src="${leaveApply.fileUrl}">
					</div>
				</td>
			</tr>
			</c:if>
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
