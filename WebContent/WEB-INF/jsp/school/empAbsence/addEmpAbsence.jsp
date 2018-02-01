<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/styles/extends/css/expenseAccount.css" />
<script type="text/javascript">
var createDate = new Date().format('yyyy-MM-dd hh:mm:ss');//获取今日时间
$(function() {
	$("#edit_createDate").val(createDate);
	//绑定提交按钮click事件
	$("#submit").click(function() {
		$("#submit").prop('disabled', true).css({'cursor':'not-allowed'});
		showMessage("正在处理，请稍后...");
		if(!biz.validate("valid",$('#empAbsenceFormEdit')[0])){
			showWarn("数据验证失败",3000);
			$("#submit").prop('disabled', false).css({'cursor':'pointer'});
			return;
		}
		
		var options = {
			url : "<m:url value='/empAbsence/addEmpAbsence.do'/>",
			type : "post",
				dataType:"json",
				success : function(d) {
					if(d.status){
						showMessage(d.message,"","",function(){
							window.parent.closeAdd();
				     		window.parent.doSearch();
						});
					}else{
						showMessage(d.message);
					}
				}
		};
		// 将options传给ajaxForm
		$('#empAbsenceFormEdit').ajaxSubmit(options);
	});

	/*补签日期格式化*/
	new biz.datepicker({
		id : "#edit_absenceDate",
		dateFmt:'yyyy-MM-dd HH:mm:ss',
		maxDate:'%y-%M-%d'
	});
	/*编辑表单数据验证*/
	new biz.validate({
		id:"#empAbsenceFormEdit",
		rules:{
			"deptName" :{required : true},
			"content" :{required : true},
			"absenceDate" :{required : true},
		}
	}); 
});
/*自动搜寻转化人 自动完成(Autocomplete) 根据用户输入值进行搜索和过滤,让用户快速找到并从预设值列表中选择*/
function getEmpIDByName(obj,value) {
	var did = $("#employeeList").find("option[value="+value+"]").attr('did');
	$(obj).parents('td').find("#edit_straightLeader").val(did);
}
</script>
</head>

<body>

	<div id="editDialog">
		<form id="empAbsenceFormEdit">
			<div class="wrap">
				<input type="hidden" id="edit_id" name="id" type="text"
					value="${empAbsence.id}" /> <input name="checkFlag" type="hidden"
					value="1" />
				<div class="top_head">
					<h2 class="top_name">补签申请表</h2>
					<div class="time_bg"
						style="top: 40px; left: 0px; height: 25px; line-height: 25px; position: absolute; width: 220px">
						<span>申请时间：</span> <input id="edit_createDate" name="createDate"
							class="input_select" style="height: 25px; width: 100px" readonly />
						<i class="search_time_ico2" style="top: 6px;"></i>
					</div>
				</div>
				<div class="center_body">
					<table class="table">
						<tr>
							<td class="inputLabelTd"><span class="required">*</span>所属部门：</td>
							<td class="inputTd"><input id="edit_deptName"
								name="deptName" type="text" class="text" value="${user.dept}"
								readonly /></td>
							<td class="inputLabelTd"><span class="required">*</span>申请人：</td>
							<td class="inputTd"><input id="edit_enterName"
								name="enterName" type="text" class="text"
								value="${user.nickName}" readonly /> <input id="edit_empId"
								name="empId" type="hidden" class="text" value="${user.id}"
								readonly /></td>
						</tr>
						<tr class="trDetails" style="height: 140px;">
							<td class="inputLabelTd"><span class="required">*</span>申请原因：</td>
							<td class="inputTd" colspan="3"><textarea class="text"
									name="content" id="edit_content"></textarea></td>
						</tr>
						<tr>
							<td class="inputLabelTd"><span class="required">*</span>补签时间：</td>
							<td class="inputTd">
								<div class="time_bg" style="width: 98%;">
									<input type="text" class="search_time150 valid text"
										name="absenceDate" id="edit_absenceDate" style="width: 88%;">
									<!-- 时间选择控件-->
									<i class="search_time_ico2"></i>
								</div>
							</td>
							<td class="inputLabelTd"><span class="required">*</span>补签类型：</td>
							<td class="inputTd"><select name="absenceType"
								id="edit_absenceType" class="input_select">
									<option value="1">上午上班</option>
									<option value="2">上午下班</option>
									<option value="3">下午上班</option>
									<option value="4">下午下班</option>
							</select></td>
						</tr>
						<tr>
							<td class="inputLabelTd"><span class="required">*</span>直接负责人：
							</td>
							<td class="inputTd"><input id="edit_straightLeader"
								name="straightLeader" type="hidden" /> <input
								id="edit_straightLeaderName" name="straightLeaderName"
								type="text" class="text" list="employeeList"
								onchange="getEmpIDByName(this,this.value);" /> <datalist
									id="employeeList">
									<c:forEach var="emp" items="${emp}">
										<option did="${emp.id}" value="${emp.nickName}"
											label="${emp.name}"></option>
									</c:forEach>
								</datalist></td>
							<td class="inputLabelTd"><span class="required">*</span>部门经理：
							</td>
							<td class="inputTd"><input id="edit_deptLeader"
								name="deptLeader" type="text" class="text"
								value="${user.deptLeader}" readonly /></td>
						</tr>
					</table>
					<div class="inputTd" style="display: block; text-align: center;">
						<input id="submit" type="button" class="ti_bottom" value="提交申请" />
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
