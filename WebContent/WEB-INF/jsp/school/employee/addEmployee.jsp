<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<style>
textarea.text {
	resize: none;
	font-size: 13px;
	color: gray;
	line-height: 14.5px;
	border: none;
	height: 45px;
	width: 98.6%;
}
</style>
</head>
<body>
	<div id="editDialog">
		<form id="employeeFormEdit" autocomplete="off">
			<div class="ui-table ui-widget ui-corner-all ui-border">
				<input type="hidden" id="edit_id" name="id" type="text"
					value="${employee.id}" />
				<table class="table">
					<!-- 员工基本信息 -->
					<tr style="text-indent: 4em">
						<td colspan="4"><i class="icon_bg icon_plan"></i> 员工基本信息</td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>姓名：</td>
						<td class="inputTd"><input id="edit_name" name="name"
							type="text" class="text" value="${employee.name}"
							style="width: 72px" placeholder="真实姓名" title="员工真实姓名" />- <input
							id="edit_nickName" name="nickName" type="text" class="text"
							value="${employee.nickName}" style="width: 72px"
							placeholder="员工昵称" title="员工昵称" /></td>
						<td class="inputLabelTd"><span class="required">*</span>性别：</td>
						<td class="inputTd"><select style="float: none"
							class="input_select" name="sex" id="edit_sex" mainid="sex">
								<option value="男">男</option>
								<option value="女">女</option>
						</select></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>身份证号码：</td>
						<td class="inputTd"><input id="edit_idCard" name="idCard"
							type="text" class="text" value="${employee.idCard}"
							onblur="fillBirthDate(this.value)" /></td>
						<td class="inputLabelTd"><span class="required">*</span>出生日期：</td>
						<td class="inputTd">
							<div class="time_bg">
								<input type="text" class="search_time150 valid" name="birthDate"
									id="edit_birthDate" mainid="birthDate">
								<!-- 时间选择控件-->
								<i class="search_time_ico2"></i>
							</div>
						</td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>籍贯：</td>
						<td class="inputTd"><select style="float: none"
							onchange="subAreaChange(this);" class="small_input_select"
							name="nativePlace" id="edit_nativePlace" mainid="nativePlace">
								<option value="">--请选择--</option>
								<c:forEach var="ca" items="${area.parent}">
									<option did="${ca.id}" value="${ca.name}">
										<c:out value="${ca.name}"></c:out>
									</option>
								</c:forEach>
						</select> <select style="float: none" class="small_input_select"
							name="nativePlace" id="edit_subnativePlace"
							mainid="subnativePlace">
								<option value="">--请选择--</option>
								<c:forEach var="ca" items="${area.child}">
									<option value="${ca.name}">
										<c:out value="${ca.name}"></c:out>
									</option>
								</c:forEach>
						</select></td>
						<td class="inputLabelTd"><span class="required">*</span>户籍性质：</td>
						<td class="inputTd"><select style="float: none"
							class="input_select" name="nodeType" id="edit_nodeType">
								<option value="农村户口">农村户口</option>
								<option value="城市户口">城市户口</option>
						</select></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>民族：</td>
						<td class="inputTd"><select style="float: none"
							class="input_select" name="nation" id="edit_nation"
							mainid="nation">
								<c:forEach var="nation" items="${nation}">
									<option value="${nation.nationName}">
										<c:out value="${nation.nationName}"></c:out>
									</option>
								</c:forEach>
						</select></td>
						<td class="inputLabelTd">婚姻状况：</td>
						<td class="inputTd"><select style="float: none"
							class="input_select" name="maritalStatus" id="edit_maritalStatus"
							mainid="maritalStatus">
								<option value="未婚">未婚</option>
								<option value="已婚">已婚</option>
								<option value="离婚">离婚</option>
								<option value="丧偶">丧偶</option>
						</select></td>
					</tr>
					<tr>
						<td class="inputLabelTd">最高学历：</td>
						<td class="inputTd"><select id="edit_educationBackground"
							name="educationBackground" class="input_select">
								<option value="本科">本科</option>
								<option value="小学">小学</option>
								<option value="初中">初中</option>
								<option value="高中（职高、高）">高中（职高、高）</option>
								<option value="中专大专（高职）">中专大专（高职）</option>
								<option value="硕士研究生">硕士研究生</option>
								<option value="博士研究生">博士研究生</option>
						</select></td>
						<td class="inputLabelTd">所学专业：</td>
						<td class="inputTd"><input id="edit_profession"
							name="profession" type="text" class="text"
							value="${employee.profession}" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd">外语水平：</td>
						<td class="inputTd"><input id="edit_foreignLanguage"
							name="foreignLanguage" type="text" class="text"
							value="${employee.foreignLanguage}" /></td>
						<td class="inputLabelTd">职业资格：</td>
						<td class="inputTd"><input
							id="edit_professionalQualification"
							name="professionalQualification" type="text" class="text"
							value="${employee.professionalQualification}" /></td>
					</tr>
					<!-- 员工联络信息 -->
					<tr style="text-indent: 4em">
						<td colspan="4"><i class="icon_bg icon_plan"></i> 员工联络信息</td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>手机号码：</td>
						<td class="inputTd"><input id="edit_mobile" name="mobile"
							type="text" class="text" value="${employee.mobile}" /></td>
						<td class="inputLabelTd"><span class="required">*</span>邮箱地址：</td>
						<td class="inputTd"><input id="edit_foxmail" name="foxmail"
							type="email" class="text" value="${employee.foxmail}" /></td>

					</tr>
					<tr>
						<td class="inputLabelTd">通讯地址：</td>
						<td class="inputTd"><input id="edit_mailAddress"
							name="mailAddress" type="text" class="text" /></td>
						<td class="inputLabelTd">邮政编码：</td>
						<td class="inputTd"><input id="edit_postcode" name="postcode"
							type="text" class="text" value="${employee.postcode}" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd">目前住址：</td>
						<td class="inputTd"><select style="float: none"
							onchange="subCurrentAreaChange(this);" class="small_input_select"
							name="currentAddress" id="edit_currentAddress"
							mainid="currentAddress">
								<option value="">--请选择--</option>
								<c:forEach var="ca" items="${area.parent}">
									<option did="${ca.id}" value="${ca.name}"><c:out
											value="${ca.name}"></c:out></option>
								</c:forEach>
						</select> <select style="float: none" class="small_input_select"
							name="currentAddress" id="edit_subcurrentAddress"
							mainid="subcurrentAddress">
								<option value="">--请选择--</option>
								<c:forEach var="ca" items="${area.child}">
									<option value="${ca.name}">
										<c:out value="${ca.name}"></c:out>
									</option>
								</c:forEach>
						</select></td>
						<td class="inputLabelTd">紧急联络人：</td>
						<td class="inputTd"><input id="edit_emergencyContact"
							name="emergencyContact" type="text" class="text"
							value="${employee.emergencyContact}" /></td>

					</tr>
					<!-- 员工入职信息 -->
					<tr style="text-indent: 4em">
						<td colspan="4"><i class="icon_bg icon_plan"></i> 员工入职信息</td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>员工状态：</td>
						<td class="inputTd"><select style="float: none"
							class="input_select" name="currState" id="edit_currState"
							onchange="getCurrState(this)">
								<option value="1">在职</option>
								<option value="4">兼职</option>
								<option value="2">试用</option>
								<option value="3">离职</option>
						</select></td>
						<td class="inputLabelTd"><span class="required">*</span>职工编号：</td>
						<td class="inputTd"><input id="edit_empNo" name="empNo"
							type="text" class="text" value="QM-${nowEmpNo.empNo+1}" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>所属部门：</td>
						<td class="inputTd"><select style="float: none"
							class="input_select" name="deptNo" id="edit_deptNo">
								<c:forEach var="org" items="${org}">
									<option value="${org.orgNo}"><c:out
											value="${org.orgName}"></c:out></option>
								</c:forEach>
						</select></td>
						<td class="inputLabelTd"><span class="required">*</span>所在岗位：</td>
						<td class="inputTd"><input id="position" name="position"
							type="text" class="text" value="${userInfo.dutyName}" /> <input
							id="dutyId" name="dutyId" type="hidden" class="text"
							value="${userInfo.dutyId}" /></td>
					</tr>

					<tr>
						<td class="inputLabelTd">岗位级别：</td>
						<td class="inputTd"><select name="dutyRank"
							id="edit_dutyRank" class="input_select">
						</select></td>
						<td class="inputLabelTd">薪级工资：</td>
						<td class="inputTd"><select id="edit_salary" name="salary"
							class="select">
						</select></td>
					</tr>
					<tr>
						<td class="inputLabelTd">基本工资：</td>
						<td class="inputTd"><input id="edit_salValue" name="salValue"
							type="number" class="text" max="0" value="3000" /></td>
						<td class="inputLabelTd">股份配额(股)：</td>
						<td class="inputTd"><input id="edit_stockNum" name="stockNum"
							type="number" class="text" max="0" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>入职日期：</td>
						<td class="inputTd">
							<div class="time_bg">
								<input type="text" class="search_time150 valid" name="entryDate"
									id="edit_entryDate">
								<!-- 时间选择控件-->
								<i class="search_time_ico2"></i>
							</div>
						</td>
						<td class="inputLabelTd"><span class="required">*</span>转正日期：</td>
						<td class="inputTd">
							<div class="time_bg">
								<input id="edit_beFullDate" name="beFullDate" type="text"
									class="search_time150 valid" value="${employee.beFullDate}" />
								<i class="search_time_ico2"></i>
							</div>
						</td>
					</tr>
					<tr>
						<td class="inputLabelTd">备注：</td>
						<td class="inputTd" colspan="3"><textarea id="edit_note"
								name="note" class="text" rows="" cols=""></textarea></td>
					</tr>
					<tr style="text-indent: 4em">
						<td colspan="4"><i class="icon_bg icon_plan"></i> 员工其他信息</td>
					</tr>
					<tr class="addEnable">
						<td class="inputLabelTd" title="工资发放账号">支付宝/微信：</td>
						<td class="inputTd" title="工资发放银行"><select id="edit_bankName"
							name="bankName" class="input_select">
								<option value="支付宝">支付宝</option>
								<option value="微信">微信</option>
						</select></td>
						<td class="inputLabelTd" title="工资发放卡号">账号：</td>
						<td class="inputTd" title="工资发放卡号"><input id="edit_bankCard"
							name="bankCard" type="text" class="text"
							value="${employee.bankCard}" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd">家庭成员信息：</td>
						<td class="inputTd" colspan="3" title="家庭成员姓名+关系+联系方式"><textarea
								id="edit_familyMember" name="familyMember" class="text"></textarea>
						</td>
					</tr>
					<tr>
						<td class="inputLabelTd">教育经历：</td>
						<td class="inputTd" colspan="3" title="时间+详细信息"><textarea
								id="edit_educationExperience" name="educationExperience"
								class="text"></textarea></td>
					</tr>

					<tr>
						<td class="inputLabelTd">工作经历：</td>
						<td class="inputTd" colspan="3" title="时间+详细信息"><textarea
								id="edit_workExperience" name="workExperience" class="text"
								rows="" cols=""></textarea></td>
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
var createDate = new Date().format('yyyy-MM-dd');//获取今日时间
$(function() {
	//岗位选择
	dutyBox = $('#position').TiledCombobox({
		url : "<m:url value='/duty/list.do'/>",
		fieldId : 'id',
		fieldName : 'dutyName',
		valueId : '#dutyId',
		multiple : false,
		onChangeFn : function(){//岗位级别联动
			$.ajax({
				type: "GET",	
				url : "<m:url value='/duty/listDutyLevel.do'/>?id="+$("#dutyId").val(),
				cache : false,
				async : false,
				dataType:"json",
				success : function(data, textStatus, jqXHR) {
					if(data&&data.records>0){
						$('#edit_dutyRank option').remove();
						$('#edit_salary option').remove();
						for(var i =0;i<data.rows.length;i++){
							if(data.rows[i]){
								$('#edit_dutyRank').append('<option value="'+data.rows[i].id+'">'+data.rows[i].levelType+data.rows[i].levelName+'</option>');
								$('#edit_salary').append('<option value="'+data.rows[i].levelSal+'">'+data.rows[i].levelSal+'</option>');
							}
						}
					}else{
						$('#edit_dutyRank').html('<option value="">无</option>');
						$('#edit_salary').html('<option value="0">/</option>');
					}
				}
			});
		},
	});	
	$("#edit_entryDate").val(createDate);
	$("#edit_beFullDate").val(createDate);
	
	
	//绑定提交按钮click事件
	$("#submit").click(function() {
		$("#submit").prop('disabled', true).css({'cursor':'not-allowed'});
		if(!biz.validate("valid",$('#employeeFormEdit')[0])){
			showWarn("数据验证失败",3000);
			$("#submit").prop('disabled', false).css({'cursor':'pointer'});
			return;
		}
		var empNumber = $('#edit_empNo').val();
		ajaxGetEmpInfo(empNumber);
		
	});
	
	/*编辑表单数据验证*/
	new biz.validate({
		id:"#employeeFormEdit",
		rules:{
			"empNo":{required : true},
			"name" : {required : true,
				maxlength : 10
			},
			"sex" : {
				required : true
			},
			"age" : {
				required : true,
				maxlength : 3,
				number:true
			},
			"mobile" : {
				required : true,
				maxlength : 11,
				number:true
			},
			"idCard" : {
				required : true,
				maxlength : 18,
			},
			"nickName" : {
				required : true,
				maxlength : 10
			},
			"entryDate" : {
				required : true,
				maxlength : 150
			}
		}
	}); 
	//格式化入职日期
	new biz.datepicker({
		id : "#edit_entryDate",
		maxDate:'#F{$dp.$D(\'edit_entryDate\',{d:0});}',
		dateFmt:'yyyy-MM-dd'
	});
	//格式化生日
	new biz.datepicker({
		id : "#edit_birthDate",
		maxDate:'#F{$dp.$D(\'edit_birthDate\',{d:0});}',
		dateFmt:'yyyy-MM-dd'
	});
	//格式化转正日期
	new biz.datepicker({
		id : "#edit_beFullDate",
		minDate:'#F{$dp.$D(\'edit_entryDate\',{d:0});}',
		dateFmt:'yyyy-MM-dd',
		value:'%y-%M-%d'
	});

});
//地区二级联动
function subCurrentAreaChange(obj){
	var val = $(obj).find("option:selected").attr("did");
	$ .ajax({
		url: "<m:url value='/employee/getAreaList.do'/>?key="+val,
		cache:false,
		dataType:"json",
		success: function(data, textStatus, jqXHR){
			$('#edit_subcurrentAddress option').remove();
			for(var i in data.ca){
				if(data.ca[i].id){
					$('#edit_subcurrentAddress').append('<option did="'+data.ca[i].id+'" value="'+data.ca[i].name+'">'+data.ca[i].name+' </option>');
				}
			}
			if(!data.ca[0]){
				$('#edit_subcurrentAddress').append('<option value="">--请选择--</option>');
			}
		}
	});
}
//省份联动
function subAreaChange(obj){
	var val = $(obj).find("option:selected").attr("did");
	$ .ajax({
		url: "<m:url value='/employee/getAreaList.do'/>?key="+val,
		cache:false,
		dataType:"json",
		success: function(data, textStatus, jqXHR){
			$('#edit_subnativePlace option').remove();
			for(var i in data.ca){
				if(data.ca[i].id){
					$('#edit_subnativePlace').append('<option did="'+data.ca[i].id+'" value="'+data.ca[i].name+'">'+data.ca[i].name+' </option>');
				}
			}
			if(!data.ca[0]){
				$('#edit_subnativePlace').append('<option value="">--请选择--</option>');
			}
		}
	});
}
//ajax验证工号是否已经存在
function ajaxGetEmpInfo(empNumber) {
	var paramDatas = {
			empNo:empNumber,
			};
		$.ajax({
				type: "post",	
				url : "<m:url value='/employee/ajaxGetEmpInfo.do'/>",
				data: paramDatas,
				cache : false,
				async : false,
				dataType:"json",
				success : function(data, textStatus, jqXHR) {
					if(data){
						showWarn("工号已存在");
					}else{
						addEmp();
					}
				}
			});
}

