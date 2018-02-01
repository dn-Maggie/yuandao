<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html  >
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<script type="text/javascript">
$(function() {
	//绑定提交按钮click事件
	$("#submit").click(function() {
		if(!biz.validate("valid",$('#standardFormEdit')[0])){
			showWarn("数据验证失败",3000);
			return;
		}
		var options = {
			url : "<m:url value='/standard/addStandard.do'/>",
			type : "post",
				dataType:"json",
				success : function(d) {
					if(d.status){
						showMessage(d.message,"","",function(){
							window.parent.closeAdd();
				     		window.parent.doSearch();
						});
					}else{
						showMessage(d.message);
					}
				}
		};
		// 将options传给ajaxForm
		$('#standardFormEdit').ajaxSubmit(options);
	});

	/*编辑表单数据验证*/
	new biz.validate({
		id:"#standardFormEdit",
		rules:{
		}
	}); 
});
</script>
</head>

<body>

	<div id="editDialog">
		<form id="standardFormEdit">
			<div class="ui-table ui-widget ui-corner-all ui-border">
				<input type="hidden" id="edit_id" name="id" type="text"
					value="${standard.id}" />
				<table class="table">
					<tr>
						<td class="inputLabelTd">流量来源编号：</td>
						<td class="inputTd"><input id="edit_pId" name="pId"
							type="text" class="text" value="${standard.pId}" /></td>
						<td class="inputLabelTd">流量来源名：</td>
						<td class="inputTd"><input id="edit_pName" name="pName"
							type="text" class="text" value="${standard.pName}" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd">来源分类编号：</td>
						<td class="inputTd"><input id="edit_sId" name="sId"
							type="text" class="text" value="${standard.sId}" /></td>
						<td class="inputLabelTd">来源分类：</td>
						<td class="inputTd"><input id="edit_sName" name="sName"
							type="text" class="text" value="${standard.sName}" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd">岗位：</td>
						<td class="inputTd"><input id="edit_position" name="position"
							type="text" class="text" value="${standard.position}" /></td>
						<td class="inputLabelTd">备注：</td>
						<td class="inputTd"><input id="edit_note" name="note"
							type="text" class="text" value="${standard.note}" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd">业绩比例：</td>
						<td class="inputTd"><input id="edit_rate" name="rate"
							type="text" class="text" value="${standard.rate}" /></td>
					<tr>
						<td class="inputTd" colspan="4" style="text-align: center;">
							<input id="submit" type="button" class="ti_bottom" value="保存" />
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>
</html>
