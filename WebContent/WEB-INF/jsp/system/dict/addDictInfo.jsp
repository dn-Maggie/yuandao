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
		if(!biz.validate("valid",$('#addForm')[0])){
			showWarn("<m:message code='validation.object'/>",3000);
			return;
		}
		
		var options = {
			url : "dictInfo/addDictInfo.do",
			type : "post",
			success : function(data) {
				var d = $.parseJSON(data);
				showMessage(d.msg,"","",function(){
					if(d.rs == "success"){
						window.parent.closeAdd();
			     		window.parent.dictInfoSearch();
					}
				});
			}
		};
		// 将options传给ajaxForm
		$('#addForm').ajaxSubmit(options);
	});
	
	//初始化下拉列表信息
	new biz.select({   
		id : "#dictTypeCode",
		addEmptyItem : false,
		url : "dictType/getDictTypeForSelect.do?typeCode=${typeCode}"
	});
	
	new biz.validate({
		id:"#addForm",
		rules:{
			dictTypeCode:{required: true},
			orderNo:{required: true,digits:true},
			dictCode:{required: true},
			dictName:{required: true}
		}
	});
	
});
</script>
</head>
<body>
	<form action="" method="post" id="addForm" name="addForm">
		<hi:icssToken />
		<div class="ui-table ui-widget ui-corner-all ui-border">
			<table class="table">
				<tr>
					<td class="inputLabelTd"><span class="required">*</span>
					<m:message code='type.typeName' />:</td>
					<td class="inputTd"><select id="dictTypeCode"
						name="dictTypeCode" class="select"></select></td>
					<td class="inputLabelTd"><span class="required">*</span>
					<m:message code='dict.orderNo' />:</td>
					<td class="inputTd"><input class="text" type="text"
						name="orderNo" id="orderNo" maxlength="6" /></td>
				</tr>
				<tr>
					<td class="inputLabelTd"><span class="required">*</span>
					<m:message code='dict.dictCode' />:</td>
					<td class="inputTd"><input class="text" type="text"
						name="dictCode" id="dictCode" maxlength="50" /></td>
					<td class="inputLabelTd"><span class="required">*</span>
					<m:message code='dict.dictName' />:</td>
					<td class="inputTd"><input class="text" type="text"
						name="dictName" id="dictName" maxlength="50" /></td>
				</tr>
				<tr>
					<td class="inputLabelTd"><m:message code='dict.memo' />:</td>
					<td class="inputTd" colspan="3"><textarea id="memo"
							class="textarea" name="memo" maxlength="500"></textarea></td>
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
