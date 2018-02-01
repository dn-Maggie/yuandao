package com.dongnao.workbench.common;

/**
 * 描述：常量类
 * 
 * @author joan.xiong
 * 
 * @version 1.0 2016-03-21
 */
public class Constant {
	/**
	 * ResultMessage消息统一返回状态(0是失败)
	 */
	public static final Integer RESULTMESSAGE_STATUS_0 = 0;
	/**
	 * ResultMessage消息统一返回状态(1是成功)
	 */
	public static final Integer RESULTMESSAGE_STATUS_1 = 1;

	/**
	 * 日期格式字符串
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * 日期时间格式字符串
	 */
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 开始时间
	 */
	public static final String START_TIME = " 00:00:00";

	/**
	 * 结束时间
	 */
	public static final String END_TIME = " 23:59:59";

	/**
	 * 换行符
	 */
	public static final Object NEWLINECHAR = "<br>";

	/**
	 * 年第一天字符常量
	 */
	public static final String MONTHDAYCHAR = "-01-01";
	/**
	 * 年最后一天字符常量
	 */
	public static final String LASTMONTHDAYCHAR = "-12-31";

	/**
	 * 日字符常量
	 */
	public static final String DAYCHAR = "-01";

	/**
	 * 日期分割字符常量
	 */
	public static final String SEPCHAR = ".";

	/**
	 * 日期分割字符常量
	 */
	public static final String DATESEPCHAR = "-";

	/**
	 * 下划线常量
	 */
	public static final String UNDERLINE = "_";

	/**
	 * 百分号常量
	 */
	public static final String PERCENT = "%";

	/**
	 * 逗号分割字符常量
	 */
	public static final String COMMACHAR = ",";

	/**
	 * 单引号
	 */
	public static final String SINGLE_QUOTES = "'";

	/**
	 * 当前登录用户的保存session的KEY
	 */
	public static final String LOGIN_USER_KEY = "_j2ee_user";

	/**
	 * 登录用户保存资源权限session的key
	 */
	public static final String RIGHT_USER_KEY = "_right_user";

	/**
	 * 当前需要权限验证的URL属性的KEY
	 */
	public static final String APP_RIGHT_KEY = "_j2ee_right";

	/**
	 * 当前位置URL对象PID的MAP结构
	 */
	public static final String MODULE_LOCATION_MID_MAP = "ModuleLocationPidMap";

	/**
	 * 当前位置URL对象URL的MAP结构
	 */
	public static final String MODULE_LOCATION_URL_MAP = "ModuleLocationURLMap";

	/**
	 * 应用的数据字典KEY
	 */
	public static final String APP_DICT_KEY = "_j2ee_dict";

	/**
	 * 成功标记
	 */
	public static final String FLAG_SUCCESS = "success";

	/**
	 * 失败标记
	 */
	public static final String FLAG_FAILURE = "failure";

	/**
	 * 可用字符串常量"1"
	 */
	public static final String ENABLE_VALUE = "1";

	/**
	 * 不可用字符串常量"0"
	 */
	public static final String DISABLE_VALUE = "0";

	/**
	 * 已删除
	 */
	public static final Integer ISDELETE_TRUE = 0;

	/**
	 * 未删除
	 */
	public static final Integer ISDELETE_FALSE = 1;

	/**
	 * 加号(+)字符串分割常量
	 */
	public static final String CHAR_PLUS = "\\+";
	/**
	 * 加号分号(+;)组合字符串分割常量
	 */
	public static final String SPLIT_PLUS_SEMI = "\\+;";
	/**
	 * 句号分号(.;)组合字符串分割常量
	 */
	public static final String SPLIT_DOT_SEMI = "\\.;";

	/**
	 * 句号分号(.)组合字符串分割常量
	 */
	public static final String SPLIT_SEPCHAR = "\\.";

	/**
	 * 星号(*)字符串分割常量
	 */
	public static final String CHAR_STAR = "\\*";

	/** ------------系统配置变量名称定义------------ begin **/
	/**
	 * 功能名称：file_upload_maxSize(文件上传大小)
	 */
	public static final String FILE_UPLOAD_MAX_SIZE = "file_upload_maxSize";

	/**
	 * 功能名称：file_root_path(文件上传根路径)
	 */
	public static final String FILE_ROOT_PATH = "file_root_path";

	/**
	 * 功能名称：upload_image_type(上传图片类型)
	 */
	public static final String UPLOAD_IMAGE_TYPE = "upload_image_type";

