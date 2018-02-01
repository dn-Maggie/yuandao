/**
 *@class I18N
 *description:js multi language class,should be loaded before others,singleton.
 *author:ganjp
 *date:2010-8-16
 */
var I18N = null;
if(!I18N){
I18N = {
	save: "保存",
	reset: "重置",
	close: "关闭",
	confirm: "确定",
	cancel: "取消",
	yes: "是",
	no: "否",
	refresh: "刷新",
	loading: "请稍等...",
	promp: "提示",
	error: "错误",
	warn:  "警告",
	add: "增加",
	edit: "编辑",
	del: "删除",
	search: "查询",
	choose: "请选择...",
	
	msg_del_sucess: "成功删除所选的数据!",
	msg_del_confirm: "确认要删除数据吗?",
	msg_no_sel_del_record: "请先选择要删除的行!",
	msg_no_sel_edit_record: "请先选择要编辑的行!",
	msg_no_sel_view_record: "请先选择要查看的行!",
	msg_single_edit_record: "编辑时只能单选!",
	msg_single_view_record: "查看时只能单选!",
	msg_bg_verify_fail: "后台验证失败",
	msg_fg_verify_fail: "前台验证失败",
	msg_fg_verify_fail_tip: "有必填项为空或非法字符不能保存!",
	msg_saving: "正在保存...",
	msg_system_error: "系统错误",
	msg_set_form_id: "请在表单中设置ID项:",
	msg_pwd_not_match: "密码不一致",
	msg_cd_format_error: "该输入项只能包含半角字母,数字,-和_",
	msg_chinese_format_error: "该项只能输入中文",
		
	grid_bbar_displayMsg:"记录 {0} - {1} 共 {2}",
	grid_bbar_emptyMsg:"没有数据记录",
	
	ajax_readyState:{
		0: "请求未连接",
        1: "服务器连接已建立",
        2: "请求已接收",
        3: "请求处理中",
        4: "请求已完成，且响应已就绪"
    },
    dropdownlist_mustcheck_error:"错误：必须选择的值（requiredvalue）没有设置为选中（selected）",
    dropdownlist_limit_error: "错误：默认选中的值（selected）多于设置的最多同时选择的值（maxchecked）!",
    dropdownlist_defaultValue:"请选择",
	dropdownlist_selectAll:"全选",
    dropdownlist_bConfirm:"确定",
    dropdownlist_bCancel:"取消",
    dropdownlist_bClear:"清空",
    dropdownlist_mostChecked_1:"最多可选",
    dropdownlist_mostChecked_2:"项",
    file_bDefaultValue:"浏览",
    file_suffix:"请选择正确的文件，后缀为：{0}",
    file_name:"请选择正确的文件，文件名为:{0}",
    ajaxfile_fail:"文件上传失败",
    ajaxfile_drop:"拖拽文件上传",
    ajaxfile_progress:"{total_size}的{percent}% ",
    ajaxfile_retry:"重试",
    ajaxfile_typeError: "{file} 校验失败. 文件类型需为: {extensions}.",
    ajaxfile_sizeError: "{file} 内容过大, 允许最大 {sizeLimit}.",
    ajaxfile_minSizeError: "{file} 内容过小, 允许最小 {minSizeLimit}.",
    ajaxfile_emptyError: "{file} 为空, 请重新选择.",
    ajaxfile_noFilesError: "没有文件上传.",
    ajaxfile_onLeave: "文件正在上传, 如离开页面将取消上传.",
    
    validator_required:"必选字段",
    validator_remote:"请修正该字段",
    validator_email: "请输入正确格式的电子邮件", 
    validator_url: "请输入合法的网址", 
    validator_date: "请输入合法的日期", 
    validator_dateISO: "请输入合法的日期 (ISO).",  
    validator_number: "请输入合法的数字(保留两位小数)",  
    validator_digits: "只能输入整数",  
    validator_creditcard: "请输入合法的信用卡号",  
    validator_equalTo: "请再次输入相同的值",  
    validator_accept: "请输入拥有合法后缀名的字符串", 
    validator_maxlength_1: "允许的最大长度为",  
    validator_maxlength_2: "个字符", 
    validator_minlength_1:"允许的最小长度为",
    validator_minlength_2:"个字符",
    validator_rangelength_1:"允许的长度为",
    validator_rangelength_2:"和",
    validator_rangelength_3:"之间",
    
    validator_range_1:"请输入介于",
    validator_range_2:"和",
    validator_range_3:"之间的值",
    
    validator_max_1:"请输入一个最大为",
    validator_max_2:"的值",
    
    validator_min_1:"请输入一个最小为",
    validator_min_2:"的值",
    validator_specialSignal:"不允许包含特殊符号!",
    validator_chineseOnly:"只能输入汉字,一个汉字占两个字节",
    validator_letterOnly:"只能输入字母",
    validator_IP:"请输入正确的IP地址!",
    validator_Port:"请输入正确的端口号!",
    validator_Postalcode:"邮政编码格式不对",
    validator_mobile:"手机号码格式不对",
    validator_alnum:"只能输入英文字母、数字",
    validator_naturalnum:"只能输入自然数",
    validator_idcardno:"请正确输入身份证号码",
    validator_time:"请正确输入时间格式",
    
    param_error:"参数有误，请重新设置!参数信息如下：",
	ajax_error:"ajax请求数据错误信息:"
};
};

