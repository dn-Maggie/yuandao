<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/styles/extends/css/expenseAccount.css" />
</head>
<body>
	<div id="editDialog">
		<form id="leaveApplyFormEdit">
			<div class="wrap">
				<input type="hidden" id="edit_id" name="id" type="text"
					value="${leaveApply.id}" /> <input name="checkFlag" type="hidden"
					value="1" /> <input name="isCost" type="hidden" value="1" />
				<div class="top_head">
					<h2 class="top_name">请 假 条</h2>
					<div class="time_bg"
						style="top: 40px; left: 0px; height: 25px; line-height: 25px; position: absolute; width: 200px">
						<span><span class="required">*</span>请假类型：</span> <select
							name="leaveType" id="edit_leaveType" class="input_select"
							style="width: 100px">
							<option value="1">事假</option>
							<option value="2">公假</option>
						</select>
					</div>
					<div class="time_bg"
						style="top: 40px; right: 0px; height: 25px; line-height: 25px; position: absolute; width: 200px">
						<span>申请日期：</span> <input id="edit_createDate" name="createDate"
							style="height: 25px; width: 100px" class="input_select" readonly />
						<i class="search_time_ico2" style="top: 6px;"></i>
					</div>
				</div>
				<div class="center_body">
					<table class="table">
						<tr>
							<td class="inputLabelTd"><span class="required">*</span>所属部门：</td>
							<td class="inputTd"><input id="edit_deptName"
								name="deptName" type="text" class="text" value="${user.dept}"
								readonly /></td>
							<td class="inputLabelTd"><span class="required">*</span>申请人：</td>
							<td class="inputTd"><input id="edit_enterName"
								name="enterName" type="text" class="text"
								value="${user.nickName}" readonly /> <input id="edit_empId"
								name="empId" type="hidden" value="${user.id}" readonly /></td>
						</tr>

						<tr class="trDetails" style="height: 140px;">
							<td class="inputLabelTd"><span class="required">*</span>请假事由：</td>
							<td class="inputTd" colspan="3"><textarea class="text"
									name="content" id="edit_content"></textarea></td>
						</tr>
						<tr>
							<td class="inputLabelTd"><span class="required">*</span>开始日期：</td>
							<td class="inputTd">
								<div class="time_bg" style="width: 98%;">
									<input type="text" class="search_time150 valid text"
										name="startDate" id="edit_startDate" mainid="startDate"
										style="width: 88%;" onblur="countLeaveDays()">
									<!-- 时间选择控件-->
									<i class="search_time_ico2"></i>
								</div>
							</td>
							<td class="inputLabelTd"><span class="required">*</span>结束日期：</td>
							<td class="inputTd">
								<div class="time_bg" style="width: 98%;">
									<input type="text" class="search_time150 valid text"
										name="endDate" id="edit_endDate" mainid="endDate"
										style="width: 88%;" onblur="countLeaveDays()">
									<!-- 时间选择控件-->
									<i class="search_time_ico2"></i>
								</div>
							</td>
						</tr>
						<tr>
							<td class="inputLabelTd"><span class="required">*</span>请假天数：</td>
							<td class="inputTd"><input id="edit_leaveDate"
								name="leaveDate" type="text" class="text"
								value="${leaveApply.leaveDate}" readonly /></td>
							<td class="inputLabelTd"><span class="required">*</span>直接负责人：
							</td>
							<td class="inputTd"><input id="edit_straightLeaderName"
								name="straightLeaderName" type="text" class="text"
								list="employeeList" onchange="getEmpIDByName(this,this.value);" />
								<datalist id="employeeList">
									<c:forEach var="emp" items="${emp}">
										<option did="${emp.id}" value="${emp.nickName}"
											label="${emp.name}"></option>
									</c:forEach>
								</datalist> <input id="edit_straightLeader" name="straightLeader"
								type="hidden" /></td>
						</tr>
					</table>
					<div class="inputTd" style="display: block; text-align: center;">
						<input id="submit" type="button" class="ti_bottom" value="提交申请" />
					</div>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		var createDate = new Date().format('yyyy-MM-dd hh:mm:ss');//获取今日时间
		$(function() {
			$("#edit_createDate").val(createDate);
			//绑定提交按钮click事件
			$("#submit").click(function() {
				$("#submit").prop('disabled', true).css({'cursor':'not-allowed'});
				showMessage("正在处理，请稍后...");
				if(!biz.validate("valid",$('#leaveApplyFormEdit')[0])){
					showWarn("数据验证失败",3000);
					$("#submit").prop('disabled', false).css({'cursor':'pointer'});
					return;
				}
				var options = {
					url : "<m:url value='/leaveApply/addLeaveApply.do'/>",
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
				$('#leaveApplyFormEdit').ajaxSubmit(options);
			});
		
		
			/*申请日期格式化*/
			new biz.datepicker({
				id : "#edit_startDate",
				minDate:'%y-%M-{%d+1}',
				dateFmt:'yyyy-MM-dd HH:mm:ss'
			});
			/*申请日期格式化*/
			new biz.datepicker({
				id : "#edit_endDate",
				minDate:'#F{$dp.$D(\'edit_startDate\',{d:0});}',
				dateFmt:'yyyy-MM-dd HH:mm:ss'
			});
			
			/*编辑表单数据验证*/
			new biz.validate({
				id:"#leaveApplyFormEdit",
				rules:{
					"deptName" :{required : true},
					"content" :{required : true},
					"startDate" :{required : true},
					"endDate" :{required : true},
				}
			}); 
		});
		
		function countLeaveDays(){
			var start = $("#edit_startDate").val();
			var startDate = start.substring(0,start.indexOf(" "));
			var startTime = start.substring(start.indexOf(" "));
			var end = $("#edit_endDate").val();
			var endDate = end.substring(0,end.indexOf(" "));
			var endTime = end.substring(end.indexOf(" "));
			if(startDate.length>0&&endDate.length>0){
				var sArr = startDate.split("-");
				var eArr = endDate.split("-");
				var stArr = startTime.split(":");
				var etArr = endTime.split(":");
				var sRDate = new Date(sArr[0], sArr[1], sArr[2],stArr[0], stArr[1], stArr[2]);
				var eRDate = new Date(eArr[0], eArr[1], eArr[2],etArr[0], etArr[1], etArr[2]);
				var leaveDate = (eRDate-sRDate)/(24*60*60*1000);
				$("#edit_leaveDate").val(leaveDate.toFixed(2));
				leaveDate>=3?$("#edit_headLeader").val("need"):"";
			}
		}
		/*自动搜寻 自动完成(Autocomplete) 根据用户输入值进行搜索和过滤,让用户快速找到并从预设值列表中选择*/
		function getEmpIDByName(obj,value) {
			var did = $("#employeeList").find("option[value="+value+"]").attr('did');
			$(obj).parents('td').find("#edit_straightLeader").val(did);
		}
		</script>
</body>
</html>
