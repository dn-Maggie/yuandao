/**
 *@class I18N
 *description:js multi language class,should be loaded before others,singleton.
 *author:ganjp
 *date:2010-8-16
 */
var I18N = null;
if(!I18N){
I18N = {
	save: "save",
	reset: "reset",	
	close: "close",
	confirm: "confirm",
	cancel: "cancel",
	yes: "yes",
	no: "no",
	refresh: "refresh",
	loading: "loading...",
	promp: "information",
	error: "error",
	warn:  "warn",
	add: "add",
	edit: "edit",
	del: "delete",
	search: "search",
	choose: "please choose...",
	
	msg_del_sucess: "delete records success",
	msg_del_confirm: "are you sure delete records?",
	msg_no_sel_del_record: "please first select records by deleted!",
	msg_no_sel_edit_record: "please first select records by edited!",
	msg_no_sel_view_record: "please first select records by viewed!",
	msg_single_edit_record: "only single record by edited",
	msg_single_view_record: "only single record by viewed",
	msg_bg_verify_fail: "Background verification failed!",
	msg_fg_verify_fail: "Frontground verification failed",
	msg_fg_verify_fail_tip: "there is error input char, so not save !",
	msg_saving: "saving ...",
	msg_system_error: "system error",
	msg_set_form_id: "please set form ID:",
	msg_pwd_not_match: "Passwords do not match",
	msg_cd_format_error: "This field should only contain letters, numbers, - and _",
	msg_chinese_format_error: "This field should only contain chinese",
	
	grid_bbar_displayMsg: "record {0} - {1} of {2}",
	grid_bbar_emptyMsg: "no records",
	
	ajax_readyState:{
		0: "(Uninitialized) the send( ) method has not yet been invoked",
        1: "(Loading) the send( ) method has been invoked, request in progress.",
        2: "(Loaded) the send( ) method has completed, entire response received.",
        3: "(Interactive) the response is being parsed.",
        4: "(Completed) the response has been parsed, is ready for harvesting."
    },
    dropdownlist_mustcheck_error:"error：some required value must be selected!",
    dropdownlist_limit_error: "error：The selected quantity is more than limited value of property 'maxchecked'!",
    dropdownlist_defaultValue:"choose...",
    dropdownlist_selectAll:"select all",
    dropdownlist_bConfirm:"Confirm",
    dropdownlist_bCancel:"Cancel",
    dropdownlist_bClear:"Clear",
    dropdownlist_mostChecked_1:"You can select up to",
    dropdownlist_mostChecked_2:"",
    file_bDefaultValue:"Browse...",
    file_suffix:"Please select correct file，suffix：{0}",
    file_name:"Please select correct file，file name:{0}",
    ajaxfile_fail:"Upload failed",
    ajaxfile_drop:"Drop files here to upload",
    ajaxfile_progress:"{percent}% of {total_size} ",
    ajaxfile_retry:"retry",
    ajaxfile_typeError: "{file} has an invalid extension. Valid extension(s): {extensions}.",
    ajaxfile_sizeError: "{file} is too large, maximum file size is {sizeLimit}.",
    ajaxfile_minSizeError: "{file} is too small, minimum file size is {minSizeLimit}.",
    ajaxfile_emptyError: "{file} is empty, please select files again without it.",
    ajaxfile_noFilesError: "No files to upload.",
    ajaxfile_onLeave: "The files are being uploaded, if you leave now the upload will be cancelled.",
  
    validator_required:"required",
    validator_remote:"Please correct the field",
    validator_email: "Please enter the correct format e-mail", 
    validator_url: "Please enter a valid URL", 
    validator_date: "Please enter a valid date", 
    validator_dateISO: "Please enter a valid date(ISO).",  
    validator_number: "Please enter a valid number",  
    validator_digits: "Please enter a integer",  
    validator_creditcard: "Please enter a valid credit card number",  
    validator_equalTo: "Please re-enter the same value",  
    validator_accept: "Please enter the string that has a legitimate extension", 
    validator_maxlength_1: "The maximum allowable length of ",  
    validator_maxlength_2: " characters", 
    validator_minlength_1:"The minimum allowed length of ",
    validator_minlength_2:" characters",
    validator_rangelength_1:"Allowed length is between ",
    validator_rangelength_2:" and",
    validator_rangelength_3:"",
    
    validator_range_1:"Please enter a value between ",
    validator_range_2:"and",
    validator_range_3:"",
    
    validator_max_1:"Please enter a maximum value of ",
    validator_max_2:"",
    
    validator_min_1:"Please enter the value of a minimum of ",
    validator_min_2:"",
    validator_specialSignal:"Contains special characters are not allowed!",
    validator_chineseOnly:"The only input Chinese characters, a character, two bytes",
    validator_letterOnly:"Letter only",
    validator_IP:"Please enter the correct IP address!",
    validator_Port:"Please enter the correct port number!",
    validator_Postalcode:"Wrong postal code format",
    validator_mobile:"Wrong phone number format",
    validator_alnum:"Can only include letters , numbers",
    validator_naturalnum:"Enter only natural numbers",
    validator_idcardno:"Please enter identity card number",
    validator_time:"Please enter the time format",
    
    param_error:"param error，please reset!As follows:",
	ajax_error:"ajax request error:"

};
};

