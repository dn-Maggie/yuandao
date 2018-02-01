<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html  >
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<base href="<%=basePath%>">
<%@ include file="../../common/header.jsp"%>
</head>
<body>
	<form id="sessionInfoForm" method="post">
		<div class="ui-table ui-widget ui-corner-all ui-border">
			<table class="table">
				<tr>
					<td class="inputLabelTd"><m:message
							code="sessionInfo.queryForm.lostTime" />:</td>
					<td class="inputTd"><input id="lostTime" name="lostTime"
						type="text" class="text" value="${sessionValue}" /></td>
				</tr>
				<tr>
					<td class="inputLabelTd"><m:message
							code="sessionInfo.queryForm.memo" />:</td>
					<td class="inputTd">
						<div>session失效时间的单位为分钟，最大不超过120分钟。如果不设置，则采用系统默认的session失效时间</div>
					</td>
				</tr>
				<tr>
					<td class="inputTd" colspan="2" style="text-align: center;"><input
						id="submit" type="button" class="ti_bottom" onclick="update();"
						value="<m:message code='button.save' />" /> <input id="return"
						type="button" class="ti_bottom" onclick="closeView();"
						value="<m:message code='button.close' />" /></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
<script type="text/javascript">
	function update(){
		var sessionValue = $("#lostTime").val();
		//验证是否为正整数
		var reg = /^[0-9]*[1-9][0-9]*$/;
		if(!reg.test(sessionValue)){
			showInfo("您输入的不是正整数，请重新输入!",3000);
			return;
		}
		if(sessionValue > 120){
			showInfo("您输入的数字超过系统限定的最大数字，请重新输入!",3000);
			return;
		}
		
		$.ajax({
			type: "post",
			url: "<c:url value='/settingInfo/updateSessionTime.do'/>",
			data:{sessionValue:sessionValue},
			async: false,
			dataType:"json",
			success: function(data){
				if(data.msg == "成功"){
					new biz.alert({type:"alert",message:"设置成功!",title:I18N.promp,callback:function(){
						window.parent.closeDialog();
					}}) ;
				}
			}
		});
	}
	
	function closeView(){
		window.parent.closeDialog();
	}
	
</script>




