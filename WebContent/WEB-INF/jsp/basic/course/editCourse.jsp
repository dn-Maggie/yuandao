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
		if(!biz.validate("valid",$('#courseFormEdit')[0])){
			showWarn("数据验证失败",3000);
			return;
		}
		var options = {
			url : "<m:url value='/course/updateCourse.do'/>",
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
		$('#courseFormEdit').ajaxSubmit(options);
	});

	/*编辑表单数据验证*/
	new biz.validate({
		id:"#courseFormEdit",
		rules:{
		}
	}); 
});
</script>
<style type="text/css">
.timeIpt {
	position: relative;
	padding-right: 20px;
}
</style>
</head>

<body>
	<form id="courseFormEdit">
		<div class="ui-table ui-widget ui-corner-all ui-border">
			<input type="hidden" id="edit_id" name="id" type="text"
				value="${course.id}" />
			<table class="table">
				<tr>
					<td class="inputLabelTd">课程名称：</td>
					<td class="inputTd"><input id="edit_name" name="name"
						type="text" class="text" value="${course.name}" /></td>
					<td class="inputLabelTd">所属学科：</td>
					<td class="inputTd"><select id="edit_subjectId"
						name="subjectId" class="text">
							<c:if test="${course.subjectId.length()<36}">
								<option selected="selected"></option>
							</c:if>
							<c:forEach items="${subList}" var="sub" varStatus="s">
								<option
									<c:if test="${course.subjectId==sub.id}">selected="selected"</c:if>
									value="${sub.id }">${sub.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td class="inputTd" colspan="4" style="text-align: center;"><input
						id="submit" type="button" class="ti_bottom"
						value="<m:message code='button.save' />" /></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
