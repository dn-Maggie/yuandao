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
			if(!biz.validate("valid",$('#resActFormEdit')[0])){
				showWarn("<m:message code='validation.object'/>",3000);
				return;
			}
			var options = {
				url : "<m:url value='/resAct/updateResAct.do'/>",
				type : "post",
				success : function(data) {
					var d = $.parseJSON(data);
					if(d.status){
						showMessage(d.message,"","",function(){
							window.parent.closeEdit();
							window.parent.doSearch(true);
						});
					}else{
						showMessage(d.message);
					}
				},
				error : ICSS.utils.showErrorMessage
			};
			// 将options传给ajaxForm
			$('#resActFormEdit').ajaxSubmit(options);
		});
		
		new biz.select({//状态下拉
		    id:"#edit_states",
		    url:"<m:url value='/dictInfo/getDictByTypeCode.do?dictTypeCode=status'/>"
		});
		new biz.select({//是否需要权限下拉
		    id:"#edit_needRight",
		    url:"<m:url value='/dictInfo/getDictByTypeCode.do?dictTypeCode=yn_num'/>"
		});

		/*编辑表单数据验证*/
		new biz.validate({
			id:"#resActFormEdit",
			rules:{
				actName:{required: true,maxlength: 126},
				actCode:{required: true,maxlength: 126},
				actUrl:{maxlength: 254},
				states:{required: true},
				needRight:{required: true},
				memo:{maxlength: 126}
			}
		}); 
	});
</script>
</head>

<body>
	<form id="resActFormEdit">
		<hi:icssToken />
		<div class="ui-table ui-widget ui-corner-all ui-border">
			<input type="hidden" id="edit_id" name="id" type="text"
				value="${resAct.id}" /> <input type="hidden" id="edit_resuuId"
				name="resuuId" type="text" value="${resAct.resuuId}" />
			<table class="table">
				<tr>
					<td class="inputLabelTd"><span class="required">*</span>
					<m:message code="resAct.actName" />:</td>
					<td class="inputTd"><input id="edit_actName" name="actName"
						type="text" class="text"
						value="<c:out value='${resAct.actName}'/>" /></td>
					<td class="inputLabelTd"><span class="required">*</span>
					<m:message code="resAct.actCode" />:</td>
					<td class="inputTd"><input id="edit_actCode" name="actCode"
						type="text" class="text"
						value="<c:out value='${resAct.actCode}'/>" /></td>
				</tr>
				<tr>
					<td class="inputLabelTd"><m:message code="resAct.actUrl" />:</td>
					<td class="inputTd" colspan="3"><input id="edit_actUrl"
						name="actUrl" type="text" class="text"
						value="<c:out value='${resAct.actUrl}'/>" /></td>
				</tr>
				<tr>
					<td class="inputLabelTd"><span class="required">*</span>
					<m:message code="resAct.states" />:</td>
					<td class="inputTd"><select id="edit_states" name="states"
						class="select"></select></td>

					<td class="inputLabelTd"><span class="required">*</span>
					<m:message code="resAct.needRight" />:</td>
					<td class="inputTd"><select id="edit_needRight"
						name="needRight" class="select"></select></td>
				</tr>
				<tr>
					<td class="inputLabelTd"><m:message code="resAct.memo" />:</td>
					<td class="inputTd" colspan="3"><input id="edit_memo"
						name="memo" type="text" class="text"
						value="<c:out value='${resAct.memo}'/>" /></td>
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