	/***
	 * 生成静态页中图片临时存放目录
	 */
	public static final String FILE_TEMP_ROOT_PATH = "file_temp_root_path";
	/***
	 * 是否使用静态页
	 */
	public static final String USE_STATIC_PAGE = "use_static_page";

	/**
	 * 功能名称：default_session_time(session失效时间)
	 */
	public static final String DEFAULT_SESSION_TIME = "default_session_time";

	/**
	 * 功能名称：web_sys_admin(系统超级管理员)
	 */
	public static final String WEB_SYS_ADMIN = "web_sys_admin";
	/**
	 * 系统超级管理员
	 */
	public static final String SUPER_ADMIN = "admin";

	/**
	 * 功能名称：Webservice加密密钥
	 */
	public static final String WSI_KEY = "wsi_key";

	/**
	 * 功能名称：Initial_password（初始密码）
	 */
	public static final String INITIAL_PASSWORD = "Initial_password";
	
	/**
	 * 功能名称：Initial_roleid（初始角色）
	 */
	public static final String INITIAL_ROLEID = "Initial_roleid";

	/**
	 * level_1(警示级别)
	 */
	public static final String LEVEL_1 = "level_1";

	/**
	 * level_2(警示级别)
	 */
	public static final String LEVEL_2 = "level_2";
	/** ------------系统配置变量名称定义------------ end **/

	/**
	 * 导入操作：0-中止操作（Excel中的数据都不导入）
	 */
	public static final String IMPORTOPER_STOP = "0";

	/**
	 * 导入操作：1-部分导入（对重复数据不进行处理，只导入不重复的数据）
	 */
	public static final String IMPORTOPER_DEPART = "1";

	/**
	 * 导入操作：2-全部导入（导入所有数据，更新重复数据为Excel中数据）
	 */
	public static final String IMPORTOPER_ALL = "2";

	/**
	 * 资源类型视频
	 */
	public static final String RESOURCE_TYPE_VIDEO = "Video";

	/**
	 * 资源类型图片
	 */
	public static final String RESOURCE_TYPE_IMAGE = "Image";

	/**
	 * 资源类型文件
	 */
	public static final String RESOURCE_TYPE_FILE = "File";

	/**
	 * 验证类型：1-唯一性
	 */
	public static final String VALIDATE_TYPE_UNIQUE = "1";

	/**
	 * 验证类型：2-有效性
	 */
	public static final String VALIDATE_TYPE_VALID = "2";

	/**
	 * 发布日期-今天
	 */
	public static final String RELEASE_DATE_TODAY = "today";

	/**
	 * 发布日期-一周内
	 */
	public static final String RELEASE_DATE_WITHIN_WEEK = "withinWeek";

	/**
	 * 发布日期-一月内
	 */
	public static final String RELEASE_DATE_WITHIN_MONTH = "withinMonth";
	/**
	 * 发布日期-自定义
	 */
	public static final String RELEASE_DATE_CUSTOMDATES = "customDates";

	/**
	 * 系统资源
	 */
	public static final String SYSTEM_RESOURCES_RT001 = "RT001";

	/**
	 * 公共资源
	 */
	public static final String PUBLIC_RESOURCES_RT002 = "RT002";

	/**
	 * 用户皮肤键
	 */
	public static final String SKIN_KEY = "csstheme";
	
	/**
	 * 普通用户的初始密码
	 */
	public static final String INIT_PASSWORD = "767e2531d46b60b3dac17b5068b22d49";

	/**
	 * 定义首页颜色显示区域
	 * 
	 */
	public static final String BG_LGREEN = "bg_lgreen";
	public static final String BG_LBLUE = "bg_lblue";
	public static final String BG_LYELLOW = "bg_lyellow";
	public static final String BG_LRED = "bg_lred";
	public static final String BG_LORANGE = "bg_lorange";
	public static final String BG_LPURPLE = "bg_lpurple";

	/**
	 * 字符串常量"1"
	 */
	public static final String ONE = "1";

	/**
	 * 字符串常量"0"
	 */
	public static final String ZERO = "0";

	/**
	 * 字符常量"2"
	 */
	public static final String TWO = "2";

	/**
	 * 字符常量"3"
	 */
	public static final String THREE = "3";

	/**
	 * 字符常量"4"
	 */
	public static final String FOUR = "4";

	/**
	 * 字符常量"5"
	 */
	public static final String FIVE = "5";

	/**
	 * 字符常量"6"
	 */
	public static final String SIX = "6";

	/**
	 * 字符常量"7"
	 */
	public static final String SEVEN = "7";

	/**
	 * 字符常量"8"
	 */
	public static final String EIGHT = "8";

