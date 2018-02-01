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
				<td class="inputLabelTd"><span class="required">*</span>任务名称：</td>
				<td class="inputTd">${taskConfig.taskName}</td>
				<td class="inputLabelTd">任务类型：</td>
				<td class="inputTd"><c:if test="${taskConfig.taskType eq 0}">循环</c:if>
					<c:if test="${taskConfig.taskType eq 1}">定时</c:if></td>
			</tr>
			<tr>
				<td class="inputLabelTd"><span class="required">*</span>执行类名：</td>
				<td class="inputTd" colspan="3">${taskConfig.className}</td>
			</tr>
			<tr>
				<td class="inputLabelTd"><span class="required">*</span>执行方法：</td>
				<td class="inputTd" colspan="3">${taskConfig.classMethod}</td>
			</tr>
			<tr>

				<td class="inputLabelTd">任务状态：</td>
				<td class="inputTd"><c:if test="${taskConfig.taskStatus eq 1}">
						<i value="0" class="state_icons icon_enabled"></i>启用</c:if> <c:if
						test="${taskConfig.taskStatus eq 0}">
						<i value="0" class="state_icons icon_nochange"></i>停用</c:if></td>
				<td class="inputLabelTd"><span class="required">*</span>任务顺序：</td>
				<td class="inputTd">${taskConfig.taskOrder}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">任务描述：</td>
				<td class="inputTd" colspan="3"
					style="padding-top: 5px; padding-bottom: 5px;">
					<div style="width: 480px; height: 65px;">${taskConfig.describe}</div>
				</td>
			</tr>
			<tr>
				<td class="inputLabelTd">开始时间：</td>
				<td class="inputTd">${taskConfig.startTime}</td>
				<td class="inputLabelTd"></td>
				<td class="inputTd"></td>
			</tr>
			<tr>
				<td class="inputLabelTd">执行频率：</td>
				<td class="inputTd" colspan="3">年：<input id="septYears"
					name="septYears" type="text" class="text" style="width: 40px;"
					value="${taskConfig.septYears}" /> 月：<input id="septMonths"
					name="septMonths" type="text" class="text" style="width: 20px;"
					value="${taskConfig.septMonths}" /> 周：<input id="septWeeks"
					name="septWeeks" type="text" class="text" style="width: 20px;"
					value="${taskConfig.septWeeks}" /> 日：<input id="septDays"
					name="septDays" type="text" class="text" style="width: 20px;"
					value="${taskConfig.septDays}" /> 时：<input id="septHours"
					name="septHours" type="text" class="text" style="width: 20px;"
					value="${taskConfig.septHours}" /> 分：<input id="septMinutes"
					name="septMinutes" type="text" class="text" style="width: 20px;"
					value="${taskConfig.septMinutes}" /> 秒：<input id="septSeconds"
					name="septSeconds" type="text" class="text" style="width: 20px;"
					value="${taskConfig.septSeconds}" />
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
