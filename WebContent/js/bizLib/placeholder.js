$(function(){
	if (!('placeholder' in $("<input/>")[0])) {
		$("*[placeholder]").each(function(index,value){
			var obj = $(this);
			if(!$(obj).attr("id")){
				var now = new Date();
		        $(obj).attr('id', 'lbl_placeholder' + now.getSeconds() + now.getMilliseconds());
			}
			
			var $label = $('<span>', {
		        html: $(obj).val() ? '' : $(obj).attr("placeholder"),
		        'for': $(obj).attr("id"),
		        css:
		            {
		                position: 'absolute',
		                cursor: 'text',
		                color: '#a9a9a9',
		                fontSize: $(obj).css('fontSize'),
		                fontFamily: $(obj).css('fontFamily')
		            }
		    }).insertAfter($(obj));
			
			// 绑定事件
		    var _resetPlaceholder = function () {
		        if ($(obj).val()) { $label.html(null); }
		        else {
		        	$label.css({ marginTop: GetStringNumValue($(obj).css('marginTop')) + 0 + 'px', marginLeft: '-' + (GetStringNumValue($(obj).css('width')) - 6) + 'px' });
		            $label.html($(obj).attr("placeholder"));
		        }
		    };
		    $label.css({ marginTop: GetStringNumValue($(obj).css('marginTop'))+ 0 + 'px', marginLeft: '-' + (GetStringNumValue($(obj).css('width')) - 6) + 'px' });
		    $(obj).on('focus blur input keyup propertychange resetplaceholder', _resetPlaceholder);
		    $label.on('click', function(){
		    	$(obj).focus();
		    });
		});
	}
});

function GetStringNumValue(str){
	var n = str.match(/\d/g);
	return n.join("");
}