//增加一条员工记录
function addEmp(){
	var options = {
			url : "<m:url value='/employee/addEmployee.do'/>",
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
				},
				error:function(d){
					showMessage(d.message);
				}
		};
		// 将options传给ajaxForm
		$('#employeeFormEdit').ajaxSubmit(options);
}
//根据身份证信息填充员工生日信息
function fillBirthDate(str){
	$("#edit_birthDate").val(str.substring(6,10)+"-"+str.substring(10,12)+"-"+str.substring(12,14));
}
//获取员工目前状态
function getCurrState(obj){
	var currState = $("#edit_currState").find("option:selected").val();
	switch (currState) {
	case '2':
		$(obj).removeClass("input_select");
		$(obj).addClass("small_input_select");
		if($("#probation")||$("#leaveDate"))
		{$("#probation").remove();$("#leaveDate").remove();}
		$(obj).after("<select  name='probation' id='probation' class='small_input_select' onchange='getBeFullDate(this)'>"
		+"<option value='1'>一个月</option>"
		+"<option value='2'>两个月</option>"
		+"<option value='3'>三个月</option>"
		+"</select>"
		);
		getBeFullDate();
		break;
	case '3':
		$(obj).removeClass("input_select");
		$(obj).addClass("small_input_select");
		if($("#probation")||$("#leaveDate"))
		{$("#probation").remove();$("#leaveDate").remove();}
		$(obj).after("<input name='leaveDate' id='leaveDate' class='text small_input_select' type='date'/>");
		break;
	default:
		$(obj).removeClass("small_input_select");
		$(obj).addClass("input_select");
		if($("#probation")||$("#leaveDate"))
		{$("#probation").remove();$("#leaveDate").remove();}
		break;
	}
}
//通过入职时间和试用时间获取转正时间
function getBeFullDate(obj){
	var currDate = new Date($dp.$('edit_entryDate').value).getTime();
	var fullDate = currDate+(1000*60*60*24*30)*$("#probation").find("option:selected").val();
	$('#edit_beFullDate').val(new Date(fullDate).toLocaleString().split(" ")[0].replace(/\//g,"-"));
}
//克隆：添加多张员工银行卡数据
function addBankMsg(){
	var $addEnable = $(".addEnable:last");
	var $clone = $addEnable.clone(true);
	$clone.find("input").val("");
	$addEnable.after($clone); 
	var index = $clone.index();
	$clone.find(".followerName").attr("id","followerName"+$clone.index());
}

//删除多张员工银行卡数据
function delBankMsg(Object){
	if($(".addEnable").size()>1)
	{$(Object).parents('.addEnable').remove();}
	else return;
};

/*自动搜寻考核人 自动完成(Autocomplete) 根据用户输入值进行搜索和过滤,让用户快速找到并从预设值列表中选择*/
function getEmpIDByName(obj,value) {
	var did = $("#employeeList").find("option[value="+value+"]").attr('did');
	$(obj).parents('.addEnable').find("#edit_checkNameT").val(did);
}

function checkstatechange(value){
	if(value=='2'){
		$("#edit_checkNameF").remove();
		$("#employeeList").remove();
		$("#checknotediv").append('<label class="checknote" id="checknote">无</label>');
	}else{
		$("#checknote").remove();
		$("#checknotediv").append('<input id="edit_checkNameF" name="checkNameF" type="text" class="text" list="employeeList" onchange="getEmpIDByName(this,this.value);" onblur="checkNameValidation()"/><datalist id="employeeList"><c:forEach var="tutor" items="${tutor}"><option did="${tutor.name}" value="${tutor.nickName}" label="${tutor.name}"></option></c:forEach></datalist>');
	}
}

//为考核人input框绑定失去焦点事件
function checkNameValidation(){
	var cname = $("#edit_checkNameF").val();
	var paramDatas = {
			checkName:cname,
			};
		$.ajax({
				type: "post",	
				url : "<m:url value='/employee/checkNameValidation.do'/>",
				data: paramDatas,
				cache : false,
				async : false,
				dataType:"json",
				success : function(data, textStatus, jqXHR) {
					if(data.flag>=1){
						console.log(data.flag);
					}else{
						console.log(data.flag);
						showMessage("请选择正确的考核人！","","",function(){	
							$("#edit_checkNameF")[0].focus();
						});
					}
				}
			});
}
</script>

</body>
</html>
