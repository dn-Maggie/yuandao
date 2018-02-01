<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML  >
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<title><m:message code="role.list.title" /></title>
<script type="text/javascript">
	var menutree;
	var gridObj = {};
	$(function() {
		gridObj = new biz.grid(
				{
					id : "#remote_rowed",/*html部分table id*/
					url : "<m:url value='/userInfo/listUserByRid.do'/>?roleRid=${roleRid}",/*grid初始化请求数据的远程地址*/
					datatype : "json",/*数据类型，设置为json数据，默认为json*/
					//navtype:"top" /*导航栏类型*/,
					//height: gridHeight,
					multiselect : false,
					pager : '#remote_prowed' /*分页栏id*/,
					rowList : [ 10, 15, 50, 100 ],//每页显示记录数
					rowNum : 10,//默认显示15条
					colModel : [
					//	{name : "userId",key:true,hidden:true}, 
					{
						name : "USER_ACCOUNT",
						label : "<m:message code='userInfo.userAccount'/>",
						index : "u.USER_ACCOUNT"
					}, {
						name : "FULL_NAME",
						label : "<m:message code='user.userName'/>",
						index : "u.FULL_NAME"
					} ],
					serializeGridData : function(postData) {//添加查询条件值
						var obj = getQueryCondition();
						$.extend(true, obj, postData);//合并查询条件值与grid的默认传递参数
						return obj;
					}
				});
		$("#remote_rowed").setGridWidth($(window).width() * 0.99);
		$("#remote_rowed").setGridWidth(document.body.clientWidth * 0.99);
	});

	$(function() {
		$(window).resize(function() {
			$("#remote_rowed").setGridWidth($(window).width() * 0.99);
			$("#remote_rowed").setGridWidth(document.body.clientWidth * 0.99);
		});
	});

	/**
	 * 获取查询条件值
	 */
	function getQueryCondition() {
		var obj = {};
		addAttrToObject(obj, "userAccount");
		addAttrToObject(obj, "fullName");
		return obj;
	}
	//查询Grid数据
	function doSearch(isStayCurrentPage) {
		if (!isStayCurrentPage)
			gridObj.setGridParam({
				"page" : "1"
			});
		gridObj.trigger('reloadGrid');
	}

	function addAttrToObject(obj, name) {
		element = document.getElementById(name);
		var notInputValue = "";
		if (element != null) {
			if (element.value == null || element.value == "") {
				if ($(element).attr("uiType") != null) {
					if ($(element).attr("uiType") == "checkbox") {
						var tempCheckBoxObj = $(element).find(".checkbox");
						if (tempCheckBoxObj != null
								&& tempCheckBoxObj.length > 0) {
							tempCheckBoxObj.each(function() {
								if (this.checked) {
									notInputValue += this.value + ",";
								}
							});
							if (notInputValue != null
									&& notInputValue.indexOf(",") != -1) {
								notInputValue = notInputValue.substring(0,
										notInputValue.length - 1);
							}

						}
					} else if ($(element).attr("uiType") == "radio") {
						var tempRadioObj = $(element).find(".radio");
						if (tempRadioObj != null && tempRadioObj.length > 0) {
							tempRadioObj.each(function() {
								if (this.checked) {
									notInputValue = this.value;
									return;
								}
							});
						}
					}
					if (notInputValue == null || notInputValue == "") {
						return;
					}
				} else {
					return;
				}
			}

		} else {
			return;
		}

		obj = obj || {};
		var value = element.value != null ? element.value : notInputValue;
		if (element.name == 'userAccount') {
			value = '%' + value + '%';
		}
		if (element.name == 'fullName') {
			value = '%' + value + '%';
		}
		obj[name] = value;
	}
</script>
</head>
<body>
	<div class="main  choice_box">
		<div id="conditions"
			class="ui-table ui-widget ui-corner-all ui-margin ui-rightDiv"
			style="display: block;">
			<form id="queryForm">
				<!-- 查询区 表单 -->
				<div class="search border-bottom">
					<ul>
						<li style="width: 200px;"><span style="width: 80px"><m:message
									code='userInfo.userAccount' />:</span><input type="text"
							class="search_choose" style="width: 100px;" id="userAccount"
							name="userAccount"></li>
						<!-- 输入框-->
						<li style="width: 200px;"><span style="width: 80px"><m:message
									code="user.userName" />:</span><input type="text"
							class="search_choose" style="width: 100px;" id="fullName"
							name="fullName"></li>
						<!-- 输入框-->
						<li style="width: 180px;"><input type="button" value="查询"
							onclick="doSearch();" class="search_btn mr22 " /></li>

						<li style="width: 70px;"><input type="reset" value="重置"
							onClick="resetForm('queryForm')" class="reset_btn"></li>
						<!-- 重置 -->
						<!-- 查询-->
					</ul>
				</div>
			</form>

			<table id="remote_rowed"></table>
			<div id="remote_prowed"></div>
		</div>
	</div>
</body>
</html>
