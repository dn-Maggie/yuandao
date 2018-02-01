<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html  >
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<script type="text/javascript" charset="utf-8"
	src="<%=request.getContextPath()%>/js/huploadify/jquery.Huploadify.js"></script>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/js/huploadify/Huploadify.css">
<script type="text/javascript">
	var menutree;
	var areaProvinceBox, areaRegionBox, areaCityBox;
	$(function() {
		//取得添加节点的父节点ID

		//省
		areaProvinceBox = $('#areaProvinceName').TiledCombobox({
			url : "<m:url value='/areaProvince/list.do'/>",
			fieldId : 'aapid',
			fieldName : 'provinceName',
			valueId : '#areaProvinceId',
			multiple : false,
			onChangeFn : function(v) {
				areaRegionBox.setQueryDataAndReload("apid=" + v);
				areaRegionBox.clearVlaue();
				areaCityBox.clearVlaue();
			}
		});
		//市
		areaRegionBox = $('#areaRegionName').TiledCombobox({
			url : "<m:url value='/areaRegion/list.do'/>",
			fieldId : 'aarid',// 数据有几级
			fieldName : 'regionName',
			valueId : '#areaRegionId',
			multiple : false,
			autoLoadData:false,
			onChangeFn : function(v) {
				areaCityBox.setQueryDataAndReload("aarid=" + v);
			}
		});
		//县区市
		areaCityBox = $('#areaCityName').TiledCombobox({
			url : "<m:url value='/areaCity/list.do'/>",
			fieldId : 'aacid',// 数据有几级
			fieldName : 'cityName',
			valueId : '#areaCityId',
			autoLoadData:false,
			multiple : false
		});


		
		
		//身份证图片上传控件
			 var up=$("#upload").Huploadify(
					{
						auto : true,
						fileTypeExts : '*.*',
						multi : true,
						// formData:{key:123456,key2:'vvvv'},
						fileSizeLimit : 102400,
						showUploadedPercent : true,
						showUploadedSize : true,
						removeTimeout : 9999999,
						uploader : "<m:url value='/upload/idCard.do'/>",
						onUploadStart : function(file) {
							console.log(file.name + '开始上传');
						},
						onInit : function(obj) {
							console.log('初始化');
							console.log(obj);
						},
						onUploadComplete : function(file) {
							console.log(file.name + '上传完成');
						},
						onCancel : function(file) {
							console.log(file.name + '删除成功');
						},
						onClearQueue : function(queueItemCount) {
							console.log('有' + queueItemCount + '个文件被删除了');
						},
						onDestroy : function() {
							console.log('destroyed!');
						},
						onSelect : function(file) {
							console.log(file.name + '加入上传队列');
						},
						onQueueComplete : function(queueData) {
							console.log('队列中的文件全部上传完成', queueData);
							up.cancel('*');
						},
						onUploadSuccess : function(file, data, response) {
							console.log('服务器返回数据', data);
							data = jQuery.parseJSON(data);
							$('#idCardImgPath').val(data.data);
							var imgUrl="<m:url value='/download/tmpIdCard.do'/>?fileName="+data.data;
							$("#idCardImgDiv").html("");
							$("#idCardImgDiv").append($('<img style="max-width:600px;" src="'+imgUrl+'"/>'));
							
						}
					});
		

		
		
		//绑定提交按钮click事件
		$("#submit").click(function() {
			if (!biz.validate("valid", $('#userInfoFormAdd')[0])) {
				showWarn("<m:message code='validation.object'/>", 3000);
				return;
			}
			var userAccount = $("#edit_userAccount").val();
			if (ajaxGetUserInfoByUserAccount(userAccount)) {
				showMessage("登录账户已经存在，请重新输入.");
				return;
			}
			var options = {
				url : "<m:url value='/signupUserInfo/add.do'/>",
				type : "post",
				dataType:"json",
				cache : false,
				success : function(d) {
					if(d.status){
						showMessage(d.message,"","",function(){
							window.parent.closeAdd();
				     		window.parent.doSearch();
						});
					}else{
						showMessage(d.message);
					}
				}
			};
			// 将options传给ajaxForm
			$('#userInfoFormAdd').ajaxSubmit(options);
		});

		/*编辑表单数据验证*/
		new biz.validate({
			id : "#userInfoFormAdd",
			rules : {
				"userAccount" : {
					required : true,
					alnumline : true,
					maxlength : 50
				},
				"fullName" : {
					required : true,
					maxlength : 15
				},
				"age" : {
					required : true,
					maxlength : 2
				},
				"mobilePhone" : {
					required : true,
					maxlength : 11
				},
				"idCard" : {
					required : true,
					maxlength : 18
				},
				"dutyName" : {
					required : true,
					maxlength : 18
				},
				"memo" : {
					maxlength : 150
				}
			}
		});

		//入职日期
		new biz.datepicker({
			id : "#edit_entryDate",//容器Id   
			event : "click",
			dateFmt : 'yyyy-MM-dd HH:mm:ss'
		});
		//出生日期
		new biz.datepicker({
			id : "#edit_birthday",//容器Id   
			event : "click",
			dateFmt : 'yyyy-MM-dd HH:mm:ss'
		});

		new biz.select(
				{//状态下拉
					id : "#edit_states",
					url : "<m:url value='/dictInfo/getDictByTypeCode.do?dictTypeCode=status'/>"
				});

	});

	function ajaxGetUserInfoByUserAccount(userAccount) {
		var b = false;
		if (userAccount != null && userAccount != "") {
			$
					.ajax({
						url : "<m:url value='/userInfo/ajaxGetUserInfoByUserAccount.do'/>?userAccount="
								+ userAccount,
						cache : false,
						async : false,
						success : function(data, textStatus, jqXHR) {
							if (data == "true") {
								showMessage("登录账户已经存在，请重新输入.");
								b = true;
							}
						}
					});
		}
		return b;
	}

