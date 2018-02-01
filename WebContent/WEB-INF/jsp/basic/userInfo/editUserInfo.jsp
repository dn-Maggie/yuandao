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
			if (!biz.validate("valid", $('#userInfoFormEdit')[0])) {
				showWarn("<m:message code='validation.object'/>", 3000);
				return;
			}
			var options = {
				url : "<m:url value='/userInfo/updateUserInfo.do'/>",
				type : "post",
				dataType:"json",
				cache : false,
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
			$('#userInfoFormEdit').ajaxSubmit(options);
		});

		$("#passwordReset").click(function() {
			var key=$('#edit_id').val();
			$.ajax({      
				   url:"<m:url value='/userInfo/passwordReset.do'/>",
				   type:'post',      
				   data:{key:key},
				   cache:false,
				   success:function(data){      
				       if(data!=null&&data!=""){
				    	  showMessage("<m:message code='passwordReset.success'/>");
				       }
				   }   
			});
		});
		/*编辑表单数据验证*/
		new biz.validate({
			id : "#userInfoFormEdit",
			rules : {
				"userAccount" : {
					required : true,
					maxlength : 50
				},
				"memo" : {
					maxlength : 150
				}
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
	<form id="userInfoFormEdit">
		<hi:icssToken />
		<div class="ui-table ui-widget ui-corner-all ui-border">
			<table class="table">
				<tr>
					<td class="inputLabelTd"><span class="required">*</span>登录账号：</td>
					<td class="inputTd"><input id="userId" name="id" type="hidden"
						class="text" value="${userInfo.id}" /> <input
						id="edit_userAccount" name="userAccount" type="text" class="text"
						style="background-color: #eee;" readonly="readonly"
						value="${userInfo.userAccount}" /></td>
					<td class="inputLabelTd"><span class="required">*</span>用户姓名：</td>
					<td class="inputTd"><input id="edit_fullName" name="fullName"
						type="text" class="text" value="${userInfo.fullName}" /></td>
				</tr>
				<tr>
					<td class="inputLabelTd"><span class="required">*</span>组织机构：</td>
					<td class="inputTd"><input id="edit_orgName" name="orgName"
						type="text" class="text" value="${userInfo.orgName}"
						readonly="readonly" /> <input id="edit_orgId" name="orgId"
						type="hidden" class="text" value="${userInfo.orgId}" /> <input
						id="orgClass" name="orgClass" type="hidden" class="text"
						value="${userInfo.orgClass}" /></td>
				</tr>
				<tr>
					<td class="inputLabelTd">备注：</td>
					<td class="inputTd"><input id="edit_memo" name="memo"
						type="text" class="text" value="${userInfo.memo}" /></td>
					<td class="inputLabelTd">状态：</td>
					<td class="inputTd"><select id="edit_states" name="states"
						class="select">
							<option value="1"
								<c:if test="${userInfo.states eq 1}" >selected</c:if>>启用</option>
							<option value="0"
								<c:if test="${userInfo.states eq 0}" >selected</c:if>>停用</option>
					</select></td>
				</tr>
				<tr>
					<td class="inputLabelTd">系统角色</td>
					<td class="inputTd"><select id="roleId" name="roleId"
						class="select">
							<c:forEach var="role" items="${roleList}">
								<option value="${role.roleId}"
									<c:if test="${userInfo.roleId eq role.roleId}" >selected</c:if>>
									${role.roleName}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td class="inputTd" colspan="5" style="text-align: center;"><input
						id="submit" type="button" class="ti_bottom" value="保存" /> <input
						id="passwordReset" type="button" class="ti_bottom" value="密码重置" />
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
