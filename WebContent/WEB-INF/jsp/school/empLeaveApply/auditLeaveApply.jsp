<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/styles/extends/css/expenseAccount.css" />
</head>
<body>
	<div id="editDialog">
		<form id="leaveApplyFormEdit">
			<div class="wrap">
				<input type="hidden" id="edit_id" name="id" type="text"
					value="${leaveApply.id}" /> <input name="checkFlag" type="hidden"
					value="2" /> <input name="isCost" type="hidden" value="1" />
				<div class="top_head">
					<h2 class="top_name">请假申请</h2>
					<div class="time_bg"
						style="top: 40px; left: 0px; height: 25px; line-height: 25px; position: absolute; width: 200px">
						<span><span class="required">*</span>请假类型：</span> <select
							name="leaveType" id="edit_leaveType" class="input_select"
							style="width: 100px">
							<option value="1"
								<c:if test="${leaveApply.leaveType==1}">selected</c:if>>事假</option>
							<option value="2"
								<c:if test="${leaveApply.leaveType==2}">selected</c:if>>公假</option>
						</select>
					</div>
					<div class="time_bg"
						style="top: 40px; right: 0px; height: 25px; line-height: 25px; position: absolute; width: 200px">
						<span>申请日期：</span> <input id="edit_createDate" name="createDate"
							type="text" value="${leaveApply.createDate}"
							style="height: 25px;" readonly /> <i class="search_time_ico2"
							style="top: 6px;"></i>
					</div>
				</div>
				<div class="center_body">
					<table class="table">
						<tr>
							<td class="inputLabelTd"><span class="required">*</span>所属部门：</td>
							<td class="inputTd"><input id="edit_deptName"
								name="deptName" type="text" class="text"
								value="${leaveApply.empDept}" readonly /></td>
							<td class="inputLabelTd"><span class="required">*</span>申请人：</td>
							<td class="inputTd"><input id="edit_enterName"
								name="enterName" type="text" class="text"
								value="${leaveApply.empNAME}" readonly /> <input id="edit_empId"
								name="empId" type="hidden" class="text"
								value="${leaveApply.empId}" readonly /></td>
						</tr>
						<tr class="trDetails" style="height: 140px;">
							<td class="inputLabelTd"><span class="required">*</span>请假事由：</td>
							<td class="inputTd" colspan="3"><textarea class="text"
									name="content" id="edit_content" readonly>${leaveApply.content}</textarea>
							</td>
						</tr>
						<tr>
							<td class="inputLabelTd"><span class="required">*</span>开始日期：</td>
							<td class="inputTd">
								<div class="time_bg" style="width: 98%;">
									<input type="text" class="search_time150 valid text"
										name="startDate" id="edit_startDate" style="width: 88%;"
										value="${leaveApply.startDate}" readonly />
									<!-- 时间选择控件-->
									<i class="search_time_ico2"></i>
								</div>
							</td>
							<td class="inputLabelTd"><span class="required">*</span>结束日期：</td>
							<td class="inputTd">
								<div class="time_bg" style="width: 98%;">
									<input type="text" class="search_time150 valid text"
										name="endDate" id="edit_endDate" style="width: 88%;"
										value="${leaveApply.endDate}" readonly />
									<!-- 时间选择控件-->
									<i class="search_time_ico2"></i>
								</div>
							</td>
						</tr>
						<tr>
							<td class="inputLabelTd"><span class="required">*</span>请假天数：</td>
							<td class="inputTd"><input value="${leaveApply.leaveDate}"
								id="edit_leaveDate" name="leaveDate" type="text" class="text"
								readonly /></td>
							<td class="inputLabelTd"><span class="required">*</span>直接负责人：</td>
							<td class="inputTd"><input
								value="${leaveApply.straightLeaderName}"
								id="edit_straightLeaderName" name="straightLeaderName"
								type="text" class="text" readonly /> <input
								value="${leaveApply.straightLeader}" id="edit_straightLeader"
								name="straightLeader" type="hidden" /></td>
						</tr>
					</table>

					<div class="inputTd" style="display: block; text-align: center;">
						<input id="submit" type="button" class="ti_bottom" value="审核通过" />
					</div>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
	var createDate = new Date().format('yyyy-MM-dd hh:mm:ss');//获取今日时间
	$(function() {
		//绑定提交按钮click事件
		$("#submit").click(function() {
			if(!biz.validate("valid",$('#leaveApplyFormEdit')[0])){
				showWarn("数据验证失败",3000);
				return;
			}
			var checkFlag = $("#edit_checkFlag").val();
			if(checkFlag=="2"){showWarn("该报销单已审核，无需重复审核");}else{
				var key = $("#edit_id").val();
				var paramDatas = {key:key};
				$.ajax({
					   type: "post",
					   url: "<m:url value='/leaveApply/auditLeaveApply.do'/>",
					   data: paramDatas,
					   cache: false,
					   dataType:"json",
					   success : function(response) {
								showMessage(response.msg,"","",function(){
									window.parent.closeAudit();
						     		window.parent.doSearch();
								});
						}
				});
			}
		});
	});
	</script>
</body>
</html>
