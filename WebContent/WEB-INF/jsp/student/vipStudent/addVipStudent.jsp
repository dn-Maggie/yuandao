<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<script type="text/javascript">
var createDate = new Date().format('yyyy-MM-dd');//获取今日时间
var currStatus = "";//初始化学生状态
$(function() {	
	$("#edit_joinTime").val(createDate);
	//初始化课程List
	courseChange($("#edit_subjectId").val());
	//绑定提交按钮click事件
	$("#submit").click(function() {
			$("#submit").prop('disabled', true).css({'cursor':'not-allowed'});
			showMessage("正在处理，请稍后...");
			if(!biz.validate("valid",$('#vipStudentFormEdit')[0])){
				showWarn("数据验证失败",3000);
				$("#submit").prop('disabled', false).css({'cursor':'pointer'});
				return;
			}
			//验证数据是否存在
			var stuqq = $("#edit_qq").val();
			var subjectid = $("#edit_subjectId").find("option:selected").val();
			var courseid = $("#edit_courseId").val();
		  	ajaxGetVipInfoByStu(stuqq,subjectid,courseid);
	});
	
	/*报名日期格式化*/
	new biz.datepicker({
		id : "#edit_joinTime",
		maxDate:'#F{$dp.$D(\'edit_joinTime\',{d:0});}',
		dateFmt:'yyyy-MM-dd'
	});
	
	/*编辑表单数据验证*/
	new biz.validate({
		id:"#vipStudentFormEdit",
		rules:{
			"name" :{required : true},
			"qq" : {required : true,number:true,rangelength:[5,12],digits:true },
			"phone" :{required : true},
			"age" : {required : true},
			"subjectId" : {required : true},
			"shouldPay" : {required : true,number:true,min:1},
			"actualPay" : {required : true,number:true,min:1},
		}
	}); 
});
//根据交易号查询订单是否存在
function ajaxGetVipInfoByStu(stuqq,subjectid,courseid) {
	var b = false;
	var paramDatas = {
			qq:stuqq,
			subjectId:subjectid,
			courseId:courseid,
			};
		$.ajax({
					url : "<m:url value='/vipStudent/ajaxGetVipInfoByStu.do'/>",
					cache : false,
					data: paramDatas,
					async : false,
					success : function(data, textStatus, jqXHR) {
						if(data=="true"){
							new biz.alert({
								type:"confirm",
								message:"已存在该vip会员信息，请确定是否重复录入？",
								callback:function(result){
				    			if(result){
				    				addVipStudent();
				    				return;
				    			}
				    		}}) ;   
						}else{
							addVipStudent();
						}
					}
				});
	return b;
}
//报名课程随报名学科联动
function courseChange(val){
	if(val){
		$("#edit_courseName").val("");
		$ .ajax({
			url: "<m:url value='/vipStudent/getCourseList.do'/>?key="+val,
			cache:false,
			dataType:"json",
			success: function(data, textStatus, jqXHR){
				$('#courseList option').remove();
				for(var i in data.course){
					if(data.course[i].id){
						$('#courseList').append('<option value="'+data.course[i].name+'" did="'+data.course[i].id+'"></option>');
					}
				}
			},error:function(){
				$('#courseList option').remove();
			}
		});
	}else{$('#courseList option').remove();}
}

