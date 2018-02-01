<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
</head>

<body>
	<form id="vipStudentFormEdit">
		<div class="ui-table ui-widget ui-corner-all ui-border">
			<input type="hidden" id="edit_id" name="id" type="text"
				value="${vipStudent.id}" />
			<table class="table">
				<tr style="text-indent: 4em">
					<td colspan="4"><i class="icon_bg icon_plan"></i> 学生基本信息</td>
				</tr>
				<tr>
					<td class="inputLabelTd"><span class="required">*</span>学员姓名：</td>
					<td class="inputTd"><input id="edit_name" name="name"
						type="text" class="text" value="${vipStudent.name}" /></td>
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
					<td class="inputLabelTd"><span class="required">*</span>QQ昵称：</td>
					<td class="inputTd"><input id="edit_qqNick" name="qqNick"
						type="text" class="text" value="${vipStudent.qqNick}" /></td>
					<td class="inputLabelTd"><span class="required">*</span>学号：</td>
						<td class="inputTd"><input id="edu_back" name="eduBack" type="text"
							class="text" value="${vipStudent.eduBack}" /></td>
				</tr>
				<tr>
					<td class="inputLabelTd"><span class="required">*</span>报名学科：</td>
					<td class="inputTd"><select
						onchange="$('#edit_courseName').val('');courseChange(this.value);"
						style="float: left" class="input_select" name="subjectId"
						id="edit_subjectId">
							<option value="">--请选择--</option>
							<c:forEach var="mr" items="${er.subject}">
								<option value="${mr.id}"
									<c:if test="${vipStudent.subjectId==mr.id}">selected</c:if>>
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
					<td class="inputTd"><input id="edit_teacherId"
						name="teacherId" type="text" class="text" list="teacherList"
						value="${vipStudent.teacherId}" /> <datalist id="teacherList">
							<c:forEach var="teacher" items="${teacher}">
								<option value="${teacher.nickName}" did="${teacher.id}"
									label="${teacher.name}"></option>
							</c:forEach>
						</datalist></td>
					<td class="inputLabelTd">班主任：</td>
					<td class="inputTd"><input id="edit_tutorId" name="tutorId"
						type="text" class="text" list="tutorList"
						value="${vipStudent.tutorId}" /> <datalist id="tutorList">
							<c:forEach var="tutor" items="${tutor}">
								<option value="${tutor.nickName}" did="${tutor.id}"
									label="${tutor.name}"></option>
							</c:forEach>
						</datalist></td>
				</tr>
				<tr>
					<td class="inputLabelTd">备注：</td>
					<td class="inputTd"><input id="edit_notes" name="notes"
						type="text" class="text" value="${vipStudent.notes}" /></td>
					<td class="inputLabelTd"><span class="required">*</span>报名时间：</td>
					<td class="inputTd">
						<div class="time_bg">
							<input type="text" class="search_time150 valid" name="joinTime"
								id="edit_joinTime" value="${vipStudent.joinTime}">
							<!-- 时间选择控件-->
							<i class="search_time_ico2"></i>
						</div>
					</td>
				</tr>
				<tr style="text-indent: 4em">
					<td colspan="4"><i class="icon_bg icon_plan"></i> 学生付款信息</td>
				</tr>
				<tr>
					<td class="inputLabelTd">应缴学费：</td>
					<td class="inputTd"><input id="edit_oldshouldPay"
						type="hidden" value="${vipStudent.shouldPay}" /> <input
						id="edit_shouldPay" name="shouldPay" onblur="countOwePay()"
						type="number" class="text" value="${vipStudent.shouldPay}" /></td>
					<td class="inputLabelTd">实缴学费：</td>
					<td class="inputTd"><input id="edit_actualPay"
						onblur="countOwePay()" name="actualPay" type="number" class="text"
						value="${vipStudent.actualPay}" readonly /></td>
				</tr>
				<tr>
					<td class="inputLabelTd">欠缴学费：</td>
					<td class="inputTd"><input id="edit_owePay" name="owePay"
						type="text" class="text" value="${vipStudent.owePay}"
						readonly="readonly" /></td>
					<td class="inputLabelTd">付款方姓名：</td>
					<td class="inputTd"><input id="edit_payerName"
						name="payerName" type="text" class="text"
						value="${vipStudent.payerName}" /></td>
				</tr>
				<tr>
					<td class="inputLabelTd">付款账号：</td>
					<td class="inputTd"><input id="edit_payAccount"
						name="payAccount" type="text" class="text"
						value="${vipStudent.payAccount}" /></td>
					<td class="inputLabelTd">付款方式：</td>
					<td class="inputTd"><select class="input_select"
						name="payType" id="edit_payType"><option value="">--请选择--</option>
							<option value="支付宝"
								<c:if test="${vipStudent.payType=='支付宝'}">selected</c:if>>支付宝</option>
							<option value="腾讯课堂"
								<c:if test="${vipStudent.payType=='腾讯课堂'}">selected</c:if>>腾讯课堂</option>
							<option value="京东白条"
								<c:if test="${vipStudent.payType=='京东白条'}">selected</c:if>>京东白条</option>
							<option value="贷款代付"
								<c:if test="${vipStudent.payType=='贷款代付'}">selected</c:if>>贷款代付</option>
					</select></td>
				</tr>
				<tr>
					<td class="inputTd" colspan="4" style="text-align: center;"><input
						id="submit" type="button" class="ti_bottom"
						value="<m:message code='button.save' />" /></td>
				</tr>
			</table>
			<input type="hidden" id="isCount" name="isCount"
				value="${vipStudent.isCount}" /> <input type="hidden" id="xftzNo"
				name="xftzNo" value="${xftz.accountNo}" /> <input type="hidden"
				id="xftzId" value="${xftz.id}" /> <input type="hidden"
				id="xftzType" value="${xftz.accountType}" /> <input type="hidden"
				id="xftzName" value="${xftz.accountName}" /> <input type="hidden"
				id="xftjNo" name="xftjNo" value="${xftj.accountNo}" /> <input
				type="hidden" id="xftjId" value="${xftj.id}" /> <input
				type="hidden" id="xftjType" value="${xftj.accountType}" /> <input
				type="hidden" id="xftjName" value="${xftj.accountName}" />
		</div>
	</form>
	<script type="text/javascript">
 var createDate = new Date().format('yyyy-MM-dd hh:mm:ss');//获取今日时间
 var i = 0;
 var currStatus,oldShouldPay,newShouldPay,oldCourseName = "";
 
