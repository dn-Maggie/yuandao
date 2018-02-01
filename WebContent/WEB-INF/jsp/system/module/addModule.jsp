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
$(function() {
	//绑定提交按钮click事件
	$("#submit").click(function() {
		if(!biz.validate("valid",$('#moduleFormEdit')[0])){
			showWarn("<m:message code='validation.object'/>",3000);
			return;
		}
		var options = {
			url : "<m:url value='/module/addModule.do'/>",
			type : "post",
			dataType:'json',
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
		$('#moduleFormEdit').ajaxSubmit(options);
	});
	
	new biz.select({//状态下拉
	    id:"#edit_enabled",
	    url:"<m:url value='/dictInfo/getDictByTypeCode.do?dictTypeCode=status'/>"
	});
	new biz.select({//状态下拉
	    id:"#edit_needRight",
	    url:"<m:url value='/dictInfo/getDictByTypeCode.do?dictTypeCode=yn_num'/>"
	});
	new biz.number({
		id:"#edit_serialIndex",
		minNum:1,//指定最小值  
		fix:true//是否自动更正  
	}); 

	/*编辑表单数据验证*/
	new biz.validate({
		id:"#moduleFormEdit",
		rules:{
		 moduleName:{required: true,maxlength: 126},
		 moduleCode:{required: true,maxlength: 126},
		 serialIndex:{required:true},
		 navurl:{maxlength: 254},
		 enabled:{required: true},
		 needRight:{required: true},
		 moduleMemo:{maxlength: 126}
		}
	}); 
	
	/* $('#uploadIcon').bind('click', function(){
		//uploadImgShow();
		var resourceObj = null;
		upload('', '', '', "Image", true, updateIcon);
		
	}); */
	
	$('#upload').Huploadify(
	{
		auto : true,
		fileTypeExts : '*.*',
		multi : true,
		// formData:{key:123456,key2:'vvvv'},
		fileSizeLimit : 99999999999,
		showUploadedPercent : true,
		showUploadedSize : true,
		removeTimeout : 9999999,
		uploader :  "<m:url value='/module/upload.do'/>",
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
			//cancel('*');
		},
		onUploadSuccess : function(file, data, response) {
			console.log('服务器返回数据', data);
			data = jQuery.parseJSON(data);
			alert(data.msg);
			if(data.rs == "success"){
				updateIcon(data);
			}
		}
	});

	function updateIcon(data){                                             
		var imgUrl = "<m:url value='/resource/showImg.do?filePath=" + data.filePath  + "&width=30&height=30'/>";
		$("#resourceId").val(data.resource.resourceId);
		$('#menuIcon').attr("src",imgUrl);
	}
});
</script>
</head>

<body>

	<div id="editDialog">
		<form id="moduleFormEdit">
			<hi:icssToken />
			<div class="ui-table ui-widget ui-corner-all ui-border">
				<input type="hidden" id="edit_id" name="id" type="text"
					value="${module.id}" />
				<table class="table">
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>
						<m:message code="module.moduleName" />:</td>
						<td class="inputTd"><input id="edit_moduleName"
							name="moduleName" type="text" class="text"
							value="${module.moduleName}" /></td>
						<td class="inputLabelTd"><span class="required">*</span>
						<m:message code="module.moduleCode" />:</td>
						<td class="inputTd"><input id="edit_moduleCode"
							name="moduleCode" type="text" class="text"
							value="<c:out value='${module.moduleCode}'/>" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><m:message code="module.navurl" />:</td>
						<td class="inputTd"><input id="edit_navurl" name="navurl"
							type="text" class="text" value="${module.navurl}" /></td>
						<td class="inputLabelTd"><span class="required">*</span>
						<m:message code="module.serialIndex" />:</td>
						<td class="inputTd"><input id="edit_serialIndex"
							name="serialIndex" type="text" class="text"
							value="<c:out value='${module.serialIndex}'/>" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>
						<m:message code="module.enabled" />:</td>
						<td class="inputTd"><select id="edit_enabled" name="enabled"
							class="select"></select></td>
						<td class="inputLabelTd"><span class="required">*</span>
						<m:message code="module.needRight" />:</td>
						<td class="inputTd"><select id="edit_needRight"
							name="needRight" class="select"></select></td>
					</tr>
					<tr>
						<td class="inputLabelTd">图标:</td>
						<td class="inputTd" colspan="4"><input id="edit_resourceId"
							name="resourceId" type="text" class="text"
							value="<c:out value='${module.resourceId}'/>" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><m:message code="module.moduleMemo" />:</td>
						<td class="inputTd" colspan="4"><input type="hidden"
							id="resourceId" name="resourceId" type="text"
							value="${module.resourceId}" /> <textarea id="edit_moduleMemo"
								name="moduleMemo" rows="3" style="width: 80%;"><c:out
									value='${module.moduleMemo}' /></textarea></td>
					</tr>
					<tr>
						<td class="inputTd" colspan="4" style="text-align: center;">
							<input id="submit" type="button" class="ti_bottom"
							value="<m:message code='button.save' />" />
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>

</body>
</html>
