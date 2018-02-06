<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html  >
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common-util/image-util.js"></script>	
<%@ include file="../../common/header.jsp"%>
<style type="text/css">
#preview {
    border: 3px dashed #ccc;
    width: 300px;
    min-height: 300px;
    margin: 20px auto;
}
#preview span {
  font-size: 18px;
  text-align: center;
  display: block;
  color: #ccc;
  line-height: 300px;
  border: none;
  cursor: pointer;
}
#preview.hover {
    border: 3px dashed #0c0;
}

#preview img {
    display: block;
    margin: 10px auto;
}

#preview p {
    margin: 10px;
    font-size: 14px;
}

progress {
    width: 100%;
}

progress:after {
    content: '%';
}

.fail {
    background: #c00;
    padding: 2px;
    color: #fff;
}

.hidden {
    display: none !important;
}

</style>
</head>
<body>
	<div id="editDialog">
		<form id="marketStudentFormEdit">
			<div class="ui-table ui-widget ui-corner-all ui-border">
				<input type="hidden" id="edit_id" name="id" type="text"
					value="${marketStudent.id}" />
				<table class="table">
					<tr>
						<td class="inputLabelTd">QQ号码：</td>
						<td class="inputTd"> <input id="edit_qq" name="qq" type="text" class="text" value="${marketStudent.qq}" /></td>
						<td class="inputLabelTd">意向学科：</td>
						<td class="inputTd">
							<select onchange="xuekeChange(this.value);" style="float:left" class="input_select" name="subjectId" id="edit_subjectId">
								<c:forEach var="mr" items="${er.subject}">
									<option value="${mr.id}"> <c:out value="${mr.name}"></c:out> </option>
					             </c:forEach>
					        </select>
						</td>
					</tr>
					<tr>
						<td class="inputLabelTd">意向课程：</td>
						<td class="inputTd">
							<input id="edit_notes" name="notes" type="text" class="text" value="${marketStudent.notes}" list="courseList"/> 
							<dataList style="float:left" class="input_select" id="courseList">
								<c:forEach var="mr" items="${er.course}">
									<option value="${mr.name}" > <c:out value="${mr.name}"></c:out> </option>
					             </c:forEach>
							</dataList>
							<input id="edit_time" name="time" type="hidden" value="${marketStudent.time}" />
						</td>
						<td class="inputLabelTd">上传图片：</td>
						<td class="inputTd">
							<input id="fileData" name="fileUrl" type="hidden"> 
							<input id="file" type="file" class="text" onchange="javascript:setImagePreviews();"/>
						</td>
					</tr>
					<tr>
						<td class="inputTd" colspan="4">
							<div id="preview"
								style="width: 100%; margin-top: 5px; margin-bottom: 5px; text-align: center;">
								<span>可拖拽上传区域</span>
							</div>
							<p id="upload" class="hidden"><label>Drag &amp; drop not supported, but you can still upload via this input field:<br><input type="file"></label></p>
		                    <p id="filereader">File API &amp; FileReader API not supported</p>
		                    <p id="formdata">XHR2's FormData is not supported</p>
		                    <p id="progress">XHR2's upload progress isn't supported</p>
							
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/common-util/image-util.js"></script>	
	<script type="text/javascript">
	var createDate = new Date().format('yyyy-MM-dd hh:mm:ss'); //获取今日时间
	/* 拖拽上传 begin*/
	var fileItems = [];
	var holder = document.getElementById('preview'),
	support = {
        filereader: document.getElementById('filereader'),
        formdata: document.getElementById('formdata'),
        progress: document.getElementById('progress')
    },
    progress = document.getElementById('uploadprogress'),
    fileupload = document.getElementById('upload');
	"filereader formdata progress".split(' ').forEach(function(api) {
         if (tests[api] === false) {
             support[api].className = 'fail';
         } else {
             support[api].className = 'hidden';
         }
    });
	if (tests.dnd) {
        holder.ondragover = function() {
            this.className = 'hover';
            return false;
        };
        holder.ondragend = function() {
            this.className = '';
            return false;
        };
        holder.ondrop = function(e) {
            this.className = '';
            e.preventDefault();
            fileItems = e.dataTransfer.files;
            viewHolder(fileItems);
        }
    } else {
        fileupload.className = 'hidden';
        fileupload.querySelector('input').onchange =  function() {
            fileItems = this.files;
            viewHolder(fileItems);
        };
    }
	function viewHolder(files) {
        while (holder.hasChildNodes()) {
            holder.removeChild(holder.firstChild);
        }
        for (var i = 0; i < files.length; i++) {
            previewfile(files[i]);
        }
    }
	function previewfile(file) {
        if (tests.filereader === true && acceptedTypes[file.type] === true) {
            var reader = new FileReader();
            reader.onload = function(event) {
                var image = new Image();
                image.src = event.target.result;
                image.width =  $(window).width()*0.6; // a fake resize
                holder.appendChild(image);
                $("#fileData").val(image.src);
            };
            reader.readAsDataURL(file);
        } else {
            holder.innerHTML += '<p>Uploaded ' + file.name + ' ' + (file.size ? (file.size / 1024 | 0) + 'K' : '');
        }
    }
	/* 拖拽上传end */
$(function() {
	$("#edit_time").val(createDate);
	//绑定提交按钮click事件
	$("#submit").click(function() {
		$("#submit").prop('disabled', true).css({'cursor':'not-allowed'});
		showMessage("正在处理，请稍后...");
		if(!biz.validate("valid",$('#marketStudentFormEdit')[0])){
			showWarn("数据验证失败",3000);
			$("#submit").prop('disabled', false).css({'cursor':'pointer'});
			return;
		}
		var options = {
				url : "<m:url value='/marketStudent/addMarketStudent.do'/>",
				type : "post",
					dataType:"json",
					success : function(d) {
						if(d.status){
							showMessage(d.message,"","",function(){
								window.parent.closeAdd();
					     		window.parent.doSearch();
							});
						}else{
							showMessage("插入数据失败，请检查该数据是否已存在");
						}
					}
			};
		//验证图片
		var fileName = $("#file").val();
		//如果有图片要上传，进行图片上次处理
		if(fileName.length>1){  
			var file = $("#file").get(0).files[0]; 
			PreviewFile(file,$('#marketStudentFormEdit'),options);
	    }else{
	    	// 直接将options传给ajaxForm（不上传图片）
	  		$('#marketStudentFormEdit').ajaxSubmit(options);
	    }
	});
	/*编辑表单数据验证*/
	new biz.validate({
		id:"#marketStudentFormEdit",
		rules:{
		}
	}); 
	$('#preview span').on("click",function(e){
        $('#file').click();
   	})
 
});

function xuekeChange(val){
	$ .ajax({
		url: "<m:url value='/marketStudent/getCourseList.do'/>?key="+val,
		cache:false,
		dataType:"json",
		success: function(data, textStatus, jqXHR){
			$('#courseList option').remove();
			for(var i in data.course){
				if(data.course[i].id){
					$('#courseList').append('<option value="'+data.course[i].name+'">'+data.course[i].name+' </option>');
				}
			}
			if(!data.course[0]){
				$('#courseList option').remove();
			}
		}
	});
}
</script>

</body>
</html>
