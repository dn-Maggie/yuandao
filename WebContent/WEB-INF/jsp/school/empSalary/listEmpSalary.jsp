<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<style type="text/css">
td>.editable {
	width: 90%;
}
</style>
<script type="text/javascript">
var gridObj = {};
	$(function(){
  		gridObj = new biz.grid({
            id:"#remote_rowed",/*html部分table id*/
            url: "<m:url value='/empSalary/listEmpSalary.do'/>",/*grid初始化请求数据的远程地址*/
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
           	sortname:"empNo",
           	sortorder:"asc", 
           	/*forceFit:true,固定宽度*/
           	rownumbers:false,/*不显示数据数，左侧行号*/
           	multiselect:true,//多选checkbox
           	multiboxonly:true,
           	footerrow:true,//页脚汇总行
           	emptyrecords: "无记录可显示",
           	pager: '#remote_prowed' /*分页栏id*/,
     		rowList:[10,15,50,100],//每页显示记录数
    		rowNum:10,//默认显示15条
    		navtype:"top" /*导航栏类型*/,
    		navopt:{edit : false,add : false,del : false,reloadAfterSubmit:true},
            colModel:[
                {name: '操作', width:50, fixed:true, sortable:false, resize:false, formatter:'actions',formatoptions:{keys:true}},
				{name : "id",hidden : true,key : true,label:"主键",index : "id"},				
				{name : "createDate",index : "create_date",hidden : true},
				{name : "empNo",index : "empNo",hidden : true},
		 		{name : "empDept",label:"所属部门",index : "empDept",hidden : true},
				{name : "empStatus",label:"在职状态",index : "empStatus",width:"3",
					formatter:function(cellvalue, options, rowObject){
	 				if (cellvalue==1) {return '在职';}else if (cellvalue==2){return '试用';}else if (cellvalue==3){return '离职';}else if (cellvalue==4){return '兼职';}
	 			}},	
	 			{name : "empName",label:"员工姓名",index : "empName",frozen : true,width:"6",
			        formatter : function(value, options, rData){
			          return value + "-"+rData['empNickName'];
		       		}},
				{name : "empNickName",label:"昵称",hidden : true,index : "empNickName",cellattr: function(rowId, value, rowObject, colModel, arrData) {
	 		          return " style=display:none; ";
 		        }},
				/* {name : "empBank",label:"银行",index : "empBank", editable:true ,width:"8"},
				{name : "empBankCard",label:"银行卡号",index : "empBankCard", editable:true ,width:"6"}, */
				{name : "empMobile",label:"电话号码",index : "empMobile",width:"8",hidden : true},
				{name : "leaveDay",label:"请假天数",index : "leave_day", width:"3",/*editable:true,*/number:true},
				{name : "lateEarlyTime",label:"迟到早退(次)",width:"3",index : "late_early_time", /*editable:true,*/number:true,editoptions : {size: 2}},
				{name : "attendanceAnomalyTime",label:"打卡异常(次)",width:"3",index : "attendance_anomaly_time", /*editable:true,*/number:true,editoptions : {size: 2}},
				/* {name : "empEntryDate",label:"入职时间",width:"6",index : "empEntryDate"},
				{name : "empBeFullDate",label:"转正日期",width:"3",index : "empBeFullDate"},  */
				{name : "actualattendance",label:"出勤天数",width:"3",index : "actualattendance"}, 
				{name : "basicSalary",label:"基本工资",width:"5",index : "basic_salary",/*editable:true,*/
					formatter:'currency', formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},	
				{name : "dutyLevelSalary",label:"薪级工资",width:"5",hidden : true,index : "duty_level_salary",/*editable:true,*/
					formatter:'currency', formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},
				{name : "shouldSalary",label:"应发工资",width:"5",index : "should_salary", /*editable:true,*/
					formatter:'integer', formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},				
				{name : "socialSecurity",label:"社保扣款",width:"4",index : "social_security", /*editable:true,*/number:true,
					formatter:'currency', formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},					
				{name : "leaveCost",label:"请假扣款",width:"4",index : "leave_cost",/*editable:true,*/
						formatter:function(cellvalue, options, rowObject){
		 				 	return Math.round(cellvalue);
 				}},
				{name : "lateEarlyCost",label:"迟到早退扣款",width:"4",index : "late_early_cost",
					formatter:'currency', formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},			
				{name : "attendanceAnomalyCost",label:"打卡异常扣款",width:"4",index : "attendance_anomaly_cost",
					formatter:'currency', formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},			
				{name : "tableMoney",label:"餐补",width:"4",index : "table_money", /*editable:true,*/number:true,
					formatter:'currency', formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},					
				{name : "housingAllowance",label:"住房补贴",width:"4",index : "housing_allowance", /*editable:true,*/number:true,
					formatter:'currency', formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},	
				{name : "trafficsubsidies",label:"交通补贴",width:"3",index : "trafficsubsidies", /*editable:true,*/number:true,
					formatter:'currency', formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},
				{name : "meritRaise",label:"绩效奖金",width:"4",index : "merit_raise", /*editable:true,*/number:true,
					formatter:'currency', formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},	
				{name : "pushMoney",label:"提成",width:"4",index : "push_money", /*editable:true,*/number:true,
						formatter:'currency', formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},		
				{name : "rests",label:"其他",width:"4",index : "rests", /*editable:true,*/number:true,
					formatter:'currency', formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},	
				{name : "note",label:"个人所得税",width:"4.5",index : "note", /*editable:true*/},
				{name : "actualSalary",label:"实发工资",width:"6",index : "actual_salary",
						formatter:function(cellvalue, options, rowObject){
			 				 	return Math.round(cellvalue);
	 			}},
				{name : "checkFlag",label:"审核状态",index : "check_flag",hidden : true,
					formatter:function(cellvalue, options, rowObject){
		 				 if (cellvalue==1) {
		 				 	return '待审核';
		 				 }else if (cellvalue==2) {
		 				 	return '已审核';
		 				 }
	 			}}	
           	],
           	serializeGridData:function(postData){//添加查询条件值，把数据进行序列化
				var obj = getQueryCondition();
    			$ .extend(true,obj,postData);//合并查询条件值与grid的默认传递参数
    			return obj;
    		},
    		gridComplete:function(){
    			$(".ui-jqgrid-sdiv").show();
           		//如果需要统计则需要定义
               $(this).footerData("set",
            		   {"操作":"合计",
            	  		"leaveDay":$(this).getCol("leaveDay",false,"sum"),
            	  		"lateEarlyTime":$(this).getCol("lateEarlyTime",false,"sum"),
            	  		"attendanceAnomalyTime":$(this).getCol("attendanceAnomalyTime",false,"sum"),
            	  		"basicSalary":$(this).getCol("basicSalary",false,"sum"),
            	   		"shouldSalary":$(this).getCol("shouldSalary",false,"sum"),
            	   		"actualSalary":$(this).getCol("actualSalary",false,"sum"),
            	   		"socialSecurity":$(this).getCol("socialSecurity",false,"sum"),
            	   		"leaveCost":$(this).getCol("leaveCost",false,"sum"),
            	   		"lateEarlyCost":$(this).getCol("lateEarlyCost",false,"sum"),
            	   		"attendanceAnomalyCost":$(this).getCol("attendanceAnomalyCost",false,"sum"),
            	   		"tableMoney":$(this).getCol("tableMoney",false,"sum"),
            	   		"housingAllowance":$(this).getCol("housingAllowance",false,"sum"),
            	   		"meritRaise":$(this).getCol("meritRaise",false,"sum"),
            	   		"pushMoney":$(this).getCol("pushMoney",false,"sum"),
            	   		"rests":$(this).getCol("rests",false,"sum"),
            	   		});
	 		},
    		/* editurl : "<m:url value='/empSalary/operEmpSalary.do'/>", */
     	});
    	
		new biz.datepicker({
  			id : "#createDateMonth",
  			dateFmt:'yyyy-MM'
  		});
  	    
    });



 	function importData(){
 		 if($('input[type="file"]').val()!=""){
 			var extend=$('input[type="file"]').val().substr($('input[type="file"]').val().lastIndexOf(".")+1);
 			if("xls|xlsx|".indexOf(extend+"|")==-1){
 				 showInfo("选择的文件必须是EXCEL文件,请确认！",3000);
 	         }else{ 
 	        	showInfo("数据正在导入...");
 	        	ajaxFileUpload();
 	        	gridObj.trigger('reloadGrid');
 	         }
 		 }else{
 			showInfo("请选EXCEL文件！",3000);
 	    }
 	}
  	
 	function ajaxFileUpload(){
 		var options = {
				url : "<m:url value='/empSalary/uploadExcelData.do'/>",
				type : "post",
					dataType:"text",
					success : function(d) {gridObj.trigger('reloadGrid');},
					error : function(d) {gridObj.trigger('reloadGrid');},
			};
			// 将options传给ajaxForm
			$('#form').ajaxSubmit(options);
 	}
 	
	//批量新增（自动生成）方法
	function autoAdd(){
		$ .ajax({
			url: "<m:url value='/empSalary/toAutoAddEmpSalary.do'/>",
			cache:false,
			success: function(data, textStatus, jqXHR){
				doSearch();
				showInfo("数据生成成功",3000);
			}
		});
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
        				url: "<m:url value='/empSalary/deleteEmpSalary.do'/>?key="+ids,
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
    //获取创建日期方法
    function getCreateDate(){
    	var createDate = $("#createDateMonth").val()+"-01";
    	$("#createDate").val(createDate);
    }
	//审核方法
    function audit(){
    	var key = ICSS.utils.getSelectRowData("id");
    	var empId = ICSS.utils.getSelectRowData("empId");
    	var checkFlag = ICSS.utils.getSelectRowData("checkFlag");
    	if(key==""){
    		showMessage("请至少选择一条数据！");
    		return ;
    	}
    	else{
    		new biz.alert({type:"confirm",message:"确定审核？",title:I18N.promp,callback:function(result){
    			if(result){
    				$ .ajax({
        				url: "<m:url value='/empSalary/auditEmpSalary.do'/>?key="+key,
        				cache:false,
        				success: function(data, textStatus, jqXHR){
    						showInfo(data,3000);
    						gridObj.trigger('reloadGrid');
        				},error:function(){
        					showInfo("审核失败",3000);
    						gridObj.trigger('reloadGrid');
        				}
        			});
    			}
    		}}) ;   
    	}
    }
    //新增的弹出框
	var add_iframe_dialog;
	function add(){
	  	//xin zeng iframe 弹出框
			var url="<m:url value='/empSalary/toAddEmpSalary.do'/>";
			add_iframe_dialog = new biz.dialog({
				id:$('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
				modal: true,
				width: $(window).width()*0.6,
				height:$(window).height()*0.8,
				title: "员工工资增加"
			});
			add_iframe_dialog.open();
	  	}
	//关闭新增页面，供子页面调用
  	function closeAdd(){
		add_iframe_dialog.close();
  	}
    </script>
</head>
<body style="height: 100%;">

	<div class="main  choice_box">
		<form id="queryForm">
			<!-- 查询区 表单 -->
			<div class="search border-bottom">
				<ul>
					<li style="width: 450px; float: left;"><span>关键字:</span> <input
						type="text" name="employeeName" id="employeeName"
						class="search_choose100" placeholder="员工姓名"> <input
						type="text" name="nickName" id="nickName" class="search_choose100"
						placeholder="员工昵称"> <input type="text" name="empNo"
						id="empNo" class="search_choose100" placeholder="员工编号"></li>
					<!-- 输入框-->
					<li><select style="float: none" class="search_choose"
						name="empDept" id="edit_dept" mainid="empDept">
							<option value="">---请选择---</option>
							<c:forEach var="org" items="${org}">
								<option value="${org.orgName}"><c:out
										value="${org.orgName}"></c:out></option>
							</c:forEach>
					</select> <span>所在部门:</span></li>
					<li><select class="search_choose" name="checkFlag">
							<option value="1">待审核</option>
							<option value="2">已审核</option>
							<option value="">所有</option>
					</select><span>审核状态:</span></li>
					<li><span>分配月份:</span>
						<div class="time_bg">
							<input id="createDateMonth" type="text" class="search_time150"
								name="createDateMonth" onchange="getCreateDate()"> <input
								id="createDate" type="hidden" name="createDate"> <i
								class="search_time_ico2"
								onclick="WdatePicker({el:'createDateMonth'})"></i>
						</div></li>
					<li style="width: 180px;"><select class="search_choose"
						name="empStatus" id="empStatus" style="width: 100px;">
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
					<li><a title="添加记录" href="javascript:;" onclick="add()"> <i
							class="back_icon down_icon"> </i> <span>新增一条工资记录</span>
					</a></li>
					<li><a title="自动生成" href="javascript:;" onclick="autoAdd()">
							<i class="back_icon down_icon"> </i> <span>生成上月记录</span>
					</a></li>
					<li><a title="外部导入" href="javascript:;"
						onclick="$('#file').click()"> <i class="back_icon import_icon">
						</i> <span>外部导入</span>
					</a>
						<form name="form" id="form" method="post"
							action="/empSalary/uploadExcelData.do"
							enctype="multipart/form-data">
							<input type="file" id="file" name="file" style="display: none"
								onchange="importData();" />
						</form></li>
					<c:if test="${edit}">
						<li><a title="<m:message code="button.edit"/>"
							href="javascript:;" onclick="edit();"><i
								class="icon_bg icon_edit"></i> <span><m:message
										code="button.edit" /></span> </a></li>
					</c:if>
					<li><a title="审核" href="javascript:" onclick="audit();"> <i
							class="back_icon send_icon"></i> <span>审核通过</span>
					</a></li>
					<c:if test="${manage}">
						<li><a title="<m:message code="button.module.moduleRes"/>"
							href="javascript:" onclick="moduleResMgt();"> <i
								class="back_icon resources_icon"></i> <span><m:message
										code="button.module.moduleRes" /></span>
						</a></li>
						<li><a title="审核" href="javascript:" onclick="audit();">
								<i class="back_icon send_icon"></i> <span>审核通过</span>
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
