<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>


<div class="sidebar" id="sidebar">
	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'fixed')
		} catch (e) {
		}
	</script>

	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<button class="btn btn-success" onclick="document.getElementById('iframepage').src ='<%=request.getContextPath() %>/standard/toListStandardBar.do'">
				<i class="icon-signal"></i>
			</button>

			<button class="btn btn-info" onclick="document.getElementById('iframepage').src ='<%=request.getContextPath() %>/standard/toListStandardBar.do'">
				<i class="icon-pencil"></i>
			</button>

			<button class="btn btn-warning" onclick="document.getElementById('iframepage').src ='<%=request.getContextPath() %>/standard/toListStandardBar.do'">
				<i class="icon-group"></i>
			</button>

			<button class="btn btn-danger" onclick="document.getElementById('iframepage').src ='<%=request.getContextPath() %>/standard/toListStandardBar.do'">
				<i class="icon-cogs"></i>
			</button>
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

	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'collapsed')
		} catch (e) {
		}
	</script>
</div>





