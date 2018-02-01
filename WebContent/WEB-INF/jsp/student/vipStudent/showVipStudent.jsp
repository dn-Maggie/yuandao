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
			<tr style="text-indent: 4em">
				<td colspan="4"><i class="icon_bg icon_plan"></i> 学生基本信息</td>
			</tr>
			<tr>
				<td class="inputLabelTd">学员姓名：</td>
				<td class="inputTd">${vipStudent.name}</td>
				<td class="inputLabelTd">手机号码：</td>
				<td class="inputTd">${vipStudent.phone}</td>
				
			</tr>
			<tr>
				<td class="inputLabelTd">QQ：</td>
				<td class="inputTd">${vipStudent.qq}</td>
				<td class="inputLabelTd">年龄：</td>
				<td class="inputTd">${vipStudent.age}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">QQ昵称：</td>
				<td class="inputTd">${vipStudent.qqNick}</td>
				<td class="inputLabelTd">学号：</td>
				<td class="inputTd">${vipStudent.eduBack}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">报名学科：</td>
				<td class="inputTd">${vipStudent.subjectName}</td>
				<td class="inputLabelTd">报名 课程：</td>
				<td class="inputTd">${vipStudent.courseName}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">听课讲师：</td>
				<td class="inputTd">${vipStudent.teacherId}</td>
				<td class="inputLabelTd">班主任：</td>
				<td class="inputTd">${vipStudent.tutorId}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">付款状态：</td>
				<td class="inputTd">${vipStudent.currStatus}</td>
				<td class="inputLabelTd">报名时间：</td>
				<td class="inputTd">${vipStudent.joinTime}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">备注：</td>
				<td class="inputTd" colspan="3">${vipStudent.notes}</td>
			</tr>
			<tr style="text-indent: 4em">
				<td colspan="4"><i class="icon_bg icon_plan"></i> 学生付款信息</td>
			</tr>
			<tr>
				<td class="inputLabelTd">应缴学费：</td>
				<td class="inputTd">${vipStudent.shouldPay}</td>
				<td class="inputLabelTd">实缴学费：</td>
				<td class="inputTd">${vipStudent.actualPay}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">欠缴学费：</td>
				<td class="inputTd">${vipStudent.owePay}</td>
				<td class="inputLabelTd">付款方姓名：</td>
				<td class="inputTd">${vipStudent.payerName}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">付款账号：</td>
				<td class="inputTd">${vipStudent.payAccount}</td>
				<td class="inputLabelTd">付款方式：</td>
				<td class="inputTd">${vipStudent.payType}</td>
			</tr>
			<tr style="text-indent: 4em">
				<td colspan="4"><i class="icon_bg icon_plan"></i> 学生来源信息</td>
			</tr>
			<tr>
				<td class="inputLabelTd">转化人类型：</td>
				<td class="inputTd">${vipStudent.followerPosition}</td>
				<td class="inputLabelTd">转化人姓名：</td>
				<td class="inputTd">${vipStudent.followerName}</td>
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
