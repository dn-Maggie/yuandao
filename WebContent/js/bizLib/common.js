// 公共js
String.prototype.trim = function() {// 删除左右两端的空格
	return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
};
// 解决浏览器缓存
function timestamp(url) {
	// var getTimestamp=Math.random();
	var getTimestamp = new Date().getTime();
	if (url.indexOf("?") > -1) {
		url = url + "&timestamp=" + getTimestamp
	} else {
		url = url + "?timestamp=" + getTimestamp
	}
	return url;
}
var ICSS = ICSS || {
	version : "1.0.0"
};

// 页面常量定义
var gridHeight = "360";
var book_split_char = "+;";
var book_split_dot = ".;";

/**
 * 检查是否带有中文及全角字符
 * 
 * @param str :
 *            需要检查的字符串
 * @returns
 */
function checkSBC(str) {
	var re = /[^\x00-\xff]/g;// ASCII 编码不在0-255的字符,即非中文及全角字符
	return re.test(str);
}

// 初始化
ICSS.init = function() {

};

ICSS.init();

ICSS.utils = {
	/**
	 * 返回Dialog的窗口ID内容
	 * 
	 * @param _v :
	 *            需要获取的具体列的字段名
	 * @returns
	 */
	getIdContent : function(divId, iframeIdName, url) {
		return $('<div id="' + divId + '"></div>')
				.html('<iframe id="'
						+ iframeIdName
						+ '" name="'
						+ iframeIdName
						+ '" src="'
						+ url
						+ '" width="100%" frameborder="no" border="0" height="97%"></iframe>');
	},

	/**
	 * 获取选中行的某列数据
	 * 
	 * @param _v :
	 *            需要获取的具体列的字段名
	 * @returns
	 */
	getSelectRowData : function(_v) {
		var arr = new Array();
		var slerows = gridObj.getGridParam('selarrrow');
		for (var i = 0; i < slerows.length; i++) {
			var row = gridObj.getRowData(slerows[i]);
			arr.push(row[_v]);
		}
		return arr.join(",");
	},

	/**
	 * 功能：位grid添加查询条件
	 * 
	 * @param obj
	 * @param name
	 */
	addAttrToObject : function(obj, name) {
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
		if (element.className == "Wdate") {
			if (value != null && "" != value) {
				if (element.name.toLowerCase().indexOf("end") > -1) {
					value = value + ' 23:59:59';
				} else {
					value = value + ' 00:00:00';
				}
			}
		}
		obj[name] = value;
	},

	/**
	 * 动态创建input
	 * 
	 * @param _n:input的名称
	 * @param _v：input的value
	 * @returns
	 */
	createInput : function(_n, _v) {
		var inp = $("<input type=\"text\" name=\"" + _n + "\" value=\"" + _v
				+ "\"/>");
		return inp;
	},

	/**
	 * 显示后台错误信息，屏蔽r1系统自带的XMLHttpRequest展示
	 */
	showErrorMessage : function(xhr, error, thrown) {
		var $html = xhr.responseText ? xhr.responseText : I18N.ajax_error
				+ I18N.ajax_readyState[xhr.readyState];
		if (xhr.readyState == 0 || xhr.readyState == 4) {
			return;
		}

		// 505,自定义session失效或被踢下线状态编码
		if (xhr.status == 505) {
			var sessionstatus = xhr.getResponseHeader("sessionstatus");
			if (sessionstatus == "timeout") {
				// 跳转的登录页面
				window.top.sessionOut("timeout");
			}
			if (sessionstatus == "userDown") {
				window.top.sessionOut("userDown");
			}
		} else {
			if (xhr.status == 506) {
				alert(xhr.responseText);
				$html = "重复操作导致Token失效，请刷新页面！";
			}
			// 错误信息以弹窗口方式显示
			var errorDialog = new biz.dialog({
						id : $("<div>" + $html + "</div>"),
						modal : false,
						width : 600,
						height : 300,
						title : $.jgrid.errors.server,
						close : function(event, ui) {
							errorDialog.destroy();
						}
					});
			errorDialog.open();
		}
	},

	/**
	 * 手动解决r1时间格式化问题，截取每列值，返回yyyy-mm-dd格式数据
	 * 
	 * @param cellvalue
	 * @param options
	 * @param rowObject
	 * @returns
	 */
	dateFormat : function(cellvalue, options, rowObject) {
		if (cellvalue) {
			var _v = cellvalue.substring(0, 10);
			return _v;
		} else {
			return "";
		}

	},

	/**
	 * 设置页面Token值
	 * 
	 * @param tokenCacheKey
	 *            Token缓存的Key值，对应icss.token.name值
	 * @param tokenValue
	 *            Token值
	 * @returns
	 */
	setToken : function(xhr, status) {
		var d = $.parseJSON(xhr.responseText);
		if (d != undefined && d.token != undefined) {
			// console.info("response token key:"+d.token.key+" token
			// value:"+d.token.value);
			var token = $("input[type=hidden][name='icss.token.name']");
			// var parentElem = token.parent('form');
			// console.info(token.length+" token.val:"+token.val());
			if (token.length > 0) {
				var tokeyValueObj = $("input.icssTokenValue");
				tokeyValueObj.attr("name", d.token.key);
				tokeyValueObj.val(d.token.value);
				token.val(d.token.key);
			}
			// console.info("page token
			// key:"+$("input[type=hidden][name='icss.token.name']").val()+"
			// token value:"+$("input.icssTokenValue").val());
		}
	},
	getCookieGridRowNum : function(defRowNum) {
		if ($.cookie(window.location.href)) {
			return $.cookie(window.location.href);
		}
		return defRowNum;
	},
	setCookieGridRowNum : function(pgButton) {
		if (pgButton == 'records') {
			$.cookie(window.location.href, $('.ui-pg-selbox-normal').val());
		}
	}

};

