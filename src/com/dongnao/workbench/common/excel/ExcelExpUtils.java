package com.dongnao.workbench.common.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import com.dongnao.workbench.common.util.ReflectUtil;
import com.dongnao.workbench.common.util.SpringInit;
import com.dongnao.workbench.common.util.StringUtil;

import jxl.write.WritableCellFormat;


public class ExcelExpUtils {
	/**
	 * 设置文件下载的编码格式
	 * @param filename、contentType 
	 * @return response
	 * @throws UnsupportedEncodingException
	 * */
	public static void setResFileDownHead(String filename, String contentType,
			HttpServletResponse response) throws UnsupportedEncodingException {
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(filename.getBytes("GBK"), "ISO8859-1"));
		response.setContentType(contentType);
		response.setDateHeader("Expires", -1L);

	}
	/**
	 * 设置文件下载格式
	 * @param filename
	 * @return response
	 * @throws UnsupportedEncodingException
	 * */
	public static void setResExcelDownHead(String filename,
			HttpServletResponse response) throws UnsupportedEncodingException {
		setResFileDownHead(filename + ".xls", "application/msexcel", response);
	}

	/**
	 * 获取下载绝对路径
	 * @param filepath
	 * @return spring上下文环境对象设置获取绝对路径
	 * */
	public static String getSystemPath(String filepath) {
		return SpringInit.getServletContext().getRealPath(filepath);
	}
	
	/**
	 * 判断是否合并单元格
	 * @param mergeMap 、row数、 col数
	 * @return true/false
	 * */
	@SuppressWarnings("unused")
	private static boolean isMergeCell(Map<String, Integer> mergeMap, int row,
			int col) {
		Set keyset = mergeMap.keySet();
		//游标Iterator 迭代器
		Iterator it = keyset.iterator();
		while (it.hasNext()) {
			String keystr = (String) it.next();
			int spanrows = ((Integer) mergeMap.get(keystr)).intValue();
			String[] arykey = keystr.split("-");
			int startrow = Integer.parseInt(arykey[0]);
			int startcol = Integer.parseInt(arykey[1]);
			if ((startcol == col) && (startrow + spanrows - 1 >= row)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取行数
	 * @param header
	 * @return colLength
	 * */
	public static int getColLength(List<ExpFieldBean> header) {
		ExpFieldBean field = null;
		Integer colspan = null;
		int count = 0;
		for (int col = 0; col < header.size(); col++) {
			field = header.get(col);
			colspan = field.getColspan();
			if ((colspan != null) && (colspan.intValue() > 1)) {
				for (int c = 0; c < colspan.intValue(); c++) {
					count++;
				}
			} else {
				count++;
			}
		}
		return count;
	}
	
	
	public static ExpFieldBean[] setWriteHeader(
			List<List<ExpFieldBean>> headers, ExcelWriteReadBean eu,
			String excelTitle) throws Exception {
		List<ExpFieldBean> header = null;
		ExpFieldBean field = null;
		int startrow = 1;
		Integer colspan = null;
		Integer rowspan = null;
		int colLength = getColLength(headers.get(0));
		ExpFieldBean[] contentFields = new ExpFieldBean[colLength];
		@SuppressWarnings("unused")
		int contentFieldsIndex = 0;
		Integer[][] table = new Integer[headers.size()][colLength];// 构造一个table二维数组
		WritableCellFormat headerStyle = ExcelStyle.getReportHeaderStyle();
		for (int row = 0; row < headers.size(); row++) {
			header = headers.get(row);
			int statrcol = 0;
			for (int col = 0; col < header.size(); col++) {
				field = header.get(col);
				colspan = field.getColspan();
				rowspan = field.getRowspan();
				if (row > 0) {
					for (int tdInx = 0; tdInx < colLength; tdInx++) {
						if (table[row][tdInx] == null) {
							statrcol = tdInx;
							break;
						}
					}
				}
				if ((colspan != null) && (colspan.intValue() > 1)) {
					for (int c = 0; c < colspan.intValue(); c++) {
						eu.printCellText(startrow, statrcol + c,
								field.getLabel(), headerStyle);
						if (row > 0) {
							table[row][statrcol + c] = 1;
						}
					}
					eu.mergeCells(statrcol, startrow,
							statrcol + colspan.intValue() - 1, startrow);
					statrcol = statrcol + colspan.intValue() - 1;

				} else {
					if (row > 0) {
						table[row][statrcol] = 1;
					}
					eu.printCellText(startrow, statrcol, field.getLabel(),
							headerStyle);
				}
				if ((rowspan != null) && (rowspan.intValue() > 1)) {
					for (int c = 0; c < rowspan.intValue(); c++) {
						table[row + c][statrcol] = 1;
					}
					eu.mergeCells(statrcol, startrow, statrcol, startrow
							+ rowspan.intValue() - 1);
				}
				if (field.getWidth() != null) {
					eu.setColWidth(statrcol,
							Math.round(field.getWidth().intValue() * 0.1188F));
				}
				if (field.getName() != null) {
					contentFields[statrcol] = field;
				}
				statrcol++;
			}
			eu.setRowHeight(startrow, 500);// 设置行高
			startrow++;
		}
		eu.printCellText(0, 0, excelTitle,
				ExcelStyle.getReportHeaderTitleStyle());// 写标题
		eu.setRowHeight(0, 600);// 设置标题行高
		eu.mergeCells(0, 0, colLength - 1, 0);// 合并标题
		return contentFields;
	}

	public static int setWriteContent(int startrow, ExpFieldBean[] content,
			List list, ExcelWriteReadBean eu) throws Exception {
		ExpFieldBean field = null;
		WritableCellFormat alignStyle = ExcelStyle.getReportContentStyle();
		Object vObj = null;
		for (int i = 0; i < list.size(); i++) {
			vObj = list.get(i);
			if (vObj instanceof Map) {
				Map rowmap = (Map) vObj;
				for (int col = 0; col < content.length; col++) {
					field = content[col];
					if (field != null) {
						if (field.getWidth() != null) {
							eu.setColWidth(col, Math.round(field.getWidth()
									.intValue() * 0.1188F));
						}
						if ((field.getColspan() != null)
								&& (field.getColspan().intValue()

								> 1)) {
							eu.mergeCells(col, startrow, col + field.getColspan

							().intValue() - 1, startrow);
						}
						String fName = field.getName();
						Object tmpo = "";
						if (fName != null) {
							tmpo = rowmap.get(field.getName().toUpperCase());
							if (tmpo == null)
								tmpo = " ";
						}

						eu.printCellText(startrow, col, tmpo, alignStyle);
						eu.setRowHeight(startrow, 350);// 设置行高
					}

				}

			} else {
				for (int col = 0; col < content.length; col++) {
					field = content[col];
					if (field.getWidth() != null) {
						eu.setColWidth(col, Math.round(field.getWidth()
								.intValue() * 0.1188F));
					}
					if ((field.getColspan() != null)
							&& (field.getColspan().intValue()

							> 1)) {
						eu.mergeCells(col, startrow, col
								+ field.getColspan().intValue() - 1, startrow);
					}
					Object tmpo = ReflectUtil.getValueByFieldName(vObj,
							field.getName());
					if (tmpo instanceof Date) {
						if (field.getDateFormat() != null) {
							tmpo = StringUtil.formatDate((Date) tmpo,
									field.getDateFormat());
						} else {
							tmpo = StringUtil
									.formatDateyyyyMMddHHmmss((Date) tmpo);
						}
					}
					if (field.getValFmt() != null) {
						tmpo = field.getValFmt().formatter(tmpo);
					}
					if (tmpo == null){
						tmpo = " ";
					}
					eu.printCellText(startrow, col, tmpo, alignStyle);
					eu.setRowHeight(startrow, 350);// 设置行高
				}
			}
			startrow++;
		}
		return startrow;
	}

	public static void setWriteFooter(int startrow,
			List<List<ExpFieldBean>> footers, Map footerMap,
			ExcelWriteReadBean eu) throws Exception {
		if (footers == null) {
			return;
		}
		Integer colspan = null;
		ExpFieldBean field = null;
		List<ExpFieldBean> footer = null;
		WritableCellFormat alignStyle = ExcelStyle.getReportFooterStyle();
		for (int i = 0; i < footers.size(); i++) {
			footer = footers.get(i);
			int statrcol = 0;
			for (int col = 0; col < footer.size(); col++) {
				field = footer.get(col);
				colspan = field.getColspan();
				if (field.getWidth() != null) {
					eu.setColWidth(statrcol,
							Math.round(field.getWidth().intValue() * 0.1188F));
				}
				if ((field.getColspan() != null)
						&& (field.getColspan().intValue() > 1)) {
					for (int c = 0; c < colspan.intValue(); c++) {
						eu.printCellText(startrow, statrcol + c,
								field.getLabel(), alignStyle);
					}
					eu.mergeCells(col, startrow, col
							+ field.getColspan().intValue() - 1, startrow);
					statrcol = statrcol + colspan.intValue() - 1;
				}
				String fName = field.getName();
				Object tmpo = "";
				if (fName != null) {
					tmpo = footerMap.get(field.getName().toUpperCase());
					if (tmpo == null)
						tmpo = "";
				}
				eu.printCellText(startrow, statrcol, tmpo, alignStyle);
				eu.setRowHeight(startrow, 350);// 设置行高
				statrcol++;
			}
			startrow++;
		}
	}

	public static void exportListToExcel(List list, HttpServletResponse res,
			List<List<ExpFieldBean>> fieldList, String filename,
			String reportname) throws Exception {
		setResExcelDownHead(filename, res);
		OutputStream out = res.getOutputStream();
		createExcle(fieldList, list, out, reportname);
	}

	public static void exportListToExcel(List list,
			List<List<ExpFieldBean>> fieldList,
			List<List<ExpFieldBean>> totalFiellist, Map footerMap,
			HttpServletResponse res, String filename, String reportname)
			throws Exception {
		setResExcelDownHead(filename, res);
		OutputStream out = res.getOutputStream();
		createExcle(fieldList, list, totalFiellist, footerMap, out, reportname);
	}

	public static void createExcle(List<List<ExpFieldBean>> headers, List list,
			OutputStream out, String title) throws Exception {
		createExcle(headers, list, null, null, out, title);
	}

	public static void createExcle(List<List<ExpFieldBean>> headers, List list,
			File fileWrite, String title) throws Exception {
		createExcle(headers, list, null, null, new FileOutputStream(fileWrite),
				title);
	}

	public static void createExcle(List<List<ExpFieldBean>> headers,
			List headerList, List<List<ExpFieldBean>> footers, Map footerMap,
			File fileWrite, String title) throws Exception {
		createExcle(headers, headerList, footers, footerMap,
				new FileOutputStream(fileWrite), title);
	}

	public static void createExcle(List<List<ExpFieldBean>> headers,
			List headerList, List<List<ExpFieldBean>> footers, Map footerMap,
			OutputStream out, String title) throws Exception {
		ExcelWriteReadBean eu = new ExcelWriteReadBean(out);
		ExpFieldBean[] contentFields = ExcelExpUtils.setWriteHeader(headers,
				eu, title);
		int startrow = ExcelExpUtils.setWriteContent(headers.size() + 1,
				contentFields, headerList, eu);
		ExcelExpUtils.setWriteFooter(startrow, footers, footerMap, eu);
		eu.importEnd();
		out.flush();
	}

	public static void setExpFieldBeanValFmt(
			List<List<ExpFieldBean>> fieldlist, IValueFormatter valFmt,
			String fieldName) {
		for (List<ExpFieldBean> list : fieldlist) {
			for (ExpFieldBean fb : list) {
				if (fb.getName().equals(fieldName)) {
					fb.setValFmt(valFmt);
					break;
				}
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String args[]) {
		List list = new ArrayList();
		Map map = new HashMap();
		map.put("ID", "111111111111111");
		map.put("NAME", "真是碉堡了");
		list.add(map);
		List<List<ExpFieldBean>> headers = new ArrayList();
		List<ExpFieldBean> header = new ArrayList<ExpFieldBean>();
		ExpFieldBean id = new ExpFieldBean();
		id.setLabel("StartDate");
		id.setWidth(200);
		id.setName("id");
		id.setDateFormat("yyyy-MM-dd");
		header.add(id);
		ExpFieldBean name = new ExpFieldBean();
		name.setLabel("Remark");
		name.setWidth(400);
		name.setName("name");
		header.add(name);
		headers.add(header);
		try {
			File fileWrite = new File("D:\\test23.xls");
			fileWrite.createNewFile();
			OutputStream out = new FileOutputStream(fileWrite);
			ExcelWriteReadBean eu = new ExcelWriteReadBean(out);
			ExcelExpUtils.createExcle(headers, list, out, "测试测试");
			eu.importEnd();
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
