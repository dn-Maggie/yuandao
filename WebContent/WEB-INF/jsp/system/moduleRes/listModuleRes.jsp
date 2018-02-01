<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML  >
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<title><m:message code="moduleRes.list.title" /></title>
<script type="text/javascript">
	var statusData = $.parseJSON('${statusData}')||{};
	var yn_numData = $.parseJSON('${yn_numData}')||{};
	var gridObj = {};
	$(function() {
		gridObj = new biz.grid({
			id : "#remote_rowed",/*html部分table id*/
			url : "<m:url value='/moduleRes/listModuleRes.do'/>",/*grid初始化请求数据的远程地址*/
			datatype : "json",/*数据类型，设置为json数据，默认为json*/
			sortname : "serialIndex",
			sortorder : "asc",
			multiselect:true,
           	multiboxonly:true,
			pager : '#remote_prowed' /*分页栏id*/,
			rowList : [ 10, 15, 50, 100 ],//每页显示记录数
			rowNum : 10,//默认显示15条
			rownumbers : true, //是否显示行号
			//height: gridHeight,
			colModel : [ {
				name : "id",
				hidden : true,
				key : true
			}, {
				name : "muuid",
				hidden : true
			}, {
				name : "resourceName",
				label : "<m:message code="moduleRes.resourceName"/>",
				align : "left",
				width : 120,
				index : "RESOURCE_NAME"
			}, {
				name : "resourcecode",
				label : "<m:message code="moduleRes.resourcecode"/>",
				align : "left",
				width : 120,
				index : "RESOURCECODE"
			}, {
				name : "resTypeName",
				label : "<m:message code='moduleRes.resType'/>",
				align : "left",
				width : 40,
				index : "RES_TYPE"
			}, {
				name : "resurl",
				label : "<m:message code="moduleRes.resurl"/>",
				align : "left",
				width : 120,
				index : "RESURL"
			}, {
				name : "status",
				label : "<m:message code="moduleRes.status"/>",
				formatter : ma_status,
				align : "center",
				width : 40,
				index : "STATUS"
			}, {
				name : "needright",
				label : "<m:message code="moduleRes.needright"/>",
				formatter : ma_needright,
				align : "center",
				width : 40,
				index : "NEEDRIGHT"
			}, {
				name : "serialindex",
				label : "<m:message code="moduleRes.serialindex"/>",
				align : "left",
				width : 40,
				index : "SERIALINDEX"
			}, {
				name : "memo",
				label : "<m:message code="moduleRes.memo"/>",
				sortable : false
			} ],
			serializeGridData : function(postData) {//添加查询条件值
				var obj = getQueryCondition();
				$.extend(true, obj, postData);//合并查询条件值与grid的默认传递参数
				return obj;
			},
			gridComplete : function() {
				//数据加载完毕后，调整样式
				var ids = gridObj.getDataIDs();
				if (ids == "") {
					$("#next_remote_prowed").attr("class",
							"ui-pg-button ui-corner-all ui-state-disabled");
					$("#last_remote_prowed").attr("class",
							"ui-pg-button ui-corner-all ui-state-disabled");
				}
			}
			
		});
		new biz.select({//状态下拉
			id : "#status",
			data : statusData,
			//url:"<m:url value='/dictInfo/getDictByTypeCode.do?dictTypeCode=status'/>",
			addEmptyItem : true
		});
		new biz.select({//状态下拉
			id : "#needright",
			data : yn_numData,
			//url:"<m:url value='/dictInfo/getDictByTypeCode.do?dictTypeCode=yn_num'/>",
			addEmptyItem : true
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
		addAttrToObject(obj, "resourceName");
		addAttrToObject(obj, "resourcecode");
		addAttrToObject(obj, "needright");
		addAttrToObject(obj, "status");
		return obj;
	}

	function addAttrToObject(obj, name) {
		element = document.getElementById(name);
		var notInputValue = "";
		if (element != null) {
			if (element.value == null || element.value == "") {
				if ($(element).attr("uiType") != null) {
					if ($(element).attr("uiType") == "checkbox") {
						var tempCheckBoxObj = $(element).find(".checkbox");
						if (tempCheckBoxObj != null
								&& tempCheckBoxObj.length > 0) {
							tempCheckBoxObj.each(function() {
								if (this.checked) {
									notInputValue += this.value + ",";
								}
							});
							if (notInputValue != null
									&& notInputValue.indexOf(",") != -1) {
								notInputValue = notInputValue.substring(0,
										notInputValue.length - 1);
							}

						}
					} else if ($(element).attr("uiType") == "radio") {
						var tempRadioObj = $(element).find(".radio");
						if (tempRadioObj != null && tempRadioObj.length > 0) {
							tempRadioObj.each(function() {
								if (this.checked) {
									notInputValue = this.value;
									return;
								}
							});
						}
					}
					if (notInputValue == null || notInputValue == "") {
						return;
					}
				} else {
					return;
				}
			}

		} else {
			return;
		}

		obj = obj || {};
		var value = element.value != null ? element.value : notInputValue;
		if (element.name == 'resourcecode') {
			value = '%' + value + '%';
		}
		if (element.name == 'resourceName') {
			value = '%' + value + '%';
		}
		obj[name] = value;
	}

	//新增的弹出框
	var add_iframe_dialog;
	//修改的弹出框
	var edit_iframe_dialog;
	//查看的弹出框
	var show_iframe_dialog;

	function add() {
		//xin zeng iframe 弹出框
		var url = "<m:url value='/moduleRes/toAddModuleRes.do'/>";
		add_iframe_dialog = new biz.dialog(
				{
					id : $('<div id="addwindow_iframe"></div>')
							.html(
									'<iframe id="iframeAdd" name="iframeAdd" src="'
											+ url
											+ '" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
					modal : true,
					width : 700,
					height : 410,
					title : "<m:message code='moduleRes.title' />"
				});
		add_iframe_dialog.open();
	}

	function closeAdd() {
		add_iframe_dialog.close();
	}

	function edit() {
		var key = ICSS.utils.getSelectRowData("id");
		if (key.indexOf(",") > -1 || key == "") {
			showMessage("<m:message code='grid.edit.chooseColAlert'/>");
			return;
		}
		var url = "<m:url value='/moduleRes/toUpdateModuleRes.do'/>?key=" + key;
		edit_iframe_dialog = new biz.dialog(
				{
					id : $('<div id="editwindow_iframe"></div>')
							.html(
									'<iframe id="iframeEdit" name="iframeEdit" src="'
											+ url
											+ '" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
					modal : true,
					width : 700,
					height : 410,
					title : "<m:message code='moduleRes.title' />"
				});
		edit_iframe_dialog.open();

	}

	//供子页面调用
	function closeEdit() {
		edit_iframe_dialog.close();
	}

	function show() {
		var key = ICSS.utils.getSelectRowData("id");
		if (key.indexOf(",") > -1 || key == "") {
			showMessage("<m:message code='grid.view.chooseColAlert'/>");
			return;
		}
		var url = "<m:url value='/moduleRes/toShowModuleRes.do'/>?key=" + key;
		show_iframe_dialog = new biz.dialog(
				{
					id : $('<div id="showwindow_iframe"></div>')
							.html(
									'<iframe id="iframeShow" name="iframeShow" src="'
											+ url
											+ '" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
					modal : true,
					width : 700,
					height : 300,
					title : "资源详情"
				});
		show_iframe_dialog.open();

	}

	function closeShow() {
		show_iframe_dialog.close();
	}

	function doSearch(isStayCurrentPage) {
		if (!isStayCurrentPage)
			gridObj.setGridParam({
				"page" : "1"
			});
		gridObj.trigger('reloadGrid');
	}
	   //重置查询表单
    function resetForm(formId){
		document.getElementById(formId).reset();
		doSearch();
	}
	//删除
	function batchDelete() {
		var ids = ICSS.utils.getSelectRowData("id");
		if (ids == "") {
			showMessage("<m:message code='grid.delete.chooseColAlert'/>");
			return;
		} else {
			new biz.alert(
					{
						type : "confirm",
						message : I18N.msg_del_confirm,
						title : I18N.promp,
						callback : function(result) {
							if (result) {
								$
										.ajax({
											url : "<m:url value='/moduleRes/deleteModuleRes.do'/>?key="
													+ ids,
											cache : false,
											success : function(data,
													textStatus, jqXHR) {
												doSearch();
												showInfo(
														"<m:message code='delete.success'/>",
														3000);
											}
										});
							}
						}
					});
		}
	}

	//菜单资源管理
	function resActMgt() {
		var key = ICSS.utils.getSelectRowData("id");
		if (key.indexOf(",") > -1 || key == "") {
			showMessage("<m:message code='grid.dealwith.chooseColAlert'/>");
			return;
		}
		var url = "<m:url value='/moduleRes/toListResAct.do'/>?key=" + key;
		resActlist_iframe_dialog = new biz.dialog(
				{
					id : $('<div id="sublist_window_iframe"></div>')
							.html(
									'<iframe id="iframeSublist" name="iframeSublist" src="'
											+ url
											+ '" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
					modal : true,
					width : 750,
					height : 450,
					title : "<m:message code='resAct.list.title' />"
				});
		resActlist_iframe_dialog.open();
	}

	//关闭关联资源管理界面
	function closeResAct() {
		resActlist_iframe_dialog.close();
	}
</script>
</head>
<body>
	<div class="container" id="container">
		<!--总div -->
		<form id="queryForm">
			<!-- 查询区 表单 -->
			<div class="search border-bottom">
				<ul>
					<li style="width: 320px; float: left;"><span>关键字:</span> <input
						id="resourceName" type="text"
						placeholder="<m:message code="moduleRes.resourceName" />"
						class="search_choose100" value="" name="resourceName"
						autocomplete="off"> <input id="resourcecode" type="text"
						placeholder="<m:message code="moduleRes.resourcecode" />"
						class="search_choose100" value="" name="resourcecode"
						autocomplete="off"></li>
					<li><select id="status" name="status" class="search_select"></select>
						<span><m:message code="moduleRes.status" />:</span></li>
					<!--下拉 -->

					<li><select id="needright" name="needright"
						class="search_select"></select> <span><m:message
								code="moduleRes.needright" />:</span></li>
					<!--下拉 -->

					<li><input type="reset" value="重置"
						onClick="resetForm('queryForm')" class="reset_btn"> <!-- 重置 -->
						<input type="button" value="查询" onclick="doSearch();"
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
						<li><a title="<m:message code="button.moduleRes.act"/>"
							href="javascript:" onclick="resActMgt();"> <i
								class="back_icon resourcesManage_icon"></i> <span><m:message
										code="button.moduleRes.act" /></span>
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
</html>
