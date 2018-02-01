<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Calendar calendar = Calendar.getInstance();
Integer year = calendar.get(Calendar.YEAR);
%>
<head>
<base href="<%=basePath%>">

<%@ include file="../../common/header.jsp"%>
<script type="text/javascript">
var today= new Date(); 
var y = today.getFullYear();

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
				<td class="inputLabelTd">员工工号：</td>
				<td class="inputTd">${employee.empNo}</td>
				<td class="inputLabelTd">职工昵称：</td>
				<td class="inputTd">${employee.nickName}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">所属部门：</td>
				<td class="inputTd">${employee.dept}</td>
				<td class="inputLabelTd">所在岗位：</td>
				<td class="inputTd">${employee.position}</td>

			</tr>
			<tr>
				<td class="inputLabelTd">在职状态：</td>
				<td class="inputTd"><c:if test="${employee.currState eq 1}">
						<c:out value="在职"></c:out>
					</c:if> <c:if test="${employee.currState eq 2}">
						<c:out value="试用"></c:out>
						<c:if test="${employee.probation eq 1}">
							<c:out value="一个月"></c:out>
						</c:if>
						<c:if test="${employee.probation eq 2}">
							<c:out value="两个月"></c:out>
						</c:if>
						<c:if test="${employee.probation eq 3}">
							<c:out value="三个月"></c:out>
						</c:if>
					</c:if> <c:if test="${employee.currState eq 3}">
						<c:out value="离职"></c:out>
					</c:if> <c:if test="${employee.currState eq 4}">
						<c:out value="兼职"></c:out>
					</c:if></td>
				<td class="inputLabelTd">入职日期：</td>
				<td class="inputTd">${employee.entryDate}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">转正日期：</td>
				<td class="inputTd">${employee.beFullDate}</td>
				<td class="inputLabelTd">离职日期：</td>
				<td class="inputTd">${employee.leaveDate}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">姓名：</td>
				<td class="inputTd">${employee.name}</td>
				<td class="inputLabelTd">手机号码：</td>
				<td class="inputTd">${employee.mobile}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">性别：</td>
				<td class="inputTd">${employee.sex}</td>
				<td class="inputLabelTd">年龄：</td>
				<td class="inputTd">${employee.age}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">籍贯：</td>
				<td class="inputTd">${employee.nativePlace}</td>
				<td class="inputLabelTd">民族：</td>
				<td class="inputTd">${employee.nation}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">户籍类型：</td>
				<td class="inputTd">${employee.nodeType}</td>
				<td class="inputLabelTd">婚姻状况：</td>
				<td class="inputTd">${employee.maritalStatus}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">身份证号码：</td>
				<td class="inputTd">${employee.idCard}</td>
				<td class="inputLabelTd">目前住址：</td>
				<td class="inputTd">${employee.currentAddress}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">通讯地址：</td>
				<td class="inputTd">${employee.mailAddress}</td>
				<td class="inputLabelTd">邮政编码：</td>
				<td class="inputTd">${employee.postcode}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">最高学历：</td>
				<td class="inputTd">${employee.educationBackground}</td>
				<td class="inputLabelTd">专业：</td>
				<td class="inputTd">${employee.profession}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">外语水平：</td>
				<td class="inputTd">${employee.foreignLanguage}</td>
				<td class="inputLabelTd">职业资格：</td>
				<td class="inputTd">${employee.professionalQualification}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">教育经历：</td>
				<td class="inputTd">${employee.educationExperience}</td>
				<td class="inputLabelTd">工作经历：</td>
				<td class="inputTd">${employee.workExperience}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">家庭成员：</td>
				<td class="inputTd">${employee.familyMember}</td>
				<td class="inputLabelTd">紧急联络人：</td>
				<td class="inputTd">${employee.emergencyContact}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">薪级工资：</td>
				<td class="inputTd">${employee.salary}</td>
				<td class="inputLabelTd">基本工资：</td>
				<td class="inputTd">${employee.salValue}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">是否被考核：</td>
				<td class="inputTd"><c:if test="${employee.isAssess eq 1}">
						<c:out value="是"></c:out>
					</c:if> <c:if test="${employee.isAssess eq 2}">
						<c:out value="否"></c:out>
					</c:if></td>
				<td class="inputLabelTd">考核人：</td>
				<td class="inputTd"><c:if test="${employee.isAssess eq 1}">
						<c:out value="${employee.checkName}"></c:out>
					</c:if> <c:if test="${employee.isAssess eq 2}">
						<c:out value="无"></c:out>
					</c:if></td>
			</tr>
			<tr>
				<td class="inputLabelTd">考核标准：</td>
				<td class="inputTd"><c:if test="${employee.checkStanderd eq 0}">
						<c:out value="无"></c:out>
					</c:if> <c:if test="${employee.checkStanderd eq 1}">
						<c:out value="职能部门基本工资考核"></c:out>
					</c:if> <c:if test="${employee.checkStanderd eq 2}">
						<c:out value="客服基本工资考核"></c:out>
					</c:if> <c:if test="${employee.checkStanderd eq 3}">
						<c:out value="公开课讲师基本工资考核"></c:out>
					</c:if> <c:if test="${employee.checkStanderd eq 4}">
						<c:out value="VIP讲师基本工资考核"></c:out>
					</c:if> <c:if test="${employee.checkStanderd eq 5}">
						<c:out value="助教基本工资考核"></c:out>
					</c:if> <c:if test="${employee.checkStanderd eq 6}">
						<c:out value="班主任基本工资考核"></c:out>
					</c:if></td>
			</tr>
			<tr>
				<td class="inputLabelTd">微信/支付宝</td>
				<td class="inputTd">${employee.bankName}</td>
				<td class="inputLabelTd">账号：</td>
				<td class="inputTd">${employee.bankCard}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">邮箱地址：</td>
				<td class="inputTd">${employee.foxmail}</td>
				<td class="inputLabelTd">备注：</td>
				<td class="inputTd">${employee.note}</td>
			</tr>

			<tr>
				<td class="inputTd" colspan="4" style="text-align: center;"><input
					class="ti_bottom" type="button" id="close_button" value="关闭" /></td>
			</tr>
		</table>
	</div>
</body>
</html>
