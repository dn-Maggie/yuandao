<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/styles/extends/css/expenseAccount.css" />
</head>
<body>
	<div id="editDialog">
		<form id="leaveApplyFormEdit">
			<div class="wrap">
				<input type="hidden" id="edit_id" name="id" type="text"
					value="${leaveApply.id}" /> <input name="checkFlag" type="hidden"
					value="1" /> <input name="isCost" type="hidden" value="1" />
				<div class="top_head">
					<h2 class="top_name">请 假 条</h2>
					<div class="time_bg"
						style="top: 40px; left: 0px; height: 25px; line-height: 25px; position: absolute; width: 200px">
						<span><span class="required">*</span>请假类型：</span> <select
							name="leaveType" id="edit_leaveType" class="input_select"
							style="width: 100px">
							<option value="1"
								<c:if test="${leaveApply.leaveType=='1'}">selected</c:if>>事假</option>
							<option value="2"
								<c:if test="${leaveApply.leaveType=='2'}">selected</c:if>>公假</option>
							<option value="3"
								<c:if test="${leaveApply.leaveType=='3'}">selected</c:if>>病假</option>
							<option value="4"
								<c:if test="${leaveApply.leaveType=='4'}">selected</c:if>>婚假</option>
						</select>
					</div>
					<div class="time_bg"
						style="top: 40px; right: 0px; height: 25px; line-height: 25px; position: absolute; width: 200px">
						<span>申请日期：</span> <input id="edit_createDate" name="createDate"
							value="${leaveApply.createDate}"
							style="height: 25px; width: 100px" class="input_select" readonly />
						<i class="search_time_ico1" style="top: 6px;"></i>
					</div>
				</div>
				<div class="center_body">
					<table class="table">
						<tr>
							<td class="inputLabelTd"><span class="required">*</span>所属部门：</td>
							<td class="inputTd"><input id="edit_deptName"
								name="deptName" type="text" class="text" value="${user.dept}"
								readonly /></td>
							<td class="inputLabelTd"><span class="required">*</span>申请人：</td>
							<td class="inputTd"><input id="edit_enterName"
								name="enterName" type="text" class="text"
								value="${user.nickName}" readonly /> <input id="edit_empId"
								name="empId" type="hidden" class="text" value="${user.id}"
								readonly /></td>
						</tr>

						<tr class="trDetails" style="height: 140px;">
							<td class="inputLabelTd"><span class="required">*</span>请假事由：</td>
							<td class="inputTd" colspan="3"><textarea class="text"
									name="content" id="edit_content">${leaveApply.content}</textarea>
							</td>
						</tr>
						<tr class="hiddenRow" style="display:none">
							<td class="inputLabelTd"><span class="required">*</span>病历证明：</td>
							<td class="inputTd" colspan="3">
								<input id="fileData" name="fileUrl" type="hidden" value="${leaveApply.fileUrl}">
								<input id="file" type="file" class="text" style="height: 20px; line-height: 20px;" onchange="javascript:setImagePreviews();" /></td>
						</tr>
						<c:if test="${not empty  leaveApply.fileUrl}">
						<tr class="hiddenRow" style="display:none">
							<td class="inputTd" colspan="4">
								<div id="preview" style="width: 100%; margin-top: 5px; margin-bottom: 5px; text-align: center;">
									<img alt="图片证明" style="" src="${leaveApply.fileUrl}">
								</div>
							</td>
						</tr>						
						</c:if>
						<tr>
							<td class="inputLabelTd"><span class="required">*</span>自：</td>
							<td class="inputTd">
								<div class="time_bg" style="width: 98%;">
									<input type="text" class="search_time150 valid text"
										name="startDate" id="edit_startDate" style="width: 88%;"
										onblur="countLeaveDays()" value="${leaveApply.startDate}" />
									<i class="search_time_ico1"></i>
								</div>
							</td>
							<td class="inputLabelTd"><span class="required">*</span>至：</td>
							<td class="inputTd">
								<div class="time_bg" style="width: 98%;">
									<input type="text" class="search_time150 valid text"
										name="endDate" id="edit_endDate" style="width: 88%;"
										onblur="countLeaveDays()" value="${leaveApply.endDate}" /> <i
										class="search_time_ico1"></i>
								</div>
							</td>
						</tr>
						<tr>
							<td class="inputLabelTd"><span class="required">*</span>请假天数：</td>
							<td class="inputTd"><input id="edit_leaveDate"
								name="leaveDate" type="text" class="text"
								value="${leaveApply.leaveDate}" readonly /></td>
							<td class="inputLabelTd"><span class="required">*</span>直接负责人：
							</td>
							<td class="inputTd"><input id="edit_straightLeaderName"
								name="straightLeaderName" type="text" class="text"
								value="${leaveApply.straightLeaderName}" list="employeeList"
								onchange="getEmpIDByName(this,this.value);" /> <datalist
									id="employeeList">
									<c:forEach var="emp" items="${emp}">
										<option did="${emp.id}" value="${emp.nickName}"
											label="${emp.name}"></option>
									</c:forEach>
								</datalist> <input id="edit_straightLeader" name="straightLeader"
								type="hidden" value="${leaveApply.straightLeader}" /></td>
						</tr>
					</table>

					<div class="inputTd" style="display: block; text-align: center;">
						<input id="submit" type="button" class="ti_bottom" value="更新提交" />
					</div>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
	$(function() {
		if($("#edit_leaveType").val()=="3"){
			$(".hiddenRow").css("display","table-row");
		}
		$("#edit_leaveType").on("change",function(e){
			if($(this).val()=="3"){
				$(".hiddenRow").css("display","table-row");
			}
			else{$(".hiddenRow").css("display","none");}
		})
		
		//绑定提交按钮click事件
		$("#submit").click(function() {
			if(!biz.validate("valid",$('#leaveApplyFormEdit')[0])){
				showWarn("数据验证失败",3000);
				return;
			}
			var options = {
				url : "<m:url value='/leaveApply/updateLeaveApply.do'/>",
				type : "post",
					dataType:"json",
					success : function(d) {
						if(d.status){
							showMessage(d.message,"","",function(){
								window.parent.closeEdit();
					     		window.parent.doSearch();
							});
						}else{
							showMessage(d.message);
						}
					}
			};
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
					$('#leaveApplyFormEdit').ajaxSubmit(options);
				}
				// 读取File对象的数据
				reader.readAsDataURL($("#file").get(0).files[0]);
		      }else{
		    	// 将options传给ajaxForm
		    	  $('#leaveApplyFormEdit').ajaxSubmit(options);
		      }
		});
		
		/*申请日期格式化*/
		new biz.datepicker({
			id : "#edit_createDate",
			minDate:'%y-%M-%d',
			dateFmt:'yyyy-MM-dd HH:mm:ss'
		});
	
		/*请假起始日期格式化*/
		new biz.datepicker({
			id : "#edit_startDate",
			minDate:'%y-%M-{%d+1}',
			dateFmt:'yyyy-MM-dd HH:mm:ss'
		});
		/*请假截止日期格式化*/
		new biz.datepicker({
			id : "#edit_endDate",
			minDate:'#F{$dp.$D(\'edit_startDate\',{d:0});}',
			dateFmt:'yyyy-MM-dd HH:mm:ss'
		});
		
		/*编辑表单数据验证*/
		new biz.validate({
			id:"#leaveApplyFormEdit",
			rules:{
				"deptName" :{required : true},
				"content" :{required : true},
				"startDate" :{required : true},
				"endDate" :{required : true},
			}
		}); 
	});
	
	//图片上传预览功能
	function setImagePreviews(avalue) {
		debugger
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
	             localImagId.style.width = "250px";
	             localImagId.style.height = "280px";
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
	
	function countLeaveDays(){
		var start = $("#edit_startDate").val();
		var startDate = start.substring(0,start.indexOf(" "));
		var startTime = start.substring(start.indexOf(" "));
		var end = $("#edit_endDate").val();
		var endDate = end.substring(0,end.indexOf(" "));
		var endTime = end.substring(end.indexOf(" "));
		if(startDate.length>0&&endDate.length>0){
			var sArr = startDate.split("-");
			var eArr = endDate.split("-");
			var stArr = startTime.split(":");
			var etArr = endTime.split(":");
			var sRDate = new Date(sArr[0], sArr[1], sArr[2],stArr[0], stArr[1], stArr[2]);
			var eRDate = new Date(eArr[0], eArr[1], eArr[2],etArr[0], etArr[1], etArr[2]);
			var leaveDate = (eRDate-sRDate)/(24*60*60*1000);
			$("#edit_leaveDate").val(leaveDate.toFixed(2));
			leaveDate>=3?$("#edit_headLeader").val("need"):"";
		}
	}
	/*自动搜寻转化人 自动完成(Autocomplete) 根据用户输入值进行搜索和过滤,让用户快速找到并从预设值列表中选择*/
	function getEmpIDByName(obj,value) {
		var did = $("#employeeList").find("option[value="+value+"]").attr('did');
		$(obj).parents('td').find("#edit_straightLeader").val(did);
	}
	</script>
</body>
</html>