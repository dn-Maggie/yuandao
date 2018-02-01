<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
</head>
<body>

	<div id="editDialog">
		<form id="vipRefundFormEdit">
			<div class="ui-table ui-widget ui-corner-all ui-border">
				<input id="edit_stuId" name="stuId" type="hidden"
					value="${vipRefund.stuId}" /> <input type="hidden" id="edit_id"
					name="id" type="text" value="${vipRefund.id}" />
				<table class="table">
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>学员姓名：</td>
						<td class="inputTd"><input id="edit_stuName" name="stuName"
							type="text" class="text" onclick="allVipMgt();" readonly /></td>
						<td class="inputLabelTd"><span class="required">*</span>退款金额：</td>
						<td class="inputTd"><input id="edit_refund" name="refund"
							type="text" class="text" value="${vipRefund.refund}"
							onblur="isAll()" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd">报名学科：</td>
						<td class="inputTd"><input id="subname" name="subname"
							type="text" class="text" readonly /></td>
						<td class="inputLabelTd">报名课程：</td>
						<td class="inputTd"><input id="edit_subjectId"
							name="subjectId" type="hidden" /> <input id="edit_courseId"
							name="courseId" type="hidden" /> <input id="coursename"
							name="coursename" type="text" class="text" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td class="inputLabelTd">退款原因：</td>
						<td class="inputTd"><input id="edit_reason" name="reason"
							type="text" class="text" value="${vipRefund.reason}" /> <input
							id="edit_isCount" name="isCount" type="hidden" /></td>
						<td class="inputLabelTd">退款时间：</td>
						<td class="inputTd"><input id="edit_time" name="time"
							type="text" class="text" value="${vipRefund.time}" /></td>
					</tr>
					<tr>
						<td class="inputTd" colspan="4" style="text-align: center;">
							<input id="submit" type="button" class="ti_bottom" value="保存" />
						</td>
					</tr>
				</table>
				<input type="hidden" id="accountNo" name="accountNo"
					value="${accountFlow.accountNo}" /> <input type="hidden"
					id="accountId" value="${accountFlow.id}" /> <input type="hidden"
					id="accountType" value="${accountFlow.accountType}" /> <input
					type="hidden" id="accountName" value="${accountFlow.accountName}" />
				<input type="hidden" id="comSource" /> <input type="hidden"
					id="followerId" /> <input type="hidden" id="followerName" /> <input
					type="hidden" id="followerType" /> <input type="hidden"
					id="followerPosition" /> <input type="hidden" id="joinTime" /> <input
					type="hidden" id="shouldPay" />
			</div>
		</form>
	</div>
	<script type="text/javascript">
	var createDate = new Date().format('yyyy-MM-dd hh:mm:ss');//获取今日时间
	/* var i = 0;//计数器i */
	var shouldPay,allPay = 0;//全额
	/*定义数组
	*followerTypes转化人类型ID 
	*followerPositions转化人类型名
	*followerIds转化人ID 
	*followerNames转化人名称
	*/
	var followerTypes = [];
	var followerPositions = [];
	var followerIds = [];
	var followerNames = [];
$(function() {
	//默认设置为当前日期
	$("#edit_time").val(createDate);
	
	//绑定提交按钮click事件
	$("#submit").click(function() {
		$("#submit").prop('disabled', true).css({'cursor':'not-allowed'});
		showMessage("正在处理，请稍后...");
		//验证表单数据是否完整正确输入
		if(!biz.validate("valid",$('#vipRefundFormEdit')[0])){
			showWarn("数据验证失败",3000);
			$("#submit").prop('disabled', false).css({'cursor':'pointer'});
			return;
		}
		
		var options = {
			url : "<m:url value='/vipRefund/addVipRefund.do'/>",
			type : "post",
				dataType:"json",
				success : function(d) {
					//修改Vip学生信息目前状态
					updateVipOwe();
					//新增数据统计信息					
					addOrderInfo();
					if(d.status){
						showMessage(d.message,"","",function(){
							window.parent.closeAdd();
							List.doSearch(window.parent.gridObj);
						});
					}else{
						showMessage(d.message);
					}
				}
		};
		// 将options传给ajaxForm
		$('#vipRefundFormEdit').ajaxSubmit(options);
	});

	/*编辑表单数据验证*/
	new biz.validate({
		id:"#vipRefundFormEdit",
		rules:{
			"stuName" :{required : true},
			"refund":{required : true},
			"time":{required : true}
		}
	}); 
	
	new biz.datepicker({
		id : "#edit_time",
		maxDate:'#F{$dp.$D(\'edit_time\',{d:0});}',
		dateFmt:'yyyy-MM-dd HH:mm:ss'
	});
});

//打开欠费Vip学员信息界面
function allVipMgt() {
	var url = baseUrl + '/vipRefund/toSelectAllVip.do';
	var title = "学员信息列表";
	allViplist_iframe_dialog = Add.create(url, title);
	List.openDialog(allViplist_iframe_dialog);
}

// 关闭欠费Vip学员信息界面
function closeAllVip() {
	List.closeDialog(allViplist_iframe_dialog);
}

