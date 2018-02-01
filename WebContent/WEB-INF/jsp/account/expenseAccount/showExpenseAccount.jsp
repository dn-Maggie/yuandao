<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<title></title>
<style>
.item-wrapper {
	display: block;
	height: 35px;
	line-height: 35px;
	text-indent: 1em;
	cursor: default;
	font-size: 13px;
	width: 20%;
	float: left;
}

.item-wrapper>.item-class {
	display: block;
	background: rgb(98,149,233);
	color: #fff;
	font-weight: bold;
	border-right: 1px #fff solid;
}

.item-wrapper>.item-value {
	display: inline-block;
}
</style>
<script type="text/javascript">
var gridObj = {};

$(function(){        
	gridObj = new biz.grid({
		id:"#remote_rowed",/*html部分table id*/
        url: "<m:url value='/expenseAccount/sumAllExpense.do'/>",/*grid初始化请求数据的远程地址*/
        datatype: "json",/*数据类型，设置为json数据，默认为json*/
       	sortname:"expense_money",
       	sortorder:"desc",
       	pager: '#remote_prowed' /*分页栏id*/,
 		rowList:[10,20,50,100],//每页显示记录数
		rowNum:20,//默认显示15条
        colModel:[
			{name : "id",hidden : true,key : true,label:"",index : "id"},	
			{name : "deptName",label:"所在部门"},	
			{name : "expenseMoney",label:"报销金额",index : "expense_money"},	
			{name : "enterName",label:"申请人"},	
									
       	],
       	multiselect: false,
		autowidth:true,
		subGrid:true,
		subGridRowExpanded: function(subgrid_id, row_id) {
			var subgrid_table_id, pager_id;
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			subgrid_table_id = subgrid_id+"_t";
			pager_id = "p_"+subgrid_table_id;
			$("#"+subgrid_id).data("loaded","true").html("<table id='"+subgrid_table_id+"' class='sub_grid' style='background:#fff'></table><div id='"+pager_id+"'class='scroll'></div>");
			subgridObj = new biz.grid({
	            id:"#"+subgrid_table_id,/*html部分table id*/
	            url: "<m:url value='/expenseAccount/subgridlist.do'/>?id="+row_id+"&startDate="+startDate+"&endDate="+endDate,/*grid初始化请求数据的远程地址*/
	            datatype: "json",/*数据类型，设置为json数据，默认为json*/
	           	sortname:"expense_money",
	           	rowNum:10,
	           	rowList:[10,20,50,100],
	           	pager: pager_id,
	           	rownumbers:false,
	           	sortorder:"desc",
	           	multiselect: false,
	            colModel: [
	                    {name : "id",hidden : true,key : true,label:"",index : "id"},
	                    {name : "expenseType",label:"报销单类型",width:30},	
						{name : "content",label:"报销事由",index : "content",width:60},	
	    				{name : "expenseMoney",label:"报销金额",index : "expense_money",width:60},
	    				{name : "enterDate",label:"申请时间",index : "enter_date",width:30},	
	    				{name : "checkFlag",label:"审核状态", width:30,align:"center",
	    					formatter:function(cellvalue, options, rowObject){
	    		 				 if (cellvalue==1) {
	    		 				 	return '待审核';
	    		 				 }else {
	    		 				 	return '已审核';
	    		 				 }
	    		 			}},	
	    		 		{name : "assignFlag",label:"放款状态",width:30,align:"center",
	    						formatter:function(cellvalue, options, rowObject){
	    			 			 if (cellvalue==1) {
	    			 				 return '未放款';
	    			 			 }else {
	    			 				 return '已放款';
	    			 			}
	    			 		}},		
	    			 		{name : "expenseWay",label:"报销方式",index : "expense_way",width:30,align:"center",},	
					],
			});
		},
		subGridType:"json",
       	serializeGridData:function(postData){//添加查询条件值
			var obj = getQueryCondition();
			$ .extend(true,obj,postData);//合并查询条件值与grid的默认传递参数
			return obj;
		},
		gridComplete:function(){
           	getFooterJsonData();
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
    
  	//总计
  	//@param jqGridObj
  	function getFooterJsonData(){
  		$.ajax({
   			url : "<m:url value='/expenseAccount/staticExpenseAccount.do'/>",
   			cache : false,
   			data: getQueryCondition(),
   			async : false,
   			dataType:"json",
   			success : function(data) {
   				var $listbox = $("#listtable_box")[0];
   				var htmlArr = [];
   				for(var i = 0; i<data.statics.length ;i++){
   					var item = data.statics[i];
   					htmlArr.push('<div class="item-wrapper">');
   					htmlArr.push('<span class="item-class">');
   					htmlArr.push(item.id);
   					htmlArr.push('</span>');
   					htmlArr.push('<span class="item-value">');
   					htmlArr.push(item.expenseMoney);
   					htmlArr.push('</span>');
   					htmlArr.push('</div>');
   				}
   				$listbox.innerHTML = htmlArr.join(' ');
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
					<li><input type="text" name="enterName" id="enterName"
						class="search_choose"> <span>申请人:</span></li>
					<!-- 输入框-->
					<li style="width: 180px;"><select class="search_choose"
						name="checkFlag" id="checkFlag" mainid="checkFlag"
						style="width: 100px;">
							<option value=" ">--请选择--</option>
							<option value="1">未审核</option>
							<option value="2">已审核</option>
					</select><span>审核状态:</span> <!--  <input type="text" name="subjectId" id="subjectId" class="search_choose"> -->
					</li>
					<!-- 输入框-->
					<li class="date_area"><span>申请日期:</span>
						<div class="time_bg">
							<input id="startDate" type="text" class="search_time150"
								name="propsMap['startDate']" mainid="startDate"> <i
								class="search_time_ico2" onclick="WdatePicker({el:'startDate'})"></i>
						</div> <i>至</i>
						<div class="time_bg">
							<input id="endDate" type="text" class="search_time150"
								name="propsMap['endDate']" mainid="endDate"> <i
								class="search_time_ico2" onclick="WdatePicker({el:'endDate'})"></i>
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

			<!--功能按钮end-->
			<div class="listtable_box">
				<table id="remote_rowed"></table>
				<div id="remote_prowed"></div>
			</div>
			<div class="listtable_box" id="listtable_box"></div>
		</div>
	</div>
</body>
</html>
