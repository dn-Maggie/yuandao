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
		getCreateDate(new Date());
		
  		gridObj = new biz.grid({
            id:"#remote_rowed",/*html部分table id*/
            url: "<m:url value='/empSalary/listEmpCost.do'/>",/*grid初始化请求数据的远程地址*/
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
           	sortname:"empNo",
           	sortorder:"asc", 
           	rownumbers:true,/*不显示数据数，左侧行号*/
           	multiselect:true,//多选checkbox
           	multiboxonly:true,
           	footerrow:true,//页脚汇总行
           	cellEdit:true,
           	afterSaveCell : function(rowid,name,val,iRow,iCol) {
           			var paramDatas = {};
	           		paramDatas[name] = val;
	           		paramDatas.id = rowid;
	           		$ .ajax({
	           			type: "post",
	    				url: "<m:url value='/empSalary/editEmpCost.do'/>",
	    				data: paramDatas,
						cache:false,
	    				dataType:"json"
	    			});
          	},
           	emptyrecords: "无记录可显示",
           	pager: '#remote_prowed' /*分页栏id*/,
     		rowList:[10,15,50,100],//每页显示记录数
    		rowNum:10,//默认显示15条
    		navtype:"top" /*导航栏类型*/,
    		navopt:{edit : false,add : false,del : false,reloadAfterSubmit:true},
            colModel:[
				{name : "id",hidden : true,key : true,label:"主键",index : "id"},				
				{name : "empNo",label : "工号",width:"3"},
		 		{name : "empDept",label:"所属部门",index : "empDept",hidden : true},
				{name : "empStatus",label:"在职状态",index : "empStatus",width:"3",
					formatter:function(cellvalue, options, rowObject){
	 				if (cellvalue==1) {return '在职';}else if (cellvalue==2){return '试用';}else if (cellvalue==3){return '离职';}else if (cellvalue==4){return '兼职';}
	 			}},	
	 			{name : "empName",label:"员工姓名",index : "empName",frozen : true,width:"4",
			        formatter : function(value, options, rData){
			          return value + "-"+rData['empNickName'];
		       		}},
				{name : "empNickName",label:"昵称",hidden : true,index : "empNickName",cellattr: function(rowId, value, rowObject, colModel, arrData) {
	 		          return " style=display:none; ";
 		        }},
 		        {name : "empEntryDate",label:"绩效统计月份",width:"6",index : "empEntryDate",
 		    	  formatter:'date',formatoptions: {newformat:'Y-m'}, 	
 		       	}, 
 		       {name : "meritRaise",label:"贡献绩效总额",width:"4",index : "meritRaise",number:true}, 
				{name : "actualSalary",label:"员工本月成本",width:"4",index : "actualSalary", editable:true,number:true},
				 {name : "rests",label:"奖金总额",width:"4",index : "rests",number:true}, 
				{name : "pushMoney",label:"实际奖金总额",width:"4",index : "pushMoney",number:true,
					cellattr : function(rowId, val, rawObject, cm, rdata){
						if(rawObject['actualSalary']-rawObject['meritRaise']>0){
				        	return "style='color:red'";
				        }else{
				        	return "style='color:green'";
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
            		   {"在职状态":"合计",
            	   		"actualSalary":$(this).getCol("actualSalary",false,"sum"),
            	   		});
	 		},
     	});
    	
		new biz.datepicker({
  			id : "#createDateMonth",
  			dateFmt:'yyyy-MM'
  		});
  	    
    });

  	
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
        				url: "<m:url value='/empSalary/deleteEmpSocialSecurity.do'/>?key="+ids,
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
    function getCreateDate(_date){
    	var createDate = _date;
    	var _createDate =  new Date(createDate);
    	var actual_createDate = _createDate.getFullYear()+"-"+(_createDate.getMonth()+1>=10?_createDate.getMonth()+1:"0"+_createDate.getMonth())+"-01";
    	$("#createDate").val(actual_createDate);
    }
    
    
    //新增的弹出框
	var add_iframe_dialog;
	function add(){
	  	//xin zeng iframe 弹出框
			var url="<m:url value='/empSalary/toAddEmpCost.do'/>";
			add_iframe_dialog = new biz.dialog({
				id:$('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
				modal: true,
				width: $(window).width()*0.6,
				height:$(window).height()*0.8,
				title: "员工社保增加"
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
						type="text" name="empName" id="empName" class="search_choose100"
						placeholder="员工姓名"> <input type="text" name="empNickName"
						id="nickName" class="search_choose100" placeholder="员工昵称">
						<input type="text" name="empNo" id="empNo"
						class="search_choose100" placeholder="员工编号"></li>
					<!-- 输入框-->
					<li><select style="float: none" class="search_choose"
						name="empDept" id="edit_dept" mainid="empDept">
							<option value="">---请选择---</option>
							<c:forEach var="org" items="${org}">
								<option value="${org.orgName}"><c:out
										value="${org.orgName}"></c:out></option>
							</c:forEach>
					</select> <span>所在部门:</span></li>
					<li><span>月份:</span>
						<div class="time_bg">
							<input id="createDateMonth" type="text" class="search_time150"
								name="createDateMonth"
								onchange="getCreateDate($(this).val()+'-01')"> <input
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
					<c:if test="${edit}">
						<li><a title="<m:message code="button.edit"/>"
							href="javascript:;" onclick="edit();"><i
								class="icon_bg icon_edit"></i> <span><m:message
										code="button.edit" /></span> </a></li>
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
