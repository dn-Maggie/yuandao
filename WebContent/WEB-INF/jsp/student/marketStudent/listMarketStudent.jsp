<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/echarts-2.2.7/echarts.js" ></script>
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/static/css/chosen.css" />
<title></title>
</head>
<body style="height: 100%;">
	<div class="main  choice_box">
		<form id="queryForm">
			<!-- 查询区 表单 -->
			<div class="search border-bottom">
				<ul>
					<li><input type="text" name="qq" id="qq" class="search_choose">
						<span>QQ号码:</span></li>
					<!-- 输入框-->
					<li class="date_area"><span>录入日期:</span>
						<div class="time_bg">
							<c:choose>
								<c:when test="${condition=='month'}">
									<input id="startDate" type="text" class="search_time150"
										name="propsMap['startDate']" value="${currDate}">
								</c:when>
								<c:when test="${condition=='year'}">
									<input id="startDate" type="text" class="search_time150" name="propsMap['startDate']" value="${currDate}">
								</c:when>
								<c:otherwise>
									<input id="startDate" type="text" class="search_time150"
										name="propsMap['startDate']">
								</c:otherwise>
							</c:choose>
							<i class="search_time_ico1"></i>
						</div> <i>至</i>
						<div class="time_bg">
							<input id="endDate" type="text" class="search_time150"
								name="propsMap['endDate']"> <i
								class="search_time_ico1"></i>
						</div></li>
					<li><select class="search_choose" name="subjectId"
						id="subjectId" >
							<option value="">--请选择--</option>
							<c:forEach var="mr" items="${er.subject}">
								<option value="${mr.id}">
									<c:out value="${mr.name}"></c:out>
								</option>
							</c:forEach>
					</select><span>学科:</span></li>
					<!--下拉 -->
					<li><select class="search_choose" name="createdby" id="createdby"
						onchange="enterChange(this.value);">
							<option value="">-请选择-</option>
							<c:forEach var="useradmin" items="${useradmin}">
								<option value="${useradmin.id}">
									<c:out value="${useradmin.fullName}"></c:out>
								</option>
							</c:forEach>
					</select><span>管理员:</span></li>
					<li><select class="search_choose" name="userId" id="userId">
							<option value="">-请选择-</option>
							<c:forEach var="user" items="${user}">
								<option value="${user.id}">
									<c:out value="${user.fullName}"></c:out>
								</option>
							</c:forEach>
					</select><span>录入人:</span></li>
					<li><input type="reset" class="reset_btn"
						onclick="resetForm('queryForm')" value="重置">
					<!-- 重置 --> <input type="button" class="search_btn mr22 "
						onclick="doSearch();ajaxGetMarketStatistic();" value="查询"></li>
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
			<div id="main" style="z-index:-1"></div>
		</div>
	</div>
	<script type="text/javascript">
