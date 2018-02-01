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
		if(!biz.validate("valid",$('#empAbsenceFormEdit')[0])){
			showWarn("数据验证失败",3000);
			return;
		}
		var options = {
			url : "<m:url value='/empAbsence/updateEmpAbsence.do'/>",
			type : "post",
				dataType:"json",
				success : function(d) {
					if(d.status){
						showMessage(d.message,"","",function(){
							window.parent.closeEdit();
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

	/*编辑表单数据验证*/
	new biz.validate({
		id:"#empAbsenceFormEdit",
		rules:{
		}
	}); 
});

</script>
</head>

<body>
	<form id="empAbsenceFormEdit">
		<div class="ui-table ui-widget ui-corner-all ui-border">
			<input type="hidden" id="edit_id" name="id" type="text"
				value="${empAbsence.id}" />
			<table class="table">
				<tr>
					<td class="inputLabelTd">：</td>
					<td class="inputTd"><input id="edit_empId" name="empId"
						type="text" class="text" value="${empAbsence.empId}" /></td>
					<td class="inputLabelTd">：</td>
					<td class="inputTd"><input id="edit_content" name="content"
						type="text" class="text" value="${empAbsence.content}" /></td>
				</tr>
				<tr>
					<td class="inputLabelTd">：</td>
					<td class="inputTd"><input id="edit_absenceDate"
						name="absenceDate" type="text" class="text"
						value="${empAbsence.absenceDate}" /></td>
					<td class="inputLabelTd">：</td>
					<td class="inputTd"><input id="edit_createDate"
						name="createDate" type="text" class="text"
						value="${empAbsence.createDate}" /></td>
				</tr>
				<tr>
					<td class="inputTd" colspan="4" style="text-align: center;"><input
						id="submit" type="button" class="ti_bottom"
						value="<m:message code='button.save' />" /></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