// 处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
function forbidBackSpace(e) {
	var ev = e || window.event; // 获取event对象
	var obj = ev.target || ev.srcElement; // 获取事件源
	var t = obj.type || obj.getAttribute('type'); // 获取事件源类型
	// 获取作为判断条件的事件类型
	var vReadOnly = obj.readOnly;
	var vDisabled = obj.disabled;
	// 处理undefined值情况
	vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
	vDisabled = (vDisabled == undefined) ? true : vDisabled;
	// 当敲Backspace键时，事件源类型为密码或单行、多行文本的，
	// 并且readOnly属性为true或disabled属性为true的，则退格键失效
	var flag1 = ev.keyCode == 8
			&& (t == "password" || t == "text" || t == "textarea")
			&& (vReadOnly == true || vDisabled == true);
	// 当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
	var flag2 = ev.keyCode == 8 && t != "password" && t != "text"
			&& t != "textarea";
	// 判断
	if (flag2 || flag1)
		return false;
}
// 禁止后退键 作用于Firefox、Opera
document.onkeypress = forbidBackSpace;
// 禁止后退键 作用于IE、Chrome
document.onkeydown = forbidBackSpace;

// 通用部分
function showMessage(msg, title, timeout, callback) {
	// jAlert(msg,title,callback);
	new biz.alert({
				type : "alert",
				message : msg,
				title : I18N.promp,
				callback : callback
			});
	// jHide(timeout);
	new biz.alert({
				type : "hide",
				times : timeout
			});
}

// show common message
function showInfo(msg, timeout, callback) {
	// jAlert(msg,I18N.promp,callback);
	new biz.alert({
				type : "alert",
				message : msg,
				title : I18N.promp,
				callback : callback
			});
	new biz.alert({
				type : "hide",
				times : timeout
			});
}

// show error message
function showError(msg, timeout, callback) {
	// jAlert(msg,I18N.error,callback);
	new biz.alert({
				type : "alert",
				message : msg,
				title : I18N.error,
				callback : callback
			});
	new biz.alert({
				type : "hide",
				times : timeout
			});
}

// show warn message
function showWarn(msg, timeout, callback) {
	// jAlert(msg,I18N.warn,callback);
	new biz.alert({
				type : "alert",
				message : msg,
				title : I18N.warn,
				callback : callback
			});
	new biz.alert({
				type : "hide",
				times : timeout
			});
}

// 遍历obj
function subObj(obj) {
	$.each(obj, function(key, val) {
				if ($.isPlainObject(val) || $.isArray(val)) {
					subObj(val);
				} else {
					alert(key + '=' + val);
				}
			});
}

// 设置定部人员信息等
function setUser(str) {
	// var sub = parent.frames["top1"];
	// 在另一个iframe中获取文本框的值
	// $(sub.document.getElementById("dquser")).html(str);
	alert(str);
	$("#dquser").html(str);
}

