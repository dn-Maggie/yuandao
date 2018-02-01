
function setRightWidth(){
	var winWidth = $(window).width();
	var width = winWidth;
	if($(".nav").css("display") != "none"){
		width = width - 78;
	}
	$("#rightSide").css("width",width +"px");
}

$(function(){
	setHeight();
	setRightWidth();

	$('ul.one').hide();
	//隐藏和显示菜单
	$('.nav li').hover(function(){
		var openMenu= $(this).children('ul.one');
		$(openMenu).show();
	},function(){
		var openMenu= $(this).children('ul.one');
		$(openMenu).hide();
	});
	
	//点击一级菜单加载当前样式
	$(".nav li").click(function(){
		$(".nav li.nav_current").removeClass("nav_current");
		$(this).addClass("nav_current");
		});

	//点击图标控制菜单的显示隐藏
	$("#icon_fx").click(function(){
		var rightSideW = $("#rightSide").width();
		if($(".nav").css("display") == "none"){
			
			$("#rightSide").css("width",(rightSideW-78) +"px");
			$(".nav").animate({
				width: "76px"
			},  "slow",null,function(){
				$(".nav").css("display","block");	
				
			});
		}else{
			$(".nav").animate({
				width: "0px"
			},  "fast",null,function(){
				$(".nav").css("display","none");
				$("#rightSide").css("width",(rightSideW+78) +"px");
			},180);
		}
	
	});
	
	//顶部浮层hover效果
	$(".h_right ul li#mainleve").mousemove(function(){
		//$(this).find('.dialogbox').show("800");
		//$(this).find('.huanfu_list').show("800");
	});
	$(".h_right ul li#mainleve").mouseleave(function(){
		//$(this).find('.dialogbox').hide("800");
		//$(this).find('.huanfu_list').hide("800");
	});
	
	
});

function setHeight(){
    var navHeight = $(".nav").height();
    var winHeight = $(window).height();
    var height = winHeight - 36;
    if(height > navHeight){
        $(".nav").css("height",height +"px");
    }
	$("#container").css("height",height +"px");
};
$(window).resize(function(){ 
    setHeight();
    setRightWidth();
});






