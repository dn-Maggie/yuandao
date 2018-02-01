<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html  >
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<base href="<%=basePath%>">

<%@ include file="../../common/header.jsp"%>
<script type="text/javascript">
$(function() {
	//绑定提交按钮click事件
	$("#sub").click(function() {
		if(!biz.validate("valid",$('#updateForm')[0])){
			showWarn("<m:message code='validation.object'/>",3000);
			return;
		}
		
		var options = {
			url : "dictType/updateDictType.do",
			type : "post",
			success : function(data) {
				var d = $.parseJSON(data);
				showMessage(d.msg,"","",function(){
					if(d.rs == "success"){
						window.parent.closeEdit();
			     		window.parent.modifyNode({id:$("#typeCode").val(), name:$("#typeName").val()}); 
					}
				});
			}
		};

		// 将options传给ajaxForm
		$('#updateForm').ajaxSubmit(options);
	});
	
	//初始化下拉列表信息
	new biz.select({   
		id:"#isSystem",
		data : [
		        {"name":"是","value":"1"},
		        {"name":"否","value":"0"}
		],
		value:"${dictType.isSystem}"
	});
	
	
	new biz.validate({
		id:"#updateForm",
		rules:{
			typeCode : {required : true,maxlength:50},
			typeName : {required : true,maxlength:15},
			isSystem : {required : true}
		}
	});
	
});
</script>
</head>
<body>
	<form action="" method="post" id="updateForm" name="updateForm">
		<hi:icssToken />
		<input type="hidden" id="id" name="id" value="${dictType.id}" />
		<div class="ui-table ui-widget ui-corner-all ui-border">
			<table class="table">
				<tr>
					<td class="inputLabelTd"><span class="required">*</span>
					<m:message code='type.typeCode' />:</td>
					<td class="inputTd"><input class="text" type="text"
						name="typeCode" id="typeCode" value="${dictType.typeCode}"
						disabled="disabled" /></td>
					<td class="inputLabelTd"><span class="required">*</span>
					<m:message code='type.typeName' />:</td>
					<td class="inputTd"><input class="text" type="text"
						name="typeName" id="typeName" value="${dictType.typeName}"
						maxlength="50" /></td>
				</tr>
				<tr>
					<td class="inputLabelTd"><span class="required">*</span>
					<m:message code='type.isSystem' />:</td>
					<td class="inputTd" colspan="3"><select id="isSystem"
						name="isSystem" class="select"></select></td>
				</tr>
				<tr>
					<td class="inputLabelTd"><m:message code='dict.memo' />:</td>
					<td class="inputTd" colspan="3"><textarea id="memo"
							class="textarea" name="memo" maxlength="500">${dictType.memo}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" style="text-align: center"><input
						class="ti_bottom" type="button" id="sub"
						value="<m:message code='dict.sub' />" /></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
