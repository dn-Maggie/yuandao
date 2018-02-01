<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
	})
	
	.on('click', '.noticeItemright', function() {
		var fid = $(this).parent();
		fid.submit();
	})
</script>
<style type="text/css">
.button_area {
	text-align: center;
}

.button_area .ti_bottom {
	width: 100px;
}
</style>
</head>

<body>
	<div class="ui-table ui-widget ui-corner-all ui-border">
		<table class="table forview">
			<tr>
				<td class="inputLabelTd">创建时间：</td>
				<td class="inputTd">${empNotice.createTime}</td>
				<td class="inputLabelTd">发布作者：</td>
				<td class="inputTd">${empNotice.createName}</td>
			</tr>
			<tr>
				<td class="inputLabelTd">最后修改时间：</td>
				<td class="inputTd">${empNotice.createTime}</td>
				<td class="inputLabelTd">最后修改作者：</td>
				<td class="inputTd">${empNotice.createName}</td>
			</tr>
			<tr>
				<td class="inputTd" colspan="4"
					style="font-weight: bold; text-align: center; font-size: 14px">${empNotice.noticeTitle}</td>
			</tr>
			<tr class="trContent">
				<td class="inputTd" colspan="4" style="padding: 10px">${empNotice.noticeContent}</td>
			</tr>
			<c:if test="${not empty empNotice.fileUrl}">
				<tr class="downloadbutton">
					<td class="" colspan="4" style="padding: 10px">
						<form name="form" method="GET"
							action="<%=request.getContextPath()%>/download/fileDownload">
							<a class="noticeItemright" name="noticeItemright" onclick=""
								id="noticeItemright"> <i class="fa fa-download"> <input
									type="hidden" name="filedownloadurl"
									value="${empNotice.fileUrl}" />
							</i><u>下载附件</u>&nbsp;&nbsp;&nbsp;&nbsp;${empNotice.fileName}
							</a>
						</form>
					</td>
				</tr>
			</c:if>
		</table>
		<div class="button_area">
			<input class="ti_bottom" type="button" id="close_button" value="关闭" />
		</div>
	</div>
</body>
</html>
