<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML  >
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<title></title>
<script type="text/javascript">
var gridObj = {};
var Model = {
		url: "<m:url value='/dutyLevel/listDutyLevel.do'/>",
		colModel:[
					{name : "id",hidden : true,key : true,label:"主键",index : "id"},				
					{name : "dutyId",label:"岗位ID",index : "duty_id"},				
					{name : "levelType",label:"等级类型（P/T/D/J）",index : "level_type"},				
					{name : "levelName",label:"评定登记级别",index : "level_name"},				
					{name : "levelSal",label:"薪级工资",index : "level_sal"}				
	           	]
		};
	$(function(){
  		gridObj = List.createGrid(Model.url,Model.colModel,"id", false);
        
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
	//修改的弹出框
   	var edit_iframe_dialog;
	//查看的弹出框
	var show_iframe_dialog;
  	
  	function add(){
  		var url = "<m:url value='/dutyLevel/toAddDutyLevel.do'/>";
		var title = "岗位级别表增加";
		add_iframe_dialog = Add.create(url, title,800,235);
		List.openDialog(add_iframe_dialog);
  	}
  	
  	//关闭新增页面，供子页面调用
  	function closeAdd(){
  		List.closeDialog(add_iframe_dialog,gridObj);
  	}
  	
    function edit(){
    	var key = ICSS.utils.getSelectRowData("id");
		var url = "<m:url value='/dutyLevel/toEditDutyLevel.do'/>";
		var title = "岗位级别表编辑";
		edit_iframe_dialog = Edit.create(key, url, title,800,255);
		List.openDialog(edit_iframe_dialog);
    }
    
    //关闭编辑页面，供子页面调用
    function closeEdit(){
    	List.closeDialog(edit_iframe_dialog,gridObj);
    }
    
    function show(){
    	var key = ICSS.utils.getSelectRowData("id");
		var url = "<m:url value='/dutyLevel/toShowDutyLevel.do'/>";
		var title = "岗位级别表详情";
		show_iframe_dialog = Show.create(key, url, title,800,235);
		List.openDialog(show_iframe_dialog);
    }
    
    //关闭查看页面，供子页面调用
    function closeShow(){
    	List.closeDialog(show_iframe_dialog);
    }
    
    //删除
    function batchDelete(){
    	var id = ICSS.utils.getSelectRowData("id");
		var url = "<m:url value='/dutyLevel/deleteDutyLevel.do'/>";
		List.batchDelete(id, url,gridObj);
    }
    </script>
</head>
<body style="height: 100%;">

	<div class="main  choice_box">
		<form id="queryForm">
			<!-- 查询区 表单 -->
			<div class="search border-bottom">
				<ul>
					<li><input type="text" name="actorName" id="actorName"
						class="search_choose"> <span>操作人:</span></li>
					<!-- 输入框-->
					<li><span>开始日期:</span>
						<div class="time_bg">
							<input type="text" class="search_time150" name="actTime"
								id="actTime" mainid="actTime">
							<!-- 时间选择控件-->
							<i class="search_time_ico2"></i>
						</div></li>
					<li class="date_area"><span>日期:</span>
						<div class="time_bg">
							<input id="startDate" type="text" class="search_time150"
								name="propsMap['startDate']"> <i
								class="search_time_ico2"></i>
						</div> <i>至</i>
						<div class="time_bg">
							<input id="endDate" type="text" class="search_time150"
								name="propsMap['endDate']"> <i class="search_time_ico2"></i>
						</div></li>
					<li><input type="text" name="actResult" id="actResult"
						class="search_choose"> <span>操作结果:</span></li>
					<!-- 输入框-->
					<li><input type="reset" class="reset_btn"
						onclick="resetForm('queryForm')" value="重置">
					<!-- 重置 --> <input type="button" class="search_btn mr22 "
						onclick="List.doSearch(gridObj);" value="查询"></li>
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
					<c:if test="${edit}">
						<li><a title="<m:message code="button.edit"/>"
							href="javascript:;" onclick="edit();"><i
								class="icon_bg icon_edit"></i> <span><m:message
										code="button.edit" /></span> </a></li>
					</c:if>
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
