<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML  >
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<title><m:message code="moduleRes.list.title" /></title>
</head>
<body>
	<%@ include file="../../common/common.jsp"%>
	<div class="main">
		<div class="barTitle">
			<div class="content">
				<a href="javascript:;" onfocus="this.blur();"></a> <span><m:message
						code="moduleRes.list.title" /></span> <a id="displayHidden"
					class="displayHidden" onclick="this.blur();" href="javascript:;"><m:message
						code='query.hideCondition' /></a>
			</div>
		</div>
		<hr class="barTitleHr"></hr>
		<div id="conditions"
			class="ui-table ui-widget ui-corner-all ui-margin"
			style="display: block">
			<form id="moduleResForm">
				<div class="equal">
					<div class="row">
						<div class="cell">
							<div class="labelcell">
								<m:message code="moduleRes.queryForm.resourceName" />
								:
							</div>
							<div class="valuecell">
								<input id="resourceName" name="resourceName" type="text"
									class="text" value="<c:out value='${moduleRes.resourceName}'/>" />
							</div>
						</div>
						<div class="cell">
							<div class="labelcell">
								<m:message code="moduleRes.queryForm.resourcecode" />
								:
							</div>
							<div class="valuecell">
								<input id="resourcecode" name="resourcecode" type="text"
									class="text" value="<c:out value='${moduleRes.resourcecode}'/>" />
							</div>
						</div>
						<div class="cell">
							<div class="labelcell">
								<m:message code="moduleRes.queryForm.needright" />
								:
							</div>
							<div class="valuecell">
								<select id="needright" name="needright" class="select"></select>
							</div>
						</div>
						<div class="cell">
							<div class="labelcell">
								<m:message code="moduleRes.queryForm.status" />
								:
							</div>
							<div class="valuecell">
								<select id="status" name="status" class="select"></select>
							</div>
						</div>
						<div class="querycell">
							<button id="query_button" type="button" onclick="query();">
								<m:message code='button.search' />
							</button>
							<button id="rest_button" type="button" onclick="resetForm();">
								<m:message code='button.reset' />
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="ui-table ui-widget ui-corner-all ui-margin">
			<div class="nav">
				<c:if test="${sys_res_add}" var="addFlagValue">
					<button id="addBtn" onclick="add();">
						<m:message code='button.add' />
					</button>
				</c:if>
				<c:if test="${sys_res_edit}" var="editFlagValue">
					<button id="editBtn" onclick="edit();">
						<m:message code='button.edit' />
					</button>
				</c:if>
				<c:if test="${sys_res_delete}" var="delFlagValue">
					<button id="deleteBtn" onclick="batchDelete();">
						<m:message code='button.delete' />
					</button>
				</c:if>
				<%--         		   <button id="viewBtn" onclick="view();"><m:message code='button.view'/></button> --%>
				<%--         		   <button id="exportBtn" onclick="excelExport();"><m:message code='button.export'/></button> --%>
				<c:if test="${addResact}" var="addResactFlagValue">
					<button id="addResactBtn" onclick="openResactView();">编辑/添加资源按钮</button>
				</c:if>

			</div>
			<table id="moduleResTable"></table>
			<div id="moduleResPager"></div>
		</div>
	</div>

	<div id="editDialog">
		<form id="moduleResFormEdit">
			<div class="ui-table ui-widget ui-corner-all ui-border">
				<input type="hidden" id="edit_resuuid" name="resuuid" type="text"
					value="<c:out value='${moduleRes.resuuid}'/>" />
				<table class="table">
					<tr>

						<td class="inputLabelTd"><m:message
								code="moduleRes.form.resourceName" />:</td>
						<td class="inputTd"><input id="edit_resourceName"
							name="resourceName" type="text" class="text"
							value="<c:out value='${moduleRes.resourceName}'/>" /></td>
						<td class="inputLabelTd"><m:message
								code="module.form.serialindex" />:</td>
						<td class="inputTd"><input id="edit_serialindex"
							name="serialindex" type="text" class="text"
							value="<c:out value='${moduleRes.serialindex}'/>" /></td>

					</tr>
					<tr>
						<td class="inputLabelTd"><m:message
								code="moduleRes.form.resourcecode" />:</td>
						<td class="inputTd"><input id="edit_resourcecode"
							name="resourcecode" type="text" class="text"
							value="<c:out value='${moduleRes.resourcecode}'/>" /></td>
						<td class="inputLabelTd"><m:message
								code="moduleRes.form.resurl" />:</td>
						<td class="inputTd"><input id="edit_resurl" name="resurl"
							type="text" class="text"
							value="<c:out value='${moduleRes.resurl}'/>" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><m:message
								code="moduleRes.form.status" />:</td>
						<td class="inputTd"><select id="edit_status" name="status"
							class="select"></select></td>

						<td class="inputLabelTd"><m:message
								code="moduleRes.form.needright" />:</td>
						<td class="inputTd"><select id="edit_needright"
							name="needright" class="select"></select></td>

					</tr>
					<tr>
						<td class="inputLabelTd"><m:message
								code="moduleRes.form.memo" />:</td>
						<td class="inputTd" colspan="3"><input id="edit_memo"
							name="memo" type="text" class="text-100"
							value="<c:out value='${moduleRes.memo}'/>" /></td>

					</tr>
				</table>
			</div>
		</form>
	</div>

	<div id="addResactDialog" style="display: block">
		<form id="addResactForm">
			<input type="hidden" id="resuuid" name="resuuid" type="text" />
			<div class="ui-table ui-widget ui-corner-all ui-border">
				<table class="table">
					<tr>
						<td class="inputTd" colspan="4">
							<div class="nav" style="height: 20px; width: 100%;">
								<button id="addRtBtn" type="button" onclick="addResact();">添加按钮</button>
								<button id="editRtBtn" type="button" onclick="editResact();">编辑按钮</button>
								<button id="delRtBtn" type="button" onclick="delResact();">删除按钮</button>
							</div>
							<table id="resactTable"></table>
							<div id="resactPager"></div>
						</td>

					</tr>
				</table>
			</div>
		</form>
	</div>

	<div id="editResactDialog">
		<form id="resactFormEdit">
			<div class="ui-table ui-widget ui-corner-all ui-border">
				<input type="hidden" id="edit1_actid" name="actid" type="text"
					value="<c:out value='${resact.actid}'/>" /> <input type="hidden"
					id="edit1_resuuid" name="resuuid" type="text"
					value="<c:out value='${resact.resuuid}'/>" />
				<table class="table">
					<tr>
						<td class="inputLabelTd">资源操作名称:</td>
						<td class="inputTd" colspan="3"><input id="edit1_actname"
							name="actname" type="text" class="text"
							value="<c:out value='${resact.actname}'/>" /></td>

					</tr>
					<tr>
						<td class="inputLabelTd">资源操作编码:</td>
						<td class="inputTd"><input id="edit1_actcode" name="actcode"
							type="text" class="text"
							value="<c:out value='${resact.actcode}'/>" /></td>

						<td class="inputLabelTd">资源操作URL:</td>
						<td class="inputTd"><input id="edit1_acturl" name="acturl"
							type="text" class="text"
							value="<c:out value='${resact.acturl}'/>" /></td>

					</tr>
					<tr>
						<td class="inputLabelTd"><m:message
								code="moduleRes.form.status" />:</td>
						<td class="inputTd"><select id="edit1_status" name="status"
							class="select"></select></td>

						<td class="inputLabelTd"><m:message
								code="moduleRes.form.needright" />:</td>
						<td class="inputTd"><select id="edit1_needright"
							name="needright" class="select"></select></td>

					</tr>
					<tr>
						<td class="inputLabelTd"><m:message
								code="moduleRes.form.memo" />:</td>
						<td class="inputTd" colspan="3"><input id="edit1_remark"
							name="remark" type="text" class="text"
							value="<c:out value='${resact.remark}'/>" /></td>

					</tr>
				</table>
			</div>
		</form>
	</div>

	<div id="showDialog"></div>
	<div id="errorDialog"></div>

