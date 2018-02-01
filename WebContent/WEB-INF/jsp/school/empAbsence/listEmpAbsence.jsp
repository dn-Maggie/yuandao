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
            url: "<m:url value='/empAbsence/listEmpAbsence.do'/>",/*grid初始化请求数据的远程地址*/
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
           	sortname:"absence_date",
           	sortorder:"desc",
           	pager: '#remote_prowed' /*分页栏id*/,
     		rowList:[10,15,50,100],//每页显示记录数
    		rowNum:10,//默认显示15条
            colModel:[
				{name : "id",hidden : true,key : true,label:"",index : "id"},	
				{name : "empId",hidden : true},
				{name : "empDept",label:"所属部门",index : "empDept"},
				{name : "empNickName",label:"员工姓名",index : "empNickName"},			
				{name : "absenceDate",label:"补录时间",index : "absence_date"},	
				{name : "absenceType",label:"补录类型",index : "absence_type",
					formatter:function(cellvalue, options, rowObject){
						switch (cellvalue) {
						case 1:return '上午上班';break;
						case 2:return '上午下班';break;
						case 3:return '下午上班';break;
						case 4:return '下午下班';break;
						default:break;
						}
		 		}},
		 		{name : "content",label:"补录原因",index : "content"},			
				{name : "createDate",label:"申请时间",index : "create_date"},
				{name : "straightLeaderName",label:"直接负责人",index : "straight_leader"},	
				{name : "deptLeader",label:"部门经理",index : "deptLeader"},		
				{name : "checkFlag",label:"审核标志",index : "check_flag",
					formatter:function(cellvalue, options, rowObject){
		 				 if (cellvalue==1) {
		 				 	return '待审核';
		 				 }else if (cellvalue==2) {
		 				 	return '已审核';
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

 

    //新增的弹出框
	var add_iframe_dialog;
	//修改的弹出框
   	var edit_iframe_dialog;
	//查看的弹出框
	var show_iframe_dialog;
	
  	function add(){
  		
  	//每个月只能加入两条数据
	$.ajax({
		 url : "<m:url value='/empAbsence/listEmpAbsence.do'/>",
            type: 'GET',
            dataType:"json",
            data: {
            	"empId":$("#userId").val(),
            	"propsMap['startDate']":new Date().format('yyyy-MM-01'),
           	 	"propsMap['endDate']":new Date().format('yyyy-MM-dd'),
           	 	
          	 },
            success : function(d) {
				if(d.records>=2){
					showMessage("本月已申请两次补签，无法继续申请","","",function(){
						closeAdd();
			     		return;
					});
				}
		}
        });
  		
  	//xin zeng iframe 弹出框
		var url="<m:url value='/empAbsence/toAddEmpAbsence.do'/>";
		add_iframe_dialog = new biz.dialog({
			id:$('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: $(window).width()*0.6,
			height:$(window).height()*0.5,
			title: "员工签卡增加"
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
		var checkFlag = ICSS.utils.getSelectRowData("checkFlag");
		if(checkFlag!="待审核"){
			showMessage("该数据已审核不能被修改");
			return ;
		}
		var empId = ICSS.utils.getSelectRowData("empId");
		if(empId!=$("#userId").val()){
			showMessage("无法修改他人数据！");
			return ;
		}
		var url="<m:url value='/empAbsence/toEditEmpAbsence.do'/>?key="+key;
		edit_iframe_dialog = new biz.dialog({
		 	id:$('<div id="editwindow_iframe"></div>').html('<iframe id="iframeEdit" name="iframeEdit" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: $(window).width()*0.6,
			height:$(window).height()*0.5,
			title: "员工签卡编辑"
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
		var url="<m:url value='/empAbsence/toShowEmpAbsence.do'/>?key="+key;
		show_iframe_dialog = new biz.dialog({
		 	id:$('<div id="showwindow_iframe"></div>').html('<iframe id="iframeShow" name="iframeShow" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: $(window).width()*0.6,
			height:$(window).height()*0.5,
			title: "员工签卡详情"
		});
  		show_iframe_dialog.open();
    }
    
    //关闭查看页面，供子页面调用
    function closeShow(){
    	show_iframe_dialog.close();
    }
    
	//审核方法
    function audit(){
    	var key = ICSS.utils.getSelectRowData("id");
    	var empId = ICSS.utils.getSelectRowData("empId");
    	var checkFlag = ICSS.utils.getSelectRowData("checkFlag");
    	if(key==""){
    		showMessage("请至少选择一条数据！");
    		return;
    	}
    	if(checkFlag.indexOf("已审核")>-1){
			showMessage("选项中包含已审核的数据，请重新选择");
			return;
		}
    	if(empId.indexOf($("#userId").val())>-1){
			showMessage("无法审核自己的申请！");
			return;
		}
    	else{
    		new biz.alert({type:"confirm",message:"确定审核数据？",title:I18N.promp,callback:function(result){
    			if(result){
    				$ .ajax({
        				url: "<m:url value='/empAbsence/auditEmpAbsence.do'/>?key="+key,
        				cache:false,
        				success: function(data, textStatus, jqXHR){
        					doSearch();
    						showInfo("审核成功",3000);
        				}
        			});
    			}
    		}}) ;   
    	}
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
    	var empId = ICSS.utils.getSelectRowData("empId");
    	var checkFlag = ICSS.utils.getSelectRowData("checkFlag");
    	if(ids==""){
    		showMessage("请至少选择一条数据！");
    		return ;
    	}
    	if(checkFlag.indexOf("已审核")>-1){
   			showMessage("选项中包含已审核的数据，请重新选择");
   			return;
   		}else{//多条数据
    		
    		var paramArr = [];
    		
    		var idsArr = ids.split(",");
    		for(var i = 0;i<idsArr.length;i++){
    			var paramObj = {};
    			paramObj.id =  ids.split(",")[i];
    			paramObj.empId = empId.split(",")[i];
    			paramObj.checkFlag = checkFlag.split(",")[i];
    			paramArr.push(paramObj);
    		}
    		paramArr = paramArr.filter(function(item) {
                return item.empId==$("#userId").val();
            });
    		paramArr = paramArr.filter(function(item) {
                return item.checkFlag == "待审核";
            });
    		new biz.alert({type:"confirm",message:I18N.msg_del_confirm,title:I18N.promp,callback:function(result){
    			if(result){
    				$ .ajax({
        				url: "<m:url value='/empAbsence/deleteEmpAbsence.do'/>",
        				type: 'post',
   	                	dataType:"json",
      					cache:false,
      					processData: false,// 告诉jQuery不要去处理发送的数据
   	               		contentType: false, // 告诉jQuery不要去设置Content-Type请求头
        				data: JSON.stringify(paramArr),
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
</head>
<body style="height: 100%;">
	<div class="main  choice_box">
		<form id="queryForm">
			<!-- 查询区 表单 -->
			<input type="hidden" name="userId" id="userId" value="${user.id}">
			<c:if test="${common&&!straightLeader&&!deptLeader&&!headLeader}">
				<input type="hidden" name="empId" id="empId" value="${user.id}">
			</c:if>
			<%-- <c:if test="${straightLeader&&!deptLeader&&!headLeader}">
				<input type="hidden" name="straightLeader" id="straightLeader" value="${user.id}">
			</c:if> --%>
			<%-- <c:if test="${deptLeader&&!headLeader}">
 				<input type="hidden" name="deptLeader" id="deptLeader" value="${user.nickName}">
 			</c:if> --%>
			<div class="search border-bottom">
				<ul>
					<li><input type="text" name="empNickName" id="empNickName"
						class="search_choose"> <span>员工姓名:</span></li>
					<!-- 输入框-->
					<li class="date_area"><span>申请日期:</span>
						<div class="time_bg">
							<input id="startDate" type="text" class="search_time150"
								name="propsMap['startDate']"> <i
								class="search_time_ico2"></i>
						</div> <i>至</i>
						<div class="time_bg">
							<input id="endDate" type="text" class="search_time150"
								name="propsMap['endDate']"> <i class="search_time_ico2"></i>
						</div></li>
					<li style="width: 180px;"><select class="search_choose"
						name="checkFlag" id="checkFlag" mainid="checkFlag"
						style="width: 100px;">
							<option value="">所有</option>
							<option value="1">待审核</option>
							<option value="2">已审核</option>
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
					<li><a title="<m:message code="button.add"/>"
						href="javascript:;" onclick="add();"> <i
							class="icon_bg icon_add"> </i> <span><m:message
									code="button.add" /></span>
					</a></li>
					<li><a title="<m:message code="button.edit"/>"
						href="javascript:;" onclick="edit();"><i
							class="icon_bg icon_edit"></i> <span><m:message
									code="button.edit" /></span> </a></li>
					<li><a title="<m:message code="button.delete"/>"
						href="javascript:;" onclick="batchDelete();"> <i
							class="icon_bg icon_del"></i> <span><m:message
									code="button.delete" /></span>
					</a></li>
					<li><a title="<m:message code="button.view"/>"
						href="javascript:" onclick="show();"> <i
							class="icon_bg icon_ckxq"></i> <span><m:message
									code="button.view" /></span>
					</a></li>
					<c:if test="${straightLeader||deptLeader||headLeader||!isNotAdmin}">
						<li><a title="审核" href="javascript:" onclick="audit();">
								<i class="icon_bg icon_audit"></i> <span>审核</span>
						</a></li>
					</c:if>
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
