<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<script type="text/javascript">
$(function() {
	var areaProvinceBox, areaRegionBox, areaCityBox;
	//绑定提交按钮click事件
	$("#submit").click(function() {
		if(!biz.validate("valid",$('#orgFormEdit')[0])){
			showWarn("数据验证失败",3000);
			return;
		}
		var options = {
			url : "<m:url value='/org/addOrg.do'/>",
			type : "post",
			dataType:'json',
			success : function(response) {
				if(response.status){
					showMessage(response.message,"","",function(){
						window.parent.closeAdd();
						List.doSearch(window.parent.gridObj);
					});
				}else{
					showMessage(response.message);
				}
			}
		};
		// 将options传给ajaxForm
		$('#orgFormEdit').ajaxSubmit(options);
	});
	
	/*编辑表单数据验证*/
	new biz.validate({
		id:"#orgFormEdit",
		rules:{
			"orgNo" : {
				required : true,
				maxlength : 50
			},
			"orgName" : {
				required : true,
				maxlength : 50
			},
			"orgFullName" : {
				required : true,
				maxlength : 50
			},
			"pinyin" : {
				required : true,
				maxlength : 50
			},
			"addr" : {
				required : true
			},
			"telephone" : {
				required : true,
				mobile:true
			},
			"email" : {
				email : true
			},
			"corpMan" : {
				required : true
			}
		}
	}); 
});
</script>
</head>

<body>

	<div id="editDialog">
		<form id="orgFormEdit">
			<div class="ui-table ui-widget ui-corner-all ui-border">
				<input type="hidden" id="id" name="id" type="text" value="${org.id}" />
				<table class="table">
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>上级机构：</td>
						<td class="inputTd"><input id="parentOrgName"
							name="parentOrgName" type="text" class="text"
							style="background-color: #eee;" readonly="readonly"
							value="${orgParent.orgName}" /> <input id="parentOrgId"
							name="parentOrgId" type="hidden" class="text"
							value="${orgParent.id}" /></td>
						<td class="inputLabelTd">机构类型</td>
						<td class="inputTd"><select name="orgType" id="orgType"
							class="select">
								<option value="1">公司</option>
								<option value="2">部门</option>
						</select></td>

					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>机构编码：</td>
						<td class="inputTd"><input id="orgNo" name="orgNo"
							type="text" class="text" value="${org.orgNo}" /></td>
						<td class="inputLabelTd"><span class="required">*</span>机构名称：</td>
						<td class="inputTd"><input id="orgName" name="orgName"
							type="text" class="text" value="${org.orgName}" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>机构全称：</td>
						<td class="inputTd"><input id="orgFullName"
							name="orgFullName" type="text" class="text"
							value="${org.orgFullName}" /></td>
						<td class="inputLabelTd"><span class="required">*</span>拼音码：</td>
						<td class="inputTd"><input id="pinyin" name="pinyin"
							type="text" class="text" value="${org.pinyin}" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd">单位描述：</td>
						<td class="inputTd"><input id="orgDesc" name="orgDesc"
							type="text" class="text" value="${org.orgDesc}" /></td>
						<td class="inputLabelTd">单位状态：</td>
						<td class="inputTd"><select name="orgState" id="orgState"
							class="select">
								<option value="1">启用</option>
								<option value="0">停用</option>
						</select></td>
					</tr>
					<tr>
						<td class="inputLabelTd">机构级别：</td>
						<td class="inputTd"><select name="orgClass" id="orgClass"
							class="select">
								<option value="3">分公司级</option>
								<option value="2">省级</option>
								<option value="1">集团级</option>
						</select></td>
						<td class="inputLabelTd">机构性质：</td>
						<td class="inputTd"><select name="orgKind" id="orgKind"
							class="select">
								<option value="2">经营</option>
								<option value="1">管理</option>
								<option value="3">后勤</option>
						</select></td>
					</tr>

					<tr>
						<td class="inputLabelTd"><span class="required">*</span>办公地址：</td>
						<td colspan="3" class="inputTd"><input id="addr" name="addr"
							type="text" class="text" style="width: 85.5%;"
							value="${org.addr}" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>电话：</td>
						<td class="inputTd"><input id="telephone" name="telephone"
							type="text" class="text" value="${org.telephone}" /></td>
						<td class="inputLabelTd">传真：</td>
						<td class="inputTd"><input id="fax" name="fax" type="text"
							class="text" value="${org.fax}" /></td>

					</tr>
					<tr>
						<td class="inputLabelTd">电子邮件地址：</td>
						<td class="inputTd"><input id="email" name="email"
							type="text" class="text" value="${org.email}" /></td>
						<td class="inputLabelTd"><span class="required">*</span>负责人：</td>
						<td class="inputTd"><input id="corpMan" name="corpMan"
							type="text" class="text" value="${org.corpMan}" /></td>

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