</body>
</html>
<script type="text/javascript">
	new biz.button({id:"#query_button", icons: {primary:"ui-icon-search"}});
	new biz.button({id:"#rest_button", icons: {primary:"ui-icon-refresh"}});
	new biz.button({id:"#addBtn", icons: {primary:'ui-icon-plus'}});
	new biz.button({id:"#editBtn", icons: {primary:'ui-icon-pencil'}});
	//new biz.button({id:"#viewBtn", icons: {primary:'ui-icon-document'}});
	new biz.button({id:"#deleteBtn", icons: {primary:'ui-icon-trash'}});
	//new biz.button({id:"#exportBtn", icons: {primary:'ui-icon-disk'}});
	new biz.button({id:"#addResactBtn", icons: {primary:'ui-icon-pencil'}});
	new biz.button({id:"#addRtBtn", icons: {primary:'ui-icon-plus'}});
	new biz.button({id:"#delRtBtn", icons: {primary:'ui-icon-trash'}});
	new biz.button({id:"#editRtBtn", icons: {primary:'ui-icon-pencil'}});
	

	/*编辑表单数据验证*/
	new biz.validate({id:"#moduleResFormEdit",rules:{
		resourceName: {required: true,maxlength:120},
		resourcecode: {required: true,maxrealitylength:20},
		resurl: {required: true,maxrealitylength:200}
		}
	}); 

	/*编辑表单数据验证*/
	new biz.validate({id:"#resactFormEdit",rules:{
		actname: {required: true,maxlength:15},
		actcode: {required: true,maxrealitylength:16},
		acturl: {required: true,maxrealitylength:200}
		}
	}); 
	
	//显示或隐藏查询条件
    $(document).ready(function(){
		$("#displayHidden").click(function(){
		       var conditionsDiv = document.getElementById("conditions");
		       var displayHidden = document.getElementById("displayHidden");
		       if(conditionsDiv.style["display"]=="block"){
		          conditionsDiv.style["display"]="none";
		          displayHidden.innerHTML="<m:message code='query.ExpandCondition'/>";
		       }else if(conditionsDiv.style["display"]=="none"){
		          conditionsDiv.style["display"]="block";
		          displayHidden.innerHTML="<m:message code='query.hideCondition'/>";
		       }
		});
	});
	
	var moduleResGrid;
    window.onload = function(){
    	moduleResGrid = new biz.grid({
    		id : "#moduleResTable",
    		url : "moduleRes/listModuleRes",
			rowNum : 15,
			width:"auto",
			multiselect:true,
    		pager : "#moduleResPager",
    		prmNames : {page:"pageNo",rows:"pageSize",sort:"orderFields",order:"order"},
    		sortname : "resource_Name",
    		sortorder : "asc",
    		colModel : [
    			{name : "resuuid", hidden:true,key:true}, 
// 				{name : "resuuid",formatter:"showlink",formatoptions:{baseLinkUrl:"#",click:view},label:"<m:message code="moduleRes.queryResult.resuuid"/>"},
				{name : "resourceName",formatter:"showlink",formatoptions:{baseLinkUrl:"#",click:view},label:"<m:message code="moduleRes.queryResult.resourceName"/>",align:"left",width:120,index:"resource_Name"},
				{name : "resourcecode",label:"<m:message code="moduleRes.queryResult.resourcecode"/>",align:"left",width:80},
				{name : "resurl",label:"<m:message code="moduleRes.queryResult.resurl"/>",align:"left",width:120},
				{name : "status",label:"<m:message code="moduleRes.queryResult.status"/>",formatter:ma_status,align:"center",width:40},
				{name : "needright",label:"<m:message code="moduleRes.queryResult.needright"/>",formatter:ma_needright,align:"center",width:40},
				//{name : "muuid",label:"<m:message code="moduleRes.queryResult.muuid"/>",align:"left",width:120},
				{name : "serialindex",label:"<m:message code="moduleRes.queryResult.serialindex"/>",align:"left",width:40},
				{name : "memo",label:"<m:message code="moduleRes.queryResult.memo"/>"}
    		],
    		exportParams:{//Excel导出参数配置
        		url : "moduleRes/exportExcel",
                customHeader : {
                	title : "<m:message code="moduleRes.list.title"/>"
                },
                queryColumns :[
					{name : "resourceName", label : "资源名称"},
					{name : "resourcecode", label : "资源编码"},
					{name : "needright", label : "是否需要权限"},
					{name : "status", label : "状态"}
                ],
                columns : [//导出哪些列，可以自己酌情增删
					{name : "resuuid",label:"<m:message code="moduleRes.queryResult.resuuid"/>"},
					{name : "resourceName",label:"<m:message code="moduleRes.queryResult.resourceName"/>"},
					{name : "resourcecode",label:"<m:message code="moduleRes.queryResult.resourcecode"/>"},
					{name : "resurl",label:"<m:message code="moduleRes.queryResult.resurl"/>"},
					{name : "status",label:"<m:message code="moduleRes.queryResult.status"/>"},
					{name : "needright",label:"<m:message code="moduleRes.queryResult.needright"/>"},
					{name : "memo",label:"<m:message code="moduleRes.queryResult.memo"/>"}
                ]
            },
    		serializeGridData:function(postData){//添加查询条件值
				var obj = getQueryCondition();
    			$.extend(true,obj,postData);//合并查询条件值与grid的默认传递参数
    			return obj;
    		}
    	});
    }

    function ma_status(cellvalue, options, rowObject){
    	if(cellvalue=='1'){
			return "启用";
		}else if(cellvalue=='0'){
			return "禁用";
		}
    }

    function ma_needright(cellvalue, options, rowObject){
    	if(cellvalue=='1'){
			return "是";
		}else if(cellvalue=='0'){
			return "否";
		}
    }
    
    function getQueryCondition(){
        var obj = {};
		addAttrToObject(obj,"resourceName");
		addAttrToObject(obj,"resourcecode");
		addAttrToObject(obj,"needright");
		addAttrToObject(obj,"status");
		return obj;
    }
	
	function addAttrToObject(obj,name){
		element = document.getElementById(name);
		var notInputValue="";
		if(element!=null){
			if(element.value==null||element.value == ""){
				if($(element).attr("uiType")!=null){
					if($(element).attr("uiType")=="checkbox"){
						var tempCheckBoxObj = $(element).find(".checkbox");
						if(tempCheckBoxObj!=null && tempCheckBoxObj.length>0){
							tempCheckBoxObj.each(function(){
								if(this.checked) {
									notInputValue  += this.value+",";
								}
							  });
							if(notInputValue!=null && notInputValue.indexOf(",")!=-1){
								notInputValue = notInputValue.substring(0,notInputValue.length-1);
							}
					
						}
					}else if($(element).attr("uiType")=="radio"){
						var tempRadioObj = $(element).find(".radio");
						if(tempRadioObj!=null && tempRadioObj.length>0){
							  tempRadioObj.each(function(){
								if(this.checked) {
									notInputValue  = this.value;
									return;
								}
							  });
						}		  
					}
					if(notInputValue == null || notInputValue == "" ){
						  return;
					}
				}else{
                   return;
				}
			}

		}else{
			return;
		}

		obj = obj || {}; 
		var value = element.value!=null?element.value:notInputValue;
        if(element.name == 'resourcecode'){
			value = '%' + value + '%';
		}
        if(element.name == 'resourceName'){
			value = '%' + value + '%';
		}
		obj[name] = value;
	}

	var resactGrid = new biz.grid({
	    id:"#resactTable",
	    datatype: "local",/*数据类型设置为本地数据,默认不加载数据*/
	    url : "<c:url value='/resact/listResact'/>",
	    rowNum : 15,
	 	width: "760",
	 	height:"220",
	 	minHeight:false,
	 	sortname : "actname",
		sortorder : "desc",
	   // rownumbers:true,
	   	pager : "#resactPager",
	    rowNum : 999,
	    colModel : [
			{name : "actid",hidden:true,key:true},
			{name : "actname",label:"资源操作名称",align:"right"},
			{name : "actcode",label:"资源操作编码",width: "50",align:"left"},
			{name : "acturl",label:"资源操作URL",align:"left"},
			{name : "status",label:"状态",formatter:ma_status,width: "40",align:"center"},
			{name : "needright",label:"是否需要权限",formatter:ma_needright,width: "50",align:"center"},
			{name : "remark",label:"备注",width: "50"}
        ],
		serializeGridData:function(postData){//添加查询条件值
			var obj = {};
	        obj["resuuid"] = $("#resuuid").val();
			$.extend(true,obj,postData);//合并查询条件值与grid的默认传递参数
			return obj;
		}
	});
	
	function query(){
		moduleResGrid.setGridParam({"page":"1"});
		moduleResGrid.trigger('reloadGrid');
	}

	function resetForm(){
		document.getElementById('moduleResForm').reset();
	}

	//导出Excel
	function excelExport(){
		moduleResGrid.excelExport(getQueryCondition());
	}
	var isEdit = true;//true:当前对话框为编辑操作  false:当前对话框为新增操作
	var editDialog = new biz.dialog({
		id:"#editDialog",
		autoOpen:false,
		title:"<m:message code="moduleRes.form.title"/>",
		width:"70%",
		modal:true,
		buttons: [
			{
				text: I18N.save,
				click: function() {
					if(isEdit){
						update();
					}else{
						save(); 
					}
				}
			 },
			{text: I18N.cancel,click: function() { editDialog.close();}}
		]
	});
	var showDialog;
	var showDialogOption = {
		id:"#showDialog",
		autoOpen:false,
		title:"<m:message code="moduleRes.form.title"/>",
		width:"70%",
		modal:true,
		buttons: [
			{
				text: I18N.close,
				click: function() { 
					showDialog.close();
					showDialog.destroy();
				}
			}
		]
	};


	var addResactDialog = new biz.dialog({
		id:"#addResactDialog",
		autoOpen:false,
		title:"资源按钮列表",
		width:"60%",
		modal:true,
		buttons: [
			/*{
				text: I18N.save,
				click: function() {
					saveUsers();
				}
			 },*/
			{text: I18N.close,click: function() { addResactDialog.close();}}
		]
	});

	function openResactView(){
		var rowid = moduleResGrid.getGridParam("selarrrow");
		if(rowid == null || rowid.length == 0){
			showInfo("<m:message code='grid.edit.chooseColAlert'/>",3000);
			return ;
		}else if(rowid.length > 1){
			showInfo("<m:message code='grid.edit.onlyOne'/>",3000);
			return ;
		}
		var id = moduleResGrid.getCell(rowid[0],"resuuid");
		$('#resuuid').val(id);
		resactGrid.clearGridData();
		resactGrid.setGridParam({datatype:'json'}).trigger('reloadGrid');
		addResactDialog.open();
	}
	
	function add(){
		isEdit = false;
		document.getElementById("moduleResFormEdit").reset();
		$("#moduleResFormEdit").find("input[type='file']").each(function(){
			   $(this).file("reset");
		});
		editDialog.open();
	}

	function save(){
		if(!biz.validate("valid","#moduleResFormEdit")){
			showWarn("<m:message code='validation.object'/>",3000);
			return;
		}

		$.ajax({
			url: "<c:url value='/moduleRes/checkIsName'/>",
			data: {cname:$.trim($('#edit_resourceName').val())},
			type: "POST",
			success: function(data, textStatus, jqXHR){
				if(data){
					$.ajax({
						url: "<c:url value='/moduleRes/save'/>",
						data: $('#moduleResFormEdit').serialize(),
						type: "POST",
						success: function(data, textStatus, jqXHR){
							editDialog.close();
							query();
							showInfo("<m:message code='add.success'/>",3000);
						},
						error:errorInfo
					});
				}else{
					showWarn("该资源名称已存在,请重新填写!",3000);
                	return;
				}
			},
			error:errorInfo
		});
	}
	
	function edit(rowid){
		isEdit = true;
		rowid = moduleResGrid.getGridParam("selarrrow");
		if(rowid == null || rowid.length == 0){
			showInfo("<m:message code='grid.edit.chooseColAlert'/>",3000);
			return ;
		}else if(rowid.length > 1){
			showInfo("<m:message code='grid.edit.onlyOne'/>",3000);
			return ;
		}
		var id = moduleResGrid.getCell(rowid[0],"resuuid");
		var dataUrl = "moduleRes/edit/" + id;
		$.ajax({
			url: dataUrl,
			dataType:"json",
			cache:false,
			success: function(data, textStatus, jqXHR){
				for(var attr in data){
						var element = document.getElementById("edit_" + attr);
						if(element != null){
							element.value = data[attr];
					}
				}
				editDialog.open();
			},
			error:errorInfo
		});
	}
	
	function update(){
		if(!biz.validate("valid","#moduleResFormEdit")){
			showWarn("<m:message code='validation.object'/>",3000);
			return;
		}

		$.ajax({
			url: "<c:url value='/moduleRes/update'/>",
			data: $('#moduleResFormEdit').serialize(),
			type: "POST",
			success: function(data, textStatus, jqXHR){
				rowid = moduleResGrid.getGridParam("selrow");
				moduleResGrid.setRowData(rowid,data);
				editDialog.close();
				showInfo("<m:message code='add.success'/>",3000);
			},
			error:errorInfo
		});

	}
	function view(rowid){
		if(rowid == null){
			var rows = moduleResGrid.getGridParam("selarrrow");
    		if(rows == null || rows.length == 0){
    			showInfo("<m:message code='grid.view.chooseColAlert'/>",3000);
    			return ;
    		}else if(rows.length > 1){
    			showInfo("<m:message code='grid.view.onlyOne'/>",3000);
    			return ;
    		}
			rowid = rows[0];
		}
		var id = moduleResGrid.getCell(rowid,'resuuid');
		var dataUrl = "moduleRes/show?id=" + id;
		$.ajax({
			url: dataUrl,
			cache:false,
			success: function(data, textStatus, jqXHR){
				showDialog = new biz.dialog(showDialogOption);
				$(showDialog.id).html(data)
				showDialog.open();
			},
			error:errorInfo
		});
	}
	function batchDelete(rowid){
		var rowid = moduleResGrid.getGridParam('selarrrow');
		if(rowid == null || rowid.length == 0){
			showInfo("<m:message code='grid.delete.chooseColAlert'/>",3000);
			return ;
		}
		new biz.alert({type:"confirm",message:I18N.msg_del_confirm,
			title:I18N.promp,callback:function(result){
	    		if (!result) {
	    			return;
	    		}
	    		var ids="";
	    		for(var i=0; i < rowid.length;i++){
	    			var id = moduleResGrid.getCell(rowid[i],"resuuid");
	    			if(i>0){
	    				ids +=',';
	    			}
	    			ids += id;
	    		}
	    		var dataUrl = "moduleRes/delete/" + ids;
				$.ajax({
					url: dataUrl,
					cache:false,
					success: function(data, textStatus, jqXHR){
						query();
						showInfo("<m:message code='delete.success'/>",3000);
					},
					error:errorInfo
				});
			}
		});
	}
	
	function errorInfo(request, textStatus, errorThrown){
		var errorDialog = new biz.dialog({
			id:"#errorDialog",
			modal:true,
			width:"70%",
			height:400,
			title:"<m:message code='error.title'/>",
			close: function(event, ui){
				errorDialog.destroy();
			}
		});
		$("#errorDialog").html(request.responseText);
		errorDialog.open();
	}


	var editResactDialog = new biz.dialog({
		id:"#editResactDialog",
		autoOpen:false,
		title:"资源操作信息添加",
		width:"65%",
		modal:true,
		buttons: [
			{
				text: I18N.save,
				click: function() {
					if(isResactEdit){
						updateResact();
					}else{
						saveResact(); 
					}
				}
			 },
			{text: I18N.cancel,click: function() { editResactDialog.close();}}
		]
	});

	var isResactEdit=true;//true:当前对话框为编辑操作  false:当前对话框为新增操作
	//添加按钮
	function addResact(){
		isResactEdit = false;
		document.getElementById("resactFormEdit").reset();
		$("#resactFormEdit").find("input[type='file']").each(function(){
			   $(this).file("reset");
		});
		//alert($('#resuuid').val());
		$('#edit1_resuuid').val($('#resuuid').val());
		editResactDialog.open();
	}

	//编辑按钮
	function editResact(rowid){
		isResactEdit = true;
		rowid = resactGrid.getGridParam("selarrrow");
		if(rowid == null || rowid.length == 0){
			showInfo("<m:message code='grid.edit.chooseColAlert'/>",3000);
			return ;
		}else if(rowid.length > 1){
			showInfo("<m:message code='grid.edit.onlyOne'/>",3000);
			return ;
		}
		var id = resactGrid.getCell(rowid[0],"actid");
		var dataUrl = "resact/edit/" + id;
		$.ajax({
			url: dataUrl,
			dataType:"json",
			cache:false,
			success: function(data, textStatus, jqXHR){
				for(var attr in data){
						var element = document.getElementById("edit1_" + attr);
						if(element != null){
							element.value = data[attr];
					}
				}
				editResactDialog.open();
			},
			error:errorInfo
		});
	}

	//删除按钮
	function delResact(rowid){
		var rowid = resactGrid.getGridParam('selarrrow');
		if(rowid == null || rowid.length == 0){
			showInfo("<m:message code='grid.delete.chooseColAlert'/>",3000);
			return ;
		}
		new biz.alert({type:"confirm",message:I18N.msg_del_confirm,
			title:I18N.promp,callback:function(result){
	    		if (!result) {
	    			return;
	    		}
	    		var ids="";
	    		for(var i=0; i < rowid.length;i++){
	    			var id = resactGrid.getCell(rowid[i],"actid");
	    			if(i>0){
	    				ids +=',';
	    			}
	    			ids += id;
	    		}
	    		var dataUrl = "resact/delete/" + ids;
				$.ajax({
					url: dataUrl,
					cache:false,
					success: function(data, textStatus, jqXHR){
						resactGrid.setGridParam({"page":"1"});
						resactGrid.trigger('reloadGrid');
						showInfo("<m:message code='delete.success'/>",3000);
					},
					error:errorInfo
				});
			}
		});

	}

	function saveResact(){
		if(!biz.validate("valid","#resactFormEdit")){
			showWarn("<m:message code='validation.object'/>",3000);
			return;
		}

		$.ajax({
			url: "<c:url value='/resact/checkIsName'/>",
			data: {cname:$.trim($('#edit1_actname').val()),resuuid:$.trim($('#edit1_resuuid').val())},
			type: "POST",
			success: function(data, textStatus, jqXHR){
				if(data){
					$.ajax({
						url: "<c:url value='/resact/save'/>",
						data: $('#resactFormEdit').serialize(),
						type: "POST",
						success: function(data, textStatus, jqXHR){
							editResactDialog.close();
							resactGrid.setGridParam({"page":"1"});
							resactGrid.trigger('reloadGrid');
							showInfo("<m:message code='add.success'/>",3000);
						},
						error:errorInfo
					});
				}else{
					showWarn("该资源操作名称已存在,请重新填写!",3000);
                	return;
				}
			},
			error:errorInfo
		});
	}


	function updateResact(){
		if(!biz.validate("valid","#resactFormEdit")){
			showWarn("<m:message code='validation.object'/>",3000);
			return;
		}

		$.ajax({
			url: "<c:url value='/resact/update'/>",
			data: $('#resactFormEdit').serialize(),
			type: "POST",
			success: function(data, textStatus, jqXHR){
				rowid = resactGrid.getGridParam("selrow");
				resactGrid.setRowData(rowid,data);
				editResactDialog.close();
				showInfo("<m:message code='add.success'/>",3000);
			},
			error:errorInfo
		});

	}

	new biz.select({//状态下拉
	    id:"#status",
	    url:"<c:url value='/bizdirt/getDictByTypeCode?typeCode=status'/>",
	    addEmptyItem:true
	});
	
	new biz.select({//状态下拉
	    id:"#edit_status",
	    url:"<c:url value='/bizdirt/getDictByTypeCode?typeCode=status'/>",
	    addEmptyItem:false
	});

	new biz.select({//状态下拉
	    id:"#edit1_status",
	    url:"<c:url value='/bizdirt/getDictByTypeCode?typeCode=status'/>",
	    addEmptyItem:false
	});

	new biz.select({
	   		id:"#needright",
	   		url:"<c:url value='/bizdirt/getDictByTypeCode?typeCode=mstatus'/>",
	   		addEmptyItem:true
	});

	new biz.select({
   		id:"#edit_needright",
   		url:"<c:url value='/bizdirt/getDictByTypeCode?typeCode=mstatus'/>",
   		addEmptyItem:false
	});

	new biz.select({
   		id:"#edit1_needright",
   		url:"<c:url value='/bizdirt/getDictByTypeCode?typeCode=mstatus'/>",
   		addEmptyItem:false
	});
</script>