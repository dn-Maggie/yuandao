<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="m" uri="/WEB-INF/tld/spring.tld"%>
<div class="sidebar" id="sidebar">
	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'fixed')
		} catch (e) {
		}
	</script>
	
	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
		
		<c:choose>   
			<c:when test="${ViewPerformance}">
				<button class="btn btn-success">
					<i class="icon-signal"></i>
				</button>
				<button class="btn btn-info">
					<i class="icon-pencil"></i>
				</button>
				<button class="btn btn-warning">
					<i class="icon-group"></i>
				</button>
				<button class="btn btn-danger" id="btn-danger">
					<i class="icon-cogs"></i>
				</button>
			</c:when>
			<c:when test="${!ViewPerformance}">
				<button class="btn">
					<i class="icon-signal"></i>
				</button>
				<button class="btn btn-info">
					<i class="icon-pencil"></i>
				</button>
				<button class="btn btn-warning">
					<i class="icon-group"></i>
				</button>
				<button class="btn btn-danger" id="btn-danger">
					<i class="icon-cogs"></i>
				</button>
			</c:when>
		</c:choose>
		</div>

		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span> <span class="btn btn-info"></span>

			<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
		</div>
	</div>
	<!-- #sidebar-shortcuts -->

	<ul class="nav nav-list">
		<li id="fhindex" class="active">
			<a href="javaScript:;" onClick="document.getElementById('iframepage').src ='adminHomePage.do';">
				<i class="icon-dashboard"></i> <span class="menu-text"> 后台首页</span>
			</a>
		</li>
		<c:forEach var="menu" items="${menus}">
			<li id="z${menu.id }"><a href="#" class="dropdown-toggle"> <i
					class="${menu.resourceId }"></i> <span class="menu-text">
						${menu.moduleName }</span> <b class="arrow icon-angle-down"></b>
			</a>
				<ul class="submenu">
					<c:forEach var="mr" items="${menu.listModuleRes}">
						<li id="lm${mr.id }"><a href="javascript:;"
							onclick='setIframePage("${mr.resourceName}","${ctx}${mr.resurl}","lm${mr.id }","z${menu.id }");'
							target="iframepage"> <i class="icon-double-angle-right"></i>
								${mr.resourceName }
						</a></li>
					</c:forEach>
				</ul></li>
		</c:forEach>
	</ul>
	<!-- /.nav-list -->

	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
			data-icon2="icon-double-angle-right"></i>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/lib/biz.jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/i18n/i18n_zh.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/lib/biz.js"></script>
	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'collapsed')
		} catch (e) {
		}
		var add_iframe_dialog;
		$(document).ready(function(){
			$("#sidebar-shortcuts-large button").on("click",function(){
				var _className = $(this).attr("class");
				if(_className.indexOf("btn-success")>0){
					//显示业绩统计柱状图
					document.getElementById('iframepage').src ='<%=request.getContextPath() %>/standard/toListStandardBar.do'
				}else if(_className.indexOf("btn-info")>0){
					//新增vip学员
					var url="<m:url value='/vipStudent/toAddVipStudent.do'/>";
					add_iframe_dialog = new biz.dialog({
						id:$('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
						modal: true,
						width: $(window).width()*0.6,
						height:$(window).height()*0.8,
						title: "会员信息管理表增加"
					});
					add_iframe_dialog.open();
				}else if(_className.indexOf("btn-warning")>0){
					//显示员工资料卡片
					document.getElementById('iframepage').src ='<%=request.getContextPath() %>/employee/toListEmployee.do'
				}else if(_className.indexOf("btn-danger")>0){
					//修改密码事件
					var url="<m:url value='/userInfo/toEditPassWord.do'/>";
					edit_password_iframe_dialog = new biz.dialog({
						id:$('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
						modal: true,
						width: 400,
						height: 240,
						title: "修改密码"
					});
					edit_password_iframe_dialog.open();
				}
			})
		})
		function closeAdd(){
			add_iframe_dialog.close();
	  	}
	</script>
</div>





