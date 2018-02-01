<div id="msgDiv"
	style="display: none; z-index: 1; width: 300px; position: absolute; left: 5; top: 5;"
	class="msgDiv">
	<div class="msgtitle">
		<div id="titleTxt" class="title">
			<fmt:message key="msg.title" />
		</div>
		<img class="icon" onClick="hide()"
			src="/styles/skin/default/images/wclose.gif" />
	</div>
	<div id="content" class="content" style="padding: 4px; display: block">
	</div>
</div>
<biz:display level="info,error,success,action" />

<script type="text/javascript">
<!--
function hide() {
	document.getElementById('msgDiv').style.display = "none";
}
setTimeout(hide,5000);
//-->
</script>


<%-- ********通用提示对话框******** --%>

<script type="text/javascript">
	
function showMessage(msg,title,timeout,callback){
	//jAlert(msg,title,callback);
	new biz.alert({type:"alert",message:msg,title:I18N.promp,callback:callback}) ;
	//jHide(timeout);
	new biz.alert({type:"hide",times:timeout}) ;
}

//show common message
function showInfo(msg,timeout,callback){
	//jAlert(msg,I18N.promp,callback);
	new biz.alert({type:"alert",message:msg,title:I18N.promp,callback:callback}) ;
	new biz.alert({type:"hide",times:timeout}) ;
}

//show error message
function showError(msg,timeout,callback){
	//jAlert(msg,I18N.error,callback);
	new biz.alert({type:"alert",message:msg,title:I18N.error,callback:callback}) ;
	new biz.alert({type:"hide",times:timeout}) ;
}

// show warn message
function showWarn(msg,timeout,callback){
	//jAlert(msg,I18N.warn,callback);
	new biz.alert({type:"alert",message:msg,title:I18N.warn,callback:callback}) ;
	new biz.alert({type:"hide",times:timeout}) ;
}

function openwindow(url,name,iWidth,iHeight)
{
	var url; <%--转向网页的地址;--%>
	var name; <%--网页名称，可为空;--%>
	var iWidth; <%--弹出窗口的宽度;--%>
	var iHeight; <%--弹出窗口的高度;--%>
	var iTop = (window.screen.availHeight-30-iHeight)/2; <%--获得窗口的垂直位置;--%>
	var iLeft = (window.screen.availWidth-10-iWidth)/2; <%--获得窗口的水平位置;--%>
	if(url.indexOf("?") == -1){
		url += "?t="+new Date().getTime();
	} else {
		url += "&t="+new Date().getTime();
	}
	window.open(url,name,"toolbar=0,location=0,directories=0,status=0,menubar=0,resizable=1,scrollbars=yes,height="+iHeight+",width="+iWidth+",top="+iTop+",left="+iLeft);
}

<%--增加日期的格式化方法--%>
Date.prototype.format=function(format,loc){
    var time={};
    time.Year=this.getFullYear();
    time.TYear=(""+time.Year).substr(2);
    time.Month=this.getMonth()+1;
    time.TMonth=time.Month<10?"0"+time.Month:time.Month;
    time.Day=this.getDate();
    time.TDay=time.Day<10?"0"+time.Day:time.Day;
    time.Hour=this.getHours();
    time.THour=time.Hour<10?"0"+time.Hour:time.Hour;
    time.hour=time.Hour<13?time.Hour:time.Hour-12;
    time.Thour=time.hour<10?"0"+time.hour:time.hour;
    time.Minute=this.getMinutes();
    time.TMinute=time.Minute<10?"0"+time.Minute:time.Minute;
    time.Second=this.getSeconds();
    time.TSecond=time.Second<10?"0"+time.Second:time.Second;
    time.Millisecond=this.getMilliseconds();
    time.Week=this.getDay();

    var MMMArrEn=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
    var MMMArr=["<fmt:message key='date.month.1'/>","<fmt:message key='date.month.2'/>","<fmt:message key='date.month.3'/>","<fmt:message key='date.month.4'/>","<fmt:message key='date.month.5'/>","<fmt:message key='date.month.6'/>","<fmt:message key='date.month.7'/>","<fmt:message key='date.month.8'/>","<fmt:message key='date.month.9'/>","<fmt:message key='date.month.10'/>","<fmt:message key='date.month.11'/>","<fmt:message key='date.month.12'/>"];
    var WeekArrEn=["Sun","Mon","Tue","Web","Thu","Fri","Sat"];
    var WeekArr=["<fmt:message key='date.week.0'/>","<fmt:message key='date.week.1'/>","<fmt:message key='date.week.2'/>","<fmt:message key='date.week.3'/>","<fmt:message key='date.week.4'/>","<fmt:message key='date.week.5'/>","<fmt:message key='date.week.6'/>"];

    var oNumber=time.Millisecond/1000;

    if(format!=undefined && format.replace(/\s/g,"").length>0){
        if(loc!=undefined && loc =="en"){
            MMMArr=MMMArrEn.slice(0);
            WeekArr=WeekArrEn.slice(0);
        }
        format=format
            .replace(/yyyy/ig,time.Year)
            .replace(/yyy/ig,time.Year)
            .replace(/yy/ig,time.TYear)
            .replace(/y/ig,time.TYear)
            .replace(/MMM/g,MMMArr[time.Month-1])
            .replace(/MM/g,time.TMonth)
            .replace(/M/g,time.Month)
            .replace(/dd/ig,time.TDay)
            .replace(/d/ig,time.Day)
            .replace(/HH/g,time.THour)
            .replace(/H/g,time.Hour)
            .replace(/hh/g,time.Thour)
            .replace(/h/g,time.hour)
            .replace(/mm/g,time.TMinute)
            .replace(/m/g,time.Minute)
            .replace(/ss/ig,time.TSecond)
            .replace(/s/ig,time.Second)
            .replace(/fff/ig,time.Millisecond)
            .replace(/ff/ig,oNumber.toFixed(2)*100)
            .replace(/f/ig,oNumber.toFixed(1)*10)
            .replace(/EEE/g,WeekArr[time.Week]);
    }
    else{
        format=time.Year+"-"+time.Month+"-"+time.Day+" "+time.Hour+":"+time.Minute+":"+time.Second;
    }
    return format;
}
</script>