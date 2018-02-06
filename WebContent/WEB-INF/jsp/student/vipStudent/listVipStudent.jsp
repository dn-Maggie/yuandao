<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<title></title>
<script type="text/javascript">
var gridObj = {};//声明表格对象
var showColModel = [];//声明表格列名和内容
var showUrl = ""//声明请求地址
var arr = [];//总数据结果集
//定义小计总计的统计字段信息(大写,第一个字段标识在哪列显示“合计”，“小计”字样 )
//可以合计总计可以单独设置。
var jsion_sumColumns = {};
jsion_sumColumns["sumColumns"] = "rn,shouldPay,actualPay,owePay";//声明页脚需要求和的总计列
	$(function(){
		//$("#endDate").val(new Date().format('yyyy-MM-dd'));
		var showType = $("#showType").val();//？
		switch (showType) {
		case "vipCnt":
			showUrl = "<m:url value='/vipStudent/listVipStudentCnt.do'/>";
			showColModel=  [{name : "id",hidden : true,key : true,label:"",index : "id"},				
							{name : "name",label:"学员姓名"},				
							{name : "qq",label:"QQ",index : "qq"},	
							{name : "qqNick",label:"QQ昵称"},	
							{name : "phone",label:"手机号码"},
							{name : "courses",label:"报名课程数量"},				
							{name : "actualPay",label:"实缴学费总额",index : "course_name"},
							{name : "shouldPay",label:"应缴学费总额",index : "should_pay"},
							{name : "owePay",label:"欠缴学费总额",index : "should_pay"},
							{name : "joinTime",hidden:true}]
			break;
		case "monthshouldPay":
		case "yearshouldPay":
			showUrl = "<m:url value='/vipStudent/listVipStudent.do'/>";
			showColModel=  [{name : "id",hidden : true,key : true,label:"",index : "id"},				
							{name : "name",label:"学员姓名"},
							{name : "eduBack",label:"学号"},
							{name : "qq",label:"QQ",index : "qq"},	
							{name : "subjectName",label:"报名学科",index : "subject_name"},				
							{name : "courseName",label:"报名课程",index : "course_name"},
							{name : "shouldPay",label:"应缴学费",index : "should_pay"},
							{name : "joinTime",label:"报名时间",index : "join_time"}]
			break;
		case "monthowePay":
		case "yearowePay":
			showUrl = "<m:url value='/vipStudent/listVipStudent.do'/>";
			showColModel=  [{name : "id",hidden : true,key : true,label:"",index : "id"},				
							{name : "name",label:"学员姓名"},	
							{name : "eduBack",label:"学号"},
							{name : "qq",label:"QQ",index : "qq"},	
							{name : "subjectName",label:"报名学科",index : "subject_name"},				
							{name : "courseName",label:"报名课程",index : "course_name"},
							{name : "owePay",label:"欠缴学费",index : "owe_pay"},	
							{name : "joinTime",label:"报名时间",index : "join_time"}]
			break;
		case "orderCnt":
			showUrl = "<m:url value='/vipStudent/listVipStudent.do'/>";
			showColModel=  [{name : "id",hidden : true,key : true,label:"",index : "id"},				
							{name : "name",label:"学员姓名"},	
							{name : "eduBack",label:"学号"},
							{name : "qq",label:"QQ",index : "qq"},	
							{name : "subjectName",label:"报名学科",index : "subject_name"},				
							{name : "courseName",label:"报名课程",index : "course_name"},
							{name : "shouldPay",label:"应缴学费",index : "should_pay"},				
							{name : "actualPay",label:"实缴学费",index : "actual_pay"},				
							{name : "owePay",label:"欠缴学费",index : "owe_pay"},		
							{name : "joinTime",label:"报名时间",index : "join_time"}]
			break;
		default:
			showUrl = "<m:url value='/vipStudent/listVipStudent.do'/>";
			showColModel=  [{name : "id",hidden : true,key : true,label:"",index : "id"},				
							{name : "name",label:"学员姓名"},		
							{name : "eduBack",label:"学号"},
							{name : "phone",label:"手机号码",index : "phone"},			
							{name : "qq",label:"QQ",index : "qq"},	
							{name : "subjectName",label:"报名学科",index : "subject_name"},				
							{name : "courseName",label:"报名课程",index : "course_name"},	
							{name : "teacherId",label:"任课讲师",index : "teacher_id"},				
							{name : "followerName",label:"转化人"},				
							{name : "shouldPay",label:"应缴学费",index : "should_pay"},				
							{name : "actualPay",label:"实缴学费",index : "actual_pay"},				
							{name : "owePay",label:"欠缴学费",index : "owe_pay"},			
							{name : "currStatus",label:"目前状态",index : "curr_status"},
							{name : "currPosition",label:"在职状态",index : "curr_position"},	
							{name : "joinTime",label:"报名时间",index : "join_time"},
							{name : "enterEmpname",label:"录入人"}]
			break;
		}
  		gridObj = new biz.grid({
            id:"#remote_rowed",/*html部分table id*/
            url: showUrl,/*grid初始化请求数据的远程地址*/
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
           	sortname:"join_time",
           	sortorder:"desc",
           	multiselect:true,//多选
           	multiboxonly:true,//multiselect:为true时才有效，只有点击checkbox时该行才被选中，点击其他列，将清除当前行的选中
           	footerrow:true,//增加页脚合计行
           	pager: '#remote_prowed', /*分页栏id*/
     		rowList:[10,20,50,100],//每页显示记录数
    		rowNum:20,//表格中可见的记录数。
    		jsonReader:{repeatitems: false},//JSON数据结构数组
            colModel:showColModel,//描述列参数数组
           	serializeGridData:function(postData){//添加查询条件值,序列化传递给ajax请求的的数据。此事件将返回一个已序列化的数据。
				var obj = getQueryCondition();//返回json格式查询数据
    			$.extend(true,obj,postData);//合并查询条件值与grid的默认传递参数
    			//嵌套多个对象，类似于数组的合并的操作，obj中与postData相同的属性被覆盖，而且集成了postData的属性
    			return obj;
    		},
    		gridComplete:function(){//当表格所有数据都加载完成，处理统计行数据
    			//$(".ui-jqgrid-sdiv").show();//
           		//如果需要统计则需要定义
               	getFooterJsonData($(this));//添加页脚合计数据
	 		}
      	});
        
		new biz.datepicker({//
  			id : "#startDate",//找到开始时间选择器的id
  			maxDate:'#F{$dp.$D(\'endDate\',{d:0});}',//
  			dateFmt:'yyyy-MM-dd'
  		});
  	    
  	    new biz.datepicker({//
  			id : "#endDate",//找到结束时间选择器的id
  			minDate:'#F{$dp.$D(\'startDate\',{d:0});}',//
  			dateFmt:'yyyy-MM-dd'
  		});
    });

    //新增的弹出框
	var add_iframe_dialog;
	//修改的弹出框
   	var edit_iframe_dialog;
	//查看的弹出框
	var show_iframe_dialog;
	//业绩分配的弹出框
	var manage_iframe_dialog;
  	
	
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
  	
    function edit(){
		var key = ICSS.utils.getSelectRowData("id");//?遍历选中的数据行的id值
		if(key.indexOf(",")>-1||key==""){//是否选择数据判断
			showMessage("请选择一条数据！");
			return ;
		}
		var url="<m:url value='/vipStudent/toEditVipStudent.do'/>?key="+key;
		edit_iframe_dialog = new biz.dialog({
		 	id:$('<div id="editwindow_iframe"></div>').html('<iframe id="iframeEdit" name="iframeEdit" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: $(window).width()*0.6,
			height:$(window).height()*0.8,
			title: "会员信息管理表编辑"
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
		var url="<m:url value='/vipStudent/toShowVipStudent.do'/>?key="+key;
		show_iframe_dialog = new biz.dialog({
		 	id:$('<div id="showwindow_iframe"></div>').html('<iframe id="iframeShow" name="iframeShow" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: $(window).width()*0.6,
			height:$(window).height()*0.8,
				title: "会员信息管理表详情"
		});
  		show_iframe_dialog.open();
    }
    
    //关闭查看页面，供子页面调用
    function closeShow(){
    	show_iframe_dialog.close();
    }
    
    
    function manage(){
		var key = ICSS.utils.getSelectRowData("id");
		if(key.indexOf(",")>-1||key==""){
			showMessage("请选择一条数据！");
			return ;
		}
		var url="<m:url value='/vipStudent/toManagePerformance.do'/>?key="+key;
		manage_iframe_dialog = new biz.dialog({
			id:$('<div id="mrselect_window_iframe"></div>').html('<iframe id="iframeMrSelect" name="iframeMrSelect" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
			modal: true,
			width: $(window).width()*0.6,
			height:$(window).height()*0.8,
			title: "业绩分配管理表编辑"
		});
		manage_iframe_dialog.open();
    }
    
    //关闭业绩分配管理页面，供子页面调用
    function closeManage(){
    	manage_iframe_dialog.close();
    }
    
    /**
    * 获取查询条件值
    */
    function getQueryCondition(){
       var obj = {};
		jQuery.each($("#queryForm").serializeArray(),function(i,o){//$("#queryForm").serializeArray()输出以数组形式序列化表单值的结果，返回 JSON 数据结构数据
			//此方法返回的是 JSON 对象而非 JSON 字符串。需要进行字符串化操作
			//返回的 JSON 对象是由一个对象数组组成的，其中每个对象包含一个或两个名值对 —— name 参数和 value 参数
        	if(o.value){
        		obj[o.name] = o.value;//遍历json对象把数据存入obj中
        	}
        });
		return obj;
    }
    //查询Grid数据
    function doSearch(isStayCurrentPage){
    	if(!isStayCurrentPage)gridObj.setGridParam({"page":"1"});//设置一个特定的参数。有些参数需trigger(“reloadGrid”)才能生效。
    	gridObj.trigger('reloadGrid');
    	//连带查询grid数据统计
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
        				url: "<m:url value='/vipStudent/deleteVipStudent.do'/>?key="+ids,
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
    
  //统计行数据
  //@param jqGridObj
  function getFooterJsonData(jqGridObj){
     var addFootData = {} ;
	 var resObj = ajaxGetStatistic();//根据条件从数据库获取结果集
     try{
       //总计
   		var _strColumns = jsion_sumColumns.sumColumns.split(",");//拆分jsion_sumColumns数组
           for(var k = 0;k<_strColumns.length; k++){//遍历resObj的值存入addFootData
        	   k == 0?addFootData[_strColumns[k]] = "总计":addFootData[_strColumns[k]] = Math.round(resObj[_strColumns[k]]) ||0;
           }
     }
   	catch(e){
    }    
   		jqGridObj.jqGrid('footerData','set',addFootData,false);//添加数据
  	}
	//根据条件从数据库获取结果集
	function ajaxGetStatistic(){
		var resObj = {};
       	$.ajax({
   			url : "<m:url value='/standard/getStuStatistic.do'/>",
   			cache : false,
   			data: getQueryCondition(),
   			async : false,
   			dataType:"json",
   			success : function(data) {
   				resObj.shouldPay = parseFloat(data.curr[0].shouldPay); 
   				resObj.actualPay = parseFloat(data.curr[0].actualPay);
   				resObj.owePay = parseFloat(data.curr[0].owePay);
   			}
   		});
       	return resObj;
	};
	
	//导出数据
    function expExcelWinShow(){
    	ExpExcel.showWin(gridObj,baseUrl+"/vipStudent/exportExcel.do",'grid','queryForm');
    }
    </script>
</head>
<body style="height: 100%;">

	<div class="main  choice_box">
		<form id="queryForm">
			<!-- 查询区 表单 -->
			<input type="hidden" id="showType" value="${condition}">
			<div class="search border-bottom">
				<ul>
					<li style="width: 160px;"><input type="text" name="name"
						id="name" class="search_choose" style="width: 80px;"> <span>学员姓名:</span></li>
					<!-- 输入框-->
					<li style="width: 160px;"><input type="text" name="qq" id="qq"
						class="search_choose" style="width: 80px;"> <span>QQ号码:</span></li>
						<!-- 输入框-->
					<li style="width: 160px;"><input type="text" name="qqNick" id="qqNick"
						class="search_choose" style="width: 80px;"> <span>QQ昵称:</span></li>
					<!-- 输入框-->
					<c:if test="${isAdmin}">
						<li style="width: 160px;"><input type="text"
							name="followerName" id="followerName" class="search_choose"
							style="width: 80px;"> <span>转化人:</span></li>
						<!-- 输入框-->
					</c:if>



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

					<li style="width: 180px;"><select class="search_choose"
						name="currStatus" id="currStatus" mainid="currStatus"
						style="width: 100px;">
							<option value="">--请选择--</option>
							<option value="已付全款">已付全款</option>
							<option value="分期付款">分期付款</option>
							<option value="已退款">已退款</option>
					</select><span>学生状态:</span> <!--  <input type="text" name="subjectId" id="subjectId" class="search_choose"> -->
					</li>
					<li style="width: 180px;"><select class="search_choose"
						name="currPosition" id="currPosition" 
						style="width: 100px;">
							<option value="">--请选择--</option>
							<option value="在读学生">在读学生</option>
							<option value="待业">待业</option>
							<option value="IT在职">IT在职</option>
							<option value="转行">转行</option>
					</select><span>在职状态:</span> 
					</li>
					<!-- 输入框-->
					<li class="date_area"><span>报名日期:</span>
						<div class="time_bg">
							<c:choose>
								<c:when
									test="${condition=='month'||condition=='monthshouldPay'}">
									<input id="startDate" type="text" class="search_time150"
										name="propsMap['startDate']" mainid="startDate"
										value="${currDate}">
								</c:when>
								<c:when test="${condition=='year'||condition=='yearshouldPay'}">
									<input id="startDate" type="text" class="search_time150"
										name="propsMap['startDate']" mainid="startDate"
										value="${currDate}">
								</c:when>
								<c:when
									test="${condition=='monthowePay'||condition=='yearowePay'}">
									<input id="startDate" type="text" class="search_time150"
										name="propsMap['startDate']" mainid="startDate"
										value="${currDate}">
									<input id="minOwePay" type="hidden" name="minOwePay" value="10">
								</c:when>
								<c:otherwise>
									<input id="startDate" type="text" class="search_time150"
										name="propsMap['startDate']" mainid="startDate">
								</c:otherwise>
							</c:choose>
							<i class="search_time_ico1"
								onclick="WdatePicker({el:'startDate'})"></i>
						</div> <i>至</i>
						<div class="time_bg">
							<input id="endDate" type="text" class="search_time150"
								name="propsMap['endDate']" mainid="endDate"> <i
								class="search_time_ico1" onclick="WdatePicker({el:'endDate'})"></i>
						</div></li>
					<li><select class="search_choose" name="isCount" id="isCount"
						mainid="isCount">
							<option value="">--请选择--</option>
							<option value="未分配业绩">未分配业绩</option>
							<option value="已分配业绩">已分配业绩</option>
					</select><span>业绩分配:</span> <!--  <input type="text" name="subjectId" id="subjectId" class="search_choose"> -->
					</li>
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
			<!--功能按钮begin-->
			<div class="list_btn_bg fl">
				<!--功能按钮 div-->
				<ul>
					<%-- <c:if test="${add}"> --%>
					<li><a title="<m:message code="button.add"/>"
						href="javascript:;" onclick="add();"> <i
							class="icon_bg icon_add"> </i> <span><m:message
									code="button.add" /></span>
					</a></li>
					<%-- </c:if>
						<c:if test="${edit}"> --%>
					<li><a title="<m:message code="button.edit"/>"
						href="javascript:;" onclick="edit();"><i
							class="icon_bg icon_edit"></i> <span><m:message
									code="button.edit" /></span> </a></li>
					<%-- </c:if> --%>
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
					<c:if test="${manage}">
						<li><a title="业绩分配" href="javascript:" onclick="manage();">
								<i class="icon_bg icon_audit"></i> <span>业绩分配</span>
						</a></li>

						<li><a href="javascript:;" onClick="expExcelWinShow();">
								<i class="icon_bg icon_download"></i> <span>导出</span>
						</a></li>
					</c:if>
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
