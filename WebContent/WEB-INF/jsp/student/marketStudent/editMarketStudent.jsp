<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html  >
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common-util/image-util.js"></script>	
<%@ include file="../../common/header.jsp"%>
</head>

<body>
	<form id="marketStudentFormEdit">
		<div class="ui-table ui-widget ui-corner-all ui-border">
			<input type="hidden" id="edit_id" name="id" type="text" value="${marketStudent.id}" /> 
			<input id="edit_time" name="time" type="hidden" value="${marketStudent.time}" />
			<table class="table">
				<tr>
					<td class="inputLabelTd">QQ号码：</td>
					<td class="inputTd"><input id="edit_qq" name="qq" type="text" class="text" value="${marketStudent.qq}" /></td>
					<td class="inputLabelTd">意向学科：</td>
					<td class="inputTd">
						<select onchange="xuekeChange(this.value);" style="float:left" class="input_select" name="subjectId" id="edit_subjectId">
							<c:forEach var="mr" items="${er.subject}">
								<option value="${mr.id}" <c:if test="${mr.id == marketStudent.subjectId}">selected</c:if>> <c:out value="${mr.name}"></c:out> </option>
				             </c:forEach>
				        </select>
					</td>
				</tr>
				<tr>
					<td class="inputLabelTd">意向课程：</td>
					<td class="inputTd">
						<input id="edit_notes" name="notes" type="text" class="text" value="${marketStudent.notes}" list="courseList"/> 
						<dataList style="float:left" class="input_select" id="courseList">
							<c:forEach var="mr" items="${er.course}">
								<option value="${mr.name}" > <c:out value="${mr.name}"></c:out> </option>
				             </c:forEach>
						</dataList>
						<input id="edit_time" name="time" type="hidden" value="${marketStudent.time}" />
					</td>
					<td class="inputLabelTd">更换图片：</td>
					<td class="inputTd"><input id="fileData" name="fileUrl"
						type="hidden" value="${marketStudent.fileUrl}"> <input
						id="file" type="file" class="text" onchange="javascript:setImagePreviews();"/></td>
				</tr>
				<tr>
					<td colspan="4" class="inputTd" id="preview">
						<img alt="图片" style="height: 400px; width: 100%" src="${marketStudent.fileUrl}">
					</td>
				</tr>
				<tr>
					<td class="inputTd" colspan="4" style="text-align: center;"><input
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
		if(!biz.validate("valid",$('#marketStudentFormEdit')[0])){
			showWarn("数据验证失败",3000);
			return;
		}
		var options = {
			url : "<m:url value='/marketStudent/updateMarketStudent.do'/>",
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
		//验证图片
		var fileName = $("#file").val();
		//如果有图片要上传，进行图片上次处理
		if(fileName.length>1){  
			var file = $("#file").get(0).files[0]; 
			PreviewFile(file,$('#marketStudentFormEdit'),options);
	    }else{
	    	// 直接将options传给ajaxForm（不上传图片）
	  		$('#marketStudentFormEdit').ajaxSubmit(options);
	    }
	});

	/*编辑表单数据验证*/
	new biz.validate({
		id:"#marketStudentFormEdit",
		rules:{
		}
	}); 
});
function xuekeChange(val){
	$('#edit_notes').val("");
	$ .ajax({
		url: "<m:url value='/marketStudent/getCourseList.do'/>?key="+val,
		cache:false,
		dataType:"json",
		success: function(data, textStatus, jqXHR){
			$('#courseList option').remove();
			for(var i in data.course){
				if(data.course[i].id){
					$('#courseList').append('<option value="'+data.course[i].name+'">'+data.course[i].name+' </option>');
				}
			}
			if(!data.course[0]){
				$('#courseList option').remove();
			}
		}
	});
}
</script>
</body>
</html>
