<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html  >
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<script type="text/javascript" charset="utf-8"
	src="<%=request.getContextPath()%>/js/huploadify/jquery.Huploadify.js"></script>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/js/huploadify/Huploadify.css">
<script type="text/javascript">
	var menutree;
	var areaProvinceBox, areaRegionBox, areaCityBox;
	$(function() {
		//取得添加节点的父节点ID

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
				"fullName" : {
					required : true,
					maxlength : 15
				},
				"age" : {
					required : true,
					maxlength : 2
				},
				"mobilePhone" : {
					required : true,
					maxlength : 11
				},
				"idCard" : {
					maxlength : 18
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
	

</script>
</head>

<body>
	<form id="userInfoFormAdd">
		<div class="ui-table ui-widget ui-corner-all ui-border">
			<table class="table">
				<tr>
					<td class="inputLabelTd"><span class="required">*</span>登录账号：</td>
					<td class="inputTd"><input id="edit_userAccount"
						name="userAccount" type="text" class="text"
						value="${userInfo.userAccount}" /></td>
					<td class="inputLabelTd"><span class="required">*</span>用户姓名：</td>
					<td class="inputTd"><input id="edit_fullName" name="fullName"
						type="text" class="text" value="${userInfo.fullName}" /> <input
						id="edit_orgName" name="orgName" type="hidden" class="text"
						value="服务部门" /> <input id="edit_orgId" name="orgId" type="hidden"
						class="text" value="4" /> <input id="orgClass" name="orgClass"
						type="hidden" class="text" value="1" /> <input id="dutyName"
						name="dutyName" type="hidden" class="text" value="兼职班主任" /> <input
						id="dutyId" name="dutyId" type="hidden" class="text"
						value="b8d29f69-9c38-4417-bd58-cc0e369c42b8" /></td>
				</tr>
				<%-- <tr>
					<td class="inputLabelTd">性别：</td>
					<td class="inputTd"><select id="edit_sex" name="sex"
						class="select">
							<option value="男">男</option>
							<option value="女">女</option>
					</select></td>
					<td class="inputLabelTd"><span class="required">*</span>年龄：</td>
					<td class="inputTd"><input id="age" name="age"
						type="text" class="text" value="${userInfo.age}" /></td>
			    </tr> --%>
				<%-- <tr>
					<td class="inputLabelTd"><span class="required">*</span>手机号码：</td>
					<td class="inputTd"><input id="mobilePhone"
						name="mobilePhone" type="text" class="text"
						value="${userInfo.mobilePhone}" /></td>
					<td class="inputLabelTd"><span class="required">*</span>身份证号码：</td>
					<td class="inputTd"><input id="idCard" name="idCard"
						type="text" class="text" value="${userInfo.idCard}" /></td>
			    </tr> --%>
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
					<td class="inputLabelTd"><span class="required">*</span>系统角色</td>
					<td class="inputTd"><input id="edit_roleName" name="roleName"
						type="text" class="text" value="兼职班主任" readonly /> <input
						id="edit_roleId" name="roleId" type="hidden" class="text"
						value="f6961666-7b81-423a-941b-c85cb7312513" /> <!-- 					<select id="edit_roleId" name="roleId" disabled="disabled"	class="select"> -->
						<!-- 						<option value="">兼职班主任</option> --> <!-- 					</select></td> -->
						<!-- <td class="inputLabelTd">身份证图片：</td>
					<td class="inputTd" >
						<input id="fileData" name="idCardImgPath" type="hidden">
						<input id="file" type="file" class="text"/>
					</td>	 -->
				</tr>


				<tr>
					<td class="inputTd" colspan="5" style="text-align: center;"><input
						id="submit" type="button" class="ti_bottom" value="保存" /> <input
						id="button" type="button" class="ti_bottom" value="重置"
						onclick="resetForm('userInfoFormAdd')" /></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
