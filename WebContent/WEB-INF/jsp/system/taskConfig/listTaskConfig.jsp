<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML  >
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<title></title>
<script type="text/javascript">
var gridObj = {};
	$(function(){
  		gridObj = new biz.grid({
            id:"#remote_rowed",/*html部分table id*/
            url: "<m:url value='/taskConfig/listTaskConfig.do'/>",/*grid初始化请求数据的远程地址*/
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
           	sortname:"task_Order",
           	sortorder:"asc",
           	//navtype:"top" /*导航栏类型*/,
           	//height: gridHeight,
           	pager: '#remote_prowed' /*分页栏id*/,
     		rowList:[10,15,50,100],//每页显示记录数
    		rowNum:10,//默认显示15条
    		singleselect : true,
            colModel:[
				{name : "id",hidden : true,key : true,label:"任务数据ID",index : "ID"},				
				{name : "taskName",label:"任务名称",index : "TASK_NAME"},	
				{name : "className",label:"执行类名",index : "CLASS_NAME"},	
				{name : "classMethod",label:"执行方法",index : "CLASS_METHOD"},				
				{name : "taskType",label:"任务类型",align : "center",index : "TASK_TYPE",width:80,
					formatter:function(cellvalue, options, cellObject){
					if (cellvalue == "0") {
						return '循环';
					} else  {
						return '定时';
					}
				}},				
				{name : "describe",label:"任务描述",index : "DESCRIBE"},				
				{name : "taskStatus",label:"任务状态",index : "TASK_STATUS",align : "center",width:80, formatter:GridColModelForMatter.enableStates},				
				{name : "startTime",label:"开始时间",index : "START_TIME",width:180},				
				{name : "septYears",label:"年",index : "SEPT_YEARS",width:30},				
				{name : "septMonths",label:"月",index : "SEPT_MONTHS",width:30},				
				{name : "septWeeks",label:"周",index : "SEPT_WEEKS",width:30},				
				{name : "septDays",label:"日",index : "SEPT_DAYS",width:30},				
				{name : "septHours",label:"时",index : "SEPT_HOURS",width:30},				
				{name : "septMinutes",label:"分",index : "SEPT_MINUTES",width:30},				
				{name : "septSeconds",label:"秒",index : "SEPT_SECONDS",width:30},				
				{name : "taskOrder",label:"顺序",index : "TASK_ORDER",width:40}				
           	],
           	serializeGridData:function(postData){//添加查询条件值
				var obj = getQueryCondition();
    			$ .extend(true,obj,postData);//合并查询条件值与grid的默认传递参数
    			return obj;
    		}
      });
        
	new biz.datepicker({
  			id : "#startDate",
  			maxDate:'#F{$dp.$D(\'endDate\',{d:0});}',
  			dateFmt:'yyyy-MM-dd'
  		});
  	    
  	    new biz.datepicker({
  			id : "#endDate",
  			minDate:'#F{$dp.$D(\'startDate\',{d:0});}',
  			dateFmt:'yyyy-MM-dd'
  		});
  	    
  		window.parent.loadedMask();
    });

 

    //新增的弹出框
	var add_iframe_dialog;
	//修改的弹出框
   	var edit_iframe_dialog;
	//查看的弹出框
	var show_iframe_dialog;
  	
  	function add(){
  	//xin zeng iframe 弹出框
		var url="<m:url value='/taskConfig/toAddTaskConfig.do'/>";
		add_iframe_dialog = new biz.dialog({
			id:$('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: 700,
			height: 450,
			title: "系统定时任务配置增加"
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
			showMessage("请选择一条数据！");
			return ;
		}
		var url="<m:url value='/taskConfig/toEditTaskConfig.do'/>?key="+key;
		edit_iframe_dialog = new biz.dialog({
		 	id:$('<div id="editwindow_iframe"></div>').html('<iframe id="iframeEdit" name="iframeEdit" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: 700,
			height: 450,
			title: "系统定时任务配置编辑"
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
			showMessage("请选择一条数据！");
			return ;
		}
		var url="<m:url value='/taskConfig/toShowTaskConfig.do'/>?key="+key;
		show_iframe_dialog = new biz.dialog({
		 	id:$('<div id="showwindow_iframe"></div>').html('<iframe id="iframeShow" name="iframeShow" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: 700,
			height: 450,
				title: "系统定时任务配置详情"
		});
  		show_iframe_dialog.open();
    }
    
    //关闭查看页面，供子页面调用
    function closeShow(){
    	show_iframe_dialog.close();
    }
    /**
    * 获取查询条件值
    */
    function getQueryCondition(){
       var obj = {};
		addAttrToObject(obj, "taskName");
		addAttrToObject(obj, "className");
		addAttrToObject(obj, "classMethod");
		addAttrToObject(obj, "taskStatus");
		return obj;
    }
    //查询Grid数据
    function doSearch(isStayCurrentPage){
    	if(!isStayCurrentPage)gridObj.setGridParam({"page":"1"});
    	gridObj.trigger('reloadGrid');
    }
    //重置查询表单
    function resetForm(formId){
		document.getElementById(formId).reset();
		doSearch();
	}
    
    //删除
    function batchDelete(){
    	var ids = ICSS.utils.getSelectRowData("id");
    	if(ids==""){
    		showMessage("请至少选择一条数据！");
    		return ;
    	}else{
    		new biz.alert({type:"confirm",message:I18N.msg_del_confirm,title:I18N.promp,callback:function(result){
    			if(result){
    				$ .ajax({
        				url: "<m:url value='/taskConfig/deleteTaskConfig.do'/>?key="+ids,
        				cache:false,
        				success: function(data, textStatus, jqXHR){
        					doSearch();
    						showInfo("删除成功",3000);
        				}
        			});
    			}
    		}}) ;   
    	}
    }
    
 // 立即执行
    function exe() {
    	var key = ICSS.utils.getSelectRowData("id");
		if(key.indexOf(",")>-1||key==""){
			showMessage("请选择一条数据！");
			return ;
		}
    	showInfo("立即执行启动成功!", 3000);
    	$.ajax({
    		url : baseUrl + "/taskConfig/exeTaskConfig.do?key=" + key,
    		cache : false,
    		success : function(data, textStatus, jqXHR) {
    			doSearch();
    			$("#modelDiv").hide();
    			showInfo("执行成功", 3000);
    		}
    	});
    }
    // 停用启用
    function updateStatus(status) {
    	var key = ICSS.utils.getSelectRowData("id");
		if(key.indexOf(",")>-1||key==""){
			showMessage("请选择一条数据！");
			return ;
		}
    	$.ajax({
    		url : baseUrl + "/taskConfig/updateStatus.do?key=" + key
    				+ "&taskStatus=" + status,
    		cache : false,
    		success : function(data, textStatus, jqXHR) {
    			doSearch();
    			showInfo("操作成功", 3000);
    		}
    	});
    }
    
    </script>
</head>
<body style="height: 100%;">

	<div class="main  choice_box">
		<form id="queryForm">
			<!-- 查询区 表单 -->
			<div class="search border-bottom">
				<ul>
					<li style="width: 430px; float: left;"><span>关键字:</span> <input
						id="taskName" type="text" placeholder="任务名称"
						class="search_choose100" value="" name="taskName"
						autocomplete="off"> <input id="className" type="text"
						placeholder="执行类名" class="search_choose100" value=""
						name="className" autocomplete="off"> <input
						id="classMethod" type="text" placeholder="执行方法"
						class="search_choose100" value="" name="classMethod"
						autocomplete="off"></li>
					<li class="w200"><span>任务状态:</span> <select id="taskStatus"
						name="taskStatus" class="search_choose100">
							<option value="">全部</option>
							<option value="1">启用</option>
							<option value="0">停用</option>
					</select></li>
					<li><input type="reset" class="reset_btn"
						onclick="resetForm('queryForm')" value="重置">
					<!-- 重置 --> <input type="button" class="search_btn mr22 "
						onclick="doSearch();" value="查询"></li>
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
					<li><a title="启用" href="javascript:"
						onclick="updateStatus(1);"> <i class="back_icon edit_icon"></i>
							<span>启用</span>
					</a></li>
					<li><a title="停用" href="javascript:"
						onclick="updateStatus(0);"> <i class="back_icon edit_icon"></i>
							<span>停用</span>
					</a></li>
					<li><a title="立即执行" href="javascript:" onclick="exe();"> <i
							class="back_icon resources_icon"></i> <span>立即执行</span>
					</a></li>

				</ul>
			</div>

			<!--功能按钮end-->
			<div class="listtable_box">
				<!--此处放表格-->
				<table id="remote_rowed"></table>
				<div id="remote_prowed"></div>
			</div>
		</div>
	</div>
</body>
</html>
