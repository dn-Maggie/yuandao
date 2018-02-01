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
			<c:if test="${common&&!straightLeader&&!deptLeader&&!headLeader}">
				<input type="hidden" name="empId" id="empId" value="${user.id}">
			</c:if>
			<c:if test="${straightLeader&&!deptLeader&&!headLeader}">
				<input type="hidden" name="straightLeader" id="straightLeader"
					value="${user.id}">
			</c:if>
			<c:if test="${deptLeader&&!headLeader}">
				<input type="hidden" name="deptLeader" id="deptLeader"
					value="${user.nickName}">
			</c:if>
			<div class="search border-bottom">
				<ul>
					<li><select class="search_choose" name="empDept" id="empDept">
							<c:forEach var="org" items="${org}">
								<option value="${org.orgNo}">
									<c:out value="${org.orgName}"></c:out>
								</option>
							</c:forEach>
					</select><span>所在部门:</span></li>
					<li><input type="text" name="empNAME" id="empNAME"
						class="search_choose"> <span>员工姓名:</span></li>
					<!-- 输入框-->
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
						name="leaveDate" id="leaveDate" style="width: 100px;">
							<option value="0">所有</option>
							<option value="2">三天以上</option>
					</select><span>请假期限:</span></li>
					<!-- 输入框-->
					<li style="width: 180px;"><select class="search_choose"
						name="leaveType" id="leaveType" mainid="leaveType"
						style="width: 100px;">
							<option value="">所有</option>
							<option value="1">事假</option>
							<option value="2">公假</option>
					</select><span>请假类型:</span></li>
					<!-- 输入框-->
					<li style="width: 180px;"><select class="search_choose"
						name="checkFlag" id="checkFlag" mainid="checkFlag"
						style="width: 100px;">
							<option value="">所有</option>
							<option value="1">未审核</option>
							<option value="2">已审核</option>
							<option value="3">已驳回</option>
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
			<!--功能按钮begin-->
			<div class="list_btn_bg fl">
				<!--功能按钮 div-->
				<ul>
					<c:if test="${straightLeader||deptLeader||headLeader||isAdmin}">
						<li><a title="审核" href="javascript:" onclick="audit();">
								<i class="icon_bg icon_audit"></i> <span>审核</span>
						</a></li>
					</c:if>
					<c:if test="${deptLeader||headLeader||isAdmin}">
						<li><a title="驳回审核" href="javascript:"
							onclick="cancelAudit();"> <i class="icon_bg icon_audit"></i>
								<span>驳回审核</span>
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
	<script type="text/javascript">
		var gridObj = {};
		$(function(){
	  		gridObj = new biz.grid({
	            id:"#remote_rowed",/*html部分table id*/
	            url: "<m:url value='/leaveApply/listLeaveApply.do'/>",/*grid初始化请求数据的远程地址*/
	            datatype: "json",/*数据类型，设置为json数据，默认为json*/
	        	sortname:"create_date",
	           	sortorder:"desc",
	           	pager: '#remote_prowed' /*分页栏id*/,
	     		rowList:[10,15,50,100],//每页显示记录数
	    		rowNum:10,//默认显示15条
	            colModel:[
					{name : "id",hidden : true,key : true,label:"主键",index : "id"},	
					{name : "empId",hidden : true},
					{name : "empName",label:"申请人",index : "empName"},	
					{name : "leaveType",label:"假别",index : "leave_type",
						formatter:function(cellvalue, options, rowObject){
			 				 if (cellvalue==1) {
			 				 	return '事假';
			 				 }else if (cellvalue==2) {
			 				 	return '公假';
			 				 }
			 		}},
					{name : "content",label:"请假事由",index : "content"},				
					{name : "startDate",label:"请假日期始",index : "leave_date_from"},				
					{name : "endDate",label:"请假日期止",index : "leave_date_to"},				
					{name : "leaveDate",label:"请假天数",index : "leave_date"},	
					{name : "deptLeader",label:"部门经理",index : "dept_leader"},		
					{name : "createDate",label:"申请日期",index : "create_date"},				
					{name : "straightLeaderName",label:"直接负责人",index : "straight_leader"},	
					{name : "deptLeader",label:"部门经理",index : "dept_leader"},		
					{name : "checkFlag",label:"审核标志",index : "check_flag",
						formatter:function(cellvalue, options, rowObject){
			 				 if (cellvalue==1) {
			 				 	return '待审核';
			 				 }else if (cellvalue==2) {
			 				 	return '已审核';
			 				 }else if (cellvalue==3) {
			 				 	return '已驳回';
			 				 }
		 			}}, 
			 		{name : "checkName",label:"审核人",index : "checkName"}, 
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
	
		//审核的弹出框
		var audit_iframe_dialog;
		//驳回审核的弹出框
		var cancelAudit_iframe_dialog;
		
		//审核方法
	    function audit(){
	    	var key = ICSS.utils.getSelectRowData("id");
	    	var empId = ICSS.utils.getSelectRowData("empId");
	    	var checkFlag = ICSS.utils.getSelectRowData("checkFlag");
	    	if(key==""){
	    		showMessage("请至少选择一条数据！");
	    		return ;
	    	}
	    	if(checkFlag.indexOf("已驳回")>-1||checkFlag.indexOf("已审核")>-1){
				showMessage("选项中包含不可审核的数据，请重新选择");
				return ;
			}
	    	if(empId.indexOf($("#empId").val())>-1){
    			showMessage("无法审核自己的申请！");
    			return;
    		}
	    	else if(key.indexOf(",")>-1){
	    		new biz.alert({type:"confirm",message:"确定审核多条数据？",title:I18N.promp,callback:function(result){
	    			if(result){
	    				$ .ajax({
	        				url: "<m:url value='/leaveApply/auditLeaveApply.do'/>?key="+key,
	        				cache:false,
	        				success: function(data, textStatus, jqXHR){
	        					doSearch();
	    						showInfo("审核成功",3000);
	        				}
	        			});
	    			}
	    		}}) ;   
	    	}else {
	    		var url="<m:url value='/leaveApply/toAuditLeaveApply.do'/>?key="+key+"&empId="+empId;
	    		audit_iframe_dialog = new biz.dialog({
				 	id:$('<div id="auditwindow_iframe"></div>').html('<iframe id="iframesettlement" name="iframesettlement" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
					modal: true,
					width: $(window).width()*0.6,
					height:$(window).height()*0.6,
					title: "审核请假条"
				});
				audit_iframe_dialog.open();
	    	}    	
	    }
	  	//关闭审核页面，供子页面调用
	    function closeAudit(){
	    	audit_iframe_dialog.close();
	    }
	  	//驳回审核方法
	    function cancelAudit(){
	    	var key = ICSS.utils.getSelectRowData("id");
	    	var empId = ICSS.utils.getSelectRowData("empId");
	    	var checkFlag = ICSS.utils.getSelectRowData("checkFlag");
	    	if(key==""){
	    		showMessage("请至少选择一条数据！");
	    		return ;
	    	}
    		new biz.alert({type:"confirm",message:"确定驳回数据？",title:I18N.promp,callback:function(result){
    			if(result){
    				$ .ajax({
        				url: "<m:url value='/leaveApply/cancelAuditLeaveApply.do'/>?key="+key,
        				cache:false,
        				success: function(data, textStatus, jqXHR){
        					doSearch();
    						showInfo("审核驳回成功",3000);
        				}
        			});
    			}
    		}}) ;   
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
	    //删除方法
	    function batchDelete(){
	    	var ids = ICSS.utils.getSelectRowData("id");
	    	if(ids==""){
	    		showMessage("请至少选择一条数据！");
	    		return ;
	    	}else{
	    		var checkFlag = ICSS.utils.getSelectRowData("checkFlag");
	    		if(checkFlag.indexOf("已审核")>-1){
	    			showMessage("选项中包含已审核的数据，不能删除");
	    			return ;
	    		}
	    		new biz.alert({type:"confirm",message:I18N.msg_del_confirm,title:I18N.promp,callback:function(result){
	    			if(result){
	    				$ .ajax({
	        				url: "<m:url value='/leaveApply/deleteLeaveApply.do'/>?key="+ids,
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
	    </script>
</body>
</html>
