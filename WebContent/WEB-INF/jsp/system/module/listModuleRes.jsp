<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML  >
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<title><m:message code="moduleRes.list.title" /></title>
<script type="text/javascript">
var moduleResGrid = {};
	$(function(){
  		moduleResGrid = new biz.grid({
            id:"#moduleRes_rowed",/*html部分table id*/
            url: "<m:url value='/moduleRes/listModuleRes.do'/>",/*grid初始化请求数据的远程地址*/
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
           	sortname:"SERIALINDEX",
           	sortorder:"asc",
           	autowidth:true,
			shrinkToFit:true,
			multiselect:true,
	       	multiboxonly:true,
           	pager: '#moduleRes_prowed' /*分页栏id*/,
     		rowList:[10,20,30,50,100,200],//每页显示记录数
    		rowNum:10,//默认显示15条
    		rownumbers: true, //是否显示行号
            colModel:[
               	{name : "id", hidden:true,key:true}, 
    			{name : "muuid", hidden:true}, 
				{name : "resourceName",label:"<m:message code="moduleRes.resourceName"/>",align:"left",width:120,index:"RESOURCE_NAME"},
				{name : "resourcecode",label:"<m:message code="moduleRes.resourcecode"/>",align:"left",width:80,index:"RESOURCECODE"},
				{name : "resurl",label:"<m:message code="moduleRes.resurl"/>",align:"left",width:120,index:"RESURL"},
				{name : "status",label:"<m:message code="moduleRes.status"/>",formatter:ma_status,align:"center",width:40,index:"STATUS"},
				{name : "needright",label:"<m:message code="moduleRes.needright"/>",formatter:ma_needright,align:"center",width:40,index:"NEEDRIGHT"},
				{name : "serialindex",label:"<m:message code="moduleRes.serialindex"/>",align:"left",width:40,index:"SERIALINDEX"},
				{name : "memo",label:"<m:message code="moduleRes.memo"/>",sortable:false}
           	],
           	serializeGridData:function(postData){//添加查询条件值
				var obj = {muuid:"${muuid}"};
    			$.extend(true,obj,postData);//合并查询条件值与grid的默认传递参数   				
    			return obj;
    			
    		},
    		gridComplete:function(){
    			//数据加载完毕后，调整样式
    			var ids = moduleResGrid.getDataIDs();
    			if(ids==""){
    				$("#next_remote_prowed").attr("class","ui-pg-button ui-corner-all ui-state-disabled");
    				$("#last_remote_prowed").attr("class","ui-pg-button ui-corner-all ui-state-disabled");
    			}
    	  	},
			setRightWidth:function(){
				
			}
      });
  		
  		window.parent.loadedMask();
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
    
    function doSearch(isStayCurrentPage){
    	moduleResGrid.setGridParam({"page":"1"});
    	moduleResGrid.trigger('reloadGrid');
    }

    function addModuleRes(){
		var url="<m:url value='/module/toSelectModuleRes.do'/>?key=${muuid}";
		mrselect_iframe_dialog = new biz.dialog({
			id:$('<div id="mrselect_window_iframe"></div>').html('<iframe id="iframeMrSelect" name="iframeMrSelect" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: 800,
			height:600,
			title: "<m:message code='moduleRes.list.title' />"
		});
		mrselect_iframe_dialog.open();
    }
    function closeMrSelect(){
    	mrselect_iframe_dialog.close();
    }

    function delMrFromModule(){
    	rowid = moduleResGrid.getGridParam("selarrrow");
    	if(rowid == null || rowid.length == 0){
			showInfo("<m:message code='grid.delete.chooseColAlert'/>",3000);
			return ;
		}
    	new biz.alert({type:"confirm",message:I18N.msg_del_confirm,title:I18N.promp,callback:function(result){
	    		if (!result) {
	    			return;
	    		}
				var ids="";
				for(var i=0; i < rowid.length;i++){
					var id = moduleResGrid.getCell(rowid[i],'id');
					if(i>0){
						ids +=',';
					}
					ids += id;
				}
				$.ajax({
					url: "<m:url value='/moduleRes/changeMrMuuid.do'/>",
					data: {resuuids:ids,muuid:""},
    				cache:false,
					success: function(data){
    					doSearch();
						showInfo("<m:message code='delete.success'/>",3000);
					}
				});
			}
		});
    }
    </script>
</head>
<body>
	<div class="listplace">
		<!--功能按钮begin-->
		<div class="list_btn_bg fl">
			<!--功能按钮 div-->
			<ul>
				<li><a title="<m:message code="button.add"/>"
					href="javascript:;" onclick="addModuleRes();"> <i
						class="icon_bg icon_add"> </i> <span><m:message
								code="button.add" /></span>
				</a></li>
				<li><a title="<m:message code="button.delete"/>"
					href="javascript:;" onclick="delMrFromModule();"> <i
						class="icon_bg icon_del"></i> <span><m:message
								code="button.delete" /></span>
				</a></li>
			</ul>
		</div>
		<!--功能按钮end-->
		<div class="listtable_box">
			<!--此处放表格-->
			<table id="moduleRes_rowed"></table>
			<div id="moduleRes_prowed"></div>
		</div>
	</div>
	<!--功能列表区end-->

</body>
</html>
