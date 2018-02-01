<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html  >
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<base href="<%=basePath%>">

<%@ include file="../../common/header.jsp"%>
<script type="text/javascript">
	$(function() {
		//绑定提交按钮click事件
		$("#sub").click(function() {
			if (!biz.validate("valid", $('#addForm')[0])) {
				showWarn("<m:message code='validation.object'/>", 3000);
				return;
			}
			var options = {
				url : "dictType/addDictType.do",
				type : "post",
				success : function(data) {
					var d = $.parseJSON(data);
					showMessage(d.msg, "", "", function() {
						if (d.rs == "success") {
							window.parent.closeAdd();
							window.parent.addNode({id:$("#typeCode").val(), name:$("#typeName").val()});
						}
					});
				}
			};
			// 将options传给ajaxForm
			$('#addForm').ajaxSubmit(options);
		});

		//初始化下拉列表信息

		new biz.select({
			id : "#isSystem",
			data : [ {
				"name" : "<m:message code='type.yes' />",
				"value" : "1"
			}, {
				"name" : "<m:message code='type.no' />",
				"value" : "0"
			} ]
		});

		new biz.validate({
			id : "#addForm",
			rules : {
				typeCode : {required : true,maxlength:50},
				typeName : {required : true,maxlength:15},
				isSystem : {required : true}
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
					<m:message code='type.typeCode' />:</td>
					<td class="inputTd"><input class="text" type="text"
						name="typeCode" id="typeCode" maxlength="50" /></td>
					<td class="inputLabelTd"><span class="required">*</span>
					<m:message code='type.typeName' />:</td>
					<td class="inputTd"><input class="text" type="text"
						name="typeName" id="typeName" maxlength="50" /></td>
				</tr>
				<tr>
					<td class="inputLabelTd"><span class="required">*</span> <m:message
							code='type.isSystem' />:</td>
					<td class="inputTd" colspan="3"><select id="isSystem"
						name="isSystem" class="select"></select></td>
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
