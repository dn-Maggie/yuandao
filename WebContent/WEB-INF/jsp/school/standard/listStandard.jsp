<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<title></title>
    <style type="text/css">
    	div.bottomStatistic{
    	    width: 25%;
		    float: left;
		    height: 20px;
		    line-height: 20px;
		    font-family: "PingFang SC", "Helvetica Neue", Helvetica, Arial, "Hiragino Sans GB", "Microsoft Yahei",sans-serif;
		    font-size: 13px;
   			color: #2b82d2;
   			font-weight: bold;
    	}
    </style>
</head>
<body style="height:100%;">

	<div class="main  choice_box">
		<form id="queryForm"><!-- 查询区 表单 -->
			<div class="search border-bottom">
				<ul>
				<li><input type="text" name="stuName" id="stuName" class="search_choose"> <span>学生姓名:</span></li><!-- 输入框-->
				<li><span>统计方式:</span>
					<select class="search_choose" name="groupBy" id="groupBy" mainid="groupBy">
						<option value="">-请选择-</option>
							<option value="subjectName">按学科</option>
							<option value="courseName">按课程</option>
					</select>
				</li>
				<li style="width:160px;">
						<select class="search_choose" name="subjectName" id="subjectName" mainid="subjectName" style="width:88px;">
						<option value="">-请选择-</option>
						<c:forEach var="subject" items="${subject}">
							<option value="${subject.id}"> <c:out value="${subject.name}"></c:out> </option>
			            </c:forEach>
					</select><span>学科:</span>
					</li><!-- 输入框-->
				<li class="date_area">
					<span>发生日期:</span>
					<div class="time_bg">
						<input id="startDate" type="text" class="search_time150" name="propsMap['startDate']" mainid="startDate">
						<i class="search_time_ico1"></i>
					</div>
					<i>至</i>
					<div class="time_bg">
						<input id="endDate" type="text" class="search_time150" name="propsMap['endDate']" mainid="endDate">
						<i class="search_time_ico1"></i>
					</div></li>	
					<li><input type="reset" class="reset_btn" onclick="resetForm('queryForm')" value="重置"><!-- 重置 -->
						<input type="button" class="search_btn mr22 " onclick="doSearch();" value="查询"></li><!-- 查询-->
				</ul>
		   </div>
	    </form>
		<div class="listplace">	
			<!--功能按钮end-->
				<div class="listtable_box">
					<!--此处放表格-->
					<table  id="remote_rowed" ></table>
					<div  id="remote_prowed"></div>		
				</div>
				<div id="total" style="position:relative;bottom:0px;width:800px;left:50%;margin-left:-400px;z-index:20">
					<!--div class="bottomStatistic">本月意向学员：
						<span id="markStuCount">
						</span>
					</div>
					<div class="bottomStatistic">本月VIP学员：
						<span id="vipcnt">
						</span>
					</div-->
					<div class="bottomStatistic">本月应收总额：
						<span id="shouldPay" >
<%-- 							<fmt:formatNumber value="${model.curr.shouldPay}" pattern="0" type="number"></fmt:formatNumber> --%>
						</span>
					</div>
					<div class="bottomStatistic">本月退款总额：
						<span id="xftk" >
<%-- 							<fmt:formatNumber value="${model.currxf.xftk}" pattern="0" type="number"></fmt:formatNumber> --%>
						</span>
					</div>
					<div class="bottomStatistic">本月应收欠款：
						<span id="arrears" >
<%-- 							<fmt:formatNumber value="${model.curr.arrears}" pattern="0" type="number"></fmt:formatNumber> --%>
						</span>
					</div>
					<div class="bottomStatistic">本月总业绩：
						<span id="allincome">
<%-- 							<fmt:formatNumber value="${model.currxf.xfsr+model.currxf.xfbk-model.currxf.xftk}" pattern="0" type="number"></fmt:formatNumber> --%>
						</span>
					</div>
					<div class="bottomStatistic">本月实收报名费：
						<span id="xfsr">
<%-- 							<fmt:formatNumber value="${model.currxf.xfsr}" pattern="0" type="number"></fmt:formatNumber> --%>
						</span>
					</div>
					<div class="bottomStatistic">本月实收补款：
						<span id="xfbk">
