<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
</head>

<body>
	<div id="editDialog">
		<form id="continuePayFormEdit" style="margin-top: 5px;">
			<div class="ui-table ui-widget ui-corner-all ui-border">
				<input type="hidden" id="edit_id" name="id" type="text"
					value="${continuePay.id}" /> <input id="edit_stuId" name="stuId"
					type="hidden" />
				<table class="table">
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>学员姓名：</td>
						<td class="inputTd"><input id="edit_stuName" name="stuName"
							type="text" class="text" onclick="ownVipMgt();"
							readonly="readonly" /></td>
						<td class="inputLabelTd">报名课程：</td>
						<td class="inputTd"><input id="edit_subjectId"
							name="subjectId" type="hidden" /> <input id="edit_subjectName"
							name="subjectName" type="hidden" /> <input id="edit_courseId"
							name="courseId" type="hidden" /> <input id="subname"
							name="subname" type="text" class="text" readonly="readonly" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>应付欠款：</td>
						<td class="inputTd"><input id="edit_owePay" name="owePay"
							type="text" class="text" readonly="readonly" /></td>
						<td class="inputLabelTd"><span class="required">*</span>补款金额：</td>
						<td class="inputTd"><input id="edit_money" name="money"
							type="text" class="text" onblur="countOwePay()" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd">欠款余额：</td>
						<td class="inputTd"><input id="edit_owe" name="owe"
							type="text" class="text" readonly="readonly" /></td>
						<td class="inputLabelTd">补款时间：</td>
						<td class="inputTd">
							<div class="time_bg">
								<input type="text" class="search_time150 valid" name="time"
									id="edit_time" mainid="time">
								<!-- 时间选择控件-->
								<i class="search_time_ico2"></i>
							</div>
						</td>
					</tr>
					<tr>
						<td class="inputLabelTd">备注：</td>
						<td class="inputTd"><input id="edit_notes" name="notes"
							type="text" class="text" value="${continuePay.notes}" /> <input
							id="edit_isCount" name="isCount" type="hidden" /></td>
					</tr>
					<tr>
						<td class="inputTd" colspan="4" style="text-align: center;">
							<input id="submit" type="button" class="ti_bottom" value="保存" />
							<input type="button" class="ti_bottom"
							onclick="resetForm('continuePayFormEdit')" value="重置" />
						</td>
					</tr>
				</table>
				<input type="hidden" id="accountNo" name="accountNo"
					value="${accountFlow.accountNo}" /> <input type="hidden"
					id="accountId" value="${accountFlow.id}" /> <input type="hidden"
					id="accountType" value="${accountFlow.accountType}" /> <input
					type="hidden" id="accountName" value="${accountFlow.accountName}" />
				<input type="hidden" id="comSource" /> <input type="hidden"
					id="comSourceName" /> <input type="hidden" id="sourceSubclass" /> <input
					type="hidden" id="followerId" /> <input type="hidden"
					id="followerName" /> <input type="hidden" id="followerType" /> <input
					type="hidden" id="followerPosition" /> <input type="hidden"
					id="followerRate" /> <input type="hidden" id="joinTime" />
			</div>
		</form>
	</div>

	<script type="text/javascript">
/*获取今日时间*/
var createDate = new Date().format('yyyy-MM-dd hh:mm:ss');//获取今日时间
/*计数器i*/
var i = 0;

