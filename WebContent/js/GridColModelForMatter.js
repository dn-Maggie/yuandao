GridColModelForMatter = {
	//计划附件状态
	fjFlag : function(cellvalue, options, cellObject) {
		if (cellvalue == "0")
			return "无";
		else
			return "有";
	},
	//审核状态
	groupCkStatus : function(cellvalue, options, cellObject) {
		if (cellvalue == "0") {
			return '<i value="0" class="state_icons icon_noaudit"></i>待审核';
		} else if (cellvalue == "1") {
			return '<i value="1" class="state_icons icon_audit1"></i>审核通过';
		} else if (cellvalue == "2") {
			return '<i value="2" class="state_icons icon_shwtg"></i>审核未通过';
		}

	},
	//资产状态
	fixedPropertyState : function(cellvalue, options, cellObject) {
		 if (cellvalue==1) {
			 	return '使用中';
		 }else if (cellvalue==2) {
		 	return '维修中';
		 }else if(cellvalue==3){
			return '已报废';
		 }else if(cellvalue==4){
			return '停用中';
		 }
	},
	dutySort : function(cellvalue, options, cellObject) {
		if (cellvalue == "1") {
			return '集团级别';
		} else if (cellvalue == "2") {
			return '省级别';
		} else if (cellvalue == "3") {
			return '分公司级别';
		}
	},
	// （1启用，0停用）
	enableStates : function(cellvalue, options, cellObject) {
		if (cellvalue == "0") {
			return '<i value="0" class="state_icons icon_nochange"></i>停用';//
		} else {
			return '<i value="1" class="state_icons icon_enabled"></i>启用';//
		}
	},
	// （工资类型）
	salaryType : function(cellvalue, options, cellObject) {
		if (cellvalue == "year") {
			return '年薪';
		} else if (cellvalue == "month") {
			return '月薪';
		} else {
			return '绩效提成';
		}
	},
	sanctionType : function(cellvalue, options, rowObject) {
		if (cellvalue == 1) {
			return '奖励';
		} else {
			return '处罚';
		}
	},
	rate : function(cellvalue, options, cellObject) {
		cellvalue = parseFloat(cellvalue)||0;
		if (cellvalue < 0 ) {
			return '<i style="float:right;font-weight:bold;padding-right:10px;color:green">'+cellvalue+'%</i>';
		} else{
			return '<i style="float:right;font-weight:bold;padding-right:10px;color:red">+'+cellvalue+'%</i>';
		}
	},
	rate_nc : function(cellvalue, options, cellObject) {
		cellvalue = parseFloat(cellvalue)||0;
		return '<i style="float:right;font-weight:bold;padding-right:10px">'+cellvalue+'%</i>';
	},
	price : function(cellvalue, options, cellObject) {
		cellvalue = parseFloat(cellvalue)||0;
		cellvalue = cellvalue<10000?cellvalue:(cellvalue*0.0001)+"万";
		if (cellvalue < 0 ) {
			return '<i style="float:right;font-weight:bold;padding-right:10px;color:green">'+cellvalue+'</i>';
		} else{
			return '<i style="float:right;font-weight:bold;padding-right:10px;color:red">'+cellvalue+'</i>';
		}
	},
	price_nc : function(cellvalue, options, cellObject) {
		cellvalue = parseFloat(cellvalue)||0;
		cellvalue = cellvalue<10000?cellvalue:(cellvalue*0.0001)+"万";
		return '<i style="float:right;font-weight:bold;padding-right:10px">'+cellvalue+'</i>';
	},
	finance_type : function(cellvalue, options, cellObject) {
		if(cellvalue=="1"){
			return '借[收入]';
		}else{
			return '贷[支出]';
		}
	}
};