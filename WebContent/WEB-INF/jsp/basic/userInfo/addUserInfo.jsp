<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<script type="text/javascript" charset="utf-8"
	src="<%=request.getContextPath()%>/js/huploadify/jquery.Huploadify.js"></script>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/js/huploadify/Huploadify.css">
<script type="text/javascript">
	var menutree,overFlag;
	var areaProvinceBox, areaRegionBox, areaCityBox;
	$(function() {

		//岗位选择
		dutyBox = $('#dutyName').TiledCombobox({
			url : "<m:url value='/duty/list.do'/>",
			fieldId : 'id',
			fieldName : 'dutyName',
			valueId : '#dutyId',
			multiple : false
		});				
		
		//绑定提交按钮click事件
		$("#submit").click(function() {
			
			$('#edit_orgName').val($("#select_orgId").find("option:selected").text());
			
			$('#salStandardName').val($('#salStandardId').find("option:selected").text());
			if (!biz.validate("valid", $('#userInfoFormAdd')[0])) {
				showWarn("<m:message code='validation.object'/>", 3000);
				return;
			}
		 	var userAccount = $("#edit_userAccount").val();
			if (ajaxGetUserInfoByUserAccount(userAccount)) {
				showMessage("登录账户已经存在，请重新输入.");
				return;
			} 
			
			var options = {
					url : "<m:url value='/userInfo/addUserInfo.do'/>",
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
			$('#userInfoFormAdd').ajaxSubmit(options);
			
		});

		/*编辑表单数据验证*/
		new biz.validate({
			id : "#userInfoFormAdd",
			rules : {
				"userAccount" : {
					required : true,
					maxlength : 50
				},
				"memo" : {
					maxlength : 150
				},
				"roleId":{required : true,}
			}
		});

		//入职日期
		new biz.datepicker({
			id : "#edit_entryDate",//容器Id   
			event : "click",
			dateFmt : 'yyyy-MM-dd HH:mm:ss'
		});
		//出生日期
		new biz.datepicker({
			id : "#edit_birthday",//容器Id   
			event : "click",
			dateFmt : 'yyyy-MM-dd HH:mm:ss'
		});

		
		new biz.select(
				{//状态下拉
					id : "#edit_states",
					url : "<m:url value='/dictInfo/getDictByTypeCode.do?dictTypeCode=status'/>"
				});

		new biz.select({//角色
			id : "#edit_role",
			url : "<m:url value='/role/getRoleListForSelect.do'/>",
			addEmptyItem : true
		});
		
		$("#salStandardId").change(function(){
			   var op=$(this).find("option:selected");
			  $('#salValue').val(op.attr("totalValue"));
			  $('#stockNum').val(op.attr("stockNum"));
		});

		$(".allRoleList").mouseover(function(){
			  overFlag=true;
		});
		$(".allRoleList").mouseout(function(){
			  overFlag=false;
		});
	
	});

	function ajaxGetUserInfoByUserAccount(userAccount) {
		var b = false;
		if (userAccount != null && userAccount != "") {
			$.ajax({
						url : "<m:url value='/userInfo/ajaxGetUserInfoByUserAccount.do'/>?userAccount="
								+ userAccount,
						cache : false,
						async : false,
						success : function(data, textStatus, jqXHR) {
							if (data == "true") {
								showMessage("登录账户已经存在，请重新输入.");
								b = true;
							}
						}
					});
		};
		return b;
	}

	//获取菜单资源数据，构建树
	function setMenuTreeJson(rid) {
		$.ajax({
			url : "<c:url value='/role/getMenuTreeJsonByRole.do'/>",
			data : {
				roleRid : rid
			},
			type : "POST",
			success : function(data, textStatus, jqXHR) {
				menutree = new biz.tree({
					id : "#menutree",
					nodes : $.parseJSON(data), //数据节点指定     
					data : {
						simpleData : {
							enable : true
						}
					}
				});//创建树 
			}
		});
	}
	function showRole(obj) {
		setMenuTreeJson(obj.value);
	}
	
	//打开员工资源管理界面
	function employeeMgt() {
		var url = baseUrl + '/userInfo/toSelectEmployee.do';
		employeelist_iframe_dialog = new biz.dialog(
				{
					id : $('<div id="sublist_window_iframe"></div>')
							.html(
									'<iframe id="iframeSublist" name="iframeSublist" src="'
											+ url
											+ '" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
					modal : true,
					width : $(window).width()*0.8,
					height : $(window).height()*0.8,
					title : "员工信息列表"
				});
		employeelist_iframe_dialog.open();
	}

	// 关闭员工资源管理界面
	function closeEmployee() {
		employeelist_iframe_dialog.close();
	}
	
	//填充数据
	function drawForm(rowData) {
		$("#edit_id").val(rowData.id);
		$("#edit_fullName").val(rowData.name);
		$("#dutyName").val(rowData.position);
	}
</script>
</head>

<body>
	<div class="listplace" style="margin: 0px">
		<!--功能按钮begin-->
		<div class="list_btn_bg fl" style="position: absolute;">
			<!--功能按钮 div-->
			<ul>
				<li><a title="从员工信息中选择" href="javascript:;"
					onclick="employeeMgt();"> <i class="back_icon resources_icon"></i>
						<span>从员工信息中选择</span>
				</a></li>
				<li><a title="重置" href="javascript:;"
					onclick="resetForm('userInfoFormAdd')"> <i
						class="icon_bg icon_del"></i><span>重置</span>
				</a></li>
			</ul>
		</div>
		<!--功能按钮end-->
	</div>

	<form id="userInfoFormAdd" style="margin-top: 35px;">
		<div class="ui-table ui-widget ui-corner-all ui-border">
			<table class="table">
				<tr>
					<td class="inputLabelTd"><span class="required">*</span>登录账号：</td>
					<td class="inputTd"><input id="edit_id" name="id"
						type="hidden" /> <input id="edit_userAccount" name="userAccount"
						type="text" class="text" value="${userInfo.userAccount}" /></td>
					<td class="inputLabelTd"><span class="required">*</span>用户姓名：</td>
					<td class="inputTd"><input id="edit_fullName" name="fullName"
						type="text" class="text" value="${userInfo.fullName}" /></td>

				</tr>
				<tr>
					<td class="inputLabelTd"><span class="required">*</span>系统角色</td>
					<td class="inputTd">
						<select class="input_select" name="roleId" id="roleId">
							<c:forEach var="role" items="${roleList}">
								<option  value="${role.roleId}">
									<c:out value="${role.roleName}"></c:out>
								</option>
							</c:forEach>
						</select>
						<input id="edit_orgName" name="orgName" type="hidden" /> 
						<input id="orgId" name="orgId" type="hidden" class="text" value="${basicOrg.id}" />
						<input id="orgClass" name="orgClass" type="hidden" class="text" value="${basicOrg.orgClass}" />
					</td>
					<td class="inputLabelTd"><span class="required">*</span>所属岗位：</td>
					<td class="inputTd"><input id="dutyName" name="dutyName"
						type="text" class="text" value="${userInfo.dutyName}" /> <input
						id="dutyId" name="dutyId" type="hidden" class="text"
						value="${userInfo.dutyId}" /></td>
				</tr>
				<tr>
					<td class="inputLabelTd">备注：</td>
					<td class="inputTd"><input id="edit_memo" name="memo"
						type="text" class="text" value="${userInfo.memo}" /></td>
					<td class="inputLabelTd">状态：</td>
					<td class="inputTd"><select id="edit_states" name="states"
						class="select">
							<option value="1">启用</option>
							<option value="0">停用</option>
					</select></td>
				</tr>
				<tr>
					<td class="inputTd" colspan="5" style="text-align: center;"><input
						id="submit" type="button" class="ti_bottom" value="保存" /></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
