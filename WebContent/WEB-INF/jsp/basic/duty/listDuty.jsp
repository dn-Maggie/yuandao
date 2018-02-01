<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<title>岗位管理</title>
<style type="text/css">
.ui-jqgrid tr.ui-row-ltr td {
	padding: 0;
	border-right: 0px;
}
</style>
<script type="text/javascript">
	var gridObj = {},dutyTypeNameBox;
	$(function() {
		initDutyTree();
		//岗位选类型择
		dutyTypeNameBox = $('#dutyTypeName').TiledCombobox({
							url : "<m:url value='/dictInfo/getDictInfoByType.do?type=dutyType'/>",
							fieldId : 'value',
							fieldName : 'name',
							valueId : '#dutyType',
							multiple : false
						});
		gridObj = new biz.grid({
			id : "#remote_rowed",/*html部分table id*/
			url : "<m:url value='/duty/listDuty.do'/>",/*grid初始化请求数据的远程地址*/
			datatype : "json",/*数据类型，设置为json数据，默认为json*/
			sortname : "t.duty_Type",
			sortorder : "asc",
			rownumbers: false,
			pager : '#remote_prowed' /*分页栏id*/,
			rowList : [ 10, 20, 30, 50],//每页显示记录数
			rowNum :20,//默认显示15条
			colModel : [{ name : "id", label : "主键ID", hidden : true },
			            { name : "dutyName", index : "t.duty_name",label : "岗位名称" }, 
			            { name : "dutyTypeName", index : "t.duty_Type", label : "岗位类型" },
						{name : "dutyDesc",	index : "t.duty_desc",label : "岗位描述"}
			],
			/* multiselect: false, */
			autowidth:true,
			subGrid:true,
			subGridRowExpanded: function(subgrid_id, row_id) {
				var subgrid_table_id, pager_id;
				subgrid_table_id = subgrid_id+"_t";
				pager_id = "p_"+subgrid_table_id;
				$("#"+subgrid_id).data("loaded","true").html("<table id='"+subgrid_table_id+"' class='sub_grid' style='background:#fff'></table>");
				subgridObj = new biz.grid({
		            id:"#"+subgrid_table_id,/*html部分table id*/
		            url: "<m:url value='/duty/listDutyLevel.do'/>?id="+row_id,/*grid初始化请求数据的远程地址*/
		            datatype: "json",/*数据类型，设置为json数据，默认为json*/
		           	sortname:"level_name",
		           	rowNum:100,
		           	rownumbers: false,
		           	sortorder:"asc",
		           	multiselect: false,
		            colModel: [
							{name : "id",hidden : true,key : true,label:"",index : "id"},
		    		 		{name : "levelType",label:"等级类型", align:"right",index : "level_type",width:2,},
		    		 		{name : "levelName",label:"评定登记级别",index : "level_name",width:2 },
		    		 		{name : "",width:50},	
		    		 		
						],
				});
			},
			subGridType:"json",
			serializeGridData : function(postData) {//添加查询条件值
				var obj = getQueryCondition();
				$.extend(true, obj, postData);//合并查询条件值与grid的默认传递参数
				return obj;
			}
		});

	});

	/**
	 * 获取查询条件值
	 */
	function getQueryCondition() {
		var obj = {};
		addAttrToObject(obj, "pid");
		addAttrToObject(obj, "dutyName");
		/* addAttrToObject(obj, "dutySort"); */
		addAttrToObject(obj, "dutyType");
		return obj;
	}


	//新增的弹出框
	var add_iframe_dialog;
	//修改的弹出框
	var edit_iframe_dialog;
	//查看的弹出框
	var show_iframe_dialog;

	function add() {
		var url = "<m:url value='/duty/toAddDuty.do'/>?pid="+$('#pid').val();
		var title = "岗位信息增加";
		add_iframe_dialog = Add.create(url, title,870,235);
		List.openDialog(add_iframe_dialog);
	}

	//关闭新增页面，供子页面调用
	function closeAdd() {
		List.closeDialog(add_iframe_dialog,gridObj);
	}

	function edit() {
		var key = ICSS.utils.getSelectRowData("id");
		var url = "<m:url value='/duty/toEditDuty.do'/>";
		var title = "岗位信息编辑";
		edit_iframe_dialog = Edit.create(key, url, title,870,255);
		List.openDialog(edit_iframe_dialog);
	}

	//关闭编辑页面，供子页面调用
	function closeEdit() {
		List.closeDialog(edit_iframe_dialog,gridObj);
	}

	function show() {
		var key = ICSS.utils.getSelectRowData("id");
		var url = "<m:url value='/duty/toShowDuty.do'/>";
		var title = "岗位信息详情";
		show_iframe_dialog = Show.create(key, url, title,600,255);
		List.openDialog(show_iframe_dialog);
	}

	//关闭查看页面，供子页面调用
	function closeShow() {
		List.closeDialog(show_iframe_dialog);
	}

	//查询Grid数据
	function doSearch(isStayCurrentPage) {
		if (!isStayCurrentPage)
			gridObj.setGridParam({
				"page" : "1"
			});
		gridObj.trigger('reloadGrid');
	}
	//重置查询表单
	function resetForm(formId) {
		document.getElementById(formId).reset();
		$("#"+formId+" input[type='hidden']").val("");
		dutyTypeNameBox.clearVlaue();
		initDutyTree();
		doSearch();
	}

	//删除
	function batchDelete() {
		var id = ICSS.utils.getSelectRowData("id");
		var url = "<m:url value='/duty/deleteDuty.do'/>";
		List.batchDelete(id, url,gridObj);
	}

	function initDutyTree() {
		$.ajax({
			url : "<m:url value='/org/initOrgTree.do'/>",
			data : {
			},
			type : "POST",
			success : function(data, textStatus, jqXHR) {
				var setting_checkbox = {
					data : {},
					id : "#dutyTree",
					nodes : $.parseJSON(data), //数据节点指定     
					view : {
						selectedMulti : false
					},
					edit : {},
					data : {
						simpleData : {
							enable : true
						}
					},
					callback : {
						onClick : treeOnClick
					}
				};
				provinceTree = new biz.tree(setting_checkbox);//创建树 
			}
		});
	}
	function treeOnClick(event, treeId, treeNode) {
		$("#pid").val(treeNode.id);
		doSearch();
	}
