// js、css文件所在路径。
$js_path = "./lib/js/lib/";
$csss_path = "./lib/style/";
var treeObj ,delskin;

function isrepeat(str){
	var searchStr = document.getElementsByTagName('head')[0].innerHTML;
	var patt = new RegExp(str);
					//判断是否已经加载此文件
	if(patt.test(searchStr)){return false;}else{return true;}
}

function js_include($script){
	 if(isrepeat($script)){
		var script = document.createElement('script');
		script.src = $js_path + $script;
		script.type = 'text/javascript';
		var head = document.getElementsByTagName('head').item(0);
		head.appendChild(script);
	}
}

function css_include($link){
	if(isrepeat($link)){
	var link = document.createElement('link');
	link.href = $csss_path + $link;
	link.type = 'text/css';
	link.rel = 'stylesheet';
	var head = document.getElementsByTagName('head').item(0);
	head.appendChild(link);
}
}

function iecss_include($link){
    if(isrepeat($link)){
        var link = document.createElement('link');
        link.href = $csss_path + $link;
        link.type = 'text/css';
        link.rel = 'stylesheet';
        var head = document.getElementsByTagName('head').item(0);
        //如果是ie浏览器则加载此css
        if (window.ActiveXObject)head.appendChild(link);
    }
}

//每个tab动态增加css
function css_style($cssStr ,$id){
	if (window.ActiveXObject){	
          //ie下通过字符处理
          // var obj = document.styleSheets[document.styleSheets.length-1];
          var i = 0;
          while( i < document.styleSheets.length){
             if( document.styleSheets[i].id === "modifyskin" )  break;
             i=i+1
          }
          var obj = document.styleSheets[i];
          var pattern = new RegExp("^#modifyskin"+$id+"\\s+{\\r+\\n+\\s+}");
          var cssStr = obj.cssText.toString().replace(pattern ,"#modifyskin" + $id+" {}");
          var isyes = false;
          var i,id;
          id = "#modifyskin" + $id + " {}" ;
          if(cssStr.indexOf(id)!= -1){
              cssStr = cssStr.replace(id ,id + $cssStr);
          }else{
              cssStr = id  + $cssStr + cssStr ;
          }
          obj.cssText = cssStr;
    }else{ //每个tab动态css作为一个文本节点，以nodename区分
   	    var nodes = document.getElementById("modifyskin").childNodes;
		  //用于判断此tab页是否存在文本节点
		  var isyes = false;
		  var i;
	    if(nodes.length != 0){
	    	for(i=0;i<nodes.length;i++){	
	    		  if(nodes[i].nodename == $id){
	    		  	isyes = true;break;
	    		   }
	    		}
	     }
	     if(isyes){
	     	  nodes[i].appendData($cssStr);
	     }else{
	     	   var newnode = document.createTextNode($cssStr);
	         newnode.nodename = $id;
	    	  document.getElementById("modifyskin").appendChild(newnode);
	     }
   	
   	} 
}

 //动态删除当前tab关联的动态css
    function delskin($id){
    	if (window.ActiveXObject){
    	    var i = 0,
                startindex = 0, //tab页修改皮肤相关css开始位置
                tempstr = "",     //tab页css开始位置往后的字符串
                tempinsex = 0,   //下一个tab在tempstr中位置
                delstr = "";     //需删除的字符串
            while( i < document.styleSheets.length){
                 if( document.styleSheets[i].id === "modifyskin" )  break;
                 i=i+1
            }
            var obj = document.styleSheets[i];
           // var pattern = new RegExp("^#modifyskin"+$id+"\\s+{\\r+\\n+\\s+}");
            $id = $id.replace("#","");
            startindex = obj.cssText.toString().indexOf("#modifyskin" + $id);
            if(startindex != -1){
                 tempstr =   obj.cssText.toString().substring(startindex);
                 tempindex =  tempstr.indexOf("#modifyskin",2);
                 delstr = (tempindex != -1) ? obj.cssText.toString().substr(startindex,tempindex) :obj.cssText.toString().substr(startindex) ;
                 obj.cssText = obj.cssText.toString().replace(delstr,"");
            }
		}else{
		 	var nodes = document.getElementById("modifyskin").childNodes;
		    if(nodes.length != 0){
		    	for(var i=0;i<nodes.length;i++){
		    		  if(("#"+nodes[i].nodename) == $id){
		    		  	 document.getElementById("modifyskin").removeChild(nodes[i]);break;
		    		   }
		    		}
		     }

		}
   }

//实现demo中源代码展开、折叠切换
function toggle(sourceBar, source) {
	 $(sourceBar).click(function() {
			$(source).toggle("fast");
	 		$(this).toggleClass(function() {
				if ($(this).is('.iconMinus')) {
				    $(this).removeClass("iconMinus");
			    	return 'iconPlus';
			  	} else {
			  	    $(this).removeClass("iconPlus");
			    	return 'iconMinus';
			  	}
			});
	   });
};

