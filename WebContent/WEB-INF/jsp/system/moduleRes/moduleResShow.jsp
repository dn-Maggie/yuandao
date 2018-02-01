<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<div>
	<div class="ui-table ui-widget ui-corner-all ui-border">
		<table class="table forview">
			<tr>
				<td class="inputLabelTd"><m:message
						code="moduleRes.detailInfo.resourcecode" />:</td>
				<td class="inputTd"><c:out value='${moduleRes.resourcecode}' /></td>

				<td class="inputLabelTd"><m:message
						code="moduleRes.detailInfo.resourceName" />:</td>
				<td class="inputTd"><c:out value='${moduleRes.resourceName}' /></td>

			</tr>
			<tr>
				<td class="inputLabelTd"><m:message
						code="moduleRes.detailInfo.resurl" />:</td>
				<td class="inputTd"><c:out value='${moduleRes.resurl}' /></td>

				<td class="inputLabelTd"><m:message
						code="moduleRes.detailInfo.memo" />:</td>
				<td class="inputTd"><c:out value='${moduleRes.memo}' /></td>

			</tr>
			<tr>
				<td class="inputLabelTd"><m:message
						code="moduleRes.detailInfo.status" />:</td>
				<td class="inputTd"><c:choose>
						<c:when test="${moduleRes.status == '1'}">启用</c:when>
						<c:when test="${moduleRes.status == '0'}">禁用</c:when>
						<c:otherwise>&nbsp;</c:otherwise>
					</c:choose></td>

				<td class="inputLabelTd"><m:message
						code="moduleRes.detailInfo.needright" />:</td>
				<td class="inputTd" colspan="0"><c:choose>
						<c:when test="${moduleRes.needright == '1'}">是</c:when>
						<c:when test="${moduleRes.needright == '0'}">否</c:when>
						<c:otherwise>&nbsp;</c:otherwise>
					</c:choose></td>

			</tr>
		</table>
	</div>
</div>