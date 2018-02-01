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
					<li style="width: auto; float: left;"><span>关键字:</span> <c:if
							test="${!isAdmin}">
							<input type="hidden" name="employeeId" id="employeeId"
								value="${user.id}">
						</c:if> <c:if test="${isAdmin}">
							<select class="search_choose" name="empDept" id="empDept">
								<c:if test="${!leader}">
									<option value="">所有部门</option>
								</c:if>
								<c:forEach var="org" items="${org}">
									<option value="${org.orgNo}">
										<c:out value="${org.orgName}"></c:out>
									</option>
								</c:forEach>
							</select>
							<input type="text" name="employeeName" id="employeeName"
								class="search_choose100" placeholder="员工姓名" autocomplete="off">
							<input type="text" name="nickName" id="nickName"
								class="search_choose100" placeholder="员工昵称" autocomplete="off">
						</c:if> <input type="text" name="stuName" id="stuName"
						class="search_choose100" placeholder="学生姓名"></li>
					<li><select class="search_choose" name="position"
						id="position">
							<option value="">--请选择--</option>
							<c:forEach var="followers" items="${followers}">
								<option value="${followers.position}">
									<c:out value="${followers.position}"></c:out>
								</option>
							</c:forEach>
					</select><span>参与岗位:</span></li>
					<li class="date_area"><span>发生时间:</span>
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
					<c:if test="${edit}">
						<li><a title="编辑学生信息" href="javascript:;" onclick="edit();"><i
								class="icon_bg icon_edit"></i> <span>编辑</span> </a></li>
					</c:if>
					<li><a title="<m:message code="button.view"/>"
						href="javascript:" onclick="show();"> <i
							class="icon_bg icon_ckxq"></i> <span><m:message
									code="button.view" /></span>
					</a></li>
					<li><a href="javascript:;" onClick="expExcelWinShow();"> <i
							class="icon_bg icon_download"></i> <span>导出</span>
					</a></li>
					<c:if test="${manage}">
						<li><a title="<m:message code="button.delete"/>"
							href="javascript:;" onclick="batchDelete();"> <i
								class="icon_bg icon_del"></i> <span><m:message
										code="button.delete" /></span>
						</a></li>
					</c:if>
				</ul>
			</div>

			<!--功能按钮end-->
			<div class="listtable_box" style="position: relative">
				<!--此处放表格-->
				<div style="display: inline-block; width: 69%; vertical-align: top;">
					<table id="remote_rowed"></table>
					<div id="remote_prowed"></div>
				</div>
				<div style="display: inline-block; width: 29%; vertical-align: top;">
					<table id="emp_table"></table>
					<div id="remote_prowed2"></div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	var gridObj = {};
	var empObj = {};
	var empObj2 = {};
	var revenueObj = {};
	$(function(){
		$("#startDate").val(new Date().format('yyyy-MM-01'));
		$("#endDate").val(new Date().format('yyyy-MM-dd'));
		gridObj = new biz.grid({
            id:"#remote_rowed",/*html部分table id*/
            url: "<m:url value='/empPerformance/listEmpPerformance.do'/>",/*grid初始化请求数据的远程地址*/
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
           	sortname:"create_date",
           	sortorder:"desc",
           	multiselect:true, 
           	multiboxonly:true,
           	forceFit:true,
           /* 	footerrow:true, */
           	pager: '#remote_prowed' /*分页栏id*/,
     		rowList:[10,15,50,100],//每页显示记录数
    		rowNum:10,//默认显示15条
            colModel:[
				{name : "id",hidden : true,key : true,label:"主键",index : "id"},			
				{name : "employeeName",label:"员工姓名",index : "employee_name",width:40, frozen : true},	 
				{name : "nickName",label:"员工昵称",index : "nickName",width:40, frozen : true},
				{name : "position",label:"参与岗位",width:40},	
				{name : "stuName",label:"学生姓名",index : "stuName",width:40},
				{name : "stuQq",label:"学生QQ",index : "stuQq",width:40},
				{name : "shouldPay",label:"学费",index : "should_pay",width:30},				
				{name : "actualPay",label:"已付",index : "actual_pay",width:30},				
				{name : "performance",label:"贡献绩效",index : "performance",width:20},
				{name : "createDate",label:"发生时间",index : "create_date",width:30},				
				{name : "note",label:"备注",index : "note",width:40}				
           	],
           	serializeGridData:function(postData){//添加查询条件值
				var obj = getQueryCondition();
    			$ .extend(true,obj,postData);//合并查询条件值与grid的默认传递参数
    			return obj;
    		},
			gridComplete:function(){//表格加载执行  
			    $(this).closest(".ui-jqgrid-bdiv").css({'overflow-x' : 'hidden'});
			 		/* $(".ui-jqgrid-sdiv").show();
				 	var footerCell = $(this).footerData();
				 	var footerObj = {};
				 	for(var i in footerCell){
				 		footerObj[i]=$(this).getCol(i,false,"sum")?$(this).getCol(i,false,"sum").toFixed(3):0;
				 	}
				 	footerObj['raw'] = true;
				 	footerObj['rn'] = "合";
				 	footerObj['cb'] = "计"; 
			    	$(this).footerData("set",footerObj); */ //将合计值显示出来
			}

      	});
        
  		
  		empObj = new biz.grid({
            id:"#emp_table",/*html部分table id*/
          	url: "<m:url value='/empPerformance/listByEmployee.do'/>",
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
           	multiselect:true,
           	multiboxonly:true,
           	pager: '#remote_prowed2' /*分页栏id*/,
     		rowList:[10,15,50,100],//每页显示记录数
    		rowNum:10,//默认显示15条
 			colModel:[
				{name : "employeeName",label:"员工姓名",index : "employee_name",width:20, frozen : true},	 
				{name : "nickName",label:"员工昵称",index : "nickName",width:20, frozen : true},
				{name : "sum",label:"贡献绩效总额",index : "sum",width:25},	
           	],
           	serializeGridData:function(postData){//添加查询条件值
				var obj = getQueryCondition();
    			$ .extend(true,obj,postData);//合并查询条件值与grid的默认传递参数
    			return obj;
    		},
    		gridComplete:function(){//表格加载执行  
			    $(this).closest(".ui-jqgrid-bdiv").css({'overflow-x' : 'hidden'});
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

    //新增的弹出框
	var add_iframe_dialog;
	 //补录的弹出框
	var addEmp_iframe_dialog;
	//修改的弹出框
   	var edit_iframe_dialog;
	//查看的弹出框
	var show_iframe_dialog;
	
  	
  //员工补录
  	function addEmp(){
  			var url="<m:url value='/empPerformance/toAddEmp.do'/>";
  			addEmp_iframe_dialog = new biz.dialog({
  				id:$('<div id="addEmpwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
  				modal: true,
  				width: $(window).width()*0.6,
  				height:$(window).height()*0.8,
  				title: "补录员工业绩"
  			});
  			addEmp_iframe_dialog.open();
  	  	}
  	  	
  	  	//关闭新增页面，供子页面调用
  	  	function closeAddEmp(){
  			addEmp_iframe_dialog.close();
  	  	}
  	
    function edit(){
		var key = ICSS.utils.getSelectRowData("id");
		if(key.indexOf(",")>-1||key==""){
			showMessage("请选择一条数据！");
			return ;
		}
		var url="<m:url value='/empPerformance/toEditEmpPerformance.do'/>?key="+key;
		edit_iframe_dialog = new biz.dialog({
		 	id:$('<div id="editwindow_iframe"></div>').html('<iframe id="iframeEdit" name="iframeEdit" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: $(window).width()*0.6,
			height:$(window).height()*0.8,
			title: "员工业绩编辑"
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
		var url="<m:url value='/empPerformance/toShowEmpPerformance.do'/>?key="+key;
		show_iframe_dialog = new biz.dialog({
		 	id:$('<div id="showwindow_iframe"></div>').html('<iframe id="iframeShow" name="iframeShow" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: /* 800 */ $(window).width()*0.6,
			height: /* 505 */ $(window).height()*0.8,
				title: "员工业绩详情"
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
    	doSearchForEmpObj(isStayCurrentPage);
    }
    function doSearchForEmpObj(isStayCurrentPage){
    	if(!isStayCurrentPage)
    		empObj.setGridParam({"page":"1"});
    	empObj.trigger('reloadGrid');
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
        				url: "<m:url value='/empPerformance/deleteEmpPerformance.do'/>?key="+ids,
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
    	ExpExcel.showWin(gridObj,baseUrl+"/empPerformance/exportExcel.do",'grid','queryForm');
    }
    
    </script>
</body>
</html>
