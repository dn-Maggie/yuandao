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
$(function() {
	$("#edit_currdate").val(createDate);
	$("#edit_enterDate").val(y+'年第'+(m<10?('0'+m):m)+'期');
	$("#enterDate").val(y+'-'+(m<10?('0'+m):m));
	//绑定提交按钮click事件
	$("#submit").click(function() {
		$("#submit").prop('disabled', true).css({'cursor':'not-allowed'});
		if(!biz.validate("valid",$('#fixedAssetFormEdit')[0])){
			showWarn("数据验证失败",3000);
			$("#submit").prop('disabled', false).css({'cursor':'pointer'});
			return;
		}
		var options = {
			url : "<m:url value='/fixedAsset/updateFixedAsset.do'/>",
			type : "post",
				dataType:"json",
				success : function(d) {
					if(d.status){
						showMessage(d.message,"","",function(){
							window.parent.closeEdit();
							List.doSearch(window.parent.gridObj);
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
			"assetNo" :{required : true},
			"enterDate" : {required : true},
			"assetName" :{required : true},
			"assetType" : {required : true},
			"beginDate" : {required : true},
			"useOrg" :{required : true},
			"enterDate" : {required : true},
			"assetName" :{required : true},
			"assetType" : {required : true},
			"model" : {required : true},
			"beginDate" : {required : true},
		}
	}); 
	
	/*日期格式化*/
	new biz.datepicker({
		id : "#edit_beginDate",
		dateFmt:'yyyy-MM-dd'
	});
});

//s 选择折旧科目
function chooseZJSub(Object){
	$('.zjSubjectList').css('display','none');
	$(Object).parents('td').find('.zjSubjectList').css('display','block');
}
//s hideZjSubjectList
function hideZjSubjectList(){
	$('.zjSubjectList').css('display','none');
}
//折旧科目填充
function fillName(Object){
	$(Object).parents('td').find('#edit_zjfySubject').val($(Object).attr('aname'));
	$(Object).parents('.zjSubjectList').css('display','none');
}
//自动计算预计残值
function countYJCZ(){
	var czyz = $("#edit_initialValue").val();
	var czl = $("#edit_remainRatio").val();
	var yjcz = czl*parseFloat(czyz)*0.01;
	$("#edit_remainValue").val(parseFloat(yjcz).toFixed(2));
}
//自动计算剩余使用月份
function countRemainMonth(){
	var yjsy = $("#edit_usePeriod").val();
	var ysy = $("#edit_depredMonth").val();
	var remainMonth = Number(yjsy)-Number(ysy);
	$("#edit_remainUseMonth").val(remainMonth);
}
//自动计算每月折旧额
function countPerDepre(){
	var all =$("#edit_initialValue").val(); 
	var yjcz = $("#edit_remainValue").val();
	var ljzj = $("#edit_usedDepre").val();
	var remainMonth = $("#edit_remainUseMonth").val();
	if(remainMonth>0){
			var perDepre = (Number(all)-Number(yjcz)-Number(ljzj))/Number(remainMonth);	
			$("#edit_perDepred").val(parseFloat(perDepre).toFixed(2));
		}
}

function worknumberchangge(v){
	$("#workNumber").val(v);
}
/* //部门员工联动
function orgEmp(val){
	//当dataList里面有数据的时候，不需要执行ajax
	if($("#employeeList")[0].children.length>0){
	}else{//当dataList里面没有数据的时候(初始加载ajax)
		if(val&val.length>0){
			console.log("a");
			$ .ajax({
				url: "<m:url value='/employee/listEmployeeByCondition.do'/>",
				cache:false,
				dataType:"json",
				data:{ deptNo:val},
				success: function(data, textStatus, jqXHR){
					for(var i = 0; i < data.length; i++){
						$("#employeeList").append('<option value="'+data[i].nickName+'" label="'+data[i].name+'" did="'+data[i].id+'"></option>')
					}
				}
			});
		}
	}
}  */
/*自动搜寻转化人 自动完成(Autocomplete) 根据用户输入值进行搜索和过滤,让用户快速找到并从预设值列表中选择*/
function getEmpIDByName(obj,value) {
	var did = $("#employeeList").find("option[value="+value+"]").attr('did');
	$(obj).parents('td').find("#edit_applyName").val(did);
}
</script>
</head>

<body>

	<div id="editDialog">
		<form id="fixedAssetFormEdit">
			<div class="wrap">
				<div class="top_head">
					<h2 class="top_name">公司资源</h2>
					<div class="time_bg"
						style="top: 40px; right: 0px; height: 25px; line-height: 25px; position: absolute;">
						<span>录入日期&nbsp;&nbsp;</span> <input id="edit_currdate"
							type="text" class="search_time150" name="currdate"
							mainid="currdate" style="height: 25px;"
							value="${fixedAsset.currdate}" readonly> <i
							class="search_time_ico2" style="top: 6px;"></i>
					</div>
				</div>
				<input type="hidden" id="edit_id" name="id" type="text"
					value="${fixedAsset.id}" />
				<table class="table">
					<tr>
						<td colspan="4" class="cut"><i class="icon_bg icon_plan"></i>
							基本信息</td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>资产编号：</td>
						<td class="inputTd"><input id="edit_assetNo" name="assetNo"
							type="text" class="text" value="${fixedAsset.assetNo}" /></td>
						<td class="inputLabelTd">会计期间：</td>
						<td class="inputTd"><input id="edit_enterDate" type="text"
							class="text" value="${fixedAsset.enterDate}" readonly /> <input
							name="enterDate" id="enterDate" type="hidden" class="text"
							value="${fixedAsset.enterDate}" /></td>
						<td class="inputLabelTd">增加方式：</td>
						<td class="inputTd"><input id="edit_addType" name="addType"
							type="text" class="text" value="${fixedAsset.addType}" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>资产项目名称：</td>
						<td class="inputTd"><input id="edit_assetName"
							name="assetName" type="text" class="text"
							value="${fixedAsset.assetName}" /></td>
						<td class="inputLabelTd"><span class="required">*</span>资产类别：</td>
						<td class="inputTd"><select class="input_select text"
							name="assetItemId" id="edit_assetType" mainid="assetItemId">
								<c:forEach var="assetItem" items="${assetItem}">
									<option
										<c:if test="${fixedAsset.assetType==assetItem.id}">selected</c:if>
										value="${assetItem.id}">${assetItem.assetName}</option>
								</c:forEach>
						</select></td>
						<td class="inputLabelTd">规格型号：</td>
						<td class="inputTd"><input id="edit_model" name="model"
							type="text" class="text" value="${fixedAsset.model}" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>开始使用日期：</td>
						<td class="inputTd">
							<div class="time_bg">
								<input type="text" class="valid text" name="beginDate"
									id="edit_beginDate" mainid="beginDate"
									value="${fixedAsset.beginDate}">
								<!-- 时间选择控件-->
								<i class="search_time_ico2"
									style="display: inline-block; top: 8px"></i>
							</div>
						</td>
						<td class="inputLabelTd"><span class="required">*</span>使用部门：</td>
						<td class="inputTd"><select class="input_select text"
							name="useOrg" id="edit_useOrg" mainid="useOrg">
								<c:forEach var="org" items="${org}">
									<option value="${org.orgNo}"
										<c:if test="${fixedAsset.useOrg==org.orgName}">selected</c:if>>${org.orgName}</option>
								</c:forEach>
						</select></td>
						<td class="inputLabelTd"><span class="required">*</span>使用人：</td>
						<td class="inputTd"><input id="workNumber" name="workNumber"
							type="hidden" class="text" value="${fixedAsset.workNumber}" /> <input
							id="edit_workName" name="workName" type="text" class="text"
							value="${fixedAsset.workNumber}"
							onchange="worknumberchangge(this.value)" /></td>
					</tr>
					<tr>
						<td colspan="4" class="cut"><i class="icon_bg icon_plan"></i>
							折旧方式</td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>折旧方法：</td>
						<td class="inputTd"><select class="input_select text"
							name="depreMethod" id="edit_depreMethod" mainid="depreMethod">
								<option value="1"
									<c:if test="${fixedAsset.depreMethod=='1'}">selected</c:if>>平均年限法</option>
								<option value="2"
									<c:if test="${fixedAsset.depreMethod=='2'}">selected</c:if>>不折旧</option>
						</select></td>
						<td class="inputLabelTd"><span class="required">*</span>当期是否折旧：</td>
						<td class="inputTd"><label class="radio"><input
								type="radio" name="isDeprenow" id="edit_isDeprenow1" value="是"
								<c:if test="${fixedAsset.isDeprenow=='是'}">checked</c:if>>是</label>
							<label class="radio"><input type="radio"
								name="isDeprenow" id="edit_isDeprenow2" value="否"
								<c:if test="${fixedAsset.isDeprenow=='否'}">checked</c:if>>否</label>
						</td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>累计折旧科目：</td>
						<td class="inputTd"><select class="input_select text"
							name="ljzjSubject" id="edit_ljzjSubject" mainid="ljzjSubject">
								<option value="累计折旧">累计折旧</option>
						</select></td>
						<td class="inputLabelTd"><span class="required">*</span>折旧费用科目：</td>
						<td class="inputTd"><input id="edit_zjfySubject"
							name="zjfySubject" type="text" class="text" value="管理费用-折旧费"
							onclick="chooseZJSub(this)" onblur="hideZjSubjectList()"
							value="${fixedAsset.zjfySubject}" />
							<div class="zjSubjectList">
								<ul class="allZjSubjectList" style="position: absolute">
									<c:forEach var="zjSubjectList" items="${zjSubjectList}">
										<li aname="${zjSubjectList.accountName}"
											onclick="fillName(this);">${zjSubjectList.accountId}-
											${zjSubjectList.accountName}</li>
									</c:forEach>
								</ul>
							</div></td>
						<td class="inputLabelTd"><span class="required">*</span>资产清理科目：</td>
						<td class="inputTd"><select class="input_select text"
							name="zichanClear" id="edit_zichanClear" mainid="zichanClear">
								<option value="固定资产清理">固定资产清理</option>
						</select></td>
					</tr>
					<tr>
						<td colspan="4" class="cut"><i class="icon_bg icon_plan"></i>
							资产数据</td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>资产原值：</td>
						<td class="inputTd"><input id="edit_initialValue"
							name="initialValue" type="text" class="text"
							value="${fixedAsset.initialValue}" /></td>
						<td class="inputLabelTd"><span class="required">*</span>残值率：</td>
						<td class="inputTd"><input id="edit_remainRatio"
							name="remainRatio" type="text" class="text"
							value="${fixedAsset.remainRatio}" onblur="countYJCZ();" />&nbsp;%
						</td>
						<td class="inputLabelTd">预计残值：</td>
						<td class="inputTd"><input id="edit_remainValue"
							name="remainValue" type="text" class="text"
							style="background: #eeeeee" value="${fixedAsset.remainValue}"
							readonly /></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>预计使用月份：</td>
						<td class="inputTd"><input id="edit_usePeriod"
							name="usePeriod" type="text" class="text"
							value="${fixedAsset.usePeriod}"
							onblur="countRemainMonth();countPerDepre();" /></td>
						<td class="inputLabelTd"><span class="required">*</span>已折旧月份：</td>
						<td class="inputTd"><input id="edit_depredMonth"
							name="depredMonth" type="text" class="text"
							value="${fixedAsset.depredMonth}"
							onblur="countRemainMonth();countPerDepre();" /></td>
						<td class="inputLabelTd">剩余使用月份：</td>
						<td class="inputTd"><input id="edit_remainUseMonth"
							name="remainUseMonth" type="text" class="text"
							value="${fixedAsset.remainUseMonth}" style="background: #eeeeee"
							readonly /></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>累计折旧：</td>
						<td class="inputTd"><input id="edit_usedDepre"
							name="usedDepre" type="text" class="text"
							value="${fixedAsset.usedDepre}" onblur="countPerDepre();" /></td>
						<td class="inputLabelTd"><span class="required">*</span>每月折旧额：</td>
						<td class="inputTd"><input id="edit_perDepred"
							name="perDepred" type="text" class="text"
							style="background: #eeeeee" value="${fixedAsset.perDepred}"
							readonly /></td>
						<td class="inputLabelTd">资产状态：</td>
						<td class="inputTd"><select class="input_select text"
							name="propertyState" id="edit_propertyState"
							mainid="propertyState">
								<option value="1"
									<c:if test="${fixedAsset.propertyState=='1'}">selected</c:if>>使用中</option>
								<option value="4"
									<c:if test="${fixedAsset.propertyState=='4'}">selected</c:if>>停用中</option>
								<option value="2"
									<c:if test="${fixedAsset.propertyState=='2'}">selected</c:if>>维修中</option>
								<option value="3"
									<c:if test="${fixedAsset.propertyState=='3'}">selected</c:if>>已报废</option>
						</select></td>
					</tr>
					<tr>
						<td class="inputTd" colspan="6" style="text-align: center;">
							<input id="submit" type="button" class="ti_bottom" value="保存" />
							<input id="reset" type="button" class="ti_bottom" value="取消"
							onclick="window.parent.closeEdit();" />
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>
</html>
