<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.dongnao.workbench.common.util.Utils"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="navbar navbar-default" id="navbar">
	<script type="text/javascript">
		try{ace.settings.check('navbar','fixed')}catch(e){}
	</script>
	<div class="navbar-container" id="navbar-container">
		<div class="navbar-header pull-left">
			<a href="#" class="navbar-brand"> <small> </small>
				<img alt="" src="<%=path%>/styles/images/y111.png">
			</a>
			<!-- /.brand -->
			<a href="#" style="float: right"></a>
		</div>
		<!-- /.navbar-header -->

		<div class="navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">
				<li><a data-toggle="dropdown" href="#" class="dropdown-toggle" style="width: 200px;">
						<img alt="" src="<%=path%>/styles/images/logo.png">
						<span class="user-info"> <small style="margin-bottom: 2px">欢迎登陆</small>
							<small><%=Utils.getLoginUserInfo(request).getFullName()%></small>
					</span> <i class="icon-caret-down"></i>
				</a>
					<ul
						class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close"
						style="width: 200px;">
						<li><a href="javascript:;" id="setPwdBtn"><i class="icon-cog"></i>修改密码</a></li>
						<li><span id="userid" style="display: none"><%=Utils.getLoginUserInfo(request).getFullName()%></span>
							<a href="javascript:;" id="userMessBtn"><i class="icon-user"></i>个人资料</a>
						</li>
						<li class="divider"></li>
						<li><a href="logout.do"> <i class="icon-off"></i> 退出
						</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.navbar-header -->
	</div>
	<!-- /.container -->
</div>