var currStatus = "分期付款";
var shouldPay = $('#shouldPay').val();
var actualPay = 0;
var owe = 0;
var stuId = "";
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
var followerRates = [];
$(function() {
	$("#edit_time").val(createDate);
	
	//绑定提交按钮click事件
	$("#submit").click(function() {
		$("#submit").prop('disabled', true).css({'cursor':'not-allowed','backgroundColor':'#ccc'});
		//验证表单数据是否完整正确输入
		if(!biz.validate("valid",$('#continuePayFormEdit')[0])){
			showWarn("数据验证失败",3000);
			return;
		}
		
		//ajax验证本月补款是否已录入
		var contPayMoney = $('#edit_money').val();
		if(contPayMoney>0){
			ajaxGetContinuePayInfo(contPayMoney);
		}
		
	});

	/*编辑表单数据验证*/
	new biz.validate({
		id:"#continuePayFormEdit",
		rules:{
			"money":{required:true,minlength:2,min:1,number:true},
			"owePay":{required : true},
			"owe":{required : true},
		}
	}); 
	
	new biz.datepicker({
		id : "#edit_time",
		maxDate:'#F{$dp.$D(\'edit_time\',{d:0});}',
		dateFmt:'yyyy-MM-dd HH:mm:ss'
	});
});
//打开Vip学员信息界面
function ownVipMgt() {
	var url = baseUrl + '/continuePay/toSelectOwnVip.do';
	ownViplist_iframe_dialog = new biz.dialog(
			{
				id : $('<div id="sublist_window_iframe"></div>')
						.html(
								'<iframe id="iframeSublist" name="iframeSublist" src="'
										+ url
										+ '" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
				modal : true,
				width : $(window).width(),
				height : $(window).height(),
				title: "vip学员列表"
			});
	ownViplist_iframe_dialog.open();
}

// 关闭Vip学员信息界面
function closeOwnVip() {
	ownViplist_iframe_dialog.close();
}

//填充数据
function drawForm(rowData) {
	$('#edit_stuId').val(rowData.id);
	$('#edit_stuName').val(rowData.name);
	$('#edit_owePay').val(rowData.owePay);
	$('#edit_subjectId').val(rowData.subjectId);
	$('#edit_subjectName').val(rowData.subjectName);
	$('#edit_courseId').val(rowData.courseId);
	$('#subname').val(rowData.courseName);
	if((rowData.followerType).length>1){
		$("#edit_isCount").val("已分配业绩");
	}else{
		$("#edit_isCount").val("未分配业绩");
	}
	$('#followerType').val(rowData.followerType);
	$('#followerPosition').val(rowData.followerPosition);
	$('#followerId').val(rowData.followerId);
	$('#followerName').val(rowData.followerName);
	$('#joinTime').val(rowData.joinTime);
	$('#shouldPay').val(rowData.shouldPay);
	shouldPay=rowData.shouldPay;
}

//自动计算欠款金额
function countOwePay(){
	var money = parseFloat($('#edit_money').val());
	var owePay = parseFloat($('#edit_owePay').val());
		if(money>owePay){
			showMessage("补款金额不能大于应付欠款");
			$("#edit_money").val(0);
			$('#edit_owe').val(0);
		}else{
			$('#edit_owe').val((parseFloat(owePay)-parseFloat(money)).toFixed(2));
			if($('#edit_owe').val()>0){
				currStatus = "分期付款";
			}else{
				currStatus = "已付全款";
			}
		}
}

//ajax验证本月补款是否已录入
function ajaxGetContinuePayInfo(contPayMoney) {
	stuId=$("#edit_stuId").val();
	subjectId=$("#edit_subjectId").val();
	courseId=$("#edit_courseId").val();
	var contPayMonth = $('#edit_time').val();
	var paramDatas = {
			money:contPayMoney,
			stuId:stuId,
			time:contPayMonth,
			subjectId:subjectId,
			courseId:courseId
			};
		$.ajax({
				type: "post",	
				url : "<m:url value='/continuePay/ajaxGetContinuePayInfo.do'/>",
				data: paramDatas,
				cache : false,
				async : false,
				dataType:"json",
				success : function(data, textStatus, jqXHR) {
					if(data){
						new biz.alert({
							type:"confirm",
							message:"该生本月已存在一条相同金额的补款记录，重复录入将导致欠款数据改变，是否继续？",
							callback:function(result){
			    			if(result){
			    				addContinuePay();
			    				return;
			    			}
			    		}}) ;   
					}else{
	    				addContinuePay();
					}
				}
			});
}

//增加一条学生补款记录
function addContinuePay(){
	var options = {
			url : "<m:url value='/continuePay/addContinuePay.do'/>",
			type : "post",
				dataType:"json",
				success : function(d) {
					//修改Vip学生信息目前状态
					updateVipOwe();
					//新增财务信息
					addAccountFlow();
					//新增订单信息
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
		$('#continuePayFormEdit').ajaxSubmit(options);
}
//更新Vip学生信息表中余额信息以及目前状态
function updateVipOwe(){
	owe=$("#edit_owe").val();
	actualPay = parseFloat(shouldPay)-parseFloat(owe);
	stuId=$("#edit_stuId").val();
	if(owe==0){
		currStatus="已付全款";
	}
	var paramDatas = {
			owePay:owe,
			id:stuId,
			shouldPay:shouldPay,
			actualPay:actualPay,
			currStatus:currStatus};
	$.ajax({
		   type: "post",
		   url: "<m:url value='/vipStudent/updateOweVipStudent.do'/>",
		   data: paramDatas,
		   cache: false,
		   dataType:"json",
		   success:function(response){
		   }
		});
}

//增加财务流水信息
function addAccountFlow(){
	var createDate;
	if(($("#edit_time").val()).length>0){
		createDate = $("#edit_time").val();
	}else{
		createDate= createDate;
	}	
	var stuId=$("#edit_stuId").val();
	var money=$("#edit_money").val();
	var accountId = $("#accountId").val();
	var accountNo = $("#accountNo").val();
	var accountType = $("#accountType").val();
	var accountName = $("#accountName").val();
	var note = "学费补款";
	var paramDatas = {
			stuId:stuId,
					money:money,
					accountId:accountId,
					accountName:accountName,
					accountType:accountType,
					accountNo:accountNo,
					createDate:createDate,
					note:note,
					};
	$.ajax({
		   type: "post",
		   url: "<m:url value='/accountFlow/addAccountFlow.do'/>",
		   data: paramDatas,
		   cache: false,
		   dataType:"json",
		   success:function(response){
			 	//只对6月12号以后的数据进行业绩分配操作
				if($('#joinTime').val()>"2016-06-12"){
					//计算分配业绩
					countPerformance();
				   }
		   }
		});
}

function countPerformance(){
	//获取分配比率
	followerPositions=$("#followerPosition").val().split(",");
	followerTypes=$("#followerType").val().split(",");
	followerIds =$("#followerId").val().split(",");
	followerNames =$("#followerName").val().split(",");
	//分两种情况增加分配比率：1.未分配业绩订单（及以前遗留订单）  2.已分配业绩订单	
	//2.已分配业绩订单
	if(followerNames.length>=1){
		for(var i = 0;i<followerNames.length;i++){
				var folname = followerNames[i];
				var foltype = followerTypes[i];
				var folid = followerIds[i];
				var folposition = followerPositions[i];
			    count(folid,foltype);
		}
	}
// 	//1.未分配业绩订单（及以前遗留订单）
// 	else if(comSource==""||followerNames[0]==""||followerNames[0]==undefined){
// 		count(1,1);
// 	}
	//计算业绩并插入一条新数据		
	function count(folid,foltype){
		var subname="";
		var employeeId = folid;
		var stuId = $("#edit_stuId").val();
		var employeeName = folname;
		var position = folposition;
		var actualPay = $("#edit_money").val();
		var performance = actualPay;
		var createDate;
		if(($("#edit_time").val()).length>0){
			createDate = $("#edit_time").val();
		}else{
			createDate= createDate;
		}	
		var note = "学费补款-"+actualPay;
		var isPass="未清算";
		var paramDatas = {
				employeeId:employeeId,
				employeeName:employeeName,
				position:position,
				shouldPay:parseFloat(shouldPay),
				actualPay:parseFloat(actualPay),
				performance:parseFloat(performance),
				createDate:createDate,
				note:note,
				isPass:isPass,
				stuId:stuId
				};
		$.ajax({
				 type: "post",
				 url: "<m:url value='/empPerformance/addEmpPerformance.do'/>",
				 data: paramDatas,
				 cache: false,
				 dataType:"json",
				 success:function(response){
				 }
				});
		}
}
//增加订单信息
function  addOrderInfo(){
	var orderType=2;//类型2：学费补款
	var stuId = $("#edit_stuId").val();
	var stuName = $.trim($("#edit_stuName").val());
	var actualPay = $("#edit_money").val();
	var subjectName = $.trim($('#edit_subjectName').val());
	var courseName = $.trim($("#subname").val());
	var paramDatas = {
			orderType:orderType,
			stuId:stuId,
			stuName:stuName,
			actualPay:actualPay,
			subjectName:subjectName,
			courseName:courseName,
			createTime:createDate,
			};
	$.ajax({
		   type: "post",
		   url: "<m:url value='/orderInfo/addOrderInfo.do'/>",
		   data: paramDatas,
		   cache: false,
		   dataType:"json",
		   success:function(response){
			   return true;
		   }
		});
}
</script>
</body>
</html>
