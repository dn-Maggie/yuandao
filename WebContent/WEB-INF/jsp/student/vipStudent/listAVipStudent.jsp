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
		search();
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

    //新增的弹出框
	var add_iframe_dialog;
  	function add(){ 
  	//xin zeng iframe 弹出框
		var url="<m:url value='/vipStudent/toAddVipStudent.do'/>";
		add_iframe_dialog = new biz.dialog({
			id:$('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: $(window).width()*0.6,
			height:$(window).height()*0.8,
			title: "会员信息管理表增加"
		});
		add_iframe_dialog.open();
  	}
  	
  	//关闭新增页面，供子页面调用
  	function closeAdd(){
		add_iframe_dialog.close();
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
    	search();
    	if(!isStayCurrentPage)gridObj.setGridParam({"page":"1"});
    	gridObj.trigger('reloadGrid');
    }
    //重置查询表单
    function resetForm(formId){
		document.getElementById(formId).reset();
	}
    
  
    function search(){
  		gridObj = new biz.grid({
            id:"#remote_rowed",/*html部分table id*/
            url: "<m:url value='/vipStudent/listVipStudentFromMarket.do'/>",
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
           	sortname:"join_time",
           	sortorder:"desc",
           	//navtype:"top" /*导航栏类型*/,
           	//height: gridHeight,
           	multiselect:true,
           	multiboxonly:true,
           	pager: '#remote_prowed' /*分页栏id*/,
     		rowList:[10,15,50,100],//每页显示记录数
    		rowNum:10,//默认显示15条
            colModel:[
				{name : "id",hidden : true,key : true,label:"",index : "id"},				
				{name : "name",label:"学员姓名"},						
				{name : "qq",label:"QQ",index : "qq"},				
				{name : "subjectName",label:"报名学科",index : "subject_name"},				
				{name : "courseName",label:"报名课程",index : "course_name"},	
				{name : "teacherId",label:"任课讲师",index : "teacher_id"},		
				{name : "shouldPay",label:"应缴学费",index : "should_pay"},				
				{name : "actualPay",label:"实缴学费",index : "actual_pay"},				
				{name : "owePay",label:"欠缴学费",index : "owe_pay"},			
				{name : "currStatus",label:"目前状态",index : "curr_status"},	
				{name : "joinTime",label:"报名时间",index : "join_time"},		
           	],
           	serializeGridData:function(postData){//添加查询条件值
				var obj = getQueryCondition();
    			$ .extend(true,obj,postData);//合并查询条件值与grid的默认传递参数
    			return obj;
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
					<li style="width: 160px;"><input type="text" name="name"
						id="name" class="search_choose" style="width: 80px;"> <span>学员姓名:</span></li>
					<!-- 输入框-->
					<li style="width: 160px;"><input type="text" name="qq" id="qq"
						class="search_choose" style="width: 80px;"> <span>QQ号码:</span></li>
					<!-- 输入框-->
					<li style="width: 160px;"><select class="search_choose"
						name="subjectId" id="subjectId" mainid="subjectId"
						style="width: 80px;">
							<option value="">-请选择-</option>
							<c:forEach var="mr" items="${er.subject}">
								<option value="${mr.id}">
									<c:out value="${mr.name}"></c:out>
								</option>
							</c:forEach>
					</select><span>报名学科:</span></li>
					<!-- 输入框-->
					<li class="date_area"><span>报名日期:</span>
						<div class="time_bg">
							<input id="startDate" type="text" class="search_time150"
								name="propsMap['startDate']" mainid="startDate"> <i
								class="search_time_ico1" onclick="WdatePicker({el:'startDate'})"></i>
						</div> <i>至</i>
						<div class="time_bg">
							<input id="endDate" type="text" class="search_time150"
								name="propsMap['endDate']" mainid="endDate"> <i
								class="search_time_ico1" onclick="WdatePicker({el:'endDate'})"></i>
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
