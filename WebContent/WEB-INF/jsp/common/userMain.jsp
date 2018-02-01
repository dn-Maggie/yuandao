<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>后台管理</title>
<!-- basic styles -->
<link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/static/css/font-awesome.min.css" />
<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

<!-- page specific plugin styles -->

<!-- fonts -->

<!-- ace styles -->

<link rel="stylesheet" href="${ctx}/static/css/ace.min.css" />
<link rel="stylesheet" href="${ctx}/static/css/ace-rtl.min.css" />
<link rel="stylesheet" href="${ctx}/static/css/ace-skins.min.css" />

<!--[if lte IE 8]>
		  <link rel="stylesheet" href="${ctx}/static/css/ace-ie.min.css" />
		<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->

<script src="${ctx}/static/js/ace-extra.min.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
		<script src="${ctx}/static/js/html5shiv.js"></script>
		<script src="${ctx}/static/js/respond.min.js"></script>
		<![endif]-->
<style type="text/css">
.mask-wrap {
	position: absolute;
	width: 100%;
	height: 100%;
	filter: alpha(opacity = 30);
	background-color: #FFFFFF;
	text-align: center;
	display: none;
	vertical-align: middle;
}

.mask-wrap-msg {
	font-size: 13px;
	position: absolute;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	width: 50%;
	height: 30%;
	margin: auto;
	color: rgb(98,149,233);
}

.mask-wrap-msg-img {
	width: 100px;
	height: 100px;
	vertical-align: middle;
}
</style>
</head>
<body>
	<!-- 页面顶部¨ -->
	<%@ include file="top.jsp"%>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {}
		</script>
		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span></a>
			<%@ include file="left.jsp"%>
			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed');
						} catch (e) {
						}
					</script>
					<ul class="breadcrumb">
						<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
						<li class="active">欢迎页</li>
					</ul>
					<!-- .breadcrumb -->
					<!-- #nav-search -->
				</div>
				<div class="" id="contentright">
					<iframe
						style="*overflow-x: hidden; *table-layout: fixed; *word-wrap: break-word; word-break: break-all;"
						id="iframepage" name="iframepage" frameBorder=0 width="100%"
						height="100%" src="userHomePage.do"> </iframe>
				</div>
				<!-- /.page-content -->
			</div>
			<!-- /.main-content -->
		</div>
		<!-- /.main-container-inner -->
	</div>
	<!-- basic scripts -->



	<!--[if !IE]> -->

	<script type="text/javascript">
			window.jQuery || document.write("<script src='${ctx}/static/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

	<!-- <![endif]-->

	<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='${ctx}/static/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

	<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${ctx}/static/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
	<script src="${ctx}/static/js/bootstrap.min.js"></script>
	<script src="${ctx}/static/js/typeahead-bs2.min.js"></script>

	<!-- page specific plugin scripts -->

	<!-- ace scripts -->

	<script src="${ctx}/static/js/ace-elements.min.js"></script>
	<script src="${ctx}/static/js/ace.min.js"></script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
	function loadingMask(wrap) {
		return;
		// 默认遮罩全局
		if (wrap) {// 如果有指定某div进行遮罩
			wrap = $(wrap);
		} else {
			wrap = $(document.body);
		}
		if (wrap.find("div.mask-wrap").length > 0) {
			wrap.find("div.mask-wrap").show();
		} else {
			var winHg = wrap.get(0).offsetHeight;
			var winWd = wrap.get(0).offsetWidth;
			var _top = wrap.position().top;
			var _left = wrap.position().left;
			wrap.prepend($("<div class=\"mask-wrap\"></div>").css({
						display : "block",
						left : _left,
						top : _top,
						width : winWd,
						zIndex : 100000,
						height : winHg
					}).append($("<div class=\"mask-wrap-msg\"></div>")
					.html('<img class="mask-wrap-msg-img" src="${ctx}/styles/images/jiazai.gif" /><span>请稍候</span>')));
		}
	}

	function loadedMask(wrap) {
		if (wrap) {//remove
			$(wrap).find("div.mask-wrap").hide();
		} else {
			$(document.body).find("div.mask-wrap").hide();
		}
	}
	
		var fmid = "fhindex";
		var mid = "fhindex";
		function setIframePage(urlPath,targetUrl,id,fid){
			
			if (id != mid) {
				$("#" + mid).removeClass();
				mid = id;
			}
			if (fid != fmid) {
				$("#" + fmid).removeClass();
				fmid = fid;
			}
			//$("#" + fid).attr("class", "active open");
			$("#" + id).attr("class", "active");
	    if(targetUrl.indexOf('http:')>-1){
	    	targetUrl=targetUrl.substr(4);
	    }
	    if(fid=="z2c9285944061be01014061be01a70000"){
	    	loadingMask($('#contentright'));
	    }
	    document.getElementById("iframepage").src = targetUrl; 
	
		//setUrlPath(urlPath);
	        
		// $("#MainTd").removeClass('animated flipInX');
	}
		
		function pageHeight() {
			if ($.support.msie) {
				return document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight
						: document.body.clientHeight;
			} else {
				return self.innerHeight;
			}
		}
		function cmainFrame() {
			$('#contentright').height(pageHeight() - 92);
			var hmain = document.getElementById("iframepage");
			hmain.style.width = '100%';
			hmain.style.height = (pageHeight() - 92) + 'px';
		}
		//保存缩放菜单状态
		function menusf() {
			if (document.getElementsByName('menusf')[0].checked) {
				$.cookie('menusf', '', {
					expires : -1
				});
				$("#sidebar").attr("class", "menu-min");
			} else {
				$.cookie('menusf', 'ok');
				$("#sidebar").attr("class", "");
			}
		}
		$(function() {
			//if (typeof ($.cookie('menusf')) == "undefined") {
				//$("#menusf").attr("checked", false);
				//$("#sidebar").attr("class", "menu-min");
				
			//} else {
				//$("#sidebar").attr("class", "");
			//}
			cmainFrame();
			window.onresize = function() {
				cmainFrame();
			}
		});
	</script>

</body>
</html>

