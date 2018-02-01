<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String wspath = "ws://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<title>新一代LKJ数据换装终端管理软件</title>
<!-- <meta http-equiv="X-UA-Compatible" content="IE=100"/>设置浏览器的浏览模式:IE=100文本模式是 IE8标准 -->
<base href="<%=basePath%>" />

<%@ include file="header.jsp"%>
<script type="text/javascript">

	
	//设置2级菜单，脱离出来方便控制（menu菜单对象）
	function setResItems(menu,type){
		$(menu.resItems).each(function(i,resItem){
			var resId=resItem.id;
			var pId = resItem.muuid;
			var rname = resItem.resourceName;
			var resurl = resItem.resurl;
			var resourcecode=resItem.resourcecode;
			var urlPath = menu.moduleName +" -> "+ rname;
			var  li_2='<li><a href="javascript:;" onclick="setIframePage(\''+urlPath+'\',\'<%=path%>'+resurl+'\');" target="iframepage"><span class="icon_second-level sicon_'+resourcecode+'"></span><span>'+rname+'</span></a></li>';
			//如果是右上菜单
			if(type){
				 $("#menu_"+pId+" ul").append(li_2);
			}
			else{
			if(i==0){
				$("<ul class='one' id='nav_"+pId+"'><div class='arrow-left'></div></ul>").appendTo($("#menu_"+pId));
			}
			var parentUI = $("#nav_"+pId);
			$(li_2).appendTo(parentUI);}
		});
		}
	
	$(function() {
		//从后台取菜单数据，进行菜单展示 $.parseJSON('${menus}');
		var ul = $("#menuNav");
		var menus = [];
		setIframePage("首页","<%=path%>/homePage/toHome.do");
		$(menus).each(function(i,menu){
			var moduleId = menu.id;
			var moduleName = menu.moduleName;
			var navurl = menu.navurl;
			var moduleIcon = menu.resourceId;
			var basePath=baseUrl;
			var li_1;
			//如果是系统管理和基础数据管理，独立到右上角
			if(menu.moduleCode=='baseMgt'||menu.moduleCode=='sysMgt'){
				li_1='<li id="menu_'+moduleId+'" class="top_menu_li" ><a href="#"><i class="'+moduleIcon+'"></i><span>'+moduleName+'</span></a> <ul class="topMenu"  style="display:block"><div class="arrow-top"></div></li>';
				$('#mainleve2').after(li_1);
				setResItems(menu,true);
			}else{
			if(navurl !=''){
				if(moduleIcon !=''){
					li_1="<li id='menu_"+moduleId+"'><a href='javascript:;' onclick='setIframePage(\""+moduleName+"\",\"<%=path%>"+navurl+"\",1);' target='iframepage'><i class='iconBox'><img src=\"<m:url value='/images/bigicon/"+moduleIcon+".png'/>\" /></i>"+moduleName+"</a></li>";
				}else{
					li_1="<li id='menu_"+moduleId+"'><a href='javascript:;' onclick='setIframePage(\""+moduleName+"\",\"<%=path%>"+navurl+"\",1);' target='iframepage'><i class='iconBox'><img src='"+basePath+"images/icon_default.png'/></i>"+moduleName+"</a></li>";
				}

			}else{
				li_1="<li id='menu_"+moduleId+"'><a href='javascript:;'><i class='iconBox'>";
				if(moduleIcon !=''){
					li_1 +="<img src=\"<m:url value='/images/bigicon/"+moduleIcon+".png'/>\" />";
				}else{
					li_1 +="<img src='"+basePath+"images/icon_default.png'/>";
				}
				li_1 += "</i>"+moduleName+"</a></li>";
				//li_1="<li id='menu_"+moduleId+"'><a href='javascript:;'><i class='iconBox'><img src='"+basePath+"images/icon_default.png'/></i>"+moduleName+"</a></li>";
			}
			$(li_1).appendTo(ul);
			setResItems(menu);
			}
		});
		
		//setRightHeight();
		
		$('ul.one,.topMenu').hide();
		
		
		//隐藏和显示子菜单
		$('.nav li').hover(function(){
			var openMenu= $(this).children('ul.one');
			$(openMenu).show();
		},function(){
			var openMenu= $(this).children('ul.one');
			$(openMenu).hide();
		});
		//隐藏和显示子菜单
		$('.top_menu_li').hover(function(){
			var openMenu= $(this).find('ul');
			$(openMenu).show();
		},function(){
			var openMenu= $(this).find('ul');
			$(openMenu).hide();
		});
	
	$('.title').click(function(){
		var $img = $(this).find('i.right_icon');
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		$(".title i.icon_up").removeClass('icon_up').addClass('icon_down');
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
			$img.removeClass('icon_up').addClass('icon_down');
		}else{
			$(this).next('ul').slideDown();
			$img.removeClass('icon_down').addClass('icon_up');
		}
	});
		
	var doc_height = pageHeight();
	$("#doc").height(doc_height - 96);
	$("#iframepage").height(doc_height - 96);

	$(window).resize(function() {
		$("#doc").height(pageHeight() - 96);
		$("#iframepage").height(pageHeight() - 96);

	});
	  /**iFrame加载完毕
	  $("#mainFrame").bind({
	        "readystatechange": function () {
	            if (this.readyState == 'complete') {
	                $("#iframeFather").hide();
	            }
	        }
	    });**/
	});
	

	function pageHeight() {
		if ($.browser.msie) {
			return document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight
					: document.body.clientHeight;
		} else {
			return self.innerHeight;
		}
	}
	
	function setRightWidth(){
		var winWidth = $(window).width();
		var width = winWidth;
		if($(".nav").css("display") != "none"){
			width = width - 78;
		}
		$("#rightSide").css("width",width +"px");
	}
	
	function setRightHeight(){
		var winHeight = $(window).height();
		var height = winHeight;
		if($(".nav").css("display") != "none"){
			height = height - 92;
		}
		$(".nav").css("height",height +"px");
	}
	
	function setIframePage(urlPath,targetUrl,level){
	    var iframe = '<div class="main-contentright"  id="MainTd" ><iframe style=" *overflow-x:hidden;*table-layout:fixed;*word-wrap:break-word;word-break:break-all;"  id="iframepage" name="iframepage" frameBorder=0 width="100%" height="100%" src="" ></iframe></div>';
	    $("#MainTd").remove();
	    $("#contentright").append(iframe);
	    if(targetUrl.indexOf('http:')>-1){
	    	targetUrl=targetUrl.substr(4);
	    }
	    document.getElementById("iframepage").src = targetUrl; 
	    $("#MainTd").addClass('animated zoomIn');
		setUrlPath(urlPath);
	        
		// $("#MainTd").removeClass('animated flipInX');
	}
	//新增的弹出框
	var edit_password_iframe_dialog;
	function editPassword(){
		var url="<m:url value='/userInfo/toEditPassWord.do'/>";
		edit_password_iframe_dialog = new biz.dialog({
			id:$('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: 400,
			height: 240,
			title: "<m:message code='userInfo.passWord.edit' />"
		});
		edit_password_iframe_dialog.open();
	}
	function logOut(){
		window.location.href = "<%=basePath%>logout.do";
	}

	function reclass(obj){
		$(".top_right ul li a.selected").removeClass("selected");
		$(obj).addClass("selected");
	}
	function goIndex(){
		window.location.href="<%=basePath%>index.do";
	}
	$(function() {
		//点击图标控制菜单的显示隐藏
		$("#icon_fx").click(
				function() {
					var menuNav = window.parent.$(".nav");
					menuNav.animate({
						//height:'toggle', 
						width : 'toggle'
					});
					if (this.className == 'icon_menuLeft') {
						this.className = 'icon_menuRight';
					} else {
						this.className = 'icon_menuLeft';
					}

					var menuWidth = menuNav.css("width");
					menuWidth = menuWidth.substr(0, menuWidth.length - 2);
					var mainTd = window.parent.document
							.getElementById("contentright");
					if (Number(menuWidth) > 10) {
						mainTd.style["margin"] = 0 + " " + 0 + " " + 0 + " "
								+ 1 + "px";
					} else {
						mainTd.style["margin"] = 0 + " " + 0 + " " + 0 + " "
								+ 76 + "px";
					}
				});

		setRightHeight();

	});
</script>
<style>
.navbar {
	background: rgb(129, 131, 139) none repeat scroll 0 0;
	border: 0 none;
	border-radius: 0;
	box-shadow: none;
	margin: 0;
	min-height: 45px;
	padding-left: 0;
	padding-right: 0;
	position: relative;
}

.navbar-header {
	content: " ";
	display: table;
	float: left;
}

.navbar .navbar-container {
	padding-left: 10px;
	padding-right: 20px;
}

.navbar .navbar-brand {
	color: #fff;
	font-size: 24px;
	padding-bottom: 10px;
	padding-top: 10px;
	line-height: 20px;
	font-family: "黑体", "Arial";
	text-shadow: none;
}

small {
	font-size: 85%;
}

.h_left {
	float: left;
}
</style>
</head>

<body style="overflow: hidden;">
	<!--A头部begin-->
	<div class="navbar">
		<div class="navbar-container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"> <!-- <small> <img src="/cmstzyy/styles/images/logo.png" alt="产贸送投资运营管理系统">
							投资运营管理系统 </small> -->
				</a>
			</div>
		</div>
	</div>
	<!--B头部begin-->
	<!--header end-->
	<div class="operatbox">
		<div class="menu_fx">
			<!--  <img src="images/menu_fx.png"  id="icon_fx"/>-->
			<i class="icon_menuLeft" id="icon_fx"></i>
			<!--向左 -->
		</div>
		<div class="placebox">
			<i class="place_icon"></i> <span>当前位置:</span> <span
				class="place_current" id="urlPath">首页</span>

		</div>
	</div>
	<div class="content clearfix" id="doc">
		<!--左侧Begin-->
		<div id="TreeTD">
			<!--菜单Begin-->
			<ul class="nav" id="menuNav">
			</ul>
			<!--菜单over-->
		</div>
		<!--左侧over-->

		<!--右内容begin-->
		<div class="main-area" id="contentright"
			style="margin: 0px 0px 0px 78px; overflow-x: hidden !important;">
		</div>
	</div>
</body>
</html>

