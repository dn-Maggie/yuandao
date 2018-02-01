<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html  >
<html>
<head>
<%@ include file="../../common/header.jsp"%>
</head>

<body>
	<div style="height: 100%;">
		<div id="tabs_top"
			style="height: 100%; border: 1px solid #cccccc; border-top: 0px solid #cccccc; border-left: 0px solid #cccccc;">
			<ul>
				<li><a id="tabs2" href="#tabs-2">补款记录</a></li>
				<li><a id="tabs3" href="#tabs-3">退款记录</a></li>
			</ul>
			<div id="tabs-2" style="height: 96%; padding: 0px;">
				<div class="main  choice_box">
					<form id="queryForm1">
						<!-- 查询区 表单 -->
						<div class="search border-bottom">
							<ul>
								<li><input type="text" name="stuName" id="stuName"
									class="search_choose"> <span>学生姓名:</span> <input
									type="hidden" name="isCount" id="isCount" value="未分配业绩" /></li>
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
						<div class="listtable_box">
							<!--此处放表格-->
							<table id="remote_rowed1"></table>
							<div id="remote_prowed1"></div>
							<div style="text-align: center;">
								<input type="button" class="ti_bottom" value="确定"
									onclick="drawForm1();" /> <input type="button"
									class="ti_bottom" onclick="window.parent.closeSelectStudent();"
									value="取消" />
							</div>
						</div>
					</div>
				</div>
			</div>

			<div id="tabs-3" style="height: 96%; padding: 0px;">
				<div class="main  choice_box">
					<form id="queryForm2">
						<!-- 查询区 表单 -->
						<div class="search border-bottom">
							<ul>
								<li><input type="text" name="stuName" id="stuName"
									class="search_choose"> <span>学员姓名:</span> <input
									type="hidden" name="isCount" id="isCount" value="未分配业绩" /></li>
								<!-- 输入框-->

								<li class="date_area"><span>退款日期:</span>
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
									onclick="doSearchForm2();" value="查询"></li>
								<!-- 查询-->
							</ul>
						</div>
					</form>
					<div class="listplace">
						<div class="listtable_box">
							<!--此处放表格-->
							<table id="remote_rowed2"></table>
							<div id="remote_prowed2"></div>
							<div style="text-align: center;">
								<input type="button" class="ti_bottom" value="确定"
									onclick="drawForm2();" /> <input type="button"
									class="ti_bottom" onclick="window.parent.closeSelectStudent();"
									value="取消" />
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<script type="text/javascript">
var continuePay = {};
var vipRefund = {};
var vipStudent = {};
$(function() {
	new biz.tabs({
		  id: "#tabs_top",
		  position:"top",
		  collapsible:true//实现可折叠的标签页
		});
	
		continuePay = new biz.grid({
            id:"#remote_rowed1",/*html部分table id*/
            url: "<m:url value='/continuePay/listContinuePay.do'/>",/*grid初始化请求数据的远程地址*/
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
           	sortname:"time",
           	sortorder:"desc",
           	autowidth:true,
			shrinkToFit:true,
			singleselect : true,
           	pager: '#remote_prowed1' /*分页栏id*/,
     		rowList:[10],//每页显示记录数
    		rowNum:10,//默认显示15条
            colModel:[
				{name : "id",hidden : true,key : true,label:"",index : "id"},		
				{name : "stuId",hidden : true},		
				{name : "qq",label:"VIP学员QQ",index : "qq"},				
				{name : "stuName",label:"补款学员姓名",index :"stuName"},	
				{name : "stuJointime",label:"报名时间",index :"stuJointime"},
				{name : "money",label:"补款金额",index : "money"},				
				{name : "operator",label:"录入人",index : "operator"},	
				{name : "subname",label:"补款课程",index : "subname"},
				{name : "time",label:"补款时间",index : "TIME"},				
				{name : "notes",label:"备注",index : "notes"}				
           	],
           	serializeGridData:function(postData){//添加查询条件值
				var obj = getQueryCondition();
    			$ .extend(true,obj,postData);//合并查询条件值与grid的默认传递参数
    			return obj;
    		}
      });
		vipRefund = new biz.grid({
            id:"#remote_rowed2",/*html部分table id*/
            url: "<m:url value='/vipRefund/listVipRefund.do'/>",/*grid初始化请求数据的远程地址*/
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
           	sortname:"time",
           	sortorder:"desc",
           	autowidth:true,
			shrinkToFit:true,
			singleselect : true,
           	pager: '#remote_prowed2' /*分页栏id*/,
     		rowList:[10],//每页显示记录数
    		rowNum:10,//默认显示15条
            colModel:[
				{name : "id",hidden : true,key : true,label:"主键",index : "id"},				
				{name : "stuId",hidden : true},				
				{name : "qq",label:"退款学员QQ",index : "qq"},
				{name : "stuName",label:"退款学员姓名"},				
				{name : "refund",label:"退款金额",index : "refund"},	
				{name : "subjectName",label:"退款科目",index : "subjectName"},	
				{name : "courseName",label:"退款课程",index : "courseName"},	
				{name : "reason",label:"退款原因",index : "reason"},	
				{name : "operator",label:"录入人",index : "operator"},
				{name : "stuJointime",label:"报名时间",index :"stuJointime"},
				{name : "time",label:"退款时间",index : "time"}				
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
	    if($("#tabs2").parent("li").hasClass("ui-tabs-selected")){
			jQuery.each($("#queryForm1").serializeArray(),function(i,o){
	     	if(o.value){
	     		obj[o.name] = o.value;
	     	}
	     });
	    }else if($("#tabs3").parent("li").hasClass("ui-tabs-selected")){
	    	jQuery.each($("#queryForm2").serializeArray(),function(i,o){
	         	if(o.value){
	         		obj[o.name] = o.value;
	         	}
	         });
	    }
			return obj;
	 }
	 //查询Grid数据
	 function doSearchForm1(){
	 	if($("#tabs2").parent("li").hasClass("ui-tabs-selected")){
	 		continuePay.setGridParam({"page":"1"});
	 		continuePay.trigger('reloadGrid');
	 	}
	 }
	//查询Grid数据
	 function doSearchForm2(){
	 	if($("#tabs3").parent("li").hasClass("ui-tabs-selected")){
	 		vipRefund.setGridParam({"page":"1"});
		 	vipRefund.trigger('reloadGrid');
	 	}
	 }
	 //重置查询表单
	 function resetForm(formId){
			document.getElementById(formId).reset();
		}
	 function drawForm1(){
		 rowid = continuePay.getGridParam("selarrrow");
	   	if(rowid == null || rowid.length == 0){
				showInfo("<m:message code='grid.dealwith.chooseColAlert'/>",3000);
				return ;
			}
			
			var rowData = $("#remote_rowed1").jqGrid('getRowData',rowid[0]);
			drawParentForm1(rowData);
	 }
 
 	function drawParentForm1(rowData){
		window.parent.closeSelectStudent();
		window.parent.drawForm1(rowData);
	};
	
	 function drawForm2(){
		 rowid = vipRefund.getGridParam("selarrrow");
	   	if(rowid == null || rowid.length == 0){
				showInfo("<m:message code='grid.dealwith.chooseColAlert'/>",3000);
				return ;
			}
			var rowData = $("#remote_rowed2").jqGrid('getRowData',rowid[0]);
			drawParentForm2(rowData);
	 }
	 
	 function drawParentForm2(rowData){
			window.parent.closeSelectStudent();
			window.parent.drawForm2(rowData);
		};
</script>
</body>
</html>
