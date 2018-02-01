GridUtil = {
	/**按条件查询结果*/
	doSearch: function (gridObj){
		if(gridObj){gridObj.setGridParam({"page":"1"});gridObj.trigger('reloadGrid');}
    },
    /**获取查询条件值*/
    getQueryCondition:function(){
        var obj = {};
 		jQuery.each($("#queryForm").serializeArray(),function(i,o){
         	if(o.value){
         		obj[o.name] = o.value;
         	}
         });
 		return obj;
     },
     createGrid :function(url,colModel,sortname,gridCompleteCount){ 
	    	return new biz.grid({
		        id:"#remote_rowed",
		        url: url,
		    	datatype: "json",
		       	sortname:sortname,
		       	sortorder:"asc",
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
    /**金额数字格式化*/
  	formatAccountting:function(cellValue,options,rowObject){
  		return accounting.formatMoney(cellValue,"",2).replace(".00","").replace(",","");
  	}
}