	/**
	 * 装置类型LKJ93
	 */
	public static final Integer DEVICET_TYPE_1 = 1;
	/**
	 * 装置类型LKJ2000
	 */
	public static final Integer DEVICET_TYPE_2 = 2;
	/**
	 * 装置类型LKJ15
	 */
	public static final Integer DEVICET_TYPE_3 = 3;
	/**
	 * 装置类型其他
	 */
	public static final Integer DEVICET_TYPE_4 = 4;

	/**
	 * 消息处理结果 未处理
	 */
	public static final String ALARMDEALFLAG_0 = "0";

	/**
	 * 消息处理结果 已处理
	 */
	public static final String ALARMDEALFLAG_1 = "1";

	/**
	 * 消息阅读状态 未读
	 */
	public static final String IS_READER_0 = "0";

	/**
	 * 消息阅读状态 已读
	 */
	public static final String IS_READER_1 = "1";

	/**
	 * 销号状态 人工销号
	 */
	public static final String CONTROLFLAG_2 = "2";

	/**
	 * 销号状态 未销号
	 */
	public static final Integer CONTROLFLAG_0 = 0;

	/**
	 * 字典类型 警示
	 */
	public static final String DICT_TYPE_TRAIN_WARNING = "train_warning";

	/**
	 * 主计划下发状态(0未下发)
	 */
	public static final Integer SENDOUTFLAG_0 = 0;
	/**
	 * 主计划下发状态(1已下发)
	 */
	public static final Integer SENDOUTFLAG_1 = 1;

	/**
	 * 审核标志0：未提交
	 */
	public static final Integer CHECKFLAG_0 = 0;
	/**
	 * 审核标志1：待审核
	 */
	public static final Integer CHECKFLAG_1 = 1;
	/**
	 * 审核标志2：已审核
	 */
	public static final Integer CHECKFLAG_2 = 2;
	/**
	 * 审核标志3：审核未通过
	 */
	public static final Integer CHECKFLAG_3 = 3;
	/**
	 * 审核标志8：已下发
	 */
	public static final Integer CHECKFLAG_8 = 8;
	/**
	 * 审核标志9：已销记
	 */
	public static final Integer CHECKFLAG_9 = 9;

	/**
	 * 审核结果1：通过
	 */
	public static final Integer CHECK_RESULT_1 = 1;
	/**
	 * 审核结果2：未通过
	 */
	public static final Integer CHECK_RESULT_2 = 2;

	/**
	 * 附件标志0：无附件
	 */
	public static final Integer FJFLAG_0 = 0;
	/**
	 * 附件标志1：有附件
	 */
	public static final Integer FJFLAG_1 = 1;

	/**
	 * 计划类型 0：主计划
	 */
	public static final Integer PLANFLAG_0 = 0;
	/**
	 * 计划类型1：子计划
	 */
	public static final Integer PLANFLAG_1 = 1;

	/**
	 * 计划类型 2：委托换装
	 */
	public static final Integer PLANFLAG_2 = 2;

	/**
	 * 委托标记0：非委托
	 */
	public static final Integer GRANT_REPLACE_FLAG_0 = 0;

	/**
	 * 委托标记 1：委托
	 */
	public static final Integer GRANT_REPLACE_FLAG_1 = 1;

	/**
	 * 部门(单位)类型00:供应商
	 */
	public static final String DEPT_TYPE_00 = "00";
	/**
	 * 部门(单位)类型01:机务段
	 */
	public static final String DEPT_TYPE_01 = "01";
	/**
	 * 部门(单位)类型02:电务段
	 */
	public static final String DEPT_TYPE_02 = "02";
	/**
	 * 部门(单位)类型12为工务段
	 */
	public static final String DEPT_TYPE_12 = "12";
	/**
	 * 部门(单位)类型05为铁路局
	 */
	public static final String DEPT_TYPE_05 = "05";
	/**
	 * 部门(单位)类型06为车辆段
	 */
	public static final String DEPT_TYPE_06 = "06";
	/**
	 * 部门(单位)类型07为动车段
	 */
	public static final String DEPT_TYPE_07 = "07";
	/**
	 * 部门(单位)类型10为其他机构
	 */
	public static final String DEPT_TYPE_10 = "10";

	/**
	 * 状态标记--新增
	 */
	public static final String MARK_STATUS_ADD = "0";

	/**
	 * 状态标记--修改
	 */
	public static final String MARK_STATUS_UPDATE = "1";