//每级节点生成导航路径的span和a元素
function createRoadEle(treeNode,parentEle){
    $(parentEle).prepend($("<a>", {
        "class":"vlink",
        text: treeNode.name,
        click: function(){
            $(this).nextAll().remove();
            treeObj.selectNode(treeNode);
            //$("#RightPane").load(treeNode.href);
            $("#RightPane iframe").attr("src",treeNode.href);
            $(this).addClass("vlinkLast");
        }
    }));
    $(parentEle).prepend($("<span>", {
       "class": "navSpan",
        text:" "
    }));
}
function navRoad(treeId,treeNode){
    var containerEle;
    if($("#navDynamic").length === 0){
        containerEle = document.createElement("div");
        containerEle.id = "navDynamic";
        $(containerEle).css({ 'float': "left" });
    }else{
        $("#navDynamic").html("");
        containerEle = document.getElementById("navDynamic");
    }
    while(treeNode.getParentNode()!== null){
        createRoadEle(treeNode,containerEle);
        treeNode = treeNode.getParentNode();
    }
    createRoadEle(treeNode,containerEle);
    $("#navRoad").append(containerEle);
    $("#navDynamic a").last().addClass("vlinkLast");
}
//获取树节点上一个子节点，没有子节点返回null
function getPreChildNode(node,isStatus){
    var tempNode;
    if(isStatus){
        tempNode = node;
    }else{
        var preNode = node.getPreNode();
        tempNode = preNode ? (preNode.isParent?preNode.children[preNode.children.length-1]:preNode) : node.getParentNode().getPreNode();
    }
    if(tempNode){
        if(tempNode.isParent){
            return getPreChildNode(tempNode.children[tempNode.children.length-1],true);
            /*if(tempNode.level == 0 && tempNode.isFirstNode){
             return null;
             }else{
             return getPreChildNode(tempNode.children[tempNode.children.length-1],true);
             }*/
        }else{
            return tempNode;
        }
    }else{
        if(node.getParentNode()){
            if(node.getParentNode().level == 0 && node.getParentNode().isFirstNode){
                return null;
            }else{
                return getPreChildNode(node.getParentNode());
            }
        }else{
            return null;
        }
    }
}
//获取树节点下一个子节点，没有子节点返回null
function getNextChildNode(node,isStatus){
    var temNode;
    if(isStatus){
        tempNode = node;
    }else{
        var nextNode = node.getNextNode();
        tempNode = nextNode ? (nextNode.isParent?nextNode.children[0]:nextNode) : node.getParentNode().getNextNode();
    }
    if(tempNode){
        if(tempNode.isParent){
            return getNextChildNode(tempNode.children[0],true);
        }else{
            return tempNode;
        }
    }else{
        if(node.getParentNode()){
            if(node.getParentNode().level == 0 && node.getParentNode().isLastNode){
                return null;
            }else{
                return getNextChildNode(node.getParentNode());
            }
        }else{
            return null;
        }
    }
}

