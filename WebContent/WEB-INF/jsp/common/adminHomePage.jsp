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
<!--ace框架-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/ace.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/ace-rtl.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/ace-skins.min.css" />
<script src="<%=request.getContextPath() %>/static/js/jquery.easy-pie-chart.min.js"></script>
<!-- Modernizr -->
<title></title>
<script type="text/javascript">
	jQuery(function($) {
		$('.easy-pie-chart.percentage').each(function(){
			var barColor = $(this).data('color') || '#6fb3e0';
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
	function lookMore(id,did,term){
	  	var url = baseUrl + '/'+did+'/toList'+id+'.do?term='+term;
	  	window.parent.document.getElementById('iframepage').src=url;	 
	}
	</script>
</head>
<body onselect="return false;" style="background: #f5f5f5">
	<div class="cd-pricing-container cd-full-width cd-secondary-theme">
		<ul class="cd-pricing-list cd-bounce-invert">
			<li style="width: 20%">
				<ul class="cd-pricing-wrapper">
					<li data-type="monthly" class="is-visible willStu"
						id="MarketStudent" did="marketStudent" term="month"
						onclick="lookMore(this.id,this.attributes['did'].value,this.attributes['term'].value)">
						<div class="cd-pricing-header topper">
							<span class="nameTop"> <i class="iconfont">&#xe883;</i> <label>今日新增录入
							</label>
							</span>
							<div class="cd-price">
								<span class="cd-value-big" title="今日新增录入">${model.currMonthMarkStu.markStuCount}</span>
							</div>
						</div>
					</li>
				</ul> <!-- .cd-pricing-wrapper -->
			</li>

			<li style="width: 20%">
				<ul class="cd-pricing-wrapper">
					<li data-type="monthly" class="is-visible oop" id="VipStudent"
						did="vipStudent" term="month"
						onclick="lookMore(this.id,this.attributes['did'].value,this.attributes['term'].value)">
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
					<li data-type="monthly" class="is-visible vipStu" id="VipStudent"
						did="vipStudent" term="month"
						onclick="lookMore(this.id,this.attributes['did'].value,this.attributes['term'].value)">
						<div class="cd-pricing-header topper">
							<span class="nameTop"> <i class="iconfont">&#xe81d;</i> <label>本月新增订单
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
					<li data-type="monthly" class="is-visible oor" id="AccountFlow"
						did="accountFlow" term="monthcost"
						onclick="lookMore(this.id,this.attributes['did'].value,this.attributes['term'].value)">
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

			<li class="cd-popular" style="width: 40%">
				<ul class="cd-pricing-wrapper">
					<li data-type="monthly" class="is-visible newOrder" id="VipStudent"
						did="vipStudent" term="monthshouldPay"
						onclick="lookMore(this.id,this.attributes['did'].value,this.attributes['term'].value)">
						<div class="cd-pricing-header topper">
							<span class="nameTop"> <i class="iconfont">&#xf0191;</i> <label>应收总额
									<i class="info-iconfont" title="本月已除去退款学员的应收账款">&#xe637;</i>
							</label>
							</span>
							<div class="cd-price">
								<span class="cd-value-big"> <fmt:formatNumber
										value="${model.currMonth.shouldPay}" pattern="0" type="number"></fmt:formatNumber>
								</span>
							</div>
						</div> <!-- .cd-pricing-header -->
					</li>

				</ul> <!-- .cd-pricing-wrapper -->
			</li>

			<li style="width: 30%">
				<ul class="cd-pricing-wrapper">
					<li data-type="monthly" class="is-visible oop" id="AccountFlow"
						did="accountFlow" term="monthxftk"
						onclick="lookMore(this.id,this.attributes['did'].value,this.attributes['term'].value)">
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

			<li class="cd-popular" style="width: 30%">
				<ul class="cd-pricing-wrapper">
					<li data-type="monthly" class="is-visible vipStu" did="vipStudent"
						id="VipStudent" term="monthowePay"
						onclick="lookMore(this.id,this.attributes['did'].value,this.attributes['term'].value)">
						<div class="cd-pricing-header topper">
							<span class="nameTop"> <i class="iconfont">&#xe765;</i> <label>应收欠款
									<i class="info-iconfont" title="本月学员剩余贷款总额">&#xe637;</i>
							</label>
							</span>
							<div class="cd-price">
								<span class="cd-value-big"> <fmt:formatNumber
										value="${model.currMonth.owePay}" pattern="0" type="number"></fmt:formatNumber>
								</span>
							</div>
						</div> <!-- .cd-pricing-header -->
					</li>

				</ul> <!-- .cd-pricing-wrapper -->
			</li>

			<li style="width: 33%">
				<ul class="cd-pricing-wrapper">
					<li data-type="monthly" class="is-visible vipStu" did="accountFlow"
						id="AccountFlow" term="monthmajorIncome"
						onclick="lookMore(this.id,this.attributes['did'].value,this.attributes['term'].value)">
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

			<li style="width: 33%">
				<ul class="cd-pricing-wrapper">
					<li data-type="monthly" class="is-visible allCost" id="AccountFlow"
						did="accountFlow" term="monthactualPay"
						onclick="lookMore(this.id,this.attributes['did'].value,this.attributes['term'].value)">
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

			<li class="cd-popular" style="width: 34%">
				<ul class="cd-pricing-wrapper">
					<li data-type="monthly" class="is-visible complain"
						did="continuePay" id="ContinuePay" term="month"
						onclick="lookMore(this.id,this.attributes['did'].value,this.attributes['term'].value)">
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

		</ul>
	</div>
	<!-- .cd-pricing-container -->
	<!-- 报表部分 -->
	<div class="cd-center-container">
		<div class="center-title">
			<span class="nameCenter"> <i class="iconfont-xsmall">&#xe7c6;
			</i>总报表
			</span>
		</div>
		<ul class="cd-popular cd-center">
			<li data-type="monthly" class="is-visible" did="vipStudent"
				id="VipStudent" term="vipCnt"
				onclick="lookMore(this.id,this.attributes['did'].value,this.attributes['term'].value)">
				<div class="cd-pricing-header center">
					<span class="nameCenter"> <i class="iconfont-small">&#xe62f;</i>
						<label>总会员</label>
					</span>
					<div class="cd-price">
						<span class="cd-value-small" title="报名学员总数"> <fmt:formatNumber
								value="${model.all.vipcnt}" pattern="0" type="number"></fmt:formatNumber>
						</span>
					</div>
				</div> <!-- .cd-pricing-header center -->
			</li>
			<li data-type="monthly" class="is-visible" did="vipStudent"
				id="VipStudent" term="orderCnt"
				onclick="lookMore(this.id,this.attributes['did'].value,this.attributes['term'].value)">
				<div class="cd-pricing-header center">
					<span class="nameCenter"> <i class="iconfont-small">&#xe7c6;</i>
						<label>订单量</label>
					</span>
					<div class="cd-price">
						<span class="cd-value-small" title="总报名次数">${model.all.cnt}</span>
					</div>
				</div> <!-- .cd-pricing-header center -->
			</li>

			<li data-type="monthly" class="is-visible" did="accountFlow"
				id="AccountFlow" term="majorIncome"
				onclick="lookMore(this.id,this.attributes['did'].value,this.attributes['term'].value)">
				<div class="cd-pricing-header center">
					<span class="nameCenter"> <i class="iconfont-small">&#xe623;</i>
						<label>主营业务收入 <i class="info-iconfont"
							title="本年实收报名费+本年实收补款-本年退款总额">&#xe637;</i>
					</label>
					</span>
					<div class="cd-price">
						<!--  <span class="cd-value-small">${model.allxf.xfsr+model.allxf.xfbk-model.allxf.xftk}</span>-->
						<span class="cd-value-small"> <fmt:formatNumber
								value="${model.all.actualPay}" pattern="0" type="number"></fmt:formatNumber>
						</span>
					</div>
				</div> <!-- .cd-pricing-header center -->
			</li>
			<li data-type="monthly" class="is-visible" did="accountFlow"
				id="AccountFlow" term="QTSR"
				onclick="lookMore(this.id,this.attributes['did'].value,this.attributes['term'].value)">
				<div class="cd-pricing-header center">
					<span class="nameCenter"> <i class="iconfont-small">&#xe623;</i>
						<label>其他业务收入 <i class="info-iconfont" title="本年其他收入总额">&#xe637;</i>
					</label>
					</span>
					<div class="cd-price">
						<span class="cd-value-small"> <fmt:formatNumber
								value="${model.allxf.qtsr}" pattern="0" type="number"></fmt:formatNumber>
						</span>
					</div>
				</div>
			</li>

			<li data-type="monthly" class="is-visible" did="accountFlow"
				id="AccountFlow" term="majorCost"
				onclick="lookMore(this.id,this.attributes['did'].value,this.attributes['term'].value)">
				<div class="cd-pricing-header center">
					<span class="nameCenter"> <i class="iconfont-small">&#xe7de;</i>
						<label>主营业务成本 <i class="info-iconfont"
							title="总成本-学费退款-其他业务支出">&#xe637;</i>
					</label>
					</span>
					<div class="cd-price">
						<span class="cd-value-small"> <fmt:formatNumber
								value="${model.allMoney.loan-model.allxf.xftk-model.allxf.qtzc}"
								pattern="0" type="number"></fmt:formatNumber>
						</span>
					</div>
				</div> <!-- .cd-pricing-header center -->
			</li>
			<li data-type="monthly" class="is-visible" did="accountFlow"
				id="AccountFlow" term="QT"
				onclick="lookMore(this.id,this.attributes['did'].value,this.attributes['term'].value)">
				<div class="cd-pricing-header center">
					<span class="nameCenter"> <i class="iconfont-small">&#xe7de;</i>
						<label>其他业务支出</label>
					</span>
					<div class="cd-price">
						<span class="cd-value-small"> <fmt:formatNumber
								value="${model.allxf.qtzc}" pattern="0" type="number"></fmt:formatNumber>
						</span>
					</div>
				</div> <!-- .cd-pricing-header center -->
			</li>

			<li data-type="monthly" class="is-visible" did="accountFlow"
				id="AccountFlow" term="allCost"
				onclick="lookMore(this.id,this.attributes['did'].value,this.attributes['term'].value)">
				<div class="cd-pricing-header center">
					<span class="nameCenter"> <i class="iconfont-small">&#xe7de;</i>
						<label>总收入 <i class="info-iconfont" title="主营业务收入+其他业务收入">&#xe637;</i>
					</label>
					</span>
					<div class="cd-price">
						<span class="cd-value-small"> <fmt:formatNumber
								value="${model.all.actualPay+model.allxf.qtsr}" pattern="0"
								type="number"></fmt:formatNumber>
						</span>
					</div>
				</div> <!-- .cd-pricing-header center -->
			</li>
			<li data-type="monthly" class="is-visible" did="accountFlow"
				id="AccountFlow" term="allCost"
				onclick="lookMore(this.id,this.attributes['did'].value,this.attributes['term'].value)">
				<div class="cd-pricing-header center">
					<span class="nameCenter"> <i class="iconfont-small">&#xe7de;</i>
						<label>总成本 <i class="info-iconfont" title="主营业务成本+其他业务支出">&#xe637;</i>
					</label>
					</span>
					<div class="cd-price">
						<span class="cd-value-small"> <fmt:formatNumber
								value="${model.allMoney.loan-model.allxf.xftk-model.allxf.xftj}"
								pattern="0" type="number"></fmt:formatNumber>
						</span>
					</div>
				</div> <!-- .cd-pricing-header center -->
			</li>
			<li data-type="monthly" class="is-visible" did="accountFlow"
				id="AccountFlow" term="allProfit"
				onclick="lookMore(this.id,this.attributes['did'].value,this.attributes['term'].value)">
				<div class="cd-pricing-header center">
					<span class="nameCenter"> <i class="iconfont-small">&#xf01c9;</i>
						<label>总利润 <i class="info-iconfont" title="总收入-总成本">&#xe637;</i>
					</label>
					</span>
					<div class="cd-price">
						<span class="cd-value-small" id="profit"> <fmt:formatNumber
								value="${model.all.actualPay+model.allxf.qtsr-model.allMoney.loan+model.allxf.xftk+model.allxf.xftj}"
								pattern="0" type="number"></fmt:formatNumber>
						</span>
					</div>
				</div>
			</li>
		</ul>
	</div>
	<script src="<%=request.getContextPath() %>/js/statistic/main.js"></script>
	<!-- Resource jQuery -->
</body>
</html>

