<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<title></title>

</head>
<body style="height: 100%;">

	<div class="main  choice_box">
		<form id="queryForm">
			<!-- 查询区 表单 -->
			<div class="search border-bottom">
				<ul>
					<li><input type="text" name="enterName" id="enterName"
						class="search_choose"> <span>申请人:</span></li>
					<!-- 输入框-->
					<li style="width: 180px;"><select class="search_choose"
						name="expenseType" id="expenseType" mainid="expenseType"
						style="width: 100px;">
							<option value="">所有</option>
							<option value="个人报销">个人报销</option>
							<option value="集体报销">集体报销</option>
					</select><span>报销类型</span></li>
					<!-- 输入框-->
					<li class="date_area"><span>申请日期:</span>
						<div class="time_bg">
							<input id="startDate" type="text" class="search_time150"
								name="propsMap['startDate']"> <i
								class="search_time_ico1" onclick="WdatePicker({el:'startDate'})"></i>
						</div> <i>至</i>
						<div class="time_bg">
							<input id="endDate" type="text" class="search_time150"
								name="propsMap['endDate']"> <i class="search_time_ico1"
								onclick="WdatePicker({el:'endDate'})"></i>
						</div></li>

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
					<li><a title="<m:message code="button.view"/>"
						href="javascript:" onclick="show();"> <i
							class="icon_bg icon_ckxq"></i> <span><m:message
									code="button.view" /></span>
					</a></li>
				</ul>
			</div>

			<!--功能按钮end-->
			<div class="listtable_box">

				<table id="remote_rowed"></table>
				<div id="remote_prowed"></div>

			</div>
		</div>
	</div>
	<script type="text/javascript">
	var gridObj = {};
	var expenseObj = {}
	$(function(){
  		gridObj = new biz.grid({
            id:"#remote_rowed",/*html部分table id*/
            url: "<m:url value='/expenseAccount/listexpenseRecords.do'/>",/*grid初始化请求数据的远程地址*/
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
           	sortname:"enter_date",
           	sortorder:"desc",
        	multiselect:true,
           	multiboxonly:true,
           	pager: '#remote_prowed' /*分页栏id*/,
     		rowList:[10,20,50,100],//每页显示记录数
    		rowNum:10,//默认显示15条
            colModel:[
				{name : "id",hidden : true,key : true,label:"",index : "id"},	
				{name : "expenseType",label:"报销单类型",width:15},	
				{name : "deptName",label:"所在部门",width:15},	
				{name : "enterName",label:"申请人",width:15},	
				{name : "expenseMoney",label:"报销金额",index : "expense_money",width:15},				
				{name : "content",label:"报销事由",index : "content",width:60},
				{name : "docAttach",label:"附单据数",index : "doc_attach",align:"center",width:10},	
				{name : "enterDate",label:"申请时间",index : "enter_date",width:20,align:"center"},		
				{name : "checkFlag",label:"审核状态",width:10,align:"center",
					formatter:function(cellvalue, options, rowObject){
		 				 if (cellvalue==1) {
		 				 	return '待审核';
		 				 }else if (cellvalue==2) {
		 				 	return '审核已通过';
		 				 }else if(cellvalue==3){
		 					return '初审已通过';
		 				 }
		 			}},
		 		{name : "assignFlag",label:"金额发放状态",width:10,align:"center",
					formatter:function(cellvalue, options, rowObject){
			 			 if (cellvalue==1) {
			 				 return '未发放';
			 			 }else if (cellvalue==2) {
			 				return '已发放';
			 			}
			 		}},
			 	{name : "expenseWay",label:"报销方式",index : "expense_way",width:20,align:"center"},
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
    });

	//审核的弹出框
	var audit_iframe_dialog;
  	
    
    function show(){
    	var key = ICSS.utils.getSelectRowData("id");
		if(key.indexOf(",")>-1||key==""){
			showMessage("请选择一条数据！");
			return ;
		}
		var url="<m:url value='/expenseAccount/toShowExpenseAccount.do'/>?key="+key;
		show_iframe_dialog = new biz.dialog({
		 	id:$('<div id="showwindow_iframe"></div>').html('<iframe id="iframeShow" name="iframeShow" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: 900,
			height:700,
				title: "报销单详情"
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
</script>
</body>
</html>
