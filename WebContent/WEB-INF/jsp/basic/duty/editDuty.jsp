<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<style type="text/css">
.dutyLevelOption {
	color: #46494d;
}
</style>
</head>

<body>

	<div id="editDialog">
		<form id="dutyFormEdit">
			<div class="ui-table ui-widget ui-corner-all ui-border">
				<input type="hidden" id="edit_id" name="id" type="text"
					value="${duty.id}" /> <input id="edit_pid" name="pid" type="hidden"
					class="text" value="${duty.pid}" />
				<table class="table">
					<tr>
						<td class="inputLabelTd">级别分类：</td>
						<td class="inputTd"><select name="dutySort"
							id="edit_dutySort" class="select">
								<option value="1"
									<c:if test="${duty.dutySort eq 1}" >selected</c:if>>集团级</option>
								<option value="2"
									<c:if test="${duty.dutySort eq 2}" >selected</c:if>>省级</option>
								<option value="3"
									<c:if test="${duty.dutySort eq 3}" >selected</c:if>>分公司级</option>
						</select></td>
						<td class="inputLabelTd">所属部门：</td>
						<td class="inputTd"><select name="orgId" id="edit_orgId"
							class="select">
								<c:forEach var="orgList" items="${orgList}">
									<option value="${orgList.id}"
										<c:if test="${orgList.id eq duty.pid}" >selected</c:if>><c:out
											value="${orgList.orgName}"></c:out></option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td class="inputLabelTd">岗位名称：</td>
						<td class="inputTd"><input id="dutyName" name="dutyName"
							type="text" class="text" value="${duty.dutyName}" /></td>
						<td class="inputLabelTd">职能描述：</td>
						<td class="inputTd"><input id="edit_dutyDesc" name="dutyDesc"
							type="text" class="text" value="${duty.dutyDesc}" /></td>
					<tr>
						<td class="inputLabelTd">岗位类型：</td>
						<td class="inputTd"><input id="dutyTypeName"
							name="dutyTypeName" type="text" class="text"
							value="${duty.dutyTypeName}" /> <input id="dutyType"
							name="dutyType" type="hidden" class="text"
							value="${duty.dutyType}" /></td>
						<td class="inputLabelTd">岗位级别：</td>
						<td class="inputTd"><select class="input_select"
							id="dutyLevel">
								<c:forEach var="dutyLevel" items="${dutyLevelList}">
									<option value="${dutyLevel.id }"><c:out
											value="${dutyLevel.levelType}${dutyLevel.levelName}"></c:out></option>
								</c:forEach>
						</select> <a class="dutyLevelOption" href="javascript:0"
							onclick="addDutyLevel()">新增 </a> <a class="dutyLevelOption"
							href="javascript:0" onclick="deleteDutyLevel()">删除 </a></td>
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

	<script type="text/javascript">
	$(function() {
		//绑定提交按钮click事件
		$("#submit").click(function() {
			if (!biz.validate("valid", $('#dutyFormEdit')[0])) {
				showWarn("数据验证失败", 3000);
				return;
			}
			var options = {
				url : "<m:url value='/duty/updateDuty.do'/>",
				type : "post",
				success : function(data) {
					var d = $.parseJSON(data);
					showMessage(d.msg, "", "", function() {
						window.parent.closeEdit();
						window.parent.doSearch();
						window.parent.initDutyTree();
					});
				}
			};
			// 将options传给ajaxForm
			$('#dutyFormEdit').ajaxSubmit(options);
		});
// 		//岗位选类型择
		dutyTypeName = $('#dutyTypeName')
				.TiledCombobox(
						{
							url : "<m:url value='/dictInfo/getDictInfoByType.do?type=dutyType'/>",
							fieldId : 'value',
							fieldName : 'name',
							valueId : '#dutyType',
							multiple : false
						});
		/*编辑表单数据验证*/
		new biz.validate({
			id : "#dutyFormEdit",
			rules : {
				"dutyName" : {
					required : true,
					maxlength : 20
				},
				"dutyTypeName" : {
					required : true
				}
			}
		});
	});
	
	//新增的弹出框
	var add_iframe_dialog;
	
	function addDutyLevel(){
		var url="<m:url value='/dutyLevel/toAddDutyLevel.do'/>?dutyId="+$("#edit_id").val();
		var title = "岗位级别表增加";
		add_iframe_dialog = Add.create(url, title,800,235);
		List.openDialog(add_iframe_dialog);
  	}
 	//关闭新增页面，供子页面调用
 	function closeAdd(){
 		List.closeDialog(add_iframe_dialog);
 	}
 	
 	 //删除岗位级别
    function deleteDutyLevel(){
    	var ids = $.trim($("#dutyLevel").find("option:selected").val());
   		new biz.alert({type:"confirm",message:"该数据已关联员工岗位级别，确定删除？",title:I18N.promp,callback:function(result){
   			if(result){
   				$ .ajax({
       				url: "<m:url value='/dutyLevel/deleteDutyLevel.do'/>?key="+ids,
       				cache:false,
       				success: function(data, textStatus, jqXHR){
       					window.location.reload();
   						showInfo("删除成功",3000);
       				}
       			});
   			} 
   		}}) ;   
    }
</script>
</body>
</html>