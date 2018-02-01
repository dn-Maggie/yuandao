<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html  >
<html>
<head>
<base href="<%=basePath%>">
<%@ include file="../../common/header.jsp"%>
</head>
<body style="height: 100%;">

	<div class="main  choice_box"
		style="height: 100%; float: left; width: 22%">
		<div class="ui-table ui-widget ui-corner-all ui-margin ui-leftDiv">
			<form id="queryForm1">
				<div id="equal1" class="equal">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="top_table">
						<tr>
							<td class="rightSide"><m:message code="type.typeName" />:</td>
							<td><input type="text" name="typeName" id="typeName"
								class="text " onkeyup="initDictTypeTree();" /></td>
						</tr>
					</table>
				</div>
			</form>
			<ul id="dictTypeTree" class="ztree"
				style="height: 350px; margin-top: 0; border-top: 1px solid #ddd; position: relative; overflow: auto; width: 97%"></ul>
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
						<li style="width: 320px; float: left;"><span>关键字:</span> <input
							id="dictName" type="text"
							placeholder="<m:message code="dict.dictName" />"
							class="search_choose100" value="" name="dictName"
							autocomplete="off"> <input id="dictCode" type="text"
							placeholder="<m:message code="dict.dictCode" />"
							class="search_choose100" value="" name="dictCode"
							autocomplete="off"></li>
						<li><input type="reset" value="重置"
							onClick="resetForm('queryForm')" class="reset_btn">
						<!-- 重置 --> <input type="button" value="查询"
							onclick="dictInfoSearch();" class="search_btn mr22 " /></li>
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
							href="javascript:;" onclick="addDictInfo();"> <i
								class="icon_bg icon_add"> </i> <span><m:message
										code="button.add" /></span>
						</a></li>
					</c:if>
					<c:if test="${edit}">
						<li><a title="<m:message code="button.edit"/>"
							href="javascript:;" onclick="editDictInfo();"><i
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
						href="javascript:" onclick="showDictInfo();"> <i
							class="icon_bg icon_ckxq"></i> <span><m:message
									code="button.view" /></span>
					</a></li>
				</ul>
			</div>
			<!--功能按钮end-->
			<table id="dictInfoTable"></table>
			<div id="dictInfoPager"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		initDictTypeTree();
		initDictInfoTable();
		$('#dictTypeTree').height(605-$("#queryForm1").height()-80);
		window.parent.loadedMask();
	});

	var dictTypeTree;
	var className = "dark";
	//新增框
	var add_iframe_dialog;
	//编辑窗
	var edit_iframe_dialog;
	//展示窗
	var show_iframe_dialog;
	//字典信息
	var gridObj = {};

	function beforeEditName(treeId, treeNode) {
		if (treeNode.isParent) {
			showMessage("父节点不能编辑！");
		} else {
			var url = "dictType/toUpdateDictType.do?typeCode=" + treeNode.id;
			edit_iframe_dialog = new biz.dialog({
				id : $('<div id="editwindow_iframe"></div>').html('<iframe id="iframeEdit" name="iframeEdit" src="' + url + '" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
				modal : true,
				width : 550,
				height : 320,
				title : "<m:message code='type.editType' />"
			});
			edit_iframe_dialog.open();
		}
		return false;
	}

	//供子页面调用
	function closeEdit() {
		edit_iframe_dialog.close();
	}

	function beforeRemove(treeId, treeNode) {
		if (treeNode.isParent) {
			showMessage("父节点不能删除！");
			return false;
		} else {
			className = (className === "dark" ? "" : "dark");
			dictTypeTree.selectNode(treeNode);
			new biz.alert({
				type : "confirm",
				message : I18N.msg_del_confirm,
				title : I18N.promp,
				callback : function(result) {
					if (result) {
						$.ajax({
							url : "<m:url value='/dictType/deleteDictType.do'/>",
							data : {
								typeCode : treeNode.id
							},
							type : "POST",
							success : function(data, status, jqXHR) {
								var d = $.parseJSON(data);
								showMessage(d.msg,"","",function(){
									if(d.rs == "success"){
										dictTypeTree.removeNode(treeNode);
									} 
								});
							},
							error : function(data, status) {
							}
						});

					}
				}
			});
			return false;	
		}
	}

	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_" + treeNode.id).length > 0) {
			return;
		}

		if (treeNode.isParent) {//只有父节点有新增按钮
			var addStr = "<span class='button add' id='addBtn_" + treeNode.id
					+ "' title='增加' onfocus='this.blur();'></span>";
			sObj.append(addStr);
			var btn = $("#addBtn_" + treeNode.id);
			if (btn) {
				btn.bind("click", function() {
					add_iframe_dialog = new biz.dialog({
						id : $('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="dictType/toAddDictType.do" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
						modal : true,
						width : 550,
						height : 320,
						title : "<m:message code='type.addType' />"
					});
					add_iframe_dialog.open();
				});
			}
		}
	}

	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_" + treeNode.id).unbind().remove();
	}
	
	function addNode(obj){
		var parentNode = dictTypeTree.getNodeByParam("id", "0", null);
		var newNode = {id:obj.id, name:obj.name};
		dictTypeTree.addNodes(parentNode, newNode, true);
	}
	
	function modifyNode(obj){
		var node = dictTypeTree.getNodeByParam("id", obj.id, null);
		node.name = obj.name;
		dictTypeTree.updateNode(node);
	}

	function closeAdd() {
		add_iframe_dialog.close();
	}

	function initDictTypeTree() {
		$.ajax({
			url : "<m:url value='/dictType/initDictTypeTree.do'/>",
			data : {
				typeName : $('#typeName').val()
			},
			type : "POST",
			success : function(data, textStatus, jqXHR) {
				var setting_checkbox = {
					id : "#dictTypeTree",
					nodes : $.parseJSON(data), //数据节点指定     
					view : {
						addHoverDom : addHoverDom,
						removeHoverDom : removeHoverDom,
						selectedMulti : false
					},
					edit : {
						enable : true,
						editNameSelectAll : true
					},
					data : {
						simpleData : {
							enable : true
						}
					},
					callback : {
						beforeEditName : beforeEditName,
						beforeRemove : beforeRemove,
						onClick : treeOnClick
					}
				};
				dictTypeTree = new biz.tree(setting_checkbox);//创建树 
			}
		});
	}
	
	function treeOnClick(event, treeId, treeNode) {//获取预设的查询条件
		if(!treeNode.isParent){
			var typeCode = treeNode.id;
			$('#dictTypeCode').val(typeCode);
			dictInfoSearch();
		}
	}

	//初始化表格
	function initDictInfoTable() {
		gridObj = new biz.grid({
			id : "#dictInfoTable",/*html部分table id*/
			url : "dictInfo/listDictInfo.do",/*grid初始化请求数据的远程地址*/
			datatype : "json",/*数据类型，设置为json数据，默认为json*/
			colModel : [ {name : 'id',label : '<m:message code="dict.id" />',sortable : false,hidden : true,key : true}, 
			    {name : 'dictTypeCode',label : '<m:message code="dict.dictTypeCode" />',index : "DICT_TYPE_CODE",width:100}, 
			    {name : 'dictTypeName',label : '<m:message code="type.typeName" />',index : "dictTypeName",width:100}, 
				{name : 'dictName',label : '<m:message code="dict.dictName" />',index : "dict_Name",width:100}, 
				{name : 'dictCode',label : '<m:message code="dict.dictCode" />',index : "dict_Code",width:100}, 
				{name : 'states', hidden:true,label : '<m:message code="dict.states" />',index : "states",width:40,formatter : function(v, g, r) {if (v == "1") {return "<m:message code='dict.normal' />";} else {return "<m:message code='dict.nonormal' />";}}}, 
				{name : 'memo',label : '<m:message code="dict.memo" />',index : "memo",sortable : false,width:60}, 
				{name : 'creatorId',label : '<m:message code="dict.creatorId" />',hidden : true,index : "creator_Id"}, 
				{name : 'createTime',label : '<m:message code="dict.createTime" />',formatter : ICSS.utils.dateFormat,hidden : true,index : "create_Time"}, 
				{name : 'updaterId',label : '<m:message code="dict.updaterId" />',hidden : true,index : "updater_Id"}, 
				{name : 'updateTime',label : '<m:message code="dict.updateTime" />',hidden : true,index : "update_Time"} ],
			sortname : "update_Time",
			sortorder : "desc",
			pager : '#dictInfoPager' /*分页栏id*/,
			rowList : [ 10, 15, 50, 100 ],//每页显示记录数
			serializeGridData : function(postData) {//添加查询条件值
				var obj = getQueryCondition();
				$.extend(true, obj, postData);//合并查询条件值与grid的默认传递参数
				return obj;
			},
			rowNum : 10,//默认显示15条
			gridComplete : function() {
				//数据加载完毕后，调整样式
				var ids = gridObj.getDataIDs();
				if (ids == "") {
					$("#next_remote_prowed").attr("class", "ui-pg-button ui-corner-all ui-state-disabled");
					$("#last_remote_prowed").attr("class", "ui-pg-button ui-corner-all ui-state-disabled");
				}
			}
		});
	}

	/**
	 * 获取查询条件值
	 */
	function getQueryCondition() {
		var obj = {};
		ICSS.utils.addAttrToObject(obj, "dictName");
		ICSS.utils.addAttrToObject(obj, "dictCode");
		ICSS.utils.addAttrToObject(obj, "dictTypeCode");
		return obj;
	}
	
	function dictInfoSearch(isStayCurrentPage) {
		if(!isStayCurrentPage)gridObj.setGridParam({"page" : "1"});
		gridObj.trigger('reloadGrid');
	}
	 //重置查询表单
    function resetForm(formId){
		document.getElementById(formId).reset();
		initDictTypeTree();
		$("#"+formId+" input[type='hidden']").val(""); 
		dictInfoSearch();
	}
	function addDictInfo() {
		var nodes = dictTypeTree.getSelectedNodes();
		if(nodes.length != 1 || nodes[0].isParent){
			showMessage("<m:message code='grid.add.chooseType'/>");
			return;
		}
		
		var url = "dictInfo/toAddDictInfo.do?typeCode=" + nodes[0].id;
		add_iframe_dialog = new biz.dialog({
			id : $('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="' + url + '" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
			modal : true,
			width : 550,
			height : 320,
			title : "<m:message code='dict.zdbxxxz' />"
		});
		add_iframe_dialog.open();
	}

	//删除
	function batchDelete() {
		var ids = ICSS.utils.getSelectRowData("id");
		if (ids == "") {
			showMessage("<m:message code='grid.delete.chooseColAlert'/>");
			return;
		} else {
			new biz.alert({
				type : "confirm",
				message : I18N.msg_del_confirm,
				title : I18N.promp,
				callback : function(r) {
					if (r) {
						$.ajax({
							url : "dictInfo/deleteDictInfo.do?key=" + ids,
							cache : false,
							success : function(data, textStatus, jqXHR) {
								dictInfoSearch();
								showInfo("<m:message code='delete.success'/>", 3000);
							}
						});
					}
				}
			});
		}
	}

	//编辑
	function editDictInfo() {
		var key = ICSS.utils.getSelectRowData("id");
		if (key.indexOf(",") > -1 || key == "") {
			showMessage("<m:message code='grid.edit.chooseColAlert'/>");
			return;
		}
		var url = "dictInfo/toUpdateDictInfo.do?key=" + key;
		edit_iframe_dialog = new biz.dialog({
					id : $('<div id="editwindow_iframe"></div>').html('<iframe id="iframeEdit" name="iframeEdit" src="' + url + '" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
					modal : true,
					width : 550,
					height : 320,
					title : "<m:message code='dict.zdbxxbj' />"
				});
		edit_iframe_dialog.open();
	}

	//展示
	function showDictInfo() {
		var key = ICSS.utils.getSelectRowData("id");
		if (key.indexOf(",") > -1 || key == "") {
			showMessage("<m:message code='grid.view.chooseColAlert'/>");
			return;
		}
		
		var url = "dictInfo/toShowDictInfo.do?key=" + key;
		show_iframe_dialog = new biz.dialog({
					id : $('<div id="showwindow_iframe"></div>').html('<iframe id="iframeShow" name="iframeShow" src="' + url + '" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
					modal : true,
					width : 550,
					height : 320,
					title : "<m:message code='dict.zdbxxxk' />"
				});
		show_iframe_dialog.open();
	}

	function closeShow() {
		show_iframe_dialog.close();
	}
</script>
</html>