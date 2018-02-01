<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html  >
<html>
<head>
<%@ include file="../../common/header.jsp"%>
</head>

<body>
	<form id="subjectFormEdit">
		<div class="ui-table ui-widget ui-corner-all ui-border">
			<input type="hidden" id="edit_id" name="id" type="text"
				value="${subject.id}" />
			<table class="table">
				<tr>
					<td class="inputLabelTd">名称：</td>
					<td class="inputTd"><input id="edit_name" name="name"
						type="text" class="text" value="${subject.name}" /></td>
					<td class="inputLabelTd">月度业绩目标：</td>
					<td class="inputTd"><input id="edit_perfTarget"
						name="perfTarget" type="text" class="text"
						value="${subject.perfTarget}" /></td>
					<td class="inputLabelTd">创建时间：</td>
					<td class="inputTd"><input id="edit_creatdate"
						name="creatdate" type="text" class="text"
						value="${subject.creatdate}" /></td>
				</tr>
				<tr>
					<td class="inputTd" colspan="6" style="text-align: center;"><input
						id="submit" type="button" class="ti_bottom"
						value="<m:message code='button.save' />" /></td>
				</tr>
			</table>
		</div>
	</form>
	<script type="text/javascript">
$(function() {
	//绑定提交按钮click事件
	$("#submit").click(function() {
		if(!biz.validate("valid",$('#subjectFormEdit')[0])){
			showWarn("数据验证失败",3000);
			return;
		}
		var options = {
			url : "<m:url value='/subject/updateSubject.do'/>",
			type : "post",
				dataType:"json",
				success : function(d) {
					if(d.status){
						showMessage(d.message,"","",function(){
							window.parent.closeEdit();
							List.doSearch(window.parent.gridObj);
						});
					}else{
						showMessage(d.message);
					}
				}
		};
		// 将options传给ajaxForm
		$('#subjectFormEdit').ajaxSubmit(options);
	});

	/*编辑表单数据验证*/
	new biz.validate({
		id:"#subjectFormEdit",
		rules:{
		}
	}); 
});

</script>
</body>
</html>
