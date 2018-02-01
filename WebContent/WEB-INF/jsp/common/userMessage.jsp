<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="no-js">
<head lang="en">
<%@ include file="header.jsp"%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<%=request.getContextPath() %>/styles/icon-font/iconfont.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/styles/icon-font/info-iconfont.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/styles/statistic/style.css">
<!-- Resource style -->

<!--ace框架-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/static/css/ace.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/static/css/ace-rtl.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/static/css/ace-skins.min.css" />
<script
	src="<%=request.getContextPath() %>/static/js/jquery.easy-pie-chart.min.js"></script>

<script src="<%=request.getContextPath() %>/js/statistic/modernizr.js"></script>
<!-- Modernizr -->
<title></title>
<style type="text/css">
body {
	-moz-user-select: none;
	-webkit-user-select: none;
	font-family: "PingFang SC", "Helvetica Neue", Helvetica, Arial,
		"Hiragino Sans GB", "Microsoft Yahei", 微软雅黑, STHeiti, 华文细黑, sans-serif;
}

div.container {
	height: 100%;
	width: 100%;
	font-family: "PingFang SC", "Helvetica Neue", Helvetica, Arial,
		"Hiragino Sans GB", "Microsoft Yahei", 微软雅黑, STHeiti, 华文细黑, sans-serif;
}

ul.userBoxWrap {
	height: 100%;
	width: 100%;
}

ul.userBoxWrap>li.userBox {
	display: block;
	height: 50%;
	width: 24%;
	float: left;
	padding: 5px;
	cursor: default;
}
li.userBox:last-of-type {
	width: 25%!important;
}
li.userBox>div.itemBox {
	border:1px #777 solid;
	border-radius: 10px;
	height: 100%;
	min-height: 254px;
	width: 100%;
	position: relative;
	color: rgb(129, 131, 139);
}


div.itemBoxTitle {
	height: 30px;
	line-height: 30px;
	width: 94%;
	margin: auto;
	background-color: rgb(255, 255, 255);
	border-bottom: 1px #777 solid;
	text-indent: 10px;
}

ul.infos>li.infoItem {
	height: 20px;
	overflow: hidden;
	margin: 20px 0px 30px 0px;
}

ul.infos>li.infoItem span {
	text-indent: 10px
}

ul.infos>li.infoItem span+a {
	display: inline-block;
	float: right;
	text-align: center;
	width: 40%;
	text-overflow: ellipsis
}

ul.infos>li.infoItem a:hover {
	cursor: pointer;
	text-decoration: underline;
}

.infoItem>.fastItem {
	display: inline-block;
	width: 50%;
	text-align: left;
	text-indent: 10px;
	color: rgb(129, 131, 139);
	cursor: pointer;
}

.infoItem>.noticeItemleft {
	display: inline-block;
	width: 70%;
	text-align: left;
	color: #484848;
	cursor: pointer;
	text-indent: 10px;
	float: left;
}

.infoItem>.noticeItemright {
	display: inline-block;
	width: 30%;
	text-align: left;
	color: #484848;
	cursor: pointer;
	text-indent: 10px;
	float: left;
}

.filedownload {
	width: 30%;
	text-align: left;
	display: inline-block;
	text-indent: 10px;
	color: rgb(129, 131, 139);
	cursor: pointer;
}
</style>
<script type="text/javascript">
	jQuery(function($) {
		$('.easy-pie-chart.percentage').each(function(){
			var barColor = $(this).data('color') || '6fb3e0';
			var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)' : '#FFF';
			var size = parseInt($(this).data('size')) || 50;
			$(this).easyPieChart({
				barColor: barColor,
				trackColor: trackColor,
				scaleColor: false,
				lineCap: 'butt',
				lineWidth:4,
				animate: /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase()) ? false : 1000,
				size: size
			});
		})
	});
	
	</script>
