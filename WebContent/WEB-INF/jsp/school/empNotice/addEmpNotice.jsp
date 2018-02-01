<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<style type="text/css">
.ace-file-input {
	display: inline-block;
	width: 200px;
	float: left;
	left: -20px
}
</style>
<%-- <%@ include file="../../common/header.jsp"%>  --%>
<%@ include file="../../common/ace.jsp"%>
<%-- <script type="text/javascript" src="<%=request.getContextPath() %>/js/bizLib/jquery.form.js"></script> --%>
<!-- page specific plugin scripts -->
<%-- <script src="<%=request.getContextPath() %>/static/js/jquery-ui-1.10.3.custom.min.js"></script> --%>
<%-- <script src="<%=request.getContextPath() %>/static/js/jquery.ui.touch-punch.min.js"></script> --%>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/bizLib/jquery.form.js"></script>
<script
	src="<%=request.getContextPath() %>/static/js/markdown/markdown.min.js"></script>
<script
	src="<%=request.getContextPath() %>/static/js/markdown/bootstrap-markdown.min.js"></script>
<script
	src="<%=request.getContextPath() %>/static/js/jquery.hotkeys.min.js"></script>
<script
	src="<%=request.getContextPath() %>/static/js/bootstrap-wysiwyg.min.js"></script>
<script src="<%=request.getContextPath() %>/static/js/bootbox.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.1.0.js"></script>
</head>
<body>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
		<iframe id="actionTarget" name="actionTarget" style="display: none"></iframe>
		<div class="main-container-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<form id="wordform" target="actionTarget" name="empNoticeFormEdit"
							method="post"
							action="<%=request.getContextPath()%>/file/fileUpload"
							enctype="multipart/form-data">
							<h4 class="header green clearfix">
								公告标题：<input id="noticeTitle" name="noticeTitle" type="text"
									class="noticeTitle text" value="" /> <span
									class="block pull-right"> <small class="grey middle">选择编辑器样式:</small>

									<span class="btn-toolbar inline middle no-margin"> <span
										data-toggle="buttons" class="btn-group no-margin"> <label
											class="btn btn-sm btn-yellow">1<input type="radio"
												value="1" />
										</label> <label class="btn btn-sm btn-yellow active">2<input
												type="radio" value="2" />
										</label> <label class="btn btn-sm btn-yellow">3<input
												type="radio" value="3" />
										</label>
									</span>
								</span>
								</span>
							</h4>
							<textarea class="wysiwyg-editor" id="noticeContent"
								name="noticeContent" style="width: 100%;"></textarea>

							<div class="widget-toolbox padding-4 clearfix">
								<div class="btn-group pull-left">
									<button class="btn btn-sm btn-grey" id="reset" type="button">
										<i class="icon-remove bigger-125"></i> 清空
									</button>
								</div>

								<div class="btn-group pull-right">
									<input id="file_upload" name="file_upload" type="file" /> <input
										id="filePath" name="filePath" type="hidden" class="filepath" />
									<!-- 保存后台返回的文件保存地址 -->
									<button class="btn btn-sm btn-success" id="submit_button"
										type="button">
										<i class="icon-globe bigger-125"></i> 发布 <i
											class="icon-arrow-right icon-on-right bigger-125"></i>
									</button>
								</div>
							</div>
						</form>
						<!-- PAGE CONTENT ENDS -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
		<!-- /.main-container-inner -->
	</div>
	<!-- /.main-container -->
	<script type="text/javascript">