;(function($){
	/**
	 * jqGrid Chinese Translation for v3.6
	 * waiting 2010.01.18
	 * http://waiting.javaeye.com/
	 * Dual licensed under the MIT and GPL licenses:
	 * http://www.opensource.org/licenses/mit-license.php
	 * http://www.gnu.org/licenses/gpl.html
	 * 
	 * update 2010.05.04
	 *		add double u3000 SPACE for search:odata to fix SEARCH box display err when narrow width from only use of eq/ne/cn/in/lt/gt operator under IE6/7
	**/
	$.jgrid = {
		defaults : {
			recordtext: "{0} - {1}\u3000共 {2} 条",	// 共字前是全角空格
			emptyrecords: "0 - 0　共 0 条",
			loadtext: "读取中...",
			pgtext : " {0} 共 {1} 页"
		},
		search : {
			caption: "搜索...",
			Find: "查找",
			Reset: "重置",
			odata : ['等于\u3000\u3000', '不等\u3000\u3000', '小于\u3000\u3000', '小于等于','大于\u3000\u3000','大于等于', 
				'开始于','不开始于','属于\u3000\u3000','不属于','结束于','不结束于','包含\u3000\u3000','不包含'],
			groupOps: [	{ op: "AND", text: "所有" },	{ op: "OR",  text: "任一" }	],
			matchText: " 匹配",
			rulesText: " 规则"
		},
		edit : {
			addCaption: "添加记录",
			editCaption: "编辑记录",
			bSubmit: "提交",
			bCancel: "取消",
			bClose: "关闭",
			saveData: "数据已改变，是否保存？",
	        save:"保存",
			bYes : "是",
			bNo : "否",
			bExit : "取消",
			msg: {
				required:"此字段必需",
				number:"请输入有效数字",
				minValue:"输值必须大于等于 ",
				maxValue:"输值必须小于等于 ",
				email: "这不是有效的e-mail地址",
				integer: "请输入有效整数",
				date: "请输入有效时间",
				url: "无效网址。前缀必须为 ('http://' 或 'https://')",
				nodefined : " 未定义！",
				novalue : " 需要返回值！",
				customarray : "自定义函数需要返回数组！",
				customfcheck : "Custom function should be present in case of custom checking!"
				
			}
		},
		view : {
			caption: "查看记录",
			bClose: "关闭"
		},
		del : {
			caption: "删除",
			msg: "删除所选记录？",
			bSubmit: "删除",
			bCancel: "取消"
		},
		nav : {
			edittext: "编辑",
			edittitle: "编辑所选记录",
			addtext:"增加",
			addtitle: "添加新记录",
			deltext: "删除",
			deltitle: "删除所选记录",
			searchtext: "查找",
			searchtitle: "查找",
			refreshtext: "刷新",
			refreshtitle: "刷新表格",
			alertcap: "注意",
			alerttext: "请选择记录",
			viewtext: "查看",
			viewtitle: "查看所选记录",
			prev:"上一页",
            next:"下一页"
		},
		col : {
			caption: "选择列",
			bSubmit: "确定",
			bCancel: "取消"
		},
		errors : {
			errcap : "错误",
			nourl : "没有设置url",
			server : "程序发生异常",
			norecords: "没有要处理的记录",
			model : "colNames 和 colModel 长度不等！"
		},
		formatter : {
			integer : {thousandsSeparator: " ", defaultValue: '0'},
			number : {decimalSeparator:".", thousandsSeparator: " ", decimalPlaces: 2, defaultValue: '0.00'},
			currency : {decimalSeparator:".", thousandsSeparator: " ", decimalPlaces: 2, prefix: "", suffix:"", defaultValue: '0.00'},
			date : {
				dayNames:   [
					"Sun", "Mon", "Tue", "Wed", "Thr", "Fri", "Sat",
			         "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
				],
				monthNames: [
					"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
					"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
				],
				AmPm : ["am","pm","AM","PM"],
				S: function (j) {return j < 11 || j > 13 ? ['st', 'nd', 'rd', 'th'][Math.min((j - 1) % 10, 3)] : 'th'},
				srcformat: 'Y-m-d',
				newformat: 'm-d-Y',
				masks : {
					ISO8601Long:"Y-m-d H:i:s",
					ISO8601Short:"Y-m-d",
					ShortDate: "Y/j/n",
					LongDate: "l, F d, Y",
					FullDateTime: "l, F d, Y g:i:s A",
					MonthDay: "F d",
					ShortTime: "g:i A",
					LongTime: "g:i:s A",
					SortableDateTime: "Y-m-d\\TH:i:s",
					UniversalSortableDateTime: "Y-m-d H:i:sO",
					YearMonth: "F, Y"
				},
				reformatAfterEdit : false
			},
			baseLinkUrl: '',
			showAction: '',
			target: '',
			checkbox : {disabled:true},
			passwordStr:'*',
			idName : 'id'
		}
	};
	})(jQuery);