;(function($){
	/**
	 * jqGrid English Translation
	 * Tony Tomov tony@trirand.com
	 * http://trirand.com/blog/ 
	 * Dual licensed under the MIT and GPL licenses:
	 * http://www.opensource.org/licenses/mit-license.php
	 * http://www.gnu.org/licenses/gpl.html
	**/
	$.jgrid = $.jgrid || {};
	$.extend($.jgrid,{
		defaults : {
			recordtext: "View {0} - {1} of {2}",
			emptyrecords: "No records to view",
			loadtext: "Loading...",
			pgtext : "Page {0} of {1}"
		},
		search : {
			caption: "Search...",
			Find: "Find",
			Reset: "Reset",
			odata : ['equal', 'not equal', 'less', 'less or equal','greater','greater or equal', 'begins with','does not begin with','is in','is not in','ends with','does not end with','contains','does not contain'],
			groupOps: [	{ op: "AND", text: "all" },	{ op: "OR",  text: "any" }	],
			matchText: " match",
			rulesText: " rules"
		},
		edit : {
			addCaption: "Add Record",
			editCaption: "Edit Record",
			bSubmit: "Submit",
			bCancel: "Cancel",
			bClose: "Close",
			saveData: "Data has been changed! Save changes?",
			save:"Save",
			bYes : "Yes",
			bNo : "No",
			bExit : "Cancel",
			msg: {
				required:"Field is required",
				number:"Please, enter valid number",
				minValue:"value must be greater than or equal to ",
				maxValue:"value must be less than or equal to",
				email: "is not a valid e-mail",
				integer: "Please, enter valid integer value",
				date: "Please, enter valid date value",
				url: "is not a valid URL. Prefix required ('http://' or 'https://')",
				nodefined : " is not defined!",
				novalue : " return value is required!",
				customarray : "Custom function should return array!",
				customfcheck : "Custom function should be present in case of custom checking!"
				
			}
		},
		view : {
			caption: "View Record",
			bClose: "Close"
		},
		del : {
			caption: "Delete",
			msg: "Delete selected record(s)?",
			bSubmit: "Delete",
			bCancel: "Cancel"
		},
		nav : {
			edittext: "Edit",
			edittitle: "Edit selected row",
			addtext:"Add",
			addtitle: "Add new row",
			deltext: "Delete",
			deltitle: "Delete selected row",
			searchtext: "Find",
			searchtitle: "Find records",
			refreshtext: "Reload",
			refreshtitle: "Reload Grid",
			alertcap: "Warning",
			alerttext: "Please, select row",
			viewtext: "View",
			viewtitle: "View selected row",
			prev:"Prev",
            next:"Next"
		},
		col : {
			caption: "Select columns",
			bSubmit: "Ok",
			bCancel: "Cancel"
		},
		errors : {
			errcap : "Error",
			nourl : "No url is set",
			server : "Server Error",
			norecords: "No records to process",
			model : "Length of colNames <> colModel!"
		},
		formatter : {
			integer : {thousandsSeparator: ",", defaultValue: '0'},
			number : {decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, defaultValue: '0.00'},
			currency : {decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "", suffix:"", defaultValue: '0.00'},
			date : {
				dayNames:   [
					"Sun", "Mon", "Tue", "Wed", "Thr", "Fri", "Sat",
					"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
				],
				monthNames: [
					"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
					"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
				],
				AmPm : ["am","pm","AM","PM"],
				S: function (j) {return j < 11 || j > 13 ? ['st', 'nd', 'rd', 'th'][Math.min((j - 1) % 10, 3)] : 'th';},
				srcformat: 'Y-m-d',
				newformat: 'n/j/Y',
				masks : {
					// see http://php.net/manual/en/function.date.php for PHP format used in jqGrid
					// and see http://docs.jquery.com/UI/Datepicker/formatDate
					// and https://github.com/jquery/globalize#dates for alternative formats used frequently
					// one can find on https://github.com/jquery/globalize/tree/master/lib/cultures many
					// information about date, time, numbers and currency formats used in different countries
					// one should just convert the information in PHP format
					ISO8601Long:"Y-m-d H:i:s",
					ISO8601Short:"Y-m-d",
					// short date:
					//    n - Numeric representation of a month, without leading zeros
					//    j - Day of the month without leading zeros
					//    Y - A full numeric representation of a year, 4 digits
					// example: 3/1/2012 which means 1 March 2012
					ShortDate: "n/j/Y", // in jQuery UI Datepicker: "M/d/yyyy"
					// long date:
					//    l - A full textual representation of the day of the week
					//    F - A full textual representation of a month
					//    d - Day of the month, 2 digits with leading zeros
					//    Y - A full numeric representation of a year, 4 digits
					LongDate: "l, F d, Y", // in jQuery UI Datepicker: "dddd, MMMM dd, yyyy"
					// long date with long time:
					//    l - A full textual representation of the day of the week
					//    F - A full textual representation of a month
					//    d - Day of the month, 2 digits with leading zeros
					//    Y - A full numeric representation of a year, 4 digits
					//    g - 12-hour format of an hour without leading zeros
					//    i - Minutes with leading zeros
					//    s - Seconds, with leading zeros
					//    A - Uppercase Ante meridiem and Post meridiem (AM or PM)
					FullDateTime: "l, F d, Y g:i:s A", // in jQuery UI Datepicker: "dddd, MMMM dd, yyyy h:mm:ss tt"
					// month day:
					//    F - A full textual representation of a month
					//    d - Day of the month, 2 digits with leading zeros
					MonthDay: "F d", // in jQuery UI Datepicker: "MMMM dd"
					// short time (without seconds)
					//    g - 12-hour format of an hour without leading zeros
					//    i - Minutes with leading zeros
					//    A - Uppercase Ante meridiem and Post meridiem (AM or PM)
					ShortTime: "g:i A", // in jQuery UI Datepicker: "h:mm tt"
					// long time (with seconds)
					//    g - 12-hour format of an hour without leading zeros
					//    i - Minutes with leading zeros
					//    s - Seconds, with leading zeros
					//    A - Uppercase Ante meridiem and Post meridiem (AM or PM)
					LongTime: "g:i:s A", // in jQuery UI Datepicker: "h:mm:ss tt"
					SortableDateTime: "Y-m-d\\TH:i:s",
					UniversalSortableDateTime: "Y-m-d H:i:sO",
					// month with year
					//    Y - A full numeric representation of a year, 4 digits
					//    F - A full textual representation of a month
					YearMonth: "F, Y" // in jQuery UI Datepicker: "MMMM, yyyy"
				},
				reformatAfterEdit : false
			},
			baseLinkUrl: '',
			showAction: '',
			target: '',
			checkbox : {disabled:true},
			passwordStr:'*',
			idName : 'id'
		}
	});
	})(jQuery);
