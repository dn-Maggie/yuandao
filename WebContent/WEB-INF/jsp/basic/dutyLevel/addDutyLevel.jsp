<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<script type="text/javascript">
$(function() {
	//绑定提交按钮click事件
	$("#submit").click(function() {
		if(!biz.validate("valid",$('#dutyLevelFormEdit')[0])){
			showWarn("数据验证失败",3000);
			return;
		}
		var options = {
			url : "<m:url value='/dutyLevel/addDutyLevel.do'/>",
			type : "post",
				dataType:"json",
				success : function(d) {
					if(d.status){
						showMessage(d.message,"","",function(){
							window.parent.closeAdd();
							parent.location.reload()
						});
					}else{
						showMessage(d.message);
					}
				}
		};
		// 将options传给ajaxForm
		$('#dutyLevelFormEdit').ajaxSubmit(options);
	});

	/*编辑表单数据验证*/
	new biz.validate({
		id:"#dutyLevelFormEdit",
		rules:{
		}
	}); 
});

</script>
</head>

<body>

	<div id="editDialog">
		<form id="dutyLevelFormEdit">
			<div class="ui-table ui-widget ui-corner-all ui-border">
				<input type="hidden" id="edit_id" name="id" type="text"
					value="${dutyLevel.id}" />
				<table class="table">
					<tr>
						<td class="inputLabelTd">岗位名称：</td>
						<td class="inputTd"><select class="input_select"
							id="edit_dutyId" name="dutyId">
								<c:forEach var="dutys" items="${dutys}">
									<option value="${dutys.id}"
										<c:if test="${dutys.id==duty.id}">selected</c:if>><c:out
											value="${dutys.dutyName}"></c:out></option>
								</c:forEach>
						</select></td>
						<td class="inputLabelTd">等级类型：</td>
						<td class="inputTd"><input id="edit_levelType"
							name="levelType" type="text" class="text" list="levelTypeList" />
							<datalist id="levelTypeList">
								<option value="P" label="">P</option>
								<option value="T" label="讲师">T</option>
								<option value="D" label="正职">D</option>
								<option value="J" label="兼职">J</option>
							</datalist></td>
					</tr>
					<tr>
						<td class="inputLabelTd">等级：</td>
						<td class="inputTd"><input id="edit_levelName"
							name="levelName" type="number" min="0" class="text"
							value="${dutyLevel.levelName}" /></td>
						<td class="inputLabelTd">薪级工资：</td>
						<td class="inputTd"><input id="edit_levelSal" name="levelSal"
							type="number" min="0" class="text" value="${dutyLevel.levelSal}" />
						</td>
					</tr>
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
