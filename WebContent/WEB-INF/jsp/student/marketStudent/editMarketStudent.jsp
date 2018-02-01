<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html  >
<html>
<head>
<%@ include file="../../common/header.jsp"%>
</head>

<body>
	<form id="marketStudentFormEdit">
		<div class="ui-table ui-widget ui-corner-all ui-border">
			<input type="hidden" id="edit_id" name="id" type="text"
				value="${marketStudent.id}" /> <input id="edit_time" name="time"
				type="hidden" value="${marketStudent.time}" />
			<table class="table">
				<tr>
					<td class="inputLabelTd">QQ号码：</td>
					<td class="inputTd"><input id="edit_qq" name="qq" type="text" class="text" value="${marketStudent.qq}" /></td>
					<td class="inputLabelTd">微信号：</td>
					<td class="inputTd"> <input id="edit_subjectId" name="subjectId" type="text" class="text" value="${marketStudent.subjectId}" /></td>
				</tr>
				<tr>
					<td class="inputLabelTd">店铺名：</td>
					<td class="inputTd"><input id="edit_notes" name="notes" type="text" class="text" value="${marketStudent.notes}" /></td>
					<td class="inputLabelTd">更换图片：</td>
					<td class="inputTd"><input id="fileData" name="fileUrl"
						type="hidden" value="${marketStudent.fileUrl}"> <input
						id="file" type="file" class="text" /></td>
				</tr>
				<tr>
					<td colspan="4" class="inputTd"><img alt="图片"
						style="height: 400px; width: 100%" src="${marketStudent.fileUrl}">
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
		if(fileName.length>1){  
			var extname = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length).toLowerCase();  
			var imgname = fileName.substring(fileName.lastIndexOf("\\")+1,fileName.length);  
			if(extname!= "jpeg"&&extname!= "jpg"&&extname!= "gif"&&extname!= "png"){  
				 showWarn("格式不正确,支持的图片格式为：JPEG、JPG、GIF、PNG！");  
		         return false;  
		        }  
			var file = $("#file").get(0).files; 
			var size = file[0].size;
			if(size>2097152){  
				  showWarn("所选择的图片太大，图片大小最多支持2M!"); 
		          return false;  
		     }  			
			// 创建一个FileReader对象
			var reader = new FileReader();
			// 绑定load事件
			reader.onload = function(e) {
				$("#fileData").val(e.target.result);
				$('#marketStudentFormEdit').ajaxSubmit(options);// 将options传给ajaxForm
			}
			// 读取File对象的数据
			reader.readAsDataURL($("#file").get(0).files[0]);
	      }
			else{
			// 将options传给ajaxForm
			$('#marketStudentFormEdit').ajaxSubmit(options);}
	});

	/*编辑表单数据验证*/
	new biz.validate({
		id:"#marketStudentFormEdit",
		rules:{
		}
	}); 
});
</script>
</body>
</html>
