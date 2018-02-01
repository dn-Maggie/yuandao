<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML  >
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<title><m:message code="role.list.title" /></title>
<style type="text/css">
.ztree .button {
	position: static;
	text-decoration: none !important;
	text-align: center;
	overflow: visible;
	font-weight: bold;
	color: #555555;
}
</style>
<script type="text/javascript">
	var menutree;
	var gridObj = {};
	var lastSel;
	$(function() {
		var menutreePh=$('#menutree').parent().height();
		$('#menutree').height(menutreePh-30);
		gridObj = new biz.grid({
			id : "#remote_rowed",/*html部分table id*/
			url : "<m:url value='/role/listRole.do'/>",/*grid初始化请求数据的远程地址*/
			datatype : "json",/*数据类型，设置为json数据，默认为json*/
			sortname : "r.create_time",
			sortorder : "desc",
			//navtype:"top" /*导航栏类型*/,
			//height: gridHeight,
			pager : '#remote_prowed' /*分页栏id*/,
			rowList : [ 10, 15, 50, 100 ],//每页显示记录数
			rowNum : 10,//默认显示15条
			multiselect : true,//启用多选框
			colModel : [ {
				name : "roleId",
				hidden : true,
				key : true
			}, {
				name : "roleName",
				label : "<m:message code='role.roleName'/>",
				width : 130,
				index : "r.role_name"
			}, {
				name : "roleCode",
				label : "<m:message code='role.roleCode'/>",
				index : "r.role_code",
				width : 130
			}, {
				name : "statesName",
				label : "<m:message code='role.states'/>",
				index : "r.states",
				formatter : function(cellvalue, options, cellObject) {
					return cellvalue == '0' ? '禁用' : '启用';
				},
				width : 130
			}
			//, {name : "startTime",label : "<m:message code='role.startTime'/>",index : "r.start_time",width : 200}
			//, {name : "endTime",label : "<m:message code='role.endTime'/>",index : "r.end_time",width : 200}
			, {
				name : "roleDesc",
				label : "<m:message code='role.roleDesc'/>",
				index : "r.role_desc",
				width : 200
			} ],
			serializeGridData : function(postData) {//添加查询条件值
				var obj = getQueryCondition();
				$.extend(true, obj, postData);//合并查询条件值与grid的默认传递参数
				return obj;
			},
			onSelectAll : function(aRowids, status) {

				gridObj.resetSelection();//当全选时取消所有选择
			},
			onSelectRow : function(rowid, status, e) {
				if (rowid == lastSel) {
					$(this).jqGrid("resetSelection");
					lastSel = undefined;
					status = false;
				} else {
					lastSel = rowid;
				}
				var id = gridObj.getCell(rowid, "roleId");
				var cnname = gridObj.getCell(rowid, "roleName");
				menutree.checkAllNodes(false);
				getResTreeByRoleId(id, cnname);
			},
			beforeSelectRow : function(rowId, e) {
				$(this).jqGrid("resetSelection");
				return true;
			}

		});
		setMenuTreeJson("");
		window.parent.loadedMask();
	});

	/**
	 * 获取查询条件值
	 */
	function getQueryCondition() {
		var obj = {};
		addAttrToObject(obj, "roleName");
		return obj;
	}

	//获取菜单资源数据，构建树
	function setMenuTreeJson(type) {
		$.ajax({
			url : "<m:url value='/role/getAllmenuTreeJson.do'/>?type=" + type,
			data : {},
			type : "POST",
			success : function(data, textStatus, jqXHR) {
				var setting_checkbox = {
					id : "#menutree",
					nodes : $.parseJSON(data), //数据节点指定     
					check : {
						enable : true,
						chkboxType : {
							"Y" : "ps",
							"N" : "s"
						}
					},
					data : {
						simpleData : {
							enable : true
						}
					}
				};
				menutree = new biz.tree(setting_checkbox);//创建树 
			}
		});
	}
	//根据角色ID设置菜单树已经勾选的资源
	function getResTreeByRoleId(rid, name) {
		menutree.checkAllNodes(false);
		if (null != rid && "" != rid) {
			$.ajax({
				url : "<c:url value='/role/getMenuTreeIdsByRoleId.do'/>",
				data : {
					roleRid : rid
				},
				type : "POST",
				dataType:"text",
				success : function(response, textStatus, jqXHR) {
					$('#treeTitle').text('菜单树(' + name + ')');
					data = response;
					if (null != data) {
						var ids = data.split(",");
						var nodes = menutree.transformToArray(menutree
								.getNodes());
						for ( var i = 0; i < nodes.length; i++) {
							for ( var j = 0; j < ids.length; j++) {
								if (ids[j] == nodes[i].id) {
									menutree.checkNode(nodes[i], true, false);
								}
							}
						}
					}
				}
			});
		}
	}

	//为角色分配菜单资源
	function portionRes() {
		new biz.alert({
			type : "confirm",
			message : "<m:message code='role.portionRes'/>",
			callback : function(result) {
				if (result) {
					var rowid = gridObj.getGridParam("selrow");
					if (rowid == null || rowid.length == 0) {
						showInfo("请选择要分配权限的行", 3000);
						return;
					}
					var id = gridObj.getCell(rowid, 'roleId');
					var resArr = [];
					var trnodes = menutree.getCheckedNodes();
					if (trnodes.length > 0) {
						for ( var i = 0; i < trnodes.length; i++) {
							if (trnodes[i].type == '2') {//资源
								var actcodes = "";
								var child = trnodes[i].children;
								if (child != undefined) {
									for ( var c = 0; c < child.length; c++) {
										var act = child[c];
										if (act.checked) {
											actcodes += act.actcode + ",";
										}
									}
									actcodes = actcodes.substr(0,
											actcodes.length - 1);
								}
								jsonObj = {
									resuuid : trnodes[i].id,
									actCodes : actcodes
								};
								resArr.push(jsonObj);
							}
						}
					}
					$.ajax({
						url : "<c:url value='/role/portionRight.do'/>",
						data : {
							roleRid : id,
							objectJson : JSON.stringify(resArr)
						},
						type : "POST",
						//dataType: "json",
						success : function(data, textStatus, jqXHR) {
							showInfo("<m:message code='add.success'/>", 3000);
						}
					});
				}
			}
		});

	}

	function showRoleUser() {
		var rowid = gridObj.getGridParam("selrow");
		if (rowid == null || rowid.length == 0) {
			showInfo("<m:message code='grid.view.chooseColAlert'/>", 3000);
			return;
		}
		var id = gridObj.getCell(rowid, 'roleId');
		var url = "<m:url value='/role/toShowRoleUser.do'/>?roleRid=" + id;
		show_iframe_dialog = new biz.dialog(
				{
					id : $('<div id="showwindow_iframe"></div>')
							.html(
									'<iframe id="iframeShow" name="iframeShow" src="'
											+ url
											+ '" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
					modal : true,
					width : 720,
					height : 480,
					title : "<m:message code='button.role.showUser' />"
				});
		show_iframe_dialog.open();
	}

	//关闭查看页面，供子页面调用
	function closeShow() {
		show_iframe_dialog.close();
	}

	//新增的弹出框
	var add_iframe_dialog;
	//修改的弹出框
	var edit_iframe_dialog;

	function add() {
		//xin zeng iframe 弹出框
		var url = "<m:url value='/role//toAddRole.do'/>";
		add_iframe_dialog = new biz.dialog(
				{
					id : $('<div id="addwindow_iframe"></div>')
							.html(
									'<iframe id="iframeAdd" name="iframeAdd" src="'
											+ url
											+ '" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
					modal : true,
					width : 800,
					height : 360,
					title : "<m:message code='role.list.title' />"
				});
		add_iframe_dialog.open();
	}

	//关闭新增页面，供子页面调用
	function closeAdd() {
		add_iframe_dialog.close();
	}

	function edit() {
		var rowid = gridObj.getGridParam("selrow");
		if (rowid == null || rowid.length == 0) {
			showInfo(I18N.msg_no_sel_edit_record, 3000);
			return;
		}
		var key = gridObj.getCell(rowid, 'roleId');
		var url = "<m:url value='/role/toEditRole.do'/>?key=" + key;
		edit_iframe_dialog = new biz.dialog(
				{
					id : $('<div id="editwindow_iframe"></div>')
							.html(
									'<iframe id="iframeEdit" name="iframeEdit" src="'
											+ url
											+ '" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
					modal : true,
					width : 800,
					height : 360,
					title : "<m:message code='role.list.title' />"
				});
		edit_iframe_dialog.open();
	}

	//关闭编辑页面，供子页面调用
	function closeEdit() {
		edit_iframe_dialog.close();
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
		doSearch();
	}

	//删除
	function batchDelete() {
		var rowid = gridObj.getGridParam("selrow");
		if (rowid == null || rowid.length == 0) {
			showInfo(I18N.msg_no_sel_del_record, 3000);
			return;
		}

		var ids = gridObj.getCell(rowid, 'roleId');
		//var ids = ICSS.utils.getSelectRowData("roleId");

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
											url : "<m:url value='/role/deleteRole.do'/>?key="
													+ ids,
											cache : false,
											success : function(data,
													textStatus, jqXHR) {
												if (data == "1") {
													doSearch();
													showInfo(
															"<m:message code='delete.success'/>",
															3000);
												} else {
													showInfo("删除失败:角色下存在用户!");
												}
											}
										});
							}
						}
					});
		}
	}