// 设置定部人员信息等
function setUrlPath(str) {
	// 在另一个iframe中设置Span的值
	// $(parent.frames["top1"].document.getElementById("urlPath")).html(str);
	$("#urlPath").html(str);
}

// 验证input框，
function val_inpt(id, name, max, callBack) {
	var _v = $("#" + id).val();
	var msg = "";
	if ($.trim(_v) == "") {
		msg = "请填写" + name;
		showError(msg);
		return false;
	} else {
		if (max != "") {
			if (_v.length > max) {
				msg = name + "内容不能超过" + max + "字";
				showError(msg);
				return false;
			}
		}
		if (typeof callBack == "function") {
			return callBack.call(this, id, name);
		}
	}
	return true;
}

// 验证下拉框，
function val_sels(id, name) {
	var _v = $("#" + id).val();
	if (_v == "") {
		var msg = "请选择" + name;
		showError(msg);
		return false;
	}
	return true;
}

// 验证数字
function valInt(id, name) {
	var _v = $("#" + id).val();
	if (isNaN(_v)) {
		var msg = name + "必须为数字";
		showError(msg);
		return false;
	}
	return true;
}

function format_isClose(cellvalue, options, rowObject) {
	if (cellvalue == '1') {
		return "未关闭";
	} else if (cellvalue == '0') {
		return "已关闭";
	}
}

// 重置查询表单
function resetForm(formId) {
	document.getElementById(formId).reset();
}

/**
 * 全局的ajax请求，用于判断当前ajax请求时会话(session)的状态(是否失效)
 * 
 * @param sessionstatus
 *            session状态
 * @return 值为timeout表示session失效，值为userDown表示用户被踢下线
 * 
 */
$.ajaxSetup({
	contentType : "application/x-www-form-urlencoded;charset=utf-8",
	/*
	 * beforeSend:function(xhr, status){ //请求发送前触发 },
	 */
	complete : ICSS.utils.setToken,
	error : ICSS.utils.showErrorMessage
		// function(XMLHttpRequest, textStatus, errorThrown){
		// //505,自定义session失效或被踢下线状态编码
		// if(XMLHttpRequest.status == 505){
		// var sessionstatus =
		// XMLHttpRequest.getResponseHeader("sessionstatus");
		// if(sessionstatus=="timeout"){
		// //跳转的登录页面
		// window.top.sessionOut("timeout");
		// }
		// if(sessionstatus == "userDown"){
		// window.top.sessionOut("userDown");
		// }
		// }else{
		// ICSS.utils.showErrorMessage;
		// }
		// }
	});

function pasreEnDate(dateStr, forwardDate) {
	try {
		if (dateStr && dateStr.trim().length != 7) {
			return;
		}
		var dd = dateStr.substring(0, 2);
		var mm = dateStr.substring(2, 5);
		var yy = dateStr.substring(5, 7);
		mm = mm.toUpperCase();
		var em = new Array("JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL",
				"AUG", "SEP", "OCT", "NOV", "DEC");
		switch (mm) {
			case em[0] :
				mm = 1;
				break;
			case em[1] :
				mm = 2;
				break;
			case em[2] :
				mm = 3;
				break;
			case em[3] :
				mm = 4;
				break;
			case em[4] :
				mm = 5;
				break;
			case em[5] :
				mm = 6;
				break;
			case em[6] :
				mm = 7;
				break;
			case em[7] :
				mm = 8;
				break;
			case em[8] :
				mm = 9;
				break;
			case em[9] :
				mm = 10;
				break;
			case em[10] :
				mm = 11;
				break;
			case em[11] :
				mm = 12;
				break;
		}
		var now = new Date();
		var year = now.getFullYear();
		if (yy.length == 2) {
			// 指定为10进制否则出问题
			yy = parseInt(yy, 10);
			if (forwardDate) {
				// 只是当前日期以后的日期
				yy = 2000 + yy;
			} else {
				// 如出生日期
				var miny = year - (2000 + yy);
				var maxy = year - (1900 + yy);
				if (miny > 0 || maxy < 100) {
					yy = 1900 + yy;
				} else {
					yy = 2000 + yy;
				}
			}
		}
		var nd = mm + "/" + dd + "/" + yy;
		var date2 = new Date(nd);
		return myGetDateText(date2);
	} catch (e) {
		return "";
	}
}