</script>
</head>

<body>
	<form id="userInfoFormAdd">
		<hi:icssToken />
		<div class="ui-table ui-widget ui-corner-all ui-border">
			<table class="table">
				<tr>
					<td class="inputLabelTd"><span class="required">*</span>登录账号：</td>
					<td class="inputTd"><input id="edit_userAccount"
						name="userAccount" type="text" class="text"
						value="${userInfo.userAccount}" /></td>
					<td class="inputLabelTd"><span class="required">*</span>用户姓名：</td>
					<td class="inputTd"><input id="edit_fullName" name="fullName"
						type="text" class="text" value="${userInfo.fullName}" /></td>

				</tr>
				<tr>
					<td class="inputLabelTd"><span class="required">*</span>手机号码：</td>
					<td class="inputTd"><input id="mobilePhone" name="mobilePhone"
						type="text" class="text" value="${userInfo.mobilePhone}" /></td>
					<td class="inputLabelTd"><span class="required">*</span>身份证号码：</td>
					<td class="inputTd"><input id="idCard" name="idCard"
						type="text" class="text" value="${userInfo.idCard}" /></td>
				</tr>
				<tr>
					<td class="inputLabelTd">性别：</td>
					<td class="inputTd"><select id="edit_sex" name="sex"
						class="select">
							<option value="1">男</option>
							<option value="0">女</option>
					</select></td>
					<td class="inputLabelTd"><span class="required">*</span>年龄：</td>
					<td class="inputTd"><input id="age" name="age" type="text"
						class="text" value="${userInfo.age}" /></td>
				</tr>
				<tr>
					<td class="inputLabelTd">出生年月：</td>
					<td class="inputTd"><input id="edit_birthday" name="birthday"
						type="text" class="text" value="${userInfo.birthday}" /></td>
					<td class="inputLabelTd">邮箱地址：</td>
					<td class="inputTd"><input id="edit_userEmail"
						name="userEmail" type="text" class="text"
						value="${userInfo.userEmail}" /></td>
				</tr>
				<tr>
					<td class="inputLabelTd">地址：</td>
					<td colspan="3" class="inputTd">省 <input id="areaProvinceName"
						name="areaProvinceName" type="text" class="text"
						value="${userInfo.areaProvinceName}" /> <input
						id="areaProvinceId" name="areaProvinceId" type="hidden"
						class="text" value="${userInfo.areaProvinceId}" /> 市 <input
						id="areaRegionName" name="areaRegionName" type="text" class="text"
						value="${userInfo.areaRegionName}" /> <input id="areaRegionId"
						name="areaRegionId" type="hidden" class="text"
						value="${userInfo.areaRegionId}" /> 县洲区 <input id="areaCityName"
						name="areaCityName" type="text" class="text"
						value="${userInfo.areaCityName}" /> <input id="areaCityId"
						name="areaCityId" type="hidden" class="text"
						value="${userInfo.areaCityId}" /></td>
				</tr>

				<tr>
					<td class="inputLabelTd">身份证图片：</td>
					<td class="inputTd" colspan="3"><input id="idCardImgPath"
						name="idCardImgPath" type="hidden" class="text"
						value="${userInfo.idCardImgPath}" />
						<div id="upload" style="text-align: center"></div> <a
						href="javascript:void(0);"
						onClick="$('#file_upload_1-button').click();">选择图片</a>
						<div id="idCardImgDiv" style="width: 600px;"></div></td>
				</tr>
				<tr>
					<td class="inputTd" colspan="5" style="text-align: center;"><input
						id="submit" type="button" class="ti_bottom" value="保存" /></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
