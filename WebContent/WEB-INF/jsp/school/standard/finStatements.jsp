<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<%@ include file="../../common/ace.jsp"%>
<title></title>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/echarts-2.2.7/echarts.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/static/css/chosen.css" />
<style type="text/css">
body {
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

.ui-jqgrid-htable {
	width: 100%;
}

.ui-jqgrid-labels {
	height: 34px;
	padding: 0 2px;
	text-align: center;
}

.ui-jqgrid-labels>th {
	width: 6.5%;
	border-right: 1px solid #D3DCEF;
	background: rgb(98,149,233);
	font-weight: 500;
	color: #fff;
	text-align: center;
}

.ui-jqgrid-labels>th.first {
	width: 9.9%;
}

.ui-row-ltr {
	height: 30px;
	border-bottom: 1px solid #D3DCEF;
}

.ui-row-ltr>td {
	text-align: center;
	border-right: 1px solid #D3DCEF;
}

.ui-row-ltr>td.first {
	text-align: center;
	font-weight: bold;
}

.checkbox {
	display: inline-block;
	float: left;
}

.profit td {
	color: #ff0000;
}

.deptchart {
	position: absolute;
	top: 10px;
	left: 0;
	right: 0;
	bottom: 5%;
	width: 1500px;
	height: 400px;
}

.tabbd {
	width: 100%;
	text-align: center;
}

input[name='timeQuantum'] {
	visibility: hidden;
}

input[name='timeQuantum']+label {
	background-color: #fff;
	font-size: 12px;
	padding: 1px 6px 3px 2px;
	text-align: center;
	height: 30px;
	line-height: 30px;
}

input[name='timeQuantum']:checked+label {
	background-color: rgb(98,149,233);
	color: #fff;
	font-size: 12px;
	padding: 1px 6px 3px 2px;
	text-align: center;
	height: 30px;
	line-height: 30px;
	border-radius: 6px;
}
</style>
</head>
<body style="height: 100%; background: #fff">
	<div class="main  choice_box">
		<form id="queryForm">
			<!-- 查询区 表单 -->
			<div class="search border-bottom">
				<ul>
					<li style="width: 200px;"><span>发生年份:</span>
						<div class="time_bg">
							<input id="r_year" type="text" class="search_time150"
								name="r_year"> <i class="search_time_ico1"></i>
						</div></li>
					<li style="width: 600px;"><select multiple
						class="width-100 chosen-select tag-input-style"
						id="form-field-select-4" name="deptname" data-placeholder="选择科目">
							<c:forEach var="subject" items="${subjectList}">
								<option value="${subject.name}"><c:out
										value="${subject.name}"></c:out></option>
							</c:forEach>
					</select></li>
					<!-- 输入框-->
					<li style="width: 400px;"><span>选择指标:</span> <input
						type="checkbox" name="timeQuantum" id="achieve" checked><label
						for="achieve">总业绩</label> <input type="checkbox"
						name="timeQuantum" id="cost" checked> <label for="cost">总成本</label>
						<input type="checkbox" name="timeQuantum" id="profit" checked><label
						for="profit">总利润</label></li>
					<!-- 输入框-->
					<li><input type="reset" class="reset_btn"
						onclick="resetQueryForm()" value="重置"> <!-- 重置 --> <input
						type="button" class="search_btn mr22 " onclick="doSearch();"
						value="查询"></li>
					<!-- 查询-->
				</ul>
			</div>
		</form>
		<div class="listtable_box">
			<!--此处放表格-->
			<table id="remote_rowed" class="ui-jqgrid-htable">
				<thead>
					<tr class="ui-jqgrid-labels">
						<th colspan="2" class="first">科目</th>
						<th>一月</th>
						<th>二月</th>
						<th>三月</th>
						<th>四月</th>
						<th>五月</th>
						<th>六月</th>
						<th>七月</th>
						<th>八月</th>
						<th>九月</th>
						<th>十月</th>
						<th>十一月</th>
						<th>十二月</th>
						<th>总计</th>
					</tr>
				</thead>
				<tbody class="item-tbody">

				</tbody>
			</table>
		</div>
		<!-- tab -->
		<div class="widget-box transparent" id="recent-box">
			<div class="widget-header">
				<div class="widget-toolbar no-border">
					<ul class="nav nav-tabs tabhd" id="recent-tab">

					</ul>
				</div>
			</div>

			<div class="widget-body">
				<div class="widget-main padding-4">
					<div class="tab-content padding-8 overflow-visible tabbd"
						style="margin: 0 auto;">
						<!-- <div id="task-tab" class="tab-pane active tabhd">
						</div> -->
					</div>
				</div>
			</div>
		</div>
		<div></div>
	</div>
	<!-- widgetheader模板 -->
	<script type="text/template" id="widgetheader">
					<li class="{{active}}">
						<a data-toggle="tab" href="{{hrefid}}">{{subject}}</a>
					</li>
    </script>

	<!-- widgetbody模板 -->
	<script type="text/template" id="widgetbody">
					<div id="{{hrefid}}" class="tab-pane {{active}}">
						<div id="{{deptchartid}}" class="deptchart" style=""></div>					
					</div>
    </script>

	<script type="text/template" id="subject-tpl-first">
					<tr class="ui-widget-content jqgrow ui-row-ltr {{class}}">
						<td class="first" style="color:#444444" rowspan={{subsize}}>{{subject}}</td><td>{{classType}}</td>
						<td>{{jan}}</td><td>{{feb}}</td>
						<td>{{mar}}</td><td>{{apr}}</td>
						<td>{{may}}</td><td>{{jun}}</td>
						<td>{{jul}}</td><td>{{aug}}</td>
						<td>{{sep}}</td><td>{{oct}}</td>
						<td>{{nov}}</td><td>{{dec}}</td><td>{{total}}</td>
					</tr>
    </script>
	<script type="text/template" id="subject-tpl">
					<tr class="ui-widget-content jqgrow ui-row-ltr {{class}}">
						<td>{{classType}}</td>
						<td>{{jan}}</td><td>{{feb}}</td>
						<td>{{mar}}</td><td>{{apr}}</td>
						<td>{{may}}</td><td>{{jun}}</td>
						<td>{{jul}}</td><td>{{aug}}</td>
						<td>{{sep}}</td><td>{{oct}}</td>
						<td>{{nov}}</td><td>{{dec}}</td><td>{{total}}</td>
					</tr>
    </script>
	<script type="text/javascript">
    var chartarr = [];//定义存放data数据的对象
	//总的数据对象
	var alldata = [];   
    $(function() {	
    	new biz.datepicker({
	  			id : "#r_year",
	  			maxDate:'%y',
	  			minDate:'2017',
	  			dateFmt:'yyyy'
	  		});
	    	$ .ajax({
				url: "<m:url value='/standard/listFinStatements.do'/>",
				cache:false,
				success: function(data){
					chartarr.push(data.rows);
					drawGrid(data.rows);
				}
			});
	    	//select多选 初始化方法
	    	$(".chosen-select").chosen(); 
    	});
    
    //根据查询条件请求后台查询
    function doSearch(){
    	var paramArray = [];
    	var paramObj = {};
    	var arr = [];
    	paramObj.r_year = $("#r_year").val();
        $(".chosen-choices>.search-choice").each(function(){
        	arr.push($(this).children('span').text())
        })
        paramObj.deptArr=arr.toString();
        paramObj.zyjcolunm = $("#achieve")[0].checked;
        paramObj.zcbcolunm = $("#cost")[0].checked;
        paramArray.push(paramObj);
    	 $.ajax({
             url: "<m:url value='/standard/listFinStatements.do'/>",
             type: 'post',
             dataType:"json",
             data: JSON.stringify(paramArray),
             processData: false,// 告诉jQuery不要去处理发送的数据
             contentType: false, // 告诉jQuery不要去设置Content-Type请求头
             success: function(rs) {
            	 chartarr.push(rs.rows);
            	 drawGrid(rs.rows)
            	
             }
         }); 
    }
    
   	//填充表格
	function drawGrid(data){
		var subArr = [];
		var subNameArr = [];
		var htmlTemp = [];//临时存放html数组
		var tabheaderTemp = [];//临时存放tabheader数组
		var tabbodyTemp = [];//临时存放tabbody数组
    	for(var i = 0;i<data.length;i++){
    		//取到科目,判断科目是否已经拿到
    		//jQuery.inArray(value,array)搜索数组中指定值并返回它的索引（如果没有找到则返回-1）
    		if($.inArray(data[i].j_subject, subNameArr)>-1){
    			//科目存在，则不需要新建科目对象
    			//拿到后台传递的某个对象，判断传递的class类型，将月份数据放在模板对象对应的class类型行
    			//遍历subArr集合，判断后台此时拿到的数据属于哪个subject对象，拿到该subject对象
    			for(var m = 0;m < subArr.length;m++){
					// 获取模板对象subject属性，根据subject属性值获取对象
					var subject = subArr[m].j_subject;
					if(subject == data[i].j_subject){
						var subObj = subArr[m]; //拿到对象
						//对对象进行处理
						//判断数据类型
		    			switch (data[i].classType) {
		    				//新建一个achieve子对象
							case "achieve":
								subObj.achieve = {};
				    			subObj.achieve.classType=data[i].classType;
				    			subObj.achieve.monthData=[data[i].jan,data[i].feb,data[i].mar,data[i].apr,data[i].may,data[i].jun,
				    			                  data[i].jul,data[i].aug,data[i].sep,data[i].oct,data[i].nov,data[i].dec];
								break;
							//新建一个cost子对象
							case "cost":
								subObj.cost = {};
				    			subObj.cost.classType=data[i].classType;
				    			subObj.cost.monthData=[data[i].jan,data[i].feb,data[i].mar,data[i].apr,data[i].may,data[i].jun,
				    			                  data[i].jul,data[i].aug,data[i].sep,data[i].oct,data[i].nov,data[i].dec];
								break;
							default:
								break;
						}
					}
				}
    			
    		}else{//不存在，新建一个科目对象
    			var subObj = {};
    			subObj.j_subject = data[i].j_subject;
    			switch (data[i].classType) {
    				//新建一个achieve子对象
					case "achieve":
						subObj.achieve = {};
		    			subObj.achieve.classType=data[i].classType;
		    			subObj.achieve.monthData=[data[i].jan,data[i].feb,data[i].mar,data[i].apr,data[i].may,data[i].jun,
		    			                  data[i].jul,data[i].aug,data[i].sep,data[i].oct,data[i].nov,data[i].dec];
		    			subArr.push(subObj);
		    			subNameArr.push(data[i].j_subject);
						break;
					//新建一个cost子对象
					case "cost":
						subObj.cost = {};
		    			subObj.cost.classType=data[i].classType;
		    			subObj.cost.monthData=[data[i].jan,data[i].feb,data[i].mar,data[i].apr,data[i].may,data[i].jun,
		    			                  data[i].jul,data[i].aug,data[i].sep,data[i].oct,data[i].nov,data[i].dec];
		    			subArr.push(subObj);
		    			subNameArr.push(data[i].j_subject);
						break;
					default:
						break;
				}
    		}
    	}
		
    	//最后把模板添加到html里面
    	//将该对象模板进行替换
    	for(var i = 0;i<subArr.length;i++){
        	var tpl = '#subject-tpl-first';
    		var achieveTotal = 0,costTotal=0;
    		if(typeof(subArr[i].achieve)!="undefined"){
    			achieveTotal = getTotal(subArr[i].achieve);
    			if($('#achieve')[0].checked){
    				replaceTml(tpl,subArr[i].j_subject,subArr[i].achieve,'业绩',achieveTotal);
    				tpl = '#subject-tpl';
    			}
    		}
    		if(typeof(subArr[i].cost)!="undefined"){
    			costTotal  = getTotal(subArr[i].cost);
    			if($('#cost')[0].checked){
    				replaceTml(tpl,subArr[i].j_subject,subArr[i].cost,'成本',costTotal);
    				tpl = '#subject-tpl';
    			}
    		}
    		if ($('#profit')[0].checked) {
    			var trTpl = $(tpl).html();//获取模板DEWWWW
    		 	htmlTemp.push(trTpl
    		 		   .replace('{{class}}', 'profit')
   				       .replace('{{subject}}', subArr[i].j_subject)
   				       .replace('{{classType}}', '利润')
   				       .replace('{{jan}}', (strToNumber(subArr[i].achieve.monthData[0])-(subArr[i].cost!=undefined?strToNumber(subArr[i].cost.monthData[0]):0)).toFixed(2)||0)
   				       .replace('{{feb}}', (strToNumber(subArr[i].achieve.monthData[1])-(subArr[i].cost!=undefined?strToNumber(subArr[i].cost.monthData[1]):0)).toFixed(2)||0)
   				       .replace('{{mar}}', (strToNumber(subArr[i].achieve.monthData[2])-(subArr[i].cost!=undefined?strToNumber(subArr[i].cost.monthData[2]):0)).toFixed(2)||0)
   				       .replace('{{apr}}', (strToNumber(subArr[i].achieve.monthData[3])-(subArr[i].cost!=undefined?strToNumber(subArr[i].cost.monthData[3]):0)).toFixed(2)||0)
   				       .replace('{{may}}', (strToNumber(subArr[i].achieve.monthData[4])-(subArr[i].cost!=undefined?strToNumber(subArr[i].cost.monthData[4]):0)).toFixed(2)||0)
   				       .replace('{{jun}}', (strToNumber(subArr[i].achieve.monthData[5])-(subArr[i].cost!=undefined?strToNumber(subArr[i].cost.monthData[5]):0)).toFixed(2)||0)
   				       .replace('{{jul}}', (strToNumber(subArr[i].achieve.monthData[6])-(subArr[i].cost!=undefined?strToNumber(subArr[i].cost.monthData[6]):0)).toFixed(2)||0)
   				       .replace('{{aug}}', (strToNumber(subArr[i].achieve.monthData[7])-(subArr[i].cost!=undefined?strToNumber(subArr[i].cost.monthData[7]):0)).toFixed(2)||0)
   				       .replace('{{sep}}', (strToNumber(subArr[i].achieve.monthData[8])-(subArr[i].cost!=undefined?strToNumber(subArr[i].cost.monthData[8]):0)).toFixed(2)||0)
   				       .replace('{{oct}}', (strToNumber(subArr[i].achieve.monthData[9])-(subArr[i].cost!=undefined?strToNumber(subArr[i].cost.monthData[9]):0)).toFixed(2)||0)
   				       .replace('{{nov}}', (strToNumber(subArr[i].achieve.monthData[10])-(subArr[i].cost!=undefined?strToNumber(subArr[i].cost.monthData[10]):0)).toFixed(2)||0)
   				       .replace('{{dec}}', (strToNumber(subArr[i].achieve.monthData[11])-(subArr[i].cost!=undefined?strToNumber(subArr[i].cost.monthData[11]):0)).toFixed(2)||0)
   				       .replace('{{total}}',(parseInt(0+achieveTotal)-parseInt(0+costTotal)).toFixed(2)||0)
	    		);
    		}
    	}
    	
    	$('.item-tbody').html(htmlTemp.join('')); //通过''连接数组元素
    	//获取总值
    	function getTotal(subObj){
    		var total = 0;
    		for(var i = 0;i<subObj.monthData.length;i++){
 			   total  += Number(subObj.monthData[i]);
 		   }
    		return total;
    	}
    	//替换模板
    	function replaceTml(temp,subName,subObj,classType,total){
    		   var trTpl = $(temp).html();//获取模板
    		   htmlTemp.push(trTpl
    				   .replace('{{class}}', '')
    				   .replace('{{subsize}}', $("[name='timeQuantum']:checked").size())
    			       .replace('{{subject}}', subName)
    			       .replace('{{classType}}', classType)
    			       .replace('{{jan}}',  (subObj.monthData[0])||0)
    			       .replace('{{feb}}', (subObj.monthData[1])||0)
    			       .replace('{{mar}}', (subObj.monthData[2])||0)
    			       .replace('{{apr}}', (subObj.monthData[3])||0)
    			       .replace('{{may}}',  (subObj.monthData[4])||0)
    			       .replace('{{jun}}', (subObj.monthData[5])||0)
    			       .replace('{{jul}}', (subObj.monthData[6])||0)
    			       .replace('{{aug}}', (subObj.monthData[7])||0)
    			       .replace('{{sep}}', (subObj.monthData[8])||0)
    			       .replace('{{oct}}', (subObj.monthData[9])||0)
    			       .replace('{{nov}}',  (subObj.monthData[10])||0)
    			       .replace('{{dec}}', (subObj.monthData[11])||0)
    			       .replace('{{total}}',total.toFixed(2))
    				)
    	   }
    	
		function replaceheader(active,hrefid,subject){
			var trTpl = $('#widgetheader').html();//获取模板
    		tabheaderTemp.push(trTpl
    				.replace('{{active}}', active)
 			       .replace('{{hrefid}}', hrefid)
 			       .replace('{{subject}}',subject)
    		)
    	}
		
		function replacebody(active,hrefid,deptchartid){
			var trTpl = $('#widgetbody').html();//获取模板
			tabbodyTemp.push(trTpl
    				.replace('{{active}}', active)
 			       .replace('{{hrefid}}', hrefid)
 			       .replace('{{deptchartid}}', deptchartid)
    		)
    	}
    	//填充表格后转换数据格式
    	dataformat();
    	//遍历每个部门，生成tab的header项
    	for(var i=0;i<alldata.length-1;i++){
    		var active = 'active';
    		if(i>0){
    			active='';
    		}
    		//console.log(alldata.length);
    		//console.log(alldata[i].deptname);
    		replaceheader(active,"#"+alldata[i].deptname.toLowerCase(),alldata[i].deptname);
    		replacebody(active,alldata[i].deptname.toLowerCase(),"m"+alldata[i].deptname.toLowerCase());
    	}
    	$('.tabhd').html(tabheaderTemp.join('')); //插入标签
    	$('.tabbd').html(tabbodyTemp.join('')); //插入标签
    	for(var i=0;i<alldata.length-1;i++){
    		drawChart(alldata[i].deptname.toLowerCase());
    	}
    	alldata=[];//页面显示完成后置为空
    	chartarr=[];
    	}
	//字符串转数字
	function strToNumber(str){
		if(str!="undefined")return parseInt(0+str);
		else{return 0}
	}
   	//数据格式转换
   	function dataformat(){
		//定义存放部门的数组
		var deptname = [] ;
		var names = [];
		//先判断结果集是否存在，通过遍历结果集，分别获得月份名、实收报名费、总业绩、应收学费
		//console.log(chartarr);
		if(chartarr.length>0){
			for(var key in chartarr[0]){//遍历data数据放入names 
				names.push(key);
				//console.log(names);
			}
/* 			names.sort(function compare(v1,v2){//对names进行key值排序
				return  v1.split("-")[0]==v2.split("-")[0]?v1.split("-")[1]-v2.split("-")[1]:v1.split("-")[0]>v2.split("-")[0];
			}); */
			var name;
			//存放部门,把后台传回的每一条数据中的部门信息存入deptname
			for(var j=0;j<names.length;j++){    
				name = chartarr[0][names[j]];
				deptname.push(name.j_subject);
				j++;//每个部门有两条数据，所以+1跳过一行
			}
			//遍历部门对象deptname
			for(var i=0;i<deptname.length;i++){
				var adnext = new Object();//要存入alldata的对象
				var monthmoney = [];
				var jan = new Object();
				var feb = new Object();
				var mar = new Object();
				var apr = new Object();
				var may = new Object();
				var jun = new Object();
				var jul = new Object();
				var aug = new Object();
				var sep = new Object();
				var oct = new Object();
				var nov = new Object();
				var dec = new Object();
				adnext.deptname = deptname[i];
				for(var j=0;j<names.length;j++){//遍历每 一条数据 
					name = chartarr[0][names[j]];
					if(adnext.deptname==name.j_subject){//找到当前部门的总业绩数据，achieve和cost数据
						for(var m=0;m<12;m++){
							if(name.classType=='achieve'){//总业绩数据
								switch(m)
								{
								case 0:
									jan.zyj = name.jan;
								  	  break;
								case 1:
									feb.zyj = name.feb;
								  	  break;
								case 2:
									mar.zyj = name.mar;
									  break;
								case 3:
									apr.zyj = name.apr;
									  break;
								case 4:
									may.zyj = name.may;
									  break;
								case 5:
									jun.zyj = name.jun;
									  break;
								case 6:
									jul.zyj = name.jul;
									  break;
								case 7:
									aug.zyj = name.aug;
									  break;
								case 8:
									sep.zyj = name.sep;
									  break;
								case 9:
									oct.zyj = name.oct;
									  break;
								case 10:
									nov.zyj = name.nov;
									  break;
								default:
									dec.zyj = name.dec;
								}
							}else{//总成本数据
								switch(m)
								{
								case 0:
									jan.zcb = name.jan;
								  	  break;
								case 1:
									feb.zcb = name.feb;
								  	  break;
								case 2:
									mar.zcb = name.mar;
									  break;
								case 3:
									apr.zcb = name.apr;
									  break;
								case 4:
									may.zcb = name.may;
									  break;
								case 5:
									jun.zcb = name.jun;
									  break;
								case 6:
									jul.zcb = name.jul;
									  break;
								case 7:
									aug.zcb = name.aug;
									  break;
								case 8:
									sep.zcb = name.sep;
									  break;
								case 9:
									oct.zcb = name.oct;
									  break;
								case 10:
									nov.zcb = name.nov;
									  break;
								default:
									dec.zcb = name.dec;
								}
							}	
							
						}
						
					}
				}
				monthmoney.push(jan);
				monthmoney.push(feb);
				monthmoney.push(mar);
				monthmoney.push(apr);
				monthmoney.push(may);
				monthmoney.push(jun);
				monthmoney.push(jul);
				monthmoney.push(aug);
				monthmoney.push(sep);
				monthmoney.push(oct);
				monthmoney.push(nov);
				monthmoney.push(dec);
				adnext.modata = monthmoney;
				alldata.push(adnext);
			}
		}
   	}
   	
	//画图表
	function drawChart(chartid){
		var names = [];
		var zyj = []
		var zcb = [];
		var profit = [];
		for(var o=0;o<alldata.length-1;o++){
			if(alldata[o].deptname.toLowerCase()==chartid){
				for(var key in alldata[o].modata){//遍历data数据放入names 
					names.push(key);//names存放的是key值 
				}
				var name;
				for(var j=0;j<names.length;j++){    
					name = alldata[o].modata[names[j]];
					if(name){
						zyj.push(Math.round(name.zyj)||0);
						zcb.push(Math.round(name.zcb)||0);
						profit.push((name.zyj-name.zcb).toFixed(2)||0);
					}else{
						zyj.push(0);zcb.push(0);profit.push(0);
					}
		        }
			}
			 
		}
		var did = "m"+chartid;
		var mainChart = echarts.init(document.getElementById(did.toLowerCase()));
		mainChart.showLoading();
		$(window).on('resize',function(){//大小自适应
			mainChart.resize();
		});
		var option = {
			tooltip: {
				show:true,
		        trigger: 'axis'
		    },
		     toolbox: {
		        feature: {
		            dataView: {show: true, readOnly: true,title:'数据视图'},//右侧小图标
		            magicType: {show: true, type: ['line', 'bar']},
		            saveAsImage: {show: true}//保存为图片
		        }
		    },
			title: {
	            text: ''
	        },
	        legend: {
	            data:['总业绩','总成本','总利润']//更换成指标项 ：总业绩、总成本，总利润
	        },
	        //x坐标
	        xAxis: [
	        	{
	   				type: 'category', //坐标轴类型
	   				axisLabel:{
	   					interval:0,
	        			show:true
	        		},
	                data:[ "一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]//更换成动态数据
				}
	        ],
	        //y坐标
	        yAxis: {
	            type: 'value',
	            name: '金额',
	            min: 0,
	            axisLabel: {
	                formatter: '$ {value}'
	            }
	        },
			series: [
				{
				    name: '总业绩',
				    type: 'bar',
				    data: zyj //更换成动态数据
					},
				{
				    name:'总成本',
				    type:'bar',
				    data:zcb //更换成动态数据
				},
				{
				    name:'总利润',
				    type:'bar',
				    data:profit //更换成动态数据
				}
		    ],
		    color:['#f68484',  '#777', '#ae91e1'],
		}
		mainChart.setOption(option);
		mainChart.hideLoading();
		window.onresize=mainChart.resize;
		}
   	
    	//重置查询条件
	   function resetQueryForm(){
		   resetForm('queryForm');
		   $(".chosen-container .chosen-choices li.search-choice .search-choice-close","#queryForm").trigger("click");//触发被选元素的指定事件类型。
	   }

	//根据条件从数据库获取结果集
	function ajaxGetStatistic(){
			var paramDatas = {months:$("#monthChoose").val(),subjectName:encodeURI($("#subjectName").val())};
	       	$.ajax({
	   			url : "<m:url value='/standard/getBarStatistic.do'/>",
	   			cache : false,
	   			data: paramDatas,
	   			async : false,
	   			dataType:"json",
	   			success : function(data) {
	   				arr.push(data);
	   			}
	   		});
    };
   
    </script>
</body>
</html>
