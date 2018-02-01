<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html  >
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<script type="text/javascript">
$(function() {
	//绑定提交按钮click事件
	$("#submit").click(function() {
		if(!biz.validate("valid",$('#dutyLevelFormEdit')[0])){
			showWarn("数据验证失败",3000);
			return;
		}
		var options = {
			url : "<m:url value='/dutyLevel/updateDutyLevel.do'/>",
			type : "post",
				dataType:"json",
				success : function(d) {
					if(d.status){
						showMessage(d.message,"","",function(){
							window.parent.closeEdit();
							List.doSearch(window.parent.gridObj);
						});
					}else{
						showMessage(d.message);
					}
				}
		};
		// 将options传给ajaxForm
		$('#dutyLevelFormEdit').ajaxSubmit(options);
	});

	/*编辑表单数据验证*/
	new biz.validate({
		id:"#dutyLevelFormEdit",
		rules:{
		}
	}); 
});

</script>
</head>

<body>
	<form id="dutyLevelFormEdit">
		<div class="ui-table ui-widget ui-corner-all ui-border">
			<input type="hidden" id="edit_id" name="id" type="text"
				value="${dutyLevel.id}" />
			<table class="table">
				<tr>
					<td class="inputLabelTd">岗位ID：</td>
					<td class="inputTd"><input id="edit_dutyId" name="dutyId"
						type="text" class="text" value="${dutyLevel.dutyId}" /></td>
					<td class="inputLabelTd">等级类型（P/T/D/J）：</td>
					<td class="inputTd"><input id="edit_levelType"
						name="levelType" type="text" class="text"
						value="${dutyLevel.levelType}" /></td>
				</tr>
				<tr>
					<td class="inputLabelTd">评定登记级别：</td>
					<td class="inputTd"><input id="edit_levelName"
						name="levelName" type="text" class="text"
						value="${dutyLevel.levelName}" /></td>
					<td class="inputLabelTd">薪级工资：</td>
					<td class="inputTd"><input id="edit_levelSal" name="levelSal"
						type="text" class="text" value="${dutyLevel.levelSal}" /></td>
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
