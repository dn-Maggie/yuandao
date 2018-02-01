<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html  >
<html>
<head>
<%@ include file="../../common/header.jsp"%>
</head>
<body>
	<div style="height: 100%;">
		<div class="main  choice_box">
			<form id="queryForm0">
				<!-- 查询区 表单 -->
				<div class="search border-bottom">
					<ul>
						<li><input type="text" name="stuName" id="stuName"
							class="search_choose"> <span>学生姓名:</span> <input
							type="hidden" name="isCount" id="isCount" value="已分配业绩" /></li>
						<!-- 输入框-->
						<li class="date_area"><span>日期:</span>
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

						<li><input type="reset" class="reset_btn"
							onclick="resetForm('queryForm')" value="重置">
						<!-- 重置 --> <input type="button" class="search_btn mr22 "
							onclick="doSearchForm1();" value="查询"></li>
						<!-- 查询-->
					</ul>
				</div>
			</form>
			<div class="listplace">
				<!--功能按钮end-->
				<div class="listtable_box">
					<!--此处放表格-->
					<table id="remote_rowed0"></table>
					<div id="remote_prowed0"></div>
					<div style="text-align: center;">
						<input type="button" class="ti_bottom" value="确定"
							onclick="drawForm0();" /> <input type="button" class="ti_bottom"
							onclick="window.parent.closeSelectStudent();" value="取消" />
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	var vipStudent = {};
	$(function() {
		vipStudent = new biz.grid({
	        id:"#remote_rowed0",/*html部分table id*/
	        url: "<m:url value='/vipStudent/listVipStudent.do'/>",/*grid初始化请求数据的远程地址*/
	        datatype: "json",/*数据类型，设置为json数据，默认为json*/
	       	sortname:"join_time",
	       	sortorder:"desc",
	       	autowidth:true,
			shrinkToFit:true,
			singleselect : true,
	       	pager: '#remote_prowed0' /*分页栏id*/,
	 		rowList:[10],//每页显示记录数
			rowNum:10,//默认显示15条
	        colModel:[
	                {name : "id",hidden : true,key : true,label:"",index : "id"},				
					{name : "name",label:"学员姓名"},				
					{name : "qq",label:"QQ",index : "qq"},	
					{name : "subjectName",label:"报名学科",index : "subject_name"},				
					{name : "courseName",label:"报名课程",index : "course_name"},
					{name : "shouldPay",label:"应缴学费",index : "should_pay"},
					{name : "actualPay",label:"实缴学费",index : "actual_pay"},
					
					{name : "followerType",hidden : true},				
					{name : "followerPosition",hidden : true},
					{name : "followerId",hidden : true},
					{name : "followerName",hidden : true},
					{name : "comSource",hidden : true},				
					{name : "sourceSubclass",hidden : true},
					{name : "followerRate",hidden : true},
					
					{name : "joinTime",label:"报名时间",index : "join_time"}
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
	/**
	 * 获取查询条件值
	 */
	 function getQueryCondition(){
	  		var obj = {};
			jQuery.each($("#queryForm0").serializeArray(),function(i,o){
		     	if(o.value){
		     		obj[o.name] = o.value;
		     	}
		     });
			return obj;
	 }
	 //查询Grid数据
	 function doSearchForm0(){
	 		vipStudent.setGridParam({"page":"1"});
	 		vipStudent.trigger('reloadGrid');
	 }
	 //重置查询表单
	 function resetForm(formId){
			document.getElementById(formId).reset();
		}
	 
	 function drawForm0(){
		rowid = vipStudent.getGridParam("selarrrow");
	   	if(rowid == null || rowid.length == 0){
				showInfo("<m:message code='grid.dealwith.chooseColAlert'/>",3000);
				return ;
			}
			var rowData = $("#remote_rowed0").jqGrid('getRowData',rowid[0]);
			drawParentForm0(rowData);
	 }
 
 	function drawParentForm0(rowData){
		window.parent.closeSelectStudent();
		window.parent.drawForm0(rowData);
	};
</script>
</body>
</html>