</script>
</head>
<body style="height: 100%;">


	<div class="main  choice_box"
		style="height: 100%; float: right; width: 22%">
		<div class="ui-table ui-widget ui-corner-all ui-margin ui-leftDiv">
			<div id="equal1"
				style="height: 25px; text-align: center; padding-top: 5px;">
				菜单列表</div>
			<ul id="menutree" class="ztree"
				style="height: 420px; margin-top: 0; border-top: 1px solid #ddd; position: relative; overflow: auto; width: 97%"></ul>
		</div>
	</div>
	<div class="main  choice_box">
		<div class="ui-table ui-widget ui-corner-all ui-margin ui-rightDiv"
			style="display: block;">
			<form id="queryForm">
				<!-- 查询区 表单 -->
				<input type="hidden" id="typeCode" value="" /> <input type="hidden"
					id="dictTypeCode" name="dictTypeCode" value="" />
				<div class="search border-bottom">
					<ul>
						<li style="width: 220px; float: left;"><span>关键字:</span> <input
							id="roleName" type="text" placeholder="角色名称"
							class="search_choose100" value="" name="roleName"
							autocomplete="off"></li>
						<li><input type="reset" value="重置"
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

					<li><a title="<m:message 

code="button.add"/>"
						href="javascript:;" onclick="add();"> <i
							class="icon_bg icon_add"> </i> <span><m:message
									code="button.add" /></span>
					</a></li>

					<c:if test="${edit}">
						<li><a title="<m:message 

code="button.edit"/>"
							href="javascript:;" onclick="edit();"><i
								class="icon_bg icon_edit"></i> <span><m:message
										code="button.edit" /></span> </a></li>
					</c:if>
					<c:if test="${delete}">
						<li><a title="<m:message 

code="button.delete"/>"
							href="javascript:;" onclick="batchDelete();"> <i
								class="icon_bg icon_del"></i> <span><m:message
										code="button.delete" /></span>
						</a></li>
					</c:if>
					<c:if test="${showUser}">
						<li><a title="<m:message 

code="button.role.showUser"/>"
							href="javascript:" onclick="showRoleUser();"> <i
								class="back_icon role_icon"></i> <span> <m:message
										code="button.role.showUser" /></span>
						</a></li>
					</c:if>
					<c:if test="${portionRight}">
						<li><a title="<m:message 

code="button.role.portion"/>"
							href="javascript:" onclick="portionRes();"> <i
								class="back_icon permissions_icon"></i> <span> <m:message
										code="button.role.portion" /></span>
						</a></li>
					</c:if>
				</ul>
			</div>
			<!--功能按钮end-->
			<table id="remote_rowed"></table>
			<div id="remote_prowed"></div>
		</div>
	</div>

</body>
</html>
