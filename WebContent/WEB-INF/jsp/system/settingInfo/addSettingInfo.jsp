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
</head>
<body>
	<div id="settingInfoDiv">
		<form id="settingInfoForm">
			<hi:icssToken />
			<div class="ui-table ui-widget ui-corner-all ui-border">
				<table id="settingInfo" class="table">
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>
						<m:message code="settingInfo.queryForm.variableName" />:</td>
						<td class="inputTd"><input id="variableName"
							name="variableName" type="text" class="text" /></td>
						<td class="inputLabelTd"><span class="required">*</span>
						<m:message code="settingInfo.queryForm.variableValue" />:</td>
						<td class="inputTd"><input id="variableValue"
							name="variableValue" type="text" class="text" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><m:message
								code="settingInfo.queryForm.varNameCn" />:</td>
						<td class="inputTd" colspan="3"><input id=varNameCn
							name="varNameCn" type="text" class="text" /></td>
					</tr>
					<tr>
						<td class="inputTd" colspan="4" style="text-align: center;">
							<input class="ti_bottom" type="button" id="submit"
							value="<m:message code='button.save' />" />
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
$(function() {
	// 绑定提交按钮click事件
	$("#submit").click(function() {
				if (!biz.validate("valid", $('#settingInfoForm')[0])) {
					showWarn("数据验证失败", 3000);
					return;
				}
				var options = {
					url : "<c:url value='/settingInfo/addSettingInfo.do'/>",
					type : "post",
					dataType : "json",
					success : function(d) {
						if (d.status) {
							showMessage(d.message, "", "", function() {
										window.parent.closeAdd();
										window.parent.doSearch();
									});
						} else {
							showMessage(d.message);
						}
					}
				};
				// 将options传给ajaxForm
				$('#settingInfoForm').ajaxSubmit(options);
			});

	/* 编辑表单数据验证 */
	new biz.validate({
				id : "#settingInfoForm",
				rules : {
					variableName : {
						required : true,
						maxlength : 50
					},
					variableValue : {
						required : true,
						maxlength : 166
					},
					varNameCn : {
						maxlength : 16
					}
				}
			});
});

</script>
</html>





