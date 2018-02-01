/** 
 * CopyRright (c)2014-2015                       
 * Project:  sn     
 * Title: ExcelStyle.java  
 * Package com.suframework.webbase.excel                                                                        
 * JDK version used:  <JDK1.5>   
 * Description: (用一句话描述该文件做什么)                                                        
 * Author: 307806767@qq.com                
 * Create date: Mar 7, 2014 9:02:49 PM 
 * Modified By：   <修改人中文名或拼音缩写>                                         
 * Modified Date:  <修改日期，格式:YYYY-MM-DD>                                    
 * Why & What is modified  <修改原因描述>    
 * Version V1.0                        
 */

package com.dongnao.workbench.common.excel;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;

public class ExcelStyle {
	private static WritableCellFormat alignStyle;

	/**
	 * 
	 * @Title: getReportHeaderStyle
	 * @Description: TODO(获取报表页眉样式)
	 * @param
	 * @return
	 * @param
	 * @throws Exception
	 *             设定文件
	 * @return WritableCellFormat 返回类型
	 * @throws
	 */
	public static WritableCellFormat getReportHeaderStyle() throws Exception {
		WritableFont font = new WritableFont(WritableFont.createFont("宋体"));
		WritableCellFormat as = new WritableCellFormat(font);
		as.setBackground(Colour.GRAY_25); // 背景色
		setBorder(as);
		return as;
	}

	public static WritableCellFormat getReportFooterStyle() throws Exception {
		WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 11,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.RED);
		WritableCellFormat as = new WritableCellFormat(font);
		setBorder(as);
		return as;
	}

	public static WritableCellFormat getReportHeaderTitleStyle()
			throws Exception {
		WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 15,
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);
		WritableCellFormat as = new WritableCellFormat(font);
		as.setAlignment(Alignment.CENTRE); // 设置对齐方式
		// ,把水平对齐方式指定为居中
		as.setVerticalAlignment(VerticalAlignment.CENTRE);// 把垂直对齐方式指定为居中
		return as;
	}

	public static void setBorder(WritableCellFormat as) throws Exception {
		// 画上
		as.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);// 设置边框的颜色和样式
		// 画左
		as.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);// 设置边框的颜色和样式
		// 画右
		as.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);// 设置边框的颜色和样式
		// 画下
		as.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);// 设置边框的颜色和样式
		// drawLine(sheet, x, y, Border.BOTTOM);
		as.setAlignment(Alignment.CENTRE); // 设置对齐方式
		// ,把水平对齐方式指定为居中
		as.setVerticalAlignment(VerticalAlignment.CENTRE);// 把垂直对齐方式指定为居中
	}

	public static WritableCellFormat getReportContentStyle() throws Exception {
		WritableCellFormat as = new WritableCellFormat();
		setBorder(as);
		return as;
	}

}
