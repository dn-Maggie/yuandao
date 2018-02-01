<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/styles/extends/css/expenseAccount.css" />
<script type="text/javascript">
var createDate = new Date().format('yyyy-MM-dd hh:mm:ss');//获取今日时间
$(function() {
	//绑定提交按钮click事件
	$("#submit").click(function() {
		if(!biz.validate("valid",$('#expenseAccountFormEdit')[0])){
			showWarn("数据验证失败",3000);
			return;
		}
		var options = {
			url : "<m:url value='/expenseAccount/updateExpenseAccount.do'/>",
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
		$('#expenseAccountFormEdit').ajaxSubmit(options);
	});

	/*编辑表单数据验证*/
	new biz.validate({
		id:"#expenseAccountFormEdit",
		rules:{
			"expenseWay" :{required : true},
			"expenseMoney" : {required : true,number:true},
			"content" :{required : true,},
			"enterPid" : {required : true},
			"enterDate" : {required : true},
			"docAttach" : {required : true,digits:true,max:100},
		}
	}); 
	/*日期格式化*/
	new biz.datepicker({
		id : "#enterDate",
		maxDate:'#F{$dp.$D(\'enterDate\',{d:0});}',
		dateFmt:'yyyy-MM-dd HH:mm:ss'
	});
});

/*自动完成(Autocomplete) 根据用户输入值进行搜索和过滤,让用户快速找到并从预设值列表中选择*/
function getEmpIDByName(obj,value) {
	var did = $("#empList").find("option[value="+value+"]").attr('did');
	$(obj).parents('td').find("#edit_enterPid").val(did);
}
/*自动完成(Autocomplete) 根据用户输入值进行搜索和过滤,让用户快速找到并从预设值列表中选择*/
function getOrgIDByName(obj,value) {
	var did = $("#orgList").find("option[value="+value+"]").attr('did');
	$(obj).parents('td').find("#edit_deptNo").val(did);
}
</script>
</head>

<body>

	<div id="editDialog">
		<form id="expenseAccountFormEdit">
			<div class="wrap">
				<input type="hidden" id="edit_id" name="id" type="text"
					value="${expenseAccount.id}" />
				<div class="top_head">
					<h2 class="top_name">报 销 单</h2>
					<div
						style="top: 40px; left: 0px; height: 25px; line-height: 25px; position: absolute;">
						<span>报销类型：</span> <select class="input_select" name="expenseType"
							id="edit_expenseType" mainid="expenseType" style="width: 100px">
							<option value="个人报销"
								<c:if test="${expenseAccount.expenseType=='个人报销'}">selected</c:if>>个人报销</option>
							<option value="集体报销"
								<c:if test="${expenseAccount.expenseType=='集体报销'}">selected</c:if>>集体报销</option>
						</select>
					</div>
					<div class="time_bg"
						style="top: 40px; right: 0px; height: 25px; line-height: 25px; position: absolute;">
						<span>申请日期&nbsp;&nbsp;</span> <input id="enterDate" type="text"
							class="search_time150" name="enterDate" style="height: 25px;"
							value="${expenseAccount.enterDate}"> <i
							class="search_time_ico2" style="top: 6px;"
							onclick="WdatePicker({el:'enterDate'})"></i>
					</div>
				</div>
				<div class="center_body">
					<table class="table">
						<tr>
							<td class="inputLabelTd"><span class="required">*</span>所属部门：</td>
							<td class="inputTd"><input id="edit_deptNo" name="deptNo"
								type="hidden" value="${expenseAccount.deptNo}" /> <input
								id="edit_deptName" name="deptName" type="text" class="text"
								list="orgList" onchange="getOrgIDByName(this,this.value);"
								value="${expenseAccount.deptName}" /> <datalist id="orgList">
									<c:forEach var="org" items="${org}">
										<option value="${org.orgName}" did="${org.id}"
											label="${org.orgFullName}"
											<c:if test="${expenseAccount.deptName==org.orgName}">selected</c:if>></option>
									</c:forEach>
								</datalist></td>
							<td class="inputLabelTd"><span class="required">*</span>报销申请人：</td>
							<td class="inputTd"><input id="edit_enterPid"
								name="enterPid" type="hidden" value="${expenseAccount.enterPid}" />
								<input id="edit_enterName" name="enterName" type="text"
								class="text" list="empList"
								onchange="getEmpIDByName(this,this.value);"
								value="${expenseAccount.enterName}" /> <datalist id="empList">
									<c:forEach var="tutor" items="${tutor}">
										<option value="${tutor.nickName}" did="${tutor.id}"
											label="${tutor.name}"
											<c:if test="${expenseAccount.enterName==tutor.nickName}">selected</c:if>></option>
									</c:forEach>
								</datalist></td>
						</tr>

						<tr class="trDetails" style="height: 100px;">
							<td class="inputLabelTd"><span class="required">*</span>报销项目：</td>
							<td class="inputTd" colspan="3"><textarea class="text"
									name="content" id="edit_content">${expenseAccount.content}</textarea>
							</td>
						</tr>
						<tr>
							<td class="inputLabelTd"><span class="required">*</span>报销金额：</td>
							<td class="inputTd"><input id="edit_expenseMoney"
								name="expenseMoney" type="text" class="text"
								value="${expenseAccount.expenseMoney}" /></td>
							<td class="inputLabelTd"><span class="required">*</span>报销方式：</td>
							<td class="inputTd"><select class="input_select text"
								name="expenseWay" id="edit_expenseWay" mainid="expenseWay"
								style="width: 97%">
									<option value="支付现款"
										<c:if test="${expenseAccount.expenseWay=='支付现款'}">selected</c:if>>支付现款</option>
									<option value="银行转账"
										<c:if test="${expenseAccount.expenseWay=='银行转账'}">selected</c:if>>银行转账</option>
							</select></td>
						</tr>
						<tr>
							<td class="inputLabelTd" colspan="4" style='text-align: right'>
								<span class="required">*</span>附单据： <input id="edit_docAttach"
								name="docAttach" type="text" class="text"
								style="width: 50px; text-align: center;"
								value="${expenseAccount.docAttach}" />张 <input
								id="edit_checkFlag" name="checkFlag" type="hidden"
								value="${expenseAccount.checkFlag}" />
							</td>
						</tr>
						<tr>
							<td class="inputLabelTd">图片证明：</td>
							<td class="inputTd" colspan="3"><input id="fileData"
								name="fileUrl" type="hidden" value="${expenseAccount.fileUrl}">
								<input id="file" type="file" class="text"
								style="height: 20px; line-height: 20px;" /></td>
						</tr>
					</table>
					<c:if test="${expenseAccount.fileUrl} != ''">
						<div style="display: block; margin-top: 5px">
							<img alt="图片证明" style="height: 600px; width: 100%"
								src="${expenseAccount.fileUrl}">
						</div>
					</c:if>
					<div class="inputTd"
						style="display: block; margin-top: 50px; text-align: center;">
						<input id="submit" type="button" class="ti_bottom" value="保存" /> <input
							id="reset" type="button" class="ti_bottom" value="取消"
							onclick="window.parent.closeEdit()" />
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
