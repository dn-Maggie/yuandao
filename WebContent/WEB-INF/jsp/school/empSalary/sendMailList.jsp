<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../../common/ace.jsp"%>
</head>
<body
	style="background: #fff; height: 100%; position: absolute; width: 100%">
	<div class="page-header">
		<h1 style="text-align: right; cursor: default;">
			<small> <i class="icon-double-angle-right"></i>
				请确保员工资料企业邮箱已填写完整
			</small> <input type="button" class="btn btn-success" id="sendBtn"
				value="全部发送"> <input type="button" class="btn btn-default"
				id="cancelBtn" value="全部暂停">
		</h1>
	</div>
	<div class="col-xs-12"
		style="height: 70%; overflow-y: scroll; overflow-x: hidden;">
		<ul class="ace-thumbnails" style="width: 100%;">
			<c:forEach var="empSalary" items="${empSalaryList}">
				<li class="empSalaryItem" data-id="${empSalary.id}"
					data-status="${empSalary.sendFlag}"
					style="width: 20%; box-sizing: border-box; margin: 0 0 20px 0; border: 0; float: left;">
					<a href="#" data-rel="colorbox" style="text-decoration: none">
						<img alt="20*20" style="width: 50px; height: 50px"
						src="<%=request.getContextPath() %>/styles/images/logo_face.png" />
						${empSalary.empNo} ${empSalary.empName}
				</a>
					<div class="tools tools-bottom">
						<a href="#"><i class="icon-play green"></i></a> <a href="#"><i
							class="icon-pause"></i></a> <a href="#"><i
							class="icon-remove red"></i></a>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div class="col-xs-12" id="errorMsg"
		style="border-top: 1px dotted #e2e2e2; height: 20%; overflow-y: scroll; overflow-x: hidden;">
	</div>

	<script type="text/javascript">
	var currentAjax = null;  
	 $(document)
		.on('click', '#sendBtn', function() {
			//等待样式
			$("#sendBtn").prop('disabled', true).css({'cursor':'not-allowed'});
				$('.empSalaryItem').each(function(){
					var key = $(this).data('id') ||"";
					$.ajax({
				   		url : "<%=basePath%>/empSalary/sendEmpSalary.do?key="+key,
				   			cache : false,
				   			async : true,
				   			dataType:"json",
				   			success : function(data) {
				   				if(data.sendFlag!=="2"){
				   			 		$("#errorMsg").append("<h6 class='red'>"+data.empNo + data.empNickName +"("+data.empName+")发送失败       失败原因:"+data.note+"</h6>");
				   			 	}else{
				   			 		removeItem(data.id,data);
				   			 	}
				   			},
				   			error:function(data){
				   				//错误信息打印
				   				$("#errorMsg").append("<h6 class='red'>"+data.empNo + data.empNickName +"("+data.empName+")发送失败       失败原因:"+data.note+"</h6>");
				   			}
				   		}); 
				});$("#sendBtn").prop('disabled', false).css({'cursor':'pointer'});
		})
		.on('click', '#cancelBtn', function() {
			/* window.parent.closeAdd(); */
		})
		.on('click', '.icon-play', function() {
			var key = $(this).parents('.empSalaryItem').attr('data-id') ||"";
			currentAjax = $.ajax({
		   		url : "<%=basePath%>/empSalary/sendEmpSalary.do?key="+key,
		   			cache : false,
		   			async : true,
		   			dataType:"json",
		   			success : function(data) {
		   				if(data.sendFlag!=="2"){
		   			 		$("#errorMsg").append("<h6 class='red'>"+data.empNo + data.empNickName +"("+data.empName+")发送失败       失败原因:"+data.note+"</h6>");
		   			 	}else{
		   			 		removeItem(data.id,data);
		   			 	}
		   			},
		   			error:function(data){
		   				//错误信息打印
		   				$("#errorMsg").append("<h6 class='red'>"+data.empNo + data.empNickName +"("+data.empName+")发送失败       失败原因:"+data.note+"</h6>");
		   			}
		   		}); 
		})
		.on('click', '.icon-pause', function() {
			//如当前AJAX请求未完成，则中止请求  
		    if(currentAjax) {currentAjax.abort();}  
		})
		.on('click', '.icon-remove', function() {
			var dataId = $(this).parents('.empSalaryItem').attr('data-id');
			removeItem(dataId);
		})
		
		function removeItem(dataId,data){
		 	//判断状态是否为1 是则不删除
			$('.ace-thumbnails').find(".empSalaryItem[data-id='"+dataId+"']").remove();
			//信息打印
		 	if(typeof(data)!="undefined"){
		 		$("#errorMsg").append("<h6 class='green'>"+data.empNo + data.empNickName +"("+data.empName+")发送成功</h6>");
		 	}
		}
	</script>
</body>
</html>