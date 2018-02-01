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
            url: "<m:url value='/fixedAsset/listRecovery.do'/>",/*grid初始化请求数据的远程地址*/
            datatype: "json",/*数据类型，设置为json数据，默认为json*/
           	sortname:"id",
           	sortorder:"asc",
           	multiselect:true,
           	multiboxonly:true,
           	pager: '#remote_prowed' /*分页栏id*/,
     		rowList:[5,10,50],//每页显示记录数
    		rowNum:10,//默认显示15条
            colModel:[
				{name : "id",hidden : true,key : true,label:"主键",index : "id"},				
				{name : "assetNo",label:"资产编号",index : "asset_no"},				
				{name : "assetName",label:"资产名称",index : "asset_name"},	
				{name : "useOrg",label:"使用部门",index : "use_org"},
				{name : "workNumber",label:"使用人",index : "work_number"},
				/* {name : "assetItemId",label:"资产项目名称",index : "asset_item_id"},				
				{name : "model",label:"规格型号",index : "model"},	 */			
				{name : "beginDate",label:"开始使用日期",index : "begin_date"},				
				/* {name : "initialValue",label:"资产原值",index : "initial_value"},		
				{name : "perDepred",label:"本期折旧 "},	 */
				{name : "propertyState",label:"资产状态 ",index : "property_state",align:"center",formatter:GridColModelForMatter.fixedPropertyState}
           	],
      });
	
	$("#submit").click(function() {
		var options = {
			url : "<m:url value='/fixedAsset/recovery.do'/>",
			type : "post",
				dataType:"json",
				success : function(d) {
					window.parent.closeRecovery();
				}
		};
		$('#fixedAssetFormEdit').ajaxSubmit(options);
	});

});

</script>
</head>

<body>
	<div id="editDialog">
		<form id="fixedAssetFormEdit" name="fixedAssetFormEdit">
			<div class="wrap">
				<div class="top_head">
					<h3 class="top_name">待回收的资源</h3>
				</div>
				<!-- <input type="hidden" id="edit_id" name="id" type="text" /> -->
				<i class="icon_bg icon_plan"></i>资源列表
				<div class="listtable_box">
					<!--此处放表格-->
					<table id="remote_rowed"></table>
					<div id="remote_prowed"></div>
				</div>
				<table class="table">
					<tr>
						<td class="inputTd" colspan="6" style="text-align: center;">
							<input id="submit" type="button" class="ti_bottom" value="一键回收" />
							<input id="resetbutton" type="button" class="ti_bottom"
							value="取消" onclick="window.parent.closeRecovery();" />
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>
</html>