// 将日期转化为2010-04-09格式的字符串
function myGetDateText(date1) {
	var dateStr = "";
	if (date1) {
		dateStr = date1.getFullYear();
		var month = date1.getMonth() + 1;
		var day = date1.getDate();
		if (month < 10) {
			dateStr += "-0" + month;
		} else {
			dateStr += "-" + month;
		}
		if (day < 10) {
			dateStr += "-0" + day;
		} else {
			dateStr += "-" + day;
		}
	}
	return dateStr;
}

function dateCharLeft0(n) {
	if (n >= 0 && n <= 9) {
		return "0" + n;
	} else {
		return n;
	}
}

/**
 * 获取当前的日期时间 格式“yyyy-MM-dd HH:MM:SS”
 * 
 * @return {}
 */
function getNowFormatDate() {
	var date = new Date();
	var seperator1 = "-";
	var seperator2 = ":";
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = date.getFullYear() + seperator1 + month + seperator1
			+ strDate + " " + dateCharLeft0(date.getHours()) + seperator2
			+ dateCharLeft0(date.getMinutes()) + seperator2
			+ dateCharLeft0(date.getSeconds());
	return currentdate;
}

function addAttrToObject(obj, name) {
	element = document.getElementById(name);
	var notInputValue = "";
	if (element != null) {
		if (element.value == null || element.value == "") {
			if ($(element).attr("uiType") != null) {
				if ($(element).attr("uiType") == "checkbox") {
					var tempCheckBoxObj = $(element).find(".checkbox");
					if (tempCheckBoxObj != null && tempCheckBoxObj.length > 0) {
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
	obj[name] = value;
}

/**
 * 创建上次控件
 * 
 * @param eleId
 * @param url
 * @returns
 */
function createupload(eleId, url) {
	return $(eleId).Huploadify({
				auto : true,
				fileTypeExts : '*.*',
				multi : true,
				// formData:{key:123456,key2:'vvvv'},
				fileSizeLimit : 102400,
				showUploadedPercent : true,
				showUploadedSize : true,
				removeTimeout : 9999999,
				uploader : url,
				onUploadStart : function(file) {
					console.log(file.name + '开始上传');
				},
				onInit : function(obj) {
					console.log('初始化');
					console.log(obj);
				},
				onUploadComplete : function(file) {
					console.log(file.name + '上传完成');
				},
				onCancel : function(file) {
					console.log(file.name + '删除成功');
				},
				onClearQueue : function(queueItemCount) {
					console.log('有' + queueItemCount + '个文件被删除了');
				},
				onDestroy : function() {
					console.log('destroyed!');
				},
				onSelect : function(file) {
					console.log(file.name + '加入上传队列');
				},
				onQueueComplete : function(queueData) {
					console.log('队列中的文件全部上传完成', queueData);
					up.cancel('*');
				},
				onUploadSuccess : function(file, data, response) {
					console.log('服务器返回数据', data);
					data = jQuery.parseJSON(data);
					$("#planfj_list").append(item);

				}
			});
}

// 选择用户的公用窗子
var selectUserWin, selectUserWin_target;

function selectUserWinOpen(target_win) {
	var url = baseUrl + '/userInfo/toListDlg.do';
	selectUserWin = new biz.dialog({
		id : $('<div id="iframe_couponChoose"></div>')
				.html('<iframe id="iframeShow" name="iframeShow" src="'
						+ url
						+ '" width="100%" frameborder="no" border="0" height="97%"></iframe>'),
		modal : true,
		width : 800,
		height : 500,
		title : "人员选择"
	});
	selectUserWin_target = target_win;
	selectUserWin.open();
}
// 关闭选择用户的公用窗子
function selectUserWinClose() {
	selectUserWin.destroy();
}

function selectUserWinFill(rowArr) {
	selectUserWin_target.fillSelectUser(rowArr);
	selectUserWin.destroy();
}

function loadingMask(wrap) {
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
				.html('<img class="mask-wrap-msg-img" src="' + baseUrl
						+ '/styles/images/jiazai.gif" /><span>请稍候</span>')));
	}
}

function loadedMask(wrap) {
	if (wrap) {// remove
		$(wrap).find("div.mask-wrap").hide();
	} else {
		$(document.body).find("div.mask-wrap").hide();
	}
}