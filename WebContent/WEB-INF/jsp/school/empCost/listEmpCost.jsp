<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<style type="text/css">
td>.editable {
	width: 90%;
}
</style>
<script type="text/javascript">
var gridObj = {};
	$(function(){
  		gridObj = new biz.grid({
            id:"#remote_rowed",/*html部分table id*/
            url: "<m:url value='/empCost/listEmpCost.do'/>",/*grid初始化请求数据的远程地址*/
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
           	sortname:"month",
           	sortorder:"asc", 
           	footerrow:true,//页脚汇总行
           	emptyrecords: "无记录可显示",
           	pager: '#remote_prowed' /*分页栏id*/,
     		rowList:[10,15,50,100],//每页显示记录数
    		rowNum:10,//默认显示15条
    		navtype:"top" /*导航栏类型*/,
    		navopt:{edit : false,add : false,del : false,reloadAfterSubmit:true},
            colModel:[
				{name : "id",hidden : true, key : true,label:"主键",index : "id"},				
		 		{name : "empDept",label:"所属部门",width:"6",index : "empDept",
					formatter:function(cellvalue, options, rowObject){
		 				 if (cellvalue=="") {
		 				 	return "所有部门"
		 				 }else {
		 					 return cellvalue;
		 				 }
	 			}},
	 			{name : "empNickName",label:"责任人",width:"6",index : "empNickName",
					formatter:function(cellvalue, options, rowObject){
		 				 if (cellvalue=="") {
		 				 	return "所有人"
		 				 }else {
		 					 return cellvalue;
		 				 }
	 			}},
 		        {name : "costMoney",label:"金额",width:"6",index : "cost_money"},
 		        {name : "costType",label:"类型",width:"6",index : "cost_type",
 		        	formatter:function(cellvalue, options, rowObject){
	 				 if (cellvalue=="1") {
		 				 	return "公摊成本"
		 				 }else if (cellvalue=="2") {
		 					 return "个人成本";
		 				 }
	 			}},
 		        {name : "costContent",label:"成本详细",width:"6",index : "cost_content"},
 		       	{name : "month",label:"统计月份",width:"6",index : "month",
 		    	  formatter:'date',formatoptions: {newformat:'Y-m'}, 	
 		       	}, 
 		      
           	],
           	serializeGridData:function(postData){//添加查询条件值，把数据进行序列化
				var obj = getQueryCondition();
    			$ .extend(true,obj,postData);//合并查询条件值与grid的默认传递参数
    			return obj;
    		},
    		gridComplete:function(){
    			$(".ui-jqgrid-sdiv").show();
           		//如果需要统计则需要定义
               $(this).footerData("set",
            		   {"所属部门":"合计",
            	   		"costMoney":$(this).getCol("costMoney",false,"sum"),
            	   		});
	 		},
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
    });

  	
    /**
    * 获取查询条件值
    */
    function getQueryCondition(){
       var obj = {};
		jQuery.each($("#queryForm").serializeArray(),function(i,o){
        	if(o.value){
        		obj[o.name] = o.value;
        	}
        });
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
        				url: "<m:url value='/empCost/deleteEmpCost.do'/>?key="+ids,
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
    
    //新增的弹出框
	var add_iframe_dialog;
	function add(){
	  	//xin zeng iframe 弹出框
			var url="<m:url value='/empCost/toAddEmpCost.do'/>";
			add_iframe_dialog = new biz.dialog({
				id:$('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
				modal: true,
				width: $(window).width()*0.6,
				height:$(window).height()*0.8,
				title: "新增成本信息"
			});
			add_iframe_dialog.open();
	  	}
	//关闭新增页面，供子页面调用
  	function closeAdd(){
		add_iframe_dialog.close();
  	}
	
  	var edit_iframe_dialog;
	function edit(){
		var key = ICSS.utils.getSelectRowData("id");
		if(key.indexOf(",")>-1||key==""){
			showMessage("请选择一条数据！");
			return ;
		}
	  	//xin zeng iframe 弹出框
			var url="<m:url value='/empCost/toEditEmpCost.do'/>?key="+key;
			edit_iframe_dialog = new biz.dialog({
				id:$('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
				modal: true,
				width: $(window).width()*0.6,
				height:$(window).height()*0.8,
				title: "修改成本信息"
			});
			edit_iframe_dialog.open();
	  	}
	//关闭新增页面，供子页面调用
  	function closeEdit(){
  		edit_iframe_dialog.close();
  	}
	
    </script>
</head>
<body style="height: 100%;">
	<div class="main  choice_box">
		<form id="queryForm">
			<!-- 查询区 表单 -->
			<div class="search border-bottom">
				<ul>
					<li ><span>关键字:</span> 
					<input type="text" name="empNickName"
						id="nickName" class="search_choose100" placeholder="员工昵称">
						</li>
					<!-- 输入框-->
					<li><select class="search_choose"
						name="empDept" id="edit_dept" mainid="empDept">
							<option value="">---请选择---</option>
							<c:forEach var="org" items="${org}">
								<option value="${org.orgName}"><c:out
										value="${org.orgName}"></c:out></option>
							</c:forEach>
					</select> <span>所在部门:</span></li>
					<li  class="date_area"><span>月份:</span>
						<div class="time_bg">
							<input id="startDate" type="text" class="search_time150"
								name="propsMap['startDate']"> <i
								class="search_time_ico1"></i>
						</div> <i>至</i>
						<div class="time_bg">
							<input id="endDate" type="text" class="search_time150"
								name="propsMap['endDate']"> <i class="search_time_ico1"></i>
						</div>
					</li>
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
					<li><a title="<m:message code="button.delete"/>"
						href="javascript:;" onclick="batchDelete();"> <i
							class="icon_bg icon_del"></i> <span><m:message
									code="button.delete" /></span>
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
