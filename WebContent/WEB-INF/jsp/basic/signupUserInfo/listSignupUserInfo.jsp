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
var areaProvinceBox, areaRegionBox, areaCityBox;
$(function() {

	gridObj = new biz.grid({
		id : "#remote_rowed",/* html部分table id */
		url : baseUrl + "/signupUserInfo/list.do",/* grid初始化请求数据的远程地址 */
		datatype : "json",/* 数据类型，设置为json数据，默认为json */
		sortname : "created",
		sortorder : "asc",
		// navtype:"top" /*导航栏类型*/,
		// height: gridHeight,
		pager : '#remote_prowed' /* 分页栏id */,
		rowList : [ 10, 20, 30, 50, 100, 200 ],// 每页显示记录数
		rowNum : 10,// 默认显示15条
		shrinkToFit:false,
		singleselect : true,
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
		}, {width : 50,
			name : "sex",
			label : "性别",
			formatter:function(cellvalue, options, rowObject){
				 if (cellvalue==1) {
				 	return '男';
				 }else {
				 	return '女';
				 }
			}
		}, 
		 {width : 50,
			name : "age",
			label : "年龄"
		},{width : 100,
			name : "mobilePhone",
			label : "手机号码",
			index:"MOBILE_PHONE"
		},{width : 100,
			name : "areaProvinceName",
			label : "省",
			index:"AREA_PROVINCE_ID"
		},{width : 100,
			name : "areaRegionName",
			label : "市州",
			index:"AREA_REGION_ID"
		},{width : 100,
			name : "areaCityName",
			label : "县区",
			index:"AREA_CITY_ID"
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


	//省
	areaProvinceBox = $('#areaProvinceName').TiledCombobox({
		url :  baseUrl +"/areaProvince/list.do",
		fieldId : 'aapid',
		fieldName : 'provinceName',
		valueId : '#areaProvinceId',
		multiple : false,
		onChangeFn : function(v) {
			areaRegionBox.setQueryDataAndReload("apid=" + v);
			areaRegionBox.clearVlaue();
			areaCityBox.clearVlaue();
		}
	});
	//市
	areaRegionBox = $('#areaRegionName').TiledCombobox({
		url :  baseUrl +"/areaRegion/list.do",
		fieldId : 'aarid',// 数据有几级
		fieldName : 'regionName',
		valueId : '#areaRegionId',
		multiple : false,
		autoLoadData:false,
		onChangeFn : function(v) {
			areaCityBox.setQueryDataAndReload("aarid=" + v);
			areaCityBox.clearVlaue();
		}
	});
	//县区市
	areaCityBox = $('#areaCityName').TiledCombobox({
		url :  baseUrl +"/areaCity/list.do",
		fieldId : 'aacid',// 数据有几级
		fieldName : 'cityName',
		valueId : '#areaCityId',
		autoLoadData:false,
		multiple : false
	});
});

/**
 * 获取查询条件值
 */
function getQueryCondition() {
	var obj = {};
	addAttrToObject(obj, "userAccount");
	addAttrToObject(obj, "fullName");
	addAttrToObject(obj, "mobilePhone");
	addAttrToObject(obj, "idCard");
	addAttrToObject(obj, "states");
	addAttrToObject(obj, "areaProvinceId");
	addAttrToObject(obj, "areaRegionId");
	addAttrToObject(obj, "areaCityId");
	return obj;
}

// 新增的弹出框
var add_iframe_dialog;
// 修改的弹出框
var edit_iframe_dialog;
// 查看的弹出框
var show_iframe_dialog;

function add() {
	// xin zeng iframe 弹出框
	if (orgId == null || orgId == '') {
		showMessage('请选择一个组织机构');
		return;
	}
	var url = baseUrl + '/signupUserInfo/toAdd.do';
	add_iframe_dialog = new biz.dialog(
			{
				id : $('<div id="addwindow_iframe"></div>')
						.html(
								'<iframe id="iframeAdd" name="iframeAdd" src="'
										+ url
										+ '" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
				modal : true,
				width : 800,
				height : 500,
				title : "用户信息增加"
			});
	add_iframe_dialog.open();
	//window.location.href=url; 
}

// 关闭新增页面，供子页面调用
function closeAdd() {
	add_iframe_dialog.close();
}

function edit() {
	var key = ICSS.utils.getSelectRowData("userId");
	if (key.indexOf(",") > -1 || key == "") {
		showMessage("请选择一条数据！");
		return;
	}
	var url = baseUrl + '/signupUserInfo/toEdit.do?key=' + key;
	edit_iframe_dialog = new biz.dialog(
			{
				id : $('<div id="editwindow_iframe"></div>')
						.html(
								'<iframe id="iframeEdit" name="iframeEdit" src="'
										+ url
										+ '" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
				modal : true,
				width : 800,
				height : 500,
				title : "用户信息编辑"
			});
	edit_iframe_dialog.open();
}

// 关闭编辑页面，供子页面调用
function closeEdit() {
	edit_iframe_dialog.close();
}

function show() {
	var key = ICSS.utils.getSelectRowData("userId");
	if (key.indexOf(",") > -1 || key == "") {
		showMessage("请选择一条数据！");
		return;
	}
	var url = baseUrl + '/signupUserInfo/toShow.do?key=' + key;
	show_iframe_dialog = new biz.dialog(
			{
				id : $('<div id="showwindow_iframe"></div>')
						.html(
								'<iframe id="iframeShow" name="iframeShow" src="'
										+ url
										+ '" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
				modal : true,
				width : 800,
				height : 500,
				title : "用户信息详情"
			});
	show_iframe_dialog.open();
}

// 关闭查看页面，供子页面调用
function closeShow() {
	show_iframe_dialog.close();
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
	doSearch();
}

// 删除
function batchDelete() {
	var ids = ICSS.utils.getSelectRowData("userId");
	if (ids == "") {
		showMessage("请至少选择一条数据！");
		return;
	} else {
		new biz.alert({
			type : "confirm",
			message : I18N.msg_del_confirm,
			title : I18N.promp,
			callback : function(result) {
				if (result) {
					$.ajax({
						url : baseUrl + '/signupUserInfo/delete.do?key='
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



function expExcelWinShow(){
	ExpExcel.showWin(gridObj,baseUrl+"/signupUserInfo/exportExcel.do",'grid','queryForm');
	}

</script>
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
							id="userAccount" type="text" placeholder="帐号"
							class="search_choose100" value="" name="userAccount"
							autocomplete="off"> <input id="fullName" type="text"
							placeholder="姓名" class="search_choose100" value=""
							name="fullName" autocomplete="off"> <input
							id="mobilePhone" type="text" placeholder="手机号码"
							class="search_choose100" value="" name="mobilePhone"
							autocomplete="off"> <input id="idCard" type="text"
							placeholder="身份证" class="search_choose100" value="" name="idCard"
							autocomplete="off"></li>
						<li class="w200"><span>人员状态:</span> <select name="states"
							id="states" class="search_choose100">
								<option value="">--请选择--</option>
								<option value="1">启用</option>
								<option value="0">停用</option>
						</select></li>

						<li style="width: 500px;"><span>所属地区:</span> <input
							id="areaProvinceName" name="areaProvinceName" type="text"
							placeholder="省" class="search_choose100" value="" /> <input
							id="areaProvinceId" name="areaProvinceId" type="hidden"
							class="text" value="" /> <input id="areaRegionName"
							name="areaRegionName" type="text" placeholder="市"
							class="search_choose100" value="" /> <input id="areaRegionId"
							name="areaRegionId" type="hidden" class="text" value="" /> <input
							id="areaCityName" name="areaCityName" type="text"
							placeholder="区县" class="search_choose100" value="" /> <input
							id="areaCityId" name="areaCityId" type="hidden" class="text"
							value="" /></li>
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
					<c:if test="${add}">
						<li><a title="<m:message code="button.add"/>"
							href="javascript:;" onclick="add();"> <i
								class="icon_bg icon_add"> </i> <span><m:message
										code="button.add" /></span>
						</a></li>
					</c:if>
					<c:if test="${edit}">
						<li><a title="<m:message code="button.edit"/>"
							href="javascript:;" onclick="edit();"><i
								class="icon_bg icon_edit"></i> <span><m:message
										code="button.edit" /></span> </a></li>
					</c:if>
					<c:if test="${delete}">
						<li><a title="<m:message code="button.delete"/>"
							href="javascript:;" onclick="batchDelete();"> <i
								class="icon_bg icon_del"></i> <span><m:message
										code="button.delete" /></span>
						</a></li>
					</c:if>
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
</html>