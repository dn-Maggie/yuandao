/**
 * 字符串方法扩展
 */
String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
	if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
		return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);
	} else {
		return this.replace(reallyDo, replaceWith);
	}
};

/**
 * 身份证号码验证
 */
String.prototype.isIDCard = function () {
    var _i = /^[1-6]\d{5}(19|20)?\d{2}(0[1-9]|1[0-2])(0[1-9]|1\d|2\d|3[01])\d{3}[\dXx]?$/;
    var _s = this;
    if (_s.length == 18 && _i.test(_s))
    	return  isIDNumberParityValid(_s);
    else
    	return _s.length == 15 && _i.test(_s);
};

if (!Array.prototype.indexOf)
{
  Array.prototype.indexOf = function(elt /* , from */)
  {
    var len = this.length >>> 0;
    var from = Number(arguments[1]) || 0;
    from = (from < 0)
         ? Math.ceil(from)
         : Math.floor(from);
    if (from < 0)
      from += len;
    for (; from < len; from++)
    {
      if (from in this &&
          this[from] === elt)
        return from;
    }
    return -1;
  };
}

/**
 * 格式化日期
 */
Date.prototype.format = function(fmt)   
{ 
var o = {   
 "M+" : this.getMonth()+1,                 // 月份
 "d+" : this.getDate(),                    // 日
 "h+" : this.getHours(),                   // 小时
 "m+" : this.getMinutes(),                 // 分
 "s+" : this.getSeconds(),                 // 秒
 "q+" : Math.floor((this.getMonth()+3)/3), // 季度
 "S"  : this.getMilliseconds()             // 毫秒
};   
if(/(y+)/.test(fmt))   
 fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
for(var k in o)   
 if(new RegExp("("+ k +")").test(fmt))   
fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
return fmt;   
}  

/**
 * 移除
 */
Array.prototype.remove = function(b) { 
	var a = this.indexOf(b); 
	if (a >= 0) { 
		this.splice(a, 1); 
		return true; 
	} 
	return false; 
}; 

/**
 * 检查身份证号码校验位
 */
function isIDNumberParityValid(idNumber)
{
    var weight = new Array( 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 );
    var parity = "10X98765432";
    var s17 = idNumber.substr(0, 17);
    var s = 0;

    for (var i = 0; i < s17.length; i++) {
        s += parseInt(s17.substr(i, 1)) * weight[i];
    }

    return parity[s % 11] == idNumber.substr(17, 1).toUpperCase();
}

/**
 * 日期工具
 */
var DateUtils = {
	now : function(){
		return  new Date();
	},
	currYear : function(){
		return DateUtils.now().getFullYear();
	},
	currMonth : function(){
		return DateUtils.now().getMonth()+1;
	}
};

/**
 * 时间工具
 */
var TimeUtils = {
	/**
	 * 倒计时
	 * @param selector
	 * @param second
	 * @param timeout
	 */
	countDown : function(selector,second,timeout){
		var $time = $(selector);
		var id = window.setInterval(function(){
			second--;
			if(second===0){//倒计时完成
				window.clearInterval(id);
				if(timeout){
					timeout();
				}
			}else if(second<=60){
				$time.text(second);
			}else{
				$time.text(Math.floor(second/60)+":"+second%60);
			}
		},1000);
	}
};

/**
 * 生成工具,生成密码GenUtils.genPassword(5, '',true, true , true , false, true,true,true, false)
 */
var GenUtils = {
	getRandomNum : function(lbound, ubound){
		return (Math.floor(Math.random() * (ubound - lbound)) + lbound);
	},
	getRandomChar : function(number, lower, upper, other, extra){
		var numberChars = "0123456789";
		var lowerChars = "abcdefghijklmnopqrstuvwxyz";
		var upperChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		var otherChars = "`~!@#$%^&*()-_=+[{]}\\|;:'\",<.>/? ";
		var charSet = extra;
		if (number === true)
			charSet += numberChars;
		if (lower === true)
			charSet += lowerChars;
		if (upper === true)
			charSet += upperChars;
		if (other === true)
			charSet += otherChars;
		return charSet.charAt(GenUtils.getRandomNum(0, charSet.length));
	},
	genPassword : function(length, extraChars, firstNumber, firstLower, firstUpper, firstOther,
								latterNumber, latterLower, latterUpper, latterOther){
		var rc = "";
		if (length > 0)
			rc = rc + GenUtils.getRandomChar(firstNumber, firstLower, firstUpper, firstOther, extraChars);
			for (var idx = 1; idx < length; ++idx) {
			rc = rc + GenUtils.getRandomChar(latterNumber, latterLower, latterUpper, latterOther, extraChars);
		}
		return rc;
	}
};


