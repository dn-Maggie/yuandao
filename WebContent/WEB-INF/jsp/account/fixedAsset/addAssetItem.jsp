<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/styles/extends/css/fixedAsset.css" />
<script type="text/javascript">
/*获取今日时间*/
var today= new Date(); 
var y = today.getFullYear();
var m = today.getMonth()+1;
var d = today.getDate();
var h = today.getHours(); 
var mi = today.getMinutes(); 
var s = today.getSeconds(); 
var createDate = new Date().format('yyyy-MM-dd');//获取今日时间
var gridObj = {};
$(function() {
	
		gridObj = new biz.grid({
            id:"#remote_rowed",/*html部分table id*/
            url: "<m:url value='/fixedAsset/listAssetItem.do'/>",/*grid初始化请求数据的远程地址*/
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
           	sortname:"id",
           	sortorder:"asc",
           	multiselect:true,
           	multiboxonly:true,
           	pager: '#remote_prowed' /*分页栏id*/,
     		rowList:[5,10,50],//每页显示记录数
    		rowNum:5,//默认显示15条
            colModel:[
				{name : "id",key : true,label:"编号",index : "id"},								
				{name : "assetName",label:"资产项目名称",index : "asset_name"},	
				{name : "assetType",label:"资产类别",index : "asset_type"}
           	],
      });
	
	$("#submit").click(function() {
		if(!biz.validate("valid",$('#fixedAssetFormEdit')[0])){
			showWarn("数据验证失败",3000);
			$("#submit").prop('disabled', false).css({'cursor':'pointer'});
			return;
		}
		var options = {
			url : "<m:url value='/fixedAsset/addAssetItem.do'/>",
			type : "post",
				dataType:"json",
				success : function(d) {
					if(d.status){
						showMessage(d.message,"","",function(){
							resetForm();
							gridObj.trigger('reloadGrid');
						});
					}else{
						showMessage(d.message);
					}
				}
		};
		// 将options传给ajaxForm
		$('#fixedAssetFormEdit').ajaxSubmit(options);
	});

	/*编辑表单数据验证*/
	new biz.validate({
		id:"#fixedAssetFormEdit",
		rules:{
			"assetName" :{required : true},
			"assetType" : {required : true},
		}
	}); 
	
	/*日期格式化*/
	new biz.datepicker({
		id : "#edit_beginDate",
		dateFmt:'yyyy-MM-dd'
	});
});

//重置查询表单
function resetForm(){
	/* $("#assetName").val("");
	$("#assetType").val("固定资产"); */
	$('#fixedAssetFormEdit')[0].reset()
}


</script>
</head>

<body>

	<div id="editDialog">
		<form id="fixedAssetFormEdit" name="fixedAssetFormEdit">
			<div class="wrap">
				<div class="top_head">
					<h3 class="top_name">添加资产项目</h3>
				</div>
				<!-- <input type="hidden" id="edit_id" name="id" type="text" /> -->
				<table class="table">
					<tr>
						<td colspan="4"><i class="icon_bg icon_plan"></i>基本信息</td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>资产项目名称
							：</td>
						<td class="inputTd"><input id="assetName" name="assetName"
							type="text" class="text" /></td>
						<td class="inputLabelTd"><span class="required">*</span>资产类别：</td>
						<td class="inputTd"><select class="input_select text"
							name="assetType" id="assetType" mainid="assetType">
								<option value="固定资产">固定资产</option>
								<option value="无形资产">无形资产</option>
								<option value="流动资产">流动资产</option>
								<option value="长期资产">长期资产</option>
								<option value="递延资产">递延资产</option>
						</select></td>
					</tr>
					<tr>
						<td class="inputTd" colspan="6" style="text-align: center;">
							<input id="submit" type="button" class="ti_bottom" value="保存" />
							<input id="resetbutton" type="button" class="ti_bottom"
							value="重置" onclick="resetForm()" />
						</td>
					</tr>
					<tr>

					</tr>
				</table>
				<i class="icon_bg icon_plan"></i> 已添加的条目
				<div class="listtable_box">
					<!--此处放表格-->
					<table id="remote_rowed"></table>
					<div id="remote_prowed"></div>
				</div>
			</div>
		</form>

	</div>
</body>
</html>
