<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<title></title>
<script type="text/javascript">
var gridObj = {};
	$(function(){
  		gridObj = new biz.grid({
            id:"#remote_rowed",/*html部分table id*/
            url: "<m:url value='/employee/listEmployee.do'/>",/*grid初始化请求数据的远程地址*/
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
           	sortname:"entry_date",
           	sortorder:"desc",
           	multiselect:true,
           	multiboxonly:true,
           	forceFit:true,
           	pager: '#remote_prowed' /*分页栏id*/,
     		rowList:[10,15,50,100],//每页显示记录数
    		rowNum:10,//默认显示15条
            colModel:[
				{name : "id",hidden : true,key : true,label:"主键",index : "id"},	
				{name : "empNo",label:"工号",index : "emp_no",width:'20'},
				{name : "name",label:"姓名",index : "name",width:'20'}, 
				{name : "nickName",label:"昵称",width:'20'},		
				{name : "mobile",label:"手机号码",width:'30'},	
				{name : "dept",label:"部门",index : "dept",width:'30'},		
				{name : "position",label:"职位",index : "position",width:'20'},	
				{name : "entryDate",label:"入职日期",index : "entry_date",width:'40'},
				/* {name : "beFullDate",label:"转正日期",index : "be_full_date",width:'40'}, */
				{name : "foxmail",label:"企业邮箱地址",index : "foxmail",width:'40'},	
				{name : "currState",label:"在职状态",index : "curr_state",width:'20',
		 			formatter:function(cellvalue, options, rowObject){
	 				 if (cellvalue==1) {return '在职';}else if (cellvalue==2){return '试用';}else if (cellvalue==3){return '离职';}else if (cellvalue==4){return '兼职';}
	 			}},	
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
  	  new biz.datepicker({
			id : "#beFullDateStart",
			maxDate:'#F{$dp.$D(\'beFullDateEnd\',{d:0});}',
			dateFmt:'yyyy-MM-dd'
		});
	    
	    new biz.datepicker({
			id : "#beFullDateEnd",
			minDate:'#F{$dp.$D(\'beFullDateStart\',{d:0});}',
			dateFmt:'yyyy-MM-dd'
		});
	    new biz.datepicker({
  			id : "#leaveDateStart",
  			maxDate:'#F{$dp.$D(\'leaveDateEnd\',{d:0});}',
  			dateFmt:'yyyy-MM-dd'
  		});
  	    
  	    new biz.datepicker({
  			id : "#leaveDateEnd",
  			minDate:'#F{$dp.$D(\'leaveDateStart\',{d:0});}',
  			dateFmt:'yyyy-MM-dd'
  		});
    });

 

    //新增的弹出框
	var add_iframe_dialog;
	//修改的弹出框
   	var edit_iframe_dialog;
	//查看的弹出框
	var show_iframe_dialog;
  	
  	function add(){
  	//xin zeng iframe 弹出框
		var url="<m:url value='/employee/toAddEmployee.do'/>";
		add_iframe_dialog = new biz.dialog({
			id:$('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: $(window).width()*0.6,
			height:$(window).height()*0.8,
			title: "员工增加"
		});
		add_iframe_dialog.open();
  	}
  	
  	//关闭新增页面，供子页面调用
  	function closeAdd(){
		add_iframe_dialog.close();
  	}
  	
    function edit(){
		var key = ICSS.utils.getSelectRowData("id");
		if(key.indexOf(",")>-1||key==""){
			showMessage("请选择一条数据！");
			return ;
		}
		
		var url="<m:url value='/employee/toEditEmployee.do'/>?key="+key;
		edit_iframe_dialog = new biz.dialog({
		 	id:$('<div id="editwindow_iframe"></div>').html('<iframe id="iframeEdit" name="iframeEdit" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: $(window).width()*0.6,
			height:$(window).height()*0.8,
			title: "员工编辑"
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
		var url="<m:url value='/employee/toShowEmployee.do'/>?key="+key;
		show_iframe_dialog = new biz.dialog({
		 	id:$('<div id="showwindow_iframe"></div>').html('<iframe id="iframeShow" name="iframeShow" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: $(window).width()*0.6,
			height:$(window).height()*0.8,
				title: "员工详情"
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
        				url: "<m:url value='/employee/deleteEmployee.do'/>?key="+ids,
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
    function expExcelWinShow(){
    	ExpExcel.showWin(gridObj,baseUrl+"/employee/exportExcel.do",'grid','queryForm');
    }
    </script>
</head>
<body style="height: 100%;">

	<div class="main  choice_box">
		<form id="queryForm">
			<!-- 查询区 表单 -->
			<div class="search border-bottom">
				<ul>
					<li><input type="text" name="name" id="name"
						class="search_choose"> <span>员工姓名:</span></li>
					<!-- 输入框-->
					<li><select style="float: none" class="search_select"
						name="dept" id="edit_dept" mainid="dept">
							<option value="">---请选择---</option>
							<c:forEach var="org" items="${org}">
								<option value="${org.orgName}"><c:out
										value="${org.orgName}"></c:out></option>
							</c:forEach>
					</select> <span>所在部门:</span></li>
					<li><select class="search_choose" name="dutyId" id="dutyId">
							<option value="">--请选择--</option>
							<c:forEach var="duty" items="${duty}">
								<option value="${duty.id}">
									<c:out value="${duty.dutyName}"></c:out>
								</option>
							</c:forEach>
					</select><span>所在岗位:</span></li>
					<li class="date_area"><span>入职时间:</span>
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
					<li class="date_area"><span>转正时间:</span>
						<div class="time_bg">
							<input id="beFullDateStart" type="text" class="search_time150"
								name="propsMap['beFullDateStart']" mainid="beFullDateStart">
							<i class="search_time_ico2"></i>
						</div> <i>至</i>
						<div class="time_bg">
							<input id="beFullDateEnd" type="text" class="search_time150"
								name="propsMap['beFullDateEnd']" mainid="beFullDateEnd">
							<i class="search_time_ico2"></i>
						</div></li>
					<li class="date_area"><span>离职时间:</span>
						<div class="time_bg">
							<input id="leaveDateStart" type="text" class="search_time150"
								name="propsMap['leaveDateStart']" mainid="leaveDateStart">
							<i class="search_time_ico2"></i>
						</div> <i>至</i>
						<div class="time_bg">
							<input id="leaveDateEnd" type="text" class="search_time150"
								name="propsMap['leaveDateEnd']" mainid="leaveDateEnd"> <i
								class="search_time_ico2"></i>
						</div></li>
					<li style="width: 180px;"><select class="search_choose"
						name="currState" id="currState" style="width: 100px;">
							<option value="">所有</option>
							<option value="1">在职</option>
							<option value="2">试用</option>
							<option value="3">离职</option>
							<option value="4">兼职</option>
					</select><span>在职状态:</span></li>
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
					<%-- <c:if test="${isHR=='true'}"> --%>
					<c:if test="${edit}">
						<li><a title="<m:message code="button.edit"/>"
							href="javascript:;" onclick="edit();"><i
								class="icon_bg icon_edit"></i> <span><m:message
										code="button.edit" /></span> </a></li>
						<li><a title="<m:message code="button.view"/>"
							href="javascript:" onclick="show();"> <i
								class="icon_bg icon_ckxq"></i> <span><m:message
										code="button.view" /></span>
						</a></li>
					</c:if>
					<c:if test="${delete}">
						<li><a title="<m:message code="button.delete"/>"
							href="javascript:;" onclick="batchDelete();"> <i
								class="icon_bg icon_del"></i> <span><m:message
										code="button.delete" /></span>
						</a></li>
					</c:if>


					<li><a href="javascript:;" onClick="expExcelWinShow();"> <i
							class="icon_bg icon_download"></i> <span>导出</span>
					</a></li>
					<c:if test="${manage}">
						<li><a title="<m:message code="button.module.moduleRes"/>"
							href="javascript:" onclick="moduleResMgt();"> <i
								class="back_icon resources_icon"></i> <span><m:message
										code="button.module.moduleRes" /></span>
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
