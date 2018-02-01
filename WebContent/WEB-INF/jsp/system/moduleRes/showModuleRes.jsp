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
		$("#close_button").click(function() {
			window.parent.closeShow();
		});
	});
</script>
</head>

<body>
	<div class="ui-table ui-widget ui-corner-all ui-border">
		<table class="table forview">
			<tr>
				<td class="inputLabelTd"><m:message
						code="moduleRes.resourceName" />:</td>
				<td class="inputTd"><c:out value='${moduleRes.resourceName}' /></td>
				<td class="inputLabelTd"><m:message
						code="moduleRes.serialindex" />:</td>
				<td class="inputTd"><c:out value='${moduleRes.serialindex}' /></td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message
						code="moduleRes.resourcecode" />:</td>
				<td class="inputTd"><c:out value='${moduleRes.resourcecode}' /></td>
				<td class="inputLabelTd"><m:message code="moduleRes.resurl" />:</td>
				<td class="inputTd"><c:out value='${moduleRes.resurl}' /></td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message code="moduleRes.status" />:</td>
				<td class="inputTd"><c:choose>
						<c:when test="${moduleRes.status == '1'}">启用</c:when>
						<c:when test="${moduleRes.status == '0'}">禁用</c:when>
						<c:otherwise>&nbsp;</c:otherwise>
					</c:choose></td>
				<td class="inputLabelTd"><m:message code="moduleRes.needright" />:</td>
				<td class="inputTd" colspan="0"><c:choose>
						<c:when test="${moduleRes.needright == '1'}">是</c:when>
						<c:when test="${moduleRes.needright == '0'}">否</c:when>
						<c:otherwise>&nbsp;</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message code="moduleRes.memo" />:</td>
				<td class="inputTd" colspan="3"><c:out
						value='${moduleRes.memo}' /></td>
			</tr>
			<tr>
				<td class="inputTd" colspan="4" style="text-align: center;"><input
					class="ti_bottom" type="button" id="close_button"
					value="<m:message code='button.close' />" /></td>
			</tr>
		</table>
	</div>
</body>
</html>