</script>
</head>
<body style="height: 100%;">

	<div class="main  choice_box container"
		style="height: 100%; float: left; width: 22%">
		<div class="ui-table ui-widget ui-corner-all ui-margin ui-leftDiv">
			<form id="queryForm1">
				<div id="equal1" class="equal">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="top_table">
						<tr>
							<td class="rightSide">岗位名称：</td>
							<td><input type="text" name="dutyName1" id="dutyName1"
								class="text " onkeyup="search_ztree('dutyTree','name',this);" /></td>

						</tr>
					</table>
				</div>
			</form>
			<ul id="dutyTree" class="ztree"
				style="height: 100%; border-top: 1px solid #ddd; position: relative; overflow: auto; width: 97%"></ul>
		</div>
	</div>
	<div class="main  choice_box">
		<div class="ui-table ui-widget ui-corner-all ui-margin ui-rightDiv"
			style="display: block;">
			<form id="queryForm">
				<!-- 查询区 表单 -->
				<input type="hidden" id="pid" name="pid" />
				<div class="search border-bottom">
					<ul>
						<li class="w200"><span>岗位名称：</span><input type="text"
							class="search_choose100" id="dutyName" name="dutyName"></li>
						<li class="w200"><span>岗位类型：</span> <input id="dutyTypeName"
							name="dutyTypeName" type="text" class="search_choose100"
							value="${duty.dutyTypeName}" /> <input id="dutyType"
							name="dutyType" type="hidden" class="text"
							value="${duty.dutyType}" /></li>

						<!-- 输入框-->

						<li class="w200"><input type="reset" value="重置"
							onClick="resetForm('queryForm')" class="reset_btn"> <!-- 重置 -->
							<input type="button" value="查询" onclick="doSearch();"
							class="search_btn mr22 " /></li>
						<!-- 查询-->
					</ul>
				</div>
			</form>

			<!--功能按钮begin-->
			<div class="list_btn_bg fl" style="z-index: 2">
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
				</ul>
			</div>
			<!--功能按钮end-->
			<table id="remote_rowed"></table>
			<div id="remote_prowed"></div>
		</div>
	</div>


</body>
</html>
