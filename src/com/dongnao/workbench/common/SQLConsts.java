package com.dongnao.workbench.common;

/**
 * 描述：定义SQL相关常量
 * @author heyin
 * @version 1.0 2013-10-22
 */
public class SQLConsts {
    
    /** SQL中需要被转换的所有字符对应的正则表达式 */
    public static final String[] PATTERNS_2BE_ESCAPED = { "/", "_", "'", "％", "＿" };
    
    /** SQL中需要被转换的所有字符被转换后的字符串对应的正则表达式 */
    public static final String[] PATTERNS_OF_REPLACEMENT = { "//", "/_", "/'", "/％", "/＿" };
    
    /** SQL转义字符 */
    public static final String ESCAPE_CHAR = "/";
    
    /** 字符串ON */
    public static final String STR_ON = "ON";
    /** 字符串OFF */
    public static final String STR_OFF = "OFF";
    
    /** 字符串1 */
    public static final String STR_1 = "1";
    /** 字符串0 */
    public static final String STR_0 = "0";
    
    /** SQL Server按中文拼音排序 字符串*/
    public static final String SQLSERVER_CHINESE_ORDER = " COLLATE chinese_prc_cs_as_ks_ws";
}