$(function() {
	 courseChange($("#edit_subjectId").val());
	 oldShouldPay = $('#edit_oldshouldPay').val();
	 newShouldPay = 0;
	 oldCourseName = $('#edit_courseName').val();
	
	//绑定提交按钮click事件
	$("#submit").click(function() {
		if(!biz.validate("valid",$('#vipStudentFormEdit')[0])){
			showWarn("数据验证失败",3000);
			return;
		}
		newShouldPay = $("#edit_shouldPay").val();
		//如果应收金额产生变动，这个月新增一条变动金额财务流水信息
		if(oldShouldPay!=newShouldPay){
// 			addAccountFlow();
			addOrderInfo(Number(newShouldPay)-Number(oldShouldPay));
		}
		updateVipStudent();
	});
	
	new biz.datepicker({
		id : "#edit_joinTime",
		maxDate:'#F{$dp.$D(\'edit_joinTime\',{d:0});}',
		dateFmt:'yyyy-MM-dd HH:mm:ss'
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
//自动计算欠款金额
function countOwePay(){
		newShouldPay = $("#edit_shouldPay").val();
		var actualPay=Number($("#edit_actualPay").val());
		var shouldPay=Number($("#edit_shouldPay").val());
		if(actualPay>shouldPay){
			showMessage("实缴学费不能大于应缴学费");
			$("#edit_actualPay").val(0);
			$("#edit_shouldPay").val(0);
			$('#edit_owePay').val(0);
		}else{
			$('#edit_owePay').val((parseFloat(shouldPay)-parseFloat(actualPay)).toFixed(2));
			if($('#edit_owePay').val()>0){
				currStatus = "分期付款";
			}else{
				currStatus = "已付全款";
			}
		}
}
//报名课程随报名学科联动
function courseChange(val){
	if(val){
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
//根据课程名称获取课程Id
function getIdByName(obj,value) {
	var did = $("#courseList").find("option[value="+value+"]").attr('did');
	$(obj).parents().find("#edit_courseId").val(did);
}
//更新学生数据
function updateVipStudent(){
	var name =  $("#edit_name").val();
	var phone = $("#edit_phone").val();
	var age = $("#edit_age").val();
	var qq = $("#edit_qq").val();
	var qqNick = $("#edit_qqNick").val();
	var eduBack = $("#edu_back").val();
	var subjectId = $("#edit_subjectId").find("option:selected").val();
	var subjectName = $.trim($("#edit_subjectId").find("option:selected").text());
	var courseId = $("#edit_courseId").val();
	var courseName = $.trim($("#edit_courseName").val());
	var teacherId = $("#edit_teacherId").val();
	var tutorId = $("#edit_tutorId").val();
	var notes = $("#edit_notes").val();
	var shouldPay = $("#edit_shouldPay").val();
	var actualPay = $("#edit_actualPay").val();
	var owePay = $("#edit_owePay").val();
	var isCount = $("#isCount").val();
	var paramDatas = {
			id:$("#edit_id").val(),
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
			currStatus:currStatus,
			isCount:isCount
				};
	$.ajax({
		   type: "post",
		   url: "<m:url value='/vipStudent/updateVipStudent.do'/>",
		   data: paramDatas,
		   cache: false,
		   dataType:"json",
		   success:function(d){
				if(d.status){
					showMessage(d.message,"","",function(){
						window.parent.closeEdit();
			     		window.parent.doSearch();
					});
				}else{
					showMessage(d.message);
				}
		   }
		});
}

//增加订单信息
function  addOrderInfo(changeMoney){
	var orderType=4;//类型4：学费调整
	var stuId = $("#edit_id").val();
	var stuName = $("#edit_name").val();
	var shouldPay = changeMoney;
	var subjectName = $.trim($("#edit_subjectId").find("option:selected").text());
	var courseName = $.trim($("#edit_courseName").val());
	var paramDatas = {
			orderType:orderType,
			stuId:stuId,
			stuName:stuName,
			shouldPay:shouldPay,
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
		   }
		});
}
</script>
</body>
</html>
