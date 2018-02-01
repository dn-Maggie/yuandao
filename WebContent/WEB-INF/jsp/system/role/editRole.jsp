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
		if(!biz.validate("valid",$('#roleFormEdit')[0])){
			showWarn("<m:message code='validation.object'/>",3000);
			return;
		}
		var options = {
			url : "<m:url value='/role/updateRole.do'/>",
			type : "post",
			dataType:"json",
			success : function(d) {
				if(d.status){
					showMessage(d.message,"","",function(){
						window.parent.closeEdit();
			     		window.parent.doSearch(true);
					});
				}else{
					showMessage(d.message);
				}
				
			}
		};
		// 将options传给ajaxForm
		$('#roleFormEdit').ajaxSubmit(options);
	});

	/*编辑表单数据验证*/
	new biz.validate({
		id:"#roleFormEdit",
		rules:{
			"roleName":{required: true,maxlength:120},
			"roleCode":{required: true,maxlength:50},
			"memo":{maxlength:100}
		}
	}); 
	new biz.select({//状态下拉
	    id:"#edit_states",
	    url:"<m:url value='/dictInfo/getDictByTypeCode.do?dictTypeCode=status'/>",
	    value:"${role.states}"
	});
	//设置日期控件联动
	new biz.datepicker({       
		id:"#startTime",       
		dateFmt:'yyyy-MM-dd HH:mm:ss',
		maxDate:'#F{$dp.$D(\'endTime\')}'
	});
	new biz.datepicker({       
		id:"#endTime",       
		dateFmt:'yyyy-MM-dd HH:mm:ss',
		minDate:'#F{$dp.$D(\'startTime\')}'
	});
});

</script>
</head>

<body>
	<form id="roleFormEdit">
		<hi:icssToken />
		<div class="ui-table ui-widget ui-corner-all ui-border">
			<input type="hidden" id="edit_roleId" name="roleId" type="text"
				value="${role.roleId}" />
			<table class="table">
				<tr>
					<td class="inputLabelTd"><span class="required">*</span>
					<m:message code="role.roleName" />:</td>
					<td class="inputTd"><input id="edit_roleName" name="roleName"
						type="text" class="text" value="${role.roleName}" /></td>
					<td class="inputLabelTd"><span class="required">*</span>
					<m:message code="role.roleCode" />:</td>
					<td class="inputTd"><input id="edit_roleCode" name="roleCode"
						type="text" class="text" value="${role.roleCode}" /></td>
				</tr>
				<%-- 			<tr>
				<td class="inputLabelTd"><m:message code="role.startTime"/>:</td>
				<td class="inputTd">
					<input id="startTime" name="startTime" type="text" class="Wdate" value="${role.startTime}"/>
				</td>
				<td class="inputLabelTd"><m:message code="role.endTime"/>:</td>
				<td class="inputTd">
					<input id="endTime" name="endTime" type="text" class="Wdate" value="${role.endTime }"/>
				</td>
			</tr> --%>
				<tr>
					<td class="inputLabelTd"><m:message code="role.roleDesc" />:</td>
					<td class="inputTd"><input id="edit_roleDesc" name="roleDesc"
						type="text" class="text" value="${role.roleDesc}" /></td>
					<td class="inputLabelTd"><m:message code="role.states" />:</td>
					<td class="inputTd"><select id="edit_states" name="states"
						class="select"></select></td>

				</tr>
				<tr>
					<td class="inputLabelTd"><m:message code="role.memo" />:</td>
					<td class="inputTd" colspan="3"><textarea rows="5" cols="120"
							name="memo" id="edit_memo" class="common_txtarea" maxlength="200"><c:out
								value="${role.memo}" /></textarea></td>
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
