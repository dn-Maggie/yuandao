<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML  >
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<title><m:message code="personrole.list.title" /></title>
<script type="text/javascript">
var gridObj = {};
	$(function(){
  		gridObj = new biz.grid({
            id:"#remote_rowed",/*html部分table id*/
            url: "<m:url value='/personrole/listPersonrole.do'/>",/*grid初始化请求数据的远程地址*/
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
           	sortname:"SERIAL_INDEX",
           	sortorder:"asc",
           	//navtype:"top" /*导航栏类型*/,
           	//height: gridHeight,
           	pager: '#remote_prowed' /*分页栏id*/,
     		rowList:[10,20,30,50,100,200],//每页显示记录数
    		rowNum:10,//默认显示15条
            colModel:[
				{name : "roleId",formatter:"showlink",formatoptions:{baseLinkUrl:"javascript:;",click:view},label:"<m:message code='personrole.roleId'/>"},				
				{name : "createTime",label:"<m:message code='personrole.createTime'/>"},				
				{name : "userId",label:"<m:message code='personrole.userId'/>"}				
           	],
           	serializeGridData:function(postData){//添加查询条件值
				var obj = getQueryCondition();
    			$ .extend(true,obj,postData);//合并查询条件值与grid的默认传递参数
    			return obj;
    		}
      });
        
    });

    /**
    * 获取查询条件值
    */
    function getQueryCondition(){
        var obj = {};
		//addAttrToObject(obj,"moduleName");
		return obj;
    }

	

    //新增的弹出框
	var add_iframe_dialog;
	//修改的弹出框
   	var edit_iframe_dialog;
	//查看的弹出框
	var show_iframe_dialog;
  	
  	function add(){
  	//xin zeng iframe 弹出框
		var url="<m:url value='/module/toAddModule.do'/>";
		add_iframe_dialog = new biz.dialog({
			id:$('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: 800,
			height: 235,
			title: "<m:message code='module.title' />"
		});
		add_iframe_dialog.open();
  	}
  	
  	//关闭新增页面，供子页面调用
  	function closeAdd(){
		add_iframe_dialog.close();
  	}
  	
    function edit(){
		var key = ICSS.utils.getSelectRowData("id");
		if(key.indexOf(",")>-1||key==""){
			showMessage("<m:message code='grid.edit.chooseColAlert'/>");
			return ;
		}
		var url="<m:url value='/module/toEditModule.do'/>?key="+key;
		edit_iframe_dialog = new biz.dialog({
		 	id:$('<div id="editwindow_iframe"></div>').html('<iframe id="iframeEdit" name="iframeEdit" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: 800,
			height: 235,
			title: "<m:message code='module.title' />"
		});
  		edit_iframe_dialog.open();
    }
    
    //关闭编辑页面，供子页面调用
    function closeEdit(){
    	edit_iframe_dialog.close();
    }
    
    function show(){
    	var key = ICSS.utils.getSelectRowData("id");
		if(key.indexOf(",")>-1||key==""){
			showMessage("<m:message code='grid.view.chooseColAlert'/>");
			return ;
		}
		var url="<m:url value='/module/toShowModule.do'/>?key="+key;
		show_iframe_dialog = new biz.dialog({
		 	id:$('<div id="showwindow_iframe"></div>').html('<iframe id="iframeShow" name="iframeShow" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: 800,
			height: 235,
			title: "<m:message code='module.title' />"
		});
  		show_iframe_dialog.open();
    }
    
    //关闭查看页面，供子页面调用
    function closeShow(){
    	show_iframe_dialog.close();
    }
    
    //查询Grid数据
    function doSearch(isStayCurrentPage){
    	if(!isStayCurrentPage)gridObj.setGridParam({"page":"1"});
    	gridObj.trigger('reloadGrid');
    }
    //重置查询表单
    function resetForm(formId){
		document.getElementById(formId).reset();
	}
    
    //删除
    function batchDelete(){
    	var ids = ICSS.utils.getSelectRowData("id");
    	if(ids==""){
    		showMessage("<m:message code='grid.delete.chooseColAlert'/>");
    		return ;
    	}else{
    		new biz.alert({type:"confirm",message:I18N.msg_del_confirm,title:I18N.promp,callback:function(result){
    			if(result){
    				$ .ajax({
        				url: "<m:url value='/module/deleteModule.do'/>?key="+ids,
        				cache:false,
        				success: function(data, textStatus, jqXHR){
        					doSearch();
    						showInfo("<m:message code='delete.success'/>",3000);
        				}
        			});
    			}
    		}}) ;   
    	}
    }
    </script>
</head>
<body>

	<div class="main  choice_box">
		<div id="conditions"
			class="ui-table ui-widget ui-corner-all ui-margin"
			style="display: block;">
			<form id="queryForm">
				<div class="equal">
					<div class="row">
						<!-- 					<div class="cell" style="width:28%;margin-top:5px"> -->
						<%-- 						<div class="labelcell"><m:message code="module.moduleName"/>:</div> --%>
						<!-- 						<div class="valuecell"> -->
						<%-- 							<input id="moduleName" name="moduleName" type="text" class="text" value="<c:out value='${module.moduleName}'/>"/> --%>
						<!-- 						</div> -->
						<!-- 					</div> -->
						<div class="nav" style="background: none; margin-top: 5px">
							<button class="btnBox btn-primary" type="button"
								style="text-align: right;" onclick="doSearch();">
								<i class="icon-white icon-search"></i><span
									style="margin-left: 1px; margin-right: 1px">查询</span>
							</button>
							<button class="btnBox btn-primary" type="reset"
								style="text-align: right;">
								<i class="icon-white icon-reset"></i><span
									style="margin-left: 1px; margin-right: 1px">重置</span>
							</button>
						</div>
					</div>
				</div>
			</form>

			<div class="ti_box2 ">
				<c:if test="${add}">
					<a title="<m:message code="button.add"/>" href="#" onclick="add();"
						class="tool_btn"> <span> <i class="back_icon add_icon"></i>
							<em class="btn_con"><m:message code="button.add" /></em>
					</span>
					</a>
				</c:if>
				<c:if test="${edit}">
					<a title="<m:message code="button.edit"/>" href="#"
						onclick="edit();" class="tool_btn"> <span> <i
							class="back_icon edit_icon"></i> <em class="btn_con"><m:message
									code="button.edit" /></em>
					</span>
					</a>
				</c:if>
				<c:if test="${delete}">
					<a title="<m:message code="button.delete"/>" href="#"
						onclick="batchDelete();" class="tool_btn"> <span> <i
							class="back_icon delete_icon"></i> <em class="btn_con"><m:message
									code="button.delete" /></em>
					</span>
					</a>
				</c:if>
				<a title="<m:message code="button.view"/>" href="#"
					onclick="show();" class="tool_btn"> <span> <i
						class="back_icon show_icon"></i> <em class="btn_con"><m:message
								code="button.view" /></em>
				</span>
				</a>
			</div>

			<table id="remote_rowed"></table>
			<div id="remote_prowed"></div>
		</div>
	</div>


</body>
</html>