function createDemo(options){
	var dialogObj = new biz.dialog({
        id:".source",
        autoOpen: false,
        width: 700,
        height:400,
        modal: true,
        resizable:true,
        title: ' 源代码',
        open: function (event, ui) {
            var $dialog = $(this);
            var atext = $(".source").prev().children("a").replaceWith('<p class="ui-xlgwr"><span class="ui-icon ui-icon-minusthick">minusthick</span> <span class="ui-icon ui-icon-extlink">extlink</span><span class="ui-icon ui-icon-closethick">close</span></p>');
            $(".ui-xlgwr>span").click(function () {
                var spantext = $(this).text();
                if (spantext == "extlink") {
                    if (window.screen) {              //判断浏览器是否支持window.screen判断浏览器是否支持screen
                        var myw = screen.availWidth;   //定义一个myw，接受到当前全屏的宽
                        var myh = screen.availHeight;  //定义一个myw，接受到当前全屏的高

                        $dialog.dialog({
                            position: ['left', 'top'],
                            width: myw * 0.75,
                            height: myh * 0.73
                        });

                    } else {
                        $dialog.dialog({
                            position: 'center',
                            width: 800,
                            height: 600
                        });
                    }
                } else if (spantext == "minusthick") {
                    $dialog.dialog({
                        position: 'top',
                        width: 210,
                        height: 45
                    });
                } else if (spantext == "close") {
                    $dialog.dialog("close");
                }
            });
        }

    });
    //默认值
    options = $.extend({titleEle:".content-title",
                        panelEle:".content-right",
                        properImg:"images/document-txt.png",
                        relateImg:"images/sample.png"},options)
    var contentEle,properEle,relateEle;
    //panel标题
    $(options.panelEle).append($("<div>", {
        "class":"panel-caption",
        text: "描述&资源"
    }));
    //panel内容区
    contentEle = $("<div>", {"class":"panel-content"});
    contentEle.append($("<div>",{"class":"description"})
        .append("<span class='deco'></span>")
        .append($("<p>",{"text":options.description})));
    if(options.proper!=null){
    	 properEle = $("<div>",{"class":"properApi"})
         .append($("<div>",{"class":"panel-label",text:"属性&方法"}));
    }
   
    relateEle = $("<div>",{"class":"relateDemo"})
        .append($("<div>",{"class":"panel-label",text:"相关示例"}));
    if(options.proper!=null){
    	for(var i= 0,length = options.proper.length;i < length;i++){
            properEle.append($("<div>",{"class":"panel-link"})
                .append($("<a>",{"href":options.proper[i].href,name:options.proper[i].name})
                    .prepend($("<span>",{text:options.proper[i].text}))));
        }
    }
    
    for(var i= 0,length = options.relate.length;i < length;i++){
        relateEle.append($("<div>",{"class":"panel-link"})
            .append($("<a>",{"href":options.relate[i].href,name:options.relate[i].name})
            .prepend($("<span>",{text:options.relate[i].text}))));
    }
    contentEle.append(properEle);
    contentEle.append(relateEle);
    $(options.panelEle).append(contentEle);
    $(options.panelEle).append($("<div>",{"class":"panel-bottom"}));
    //给所有的属性、相关示例绑定click事件
    $(".panel-link a").each(function(){
        $(this).click(function(){
            var node,that = this,treeObj = window.parent.treeObj;
            node = treeObj ? treeObj.getNodeByParam("ename",that.name,null): null;
            if(node){
                //window.parent.navRoad(node.index,node);
                treeObj.selectNode(node);
            }
        })
    })
    //标题&操作按钮
    $(options.titleEle).append($("<div>",{"class":"title",text:options.title}))
        .append($("<div>",{"class":"operate"})
                .append($("<button>",{"class":"thumbnailLink collapse",text:"查看描述&资源"}))
                .append($("<button>",{"class":"thumbnailLink viewSource",text:"查看源代码"})));

    //.content-left自适应大小
    $(window).resize(function(){
        $(".content-left").css("width",$(".iframeContent").width()-$(".content-right").width()) ;
    });
    $(".content-left").css("width",$(".iframeContent").width()-$(".content-right").width()) ;
    //查看源代码弹出框
    SyntaxHighlighter.all();
    SyntaxHighlighter.highlight("source");//代码高亮
    
    $(".viewSource").click(function(){
        dialogObj.open();
    })
    $(".collapse").toggle(
        function () {
            $(".content-right").css({width:300,display:"block",overflow:"hidden"});
            $(this).html("关闭描述&资源");
            $(window).trigger("resize");
        },
        function () {
            $(".content-right").css({width:0,display:"none",overflow:"hidden"});
            $(this).html("查看描述&资源");
            $(window).trigger("resize");
        }
    );
    $(".ui-dialog").has(".source").css({
        "padding": "0",
        "padding-bottom": "15px",
        "background-color":  "#CECED1",
        height: "15px",
        width: "15px",
        "border":"1px solid #cccccc"
    });
    $(".ui-widget-header",$(".ui-dialog").has(".source")).css({
       
        "border":"none"
        
    });
}

function createThumbnail(options){
	for(var i = 0,length = options.length;i<length;i++){
        var screenshotLabelEle,screenshotLinkEle,listEle,screenshotEle,setting = $.extend({title:"",parentEle:"body"},options[i]);
        //panel标题
        listEle = $("<div>", {"class":"list"});
        screenshotEle = $("<div>", {"class":"screenshot"})
            .append($("<img>", {"class":"v-icon","src":setting.imgUrl}))
            .append($("<span>"));
        listEle.append(screenshotEle);
        screenshotLabelEle = $("<div>", {"class":"v-label"}).css("width",$(setting.parentEle).outerWidth()*0.4 - 100);
        screenshotLinkEle = $("<div>", {"class":"v-link"});
        listEle.append(screenshotLabelEle);
        listEle.append(screenshotLinkEle);
        screenshotLabelEle.append($("<span>", {"text":setting.title}));
        for(var j= 0,listlength = setting.list.length;j<listlength;j++){
            screenshotLinkEle.append($("<a>", {"class":"thumbnailLink",name:setting.list[j].name,text:setting.list[j].description,"href":setting.list[j].url}));
        }
        $(setting.parentEle).append(listEle);
    }
    $(".v-link a").each(function(){
        $(this).click(function(){
            var node,that = this,treeObj = window.parent.treeObj;
            node = treeObj ? treeObj.getNodeByParam("ename",that.name,null): null;
            if(node){
                //window.parent.navRoad(node.index,node);
                treeObj.selectNode(node);
                window.parent.document.getElementsByName("iframe").src = node.href;
                //$("#RightPane iframe").attr("src",node.href);
            }
        })
    })
}




//需加载的文件
/*
css_include("reset.css");
css_include("layout.css");
css_include("skin/default/theme.css");
css_include("skin/default/components/tree/zTreeStyle.css");
css_include("skin/default/components/jqgrid/jquery.jqgrid.css");
js_include("jquery-all.js");
js_include("validate.js");
js_include("WdatePicker.js");
js_include("widgets.js");
$js_path = "./lib/js/i18n/";
js_include("i18n_zh.js");
$js_path = "./lib/js/lib/";
js_include("tree.js");
js_include("grid.js");
js_include("biz.js");
$js_path = "./";
js_include("indexDepend/jquery.layout.js");
js_include("indexDepend/jquery.contextMenu.js");*/
//js_include("tree/treeDemoData.js");