var gridObj = {};
	$(function(){
  		gridObj = new biz.grid({
            id:"#remote_rowed",/*html部分table id*/
            url: "<m:url value='/marketStudent/listMarketStudent.do'/>",/*grid初始化请求数据的远程地址*/
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
           	sortname:"time",
           	sortorder:"desc",
           	multiselect:true,
           	multiboxonly:true,
           	pager: '#remote_prowed' /*分页栏id*/,
     		rowList:[10,20,50,100],//每页显示记录数
    		rowNum:10,//默认显示15条
            colModel:[
				{name : "id",hidden : true,key : true,label:"",index : "id"},				
				{name : "qq",label:"QQ号码",index : "qq"},				
				{name : "notes",label:"意向课程",index : "notes"},		
				{name : "subjectname",label:"意向学科",index : "subjectname"},				
				{name : "fullname",label:"录入人",index : "fullname"},				
				{name : "time",label:"时间",index : "time"}	
           	],
           	serializeGridData:function(postData){//添加查询条件值
				var obj = getQueryCondition();
    			$ .extend(true,obj,postData);//合并查询条件值与grid的默认传递参数
    			return obj;
    		}
      });
  		ajaxGetMarketStatistic();
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
  	//xin zeng iframe 弹出框
		var url="<m:url value='/marketStudent/toAddMarketStudent.do'/>";
		add_iframe_dialog = new biz.dialog({
			id:$('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: 800,
			height: $(window).height()*0.6,
			title: "录入学员信息表增加"
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
		var url="<m:url value='/marketStudent/toEditMarketStudent.do'/>?key="+key;
		edit_iframe_dialog = new biz.dialog({
		 	id:$('<div id="editwindow_iframe"></div>').html('<iframe id="iframeEdit" name="iframeEdit" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: $(window).width()*0.6,
			height:$(window).height()*0.8,
			title: "录入学员信息表编辑"
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
		var url="<m:url value='/marketStudent/toShowMarketStudent.do'/>?key="+key;
		show_iframe_dialog = new biz.dialog({
		 	id:$('<div id="showwindow_iframe"></div>').html('<iframe id="iframeShow" name="iframeShow" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: $(window).width()*0.6,
			height:$(window).height()*0.8,
				title: "录入学员信息表详情"
		});
  		show_iframe_dialog.open();
    }
    //跟踪
    function genzong(){
    	var key = ICSS.utils.getSelectRowData("id");
		if(key.indexOf(",")>-1||key==""){
			showMessage("请选择一条数据！");
			return ;
		}
		var url="<m:url value='/marketStudent/toGenZongMarketStudent.do'/>?key="+key;
		show_iframe_dialog = new biz.dialog({
		 	id:$('<div id="showwindow_iframe"></div>').html('<iframe id="iframeShow" name="iframeShow" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: $(window).width()*0.6,
			height:$(window).height()*0.8,
				title: "跟踪信息详情"
		});
  		show_iframe_dialog.open();
    }
  //报名
    function baomin(){
    	var key = ICSS.utils.getSelectRowData("id");
		if(key.indexOf(",")>-1||key==""){
			showMessage("请选择一条数据！");
			return ;
		}
		var url="<m:url value='/marketStudent/toBaoMinMarketStudent.do'/>?key="+key;
		show_iframe_dialog = new biz.dialog({
		 	id:$('<div id="showwindow_iframe"></div>').html('<iframe id="iframeShow" name="iframeShow" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: $(window).width()*0.6,
			height:$(window).height()*0.8,
				title: "报名信息详情"
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
        				url: "<m:url value='/marketStudent/deleteMarketStudent.do'/>?key="+ids,
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
  //录入人员随录入管理员二级联动
   function enterChange(val){
    	if(val){
    		$("#userId option").remove();
    		$ .ajax({
    			url: "<m:url value='/marketStudent/getEnterUser.do'/>?createdby="+val,
    			cache:false,
    			dataType:"json",
    			success: function(data, textStatus, jqXHR){
    				 $('#userId').append('<option value="">所有</option>'); 
    				 for(var i in data.userlist){
    					if(data.userlist[i].id){
    						$('#userId').append('<option value="'+data.userlist[i].id+'">'+data.userlist[i].fullName+'</option>');
    					}
    				} 
    			},
    			error:function(){
    				$('#userId option').remove();
    			}
    		});
    	}else{$('#userId option').remove();}
    }
  
  	function ajaxGetMarketStatistic(){
	  	  var paramDatas = {};
			jQuery.each($("#queryForm").serializeArray(),function(i,o){
	      	if(o.value){
	      		paramDatas[o.name] = o.value;
	      	}
	      });
		$.ajax({
   			url : "<m:url value='/marketStudent/getMarketStatistic.do'/>",
   			cache : false,
   			data: paramDatas,
   			async : false,
   			dataType:"json",
   			success : function(data) {
   				drawMainChart(data.curr);
   			}
   		});
	}
	  
   	function drawMainChart(data){
	//初始化变量
	var arr = [];
	var count = [];
	for(var i= 0 ;i< data.length;i++){
		arr.push(data[i].fullname);
		count.push(data[i].notes)
	}
	var mainContainer = document.getElementById('main');
	//用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
	var resizeMainContainer = function () {
		mainContainer.style.width = window.innerWidth+'px';
		mainContainer.style.height = window.innerHeight*0.4+'px';
	};
	//设置容器高宽
	resizeMainContainer();
	// 基于准备好的dom，初始化echarts实例
	var mainChart = echarts.init(mainContainer);
	$(window).on('resize',function(){//屏幕大小自适应，重置容器高宽
	    resizeMainContainer();
	    mainChart.resize();
	});
	var option = {
		tooltip: {
			show:true,
	        trigger: 'axis'
	    },
	     toolbox: {
	        feature: {
	            dataView: {show: true, readOnly: true,title:'数据视图'},//右侧小图标
	            magicType: {show: true, type: ['line', 'bar']},
	            saveAsImage: {show: true}//保存为图片
	        }
	    },
		title: {
           text: ''
       },
       legend: {
           data:['录入人数','业绩增长比']
       },
       //x坐标
       xAxis: [
       	{
  				type: 'category', //坐标轴类型
  				name: '姓名',
  				axisLabel:{
       			show:true
       		},
               data: arr
			}
       ],
       //y坐标
       yAxis: {
           type: 'value',
           name: '录入人数',
           min: 0,
           axisLabel: {
               formatter:'{value}'
           }
       },
		series: [
	        {
	            name:'录入人数',
	            type:'bar',
	            data:count
	        },
	        {
	            name:'业绩增长比',
	            type:'line',
	            data:count
	        }
	    ],
	    color:['#f6ac61', '#c4ccd3'],
	}
	mainChart.setOption(option);
}
</script>
</body>
</html>
