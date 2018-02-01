<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML  >
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<title><m:message code="resAct.list.title" /></title>
<script type="text/javascript">
var gridObj = {};
	$(function(){
  		gridObj = new biz.grid({
            id:"#remote_rowed",/*html部分table id*/
            url: "<m:url value='/resAct/listResAct.do'/>",/*grid初始化请求数据的远程地址*/
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
           	sortname:"ACT_NAME",
           	sortorder:"asc",
           	pager: '#remote_prowed' /*分页栏id*/,
     		rowList:[10,20,30,50,100,200],//每页显示记录数
    		rowNum:10,//默认显示15条
    		rownumbers: true, //是否显示行号
            colModel:[
               	{name : "id", hidden:true,key:true}, 
    			{name : "resuuId", hidden:true}, 
				{name : "actName",formatter:"showlink",formatoptions:{baseLinkUrl:"#",click:show},label:"<m:message code="resAct.actName"/>",align:"left",width:120,index:"ACT_NAME"},
				{name : "actCode",label:"<m:message code="resAct.actCode"/>",align:"left",width:80,index:"ACT_CODE"},
				{name : "actUrl",label:"<m:message code="resAct.actUrl"/>",align:"left",width:120,index:"ACT_URL"},
				{name : "needRight",label:"<m:message code="resAct.needRight"/>",formatter:ma_needright,align:"center",width:70,index:"NEED_RIGHT"},
				{name : "states",label:"<m:message code="resAct.states"/>",formatter:ma_status,align:"left",width:40,index:"STATES"},
				{name : "memo",label:"<m:message code="resAct.memo"/>",sortable:false}
           	],
           	serializeGridData:function(postData){//添加查询条件值
				var obj = {resuuId:"${resuuid}"};
    			$.extend(true,obj,postData);//合并查询条件值与grid的默认传递参数
    			return obj;
    		},
    		gridComplete:function(){
    			//数据加载完毕后，调整样式
    			var ids = gridObj.getDataIDs();
    			if(ids==""){
    				$("#next_remote_prowed").attr("class","ui-pg-button ui-corner-all ui-state-disabled");
    				$("#last_remote_prowed").attr("class","ui-pg-button ui-corner-all ui-state-disabled");
    			}
    	  	}
      });
  		new biz.select({//状态下拉
  		    id:"#states",
  		    url:"<m:url value='/dictInfo/getDictByTypeCode.do?dictTypeCode=status'/>",
  	   		addEmptyItem:true
  		});
  		new biz.select({//状态下拉
  		    id:"#needRight",
  		    url:"<m:url value='/dictInfo/getDictByTypeCode.do?dictTypeCode=yn_num'/>",
  	   		addEmptyItem:true
  		});
        
    });

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
    
    //新增的弹出框
	var add_iframe_dialog;
	//修改的弹出框
   	var edit_iframe_dialog;
	//查看的弹出框
	var show_iframe_dialog;
  	
  	function add(){
  	//xin zeng iframe 弹出框
		var url="<m:url value='/resAct/toAddResAct.do'/>?key=${resuuid}";
		add_iframe_dialog = new biz.dialog({
			id:$('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: 520,
			height: 290,
			title: "<m:message code='resAct.title' />"
		});
		add_iframe_dialog.open();
  	}
  	
  	function closeAdd(){
		add_iframe_dialog.close();
  	}
  	
    function edit(){
		var key = ICSS.utils.getSelectRowData("id");
		if(key.indexOf(",")>-1||key==""){
			showMessage("<m:message code='grid.edit.chooseColAlert'/>");
			return ;
		}
		var url="<m:url value='/resAct/toUpdateResAct.do'/>?key="+key;
		edit_iframe_dialog = new biz.dialog({
		 	id:$('<div id="editwindow_iframe"></div>').html('<iframe id="iframeEdit" name="iframeEdit" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: 520,
			height: 290,
			title: "<m:message code='resAct.title' />"
		});
  		edit_iframe_dialog.open();
  		
    }
    
    //供子页面调用
    function closeEdit(){
    	edit_iframe_dialog.close();
    }
    
    function show(){
    	var key = ICSS.utils.getSelectRowData("id");
		if(key.indexOf(",")>-1||key==""){
			showMessage("<m:message code='grid.view.chooseColAlert'/>");
			return ;
		}
		var url="<m:url value='/resAct/toShowResAct.do'/>?key="+key;
		show_iframe_dialog = new biz.dialog({
		 	id:$('<div id="showwindow_iframe"></div>').html('<iframe id="iframeShow" name="iframeShow" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: 800,
			height: 230,
			title: "<m:message code='resAct.title' />"
		});
  		show_iframe_dialog.open();
    	
    }
    
    function closeShow(){
    	show_iframe_dialog.close();
    }
    
    function doSearch(isStayCurrentPage){
    	if(!isStayCurrentPage)gridObj.setGridParam({"page":"1"});
    	gridObj.trigger('reloadGrid');
    }
    
    //删除
    function batchDelete(){
    	var ids = ICSS.utils.getSelectRowData("id");
    	if(ids==""){
    		showMessage("<m:message code='grid.delete.chooseColAlert'/>");
    		return ;
    	}else{
    		new biz.alert({type:"confirm",message:I18N.msg_del_confirm,title:I18N.promp,callback:function(result){
    			if(result){
    				$.ajax({
        				url: "<m:url value='/resAct/deleteResAct.do'/>?key="+ids,
        				cache:false,
        				success: function(data, textStatus, jqXHR){
        					doSearch();
    						showInfo("<m:message code='delete.success'/>",3000);
        				}
        			});
    			}
    		}}) ;   
    	}
    }
    
    </script>
</head>
<body>
	<div class="container" id="container">
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
				</ul>
			</div>
			<!--功能按钮end-->
			<div class="listtable_box">
				<!--此处放表格-->
				<table id="remote_rowed"></table>
				<div id="remote_prowed"></div>
			</div>
		</div>
		<table id="foot"
			style="height: 80px; margin: 0 auto; position: relative;">
			<tr>
				<td>
					<button class="ti_bottom" id="return_button" type="button"
						onclick="window.parent.closeResAct();">
						<m:message code="button.close" />
					</button>
				</td>
			</tr>
		</table>
	</div>

</body>
</html>
