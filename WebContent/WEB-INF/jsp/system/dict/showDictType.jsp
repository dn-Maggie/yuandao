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
<link href="<%=basePath %>styles/extends/css/pre.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">
$(function() {
	//绑定提交按钮click事件
	$("#sub").click(function() {
		window.parent.closeShow();
	});
	
});
	</script>
</head>

<body>
	<input type="hidden" name="id" value="${dictType.id }" />
	<div class="ui-table ui-widget ui-corner-all ui-border">
		<table class="table">
			<tr>
				<td class="inputLabelTd"><m:message code='type.typeCode' /></td>
				<td class="inputTd">${dictType.typeCode }</td>
				<td class="inputLabelTd"><m:message code='type.typeName' /></td>
				<td class="inputTd">${dictType.typeName }</td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message code='dict.states' /></td>
				<td class="inputTd"><c:choose>
						<c:when test="${dictType.states == '1'}">
							<m:message code='dict.normal' />
						</c:when>
						<c:otherwise>
							<m:message code='dict.nonormal' />
						</c:otherwise>
					</c:choose></td>
				<td class="inputLabelTd"><m:message code='type.isSystem' /></td>
				<td class="inputTd"><c:choose>
						<c:when test="${dictType.isSystem == '1'}">
							<m:message code="type.yes" />
						</c:when>
						<c:otherwise>
							<m:message code="type.no" />
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message code='dict.creatorId' /></td>
				<td class="inputTd">${dictType.creatorId }</td>
				<td class="inputLabelTd"><m:message code='dict.createTime' /></td>
				<td class="inputTd">${dictType.createTime }</td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message code='dict.updaterId' /></td>
				<td class="inputTd">${dictType.updaterId }</td>
				<td class="inputLabelTd"><m:message code='dict.updateTime' /></td>
				<td class="inputTd">${dictType.updateTime }</td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message code='dict.memo' /></td>
				<td class="inputTd" colspan="3"><pre>${dictType.memo }</pre></td>
			</tr>

			<tr>
				<td colspan="4" style="text-align: center"><input
					class="ti_bottom" type="button" id="sub"
					value="<m:message code='dict.close' />" /></td>
			</tr>
		</table>
	</div>
</body>
</html>