/* var createDate = new Date().format('yyyy-MM-dd hh:mm:ss');//获取今日时间 */
jQuery(function($){
	$('input[type="file"]').ace_file_input({
		no_file:'请选择...',
		btn_choose:'选择',
		btn_change:'更换',
		droppable:false,
		thumbnail:false,
	})
	
	/* $("#edit_createTime").val(createDate); */
	function showErrorAlert (reason, detail) {
		var msg='';
		if (reason==='unsupported-file-type') { msg = "Unsupported format " +detail; }
		else {
			console.log("error uploading file", reason, detail);
		}
		$('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'+ 
		 '<strong>File upload error</strong> '+msg+' </div>').prependTo('#alerts');
	}

	//$('#noticeContent').ace_wysiwyg();//this will create the default editor will all buttons

	//but we want to change a few buttons colors for the third style
	$('#noticeContent').ace_wysiwyg({
		toolbar:
		[
			'font',
			null,
			'fontSize',
			null,
			{name:'bold', className:'btn-info'},
			{name:'italic', className:'btn-info'},
			{name:'strikethrough', className:'btn-info'},
			{name:'underline', className:'btn-info'},
			null,
			{name:'insertunorderedlist', className:'btn-success'},
			{name:'insertorderedlist', className:'btn-success'},
			{name:'outdent', className:'btn-purple'},
			{name:'indent', className:'btn-purple'},
			null,
			{name:'justifyleft', className:'btn-primary'},
			{name:'justifycenter', className:'btn-primary'},
			{name:'justifyright', className:'btn-primary'},
			{name:'justifyfull', className:'btn-inverse'},
			null,
			{name:'createLink', className:'btn-pink'},
			{name:'unlink', className:'btn-pink'},
			null,
			{name:'insertImage', className:'btn-success'},
			null,
			'foreColor',
			null,
			{name:'undo', className:'btn-grey'},
			{name:'redo', className:'btn-grey'}
		],
		'wysiwyg': {
			fileUploadError: showErrorAlert
		}
	}).prev().addClass('wysiwyg-style2');


	$('[data-toggle="buttons"] .btn').on('click', function(e){
		var target = $(this).find('input[type=radio]');
		var which = parseInt(target.val());
		var toolbar = $('#noticeContent').prev().get(0);
		if(which == 1 || which == 2 || which == 3) {
			toolbar.className = toolbar.className.replace(/wysiwyg\-style(1|2)/g , '');
			if(which == 1) $(toolbar).addClass('wysiwyg-style1');
			else if(which == 2) $(toolbar).addClass('wysiwyg-style2');
		}
	});
	
	$('#submit_button').click(function(){
		if($("#noticeTitle").val()=="") {alert("请填写公告标题"); return;}
		if($("#noticeContent").val()=="") {alert("请填写公告内容"); return;}
/* 		$(this).parent().find('#image_productClassName').val($.trim($("#edit_storeId").find("option:selected").text()));*/
		//var $realPathobj = $(this).parents().find('#filepath')[0];  
		if($("#file_upload").val()!=""){
			var options = {
					url : "<%=request.getContextPath()%>/upload/fileUpload",
					type : "post",
					dataType:"json", 
					processData: false,
			        contentType: false, 
					success : function(d) {
						if(d&&d.code == '0000'){//d存在并且d有d.code
							$('#filePath').val(d.fileAddr); 
							normalFormSubmit(1);//普通文本域请求
						}else{
							 alert("文件上传失败"); 
						}
					},
					error:function(){
						 alert("文件上传失败"); 
					}
				};
				$('#wordform').ajaxSubmit(options);			
		}else{
			normalFormSubmit(2);
		}
		
		
	});
	
	function normalFormSubmit(num){
		if(num==1){
			var paramDatas = {
					noticeTitle:$("#noticeTitle").val(),
					noticeContent:$("#noticeContent").val(),
					fileUrl:$("#filePath").val(),
				};
		}else{
			var paramDatas = {
					noticeTitle:$("#noticeTitle").val(),
					noticeContent:$("#noticeContent").val()
				};
		}
		$.ajax({
	 		   type: "post",
	 		   url : "<%=request.getContextPath()%>/empNotice/addEmpNotice.do",
	 		   data: paramDatas,
	 		   cache: false,
	 		   dataType:"json",
	           error: function() {
	          		alert("请求失败");
	           },
	           success : function(d) {
					if(d.status){
						alert("公告发布成功");
						doSearch();
					}else{
						alert(d.message);
					}
				}
	 		});
	}
	
    //查询Grid数据
    function doSearch(isStayCurrentPage){
    	if(!isStayCurrentPage)window.parent.gridObj.setGridParam({"page":"1"});
    	 window.location.reload();//刷新当前页面
    	window.parent.gridObj.trigger('reloadGrid');//刷新父页面
    }
	
	$('#reset').on('click', function(){
			$("#noticeTitle").val("");
			$("#noticeContent").val("");
			$(this).parents().find('.pull-right').find('.icon-remove').click();//清除input框文件
			//$('.icon-remove').trigger("click");
	});
	//Add Image Resize Functionality to Chrome and Safari
	//webkit browsers don't have image resize functionality when content is editable
	//so let's add something using jQuery UI resizable
	//another option would be opening a dialog for user to enter dimensions.
		if ( typeof jQuery.ui !== 'undefined' && /applewebkit/.test(navigator.userAgent.toLowerCase()) ) {
			
			var lastResizableImg = null;
			function destroyResizable() {
				if(lastResizableImg == null) return;
				lastResizableImg.resizable( "destroy" );
				lastResizableImg.removeData('resizable');
				lastResizableImg = null;
			}
	
			var enableImageResize = function() {
				$('.wysiwyg-editor')
				.on('mousedown', function(e) {
					var target = $(e.target);
					if( e.target instanceof HTMLImageElement ) {
						if( !target.data('resizable') ) {
							target.resizable({
								aspectRatio: e.target.width / e.target.height,
							});
							target.data('resizable', true);
							
							if( lastResizableImg != null ) {//disable previous resizable image
								lastResizableImg.resizable( "destroy" );
								lastResizableImg.removeData('resizable');
							}
							lastResizableImg = target;
						}
					}
				})
				.on('click', function(e) {
					if( lastResizableImg != null && !(e.target instanceof HTMLImageElement) ) {
						destroyResizable();
					}
				})
				.on('keydown', function() {
					destroyResizable();
				});
	    	}
			enableImageResize();
		}
	});
	</script>
</body>
</html>