/**
 * 验证表单
 * @author yangfan 2016-01-06
 * @param params
 * @return
 */
function validateFormDis(){
	
	$("#bankDimens li a span").click(function(){
		var $this = $(this);
		if($this.hasClass("checked")){
			var flag = false;
			$("#mainForm").find("input[type='text']").each(function(){
				if($(this).val()){
					flag = true;
					return false
				}
			});
			if(flag == false){
				$("#filtrate").attr("disabled","true");
			}
		}else{
			$("#filtrate").removeAttr("disabled");
		}
	});
	
	$("#mainForm").find("input[type='text']").keyup(function(){
		var flag = false;
		$("#mainForm").find("input[type='text']").each(function(){
			if($(this).val()){
				flag = true;
				return false
			}
		});
		
		if(flag == true){
			$("#filtrate").removeAttr("disabled");
		}else{
			if(!$("#bankDimens li a span").hasClass("checked")){
				$("#filtrate").attr("disabled","true");
			}else{
				$("#filtrate").removeAttr("disabled");
			}
		}
	});
	
	$("div.datetimepicker table tr td").click(function(){
		var _this = $(this);
		window.setTimeout(function(){
			if(_this.hasClass("active")){
				$("#filtrate").removeAttr("disabled");
			}
		},500);
	});
}

/**
 * 转换金额显示方式，加千分位符
 */
function formateText(selector){
	selector = selector||document.body;
	$(".money",$(selector)).each(function() {
        $(this).text(addThousandChar($(this).text()));
    });
	$(".zerobymoney",$(selector)).each(function() {
		if(parseInt(addThousandChar($(this).text()))==0){
			 $(this).text("");
		}else{
			 $(this).text(addThousandChar($(this).text()));
		}     
    });
	
	// 转换百分比格式
	$(".percent",$(selector)).each(function() {
        $(this).text(addThousandChar(formatePercent($(this).text())));
    });
	$(".datetime",$(selector)).each(function() {
        $(this).html(formateDatetime(parseInt($(this).text()),$(this).attr("format")));
    });
}

/**
 * 日期选择控件
 * @author yangfan 2016-01-06
 * @param params
 * @return
 */

// 日期选择控件
function initDatetimePicker() {
    $('.form-date').each(function(){
    	$(this).datetimepicker({
            language: 'zh-CN',
            format: "yyyy-mm-dd",
            weekStart: 1,
            todayBtn: 0,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            startDate: ($(this).attr("allow_before")?null:new Date()),
            forceParse: 0
        }).on('changeDate', function(ev){
        	var $t = $($(ev.target).attr("date-relative"));
        	if($t.size()>0){
        		$t.datetimepicker('setStartDate', ev.date);
        		if(ev.date>$t.datetimepicker('getDate')){
        			$('input',$t).val("");
        		}
        	}
        });
    });
}

/**
 * 格式化金额
 * @author yangfan 2016-01-06
 * @param params
 * @return
 */
function formatMoney (number, simple) {
	var num = parseFloat(number + "");
	var unit = "元";
	if (simple) {
		if (num >= 100000000) {
			num = Math.round(num / 1000000) / 100;
			unit = "亿";
		}
		else if (num >= 10000000) {
			num = Math.round(num / 100000) / 100;
			unit = "千万";
		}
		else if (num >= 10000) {
			num = Math.round(num / 100) / 100;
			unit = "万";
		}
	}
	
	num = addThousandChar(num);
	var obj = { number : num,  unit : unit };
	return obj;
}

/**
 * 格式化百分比
 * @author yangfan 2016-01-06
 * @param params
 * @return
 */
function formatePercent(number) {
	if(number==="" || isNaN(number))return number;
	var num = parseFloat(number + "") * 100;
	return Math.round(num*100)/100;
}

