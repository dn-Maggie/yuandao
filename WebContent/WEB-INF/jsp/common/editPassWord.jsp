<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="header.jsp"%>

<script type="text/javascript">
$(function() {
	//绑定提交按钮click事件
	$("#submit").click(function() {
		if(!biz.validate("valid",$('#userInfoFormEdit')[0])){
			showWarn("<m:message code='validation.object'/>",3000);
			return;
		}
		var pattern1 =/[`~!@#$%^&*_+<>{}\/'[\]]/im;
		if(pattern1.test($("#input_password").val())){
			showWarn("密码不能含有除大小写字母，数字以外的字符！",5000);
			return;
		}
		var pattern2 = new RegExp("(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9]{8,12}");
		if(!pattern2.test($("#input_password").val())){
			showWarn("密码必须含有大写字母、小写字母、数字(8-12位)",5000);
			return;
		}
		var options = {
			url : "<m:url value='/userInfo/changePassWord.do'/>",
			type : "post",
			success : function(data) {
				var d = $ .parseJSON(data);
				if(d.rs=="false"){
					showWarn("旧密码错误！",3000);
				}else{
					showMessage("密码修改成功，请重新登陆","","",function(){
						window.top.location.href = "<%=basePath%>/logout.do";
					});
				}
			}
		};
		// 将options传给ajaxForm
		$('#userInfoFormEdit').ajaxSubmit(options);
		
		
		
	});
	/*  if(navigator.userAgent.toLowerCase().indexOf("chrome") != -1){
		var selectors = document.getElementsByTagName("input");
			for(var i=0;i<selectors.length;i++){
				if((selectors[i].type !== "submit") && (selectors[i].type !== "password")){
					var input = selectors[i];
					var inputName = selectors[i].name;
					var inputid = selectors[i].id;
					selectors[i].removeAttribute("aPassword");
					selectors[i].removeAttribute("eidt_Password");
					setTimeout(function(){
						input.setAttribute("aPassword",inputName);
						input.setAttribute("eidt_Password",inputid);
					},1)
				}
	
			}

		}  */
	/*编辑表单数据验证*/
	new biz.validate({
		id:"#userInfoFormEdit",
		rules:{
			"oldPassword":{
				required: true
			},
			"newPassWord":{
				required: true
			},
			"confirm_password":{
				required: true
				/* alnumline:true, */
			}
		}
	}); 
	new biz.validate({
		id:"#confirm_password",equalTo:"#input_password"
		}) ;
});
</script>
<form id="userInfoFormEdit">
	<!-- <hi:icssToken/> -->
	<div class="ui-table ui-widget ui-corner-all ui-border">
		<table class="table">
			<tr style="display: none;">
				<!-- 绕开谷歌浏览器对表单第一个password控件记住密码的规则 -->
				<td class="inputTd"><INPUT id=Password class=password value=""
					type=password name="PassWord"></td>
			</tr>
			<tr>
				<td class="inputLabelTd">旧密码:</td>
				<td class="inputTd"><input id=eidt_Password class=password
					value="" type="password" name="oldPassword" /></td>
			</tr>
			<tr>
				<td class="inputLabelTd">新密码:</td>
				<td class="inputTd"><input id=input_password class=password
					value="" type="password" name="newPassWord" /></td>
			</tr>
			<tr>
				<td class="inputLabelTd">新密码确认:</td>
				<td class="inputTd"><input id=confirm_password class=password
					value="" type="password" name="confirm_password" /></td>
			</tr>
			<tr>
			<tr>
				<td class="inputTd" colspan="2" style="text-align: center;"><input
					id="submit" type="button" class="ti_bottom"
					value="<m:message code='button.save' />" /></td>
			</tr>

		</table>
	</div>
</form>
