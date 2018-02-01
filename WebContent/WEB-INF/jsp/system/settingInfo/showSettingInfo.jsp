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
	<div class="ui-table ui-widget ui-corner-all ui-border">
		<table id="settingInfo" name="settingInfo" class="table forview">
			<tr>
				<td class="inputLabelTd"><m:message
						code="settingInfo.queryForm.variableName" />:</td>
				<td class="inputTd"><c:out value="${settingInfo.variableName }" />
				</td>
				<td class="inputLabelTd"><m:message
						code="settingInfo.queryForm.variableValue" />:</td>
				<td class="inputTd"><c:out
						value="${settingInfo.variableValue }" /></td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message
						code="settingInfo.queryForm.varNameCn" />:</td>
				<td class="inputTd" colspan="3"><c:out
						value="${settingInfo.varNameCn }" /></td>
			</tr>
			<tr>
				<td class="inputTd" colspan="4" style="text-align: center;"><input
					class="ti_bottom" type="button" id="close_button"
					onclick="closeShow()" value="<m:message code='button.close' />" />
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
<script type="text/javascript">
	function closeShow(){
		window.parent.closeView();
	}
	
</script>




