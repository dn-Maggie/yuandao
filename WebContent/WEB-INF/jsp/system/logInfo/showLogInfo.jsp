<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html  >
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<head>
<base href="<%=basePath%>">
<%@ include file="../../common/header.jsp"%>
</head>
<body>
	<div class="ui-table ui-widget ui-corner-all ui-border">
		<table id="logInfo" class="table forview">
			<tr style="border: none">
				<td class="inputLabelTd"><m:message
						code='logInfo.queryForm.userId' />:</td>
				<td class="inputTd"><c:out value="${logInfo.userId }" /></td>

				<td class="inputLabelTd"><m:message
						code='logInfo.queryForm.actorName' />:</td>
				<td class="inputTd"><c:out value="${logInfo.actorName }" /></td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message
						code='logInfo.queryForm.actType' />:</td>
				<td class="inputTd"><c:out value="${logInfo.actType }" /></td>

				<td class="inputLabelTd"><m:message
						code='logInfo.queryForm.actAction' />:</td>
				<td class="inputTd"><c:out value="${logInfo.actAction }" /></td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message
						code='logInfo.queryForm.actTime' />:</td>
				<td class="inputTd"><c:out value="${logInfo.actTime }" /></td>

				<td class="inputLabelTd"><m:message
						code='logInfo.queryForm.actResult' />:</td>
				<td class="inputTd"><c:out value="${logInfo.actResult }" /></td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message
						code='logInfo.queryForm.actObj' />:</td>
				<td class="inputTd" colspan="3"><c:out
						value="${logInfo.actObj }" /></td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message
						code='logInfo.queryForm.actModule' />:</td>
				<td class="inputTd" colspan="3"><c:out
						value="${logInfo.actModule }" /></td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message
						code='logInfo.queryForm.actMessage' />:</td>
				<td class="inputTd" colspan="3"><c:out
						value="${logInfo.actMessage }" /></td>
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