//自动计算欠款金额
function countOwePay(){
		var actualPay=Number($("#edit_actualPay").val());
		var shouldPay=Number($("#edit_shouldPay").val());
		if(actualPay>shouldPay){
			showMessage("实缴学费不能大于应缴学费");
			$("#edit_actualPay").val(0);
			$("#edit_shouldPay").val(0);
			$('#edit_owePay').val(0);
		}else{
			$('#edit_owePay').val((parseFloat(shouldPay)-parseFloat(actualPay)).toFixed(2));
			$('#edit_owePay').val()>0?currStatus="分期付款":currStatus="已付全款";
		}
}
//自动填入付款方名称
function drawPayName(val){
		$('#edit_payerName').val(val);
}
//根据课程改变名获取课程Id
function getIdByName(obj,value) {
	var did = $("#courseList").find("option[value="+value+"]").attr('did');
	$(obj).parents().find("#edit_courseId").val(did);
}
//插入一条学生数据
function addVipStudent(){
	var name =  $.trim($("#edit_name").val());
	var phone = $("#edit_phone").val();
	var age = $("#edit_age").val();
	var qq = $("#edit_qq").val();
	var qqNick = $("#edit_qqNick").val();
	var eduBack = $("#edu_back").val();
	var subjectId = $("#edit_subjectId").find("option:selected").val();
	var subjectName = $.trim($("#edit_subjectId").find("option:selected").text());
	var courseId = $("#courseList").find("option").attr('did');
	var courseName = $.trim($("#edit_courseName").val());
	var teacherId = $("#edit_teacherId").val();
	var tutorId = $("#edit_tutorId").val();
	var notes = $("#edit_notes").val();
	var currCompany = $("#edit_currCompany").val();
	var currPosition = $("#edit_currPosition").val();
	var workingYear = $("#edit_workingYear").val();
	var salary = $("#edit_salary").val();
	var shouldPay = $("#edit_shouldPay").val();
	var actualPay = $("#edit_actualPay").val();
	var owePay = $("#edit_owePay").val();
	var payerName = $("#edit_payerName").val();
	var payType = $("#edit_payType").val();
	var payAccount = $("#edit_payAccount").val();
	var receiveAccount = $("#edit_receiveAccount").val();
	var serialNo = $("#edit_serialNo").val();
	var isCount = "未分配业绩";
	var joinTime = $("#edit_joinTime").val();
	if(joinTime.length<2){
		joinTime= createDate;	}
	var paramDatas = {
			name:name,
			age:age,
			phone:phone,
			qq:qq,
			qqNick:qqNick,
			eduBack:eduBack,
			subjectId:subjectId,
			subjectName:subjectName,
			courseId:courseId,
			courseName:courseName,
			teacherId:teacherId,
			tutorId:tutorId,
			notes:notes,
			shouldPay:shouldPay,
			actualPay:actualPay,
			owePay:owePay,
			payerName:payerName,
			payType:payType,
			payAccount:payAccount,
			receiveAccount:receiveAccount,
			serialNo:serialNo,
			currStatus:currStatus,
			joinTime:joinTime,
			isCount:isCount,
			currPosition:currPosition
				};
	$.ajax({
		   type: "post",
		   url: "<m:url value='/vipStudent/addVipStudent.do'/>",
		   data: paramDatas,
		   cache: false,
		   dataType:"json",
		   success:function(response){
			 		//增加订单		
			   		addOrderInfo(response.id);
			   		//增加财务信息
			  		addAccountFlow(response.id);
			  		showMessage("操作成功","","",function(){
						window.parent.closeAdd();
			     		window.parent.doSearch();
					});
		   }
		});
}
//增加财务流水信息
function addAccountFlow(stuId){		
	var money=$("#edit_actualPay").val();
	var stuId = stuId;
	var accountId = $("#accountId").val();
	var accountNo = $("#accountNo").val();
	var accountType = $("#accountType").val();
	var accountName = $("#accountName").val();
	var note = "-"+accountName;
	var paramDatas = {
					money:money,
					accountId:accountId,
					accountName:accountName,
					accountType:accountType,
					accountNo:accountNo,
					createDate:$("#edit_joinTime").val(),
					note:note,
					stuId:stuId
					};
	$.ajax({
		   type: "post",
		   url: "<m:url value='/accountFlow/addAccountFlow.do'/>",
		   data: paramDatas,
		   cache: false,
		   dataType:"json",
		   success:function(response){
		   }
		});
}
//增加订单信息
function  addOrderInfo(stuid){
	var orderType=1;//类型1：学费收入
	var stuId = stuid;
	var stuName = $.trim($("#edit_name").val());
	var shouldPay=$("#edit_shouldPay").val();
	var actualPay = $("#edit_actualPay").val();
	var subjectName = $.trim($("#edit_subjectId").find("option:selected").text());
	var courseName = $.trim($("#edit_courseName").val());
	var paramDatas = {
			orderType:orderType,
			stuId:stuId,
			stuName:stuName,
			shouldPay:shouldPay,
			actualPay:actualPay,
			subjectName:subjectName,
			courseName:courseName,
			createTime:$("#edit_joinTime").val(),
					};
	$.ajax({
		   type: "post",
		   url: "<m:url value='/orderInfo/addOrderInfo.do'/>",
		   data: paramDatas,
		   cache: false,
		   dataType:"json",
		   success:function(response){
		   }
		});
}
</script>
</head>

