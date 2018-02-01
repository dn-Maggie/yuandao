<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/styles/extends/css/expenseAccount.css" />
<script type="text/javascript">
var createDate = new Date().format('yyyy-MM-dd hh:mm:ss');//获取今日时间
$(function() {
	$("#enterDate").val(createDate);
	//绑定提交按钮click事件
	$("#submit").click(function() {
		/* $("#submit").prop('disabled', true).css({'cursor':'not-allowed'}); */
		if(!biz.validate("valid",$('#expenseAccountFormEdit')[0])){
			showWarn("数据验证失败",3000);
			/* $("#submit").prop('disabled', false).css({'cursor':'pointer'}); */
			return;
		}
		var options = {
			url : "<m:url value='/expenseAccount/addExpenseAccount.do'/>",
			type : "post",
				dataType:"json",
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
		
		
	});

	/*编辑表单数据验证*/
	new biz.validate({
		id:"#expenseAccountFormEdit",
		rules:{
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

/*自动完成(Autocomplete) 根据用户输入值进行搜索和过滤,让用户快速找到并从预设值列表中选择*/
function getEmpIDByName(obj,value) {
	var did = $("#empList").find("option[value="+value+"]").attr('did');
	$(obj).parents('td').find("#edit_enterPid").val(did);
}
/*自动完成(Autocomplete) 根据用户输入值进行搜索和过滤,让用户快速找到并从预设值列表中选择*/
function getOrgIDByName(obj,value) {
	var did = $("#orgList").find("option[value="+value+"]").attr('did');
	$(obj).parents('td').find("#edit_deptNo").val(did);
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
							<option value="个人报销">个人报销</option>
							<option value="集体报销">集体报销</option>
						</select>
					</div>
					<div class="time_bg"
						style="top: 40px; right: 0px; height: 25px; line-height: 25px; position: absolute; width: 200px">
						<span>申请日期：</span> <input id="enterDate" type="text"
							class="search_time150" name="enterDate" mainid="enterDate"
							style="height: 25px;"> <i class="search_time_ico2"
							onclick="WdatePicker({el:'enterDate'})" style="top: 6px;"></i>
					</div>
				</div>
				<div class="center_body">
					<table class="table">
						<tr>
							<td class="inputLabelTd"><span class="required">*</span>所属部门：</td>
							<td class="inputTd"><input id="edit_deptNo" name="deptNo"
								type="hidden" /> <input id="edit_deptName" name="deptName"
								type="text" class="text" list="orgList" value="${employee.dept}"
								onchange="getOrgIDByName(this,this.value);" /> <datalist
									id="orgList">
									<c:forEach var="org" items="${org}">
										<option value="${org.orgName}" did="${org.id}"></option>
									</c:forEach>
								</datalist></td>
							<td class="inputLabelTd"><span class="required">*</span>报销申请人：</td>
							<td class="inputTd"><input id="edit_enterPid"
								name="enterPid" type="hidden" value="${employee.id}" /> <input
								id="edit_enterName" name="enterName" type="text" class="text"
								list="empList" value="${employee.name}"
								onchange="getEmpIDByName(this,this.value);" /> <datalist
									id="empList">
									<c:forEach var="tutor" items="${tutor}">
										<option value="${tutor.nickName}" did="${tutor.id}"></option>
									</c:forEach>
								</datalist></td>
						</tr>

						<tr class="trDetails" style="height: 140px;">
							<td class="inputLabelTd"><span class="required">*</span>报销项目：</td>
							<td class="inputTd" colspan="3"><textarea class="text"
									name="content" id="edit_content"></textarea></td>
						</tr>
						<tr>
							<td class="inputLabelTd"><span class="required">*</span>报销金额：</td>
							<td class="inputTd"><input id="edit_expenseMoney"
								name="expenseMoney" type="text" class="text"
								value="${expenseAccount.expenseMoney}" /></td>
							<td class="inputLabelTd"><span class="required">*</span>报销方式：</td>
							<td class="inputTd"><select class="input_select text"
								name="expenseWay" id="edit_expenseWay" mainid="expenseWay"
								style="width: 97%">
									<option value="支付现款">支付现款</option>
									<option value="银行转账">银行转账</option>
							</select></td>
						</tr>
						<tr>
							<td class="inputLabelTd" colspan="4" style='text-align: right'>
								<span class="required">*</span>附单据： <input id="edit_docAttach"
								name="docAttach" type="number" class="text"
								style="width: 70px; text-align: center;" />张 <input
								id="edit_checkFlag" name="checkFlag" type="hidden" value="1" />
								<input id="edit_assignFlag" name="assignFlag" type="hidden"
								value="1" />
							</td>
						</tr>
						<tr>
							<td class="inputLabelTd">图片证明：</td>
							<td class="inputTd" colspan="3"><input id="fileData"
								name="fileUrl" type="hidden"> <input id="file"
								type="file" class="text"
								style="height: 20px; line-height: 20px;"
								onchange="javascript:setImagePreviews();" /></td>

						</tr>
						<tr>
							<td class="inputTd" colspan="4">
								<div id="preview"
									style="width: 100%; margin-top: 5px; margin-bottom: 5px; text-align: center;">
									<span class="required">*</span>暂未上传图片
								</div>
							</td>

						</tr>
					</table>
					<!-- 			<div style="display:block; float: left; margin-top:5px;margin-left: 20px;"><span class="required">*</span>附单据 -->
					<!-- 				<input id="edit_docAttach" name="docAttach" type="text" class="text" style="width: 50px;text-align: center;"/>张 -->

					<!-- 			</div> -->
					<div class="inputTd"
						style="display: block; margin-top: 10px; text-align: center;">
						<input id="submit" type="button" class="ti_bottom" value="提交申请" />
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
