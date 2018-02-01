<%@page import="com.dongnao.workbench.common.util.Utils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<%@ include file="../../common/header.jsp"%>
<script type="text/javascript">

var gridObj = {};
var orgId, orgName;
$(function() {
	initOrgTree();
	gridObj = new biz.grid({
		id : "#remote_rowed",/* html部分table id */
		url : baseUrl + "/userInfo/listUserInfo.do",/* grid初始化请求数据的远程地址 */
		datatype : "json",/* 数据类型，设置为json数据，默认为json */
		sortname : "created",
		sortorder : "asc",
		pager : '#remote_prowed' /* 分页栏id */,
		rowList : [ 10, 20, 30, 50, 100, 200 ],// 每页显示记录数
		rowNum : 10,// 默认显示15条
		shrinkToFit:false,
		colModel : [ {
			name : "userId",
			hidden : true,
			key : true
		}, {width : 100,
			name : "userAccount",
			label : "登录账号",
			index : "USER_ACCOUNT"
		}, {width : 100,
			name : "fullName",
			label : "用户姓名",
			index : "full_Name"
		}, {width : 100,
			name : "orgName",
			label : "部门单位",
			index : "ORG_ID"
		},{width : 100,
			name : "lastLoginIp",
			label : "最后登录IP",
			index : "LAST_LOGIN_IP"
		}, {width : 50,
			name : "states",
			label : "状态",
			formatter:GridColModelForMatter.enableStates
		}, {width : 150,
			name : "created",
			label : "创建时间"
		}, {width : 150,
			name : "lastLoginTime",
			label : "最后登录时间",
			index : "LAST_LOGIN_TIME"
		}],
		serializeGridData : function(postData) {// 添加查询条件值
			var obj = getQueryCondition();
			$.extend(true, obj, postData);// 合并查询条件值与grid的默认传递参数
			return obj;
		}
	});

});

/**
 * 获取查询条件值
 */
function getQueryCondition() {
	var obj = {};
	addAttrToObject(obj, "orgId");
	addAttrToObject(obj, "userAccount");
	addAttrToObject(obj, "fullName");
	addAttrToObject(obj, "orgClass");
	return obj;
}



//选择确定按钮
function doSelect() {
	var rowArr = new Array();
	var selRows = gridObj.getGridParam('selarrrow');
	if (selRows == null || selRows.length == 0) {
		showInfo("请选择数据！", 3000);
		return;
	}
	for ( var i = 0; i < selRows.length; i++) {
		var row = gridObj.getRowData(selRows[i]);
		rowArr.push(row);
	}
	window.parent.selectUserWinFill(rowArr);
}


// 查询Grid数据
function doSearch(isStayCurrentPage) {
	if (!isStayCurrentPage)
		gridObj.setGridParam({
			"page" : "1"
		});
	gridObj.trigger('reloadGrid');
}
// 重置查询表单
function resetForm(formId) {
	document.getElementById(formId).reset();
	$("#"+formId+" input[type='hidden']").val(""); 
	$(".selected").removeClass("selected");//清除选中
	initOrgTree();
	doSearch();
}


function initOrgTree() {
	$.ajax({
		url : baseUrl + "/org/initOrgTree.do",
		data : {
		// regionName : $('#regionName').val(),
		// provinceName : $('#provinceName').val()
		},
		type : "POST",
		dataType:"json",
		success : function(data, textStatus, jqXHR) {
			var setting_checkbox = {
				data : {},
				id : "#orgTree",
				nodes :data, // 数据节点指定
				data : {
					simpleData : {
						enable : true
					}
				},
				callback : {
					onClick : treeOnClick
				}
			};
			provinceTree = new biz.tree(setting_checkbox);// 创建树
		}
	});
}
function treeOnClick(event, treeId, treeNode) {//
// if(treeNode.isParent){
// $("#apid").val(treeNode.id);
	$("#orgId").val(treeNode.id);
	orgId = treeNode.id;
	orgName = treeNode.name;
	doSearch();
	// }else{
	// $("#apid").val(treeNode.pId);
	// $("#aarid").val(treeNode.id);
	// doSearch();
	// }
}

</script>
</head>
<body style="height: 100%;">
	<div class="main  choice_box container"
		style="height: 100%; float: left; width: 26%">
		<div class="ui-table ui-widget ui-corner-all ui-margin ui-leftDiv">
			<form id="queryForm1">
				<div id="equal1" class="equal">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="top_table">
						<tr>
							<td class="rightSide">名称：</td>
							<td><input type="text" name="orgName" id="orgName"
								class="text" style="width: 100px;"
								onkeyup="search_ztree('orgTree','name',this);" /></td>

						</tr>
					</table>
				</div>
			</form>
			<ul id="orgTree" class="ztree"
				style="height: 100%; border-top: 1px solid #ddd; position: relative; overflow: auto; width: 97%"></ul>
		</div>
	</div>
	<div class="main  choice_box">
		<div class="ui-table ui-widget ui-corner-all ui-margin ui-rightDiv"
			style="display: block;">
			<form id="queryForm">
				<!-- 查询区 表单 -->
				<input type="hidden" id="orgId" name="orgId" />
				<!-- 查询区 表单 -->
				<div class="search border-bottom">
					<ul>
						<li style="width: 560px; float: left;"><span>关键字:</span> <input
							id="userAccount" type="text" placeholder="帐号"
							class="search_choose100" value="" name="userAccount"
							autocomplete="off"> <input id="fullName" type="text"
							placeholder="姓名" class="search_choose100" value=""
							name="fullName" autocomplete="off"></li>
						<li style="width: 400px;"><input type="reset" value="确定"
							onClick="doSelect()" class="reset_btn"> <input
							type="reset" value="重置" onClick="resetForm('queryForm')"
							class="reset_btn mr22">
						<!-- 重置 --> <input type="button" value="查询" onclick="doSearch();"
							class="search_btn mr22 " /></li>
						<!-- 查询-->
					</ul>
				</div>
			</form>


			<!--功能按钮end-->
			<table id="remote_rowed"></table>
			<div id="remote_prowed"></div>
		</div>
	</div>
</body>
</html>