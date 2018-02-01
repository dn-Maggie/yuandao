<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/styles/extends/css/expenseAccount.css" />
</head>
<body>
	<div id="editDialog">
		<form id="expenseAccountFormEdit">
			<div class="wrap">
				<input type="hidden" id="edit_id" name="id" type="text"
					value="${expenseAccount.id}" />
				<div class="top_head">
					<h2 class="top_name">报 销 单</h2>
					<div
						style="top: 40px; left: 0px; height: 25px; line-height: 25px; position: absolute;">
						<span>报销类型：</span> <select class="input_select" name="expenseType"
							id="edit_expenseType" mainid="expenseType" style="width: 100px">
							<option value="个人报销"
								<c:if test="${expenseAccount.expenseType=='个人报销'}">selected</c:if>>个人报销</option>
							<option value="集体报销"
								<c:if test="${expenseAccount.expenseType=='集体报销'}">selected</c:if>>集体报销</option>
						</select>
					</div>
					<div class="time_bg"
						style="top: 40px; right: 0px; height: 25px; line-height: 25px; position: absolute;">
						<span>申请日期&nbsp;&nbsp;</span> <input id="enterDate" type="text"
							class="search_time150" name="enterDate" mainid="enterDate"
							style="height: 25px;" value="${expenseAccount.enterDate}"
							disabled="disabled"> <i class="search_time_ico2"
							style="top: 6px;" onclick="WdatePicker({el:'enterDate'})"></i>
					</div>
				</div>
				<div class="center_body">
					<table class="table">
						<tr>
							<td class="inputLabelTd"><span class="required">*</span>所属部门：</td>
							<td class="inputTd"><input id="edit_deptNo" name="deptNo"
								type="hidden" value="${expenseAccount.deptNo}" /> <input
								id="edit_deptName" name="deptName" type="text" class="text"
								value="${expenseAccount.deptName}" readonly /></td>
							<td class="inputLabelTd"><span class="required">*</span>报销申请人：</td>
							<td class="inputTd"><input id="edit_enterPid"
								name="enterPid" type="hidden" value="${expenseAccount.enterPid}" />
								<input id="edit_enterName" name="enterName" type="text"
								class="text" value="${expenseAccount.enterName}" readonly /></td>
						</tr>
						<tr class="trDetails" style="height: 100px;">
							<td class="inputLabelTd"><span class="required">*</span>报销项目：</td>
							<td class="inputTd" colspan="3"><textarea class="text"
									name="content" id="edit_content" readonly>${expenseAccount.content}</textarea>
							</td>
						</tr>
						<tr>
							<td class="inputLabelTd"><span class="required">*</span>报销金额：</td>
							<td class="inputTd"><input id="edit_expenseMoney"
								name="expenseMoney" type="text" class="text"
								value="${expenseAccount.expenseMoney}" readonly /></td>
							<td class="inputLabelTd"><span class="required">*</span>报销方式：</td>
							<td class="inputTd"><select class="input_select text"
								name="expenseWay" id="edit_expenseWay" mainid="expenseWay"
								style="width: 97%" disabled="disabled">
									<option value="支付现款"
										<c:if test="${expenseAccount.expenseWay=='支付现款'}">selected</c:if>>支付现款</option>
									<option value="银行转账"
										<c:if test="${expenseAccount.expenseWay=='银行转账'}">selected</c:if>>银行转账</option>
							</select></td>
						</tr>
						<tr>
							<td colspan='4'>
								<div
									style="display: block; float: right; margin-top: 5px; margin-right: 20px;">
									<span class="required">*</span>附单据 <input id="edit_docAttach"
										name="docAttach" type="text" class="text"
										style="width: 50px; text-align: center;"
										value="${expenseAccount.docAttach}" readonly />张 <input
										id="edit_checkFlag" name="checkFlag" type="hidden"
										value="${expenseAccount.checkFlag}" />
								</div>
							</td>
						</tr>
					</table>
					<div style="display: block; margin-top: 5px; text-align: center;">
						<c:choose>
							<c:when test="${not empty expenseAccount.fileUrl}">
								<img alt="图片证明" style="" src="${expenseAccount.fileUrl}">
							</c:when>
							<c:otherwise>
								注：无图片证明
							</c:otherwise>
						</c:choose>
					</div>
					<!-- <div style="background-color:#0D0D0D;height:3px;width:100%;margin-top:15px;"></div> -->
					<table class="table" style="margin-top: 15px;">
						<tr>
							<td class="inputLabelTd"><span class="required">*</span>审核凭证截图：</td>
							<td class="inputTd"><input id="fileData" name="checkPizhu"
								type="hidden"> <input id="file" type="file" class="text"
								style="height: 20px; line-height: 20px;"
								onchange="javascript:setImagePreviews();" /></td>
						</tr>
						<tr>
							<td colspan='2'>
								<div id="preview"
									style="width: 100%; margin-top: 5px; margin-bottom: 5px;"></div>
							</td>
						</tr>
					</table>
					<div class="inputTd"
						style="display: block; text-align: center; width: 100%">
						<!-- <input id="submit" type="button" class="ti_bottom" value="审核" /> -->
						<input id="submitl" type="button" class="ti_bottom" value="审核"
							onclick="audit('lastaudit')" />
						<%-- <c:if test="${isFounder}">
								<input id="submitf" type="button" class="ti_bottom" value="初审" onclick="audit('firstaudit')"/>
								</c:if> --%>
						<input id="reset" type="button" class="ti_bottom" value="取消"
							onclick="window.parent.closeAudit()" />
					</div>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
	/*获取今日时间*/
	var createDate = new Date().format('yyyy-MM-dd hh:mm:ss');//获取今日时间
	$(function() {
		//绑定提交按钮click事件
		$("#submit").click(function() {
			if(!biz.validate("valid",$('#expenseAccountFormEdit')[0])){
				showWarn("数据验证失败",3000);
				return;
			}
			
			var checkFlag = $("#edit_checkFlag").val();
			if(checkFlag=="2"){showWarn("该报销单已审核，无需重复审核");}else{
				var key = $("#edit_id").val();
				var paramDatas = {key:key};
				$.ajax({
					   type: "post",
					   url: "<m:url value='/expenseAccount/auditExpenseAccount.do'/>",
					   data: paramDatas,
					   cache: false,
					   dataType:"json",
					   success : function(response) {
								showMessage(response.msg,"","",function(){
									window.parent.closeAudit();
						     		window.parent.doSearch();
								});
						}
				});
			}
		});
	
		/*编辑表单数据验证*/
		new biz.validate({
			id:"#expenseAccountFormEdit",
			rules:{
				"expenseWay" :{required : true},
				"expenseMoney" : {required : true,number:true},
				"content" :{required : true,},
				"enterPid" : {required : true},
				"enterDate" : {required : true},
				"docAttach" : {required : true,digits:true,max:100},
			}
		}); 
		/*日期格式化*/
		new biz.datepicker({
			id : "#enterDate",
			maxDate:'#F{$dp.$D(\'enterDate\',{d:0});}',
			dateFmt:'yyyy-MM-dd HH:mm:ss'
		});
	});
	
	//审核方法
	function audit(str){
		var urll="<m:url value='/expenseAccount/singaleaudit.do'/>";
/* 		if(str=='lastaudit'){
			urll="<m:url value='/expenseAccount/rauditExpenseAccount.do'/>";
		} */
/* 		if(!biz.validate("valid",$('#expenseAccountFormEdit')[0])){
			showWarn("数据验证失败",3000);
			return;
		} */
		
		
		var checkFlag = $("#edit_checkFlag").val();
		if (checkFlag == "2") {
				showWarn("该报销单已审核，无需重复审核");
			} else {
/* 				var key = $("#edit_id").val();
				var paramDatas = {
					key : key
				}; */
				var options = {
						type : "post",
						url : urll,
						//data : paramDatas,
						cache : false,
						dataType : "json",
						success : function(response) {
							showMessage(response.msg, "", "", function() {
								window.parent.closeAudit();
								window.parent.doSearch();
							});
						}
				};
				
				//验证图片
				var fileName = $("#file").val();
				if(fileName.length>1){  
					var extname = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length).toLowerCase();  
					var imgname = fileName.substring(fileName.lastIndexOf("\\")+1,fileName.length);  
					if(extname!= "jpeg"&&extname!= "jpg"&&extname!= "gif"&&extname!= "png"){  
						 showWarn("格式不正确,支持的图片格式为：JPEG、JPG、GIF、PNG！");  
				         return false;  
				        }  
					var file = $("#file").get(0).files; 
					var size = file[0].size;
					if(size>2097152){  
						  showWarn("所选择的图片太大，图片大小最多支持2M!"); 
				          return false;  
				     }  			
					// 创建一个FileReader对象
					var reader = new FileReader();
					// 绑定load事件
					reader.onload = function(e) {
						$("#fileData").val(e.target.result);
						$('#expenseAccountFormEdit').ajaxSubmit(options);// 将options传给ajaxForm
					}
					// 读取File对象的数据
					reader.readAsDataURL($("#file").get(0).files[0]);
			      }else{
			    	// 将options传给ajaxForm
			  		$('#expenseAccountFormEdit').ajaxSubmit(options);
			      }
			}
		}
		//图片上传预览功能
	   function setImagePreviews(avalue) {
	        var docObj = document.getElementById("file");
	        var dd = document.getElementById("preview");
	        dd.innerHTML = "";
	        var fileList = docObj.files;
	        for (var i = 0; i < fileList.length; i++) {            
	            dd.innerHTML += "<div align='center'> <img id='img" + i + "'  /> </div>";
	            var imgObjPreview = document.getElementById("img"+i); 
	            if (docObj.files && docObj.files[i]) {
	                //火狐下，直接设img属性
	                imgObjPreview.style.display = 'block';
	                //imgObjPreview.style.width = '250px';//设置图片显示尺寸
	                //imgObjPreview.style.height = '280px';
	                //imgObjPreview.src = docObj.files[0].getAsDataURL();
	                //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
	                imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
	            }
	            else {
	                //IE下，使用滤镜
	                docObj.select();
	                var imgSrc = document.selection.createRange().text;
	                alert(imgSrc)
	                var localImagId = document.getElementById("img" + i);
	                //必须设置初始大小
	                //localImagId.style.width = "250px";
	                //localImagId.style.height = "280px";
	                //图片异常的捕捉，防止用户修改后缀来伪造图片
	                try {
	                    localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
	                    localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
	                }
	                catch (e) {
	                    alert("您上传的图片格式不正确，请重新选择!");
	                    return false;
	                }
	                imgObjPreview.style.display = 'none';
	                document.selection.empty();
	            }
	        }  
	        return true;
	    }

	</script>
</body>
</html>
