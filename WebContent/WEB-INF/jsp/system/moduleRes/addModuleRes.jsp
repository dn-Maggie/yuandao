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
		if(!biz.validate("valid",$('#moduleResFormEdit')[0])){
			showWarn("<m:message code='validation.object'/>",3000);
			return;
		}
		var options = {
			url : "<m:url value='/moduleRes/addModuleRes.do'/>",
			type : "post",
			dataType:'json',
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
		$('#moduleResFormEdit').ajaxSubmit(options);
	});
	
	new biz.select({//状态下拉
	    id:"#edit_status",
	    url:"<m:url value='/dictInfo/getDictByTypeCode.do?dictTypeCode=status'/>"
	});
	new biz.select({//状态下拉
	    id:"#edit_needright",
	    url:"<m:url value='/dictInfo/getDictByTypeCode.do?dictTypeCode=yn_num'/>"
	});
	new biz.select({//状态下拉
	    id:"#edit_resType",
	    url:"<m:url value='/dictInfo/getDictByTypeCode.do?dictTypeCode=resType'/>"
	});
	new biz.number({
		id:"#edit_serialindex",
		minNum:1,//指定最小值  
		fix:true//是否自动更正  
	}); 

	/*编辑表单数据验证*/
	new biz.validate({
		id:"#moduleResFormEdit",
		rules:{
			resourceName:{required: true,maxlength: 126},
			resourcecode:{required: true,maxlength: 45},
			resurl:{maxlength: 120},
			serialindex:{required:true,number:true},
			status:{required: true},
			needright:{required: true},
			memo:{maxlength: 126}
		}
	}); 
});
</script>
</head>

<body>

	<div id="editDialog">
		<form id="moduleResFormEdit">
			<hi:icssToken />
			<div class="ui-table ui-widget ui-corner-all ui-border">
				<input type="hidden" id="edit_id" name="id" type="text"
					value="${moduleRes.id}" />
				<table class="table">
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>
						<m:message code="moduleRes.resourceName" />:</td>
						<td class="inputTd"><input id="edit_resourceName"
							name="resourceName" type="text" class="text"
							value="<c:out value='${moduleRes.resourceName}'/>" /></td>
						<td class="inputLabelTd"><span class="required">*</span>
						<m:message code="moduleRes.serialindex" />:</td>
						<td class="inputTd"><input id="edit_serialindex"
							name="serialindex" type="text" class="text"
							value="<c:out value='${moduleRes.serialindex}'/>" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>
						<m:message code="moduleRes.resourcecode" />:</td>
						<td class="inputTd"><input id="edit_resourcecode"
							name="resourcecode" type="text" class="text"
							value="<c:out value='${moduleRes.resourcecode}'/>" /></td>
						<td class="inputLabelTd"><m:message code="moduleRes.resurl" />:</td>
						<td class="inputTd"><input id="edit_resurl" name="resurl"
							type="text" class="text"
							value="<c:out value='${moduleRes.resurl}'/>" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>
						<m:message code="moduleRes.status" />:</td>
						<td class="inputTd"><select id="edit_status" name="status"
							class="select"></select></td>

						<td class="inputLabelTd"><span class="required">*</span>
						<m:message code="moduleRes.needright" />:</td>
						<td class="inputTd"><select id="edit_needright"
							name="needright" class="select"></select></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><m:message code="moduleRes.resType" />:</td>
						<td class="inputTd"><select id="edit_resType" name="resType"
							class="select"></select></td>
						<td class="inputLabelTd" colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td class="inputLabelTd"><m:message code="moduleRes.memo" />:</td>
						<td class="inputTd" colspan="3"><textarea id="edit_memo"
								name="memo" class="textarea" maxlength="500"></textarea></td>
					</tr>
					<tr>
						<td class="inputTd" colspan="4" style="text-align: center;">
							<input id="submit" type="button" class="ti_bottom"
							value="<m:message code='button.save' />" />
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>
</html>
