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
				<td colspan="4"><i class="icon_bg icon_plan"></i> 学生信息</td>
			</tr>
			<tr>
				<td class="inputLabelTd">学生姓名：</td>
				<td class="inputTd">${vipStudent.name}</td>
				<td class="inputLabelTd">学生QQ：</td>
				<td class="inputTd">${vipStudent.qq}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">报名学科：</td>
				<td class="inputTd">${vipStudent.subjectName}</td>
				<td class="inputLabelTd">报名课程：</td>
				<td class="inputTd">${vipStudent.courseName}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">应缴学费：</td>
				<td class="inputTd">${vipStudent.shouldPay}</td>
				<td class="inputLabelTd">实缴学费：</td>
				<td class="inputTd">${vipStudent.actualPay}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">报名日期：</td>
				<td class="inputTd">${empPerformance.createDate}</td>
				<td class="inputLabelTd">备注：</td>
				<td class="inputTd">${vipStudent.notes}</td>
			</tr>
			<tr>
				<td colspan="4"><i class="icon_bg icon_plan"></i> 转化信息</td>
			</tr>
			<tr>
				<td class="inputLabelTd">转化人姓名：</td>
				<td class="inputTd">${empPerformance.employeeName}</td>
				<td class="inputLabelTd">参与岗位：</td>
				<td class="inputTd">${empPerformance.position}</td>
			<tr>
			<tr>
				<td class="inputLabelTd">流量来源：</td>
				<td class="inputTd">${empPerformance.comSource}</td>
				<td class="inputLabelTd">来源分类：</td>
				<td class="inputTd">${empPerformance.sourceSubclass}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">分配金额：</td>
				<td class="inputTd">${empPerformance.actualPay}</td>
				<td class="inputLabelTd">分配时间：</td>
				<td class="inputTd">${empPerformance.createDate}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">贡献绩效：</td>
				<td class="inputTd">${empPerformance.performance}</td>
				<td class="inputLabelTd">备注：</td>
				<td class="inputTd">${empPerformance.note}</td>
			</tr>
			<tr>
				<td class="inputTd" colspan="4" style="text-align: center;"><input
					class="ti_bottom" type="button" id="close_button" value="关闭" /></td>
			</tr>
		</table>
	</div>
</body>
</html>
