<%@page import="com.dongnao.workbench.common.enums.FinanceType"%>
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
		if(!biz.validate("valid",$('#accountFlowFormEdit')[0])){
			showWarn("数据验证失败",3000);
			return;
		}
		var options = {
			url : "<m:url value='/accountFlow/addAccountFlow.do'/>",
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
		$('#accountFlowFormEdit').ajaxSubmit(options);
	});

	new biz.autocomplete(
			{
				id : "#edit_accountNo",
				source : "<m:url value='/accountFinance/getFinanceNoByStockCode.do'/>",
				focus : function(event, ui) {
				},
				select : function(event, ui) {
					$("#edit_accountId").val(ui.item.id);
					$("#edit_accountNo").val(ui.item.accountNo);
					$("#edit_accountName").val(ui.item.accountName);
					$("#groupFinanceType").val(ui.item.accountType);
					return false;
				}
			});
	/*编辑表单数据验证*/
	new biz.validate({
		id:"#accountFlowFormEdit",
		rules:{
		}
	}); 
	
	new biz.datepicker({
			id : "#edit_createDate",
			maxDate:'#F{$dp.$D(\'edit_createDate\',{d:0});}',
			dateFmt:'yyyy-MM-dd HH:mm:ss'
		});
});
</script>
</head>

<body>

	<div id="editDialog">
		<form id="accountFlowFormEdit">
			<div class="ui-table ui-widget ui-corner-all ui-border">
				<input type="hidden" id="edit_id" name="id" type="text"
					value="${accountFlow.id}" />
				<table class="table">
					<tr>
						<td class="inputLabelTd">科目编号：</td>
						<td class="inputTd"><input id="edit_accountId"
							name="accountId" type="hidden" class="text"
							value="${accountFlow.accountId}" /> <input id="edit_accountNo"
							name="accountNo" type="text" class="text"
							value="${accountFlow.accountNo}" /></td>
						<td class="inputLabelTd">科目类型：</td>
						<td class="inputTd"><select class="input_select"
							id="groupFinanceType" name="accountType" style="float: none">
								<option value="">--请选择--</option>
								<c:forEach items="<%=FinanceType.values() %>" var="status">
									<option value="${status.value }">${status.name }</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td class="inputLabelTd">科目名称：</td>
						<td class="inputTd"><input id="edit_accountName"
							name="accountName" type="text" class="text"
							value="${accountFlow.accountName}" /></td>

						<td class="inputLabelTd">发生时间：</td>
						<td class="inputTd">
							<div class="time_bg">
								<input type="text" class="search_time150" name="createDate"
									id="edit_createDate" mainid="createDate">
								<!-- 时间选择控件-->
								<i class="search_time_ico2"></i>
							</div>

						</td>
					</tr>
					<tr>
						<td class="inputLabelTd">发生金额：</td>
						<td class="inputTd"><input id="edit_money" name="money"
							type="number" class="text" value="${accountFlow.money}" /></td>
						<td class="inputLabelTd">备注：</td>
						<td class="inputTd"><input id="edit_note" name="note"
							type="text" class="text" value="${accountFlow.note}" /></td>
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