/**
 * 格式化时间戳
 * @author yangfan 2016-01-06
 * @param params
 * @return
 */
function formateDatetime(time,format) {
	var d = new Date(time);
	return format.replace("yyyy",d.getFullYear()).replace("MM",(d.getMonth()+1)<10?"0"+(d.getMonth()+1):(d.getMonth()+1)).replace("dd",d.getDate()<10?"0"+d.getDate():d.getDate()).replace("HH",d.getHours()).replace("mm",d.getMinutes()).replace("ss",d.getSeconds());
}

/**
 * 数字千分位处理(保留两位小数)
 * @author yangfan 2016-01-06
 * @param params
 * @return
 */
function addThousandChar(number, divide) {
	if (isNaN(number))
		return number;
	
	var num = String(number).replace(new RegExp("[, ]+","g"),"");
	
	if (divide && !isNaN(divide)) {
    	num = Number(num) / parseFloat(divide);
    }
    else {
    	num = parseFloat(num);
    }
    
    num = String(num.toFixed(2));
    
    // 正负号处理
    var symble = "";
    if(/^([-+]).*$/.test(num)) {
        symble = num.replace(/^([-+]).*$/,"$1");
        num = num.replace(/^([-+])(.*)$/,"$2");
    }

    if(/^[0-9]+(\.[0-9]+)?$/.test(num)) {
        if(/^\./.test(num)) {
        	num = "0" + num;
        }

        var decimal = num.replace(/^[0-9]+(\.[0-9]+)?$/,"$1");
        var integer= num.replace(/^([0-9]+)(\.[0-9]+)?$/,"$1");

        var re=/(\d+)(\d{3})/;

        while(re.test(integer)){
            integer = integer.replace(re, "$1,$2");
        }
        number = symble + integer + decimal;
    }
    
    return number;
}

/**
 * 金额小写转大写
 * @author yangfan 2016-01-06
 * @param params
 * @return
 */
function chineseMoney(money)
{
    if (isNaN(money) || money > Math.pow(10, 12))
        return "";
    var cn = "零壹贰叁肆伍陆柒捌玖";
    var unit = new Array("拾百千", "分角");
    var unit1 = new Array("万亿", "");
    var numArray = money.toString().split(".");
    var start = new Array(numArray[0].length - 1, 2);

    function toChinese(num, index)
    {
        num = num.replace(/\d/g, function($1)
        {
            return cn.charAt($1) + unit[index].charAt(start-- % 4 ? start % 4 : -1);
        });
        return num;
    }

    for (var i = 0; i < numArray.length; i++)
    {
        var tmp = "";
        for (var j = 0; j * 4 < numArray[i].length; j++)
        {
            var strIndex = numArray[i].length - (j + 1) * 4;
            var str = numArray[i].substring(strIndex, strIndex + 4);
            start = i ? 2 : str.length - 1;
            var tmp1 = toChinese(str, i);
            tmp1 = tmp1.replace(/(零.)+/g, "零").replace(/零+$/, "");
            tmp1 = tmp1.replace(/^壹拾/, "拾");
            tmp = (tmp1 + unit1[i].charAt(j - 1)) + tmp;
        }
        numArray[i] = tmp;
    }

    numArray[1] = numArray[1] ? numArray[1] : "";
    numArray[0] = numArray[0] ? numArray[0] + "元" : (numArray[0], numArray[1] = numArray[1].replace(/^零+/, ""));
    numArray[0] = numArray[0].match(/亿万/) ? numArray[0].replace("亿万","亿") : numArray[0];
    numArray[1] = numArray[1].match(/分/) ? numArray[1] : numArray[1] + "整";
    return numArray[0] + numArray[1];
}

/**
 * 密码强度检查
 * @author yangfan 2016-01-06
 * @param params
 * @return
 */
function checkPassword(value) {
	    var lv = 0;
	    if (value.length == 0)
	    	return true;
	    if(value.match(/[a-z]/g)){lv++;}
	    if(value.match(/[0-9]/g)){lv++;}
	    if(value.match(/(.[^a-z0-9])/g)){lv++;}
	    if(value.length < 6){ lv = 0;}
	    return lv >= 2;
}