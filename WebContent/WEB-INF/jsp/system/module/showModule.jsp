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
				<td class="inputLabelTd"><m:message code="module.moduleName" />:</td>
				<td class="inputTd"><c:out value='${module.moduleName}' /></td>

				<td class="inputLabelTd"><m:message code="module.moduleCode" />:</td>
				<td class="inputTd"><c:out value='${module.moduleCode}' /></td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message code="module.navurl" />:</td>
				<td class="inputTd"><c:out value='${module.navurl}' /></td>

				<td class="inputLabelTd"><m:message code="module.serialIndex" />:</td>
				<td class="inputTd"><c:out value='${module.serialIndex}' /></td>

			</tr>
			<tr>
				<td class="inputLabelTd"><m:message code="module.enabled" />:</td>
				<td class="inputTd"><c:choose>
						<c:when test="${module.enabled == '1'}">启用</c:when>
						<c:when test="${module.enabled == '0'}">禁用</c:when>
						<c:otherwise>&nbsp;</c:otherwise>
					</c:choose></td>

				<td class="inputLabelTd"><m:message code="module.needRight" />:</td>
				<td class="inputTd"><c:choose>
						<c:when test="${module.needRight == '1'}">是</c:when>
						<c:when test="${module.needRight == '0'}">否</c:when>
						<c:otherwise>&nbsp;</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message code="module.moduleMemo" />:</td>
				<td class="inputTd" colspan="3"><c:out
						value='${module.moduleMemo}' /></td>
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
