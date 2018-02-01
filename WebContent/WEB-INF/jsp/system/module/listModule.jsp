<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML  >
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<title><m:message code="module.list.title" /></title>
</head>
<body>
	<div class="container" id="container">
		<!--总div -->
		<form id="queryForm">
			<!-- 查询区 表单 -->
			<div class="search border-bottom">
				<ul>
					<li style="width: 320px; float: left;"><span>关键字:</span> <input
						id="moduleName" type="text"
						placeholder="<m:message code="module.moduleName" />"
						class="search_choose100" value="" name="moduleName"
						autocomplete="off"> <input id="moduleCode" type="text"
						placeholder="<m:message code="module.moduleCode" />"
						class="search_choose100" value="" name="moduleCode"
						autocomplete="off"></li>
					<li><input type="reset" value="重置"
						onClick="resetForm('queryForm')" class="reset_btn">
					<!-- 重置 --> <input type="button" value="查询" onclick="doSearch();"
						class="search_btn mr22 " /></li>
					<!-- 查询-->
				</ul>
			</div>
		</form>
		<div class="listplace">
			<!--功能按钮begin-->
			<div class="list_btn_bg fl">
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
						href="javascript:" onclick="show();"> <i
							class="icon_bg icon_ckxq"></i> <span><m:message
									code="button.view" /></span>
					</a></li>
					<c:if test="${manage}">
						<li><a title="<m:message code="button.module.moduleRes"/>"
							href="javascript:" onclick="moduleResMgt();"> <i
								class="back_icon resources_icon"></i> <span><m:message
										code="button.module.moduleRes" /></span>
						</a></li>
					</c:if>
				</ul>
			</div>
			<!--功能按钮end-->
			<div class="listtable_box">
				<!--此处放表格-->
				<table id="remote_rowed"></table>
				<div id="remote_prowed"></div>
			</div>
		</div>
		<!--功能列表区end-->
	</div>
</body>
<script type="text/javascript">

var gridObj = {};
$(function() {
	gridObj = new biz.grid({
		id : "#remote_rowed",/* html部分table id */
		url : baseUrl + '/module/listModule.do',/* grid初始化请求数据的远程地址 */
		datatype : "json",/* 数据类型，设置为json数据，默认为json */
		sortname : "SERIAL_INDEX",
		sortorder : "asc",
		multiselect:true,
       	multiboxonly:true,
		// navtype:"top" /*导航栏类型*/,
		// height: gridHeight,
		pager : '#remote_prowed' /* 分页栏id */,
		rowList : [ 10, 15, 50, 100 ],// 每页显示记录数
		rowNum : 10,// 默认显示15条
		rownumbers : true, // 是否显示行号
		colModel : [ {
			name : "id",
			hidden : true,
			key : true
		}, {
			name : "moduleName",
			label : "菜单名称",
			index : "MODULE_NAME"
		}, {
			name : "moduleCode",
			label : "菜单编码",
			index : "MODULE_CODE"
		}, {
			name : "navurl",
			label : "导航URL",
			index : "NAVURL"
		}, {
			name : "serialIndex",
			label : "顺序",
			align : "center",
			width : 40,
			index : "SERIAL_INDEX"
		}, {
			name : "enabled",
			label : "状态",
			formatter : ma_status,
			align : "center",
			width : 40,
			index : "ENABLED"
		}, {
			name : "needRight",
			label : "是否需求权限",
			formatter : ma_needright,
			align : "center",
			width : 50,
			index : "NEED_RIGHT"
		}, {
			name : "moduleMemo",
			label : "备注",
			sortable : false
		} ],
		serializeGridData : function(postData) {// 添加查询条件值
			var obj = getQueryCondition();
			$.extend(true, obj, postData);// 合并查询条件值与grid的默认传递参数
			return obj;
		},
		gridComplete : function() {
			// 数据加载完毕后，调整样式
			var ids = gridObj.getDataIDs();
			if (ids == "") {
				$("#next_remote_prowed").attr("class",
						"ui-pg-button ui-corner-all ui-state-disabled");
				$("#last_remote_prowed").attr("class",
						"ui-pg-button ui-corner-all ui-state-disabled");
			}
		}
	});
	window.parent.loadedMask();
});

function ma_status(cellvalue, options, rowObject) {
	if (cellvalue == '1') {
		return "启用";
	} else if (cellvalue == '0') {
		return "禁用";
	}
}

function ma_needright(cellvalue, options, rowObject) {
	if (cellvalue == '1') {
		return "是";
	} else if (cellvalue == '0') {
		return "否";
	}
}

/**
 * 获取查询条件值
 */
function getQueryCondition() {
	var obj = {};
	addAttrToObject(obj, "moduleName");
	addAttrToObject(obj, "moduleCode");
	addAttrToObject(obj, "enabled");
	addAttrToObject(obj, "needRight");
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
	var url = baseUrl + '/module/toAddModule.do';
	add_iframe_dialog = new biz.dialog(
			{
				id : $('<div id="addwindow_iframe"></div>')
						.html(
								'<iframe id="iframeAdd" name="iframeAdd" src="'
										+ url
										+ '" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
				modal : true,
				width : 600,
				height : 300,
				title : "添加菜单信息"
			});
	add_iframe_dialog.open();
}

// 关闭新增页面，供子页面调用
function closeAdd() {
	add_iframe_dialog.close();
}

function edit() {
	var key = ICSS.utils.getSelectRowData("id");
	if (key.indexOf(",") > -1 || key == "") {
		showMessage("请选择一条数据");
		return;
	}
	var url = baseUrl + '/module/toUpdateModule.do?key=' + key;
	edit_iframe_dialog = new biz.dialog(
			{
				id : $('<div id="editwindow_iframe"></div>')
						.html(
								'<iframe id="iframeEdit" name="iframeEdit" src="'
										+ url
										+ '" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
				modal : true,
				width : 600,
				height : 300,
				title : "编辑菜单信息"
			});
	edit_iframe_dialog.open();
}

// 关闭编辑页面，供子页面调用
function closeEdit() {
	edit_iframe_dialog.close();
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
	doSearch();
}

// 删除
function batchDelete() {
	var ids = ICSS.utils.getSelectRowData("id");
	if (ids == "") {
		showMessage("请选择一条数据");
		return;
	} else {
		new biz.alert({
			type : "confirm",
			message : I18N.msg_del_confirm,
			title : I18N.promp,
			callback : function(result) {
				if (result) {
					$.ajax({
						url : baseUrl + '/module/deleteModule.do?key=' + ids,
						cache : false,
						success : function(data, textStatus, jqXHR) {
							doSearch();
							showInfo("删除成功", 3000);
						}
					});
				}
			}
		});
	}
}
// 打开菜单资源管理界面
function moduleResMgt() {
	var key = ICSS.utils.getSelectRowData("id");
	if (key.indexOf(",") > -1 || key == "") {
		showMessage("请选择一条数据");
		return;
	}
	var url = baseUrl + '/module/toListModuleRes.do?key=' + key;
	moduleReslist_iframe_dialog = new biz.dialog(
			{
				id : $('<div id="sublist_window_iframe"></div>')
						.html(
								'<iframe id="iframeSublist" name="iframeSublist" src="'
										+ url
										+ '" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
				modal : true,
				width : $(window).width()*0.8,
				height : $(window).height()*0.8,
				title : "菜单资源管理"
			});
	moduleReslist_iframe_dialog.open();
}

// 关闭关联资源管理界面
function closeModuleRes() {
	moduleReslist_iframe_dialog.close();
}


</script>
</html>