</head>
<body onselect="return false;" style="background: #fff">
	<div class="cd-pricing-container cd-full-width cd-secondary-theme">
		<ul class="cd-pricing-list cd-bounce-invert">
			<li style="width: 20%">
				<ul class="cd-pricing-wrapper">
					<li data-type="monthly" class="is-visible willStu"
						id="MarketStudent">
						<div class="cd-pricing-header topper">
							<span class="nameTop"> <i class="iconfont">&#xe883;</i> <label>今日新增录入</label></span>
							<div class="cd-price">
								<span class="cd-value-big" title="今日新增录入">${model.currMonthMarkStu.markStuCount}</span>
							</div>
						</div>
					</li>


				</ul> <!-- .cd-pricing-wrapper -->
			</li>

			<li style="width: 20%">
				<ul class="cd-pricing-wrapper">
					<li data-type="monthly" class="is-visible oop">
						<div class="cd-pricing-header topper">
							<span class="nameTop"> <i class="iconfont">&#xe883;</i> <label>本月VIP学员
							</label>
							</span>
							<div class="cd-price">
								<span class="cd-value-big" title="本月新增报名学员">${model.currMonth.vipcnt}</span>
							</div>
						</div>
					</li>
				</ul> <!-- .cd-pricing-wrapper -->
			</li>

			<li class="cd-popular" style="width: 20%">
				<ul class="cd-pricing-wrapper">
					<li data-type="monthly" class="is-visible vipStu" id="VipStudent">
						<div class="cd-pricing-header topper">
							<span class="nameTop"> <i class="iconfont">&#xe81d;</i> <label>本月新增订单
									<!-- 								<i class="info-iconfont" title="本月新增报名订单">&#xe67f;</i> -->
							</label>
							</span>
							<div class="cd-price">
								<span class="cd-value-big" title="本月新增报名订单">${model.currMonth.cnt}</span>
							</div>
						</div> <!-- .cd-pricing-header -->
					</li>


				</ul>
			</li>
			<!-- .cd-pricing-wrapper -->

			 <li style="width: 40%">
				<ul class="cd-pricing-wrapper">
					<li data-type="monthly" class="is-visible oor" id="AccountFlow">
						<div class="cd-pricing-header topper">
							<span class="nameTop"> <i class="iconfont">&#xe81d;</i> <label>消费支出
									<i class="info-iconfont" title="本月已除去学费退款的成本费用">&#xe637;</i>
							</label>
							</span>
							<div class="cd-price">
								<span class="cd-value-big"> <fmt:formatNumber
										value="${model.currMonthMoney.loan-model.currMonthxf.xftk-model.currMonthxf.xftj}"
										pattern="0" type="number"></fmt:formatNumber>
								</span>
							</div>
						</div>
					</li>
				</ul> <!-- .cd-pricing-wrapper -->
			</li>

			<c:if test="${ViewPerformance}">
				<li style="width: 50%">
					<ul class="cd-pricing-wrapper">
						<li data-type="monthly" class="is-visible allCost" id="AccountFlow" >
							<div class="cd-pricing-header topper">
								<span class="nameTop"> <i class="iconfont">&#xe6a9;</i> <label>总业绩
										 <i class="info-iconfont" title="本月实收报名费+本月实收补款">&#xe637;</i>
								</label>
								</span>
								<div class="cd-price">
									<div class="easy-pie-chart percentage"
										style="margin-right: 10px"
										data-percent="${(model.currMonthxf.xfsr+model.currMonthxf.xfbk)/(model.perfTarget/100)}"
										data-size="56">
										<span class="percent"> <fmt:formatNumber
												value="${(model.currMonthxf.xfsr+model.currMonthxf.xfbk)/(model.perfTarget/100)}"
												pattern="0.0" type="number"></fmt:formatNumber>
										</span>%
									</div>
									<span class="cd-value-big"> <fmt:formatNumber
											value="${model.currMonthxf.xfsr+model.currMonthxf.xfbk}"
											pattern="0" type="number"></fmt:formatNumber>
									</span>
								</div>
							</div> <!-- .cd-pricing-header -->
						</li>

					</ul>
				</li>
				<li class="cd-popular" style="width: 25%">
					<ul class="cd-pricing-wrapper">
						<li data-type="monthly" class="is-visible newOrder"
							id="VipStudent">
							<div class="cd-pricing-header topper">
								<span class="nameTop"> <i class="iconfont">&#xf0191;</i>
									<label>应收总额 <i class="info-iconfont"
										title="本月已除去退款学员的应收账款">&#xe637;</i>
								</label>
								</span>
								<div class="cd-price">
									<span class="cd-value-big"> <fmt:formatNumber
											value="${model.currMonth.shouldPay}" pattern="0"
											type="number"></fmt:formatNumber>
									</span>
								</div>
							</div> <!-- .cd-pricing-header -->
						</li>
					</ul> <!-- .cd-pricing-wrapper -->
				</li>
				<li style="width: 25%">
					<ul class="cd-pricing-wrapper">
						<li data-type="monthly" class="is-visible allCost"
							id="AccountFlow">
							<div class="cd-pricing-header topper">
								<span class="nameTop"> <i class="iconfont">&#xe7de;</i> <label>退款总额
										<i class="info-iconfont" title="本月退款学员退款总额">&#xe637;</i>
								</label>
								</span>
								<div class="cd-price">
									<span class="cd-value-big"> <fmt:formatNumber
											value="${model.currMonthxf.xftk}" pattern="0" type="number"></fmt:formatNumber>
									</span>
								</div>
							</div> <!-- .cd-pricing-header -->
						</li>


					</ul> <!-- .cd-pricing-wrapper -->
				</li>
				

				<li style="width: 40%">
					<ul class="cd-pricing-wrapper">
						<li data-type="monthly" class="is-visible willStu" id="AccountFlow">
							<div class="cd-pricing-header topper">
								<span class="nameTop"> <i class="iconfont">&#xe6a9;</i> <label>实收总业绩
										<i class="info-iconfont"
										title="本月实收报名费+本月实收补款-本月退款总额">&#xe637;</i>
								</label>
								</span>
								<div class="cd-price">
									<div class="easy-pie-chart percentage"
										style="margin-right: 10px"
										data-percent="${(model.currMonthxf.xfsr+model.currMonthxf.xfbk-model.currMonthxf.xftk)/(model.perfTarget/100)}"
										data-size="56">
										<span class="percent"> <fmt:formatNumber
												value="${(model.currMonthxf.xfsr+model.currMonthxf.xfbk-model.currMonthxf.xftk)/(model.perfTarget/100)}"
												pattern="0.0" type="number"></fmt:formatNumber>
										</span>%
									</div>
									<span class="cd-value-big"> <fmt:formatNumber
											value="${model.currMonthxf.xfsr+model.currMonthxf.xfbk-model.currMonthxf.xftk}"
											pattern="0" type="number"></fmt:formatNumber>
									</span>
								</div>
							</div> <!-- .cd-pricing-header -->
						</li>

					</ul> <!-- .cd-pricing-wrapper -->
				<li style="width: 30%">
					<ul class="cd-pricing-wrapper">
						<li data-type="monthly" class="is-visible oop" id="VipStudent">
							<div class="cd-pricing-header topper">
								<span class="nameTop"> <i class="iconfont">&#xe6a9;</i> <label>实收报名费
										<i class="info-iconfont" title="本月新增学员首次支付总额+本月其他方式学费收入">&#xe637;</i>
								</label>
								</span>
								<div class="cd-price">
									<span class="cd-value-big"> <fmt:formatNumber
											value="${model.currMonthxf.xfsr}" pattern="0" type="number"></fmt:formatNumber>
									</span>
								</div>
							</div> <!-- .cd-pricing-header -->
						</li>
					</ul> <!-- .cd-pricing-wrapper -->
				</li>

				<li class="cd-popular" style="width: 30%">
					<ul class="cd-pricing-wrapper">
						<li data-type="monthly" class="is-visible complain"
							id="ContinuePay">
							<div class="cd-pricing-header topper">
								<span class="nameTop"> <i class="iconfont">&#xe6cc;</i> <label>实收补款
										<i class="info-iconfont" title="本月收到补款总额">&#xe637;</i>
								</label>
								</span>
								<div class="cd-price">
									<span class="cd-value-big"> <fmt:formatNumber
											value="${model.currMonthxf.xfbk}" pattern="0" type="number"></fmt:formatNumber>
									</span>
								</div>
							</div>
						</li>
					</ul>
				</li>

			</c:if>

			<li class="cd-popular" style="width: 33.3%">
				<ul class="cd-pricing-wrapper">
					<li data-type="monthly" class="is-visible oop" id="ContinuePay">
						<div class="cd-pricing-header topper">
							<span class="nameTop"> <i class="iconfont">&#xe6cc;</i> <label>今日业绩
									<i class="info-iconfont" title="当日业绩总额">&#xe637;</i>
							</label>
							</span>
							<div class="cd-price">
								<span class="cd-value-big"> <fmt:formatNumber
										value="${model.today.xfsr-model.today.xfsr+model.today.xfsr}"
										pattern="0" type="number"></fmt:formatNumber>
								</span>
							</div>
						</div>
					</li>
				</ul>
			</li>

			<li class="cd-popular" style="width: 33.3%">
				<ul class="cd-pricing-wrapper">
					<li data-type="monthly" class="is-visible oor" id="ContinuePay">
						<div class="cd-pricing-header topper">
							<span class="nameTop"> <i class="iconfont">&#xe6cc;</i> <label>今日补款
									<i class="info-iconfont" title="当日补款总额">&#xe637;</i>
							</label>
							</span>
							<div class="cd-price">
								<span class="cd-value-big"> <fmt:formatNumber
										value="${model.today.xfbk-model.today.xfbk+model.today.xfbk}"
										pattern="0" type="number"></fmt:formatNumber>
								</span>
							</div>
						</div>
					</li>

				</ul>
			</li>

			<li class="cd-popular" style="width: 33.3%">
				<ul class="cd-pricing-wrapper">
					<li data-type="monthly" class="is-visible vipStu" id="ContinuePay">
						<div class="cd-pricing-header topper">
							<span class="nameTop"> <i class="iconfont">&#xe6cc;</i> <label>今日实收业绩
									<i class="info-iconfont" title="当日业绩总额+当日补款总额-当日退款总额">&#xe637;</i>
							</label>
							</span>
							<div class="cd-price">
								<span class="cd-value-big"> <fmt:formatNumber
										value="${model.today.xfsr+model.today.xfbk-model.today.xftk}"
										pattern="0" type="number"></fmt:formatNumber>
								</span>
							</div>

						</div>
					</li>
				</ul>
			</li>

			<c:if test="${model.myPerformance.performance >0}">
				<li class="cd-popular" style="width: 50%">
					<ul class="cd-pricing-wrapper">
						<li data-type="monthly" class="is-visible oor" id="ContinuePay" >
							<div class="cd-pricing-header topper">
								<span class="nameTop"> <i class="iconfont">&#xe6cc;</i> <label>我的绩效贡献总额
										<i class="info-iconfont" title="我的绩效贡献总额">&#xe637;</i>
								</label>
								</span>
								<div class="cd-price">
									<span class="cd-value-big"> <fmt:formatNumber
											value="${model.myPerformance.performance}" pattern="0"
											type="number"></fmt:formatNumber>
									</span>
								</div>
							</div>
						</li>
					</ul>
				</li>
			</c:if>
			<c:if test="${isHavePerformance}">
				<li class="cd-popular" style="width: 50%">
					<ul class="cd-pricing-wrapper">
						<li data-type="monthly" class="is-visible oor" id="ContinuePay">
							<div class="cd-pricing-header topper">
								<span class="nameTop"> <i class="iconfont">&#xe6cc;</i> <label>部门本月总业绩
										<i class="info-iconfont" title="部门本月总业绩">&#xe637;</i>
								</label>
								</span>
								<div class="cd-price">
									<div class="easy-pie-chart percentage"
										style="margin-right: 10px"
										data-percent="${(model.deptPerformance.xfsr+model.deptPerformance.xfbk-model.deptPerformance.xftk)/(model.subject.perfTarget/100)}"
										data-size="56">
										<span class="percent"> <fmt:formatNumber
												value="${(model.deptPerformance.xfsr+model.deptPerformance.xfbk-model.deptPerformance.xftk)/(model.subject.perfTarget/100)}"
												pattern="0.0" type="number"></fmt:formatNumber>
										</span>%
									</div>
									<span class="cd-value-big"> <fmt:formatNumber
											value="${model.deptPerformance.xfsr+model.deptPerformance.xfbk-model.deptPerformance.xftk}"
											pattern="0" type="number"></fmt:formatNumber>
									</span>
								</div>
							</div>
						</li>
					</ul>
				</li>

			</c:if>
		</ul>
		<!-- .cd-pricing-list -->
	</div>
	<!-- .cd-pricing-container -->

	<div class="cd-center-container">
		<div class="center-title">
			<span class="nameCenter"> <i class="iconfont-xsmall">&#xe7c6;
			</i>本月个人业务统计 <input type="hidden" name="isModifyPw" id="isModifyPw"
				value="${isModifyPw}">
			</span>
		</div>
		<div class="container">
			<ul class="userBoxWrap">
				<li class="userBox">
					<div class="itemBox">
						<div class="itemBoxTitle">
							<span class="nameCenter"> <i class="iconfont-xsmall">&#xe7c6;
							</i>个人信息
							</span> <span id="userid" style="display: none">${employee.id}</span>
						</div>
						<div class="itemBoxInfo">
							<ul class="infos">
								<li class="infoItem"><span>【员工姓名】</span><a>${user.fullName}</a></li>
								<li class="infoItem"><span>【员工昵称】</span><a>${employee.nickName}</a></li>
								<li class="infoItem"><span>【所在部门】</span><a>${employee.dept}</a></li>
								<li class="infoItem"><span>【所属岗位】</span><a>${employee.position}</a></li>
							</ul>
						</div>
					</div>
				</li>

				<li class="userBox">
					<div class="itemBox">
						<div class="itemBoxTitle">
							<span class="nameCenter"> <i class="iconfont-xsmall">&#xe7c6;
							</i>
							<!-- 考勤情况 -->个人业务
							</span>
						</div>
						<div class="itemBoxInfo">
							<ul class="infos">
								<li class="infoItem"><span>【累计请假】</span><a> 天</a></li>
								<li class="infoItem"><span>【费用报销】</span><a>${model.myExpense.docAttach}
										笔</a></li>
								<li class="infoItem"><span>【报销总额】</span><a>${model.myExpense.expenseMoney}
										元</a></li>
								<li class="infoItem"><span>【业绩总额】</span><a>${model.myPerformance.performance}
										元</a></li>
							</ul>
						</div>
					</div>
				</li>

				<li class="userBox">
					<div class="itemBox">
						<div class="itemBoxTitle">
							<span class="nameCenter"> <i class="iconfont-xsmall">&#xe7c6;
							</i>快捷菜单
							</span>
						</div>
						<div class="itemBoxInfo">
							<ul class="infos">
								<li class="infoItem"><a class="fastItem"><i
										class="iconfont-xsmall">&#xe7c6; </i>我的业绩</a><a class="fastItem"><i
										class="iconfont-xsmall">&#xe7c6; </i>费用借款</a></li>
								<li class="infoItem"><a class="fastItem" id="leaveApply"><i
										class="iconfont-xsmall">&#xe7c6; </i>请假申请</a><a class="fastItem"><i
										class="iconfont-xsmall">&#xe7c6; </i>调休申请</a></li>
								<li class="infoItem"><a class="fastItem" id="expenseApply"><i
										class="iconfont-xsmall">&#xe7c6; </i>费用报销</a></li>
							</ul>
						</div>
					</div>
				</li>

				<li class="userBox">
					<div class="itemBox">
						<div class="itemBoxTitle">
							<span class="nameCenter"> <i class="iconfont-xsmall">&#xe7c6;
							</i>员工必读
							</span>
						</div>
						<div class="itemBoxInfo">
							<ul class="infos">
								<c:forEach var="noticeList" items="${noticeList}" begin="0"
									end="3" step="1" varStatus="status">
									<li class="infoItem"><a class="noticeItemleft"
										did="${noticeList.id}">${noticeList.noticeTitle}</a> <c:if
											test="${not empty noticeList.fileUrl}">
											<form name="form" method="GET"
												action="<%=request.getContextPath()%>/download/fileDownload">
												<a class="noticeItemright" name="noticeItemright" onclick=""
													id="noticeItemright" value="${noticeList.id}"> <i
													class="fa fa-download"> <input type="hidden"
														name="filedownloadurl" value="${noticeList.fileUrl}" />
												</i>下载附件
												</a>
											</form>
										</c:if></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</li>

			</ul>
		</div>
	</div>
	<script src="<%=request.getContextPath() %>/js/statistic/main.js"></script>
	<!-- Resource jQuery -->
	<script>
	function GetDateStr(AddDayCount,time) { 
		var dd = new Date(); 
		dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期 
		var y = dd.getFullYear(); 
		var m = dd.getMonth()+1;//获取当前月份的日期 
		var d = dd.getDate(); 
		return y+"-"+m+"-"+d+" "+time; 
	} 
	//查看用户信息弹出框
	var show_iframe_dialog;
	//修改密码弹出框
	var edit_password_iframe_dialog;
	//关闭查看用户资料页面，供子页面调用
    function closeShow(){
    	show_iframe_dialog.close();
    }
    //查看公告的弹出框
	/* var showNotice_iframe_dialog; */
	
	$(document).ready(function(){
		//获取父框架修改密码和用户资料按钮
		var _iframe = window.parent;
		var _userMessBtn =_iframe.document.getElementById('userMessBtn');
		var _setPwdBtn =_iframe.document.getElementById('setPwdBtn');
		//用户资料点击事件
		_userMessBtn.addEventListener("click", function(){
			var key = $("#userid").text();
			var url="<m:url value='/employee/toShowEmployee.do'/>?key="+key;
			show_iframe_dialog = new biz.dialog({
			 	id:$('<div id="showwindow_iframe"></div>').html('<iframe id="iframeShow" name="iframeShow" scrolling="no" src="'+url+'" width="100%" frameborder="no" border="0" height="100%"></iframe>'),  
				modal: true,
				width: $(window).width()*0.6,
				height:$(window).height()*0.8,
					title: "个人资料"
			});
	  		show_iframe_dialog.open();
		})
		//修改密码点击事件
		_setPwdBtn.addEventListener("click", function(){
				var url="<m:url value='/userInfo/toEditPassWord.do'/>";
				edit_password_iframe_dialog = new biz.dialog({
					id:$('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
					modal: true,
					width: 400,
					height: 240,
					title: "修改密码"
				});
				edit_password_iframe_dialog.open();
		})
		if($("#isModifyPw").val()=="t"){//如果当前用户还是默认密码的话跳转到修改密码页面
			_setPwdBtn.click()
		}
	})
	// 弹出公告通知
	.on('click', '.noticeItemleft', function() {
		var key = this.getAttribute("did");
		var url="<m:url value='/empNotice/toShowEmpNotice.do'/>?key="+key;
		show_iframe_dialog = new biz.dialog({
		 	id:$('<div id="showwindow_iframe"></div>').html('<iframe id="iframeShow" name="iframeShow" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: $(window).width()*0.6,
			height: $(window).height()*0.8,
				title: "公告通知详情"
		});
  		show_iframe_dialog.open();
	})
	// 请假申请
	.on('click', '#leaveApply', function() {
		var url="<m:url value='/leaveApply/toAddLeaveApply.do'/>";
		show_iframe_dialog = new biz.dialog({
			id:$('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: $(window).width()*0.6,
			height:$(window).height()*0.6,
			title: "请假申请"
		});
		show_iframe_dialog.open();
	})
	// 费用报销
	.on('click', '#expenseApply', function() {
		var url="<m:url value='/expenseAccount/toAddExpenseAccount.do'/>";
		show_iframe_dialog = new biz.dialog({
			id:$('<div id="addwindow_iframe"></div>').html('<iframe id="iframeAdd" name="iframeAdd" src="'+url+'" width="100%" frameborder="no" border="0" height="97%"></iframe>'),  
			modal: true,
			width: $(window).width()*0.6,
			height:$(window).height()*0.6,
			title: "新增报销单"
		});
		show_iframe_dialog.open();
	})
	.on('click', '.noticeItemright', function() {
		var fid = $(this).parent();
		fid.submit();
	})
</script>
</body>
</html>

