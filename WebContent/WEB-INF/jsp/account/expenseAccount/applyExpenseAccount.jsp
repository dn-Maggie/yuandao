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
  		gridObj = new biz.grid({
            id:"#remote_rowed",/*html部分table id*/
            url: "<m:url value='/expenseAccount/listExpenseAccount.do'/>",/*grid初始化请求数据的远程地址*/
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
           	sortname:"enter_date",
           	sortorder:"desc",
           	multiselect:true,
           	multiboxonly:true,
           	pager: '#remote_prowed' /*分页栏id*/,
     		rowList:[10,15,50,100],//每页显示记录数
    		rowNum:10,//默认显示15条
            colModel:[
				{name : "id",hidden : true,key : true,label:"",index : "id"},	
				{name : "expenseType",label:"报销单类型"},	
				{name : "deptName",label:"所在部门"},	
				{name : "enterName",label:"申请人"},	
				{name : "expenseMoney",label:"报销金额",index : "expense_money"},				
				{name : "content",label:"报销事由",index : "content"},					
				{name : "enterDate",label:"申请时间",index : "enter_date"},
				{name : "checkFlag",label:"审核状态",align:"center",
					formatter:function(cellvalue, options, rowObject){
		 				 if (cellvalue==1) {
		 				 	return '待审核';
		 				 }else {
		 				 	return '已审核';
		 				 }
		 			}},
		 		{name : "assignFlag",label:"放款状态",align:"center",
    				formatter:function(cellvalue, options, rowObject){
    			 		 if (cellvalue==1) {
    			 			 return '未放款';
    			 		 }else {
    			 			return '已放款';
    			 		}
    			 	}},	
    			{name : "expenseWay",label:"报销方式",index : "expense_way",align:"center"},		
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
		var url="<m:url value='/expenseAccount/toAddExpenseAccount.do'/>";
		add_iframe_dialog = new biz.dialog({
			id:$('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: $(window).width()*0.6,
			height:$(window).height()*0.6,
			title: "新增报销单"
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
		if(checkFlag=="已审核"){
			showMessage("已审核的数据不能进行修改");
			return ;
		}
		var url="<m:url value='/expenseAccount/toEditExpenseAccount.do'/>?key="+key;
		edit_iframe_dialog = new biz.dialog({
		 	id:$('<div id="editwindow_iframe"></div>').html('<iframe id="iframeEdit" name="iframeEdit" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: $(window).width()*0.6,
			height:$(window).height()*0.6,
			title: "修改报销单"
		});
  		edit_iframe_dialog.open();
    }
    
    //关闭编辑页面，供子页面调用
    function closeEdit(){
    	edit_iframe_dialog.close();
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
    		var checkFlag = ICSS.utils.getSelectRowData("checkFlag");
    		if(checkFlag=="已审核"){
    			showMessage("已审核的数据不能删除");
    			return ;
    		}
    		new biz.alert({type:"confirm",message:I18N.msg_del_confirm,title:I18N.promp,callback:function(result){
    			if(result){
    				$ .ajax({
        				url: "<m:url value='/expenseAccount/deleteExpenseAccount.do'/>?key="+ids,
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
</head>
<body style="height: 100%;">

	<div class="main  choice_box">
		<form id="queryForm">
			<!-- 查询区 表单 -->
			<div class="search border-bottom">
				<ul>
					<li class="date_area"><span>申请日期:</span>
						<div class="time_bg">
							<input type="hidden" name="enterName" id="enterName"
								value="${user.fullName}"> <input id="startDate"
								type="text" class="search_time150" name="propsMap['startDate']"
								mainid="startDate"> <i class="search_time_ico2"
								onclick="WdatePicker({el:'startDate'})"></i>
						</div> <i>至</i>
						<div class="time_bg">
							<input id="endDate" type="text" class="search_time150"
								name="propsMap['endDate']" mainid="endDate"> <i
								class="search_time_ico2" onclick="WdatePicker({el:'endDate'})"></i>
						</div></li>
					<li style="width: 180px;"><select class="search_choose"
						name="expenseType" id="expenseType" mainid="expenseType"
						style="width: 100px;">
							<option value="">所有</option>
							<option value="个人报销">个人报销</option>
							<option value="集体报销">集体报销</option>
					</select><span>报销类型</span></li>
					<!-- 输入框-->
					<li style="width: 180px;"><select class="search_choose"
						name="checkFlag" id="checkFlag" mainid="checkFlag"
						style="width: 100px;">
							<option value="">所有</option>
							<option value="1">未审核</option>
							<option value="2">已审核</option>
					</select><span>审核状态:</span></li>
					<!-- 输入框-->
					<li style="width: 180px;"><select class="search_choose"
						name="expenseType" id="expenseType" mainid="expenseType"
						style="width: 100px;">
							<option value="">所有</option>
							<option value="支付现款">支付现款</option>
							<option value="银行转账">银行转账</option>
					</select><span>报销方式</span></li>
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
					<li><a title="申请" href="javascript:;" onclick="add();"> <i
							class="back_icon add_icon"> </i> <span>申请</span>
					</a></li>
					<%-- 							<li><a title="<m:message code="button.edit"/>" href="javascript:;"
								onclick="edit();"><i class="icon_bg icon_edit"></i> <span><m:message
											code="button.edit" /></span> </a></li> --%>

					<li><a title="<m:message code="button.delete"/>"
						href="javascript:;" onclick="batchDelete();"> <i
							class="icon_bg icon_del"></i> <span><m:message
									code="button.delete" /></span>
					</a></li>
				</ul>
			</div>

			<!--功能按钮end-->
			<div class="listtable_box">
				<table id="remote_rowed"></table>
				<div id="remote_prowed"></div>

			</div>
		</div>
	</div>
</body>
</html>
