<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<script type="text/javascript">
$(function() {
	//绑定提交按钮click事件
	$("#submit").click(function() {
		if(!biz.validate("valid",$('#empCostFormEdit')[0])){
			showWarn("数据验证失败",3000);
			return;
		}
		var options = {
			url : "<m:url value='/empCost/updateEmpCost.do'/>",
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
		$('#empCostFormEdit').ajaxSubmit(options);
	});

	/*编辑表单数据验证*/
	new biz.validate({
		id:"#empCostFormEdit",
		rules:{
		}
	}); 
	new biz.datepicker({
		id : "#edit_month",
		minDate:'#F{$dp.$D(\'edit_month\',{d:0});}',
		dateFmt:'yyyy-MM-dd'
	});
	
	$("#edit_costType").on("change",function(){
		var _value = $(this).val();
		switch (_value) {
		case "1":
			$(".hiddenRow").css("display","none");
			break;
		case "2":
			$(".hiddenRow").css("display","table-row");
			break;
		default:
			break;
		}
	})
});
/*自动搜寻转化人 自动完成(Autocomplete) 根据用户输入值进行搜索和过滤,让用户快速找到并从预设值列表中选择*/
function getEmpIDByName(obj,value) {
	var did = $("#empList").find("option[value="+value+"]").attr('did');
	$(obj).parents('.hiddenRow').find("#edit_empId").val(did);
}

function getEmpByOrgID(obj,value) {
	var did = $("#orgList").find("option[value="+value+"]").attr('did');
	var paramDatas = {
			deptNo:did
			};
	$ .ajax({
		url: "<m:url value='/employee/listEmployee.do'/>",
		cache:false,
		dataType:"json",
		data: paramDatas,
		success: function(data, textStatus, jqXHR){
			$('#empList option').remove();
			for(var i in data.rows){
				if(data.rows[i].deptNo){
					$('#empList').append('<option did="'+data.rows[i].id+'" value="'+data.rows[i].nickName+'">'+data.rows[i].name+' </option>');
				}
			}
			if(!data.rows[0]){
				$('#empList option').remove();
			}
		}
	});
}

</script>
</head>

<body>

	<div id="editDialog">
		<form id="empCostFormEdit">
			<div class="center_body ui-table ui-widget ui-corner-all ui-border">
				<input type="hidden" id="edit_id" name="id" type="text" value="${empCost.id}" />
				<table class="table">
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>成本类型：</td>
						<td class="inputTd">
							<select id="edit_costType" name="costType" class="input_select">
								<option value="1"<c:if test="${empCost.costType=='1'}">selected</c:if>>公摊成本</option>
								<option value="2"<c:if test="${empCost.costType=='2'}">selected</c:if>>个人承担</option>
							</select>
						</td>
						<td class="inputLabelTd"><span class="required">*</span>发生时间：</td>
						<td class="inputTd">
							<div class="time_bg" style="width: 168px;">
								<input type="text" class="search_time150 valid text" name="month" id="edit_month" value="${empCost.month}">
								<i class="search_time_ico1"></i>
							</div>
						</td>
					</tr>
					<tr class="hiddenRow" <c:if test="${empCost.costType=='1'}">style="display:none"</c:if>>	
						<td class="inputLabelTd"><span class="required">*</span>部门：</td>
						<td class="inputTd">
							<input id="edit_empDept" name="empDept" type="text" class="text" list="orgList" onchange="getEmpByOrgID(this,this.value);" value="${empCost.empDept}"/>
							<datalist id="orgList">
								<c:forEach var="org" items="${org}">
									<option value="${org.orgName}" did="${org.orgNo}"></option>
								</c:forEach>
							</datalist>
						</td>
						<td class="inputLabelTd"><span class="required">*</span>责任人：</td>
						<td class="inputTd">
							<input id="edit_empId" name="empId" type="hidden" value="${empCost.empId}" />
							<input id="edit_empName" name="empName"  value="${empCost.empNickName}"
								type="text" class="text" list="empList" 
								onchange="getEmpIDByName(this,this.value);"/>
								<dataList  id="empList">
									<c:forEach var="emp" items="${empList}">
										<option did="${emp.id}"  value="${emp.nickName}"><c:out value="${emp.name}"></c:out>	</option>
									</c:forEach>
								</dataList>
						</td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>金额：</td>
						<td class="inputTd">
							<input id="edit_costMoney" name="costMoney" type="text" class="text" value="${empCost.costMoney}" />
						</td>
						<td class="inputLabelTd"><span class="required">*</span>详细内容：</td>
						<td class="inputTd" >
							<input id="edit_costContent" name="costContent" type="text" class="text" value="${empCost.costContent}" />
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
