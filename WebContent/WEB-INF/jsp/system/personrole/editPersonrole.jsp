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
		if(!biz.validate("valid",$('#personroleFormEdit')[0])){
			showWarn("<m:message code='validation.object'/>",3000);
			return;
		}
		var options = {
			url : "<m:url value='/personrole/updatePersonrole.do'/>",
			type : "post",
			success : function(data) {
				var d = $ .parseJSON(data);
				showMessage(d.msg,"","",function(){
					window.parent.closeEdit();
		     		window.parent.doSearch(true);
				});
			}
		};
		// 将options传给ajaxForm
		$('#personroleFormEdit').ajaxSubmit(options);
	});

	/*编辑表单数据验证*/
	new biz.validate({
		id:"#personroleFormEdit",
		rules:{
		}
	}); 
});

</script>
</head>

<body>
	<form id="personroleFormEdit">
		<hi:icssToken />
		<div class="ui-table ui-widget ui-corner-all ui-border">
			<input type="hidden" id="edit_id" name="id" type="text"
				value="${personroleid}" />
			<table class="table">
				<tr>
					<td class="inputLabelTd"><m:message code="personrole.roleId" />:</td>
					<td class="inputTd"><input id="edit_roleId" name="roleId"
						type="text" class="text" value="${personrole.roleId}" /></td>
					<td class="inputLabelTd"><m:message
							code="personrole.createTime" />:</td>
					<td class="inputTd"><input id="edit_createTime"
						name="createTime" type="text" class="text"
						value="${personrole.createTime}" /></td>
				</tr>
				<tr>
					<td class="inputLabelTd"><m:message code="personrole.userId" />:</td>
					<td class="inputTd" colspan="3"><input id="edit_userId"
						name="userId" type="text" class="text"
						value="${personrole.userId}" /></td>
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