//填充数据
function drawForm(rowData) {
	$('#edit_stuId').val(rowData.id);
	$('#edit_stuName').val(rowData.name);
	$('#edit_subjectId').val(rowData.subjectId);
	$('#edit_courseId').val(rowData.courseId);
	$('#subname').val(rowData.subjectName);
	$('#coursename').val(rowData.courseName);
	rowData.followerType.length>1?$("#edit_isCount").val("已分配业绩"):$("#edit_isCount").val("未分配业绩");
	$('#followerType').val(rowData.followerType);
	$('#shouldPay').val(rowData.shouldPay);
	allPay = rowData.actualPay;
	$('#edit_refund').val(rowData.actualPay);
	$('#followerPosition').val(rowData.followerPosition);
	$('#followerId').val(rowData.followerId);
	$('#followerName').val(rowData.followerName);
	$('#joinTime').val(rowData.joinTime);
	shouldPay=rowData.shouldPay;
}
//修改Vip学生信息表中付款信息以及目前状态
function updateVipOwe(){
	if(Number($("#edit_refund").val()) == Number(allPay)){
		var owe= 0;
		var actualPay= 0;
		var shouldPay = 0;
		var currStatus = "已退款";
	}else if($("#edit_refund").val()<allPay){
		var actualPay=  Number(allPay) - Number($("#edit_refund").val());
		var owe=  Number($('#shouldPay').val()) - actualPay;
		var currStatus = "分期付款";
	}
	var stuId=$("#edit_stuId").val();
	var paramDatas = {
			owePay:owe,id:stuId,actualPay:actualPay,shouldPay:$('#shouldPay').val(),currStatus:currStatus};
	$.ajax({
		   type: "post",
		   url: "<m:url value='/vipStudent/updateOweVipStudent.do'/>",
		   data: paramDatas,
		   cache: false,
		   dataType:"json",
		   success:function(response){
			 //增加财务流水信息
			 addAccountFlow();
		   }
		});
}
//判断是否全额退款
function isAll(){
	if(Number($("#edit_refund").val()) > Number(allPay)){
		showMessage("退款金额不得大于实缴金额");
	}
}

//增加退款财务流水信息
function addAccountFlow(){
	if(($("#edit_time").val()).length>0){
		createDate = $("#edit_time").val();
	}else{
		createDate= createDate;
	}	
	var stuId = $("#edit_stuId").val();
	var money=$("#edit_refund").val();
	var accountId = $("#accountId").val();
	var accountNo = $("#accountNo").val();
	var accountType = $("#accountType").val();
	var accountName = $("#accountName").val();
	var note = '学费退款 冲减本月学费收入';
	var paramDatas = {
					stuId:stuId,
					money:parseFloat(money),
					accountId:accountId,
					accountName:accountName,
					accountType:accountType,
					accountNo:accountNo,
					createDate:createDate,
					note:note
					};
	$.ajax({
		   type: "post",
		   url: "<m:url value='/accountFlow/addAccountFlow.do'/>",
		   data: paramDatas,
		   cache: false,
		   dataType:"json",
		   success:function(response){
			   //满足6月12日以后的才不算业绩
			   if($('#joinTime').val()>"2016-06-12"){
				 //扣除相应业绩
				 countPerformance();
			   }
		   }
		});
}

function countPerformance(){
	followerPositions=$("#followerPosition").val().split(",");
	followerTypes=$("#followerType").val().split(",");
	followerIds =$("#followerId").val().split(",");
	followerNames =$("#followerName").val().split(",");
	for(var i = 0;i<followerNames.length;i++){
			var folname = followerNames[i];
			var foltype = followerTypes[i];
			var folid = followerIds[i];
			var folposition = followerPositions[i];
			count(folid,foltype);
	}
	//冲减业绩表中的数据		
	function count(folid,foltype){
		var employeeId = folid;
		var stuId = $("#edit_stuId").val();
		var employeeName = folname;
		var position = folposition;
		var actualPay = parseFloat(0)-parseFloat($("#edit_refund").val());
		var performance = parseFloat(actualPay);
		if(($("#edit_time").val()).length>0){
			createDate = $("#edit_time").val();
		}else{
			createDate= createDate;
		}	
		var note = "学费退款-"+actualPay;
		var isPass="未清算";
		var paramDatas = {
				employeeId:employeeId,
				employeeName:employeeName,
				position:position,
				shouldPay:0,
				actualPay:parseFloat(actualPay),
				performance:parseFloat(performance),
				createDate:createDate,
				isPass:isPass,
				note:note,
				stuId:stuId
				};
		$.ajax({
				 type: "post",
				 url: "<m:url value='/empPerformance/addEmpPerformance.do'/>",
				 data: paramDatas,
				 cache: false,
				 dataType:"json",
				});
		}
}
//增加订单信息
function addOrderInfo(){
	if(Number($("#edit_refund").val()) == Number(allPay)){
		var shouldPay= 0;
	}else if(Number($("#edit_refund").val()) < Number(allPay)){
		var shouldPay= 0 ;
	}
	
	var orderType=3;//类型3：学费退款
	var stuId = $("#edit_stuId").val();
	var stuName = $.trim($("#edit_stuName").val());
	var actualPay =-$('#edit_refund').val();
	var subjectName = $.trim($("#subname").val());
	var courseName = $.trim($('#coursename').val());
	var paramDatas = {
			orderType:orderType,
			stuId:stuId,
			stuName:stuName,
			shouldPay:shouldPay,
			actualPay:actualPay,
			subjectName:subjectName,
			courseName:courseName,
			createTime:createDate
					};
	$.ajax({
		   type: "post",
		   url: "<m:url value='/orderInfo/addOrderInfo.do'/>",
		   data: paramDatas,
		   cache: false,
		   dataType:"json",
		});
}
</script>

</body>
</html>
