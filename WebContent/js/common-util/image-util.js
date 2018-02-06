/**
 * 这是基于html5的前端图片工具，压缩工具。
 */
var acceptedTypes = {
                'image/png': true,
                'image/jpeg': true,
                'image/jpg': true,
                'image/gif': true
	},
	tests = {
	        filereader: typeof FileReader != 'undefined',
	        dnd: 'draggable' in document.createElement('span'),
	        formdata: !!window.FormData,
	        progress: "upload" in new XMLHttpRequest
	}

var PreviewFile = function(file,formObj,options){
	if (tests.filereader === true && acceptedTypes[file.type] === true) {
		 var reader = new FileReader();
		 var size = file.size;
		 if(size>2097152)showWarn("图片过大，不得超过2M");
		 else if(size>1048576 && size<2097152){  
				//压缩图片
				reader.onload = function(event) {
				 $("#fileData").val(ImageResizer());
				 formObj.ajaxSubmit(options);
				}
			}else{
				// 绑定load事件
				reader.onload = function(e) {
					$("#fileData").val(e.target.result);
					formObj.ajaxSubmit(options);
				}
			}
		// 读取File对象的数据
		reader.readAsDataURL($("#file").get(0).files[0]);
	}else{
		showWarn("格式不正确,支持的图片格式为：JPEG、JPG、GIF、PNG！");  
		return false;  
	}
}


var setImagePreviews  = function(avalue){
	 var docObj = document.getElementById("file");
     var dd = document.getElementById("preview");
     dd.innerHTML = "";
     var fileList = docObj.files;
     for (var i = 0; i < fileList.length; i++) {            
         dd.innerHTML += "<div align='center'> <img id='img" + i + "'  /> </div>";
         var imgObjPreview = document.getElementById("img"+i); 
         if (docObj.files && docObj.files[i]) {
             //火狐下，直接设img属性
             imgObjPreview.style.display = 'block';
             //imgObjPreview.style.width = '250px';//设置图片显示尺寸
             //imgObjPreview.style.height = '280px';
             //imgObjPreview.src = docObj.files[0].getAsDataURL();
             //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
             imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
             imgObjPreview.width = $(window).width()*0.6;
             imgObjPreview.maxHeight = $(window).width()*0.6;
         }
         else {
             //IE下，使用滤镜
             docObj.select();
             var imgSrc = document.selection.createRange().text;
             alert(imgSrc)
             var localImagId = document.getElementById("img" + i);
             //必须设置初始大小
             localImagId.style.width = "250px";
             localImagId.style.height = "280px";
             //图片异常的捕捉，防止用户修改后缀来伪造图片
             try {
                 localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                 localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
             }
             catch (e) {
                 alert("您上传的图片格式不正确，请重新选择!");
                 return false;
             }
             imgObjPreview.style.display = 'none';
             document.selection.empty();
         }
     }  
     return true;
}

var ImageResizer = function(){
	var w = 500,
   	h = 500,
   	scale = w / h,
   	quality = 0.7;  // 默认图片质量为0.7
    //生成canvas
    var canvas = document.createElement('canvas');
    var ctx = canvas.getContext('2d');
 	// 创建属性节点
    var anw = document.createAttribute("width");
    anw.nodeValue = w;
    var anh = document.createAttribute("height");
    anh.nodeValue = h;
    canvas.setAttributeNode(anw);
    canvas.setAttributeNode(anh); 
    var image = $("#preview img")[0];
    ctx.drawImage(image, 0, 0, w, h);
	// 图像质量
    resizeImgBase64 = canvas.toDataURL('image/jpeg', quality );
    return resizeImgBase64;
};

function dataURLtoBlob(dataurl) {
    var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    while(n--){
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new Blob([u8arr], {type:mime});
}