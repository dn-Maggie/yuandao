<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<title></title>
<style>
.item-wrapper {
	display: block;
	height: 40px;
	line-height: 40px;
	text-indent: 1em;
	cursor: default;
	font-size: 14px;
}

.item-wrapper>.item-class {
	width: 20%;
	height: 100%;
	display: inline-block;
	font-weight: bold;
	border-right: 1px #fff solid;
}

.item-wrapper>.item-value {
	display: inline-block;
}

.item-wrapper>.item-value:hover {
	text-decoration: underline;
	cursor: pointer;
}
</style>
</head>
<body style="height: 100%;">

	<div class="main  choice_box">
		<form id="queryForm">
			<!-- 查询区 表单 -->
			<div class="search border-bottom">
				<ul>
					<li style="width: 180px;"><select class="search_choose"
						name="checkFlag" id="checkFlag" mainid="checkFlag"
						style="width: 100px;">
							<option value="">所有</option>
							<option value="1">未审核</option>
							<option value="2">已审核</option>
					</select><span>审核状态:</span></li>
					<!-- 输入框-->
					<li style="width: 180px;"><select class="search_choose"
						name="assignFlag" id="assignFlag" mainid="assignFlag"
						style="width: 100px;">
							<option value="">所有</option>
							<option value="1">未发放</option>
							<option value="2">已发放</option>

					</select><span>发放状态:</span></li>
					<li class="date_area"><span>申请日期:</span>
						<div class="time_bg">
							<input id="startDate" type="text" class="search_time150"
								name="propsMap['startDate']" mainid="startDate"> <i
								class="search_time_ico1"></i>
						</div> <i>至</i>
						<div class="time_bg">
							<input id="endDate" type="text" class="search_time150"
								name="propsMap['endDate']" mainid="endDate"> <i
								class="search_time_ico1"></i>
						</div></li>

					<li><input type="reset" class="reset_btn"
						onclick="resetForm('queryForm')" value="重置">
					<!-- 重置 --> <input type="button" class="search_btn mr22 "
						onclick="doSearch();" value="查询"></li>
					<!-- 查询-->
				</ul>
			</div>
		</form>
		<div class="listplace">
			<div class="listtable_box" id="listtable_box">
				<div class="item-wrapper" style="background: rgb(98,149,233); color: #fff;">
					<img class="item-img" alt=""> <span class="item-class">统计类型</span>
					<span class="item-value">金额总数</span>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(function(){
        var getStatics = $.ajax({
   			url : "<m:url value='/expenseAccount/staticExpenseAccount.do'/>",
   			cache : false,
   			data: getQueryCondition(),
   			async : false,
   			dataType:"json",
   			success : function(data) {
   				var $listbox = $("#listtable_box")[0];
   				var htmlArr = [];
   				for(var i = 0; i<data.statics.length ;i++){
   					var item = data.statics[i];
   					htmlArr.push('<div class="item-wrapper">');
   					htmlArr.push('<span class="item-class">');
   					htmlArr.push(item.id);
   					htmlArr.push('</span>');
   					htmlArr.push('<span class="item-value">');
   					htmlArr.push(item.expenseMoney);
   					htmlArr.push('</span>');
   					htmlArr.push('</div>');
   				}
   				$listbox.innerHTML += htmlArr.join(' ');
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
    	if(!isStayCurrentPage)getStatics;
    }
    //重置查询表单
    function resetForm(formId){
		document.getElementById(formId).reset();
	}
    
</script>
</body>
</html>