	/**
	 * 全局超级管理员标识
	 */
	public static final String GOLBAL_SUPER_ADMIN = "00000000-0000-0000-0000-0000";

	/**
	 * 字典类型 --消息类型
	 */
	public static final String DICT_TYPE_ALARM_TYPE = "alarm_type";

	/**
	 * 大屏幕查询类型 --完整换装
	 */
	public static final String SELECT_TYPE_WZHZ = "wzhz";

	/**
	 * 大屏幕查询类型 --块换装
	 */
	public static final String SELECT_TYPE_KHZ = "khz";

	/**
	 * 大屏幕查询类型 --销号
	 */
	public static final String SELECT_TYPE_XH = "xh";

	/**
	 * 大屏幕查询类型 --预警
	 */
	public static final String SELECT_TYPE_YJ = "yj";

	/**
	 * 警示状态 0：正常
	 */

	/**
	 * 机车AB节A节
	 */
	public static final String LOCO_NO_AB_A = "A";
	/**
	 * 机车AB节B节
	 */
	public static final String LOCO_NO_AB_B = "B";

	/**
	 * 换装转态 未开始
	 */
	public static final Integer TRACE_RELOAD_FLAG_0 = 0;

	/**
	 * 换装转态 已完成
	 */
	public static final Integer TRACE_RELOAD_FLAG_1 = 1;

	/**
	 * 警示状态 0:正常
	 */
	public static final String TRAINWARNING_0 = "0";

	/**
	 * 警示状态 1：关注
	 */
	public static final String TRAINWARNING_1 = "1";

	/**
	 * 警示状态 2：预警
	 */
	public static final String TRAINWARNING_2 = "2";

	/**
	 * 警示状态 3：故障
	 */
	public static final String TRAINWARNING_3 = "3";

	/**
	 * 警示状态 4：报警
	 */
	public static final String TRAINWARNING_4 = "4";

	/**
	 * 警示状态 5：提前换
	 */
	public static final String TRAINWARNING_5 = "5";

	/**
	 * 警示状态 6：漏换
	 */
	public static final String TRAINWARNING_6 = "6";

	/**
	 * 警示状态 7：错换
	 */
	public static final String TRAINWARNING_7 = "7";

	/**
	 * 警示状态 8：未知
	 */
	public static final String TRAINWARNING_8 = "8";

	/**
	 * 换装状态 0:未换装
	 */
	public static final String RELOADRESULT_0 = "0";

	/**
	 * 换装状态 1:正在发送
	 */
	public static final String RELOADRESULT_1 = "1";

	/**
	 * 换装状态 2:已发送
	 */
	public static final String RELOADRESULT_2 = "2";

	/**
	 * 换装状态 3: 已更新
	 */
	public static final String RELOADRESULT_3 = "3";

	/**
	 * 换装状态 4: 已启用
	 */
	public static final String RELOADRESULT_4 = "4";

	/**
	 * 换装状态 5:传输失败
	 */
	public static final String RELOADRESULT_5 = "5";

	/**
	 * 机车导入模板列长度
	 */
	public static final Integer IMPORT_TEMP_COLUMN_COUPON = 23;

	/**
	 * 用户导入模板列长度
	 */
	public static final Integer IMPORT_TEMP_COLUMN_USER = 3;

	/**
	 * 按钮编码 下发
	 */
	public static final String BUTTON_CODE_SENDING = "sending";
	/**
	 * 按钮编码 提交审批
	 */
	public static final String BUTTON_CODE_SUBMIT_CHECK = "submitCheck";
	/**
	 * 按钮编码 审批
	 */
	public static final String BUTTON_CODE_CHECK = "toCheck";
	/**
	 * 按钮编码 机务 审批
	 */
	public static final String BUTTON_CODE_CHECKJW = "toCheckJW";

	/**
	 * 是管理员
	 */
	public static final String LOGIN_USER_IS_ADMIN = "1";

	/**
	 * 不是管理员
	 */
	public static final String LOGIN_USER_NOT_ADMIN = "0";

	/**
	 * 操作类型_修改
	 */
	public static final String UPDAE_TYPE_EDIT = "1";

	/**
	 * 操作类型_删除
	 */
	public static final String UPDAE_TYPE_DELETE = "0";

	/**
	 * 超级操作
	 */
	public static final String SUPER_OPER = "1";

	/**
	 * 计划内容
	 */
	public static final String PLANCONTENT = "计划内容";
	/**
	 * 已删除
	 */
	public static final Integer ISACTIVE_0 = 0;
	/**
	 * 未删除
	 */
	public static final Integer ISACTIVE_1 = 1;

}
