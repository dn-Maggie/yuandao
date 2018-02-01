<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<script type="text/javascript">
$(function() {
	//绑定提交按钮click事件
	$("#submit").click(function() {
		if(!biz.validate("valid",$('#empSalaryFormEdit')[0])){
			showWarn("数据验证失败",3000);
			return;
		}
		var options = {
			url : "<m:url value='/empSalary/addEmpSalary.do'/>",
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
		$('#empSalaryFormEdit').ajaxSubmit(options);
	});

	/*编辑表单数据验证*/
	new biz.validate({
		id:"#empSalaryFormEdit",
		rules:{
		}
	}); 
});
</script>
</head>

<body>

	<div id="editDialog">
		<form id="empSalaryFormEdit">
			<div class="ui-table ui-widget ui-corner-all ui-border">
				<input type="hidden" id="edit_id" name="id" type="text"
					value="${empSalary.id}" />
				<table class="table">
					<tr>
						<td class="inputLabelTd">员工姓名</td>
						<td class="inputTd"><select id="edit_empId" name="empId"
							class="input_select">
								<c:forEach var="emp" items="${empList}">
									<option value="${emp.id}">
										<c:out value="${emp.name}"></c:out>
									</option>
								</c:forEach>
						</select></td>
						<td class="inputLabelTd">基本工资：</td>
						<td class="inputTd"><input id="edit_basicSalay"
							name="basicSalay" type="text" class="text"
							value="${empSalary.basicSalay}" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd">应发工资：</td>
						<td class="inputTd"><input id="edit_shouldSalary"
							name="shouldSalary" type="text" class="text"
							value="${empSalary.shouldSalary}" /></td>
						<td class="inputLabelTd">社保扣款：</td>
						<td class="inputTd"><input id="edit_socialSecurity"
							name="socialSecurity" type="text" class="text"
							value="${empSalary.socialSecurity}" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd">请假扣款：</td>
						<td class="inputTd"><input id="edit_leaveCost"
							name="leaveCost" type="text" class="text"
							value="${empSalary.leaveCost}" /></td>
						<td class="inputLabelTd">餐补：</td>
						<td class="inputTd"><input id="edit_tableMoney"
							name="tableMoney" type="text" class="text"
							value="${empSalary.tableMoney}" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd">住房补贴：</td>
						<td class="inputTd"><input id="edit_housingAllowance"
							name="housingAllowance" type="text" class="text"
							value="${empSalary.housingAllowance}" /></td>
						<td class="inputLabelTd">绩效奖金：</td>
						<td class="inputTd"><input id="edit_meritRaise"
							name="meritRaise" type="text" class="text"
							value="${empSalary.meritRaise}" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd">其他：</td>
						<td class="inputTd"><input id="edit_rests" name="rests"
							type="text" class="text" value="${empSalary.rests}" /></td>
						<td class="inputLabelTd">实发工资：</td>
						<td class="inputTd"><input id="edit_actualSalary"
							name="actualSalary" type="text" class="text"
							value="${empSalary.actualSalary}" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd">备注：</td>
						<td class="inputTd"><input id="edit_note" name="note"
							type="text" class="text" value="${empSalary.note}" /></td>
					<tr>
						<td class="inputTd" colspan="4" style="text-align: center;">
							<input id="submit" type="button" class="ti_bottom" value="保存" />
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>
</html>