<body>

	<div id="editDialog">
		<form id="vipStudentFormEdit">
			<div class="ui-table ui-widget ui-corner-all ui-border">
				<input type="hidden" id="edit_id" name="id" type="text"
					value="${vipStudent.id}" />
				<table class="table" id="vipStuTable">
					<tr style="text-indent: 4em">
						<td colspan="4"><i class="icon_bg icon_plan"></i> 学生基本信息</td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>学员姓名：</td>
						<td class="inputTd"><input id="edit_name" name="name"
							type="text" class="text" onblur="drawPayName(this.value)" /></td>
						<td class="inputLabelTd"><span class="required">*</span>手机号码：</td>
						<td class="inputTd"><input id="edit_phone" name="phone"
							type="text" class="text" value="${vipStudent.phone}" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>QQ：</td>
						<td class="inputTd"><input id="edit_qq" name="qq" type="text"
							class="text" value="${vipStudent.qq}" /></td>
						<td class="inputLabelTd"><span class="required">*</span>年龄：</td>
						<td class="inputTd"><input id="edit_age" name="age"
							type="text" class="text" value="${vipStudent.age}" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span> QQ昵称：</td>
						<td class="inputTd"><input id="edit_qqNick" name="qqNick"
							type="text" class="text" value="${vipStudent.qqNick}" /></td>
						<td class="inputLabelTd"><span class="required">*</span> 学号：</td>
						<td class="inputTd"><input id="edu_back" name="eduBack" type="text"
							class="text" value="${vipStudent.eduBack}" /></td>
					</tr>

					<tr>
						<td class="inputLabelTd"><span class="required">*</span>报名学科：</td>
						<td class="inputTd"><select
							onchange="courseChange(this.value);" class="input_select"
							name="subjectId" id="edit_subjectId" mainid="subjectId">
								<c:forEach var="mr" items="${er.subject}">
									<option value="${mr.id}">
										<c:out value="${mr.name}"></c:out>
									</option>
								</c:forEach>
						</select></td>
						<td class="inputLabelTd"><span class="required">*</span>报名课程：</td>
						<td class="inputTd"><input type="hidden" name="courseId"
							id="edit_courseId" value="${vipStudent.courseId}" /> <input
							class="text" name="courseName" id="edit_courseName"
							list="courseList" value="${vipStudent.courseName}"
							onchange="getIdByName(this,this.value)" /> <datalist
								id="courseList">
							</datalist></td>
					</tr>
					<tr>
						<td class="inputLabelTd">任课讲师：</td>
						<td class="inputTd"><input class="text" name="teacherId"
							id="edit_teacherId" list="teacherList"> <datalist
								id="teacherList">
								<c:forEach var="teacher" items="${teacher}">
									<option value="${teacher.nickName}" did="${teacher.id}"
										label="${teacher.name}"></option>
								</c:forEach>
							</datalist></td>
						<td class="inputLabelTd">班主任：</td>
						<td class="inputTd"><input class="text" name="tutorId"
							id="edit_tutorId" list="tutorList"> <datalist
								id="tutorList">
								<c:forEach var="tutor" items="${tutor}">
									<option value="${tutor.nickName}" did="${tutor.id}"
										label="${tutor.name}"></option>
								</c:forEach>
							</datalist></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>在职状态：</td>
						<td class="inputTd">
							<input class="text" name="currPosition" id="edit_currPosition" list="currPositionList"> 
							<datalist id="currPositionList">
								<option value="在读学生" label="在读学生"></option>
								<option value="待业" label="待业"></option>
								<option value="IT在职" label="IT在职"></option>
								<option value="转行" label="转行"></option>
							</datalist>
						</td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>报名时间：</td>
						<td class="inputTd">
							<div class="time_bg">
								<input type="text" class="search_time150 valid" name="joinTime"
									id="edit_joinTime">
								<!-- 时间选择控件-->
								<i class="search_time_ico1"
									onclick="WdatePicker({el:'edit_joinTime'})"></i>
							</div>
						</td>
						<td class="inputLabelTd">备注：</td>
						<td class="inputTd"><input id="edit_notes" name="notes"
							type="text" class="text" value="${vipStudent.notes}" /></td>
					</tr>
					<tr style="text-indent: 4em">
						<td colspan="4"><i class="icon_bg icon_plan"></i> 学生付款信息</td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>应缴学费：</td>
						<td class="inputTd"><input id="edit_shouldPay"
							onblur="countOwePay()" name="shouldPay" type="text" class="text" />
						</td>
						<td class="inputLabelTd"><span class="required">*</span>实缴学费：</td>
						<td class="inputTd"><input id="edit_actualPay"
							onblur="countOwePay()" name="actualPay" type="text" class="text" />
						</td>
					</tr>
					<tr>
						<td class="inputLabelTd">欠缴学费：</td>
						<td class="inputTd"><input id="edit_owePay" name="owePay"
							type="text" class="text" readonly /></td>
						<td class="inputLabelTd"><span class="required">*</span>付款方姓名：</td>
						<td class="inputTd"><input id="edit_payerName"
							name="payerName" type="text" class="text"
							value="${vipStudent.payerName}" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd">付款方式：</td>
						<td class="inputTd"><input class="text" name="payType"
							id="edit_payType" list="payTypeList"> <dataList
								id="payTypeList">
								<option value="支付宝">支付宝</option>
								<option value="腾讯课堂">腾讯课堂</option>
								<option value="京东白条">京东白条</option>
								<option value="贷款代付">贷款代付</option>
							</dataList></td>
						<td class="inputLabelTd">付款账号：</td>
						<td class="inputTd"><input id="edit_payAccount"
							name="payAccount" type="text" class="text"
							value="${vipStudent.payAccount}" /></td>
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
			</div>
		</form>
	</div>
</body>
</html>
