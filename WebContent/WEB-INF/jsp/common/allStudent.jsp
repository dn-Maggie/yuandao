<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head lang="en">

<script type="text/javascript">
  baseUrl="<%=request.getContextPath() %>";
</script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/excelExport.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/styles/index.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/lib/biz.jquery.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/lib/biz-html5.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/lib/biz-ie.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/jquery.cookie.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/lib/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/i18n/i18n_zh.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/lib/biz.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/jquery.nicescroll.js"></script>
<script title="js模板控件" type="text/javascript"
	src="<%=request.getContextPath() %>/js/doT.min.js"></script>
<!-- 自定义js -->
<script title="表格列耐热格式" type="text/javascript"
	src="<%=request.getContextPath() %>/js/GridColModelForMatter.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/bizLib/common.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/bizLib/jquery.form.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/bizLib/jquery.contextmenu.r2.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/tiledCombobox.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/bizLib/placeholder.js"></script>

<script
	src="<%=request.getContextPath() %>/js/gameco/jquery.collapse.js"></script>
<script src="<%=request.getContextPath() %>/modulejs/common.js"></script>
<script
	src="<%=request.getContextPath() %>/js/common-util/opera-util.js"></script>
<script src="<%=request.getContextPath() %>/js/common-util/grid-util.js"></script>

<!-- 新添加的js -->
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/statistic/helium.js"></script>
<script
	src="<%=request.getContextPath() %>/js/common-util/opera-util.js"></script>
<meta charset="UTF-8">
<title>后台管理</title>
<link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/static/css/font-awesome.min.css" />
<link rel="stylesheet" href="${ctx}/static/css/ace.min.css" />
<link rel="stylesheet" href="${ctx}/static/css/ace-rtl.min.css" />
<link rel="stylesheet" href="${ctx}/static/css/ace-skins.min.css" />
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
			} catch (e) {
			}
		</script>
		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span>
			</a>
			<%@ include file="Newleft.jsp"%>
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
						<li class="active">学生信息查询</li>
					</ul>
				</div>
				<div class="" id="contentright">
					<iframe
						style="*overflow-x: hidden; *table-layout: fixed; *word-wrap: break-word; word-break: break-all;"
						id="iframepage" name="iframepage" frameBorder=0 width="100%"
						height="100%" src="vipStudent/toListAVipStudent.do"> </iframe>
				</div>
				<!-- /.page-content -->
			</div>
			<!-- /.main-content -->
			<!-- 换肤 -->
			<!-- /#ace-settings-container -->
		</div>
		<!-- /.main-container-inner -->
	</div>
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
			$("#" + id).attr("class", "active");
	    if(targetUrl.indexOf('http:')>-1){
	    	targetUrl=targetUrl.substr(4);
	    }
	    if(fid=="z2c9285944061be01014061be01a70000"){
	    	loadingMask($('#contentright'));
	    }
	    document.getElementById("iframepage").src = targetUrl; 
	
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
		//修改密码弹出框
		var edit_password_iframe_dialog;
		$(document).ready(function(){
			cmainFrame();
			window.onresize = function() {
				cmainFrame();
			}
			//修改密码点击事件
			//获取父框架修改密码和用户资料按钮
			var _iframe = window.parent;
			var _setPwdBtn =_iframe.document.getElementById('setPwdBtn');
			_setPwdBtn.addEventListener("click", function(){
				var url= baseUrl +'/userInfo/toEditPassWord.do';
				edit_password_iframe_dialog = new biz.dialog({
					id:$('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
					modal: true,
					width: 400,
					height: 240,
					title: "修改密码"
				});
				edit_password_iframe_dialog.open();
		})
			
		});
	</script>

</body>
</html>

