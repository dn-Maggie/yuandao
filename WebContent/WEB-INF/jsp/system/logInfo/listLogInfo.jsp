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
<title><m:message code="logInfo.list.title" /></title>
</head>
<body>

	<div class="container" id="container">
		<!--总div -->
		<form id="queryForm">
			<!-- 查询区 表单 -->
			<div class="search border-bottom">
				<ul>
					<li style="width: 310px; float: left;"><span>关键字:</span> <input
						id="actorName" type="text"
						placeholder="<m:message code="logInfo.queryForm.actorName"/>"
						class="search_choose100" value="" name="actorName"
						autocomplete="off"> <input id="actResult" type="text"
						placeholder="<m:message code="logInfo.queryForm.actResult" />"
						class="search_choose100" value="" name="actResult"
						autocomplete="off"></li>
					<li class="date_area"><span>操作时间:</span>
						<div class="time_bg">
							<input type="text" name="actTime" class="search_time150"
								id="actTime"> <i class="search_time_ico2"></i>
						</div> <i>至</i>
						<div class="time_bg">
							<input type="text" name="endTime" class="search_time150"
								id="endTime"> <i class="search_time_ico2"></i>
						</div></li>
					<li><span><m:message code="logInfo.queryForm.actType" />:</span><select
						id="actType" name="actType" class="search_select"></select></li>
					<!--下拉 -->
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

					<li><a title="<m:message code="button.delete"/>"
						href="javascript:;" onclick="batchDelete();"> <i
							class="icon_bg icon_del"></i> <span><m:message
									code="button.delete" /></span>
					</a></li>

					<li><a title="<m:message code="button.view"/>"
						href="javascript:;" onclick="view();"> <i
							class="icon_bg icon_ckxq"></i> <span><m:message
									code="button.view" /></span>
					</a></li>
				</ul>
			</div>
			<!--功能按钮end-->
			<div class="listtable_box">
				<!--此处放表格-->
				<table id="logInfoTable"></table>
				<div id="logInfoPager"></div>
			</div>
		</div>
		<!--功能列表区end-->
	</div>
</body>
</html>
<script type="text/javascript" charset="UTF-8">  
 
	
	var logInfoGrid = {};
	$(function() {
		logInfoGrid = new biz.grid({
            id:"#logInfoTable",/*html部分table id*/
            url: "logInfo/listLogInfo.do",/*grid初始化请求数据的远程地址*/
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
            shrinkToFit:false,
            rownumbers: true, //是否显示行号
            
            colModel:[
				{name : "id",hidden:true},
				//{name : "userId",label:"<m:message code="logInfo.queryResult.userId"/>",index:'USER_ID',width:266},
				{name : "actorName",label:"<m:message code="logInfo.queryResult.actorName"/>",index:'ACTOR_NAME',width:100},
				{name : "actType",label:"<m:message code="logInfo.queryResult.actType"/>",index:'ACT_TYPE',width:100},
				{name : "actTime",label:"<m:message code="logInfo.queryResult.actTime"/>",index:'ACT_TIME'},
				{name : "actResult",label:"<m:message code="logInfo.queryResult.actResult"/>",index:'ACT_RESULT',width:70},
				{name : "actAction",label:"<m:message code="logInfo.queryResult.actAction"/>",index:'ACT_ACTION'},
				{name : "actModule",label:"<m:message code="logInfo.queryResult.actModule"/>",index:'ACT_MODULE',width:351}
           	],
           	sortname:"ACT_TIME",
           	sortorder:"desc",
           	//navtype:"top" /*导航栏类型*/,
           	//height: gridHeight,
           	pager: '#logInfoPager' /*分页栏id*/,
     		rowList:[10,20,30,50,100,200],//每页显示记录数
           	serializeGridData:function(postData){//添加查询条件值
				var obj = getQueryCondition();
    			$.extend(true,obj,postData);//合并查询条件值与grid的默认传递参数
    			return obj;
    		},
    		rowNum:10//默认显示10条
      });
		
		   new biz.datepicker({
				id : "#actTime",
				maxDate:'#F{$dp.$D(\'endTime\',{d:0});}',
				dateFmt:'yyyy-MM-dd'
			});
		    
		    new biz.datepicker({
				id : "#endTime",
				minDate:'#F{$dp.$D(\'actTime\',{d:0});}',
				dateFmt:'yyyy-MM-dd'
			});
		    
		  //操作类型下拉框
			new biz.select({   
				id:"#actType",
				url : "<c:url value='/logInfo/getActTypeByEnum.do'/>",
				addEmptyItem:true
			});
		
			window.parent.loadedMask();
    });
    
    /**
    * 获取查询条件值
    */
    function getQueryCondition(){
        var obj = {};
        addAttrToObject(obj,"actorName");
        addAttrToObject(obj,"actType");
        addAttrToObject(obj,"actTime");
        addAttrToObject(obj,"endTime");
        addAttrToObject(obj,"actResult");
		return obj;
    }
    
   
    
	//查看的弹出框
	var view_iframe_dialog;
	function view(){
    	rowid = logInfoGrid.getGridParam("selarrrow");
		if(rowid == null || rowid.length == 0){
			showInfo("<m:message code='grid.view.chooseColAlert'/>",3000);
			return ;
		}else if(rowid.length > 1){
			showInfo("<m:message code='grid.view.onlyOne'/>",3000);
			return ;
		}
		var id = logInfoGrid.getCell(rowid[0],"id");
		var url="logInfo/toShowLogInfo.do?id="+id;
		view_iframe_dialog = new biz.dialog({
		 	id:$('<div id="viewwindow_iframe"></div>').html('<iframe id="iframeView" name="iframeView" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: 900,
			height: 360,
			title: "<m:message code="button.view" />"
		});
  		view_iframe_dialog.open();
    }

    function closeView(){
    	view_iframe_dialog.close();
    }
  	
    function doSearch(){
    	logInfoGrid.setGridParam({"page":"1"});
    	logInfoGrid.trigger('reloadGrid');
    }
	//重置查询表单
	function resetForm(formId) {
		document.getElementById(formId).reset();
		doSearch();
	}
    
    //删除
    function batchDelete(rowid){
    	rowid = logInfoGrid.getGridParam("selarrrow");
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
					var id = logInfoGrid.getCell(rowid[i],'id');
					if(i>0){
						ids +=',';
					}
					ids += id;
				}
				$.ajax({
					url: "<c:url value='/logInfo/deleteLogInfo.do'/>",
					type: "post",
					data: {ids:ids},
					dataType: "json",
					success: function(data){
						if(data.msg == "成功"){
							doSearch() ;
						}
					}
				});
			}
		});
    }
    </script>