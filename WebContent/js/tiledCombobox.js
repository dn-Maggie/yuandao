(function($) {
	$.fn.TiledCombobox = function(opts) {
		var defaults = {
			method : 'post',// 发送请求的方式，get或post
			url : null,// 数据加载url
			data : null,
			fieldId : 'id',// 数据有几级
			fieldName : 'name',// 查询URL
			defValId : null,
			valueId : null,
			multiple : true,// 默认不能多选
			onInit : null,// 初始化时的动作
			onInitAfter : null,
			onChangeFn : null,
			queryData : null,// 查询参数
			autoLoadData : true,// 自动加载数据
			defTitle : '请选择',
			clickFn : null
		// 点击动作
		};
		var option = $.extend(defaults, opts);
		// 定义一个通用函数集合
		var F = {
			createBoxItemsHTML : function(datas, selectedVal) {
				var html = '<ul>', tmpId;
				$.each(datas, function(i, v) {
					if (v) {
						tmpId = v[option.fieldId];
						html += '<li '
								+ (selectedVal == null ? '' : F
										.getSelectedClass(tmpId, selectedVal))
								+ ' id="' + tmpId + '">' + v[option.fieldName]
								+ '</li>';
					}
				});
				return html += '</ul>';
			},
			getBoxShowXY : function() {
				return {
					complete : function() {
//						selectBox.css({
//							'top' : _this.offset() + _this.height(),
//							'left' : 
//						});
					}
				};
			},
			getSelectedClass : function(val, selectedVal) {
				if (val == selectedVal) {
					return 'class="selected"';
				} else {
					return '';
				}
			}
		};
		var returnObj = null;
		var $container = $(this).wrap("<div class='selectBoxWarp'></div>").parent();
		var _this = this;
		var _this_id = $(this).attr("id");
		var selectBox = null;
		var isMouseoverBox = false;// 是否鼠标进入了选中层
		var hiddenEle = null;
		var chooseManager = {
			loadData : function(targetEle) {
				$.ajax({
					url : option.url,
					data : option.queryData == null ? "" : option.queryData,
					dataType : 'json',
					type : 'post',
					success : function(data, textStatus, jqXHR) {
						targetEle.html("");
						targetEle.append($(F.createBoxItemsHTML(data,
								option.defValId)));
						chooseManager.initEvents();
					}
				});
			},
			// 有两种，一种是直接给data，一种是ajax通过url获取
			init : function() {
				//_this.val("");
				_this.attr("readOnly", true);
				hiddenEle = $(option.valueId);
				// _this.removeAttr("id");
				// _this.removeAttr("name");
				// 外框
				selectBox = $('#selectBox_' + _this_id);
				if (selectBox.length == 0) {
					selectBox = $('<div id="selectBox_'
							+ _this_id
							+ '" class="selectBox nicescroll" style="overflow-x: hidden;overflow-y: auto;" tabindex="5001"></div>');
					$container.append(selectBox);
				} else {
					selectBox.html("");
				}
				selectBox.hide();
				// _this.parent().after(selectBox);
				if (option.data != null) {
					selectBox.append($(F.createBoxItemsHTML(option.data,
							option.defValId)));
					chooseManager.initEvents();// 初始化事件
				} else {// url控制
					if (option.autoLoadData) {
						chooseManager.loadData(selectBox);
					}
				}
				returnObj = {
					setDataAndReload : function(_data) {
						option.data = _data;
						option.url = null;
						chooseManager.loadData(selectBox);
					},
					setQueryDataAndReload : function(_query_data) {
						option.queryData = _query_data;
						option.data = null;
						chooseManager.loadData(selectBox);
					},
					clearVlaue : function() {
						$container.find(".selected").removeClass("selected");
						_this.val("");
						hiddenEle.val("");
						if (option.valueId != null) {
							$(option.valueId).val("");
						}
					},
					reset : function() {
						$container.find(".selected").removeClass("selected");
						_this.val("");
						hiddenEle.val("");
					},
					TiledCombobox : function() {// 构造方法
						var method = arguments[0];
						if (method in this) {
							Array.prototype.splice.call(arguments, 0, 1);
							this[method].apply(this[method], arguments);
						}
					}
				};
			},
			initEvents : function() {
				_this.focus(function() {
//					selectBox.css({
//						'top' : _this.offset().top + _this.height(),
//						'left' : _this.offset().left
//					});
					selectBox.show();
				});
				selectBox.mouseover(function() {
					isMouseoverBox = true;
				});
				selectBox.mouseleave(function() {
					isMouseoverBox = false;
				});
				_this.blur(function() {
					if (isMouseoverBox == false)
						selectBox.hide();
				});
				selectBox.blur(function() {
					selectBox.hide();
				});
				// 绑定点击事件
				selectBox.find("li").click(function() {
					chooseManager.li_on_click(this);
					if (option.onChangeFn != null) {// 增加值切换事件
						(option.onChangeFn(hiddenEle.val(), _this.val()));
					}
				});
			},
			// 选中事件
			li_on_click : function(li_ele) {
				if (option.multiple == false) {// 如果是单选
					if ($(li_ele).hasClass("selected")) {
						$(li_ele).removeClass("selected");
						_this.val("");
						hiddenEle.val("");
					} else {
						selectBox.find("li").removeClass("selected");
						$(li_ele).addClass("selected");
						_this.val($(li_ele).text());
						hiddenEle.val(li_ele.id);
					}
				} else {
					if ($(li_ele).hasClass("selected")) {
						$(li_ele).removeClass("selected");
					} else {
						$(li_ele).addClass("selected");
					}
					var idlist = new Array();
					var namelist = new Array();
					selectBox.find(".selected").each(function(li) {
						idlist.push(this.id);
						namelist.push($(this).text());
					});
					_this.val(namelist.join("，"));
					hiddenEle.val(idlist.join(","));
				}
			}
		}

		chooseManager.init();
		return returnObj;

	}
})(jQuery);
