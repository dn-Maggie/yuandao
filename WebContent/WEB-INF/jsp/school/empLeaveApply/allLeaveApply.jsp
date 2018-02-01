<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<title></title>
</head>
<body>
	<div class="main  choice_box">
		<form id="queryForm">
			<!-- 查询区 表单 -->
			<div class="search border-bottom">
				<ul>
					<li class="date_area"><span>申请日期:</span>
						<div class="time_bg">
							<input id="startDate" type="text" class="search_time150"
								name="propsMap['startDate']" mainid="startDate"> <i
								class="search_time_ico2"></i>
						</div> <i>至</i>
						<div class="time_bg">
							<input id="endDate" type="text" class="search_time150"
								name="propsMap['endDate']" mainid="endDate"> <i
								class="search_time_ico2"></i>
						</div></li>

					<li style="width: 180px;"><select class="search_choose"
						name="checkFlag" id="checkFlag" mainid="checkFlag"
						style="width: 100px;">
							<option value="">所有</option>
							<option value="1">未审核</option>
							<option value="2">已审核</option>
					</select><span>审核状态:</span></li>
					<!-- 输入框-->
					<li><input type="reset" class="reset_btn"
						onclick="resetForm('queryForm')" value="重置">
					<!-- 重置 --> <input type="button" class="search_btn mr22 "
						onclick="doSearch();" value="查询"></li>
					<!-- 查询-->
				</ul>
			</div>
		</form>
		<div class="listplace">
			<div class="listtable_box">
				<!--此处放表格-->
				<table id="remote_rowed"></table>
				<div id="remote_prowed"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	var gridObj = {};
	$(function(){
  		gridObj = new biz.grid({
            id:"#remote_rowed",/*html部分table id*/
            url: "<m:url value='/leaveApply/sumAllLeave.do'/>",/*grid初始化请求数据的远程地址*/
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
           	sortname:"leave_date",
           	sortorder:"desc",
           	pager: '#remote_prowed' /*分页栏id*/,
     		rowList:[10,20,50,100],//每页显示记录数
    		rowNum:20,//默认显示15条
            colModel:[
				{name : "id",hidden : true,key : true,label:"主键",index : "id"},				
				{name : "empName",label:"申请人",index : "empName"},	
				{name : "straightLeaderName",label:"直接负责人",index : "straight_leader"},
				{name : "empDept",label:"所在部门",index : "empDept"},	
				{name : "deptLeader",label:"部门经理",index : "dept_leader"},
				{name : "leaveDate",label:"请假天数",index : "leave_date",align:"center"},	
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
				$("#"+subgrid_id).data("loaded","true").html("<table id='"+subgrid_table_id+"' class='sub_grid' style='background:#fff'></table>");
				subgridObj = new biz.grid({
		            id:"#"+subgrid_table_id,/*html部分table id*/
		            url: "<m:url value='/leaveApply/subgridlist.do'/>?id="+row_id+"&startDate="+startDate+"&endDate="+endDate,/*grid初始化请求数据的远程地址*/
		            datatype: "json",/*数据类型，设置为json数据，默认为json*/
		           	sortname:"leave_date",
		           	rownumbers: false,
		           	sortorder:"desc",
		           	multiselect: false,
		            colModel: [
							{name : "id",hidden : true,key : true,label:"",index : "id"},
		                    {name : "leaveType",label:"假别",index : "leave_type",width:9,
		    					formatter:function(cellvalue, options, rowObject){
		    		 				 if (cellvalue==1) {
		    		 				 	return '事假';
		    		 				 }else if (cellvalue==2) {
		    		 				 	return '公假';
		    		 				 }
		    		 		}},	
		    		 		{name : "content",label:"请假事由",index : "content",width:40,},
		    		 		{name : "startDate",label:"请假日期始",index : "leave_date_from",width:6,},				
		    				{name : "endDate",label:"请假日期止",index : "leave_date_to",width:6},		
		    				{name : "leaveDate",label:"请假天数",index : "leave_date",width:4,align:"right"},
		    				{name : "createDate",label:"申请日期",index : "create_date",width:8,align:"center"},	
		    				{name : "checkFlag",label:"审核状态", width:30,align:"center",width:8,
		    					formatter:function(cellvalue, options, rowObject){
		    		 				 if (cellvalue==1) {
		    		 				 	return '待审核';
		    		 				 }else {
		    		 				 	return '已审核';
		    		 				 }
		    		 			}},	
						],
				});
			},
			subGridType:"json",
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
