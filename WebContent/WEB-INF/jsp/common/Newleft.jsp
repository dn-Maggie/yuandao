<%@page import="java.util.List"%>
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
			<button class="btn btn-success">
				<i class="icon-signal"></i>
			</button>

			<button class="btn btn-info">
				<i class="icon-pencil"></i>
			</button>

			<button class="btn btn-warning">
				<i class="icon-group"></i>
			</button>

			<button class="btn btn-danger">
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
		<li id="fhindex"><a href="javaScript:;"
			onClick="document.getElementById('iframepage').src ='vipStudent/toListAVipStudent.do';">
				<i class="icon-dashboard"></i> <span class="menu-text">学员信息查询</span>
		</a></li>
		<li id="z75487eff-dae8-424d-92e4-7b9df956363c"><a
			href="javaScript:;"
			onClick="document.getElementById('iframepage').src ='marketStudent/toListAMarketStudent.do';">
				<i class="icon-leaf"></i> <span class="menu-text">意向学员信息</span>
		</a></li>
		<c:if test="${isAdmin}">
			<li><a href="javaScript:;"
				onClick="document.getElementById('iframepage').src ='userInfo/toListNewUserInfo.do';">
					<i class="icon-user"></i> <span class="menu-text">系统账号管理</span>
			</a></li>
		</c:if>
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





