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
</head>
<body style="height: 100%;">
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
							id="createdby" type="hidden" value="${createdby}"
							name="createdby"> <input id="userAccount" type="text"
							placeholder="帐号" class="search_choose100" value=""
							name="userAccount" autocomplete="off"> <input
							id="fullName" type="text" placeholder="姓名"
							class="search_choose100" value="" name="fullName"
							autocomplete="off"></li>
						<li class="w200"><input type="reset" value="重置"
							onClick="resetForm('queryForm')" class="reset_btn">
						<!-- 重置 --> <input type="button" value="查询" onclick="doSearch();"
							class="search_btn mr22 " /></li>
						<!-- 查询-->
					</ul>
				</div>
			</form>

			<!--功能按钮begin-->
			<div class="list_btn_bg fl" style="z-index: 2">
				<!--功能按钮 div-->
				<!--功能按钮 div-->
				<ul>
					<li><a title="<m:message code="button.add"/>"
						href="javascript:;" onclick="add();"> <i
							class="icon_bg icon_add"> </i> <span><m:message
									code="button.add" /></span>
					</a></li>
					<li><a title="<m:message code="button.delete"/>"
						href="javascript:;" onclick="batchDelete();"> <i
							class="icon_bg icon_del"></i> <span><m:message
									code="button.delete" /></span>
					</a></li>
					<li><a title="<m:message code="button.view"/>"
						href="javascript:;" onclick="show();"> <i
							class="icon_bg icon_ckxq"></i> <span><m:message
									code="button.view" /></span>
					</a></li>
					<li><a href="javascript:;" onClick="expExcelWinShow();"> <i
							class="icon_bg icon_download"></i> <span>导出</span>
					</a></li>

				</ul>
			</div>
			<!--功能按钮end-->
			<table id="remote_rowed"></table>
			<div id="remote_prowed"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
var gridObj = {};
var orgId, orgName;
var areaProvinceBox, areaRegionBox, areaCityBox;
$(function() {
	gridObj = new biz.grid({
		id : "#remote_rowed",/* html部分table id */
		url : baseUrl + "/userInfo/listUserInfo.do",/* grid初始化请求数据的远程地址 */
		datatype : "json",/* 数据类型，设置为json数据，默认为json */
		sortname : "created",
		sortorder : "asc",
		pager : '#remote_prowed' /* 分页栏id */,
		rowList : [ 10, 20, 30, 50, 100, 200 ],// 每页显示记录数
		rowNum : 10,// 默认显示15条
		autowidth:true,
		shrinkToFit:true,
		singleselect : true,
		colModel : [ {
			name : "id",
			hidden : true,
			key : true
		},{
			name : "roleId",
			hidden : true
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
		}, {width : 100,
			name : "dutyName",
			label : "岗位",
			index : "duty_name"
		},
		{width : 100,
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
			$("#gview_remote_rowed").setGridWidth($(window).width()*0.98);
		}
	});

});

/**
 * 获取查询条件值
 */
function getQueryCondition() {
	var obj = {};
	jQuery.each($("#queryForm").serializeArray(),function(i,o){
    	if(o.value){
    		obj[o.name] = o.value;
    	}
    });
	return obj;
}

// 新增的弹出框
var add_iframe_dialog;
// 修改的弹出框
var edit_iframe_dialog;
// 查看的弹出框
var show_iframe_dialog;

function add() {
	var url = baseUrl + '/userInfo/toAddNewUserInfo.do?orgId=4';
	add_iframe_dialog = new biz.dialog(
			{
				id : $('<div id="addwindow_iframe"></div>')
						.html(
								'<iframe id="iframeAdd" name="iframeAdd" src="'
										+ url
										+ '" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
				modal : true,
				width : 800,
				height : 330,
				title : "用户信息增加"
			});
	add_iframe_dialog.open();
	//window.location.href=url; 
}

// 关闭新增页面，供子页面调用
function closeAdd() {
	add_iframe_dialog.close();
}

function show() {
	var key = ICSS.utils.getSelectRowData("id");
	if (key.indexOf(",") > -1 || key == "") {
		showMessage("请选择一条数据！");
		return;
	}
	var url = baseUrl + '/userInfo/toShowUserInfo.do?key=' + key;
	show_iframe_dialog = new biz.dialog(
			{
				id : $('<div id="showwindow_iframe"></div>')
						.html(
								'<iframe id="iframeShow" name="iframeShow" src="'
										+ url
										+ '" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
				modal : true,
				width : 800,
				height : 700,
				title : "用户信息详情"
			});
	show_iframe_dialog.open();
}

// 关闭查看页面，供子页面调用
function closeShow() {
	show_iframe_dialog.close();
}

//删除
function batchDelete() {
	var ids = ICSS.utils.getSelectRowData("id");
	var roleIds = ICSS.utils.getSelectRowData("roleId");
	if (ids == "") {
		showMessage("请至少选择一条数据！");
		return;
	} else {
		if(roleIds=="dc3dfb2e-5e70-4525-9739-beef0856f15c"){showMessage("不可删除最高权限！");}else{
		new biz.alert({
			type : "confirm",
			message : I18N.msg_del_confirm,
			title : I18N.promp,
			callback : function(result) {
				if (result) {
					$.ajax({
						url : baseUrl + '/userInfo/deleteUserInfo.do?key='
								+ ids,
						cache : false,
						dataType : 'json',
						success : function(d, textStatus, jqXHR) {
							doSearch();
							showInfo(d.msg || d.message, 3000);
						}
					});
				}
			}
		});
		}
	}
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

function expExcelWinShow(){
	ExpExcel.showWin(gridObj,baseUrl+"/userInfo/exportExcel.do",'grid','queryForm');
	}


</script>
</html>