<%@page import="com.dongnao.workbench.common.util.Utils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
		if(window!=top){
			top.location.reload();
		}
	</script>
<base href="<%=basePath%>">
<%@ include file="header.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=path%>/styles/login.css" />
<title>猿说办公协同管理系统</title>
</head>

<body id="login-body" onselect="return false">
	<form id="loginForm" name="loginForm" method="post" action="">
		<div id="loginWindow">
			<table>
				<tr>
					<td class="logo_box" align="center"><img alt="" src="<%=path%>/styles/images/head_title.png"></td>
				</tr>
				<tr>
					<td>
						<div class="group">
							<span class="addon login_name fa fa-user fa-3x"></span> 
							<input id="loginName" name="loginName" onkeypress="xKeyEvent(event)" tabindex="1" accesskey="n" type="text" autocomplete="off" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="group">
							<span class="addon password fa fa-lock fa-3x"></span> <input id="userpw" name="userpw" onkeypress="xKeyEvent(event)" tabindex="2" accesskey="p" type="password" autocomplete="off" />
						</div>
					</td>
				</tr>
				<tr style="height: 30px">
					<td>
						<div class="remb">
							<span> <input id="remb" type="checkbox" name="remb" /> <label
								for="remb">记住用户名和密码</label> <label class="remb_label" for="remb"></label>
							</span>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="group check_code" style="float: left">
							<span class="addon">验</span> <input class="codeInput"
								id="codeInput" onkeypress="xKeyEvent(event)" /> <img
								src="<%=basePath%>validateCode" id="codeImg" alt="点击刷新验证码">
						</div>
					</td>
				</tr>
				<tr>
					<td>
							<button type="button" class="btn_reset" id="submit" onclick="loginAction('#loginForm')">登录</button>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
<script>
	var codeChecked = false;
	 $(function(){  
         $("#codeInput").on("input", checkCode); 
         $("#codeImg").on("click", refreshCaptcha);
     });  
	
	//验证验证码
	function checkCode(){  
         var strcode =$(this).val();
         if(strcode.length <=3 ){ //验证码一般大于三位  
             return;  
         }  
         codeChecked = false;
         $.ajax({
        	 url : "<%=basePath%>varifyCode.do?strcode="+strcode,
       		 cache : false,
      		 dataType : 'json',
  			 success : function(data, textStatus, jqXHR) {
  				if(data.status == 1){
	        		 codeChecked = true;
	        	 }
         	 }
         });
         if(event.keyCode==13){  
        	 loginAction('#loginForm');
         }  
      }  
	//重载验证码  
	 function refreshCaptcha() {  
		url = "<%=basePath%>validateCode?code=" + Math.random(),
        $('#codeImg').attr('src',url);   
    }   
	//登陆动作
	function loginAction(formId) {
		if(!codeChecked){showWarn("请输入正确的验证码！");codeChecked = false;refreshCaptcha();return;}  
		showInfo("登陆中，请稍后...");
		$("#popup_ok").css("visibility","hidden");
		//表单验证
		function formValidation() {
			var u = f['loginName'];
			var p = f['userpw'];
			if (u.value == null || u.value == "") {
				alert("请输入用户名");
				u.focus();
				return false;
			}
			if (p.value == null || p.value == "") {
				alert("请输入密码");
				p.focus();
				codeChecked = false;
				return false;
			}
			return true;
		}
		var uname = $('#loginName').val();
		var pawd = $('#userpw').val();
		var strcode =$("#codeInput").val();
		$.ajax({
			url : "<%=basePath%>appLogin.do?userName=" + uname + "&password=" + pawd+ "&strcode=" + strcode,
			cache : false,
			dataType : 'json',
			success : function(data, textStatus, jqXHR) {
				if (data.status == 1 && codeChecked == true) {
					window.location.href = 'index.do';
				} else {
					codeChecked = false;
					showMessage(data.message);
					refreshCaptcha(); 
				}
			}
		});
	}

	//响应回车按键
	function xKeyEvent(e) {
		var e = e || windows.event;
		switch (e.keyCode | e.which | e.charCode) {
		case 13: {
			//响应回车按键
			loginAction('#loginForm');
			}
		}
	}
</script>
<%-- <script type="text/javascript" src="<%=request.getContextPath() %>/js/statistic/helium.js" onload="helium.init()" async></script> --%>
</html>
