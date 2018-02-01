<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<title></title>
<script type="text/javascript">
var gridObj = {};

var Model = {
		url: "<m:url value='/fixedAsset/listFixedAsset.do'/>",
		colModel:[
					{name : "id",hidden : true,key : true,label:"主键",index : "id"},				
					{name : "assetNo",label:"资产编号",index : "asset_no"},				
					{name : "assetName",label:"资产名称",index : "asset_name"},	
					{name : "useOrg",label:"使用部门",index : "use_org"},
					{name : "workNumber",label:"使用人",index : "work_number"},
					{name : "assetItemId",label:"资产项目名称",index : "asset_item_id"},				
					{name : "model",label:"规格型号",index : "model"},				
					{name : "beginDate",label:"开始使用日期",index : "begin_date"},				
					{name : "initialValue",label:"资产原值",index : "initial_value"},		
					{name : "perDepred",index : "per_depred",label:"本期折旧 "},	
					{name : "propertyState",label:"资产状态 ",index : "property_state",align:"center",formatter:GridColModelForMatter.fixedPropertyState}	
	           	]
		};
	$(function(){
  		gridObj =  List.createGrid(Model.url,Model.colModel,"asset_no", false);
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
	//累计折旧的弹出框
	var depre_iframe_dialog;
	//添加资产项目的弹出框
	var assetitem_iframe_dialog;
	//添加资源回收目的弹出框
	var recovery_iframe_dialog;
  	
  	function add(){
  		var url = "<m:url value='/fixedAsset/toAddFixedAsset.do'/>";
		var title = "公司资源信息增加";
		add_iframe_dialog = Add.create(url, title,$(window).width()*0.8,$(window).height()*0.8);
		List.openDialog(add_iframe_dialog);
  	}
  	
  	//关闭新增页面，供子页面调用
  	function closeAdd(){
  		List.closeDialog(add_iframe_dialog,gridObj);
  	}
  	
    function edit(){
    	var key = ICSS.utils.getSelectRowData("id");
		var url = "<m:url value='/fixedAsset/toEditFixedAsset.do'/>";
		var title = "公司资源信息编辑";
		edit_iframe_dialog = Edit.create(key, url, title,$(window).width()*0.8,$(window).height()*0.8);
		List.openDialog(edit_iframe_dialog);
    }
    
    //关闭编辑页面，供子页面调用
    function closeEdit(){
    	List.closeDialog(edit_iframe_dialog,gridObj);
    }
    
    function show(){
    	var key = ICSS.utils.getSelectRowData("id");
		var url = "<m:url value='/fixedAsset/toShowFixedAsset.do'/>";
		var title = "公司资源信息详情";
		show_iframe_dialog = Show.create(key, url, title,$(window).width()*0.6,$(window).height()*0.67);
		List.openDialog(show_iframe_dialog);
    }
    
    //关闭查看页面，供子页面调用
    function closeShow(){
    	List.closeDialog(show_iframe_dialog);
    }
    
    //计提折旧
    function depre(){
    	var key = ICSS.utils.getSelectRowData("id");
		var url = "<m:url value='/fixedAsset/toShowFixedAsset.do'/>";
		var title = "计提折旧";
		depre_iframe_dialog = Edit.create(key, url, title,$(window).width()*0.6,$(window).height()*0.67);
		List.openDialog(depre_iframe_dialog);
    }
    
    //关闭查看页面，供子页面调用
    function closeDepre(){
    	List.closeDialog(depre_iframe_dialog);
    }
    //添加资产项目
    function assetitem(){
    	var url = "<m:url value='/fixedAsset/toAddAssetItem.do'/>";
		var title = "添加资产项目";
		assetitem_iframe_dialog = Add.create(url, title,$(window).width()*0.35,$(window).height()*0.6);
		List.openDialog(assetitem_iframe_dialog);
    }
    
    //关闭查看页面，供子页面调用
    function closeAssetitem(){
    	List.closeDialog(assetitem_iframe_dialog);
    }
    //待回收的资源
    function recovery(){
    	var url="<m:url value='/fixedAsset/toRecovery.do'/>";
		var title = "待回收的资源";
		recovery_iframe_dialog = Add.create(url, title,$(window).width()*0.6,$(window).height()*0.45);
		List.openDialog(recovery_iframe_dialog);
    }
    
    //关闭查看页面，供子页面调用
    function closeRecovery(){
    	List.closeDialog(recovery_iframe_dialog);
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
    //删除
    function batchDelete(){
    	var id = ICSS.utils.getSelectRowData("id");
		var url = "<m:url value='/fixedAsset/deleteFixedAsset.do'/>";
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
					<!-- 				<li><span>开始日期:</span>
						<div class="time_bg">
						<input type="text" class="search_time150" name="actTime" id="actTime" mainid="actTime">时间选择控件
						<i class="search_time_ico2"></i>
						</div></li> -->
					<li class="date_area"><span>使用日期:</span>
						<div class="time_bg">
							<input id="startDate" type="text" class="search_time150"
								name="propsMap['startDate']"> <i
								class="search_time_ico2"></i>
						</div> <i>至</i>
						<div class="time_bg">
							<input id="endDate" type="text" class="search_time150"
								name="propsMap['endDate']"> <i class="search_time_ico2"></i>
						</div></li>
					<li style="width: 300px"><span>资产项目名称:</span> <select
						class="input_select text" name="assetItemId" id="edit_assetType">
							<option value="">所有</option>
							<c:forEach var="assetItem" items="${assetItem}">
								<option value="${assetItem.id}">${assetItem.assetName}</option>
							</c:forEach>
					</select></li>
					<li style="width: 300px"><span>资产状态:</span> <select
						class="input_select text" name="propertyState"
						id="edit_propertyState">
							<option value="">所有</option>
							<option value="1">使用中</option>
							<option value="4">停用中</option>
							<option value="2">维修中</option>
							<option value="3">已报废</option>
					</select></li>
					<li><input type="reset" class="reset_btn"
						onclick="List.resetForm('queryForm')" value="重置">
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
					<%-- 					<c:if test="${manage}">
						<li><a title="计提折旧" href="javascript:"
							onclick=";"> <i class="icon_bg icon_submit"></i> <span>计提折旧</span>
						</a></li>
						</c:if> --%>
					<li><a title="添加资产项目" href="javascript:"
						onclick="assetitem();"> <i class="icon_bg icon_submit"></i> <span>添加资产项目</span>
					</a></li>
					<li><a title="资源回收" href="javascript:" onclick="recovery();">
							<i class="icon_bg icon_submit"></i> <span>资源回收</span>
					</a></li>
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
