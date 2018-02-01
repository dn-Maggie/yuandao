<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<base href="<%=basePath%>">
<%@ include file="../../common/header.jsp"%>
<title><m:message code="settingInfo.list.title" /></title>
</head>
<body>
	<div class="container" id="container">
		<!--总div -->
		<form id="queryForm">
			<!-- 查询区 表单 -->
			<div class="search border-bottom">
				<ul>
					<li style="width: 310px; float: left;"><span>关键字:</span> <input
						id="varNameCn" type="text"
						placeholder="<m:message code="settingInfo.queryForm.varNameCn" />"
						class="search_choose100" value="" name="varNameCn"
						autocomplete="off"> <input id="variableName" type="text"
						placeholder="<m:message code="settingInfo.queryForm.variableName" />"
						class="search_choose100" value="" name="variableName"
						autocomplete="off"></li>

					<li class="w220"><input type="reset" value="重置"
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
						href="javascript:" onclick="view();"> <i
							class="icon_bg icon_ckxq"></i> <span><m:message
									code="button.view" /></span>
					</a></li>
				</ul>
			</div>
			<!--功能按钮end-->
			<div class="listtable_box">
				<!--此处放表格-->
				<table id="settingInfoTable"></table>
				<div id="settingInfoPager"></div>
			</div>
		</div>
		<!--功能列表区end-->
	</div>
</body>
</html>
<script type="text/javascript" charset="UTF-8">  
	var settingInfoGrid = {};
	$(function() {
		settingInfoGrid = new biz.grid({
            id:"#settingInfoTable",/*html部分table id*/
            url: "settingInfo/listSettingInfo.do",/*grid初始化请求数据的远程地址*/
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
            
            colModel:[
				{name : "id",hidden:true}, 
				{name : "varNameCn",label:"<m:message code="settingInfo.queryResult.varNameCn"/>",index:'VAR_NAME_CN'},
				{name : "variableName",label:"<m:message code="settingInfo.queryResult.variableName"/>",index:'VARIABLE_NAME'},
				{name : "variableValue",label:"<m:message code="settingInfo.queryResult.variableValue"/>",index:'VARIABLE_VALUE'}
				
           	],
           	sortname:"VARIABLE_NAME",
           	sortorder:"asc",
           	
           	rownumbers: true, //是否显示行号
           	//navtype:"top" /*导航栏类型*/,
           	//height: gridHeight,
           	pager: '#settingInfoPager' /*分页栏id*/,
     		rowList:[10,20,30,50,100,220],//每页显示记录数
           	serializeGridData:function(postData){//添加查询条件值
				var obj = getQueryCondition();
    			$.extend(true,obj,postData);//合并查询条件值与grid的默认传递参数
    			return obj;
    		},
    		rowNum:10//默认显示10条
      });
		window.parent.loadedMask();
    });
    
    /**
    * 获取查询条件值
    */
    function getQueryCondition(){
        var obj = {};
        addAttrToObject(obj,"variableName");
        addAttrToObject(obj,"varNameCn");
		return obj;
    }
    
 
    
    //新增的弹出框
	var add_iframe_dialog;
	function add(){
		add_iframe_dialog = new biz.dialog({
			id:$('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="settingInfo/toAddSettingInfo.do" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: 600,
			height:220,
			title: "添加功能信息"
		});
		add_iframe_dialog.open();
  	}
  	
  	function closeAdd(){
		add_iframe_dialog.close();
  	}
  	
	//修改的弹出框
   	var edit_iframe_dialog;
    function edit(rowid){
    	rowid = settingInfoGrid.getGridParam("selarrrow");
		if(rowid == null || rowid.length == 0){
			showInfo("<m:message code='grid.edit.chooseColAlert'/>",3000);
			return ;
		}else if(rowid.length > 1){
			showInfo("<m:message code='grid.edit.onlyOne'/>",3000);
			return ;
		}
		var id = settingInfoGrid.getCell(rowid[0],"id");
		var url="settingInfo/toUpdateSettingInfo.do?id="+id;
		edit_iframe_dialog = new biz.dialog({
		 	id:$('<div id="editwindow_iframe"></div>').html('<iframe id="iframeEdit" name="iframeEdit" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: 600,
			height: 220,
			title: "编辑功能信息"
		});
  		edit_iframe_dialog.open();
    }

    function closeEdit(){
    	edit_iframe_dialog.close();
    }
    
	//查看的弹出框
	var view_iframe_dialog;
	function view(){
    	rowid = settingInfoGrid.getGridParam("selarrrow");
		if(rowid == null || rowid.length == 0){
			showInfo("<m:message code='grid.view.chooseColAlert'/>",3000);
			return ;
		}else if(rowid.length > 1){
			showInfo("<m:message code='grid.view.onlyOne'/>",3000);
			return ;
		}
		var id = settingInfoGrid.getCell(rowid[0],"id");
		var url="settingInfo/toShowSettingInfo.do?id="+id;
		view_iframe_dialog = new biz.dialog({
		 	id:$('<div id="viewwindow_iframe"></div>').html('<iframe id="iframeView" name="iframeView" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: 600,
			height: 220,
			title: "功能信息详情"
		});
  		view_iframe_dialog.open();
    }

    function closeView(){
    	view_iframe_dialog.close();
    }
  	
    function doSearch(){
    	settingInfoGrid.setGridParam({"page":"1"});
    	settingInfoGrid.trigger('reloadGrid');
    }
    
 // 重置查询表单
    function resetForm(formId) {
    	document.getElementById(formId).reset();
    	doSearch();
    }
    
    //删除
    function batchDelete(rowid){
    	rowid = settingInfoGrid.getGridParam("selarrrow");
    	if(rowid == null || rowid.length == 0){
			showInfo("<m:message code='grid.delete.chooseColAlert'/>",3000);
			return ;
		}
    	new biz.alert({type:"confirm",message:I18N.msg_del_confirm,
			title:I18N.promp,callback:function(result){
	    		if (!result) {
	    			return;
	    		}
				var ids="";
				for(var i=0; i < rowid.length;i++){
					var id = settingInfoGrid.getCell(rowid[i],'id');
					if(i>0){
						ids +=',';
					}
					ids += id;
				}
				$.ajax({
					url: "<c:url value='/settingInfo/deleteSettingInfo.do'/>",
					type: "post",
					data: {ids:ids},
					dataType: "json",
					success: function(data){
						if(data.msg == "成功"){
							doSearch();
						}
					}
				});
			}
		});
    }
    </script>