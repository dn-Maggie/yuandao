ExpExcel = {
	expExcel : function(formId, queryFormId) {
		var form = $(formId);
		var tableIdOrGridId = $("#tableIdOrGridId").val();
		var expTableType = $("#expTableType").val();
		if (expTableType == "grid") {
			ExpExcel.onSubmitbBefore(formId, tableIdOrGridId);
		}
		if (expTableType == "report") {
			var queryParams = $(queryFormId).getFormParams();
			ExpExcel.createOrSetQueryHidden(formId, queryParams);
		}
		form.submit();
	},

	createOrSetQueryHidden : function(formId, queryParams) {
		$.each(queryParams, function(idx, item) {
			var eid = formId + '_' + idx;
			var es = $(eid);
			if (es.length == 0) {
				$(ExpExcel.createHidden(eid.replace('#', ''), item.trim(), idx))
						.appendTo($(formId));
			} else {
				$(es).val(item.trim());
			}
		});
	},
	htmlEleToField : function(ele) {
		var v_title = $(ele).html();
		if ($.trim(v_title) === '') {
			v_title = null;
		}
		var v_field = null;
		var v_width = null;
		var v_rowspan = null;
		var v_colspan = null;
		if (typeof($(ele).attr('width')) != "undefined") {
			v_width = $(ele).attr('width');
		}
		if (typeof($(ele).attr('field')) != "undefined") {
			v_field = $(ele).attr('field');
		}
		if (typeof($(ele).attr('rowspan')) != "undefined") {
			v_rowspan = $(ele).attr('rowspan');
		}
		if (typeof($(ele).attr('colspan')) != "undefined") {
			v_colspan = $(ele).attr('colspan');
		}
		return {
			field : v_field,
			title : v_title,
			width : v_width,
			rowspan : v_rowspan,
			colspan : v_colspan
		};
	},
	getTableTotalFields : function(tableId) {
		var countRows = $(tableId).find(".count-row");// 得到合计行
		var trArray = new Array(); // 先声明一维
		var thArray; // 先声明一维
		$.each(countRows, function(i, n) {
					thArray = new Array();
					$.each($(n).children("td"), function() {
								thArray.push(ExpExcel.htmlEleToField(this));
							});
					trArray.push(thArray);
				});
		return trArray;
	},
	createHidden : function(name, value) {
		if (value) {
			value = 'value="' + value + '" />';
		} else {
			value = ' />';
		}
		var hidn = $('<input type="hidden"   name="' + name + '"  ' + value);
		return hidn;
	},
	getTableHeadFields : function(tableId) {
		var headRows = $(tableId).find(".HeadRow");// 得到里面的标题行集合
		var trArray = new Array(); // 先声明一维
		var thArray; // 先声明一维
		$.each(headRows, function(i, n) {
					thArray = new Array();
					$.each($(n).children("th"), function() {
								thArray.push(ExpExcel.htmlEleToField(this));
							});
					trArray.push(thArray);
				});
		return trArray;
	},
	htmlTableFieldsToFrom : function(formId, tableId) {
		var headFields = ExpExcel.getTableHeadFields(tableId);
		ExpExcel.fieldsToFrom(formId, headFields, "fieldlist");
		var totalFields = ExpExcel.getTableTotalFields(tableId);
		ExpExcel.fieldsToFrom(formId, totalFields, "totallist");
	},
	/**
	 * 把field变成表单元素加到表单里面
	 * 
	 * @param {}
	 *            formId
	 * @param {}
	 *            fields
	 */
	fieldsToFrom : function(formId, fields, paramName) {
		var k = 0, tmpj, clum;
		var form = $(formId);
		for (var i = 0; i < fields.length; i++) {
			clum = fields[i];// 单个列对象
			if (!clum.hidden | clum.hidden != true) { // 如果这列不是隐藏的
				if (clum.name != 'rn' && clum.name != 'subgrid'
						&& clum.name != 'cb' && clum.width != 25) {
					for (op in clum) {// 循环列对象里的所有属性
						if (op == 'name' || op == 'label' || op == 'width') {// 要取得的属性
							$(ExpExcel.createHidden((paramName + '[0][' + k
											+ '].' + op), clum[op]))
									.appendTo(form);
						}
					}
					k++;
				}
			}
		}
	},
	createWinHtml : function() {
		var htmlTmp = '<form id="expExcelForm"  method="post" action="">';
		htmlTmp += '<input type="hidden" id="startpage" name="startpage"/>';
		htmlTmp += '<input type="hidden" id="endpage" name="endpage"/>';
		htmlTmp += '<input type="hidden" id="pageSize" name="pageSize"/>';
		htmlTmp += '<input type="hidden" id="orderFields" name="orderFields"/>';
		htmlTmp += '<input type="hidden" id="order" name="order"/>';
		htmlTmp += '<div style="height:140px;margin-left:44px; margin-top:16px;"><div style="height:32px; line-height:32px;"><input type="radio" checked="checked" name="expType" value="1" />导出当前页数据</div>';
		htmlTmp += '<div style="height:32px; line-height:32px;"><input type="radio" name="expType" value="2" />导出全部数据</div></div>';
		htmlTmp += '<div style="float:left;margin-left:46px;"><input type="button" id="cancel" class="search_btn4" value="取消">';
		htmlTmp += '<input type="button" id="export" class="add_save" value="导出" /></div>';
		htmlTmp += '</form>';
		return htmlTmp;
	},
	// Grid导出
	exeExportByGrid : function(gridObj, expUrl, queryForm) {
		var formId = '#expExcelForm';
		var expExcelForm = $(formId);
		if (expExcelForm.attr("action") == expUrl) {
			expExcelForm.submit();
		} else {
			expExcelForm.attr("action", expUrl);
			var columns = gridObj.jqGrid('getGridParam', 'colModel');
			var queryPostDatas = getQueryCondition();
			$.each(queryPostDatas, function(k, v) {
						$(ExpExcel.createHidden(k, v)).appendTo(expExcelForm);
					});
			$('#orderFields').val(gridObj.jqGrid('getGridParam', 'sortname'));
			$('#order').val(gridObj.jqGrid('getGridParam', 'sortorder'));
			var page = gridObj.jqGrid('getGridParam', 'page');
			var pagesize = gridObj.jqGrid('getGridParam', 'rowNum');
			var expType = $('input[name=expType]:checked');
			var t = $(expType).val();
			$('#startpage').val(page);
			$('#endpage').val(page);
			$('#pageSize').val(pagesize);
			ExpExcel.fieldsToFrom(formId, columns, "fieldlist");
			expExcelForm.submit();
		}
	},
	showWin : function(tableIdOrGridId, expUrl, type, queryForm) {
		$("#excelExportDialogDiv").remove();
		var excelExportDialogDiv = $('<div id="excelExportDialogDiv">'
				+ ExpExcel.createWinHtml() + '</div>');
		$(document.body).append(excelExportDialogDiv);
		if (type == "grid") {
			excelExportDialogDiv.find('#export').bind("click", function() {
						ExpExcel.exeExportByGrid(tableIdOrGridId, expUrl,
								queryForm);
					});
		} else {

		}

		excelExportDialogDiv.find('#cancel').bind("click", function() {
					excelExportDialogDiv.dialog("close");
				});
		excelExportDialogDiv.dialog({
					height : 250,
					width : 320,
					autoOpen : false,
					modal : true,
					title : "导出选择"
				});
		excelExportDialogDiv.dialog("open");
	}

}