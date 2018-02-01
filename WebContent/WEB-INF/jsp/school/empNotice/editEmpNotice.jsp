<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/js/editor/lib/css/bootstrap.min.css"></link>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/js/editor/lib/css/prettify.css"></link>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/js/editor/src/bootstrap-wysihtml5.css"></link>
<style type="text/css">
.btn.jumbo {
	font-size: 20px;
	font-weight: normal;
	padding: 14px 24px;
	margin-right: 10px;
	-webkit-border-radius: 6px;
	-moz-border-radius: 6px;
	border-radius: 6px;
}

.noticeTitle {
	width: 98%;
	font-weight: bold;
}

.noticeContent {
	resize: none;
	height: 300px;
	overflow: scroll;
}

.button_area {
	text-align: center;
}

.button_area .ti_bottom {
	width: 100px;
}
</style>
<script
	src="<%=request.getContextPath() %>/js/editor/lib/js/wysihtml5-0.3.0.js"></script>
<script
	src="<%=request.getContextPath() %>/js/editor/lib/js/prettify.js"></script>
<script
	src="<%=request.getContextPath() %>/js/editor/src/bootstrap-wysihtml5.js"></script>

<script type="text/javascript">
var createDate = new Date().format('yyyy-MM-dd hh:mm:ss');//获取今日时间
$(function() {
	//实例化富文本
	$('.textarea').wysihtml5();
	$("#edit_editTime").val(createDate);
	//绑定提交按钮click事件
	$("#submit").click(function() {
		$("#submit").prop('disabled', true).css({'cursor':'not-allowed'});
		if(!biz.validate("valid",$('#empNoticeFormEdit')[0])){
			showWarn("数据验证失败",3000);
			$("#submit").prop('disabled', false).css({'cursor':'pointer'});
			return;
		}
		var options = {
			url : "<m:url value='/empNotice/updateEmpNotice.do'/>",
			type : "post",
				dataType:"json",
				success : function(d) {
					if(d.status){
						showMessage(d.message,"","",function(){
							window.parent.closeEdit();
				     		window.parent.doSearch();
						});
					}else{
						showMessage(d.message);
					}
				}
		};
		// 将options传给ajaxForm
		$('#empNoticeFormEdit').ajaxSubmit(options);
	});

	/*编辑表单数据验证*/
	new biz.validate({
		id:"#empNoticeFormEdit",
		rules:{
			"noticeTitle" :{required : true},
			"noticeContent" :{required : true}
		}
	}); 
});

</script>
</head>

<body>
	<form id="empNoticeFormEdit">
		<div class="ui-table ui-widget ui-corner-all ui-border">
			<input type="hidden" id="edit_id" name="id" type="text"
				value="${empNotice.id}" />
			<table class="table">
				<tr>
					<td class="inputLabelTd">创建时间：</td>
					<td class="inputTd"><input id="edit_createTime"
						name="createTime" type="text" class="text"
						value="${empNotice.createTime}" readonly /> <input
						id="edit_editTime" name="editTime" type="hidden" /></td>

					<td class="inputLabelTd">发布者：</td>
					<td class="inputTd"><input id="edit_createName"
						name="createName" type="text" class="text"
						value="${empNotice.createName}" readonly /> <input
						id="edit_editId" name="editId" type="hidden" value="${user.id}" />
					</td>
				</tr>
				<tr>
					<td class="inputLabelTd">公告标题：</td>
					<td class="inputTd" colspan="3"><input id="edit_noticeTitle"
						name="noticeTitle" type="text" class="noticeTitle text"
						value="${empNotice.noticeTitle}" /></td>

				</tr>
				<tr>
					<td class="inputLabelTd">公告内容：</td>
					<td class="inputTd" colspan="3"><textarea
							class="textarea noticeContent" placeholder="请输入内容..."
							id="edit_noticeContent" name="noticeContent">${empNotice.noticeContent}</textarea>
					</td>
				</tr>
			</table>
			<div class="button_area">
				<input id="submit" type="button" class="ti_bottom"
					value="<m:message code='button.save' />" />
			</div>
		</div>
	</form>
</body>
</html>
