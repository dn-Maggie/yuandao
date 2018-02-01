<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html  >
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<base href="<%=basePath%>">
<link href="<%=basePath %>styles/extends/css/pre.css" rel="stylesheet"
	type="text/css" />

<%@ include file="../../common/header.jsp"%>
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
	<input type="hidden" name="id" value="${dictInfo.id }" />
	<div class="ui-table ui-widget ui-corner-all ui-border">
		<table class="table forview">
			<tr>
				<td class="inputLabelTd"><m:message code='dict.dictTypeCode' /></td>
				<td class="inputTd">${dictInfo.dictTypeCode }</td>
				<td class="inputLabelTd"><m:message code='dict.dictCode' /></td>
				<td class="inputTd">${dictInfo.dictCode }</td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message code='dict.states' /></td>
				<td class="inputTd"><c:choose>
						<c:when test="${dictInfo.states == '1'}">
							<m:message code='dict.normal' />
						</c:when>
						<c:otherwise>
							<m:message code='dict.nonormal' />
						</c:otherwise>
					</c:choose></td>
				<td class="inputLabelTd"><m:message code='dict.orderNo' /></td>
				<td class="inputTd">${dictInfo.orderNo }</td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message code='dict.creatorId' /></td>
				<td class="inputTd">${dictInfo.creatorId }</td>
				<td class="inputLabelTd"><m:message code='dict.createTime' /></td>
				<td class="inputTd">${dictInfo.createTime }</td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message code='dict.updaterId' /></td>
				<td class="inputTd">${dictInfo.updaterId }</td>
				<td class="inputLabelTd"><m:message code='dict.updateTime' /></td>
				<td class="inputTd">${dictInfo.updateTime }</td>
			</tr>
			<tr>
				<td class="inputLabelTd"><m:message code='dict.memo' /></td>
				<td class="inputTd" colspan="3"><xmp>${dictInfo.memo }</xmp></td>
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
