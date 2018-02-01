<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<title></title>
<style type="text/css">
td>.editable {
	width: 90%;
}
</style>
<script type="text/javascript">
var gridObj = {};
var jsion_sumColumns = {};	
jsion_sumColumns["sumColumns"] = "rn,actualSalary";   //总计
	$(function(){
		var lastsel;
  		gridObj = new biz.grid({
            id:"#remote_rowed",/*html部分table id*/
            url: "<m:url value='/empSalary/listEmpSalary.do'/>",/*grid初始化请求数据的远程地址*/
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
           	sortname:"empNo",
           	sortorder:"asc", 
           	//forceFit:true,/*固定宽度*/
           	autoWidth:true,
           	rownumbers:false,/*不显示数据数*/
        	multiselect:true,/*可多选*/
           	multiboxonly:false,/*仅可通过checkBox多选*/
           	emptyrecords: "无记录可显示",
        	footerrow:true,
        	/* cellEdit:true, *///是否开启单元格的编辑功能  
           	cellsubmit:'remote',//or 'clientArray',remote代表每次编辑提交后进行服务器保存，  clientArray只保存到数据表格不保存到服务器  
           	cellurl:'<m:url value='/empSalary/updateEmpSalary.do'/>',//cellsubmit要提交的后台路径  
           	pager: '#remote_prowed' /*分页栏id*/,
     		rowList:[10,15,50,100],//每页显示记录数
    		rowNum:10,//默认显示15条
/*     		navtype:"top" ,
    		navopt:{edit : true,add : false,del : false,reloadAfterSubmit:true}, */
            colModel:[
				{name : "id",hidden : true,key : true,label:"主键",index : "id"},				
				{name : "createDate",index : "create_date",hidden : true},
		 		{name : "empDept",label:"所属部门",index : "empDept",hidden : true},
		 		{name : "empNo",index : "empNo",label:"员工编号"},
				{name : "empName",label:"员工姓名",index : "empName",frozen : true},	
				{name : "empStatus",label:"在职状态",index : "empStatus",
					formatter:function(cellvalue, options, rowObject){
	 				 if (cellvalue==1) {return '在职';}
	 				 else if (cellvalue==2){return '试用';}
	 				 else if (cellvalue==3){return '离职';}
	 				 else if (cellvalue==4){return '兼职';}
	 			}},	
				{name : "empNickName",label:"昵称",index : "empNickName"},
				{name : "empBank",label:"银行",index : "empBank",hidden:true},
				{name : "empBankCard",label:"卡号",index : "empBankCard"},
				{name : "empMobile",label:"电话号码",index : "empMobile"},
				{name : "leaveDay",label:"请假天数",index : "leave_day",editable:true},
				{name : "lateEarlyTime",label:"迟到早退(次)",index : "late_early_time",editable:true},
				{name : "attendanceAnomalyTime",label:"打卡异常(次)",index : "attendance_anomaly_time",editable:true},
				{name : "empEntryDate",label:"入职时间",index : "empEntryDate",hidden:true},
				{name : "empBeFullDate",label:"转正日期",index : "empBeFullDate",hidden:true},
				{name : "actualattendance",label:"出勤天数",index : "actualattendance",editable:true},
				{name : "basicSalary",label:"基本工资",index : "basic_salary",editable:true,
					formatter:'currency', formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},	
				{name : "dutyLevelSalary",label:"薪级工资",hidden : true,index : "duty_level_salary",formatter:'currency', 
					formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},
				{name : "shouldSalary",label:"应发工资",index : "should_salary",editable:true,formatter:'currency',
					formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},				
				{name : "socialSecurity",label:"社保扣款",index : "social_security",editable:true,formatter:'currency', 
					formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},				
				{name : "leaveCost",label:"请假扣款",index : "leave_cost", editable:true,formatter:'currency',
						formatter:function(cellvalue, options, rowObject){
			 				 	return Math.round(cellvalue);
	 			}},
				{name : "lateEarlyCost",label:"迟到早退扣款",index : "late_early_cost",editable:true, formatter:'currency',
					formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},		
				{name : "attendanceAnomalyCost",label:"打卡异常扣款",index : "attendance_anomaly_cost",editable:true, formatter:'currency',
					formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},		
				{name : "tableMoney",label:"餐补",index : "table_money",editable:true,formatter:'currency', 
					formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},				
				{name : "housingAllowance",label:"住房补贴",index : "housing_allowance",editable:true,formatter:'currency',
					formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},	
				{name : "trafficsubsidies",label:"交通补贴",index : "trafficsubsidies", editable:true,
					formatter:'currency', formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},
				{name : "meritRaise",label:"绩效奖金",index : "merit_raise",editable:true,
					formatter:'currency', formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},	
				{name : "pushMoney",label:"提成",index : "push_money",editable:true,
					formatter:'currency', formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},	
				{name : "rests",label:"其他",index : "rests",editable:true,formatter:'currency', 
					formatoptions:{thousandsSeparator: ',',decimalPlaces:'2'}},				
				{name : "actualSalary",label:"实发工资",index : "actual_salary",editable:true,
						formatter:function(cellvalue, options, rowObject){
			 				 	return Math.round(cellvalue);
	 			}},	
				{name : "note",label:"备注",index : "note",editable:true},
				{name : "sendFlag",label:"分发状态",index : "send_flag",
					formatter:function(cellvalue, options, rowObject){
		 				 if (cellvalue==1) {
		 				 	return '未发送';
		 				 }else if (cellvalue==2) {
		 				 	return '已发送';
		 				 }
	 			}},	 
				{name : "",label:"保存",index : "operate",width:220,align: 'center',formatter: function (cellvalue, options, rowObject) {
					return "<input id=\"savecell\" type=\"button\" class=\"ti_bottom\" value=\"保存\" onclick=\"test()\"/>"; 
					},
	            },
	        
           	],
           	serializeGridData:function(postData){//添加查询条件值
				var obj = getQueryCondition();
    			$ .extend(true,obj,postData);//合并查询条件值与grid的默认传递参数
    			return obj;
    		},
    		beforeEditCell:function(rowid,cellname,value,iRow,iCol){
                businessplanmag_iRow=iRow;//全局变量
                businessplanmag_iCol=iCol;
            },
    		/* editurl : "<m:url value='/empSalary/operEmpSalary.do'/>", */
    		gridComplete:function(){
    			$(".ui-jqgrid-sdiv").show();
           		//如果需要统计则需要定义
               	//getFooterJsonData($(this));
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
	 		}
      	});
  		
		new biz.datepicker({
  			id : "#createDateMonth",
  			dateFmt:'yyyy-MM'
  		});
    });
    
	//新增的弹出框
	var send_iframe_dialog;
    //一键分发邮件
    function sendEmail(){
	    	var url="<m:url value='/empSalary/toSendEmpSalary.do'/>",
	    	send_iframe_dialog = new biz.dialog({
				id:$('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
				modal: true,
				width: $(window).width()*0.6,
				height: $(window).height()*0.8,
				title: ""
			});
			send_iframe_dialog.open();
    }
    
  //关闭发送页面，供子页面调用
  	function closeAdd(){
  		send_iframe_dialog.close();
  		if(!isStayCurrentPage)gridObj.setGridParam({"page":"1"});
  		gridObj.trigger('reloadGrid');
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
    
    //总计
    //@param jqGridObj
    function getFooterJsonData(jqGridObj){
       var addFootData = {} ;
  	   var resObj = ajaxGetStatistic();
       try{
         //总计
     		var _strColumns = jsion_sumColumns.sumColumns.split(",");
             for(var k = 0;k<_strColumns.length; k++){
          	   k == 0?addFootData[_strColumns[k]] = "总计：":addFootData[_strColumns[k]] = Math.round(resObj[_strColumns[k]]) ||0;
             }
       }
     	catch(e){
      }    
     		jqGridObj.jqGrid('footerData','set',addFootData,false);
   	}
    //根据条件从数据库获取结果集
	function ajaxGetStatistic(){
		var resObj = {};
       	$.ajax({
   			url : "<m:url value='/empSalary/getEmpSalaryStatistic.do'/>",
   			cache : false,
   			data: getQueryCondition(),
   			async : false,
   			dataType:"json",
   			success : function(data) {
   				resObj.actualSalary = parseFloat(data.curr.actualSalary);
   			}
   		});
       	return resObj;
	};
	 //获取创建日期方法
    function getCreateDate(){
    	var createDate = $("#createDateMonth").val()+"-01";
    	$("#createDate").val(createDate);
    }
    
	 
    //发放金额后插入流水记录数据
    function insertaccountflow(){
    	var key = ICSS.utils.getSelectRowData("id");
    	if(key==""){
    		showMessage("请至少选择一条数据！");
    		return ;
    	}
    	else{
    		new biz.alert({type:"confirm",message:"确认发放以后将生成对应员工支出流水记录，确定继续？",title:I18N.promp,callback:function(result){
    			if(result){
    				$ .ajax({
        				url: "<m:url value='/empSalary/insertSalary.do'/>",
        				cache:false,
        				data: {key:key},
        				type : "post",
        				dataType:"json",
        				success: function(data, textStatus, jqXHR){
        					doSearch();
    						showInfo("流水记录已生成",3000);
        				}
        			});
    			}
    		}}) ;   
    	}  	
    }
    
	function test() {
		//最后一条数据在关闭窗口前要用saveCell函数结束文本编辑状态，不然
		$("#remote_rowed").jqGrid("saveCell", businessplanmag_iRow,
				businessplanmag_iCol);
/* 		gridObj.trigger('reloadGrid'); 这个刷新无效要用下面的*****/
		window.location.reload();
	}
    </script>
</head>
<body style="height: 100%;">

	<div class="main  choice_box">
		<form id="queryForm">
			<!-- 查询区 表单 -->
			<input type="hidden" name="checkFlag" value="2">
			<div class="search border-bottom">
				<ul>
					<li><select style="float: none" class="search_choose"
						name="empDept" id="edit_dept" mainid="empDept">
							<option value="">---请选择---</option>
							<c:forEach var="org" items="${org}">
								<option value="${org.orgName}"><c:out
										value="${org.orgName}"></c:out></option>
							</c:forEach>
					</select><span>所在部门:</span></li>
					<li><input type="text" name="employeeName" id="employeeName"
						class="search_choose"> <span>员工姓名:</span></li>
					<!-- 输入框-->
					<li><span>分配月份:</span>
						<div class="time_bg">
							<input id="createDateMonth" type="text" class="search_time150"
								name="createDateMonth" onchange="getCreateDate()"> <input
								id="createDate" type="hidden" name="createDate"> <i
								class="search_time_ico2"
								onclick="WdatePicker({el:'createDateMonth'})"></i>
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
					<li><a title="分发邮件" href="javascript:" onclick="sendEmail();">
							<i class="back_icon send_icon"></i> <span>分发邮件</span>
					</a> <a title="工资发放" href="javascript:" onclick="insertaccountflow();">
							<i class="back_icon send_icon"></i> <span>发放工资/生成流水记录</span>
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
