<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<title><m:message code="moduleRes.list.title" /></title>
</head>
<body>
	<div class="main  choice_box">
		<form id="queryForm">
			<!-- 查询区 表单 -->
			<div class="search border-bottom">
				<ul>
					<li style="width: 320px; float: left;"><span>模糊查询:</span> <input
						id="name" type="text" class="search_choose100" value=""
						name="name" placeholder="学员姓名" /> <input id="qq" type="text"
						class="search_choose100" value="" name="qq" placeholder="学员QQ" />
					</li>

					<li><input type="reset" value="重置" id="reset"
						onClick="List.resetForm('queryForm')" class="reset_btn">
					<!-- 重置 --> <input type="button" value="查询" id="query"
						class="search_btn mr22 " onclick="List.doSearch(gridObj);" /></li>
					<!-- 查询-->
				</ul>
			</div>
		</form>
		<div class="listtable_box">
			<!--此处放表格-->
			<table id="remote_rowed"></table>
			<div id="remote_prowed"></div>
		</div>
		<div class="btn_area">
			<input type="button" id="submit" class="add_save" value="确定" />
		</div>
	</div>
</body>
<script type="text/javascript">
var gridObj = {};
var Model = {
		url: "<m:url value='/vipStudent/listVipStudent.do'/>",
		colModel:[
	               	{name : "id", hidden:true,key:true}, 
	    			{name : "name",label:"姓名",width:40}, 
					{name : "subjectName",label:"报名学科",width:40},			
					{name : "courseName",label:"报名课程",width:40},
	    			{name : "shouldPay",label:"应付学费",width:40},
	    			{name : "actualPay",label:"实付学费",width:40},
					{name : "owePay",label:"欠缴学费",width:40},
					{name : "currStatus",label:"目前状态",width:40},
					{name : "subjectId",hidden:true},
					{name : "courseId",hidden:true},
					{name : "followerId",hidden:true},
					{name : "followerPosition",hidden:true},
					{name : "followerName",hidden:true},
	    			{name : "followerType",hidden:true},
	    			{name : "followerRate",hidden:true},
					{name : "comSourceName",hidden:true},
					{name : "joinTime",hidden:true},
	           	]
		};
	$(function(){
  		gridObj = List.createGrid(Model.url,Model.colModel,"join_time", false);
  		
  		//绑定提交按钮click事件
  		$("#submit").click(function() {
  	    	rowid = gridObj.getGridParam("selarrrow");
  	    	if(rowid == null || rowid.length != 1){
  				showInfo("<m:message code='grid.dealwith.chooseColAlert'/>",3000);
  				return ;
  			}
  			var ids="";
  			for(var i=0; i < rowid.length;i++){
  				var id = gridObj.getCell(rowid[i],'id');
  				if(i>0){
  					ids +=',';
  				}
  				ids += id;
  			}
  			var rowData = $("#remote_rowed").jqGrid('getRowData',rowid);
  			drawParentForm(rowData);
  		});
    });
	function drawParentForm(rowData){
		window.parent.closeAllVip();
		window.parent.drawForm(rowData);
	};
	

 	
    </script>
</html>
