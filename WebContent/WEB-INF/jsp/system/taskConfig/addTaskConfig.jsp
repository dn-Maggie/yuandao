<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html  >
<html>
<head>
<%@ include file="../../common/header.jsp"%>
<script type="text/javascript">
$(function() {
	//绑定提交按钮click事件
	$("#submit").click(function() {
		if(!biz.validate("valid",$('#taskConfigFormEdit')[0])){
			showWarn("数据验证失败",3000);
			return;
		}
		var options = {
			url : "<m:url value='/taskConfig/addTaskConfig.do'/>",
			type : "post",
				dataType:"json",
				success : function(d) {
					if(d.status){
						showMessage(d.message,"","",function(){
							window.parent.closeAdd();
				     		window.parent.doSearch();
						});
					}else{
						showMessage(d.message);
					}
				}
		};
		// 将options传给ajaxForm
		$('#taskConfigFormEdit').ajaxSubmit(options);
	});
	new biz.number({//顺序
        id:"#taskOrder"
    });
	 new biz.datepicker({
			id : "#startTime",
			dateFmt:'yyyy-MM-dd HH:mm:ss'
		});
	new biz.number({//年
        id:"#septYears",
        minNum:1,
        maxNum:12
    });
	new biz.number({//月
        id:"#septMonths",
        minNum:1,
        maxNum:12
    });
	new biz.number({//周
        id:"#septWeeks",
        minNum:1,
        maxNum:7
    });
	new biz.number({//日
        id:"#septDays",
        minNum:1,
        maxNum:31
    });
	new biz.number({//时
        id:"#septHours",
        minNum:0,
        maxNum:59
    });
	new biz.number({//分
        id:"#septMinutes",
        minNum:0,
        maxNum:59
    });
	new biz.number({//秒
        id:"#septSeconds",
        minNum:0,
        maxNum:59
    });
	/*编辑表单数据验证*/
	new biz.validate({
		id:"#taskConfigFormEdit",
		rules:{
			"taskName" : {
				required : true,
				maxlength : 20
			},
			"className" : {
				required : true,
				maxlength : 200
			},
			"classMethod" : {
				required : true,
				maxlength : 100
			},
			"taskOrder" : {
				required : true,
				maxlength : 10
			}
		}
	}); 
});
</script>
</head>

<body>

	<div id="editDialog">
		<form id="taskConfigFormEdit">
			<div class="ui-table ui-widget ui-corner-all ui-border">
				<input type="hidden" id="id" name="id" type="text"
					value="${taskConfig.id}" />
				<table class="table">
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>任务名称：</td>
						<td class="inputTd"><input id="taskName" name="taskName"
							type="text" class="text" value="${taskConfig.taskName}" /></td>
						<td class="inputLabelTd">任务类型：</td>
						<td class="inputTd"><select id="taskType" name="taskType"
							class="select">
								<option value="0">循环</option>
								<option value="1">定时</option>
						</select></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>执行类名：</td>
						<td class="inputTd" colspan="3"><input id="className"
							name="className" type="text" class="text" style="width: 480px;"
							value="${taskConfig.className}" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd"><span class="required">*</span>执行方法：</td>
						<td class="inputTd" colspan="3"><input id="classMethod"
							name="classMethod" type="text" class="text" style="width: 480px;"
							value="${taskConfig.classMethod}" /></td>
					</tr>
					<tr>

						<td class="inputLabelTd">任务状态：</td>
						<td class="inputTd"><select id="taskStatus" name="taskStatus"
							class="select">
								<option value="1">启用</option>
								<option value="0">停用</option>
						</select></td>
						<td class="inputLabelTd"><span class="required">*</span>任务顺序：</td>
						<td class="inputTd"><input id="taskOrder" name="taskOrder"
							type="text" class="text" value="${taskConfig.taskOrder}" /></td>
					</tr>
					<tr>
						<td class="inputLabelTd">任务描述：</td>
						<td class="inputTd" colspan="3"
							style="padding-top: 5px; padding-bottom: 5px;"><textarea
								rows="1" id="describe" name="describe" class="text"
								style="width: 480px; height: 65px;">${taskConfig.describe}</textarea>
						</td>
					</tr>
					<tr>
						<td class="inputLabelTd">开始时间：</td>
						<td class="inputTd"><input id="startTime" name="startTime"
							type="text" class="text" value="${taskConfig.startTime}" /></td>
						<td class="inputLabelTd"></td>
						<td class="inputTd"></td>
					</tr>
					<tr>
						<td class="inputLabelTd">执行频率：</td>
						<td class="inputTd" colspan="3">年：<input id="septYears"
							name="septYears" type="text" class="text" style="width: 40px;"
							value="${taskConfig.septYears}" /> 月：<input id="septMonths"
							name="septMonths" type="text" class="text" style="width: 20px;"
							value="${taskConfig.septMonths}" /> 周：<input id="septWeeks"
							name="septWeeks" type="text" class="text" style="width: 20px;"
							value="${taskConfig.septWeeks}" /> 日：<input id="septDays"
							name="septDays" type="text" class="text" style="width: 20px;"
							value="${taskConfig.septDays}" /> 时：<input id="septHours"
							name="septHours" type="text" class="text" style="width: 20px;"
							value="${taskConfig.septHours}" /> 分：<input id="septMinutes"
							name="septMinutes" type="text" class="text" style="width: 20px;"
							value="${taskConfig.septMinutes}" /> 秒：<input id="septSeconds"
							name="septSeconds" type="text" class="text" style="width: 20px;"
							value="${taskConfig.septSeconds}" />
						</td>
					</tr>



					<tr>
						<td class="inputTd" colspan="4" style="text-align: center;">
							<input id="submit" type="button" class="ti_bottom" value="保存" />
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>
</html>
