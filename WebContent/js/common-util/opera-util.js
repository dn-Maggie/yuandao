Add={
		create:function(url,title,width,height){
			add_iframe_dialog = new biz.dialog({
				id:$('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
				modal: true,
				width: width?width:$(window).width()*0.6,
				height: height?height:$(window).height()*0.8,
				title: title
			});
			return add_iframe_dialog;
		},
};
//修改的弹出框
Edit ={
		create:function(key,url,title,width,height){
			var key = key;
			if(key.indexOf(",")>-1||key==""){
				showMessage("请选择一条数据！");
				return ;
			}
			var url=url+"?key="+key;
			edit_iframe_dialog = new biz.dialog({
			 	id:$('<div id="editwindow_iframe"></div>').html('<iframe id="iframeEdit" name="iframeEdit" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
				modal: true,
				width: width?width:$(window).width()*0.6,
				height: height?height:$(window).height()*0.8,
				title: title
			});
			return edit_iframe_dialog;
		},
} ;
//查看的弹出框
Show = {
		create:function(key,url,title,width,height){
			var key = key;
			if(key.indexOf(",")>-1||key==""){
				showMessage("请选择一条数据！");
				return ;
			}
			var url=url+"?key="+key;
			show_iframe_dialog = new biz.dialog({
			 	id:$('<div id="showwindow_iframe"></div>').html('<iframe id="iframeShow" name="iframeShow" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
				modal: true,
				width: width?width:$(window).width()*0.6,
				height: height?height:$(window).height()*0.8,
				title:title
			});
	  		return show_iframe_dialog;
		},
};
List = {
		createGrid :function(url,colModel,sortname,gridCompleteCount){ 
	    	return new biz.grid({
		        id:"#remote_rowed",
		        url: url,
		        multiselect: true,  
		        multiboxonly:true,  
		    	datatype: "json",
		       	sortname:sortname,
		       	sortorder:"desc",
		       	footerrow:gridCompleteCount,
		       	pager: "remote_prowed",
		        colModel:colModel,
		        rownumbers:false,
				serializeGridData:function(postData){//添加查询条件值
					var obj = List.getQueryCondition();
					$ .extend(true,obj,postData);//合并查询条件值与grid的默认传递参数
					return obj;
				},
		        emptyrecords: "无记录可显示",
		        rowList:[10,15,50,100],//每页显示记录数
				rowNum:15,//默认显示15条
				gridComplete:function(){//表格加载执行  
				    $(this).closest(".ui-jqgrid-bdiv").css({ 'overflow-x' : 'hidden' });
				 	if(gridCompleteCount){
				 		$(".ui-jqgrid-sdiv").show();
					 	var footerCell = $(this).footerData();
					 	var footerObj = {};
					 	for(var i in footerCell){
					 		footerObj[i]=$(this).getCol(i,false,"sum")?$(this).getCol(i,false,"sum").toFixed(3):0;
					 	}
					 	footerObj['raw'] = true;
					 	footerObj['rn'] = "合";
					 	footerObj['cb'] = "计";
				    	$(this).footerData("set",footerObj); //将合计值显示出来
				 	}
				}
	    	});
	    },
	    openDialog:function(dialog){
	    	if(dialog!=undefined)dialog.open();
	    },
	    closeDialog:function(dialog,gridObj,gridObj2,gridObj3){
	    	dialog.close();
	    	if(gridObj){List.doSearch(gridObj);}
	    	if(gridObj2){List.doSearch(gridObj2);}
	    	if(gridObj3){List.doSearch(gridObj3);}
	    },
	    batchDelete:function(id,url,gridObj,gridObj2){
    	var ids = id;
    	if(ids==""){
    		showMessage("请至少选择一条数据！");
    		return ;
    	}else{
    		new biz.alert({type:"confirm",message:I18N.msg_del_confirm,title:I18N.promp,callback:function(result){
    			if(result){
    				$ .ajax({
        				url: url+"?key="+ids,
        				cache:false,
        				success: function(data, textStatus, jqXHR){
    						showMessage("操作成功","","",function(){
    							List.doSearch(gridObj);
    							if(gridObj2)List.doSearch(gridObj2);
    						});
        				}
        			});
    			}
    		}});   
    	}
    },
    /**获取查询条件值*/
	getQueryCondition:function (){
       var obj = {};
		jQuery.each($("#queryForm").serializeArray(),function(i,o){
			//$("#queryForm").serializeArray()输出以数组形式序列化表单值的结果，返回 JSON 数据结构数据
 			//此方法返回的是 JSON 对象而非 JSON 字符串。需要进行字符串化操作
 			//返回的 JSON 对象是由一个对象数组组成的，其中每个对象包含一个或两个名值对 —— name 参数和 value 参数
         	if(o.value){
         		obj[o.name] = o.value;//遍历json对象把数据存入obj中
         	}
        });
		return obj;
    },
    /**按条件查询结果*/
	doSearch: function (gridObj){
		if(gridObj){gridObj.setGridParam({"page":"1"});gridObj.trigger('reloadGrid');}
    },
    resetForm:  function(formId){
		document.getElementById(formId).reset();
	},
}