<%-- 							<fmt:formatNumber value="${model.currxf.xfbk}" pattern="0" type="number"></fmt:formatNumber> --%>
						</span>
					</div>
				</div> 
		</div>
	</div>
	<script type="text/javascript">
	var gridObj = {};
	$(function(){
		 $("#startDate").val(new Date().format('yyyy-MM-01'));
		 $("#endDate").val(new Date().format('yyyy-MM-dd'));
		gridObj = new biz.grid({
            id:"#remote_rowed",/*html部分table id*/
            url: "<m:url value='/orderInfo/sumOrder.do'/>",/*grid初始化请求数据的远程地址*/
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
           	sortname:"create_time",
           	sortorder:"desc",
            colModel: [
						{name : "stuName",label:"学员姓名"},
						{name : "subjectName",label:"报名学科",index : "subjectName"},
						{name : "courseName",label:"报名课程",index : "courseName"},	
						{name : "createTime",label:"创建时间",index : "create_time"},		
						{name : "shouldPay",label:"应缴学费",index : "should_pay",formatter:'integer', formatoptions:{thousandsSeparator: ','}},				
						{name : "actualPay",label:"实缴学费",index : "actual_pay",formatter:'integer', formatoptions:{thousandsSeparator: ','}},										
						],
        	pager: '#remote_prowed' /*分页栏id*/,
     		rowList:[10,20,50,100],//每页显示记录数
    		rowNum:20,//默认显示20条
    		multiselect: false,
    		footerRow:true,
			autowidth:true,
			subGrid:true,
			subGridRowExpanded: function(subgrid_id, row_id) {
				var subgrid_table_id, pager_id;
				subgrid_table_id = subgrid_id+"_t";
				pager_id = "p_"+subgrid_table_id;
				$("#"+subgrid_id).data("loaded","true").html("<table id='"+subgrid_table_id+"' class='sub_grid' style='background:#fff'></table>");
				new biz.grid({
		            id:"#"+subgrid_table_id,/*html部分table id*/
		            url: "<m:url value='/orderInfo/subgridlist.do'/>?id="+row_id,/*grid初始化请求数据的远程地址*/
		            datatype: "json",/*数据类型，设置为json数据，默认为json*/
		           	sortname:"should_pay,actual_pay",
		           	rownumbers: false,
		           	sortorder:"desc",
		           	multiselect: false,
		            colModel: [
								{name : "orderType",label:"操作类型",
									formatter:function(cellvalue, options, rowObject){
						 				 if (cellvalue==1) {
						 				 	return '学费收入';
						 				 }else if(cellvalue==2){
						 				 	return '学费补款';
						 				 }else if(cellvalue==3){
						 				 	return '学费退款';
						 				 }else if(cellvalue==4){
						 				 	return '学费调整';
						 				 }
						 			}	
								},
								{name : "subjectName",label:"报名学科",index : "subjectName"},
								{name : "courseName",label:"报名课程",index : "courseName"},	
								{name : "createTime",label:"创建时间",index : "create_time"},		
								{name : "shouldPay",label:"应缴学费",index : "should_pay",formatter:'integer', formatoptions:{thousandsSeparator: ','}},				
								{name : "actualPay",label:"实缴学费",index : "actual_pay",formatter:'integer', formatoptions:{thousandsSeparator: ','}},										
								]
				});
			},
			subGridType:"json",
           	serializeGridData:function(postData){//添加查询条件值
				var obj = getQueryCondition();
    			$ .extend(true,obj,postData);//合并查询条件值与grid的默认传递参数
    			return obj;
    		},
	  		gridComplete:function(){
	  			ajaxGetStatistic();
	 		}
     	 });
	    new biz.datepicker({
  			id : "#startDate",
  			maxDate:'#F{$dp.$D(\'endDate\',{d:0});}',
  			dateFmt:'yyyy-MM-dd',
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
    	search();
    	if(!isStayCurrentPage)gridObj.setGridParam({"page":"1"});
    	gridObj.trigger('reloadGrid');
    	
    }
    //重置查询表单
    function resetForm(formId){
		document.getElementById(formId).reset();
	}
    
    function search(){
		var groupBy = $("#groupBy").val(); 
		if(groupBy=='subjectName'){
			$("#remote_rowed").jqGrid('showCol',["cnt","subjectName"]);
			$("#remote_rowed").jqGrid('hideCol',["stuName","createTime","courseName"]);		
			$("#remote_rowed").jqGrid('setGridWidth',($(window).width()*0.99));
		}else if(groupBy=='courseName'){
			$("#remote_rowed").jqGrid('showCol',["courseName","cnt"]);
			$("#remote_rowed").jqGrid('hideCol',["stuName","createTime"]);	
			$("#remote_rowed").jqGrid('setGridWidth',($(window).width()*0.99));
		}else{
			$("#remote_rowed").jqGrid('hideCol',["cnt"]);
			$("#remote_rowed").jqGrid('showCol',["stuName","createTime","courseName"]);	
			$("#remote_rowed").jqGrid('setGridWidth',($(window).width()*0.99));
		}
	}   
    function ajaxGetStatistic(){
    	var startDate = $("#startDate").val();
    	var temp = startDate.split("-");
    	var month = temp[0]+""+temp[1];
    	var paramDatas = {month:month,subject:$("#subjectName").val()};
    	$.ajax({
			url : "<m:url value='/standard/getStatistic.do'/>",
			cache : false,
			data: paramDatas,
			async : false,
			dataType:"json",
			success : function(data) {
				var arr = data;
				 if(typeof(arr.curr)!=null) 
					{
						try {
							$("#vipcnt").text(arr.curr.vipcnt);
							$("#shouldPay").text((arr.curr.shouldPay).toFixed(2));
							$("#xftk").text((arr.currxf.xftk).toFixed(2));
							$("#arrears").text((arr.curr.owePay).toFixed(2));
							$("#allincome").text((arr.currxf.xfsr+arr.currxf.xfbk-arr.currxf.xftk).toFixed(2));
							$("#xfsr").text((arr.currxf.xfsr).toFixed(2));
							$("#xfbk").text((arr.currxf.xfbk).toFixed(2));
						} catch (e) {
							return;
						}
					}
				else{
					return;
				} 
			}
		});
    };
    </script>
</body>
</html>
