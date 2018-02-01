package com.dongnao.workbench.common.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class ExcelUtils {
	private Workbook readWorkbook;
	private WritableWorkbook writeWorkbook;
	private Sheet readSheet;
	private WritableSheet writeSheet;
	private Workbook templetWorkbook;

	public Workbook getTempletWorkbook() {
		return this.templetWorkbook;
	}

	public Workbook getReadWorkbook() {
		return this.readWorkbook;
	}

	public WritableWorkbook getWriteWorkbook() {
		return this.writeWorkbook;
	}

	public Sheet getReadSheet() {
		return this.readSheet;
	}

	public WritableSheet getWriteSheet() {
		return this.writeSheet;
	}

	public ExcelUtils(String sheetname, OutputStream os, String templetpath) {
		try {
			this.templetWorkbook = Workbook.getWorkbook(new File(templetpath));
			this.writeWorkbook = Workbook.createWorkbook(os,
					this.templetWorkbook);

			this.writeSheet = this.writeWorkbook.getSheet(sheetname);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ExcelUtils(OutputStream os) {
		try {
			this.writeWorkbook = Workbook.createWorkbook(os);
			this.writeSheet = this.writeWorkbook.createSheet("第一页", 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: printCellText
	 * @Description: TODO(输出单元格值)
	 * @param row
	 *            行
	 * @param col
	 *            列
	 * @param inputobj
	 *            值
	 * @param wcf
	 *            格式化
	 * @param
	 * @throws Exception
	 *             设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void printCellText(int row, int col, Object inputobj,
			WritableCellFormat wcf) throws Exception {
		if (inputobj == null) {
			return;
		}
		WritableCell wc = null;

		if (inputobj instanceof java.lang.Number)
			wc = new jxl.write.Number(col, row,
					((java.lang.Number) inputobj).doubleValue());
		else if (inputobj instanceof Date)
			wc = new DateTime(col, row, (Date) inputobj);
		else {
			wc = new Label(col, row, inputobj.toString().trim());
		}

		if (wcf == null) {
			CellFormat oldwcf = this.writeSheet.getCell(col, row)
					.getCellFormat();
			if (oldwcf != null)
				wc.setCellFormat(oldwcf);
		} else {
			wc.setCellFormat(wcf);
		}

		this.writeSheet.addCell(wc);
	}

	/**
	 * 
	 * @Title: mergeCells
	 * @Description: TODO(单元格合并函数)
	 * @param startCol
	 *            单元格的列号
	 * @param startRow
	 *            单元格的行号
	 * @param endCol
	 *            从单元格[startCol,startRow]起，向下合并的列数d
	 * @param endRow
	 *            从单元格[startCol,startRow]起，向下合并的行数
	 * @param
	 * @throws Exception
	 *             设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void mergeCells(int startCol, int startRow, int endCol, int endRow)
			throws Exception {
		this.writeSheet.mergeCells(startCol, startRow, endCol, endRow);
	}

	public void writeImage(int row, int col, double width, double height,
			String imgUrl) {
		WritableImage ri = new WritableImage(row, col, width, height, new File(
				imgUrl));
		this.writeSheet.addImage(ri);
	}

	public void setRowHeight(int row, int height) throws Exception {
		this.writeSheet.setRowView(row, height);
	}

	public void setColWidth(int col, int width) {
		this.writeSheet.setColumnView(col, width);
	}

	/**
	 * 
	 * @Title: copyCell
	 * @Description: TODO(复制单元格)
	 * @param row
	 *            行
	 * @param col
	 *            列
	 * @param targetrow
	 *            目标行
	 * @param targetcol
	 *            目标列
	 * @param
	 * @throws Exception
	 *             设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void copyCell(int row, int col, int targetrow, int targetcol)
			throws Exception {
		this.writeSheet.addCell(this.writeSheet.getWritableCell(col, row)
				.copyTo(targetcol, targetrow));
	}

	public void importEnd() throws Exception {
		this.writeWorkbook.write();
		this.writeWorkbook.close();
		if (this.templetWorkbook != null) {
			this.templetWorkbook.close();
		}
		this.writeSheet = null;
		this.writeWorkbook = null;
		this.templetWorkbook = null;
	}

	public ExcelUtils(String filepath, String sheetname) {
		try {
			this.readWorkbook = Workbook.getWorkbook(new File(filepath));

			this.readSheet = this.readWorkbook.getSheet(sheetname);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 读取指定行列数据
	public String read(int col, int row) {
		try {

			Cell a2 = this.readSheet.getCell(col, row);
			String rest = a2.getContents();
			return rest;
		} catch (Exception e) {
			this.readWorkbook.close();
		}
		return null;
	}

	// 读取某一行数据
	public String[] readLine(int row) {
		try {
			int colnum = this.readSheet.getColumns();
			String[] rest = new String[colnum];
			for (int i = 0; i < colnum; ++i) {
				String sTemp = read(i, row);
				if (sTemp != null)
					rest[i] = sTemp;
			}
			return rest;
		} catch (Exception e) {
			this.readWorkbook.close();
		}
		return null;
	}

	// 读取整个excel数据,params传出参数行列
	public Map<String, Object> readExcel(Integer tempCols) {
		Map<String, Object> map = new HashMap<String, Object>();
		int rows = this.readSheet.getRows(); // 行
		int colnum = this.readSheet.getColumns(); // 列
		if (rows == 0 && colnum == 0) {
			return null;
		}
		String[][] rest = new String[rows - 1][colnum];
		if (colnum < tempCols) {
			return null;
		}
		try {
			for (int i = 1; i < rows; i++) { // 行循环，有表头，从1开始
				for (int j = 0; j < colnum; j++) { // 列循环
					Cell cell = this.readSheet.getCell(j, i);

					rest[i - 1][j] = cell.getContents();
				}
			}
		} catch (Exception e) {
			this.readWorkbook.close();
		}
		map.put("row", rows - 1);
		map.put("col", colnum);
		map.put("str", rest);
		return map;
	}

	public void readEnd() {
		this.readWorkbook.close();
		this.readSheet = null;
		this.readWorkbook = null;
	}

	public static void setResFileDownHead(String filename, String contentType,
			HttpServletResponse response) throws UnsupportedEncodingException {
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(filename.getBytes("GBK"), "ISO8859-1"));
		response.setContentType(contentType);
		response.setDateHeader("Expires", -1L);

	}

	public static void setResExcelDownHead(String filename,
			HttpServletResponse response) throws UnsupportedEncodingException {
		setResFileDownHead(filename + ".xls", "application/msexcel", response);
	}

	public static String getSystemPath(String filepath) {
		return SpringInit.getServletContext().getRealPath(filepath);
	}

	public static String getTemplatePath(String templatepath) {
		return getSystemPath("/WEB-INF/template" + templatepath);
	}

	private static boolean isMergeCell(Map<String, Integer> mergeMap, int row,
			int col) {
		Set keyset = mergeMap.keySet();
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

	public static void exportListToExcel(List list, HttpServletResponse res,
			List<List<ExpFieldBean>> fieldList, String filename,
			String reportname, ICellValueFormatter fmt, IExportListToExcel excel)
			throws Exception {
		String templatepath = getTemplatePath("/listexcel.xls");
		String sheetname = "Sheet1";

		setResExcelDownHead(filename, res);

		OutputStream out = res.getOutputStream();

		ExcelUtils eu = new ExcelUtils(sheetname, out, templatepath);

		eu.printCellText(0, 0, reportname, null);

		List<ExpFieldBean> flist = new ArrayList<ExpFieldBean>();

		Map mergeMap = new HashMap();

		int startrow = 1;

		for (int i = 0; i < fieldList.size(); ++i) {
			int cols = 0;

			if (i < fieldList.size() - 1) {// 如果是最后一列了
				eu.copyCell(startrow + 1, 0, startrow + 2, 0);
				eu.copyCell(startrow, 0, startrow + 1, 0);
				eu.setRowHeight(startrow + 1, 500);
			}

			List<ExpFieldBean> subfieldList = (List<ExpFieldBean>) fieldList
					.get(i);

			for (int l = 0; l < subfieldList.size(); ++l) {
				while ((mergeMap.size() > 0)
						&& (isMergeCell(mergeMap, startrow, cols))) {
					eu.copyCell(startrow, cols, startrow, cols + 1);
					++cols;
				}

				ExpFieldBean field = (ExpFieldBean) subfieldList.get(l);
				if (!StringUtil.isNullorBlank(field.getName())) {
					if (cols >= flist.size())
						flist.add(field);
					else {
						flist.add(cols, field);
					}
				}

				if (l < subfieldList.size() - 1) {
					if ((field.getColspan() != null)
							&& (field.getColspan().intValue() > 1)) {
						for (int z = 1; z < field.getColspan().intValue(); ++z) {
							eu.copyCell(startrow, cols, startrow, cols + z);
						}
						eu.copyCell(startrow, cols, startrow, cols
								+ field.getColspan().intValue());
						eu.mergeCells(cols, startrow, cols
								+ field.getColspan().intValue() - 1, startrow);
					} else {
						eu.copyCell(startrow, cols, startrow, cols + 1);
					}
				}

				eu.printCellText(startrow, cols, field.getLabel(), null);
				if (field.getWidth() != null) {
					eu.setColWidth(cols,
							Math.round(field.getWidth().intValue() * 0.1188F));
				}
				if ((field.getRowspan() != null)
						&& (field.getRowspan().intValue() > 1)) {
					mergeMap.put(startrow + "-" + cols, field.getRowspan());
				}
				if ((field.getColspan() != null)
						&& (field.getColspan().intValue() > 1))
					cols += field.getColspan().intValue();
				else {
					++cols;
				}
			}

			++startrow;
		}

		eu.mergeCells(0, 0, flist.size() - 1, 0);

		Set keyset = mergeMap.keySet();
		Iterator it = keyset.iterator();
		while (it.hasNext()) {
			String keystr = (String) it.next();
			int spanrows = ((Integer) mergeMap.get(keystr)).intValue();
			String[] arykey = keystr.split("-");
			int mergestartrow = Integer.parseInt(arykey[0]);
			int mergestartcol = Integer.parseInt(arykey[1]);
			for (int z = 1; z < spanrows; ++z)
				eu.copyCell(mergestartrow, mergestartcol, mergestartrow + z,
						mergestartcol);
			eu.mergeCells(mergestartcol, mergestartrow, mergestartcol,
					mergestartrow + spanrows - 1);
		}

		eu.setRowHeight(startrow, 340);
		Object vObj = null;
		for (int j = 0; j < list.size(); ++j) {
			vObj = list.get(j);
			if (vObj instanceof Map) {
				Map rowmap = (Map) vObj;
				if (j < list.size() - 1) {
					eu.copyCell(startrow, 0, startrow + 1, 0);
					eu.setRowHeight(startrow + 1, 340);
				}
				for (int k = 0; k < flist.size(); ++k) {
					if (k < flist.size() - 1) {
						eu.copyCell(startrow, k, startrow, k + 1);
					}
					Object tmpo = rowmap.get(((ExpFieldBean) flist.get(k))
							.getName().toUpperCase());
					if (fmt != null) {
						tmpo = fmt.formatter(rowmap,
								(ExpFieldBean) flist.get(k), tmpo);
					}
					eu.printCellText(startrow, k, tmpo, null);
				}
			} else {
				if (j < list.size() - 1) {
					eu.copyCell(startrow, 0, startrow + 1, 0);
					eu.setRowHeight(startrow + 1, 340);
				}
				for (int k = 0; k < flist.size(); ++k) {
					if (k < flist.size() - 1) {
						eu.copyCell(startrow, k, startrow, k + 1);
					}
					Object tmpo = ReflectUtil.getValueByFieldName(vObj, flist
							.get(k).getName());
					if (flist.get(k).getValFmt() != null) {
						tmpo=flist.get(k).getValFmt().formatter(tmpo);
					}
					eu.printCellText(startrow, k, tmpo, null);
				}
			}
			++startrow;
		}

		if (excel != null) {
			excel.exportListAfter(eu, startrow);
		}
		eu.importEnd();

		out.flush();
	}

	public static abstract interface ICellValueFormatter {
		public abstract Object formatter(Map paramMap,
				ExpFieldBean paramExpFieldBean, Object paramObject);
	}

	public static abstract interface IExportListToExcel {
		public abstract void exportListAfter(ExcelUtils paramExcelUtils,
				int paramInt);
	}

	public static void setWriteHeader(List<List<ExpFieldBean>> headers,
			ExcelUtils eu, String excelTitle) throws Exception {
		List<ExpFieldBean> header = null;
		ExpFieldBean field = null;
		int startrow = 1;

		WritableCellFormat alignStyle = ExcelStyle.getReportContentStyle();

		for (int row = 0; row < headers.size(); row++) {
			header = headers.get(row);
			for (int col = 0; col < header.size(); col++) {
				field = header.get(col);
				if (field.getWidth() != null) {
					eu.setColWidth(col,
							Math.round(field.getWidth().intValue() * 0.1188F));
				}
				if ((field.getColspan() != null)
						&& (field.getColspan().intValue() > 1)) {
					eu.mergeCells(col, startrow, col
							+ field.getColspan().intValue() - 1, startrow);
				}
				eu.printCellText(startrow, col, field.getLabel(), alignStyle);
			}
			startrow++;
		}

	}

	public static void setWritecontent(int startrow,
			List<List<ExpFieldBean>> contents, List list, ExcelUtils eu)
			throws Exception {
		List<ExpFieldBean> content = null;
		ExpFieldBean field = null;
		WritableCellFormat alignStyle = ExcelStyle.getReportContentStyle();
		for (int i = 0; i < list.size(); i++) {
			for (int row = 0; row < contents.size(); row++) {
				content = contents.get(row);
				for (int col = 0; col < content.size(); col++) {
					field = content.get(col);
					if (field.getWidth() != null) {
						eu.setColWidth(col, Math.round(field.getWidth()
								.intValue() * 0.1188F));
					}
					if ((field.getColspan() != null)
							&& (field.getColspan().intValue() > 1)) {
						eu.mergeCells(col, startrow, col
								+ field.getColspan().intValue() - 1, startrow);
					}
					eu.printCellText(startrow, col, field.getLabel(),
							alignStyle);
				}
				startrow++;
			}
		}
	}

	public static void exportListToExcel(List list,
			List<List<ExpFieldBean>> headers,
			List<List<ExpFieldBean>> contents,
			List<List<ExpFieldBean>> footers, OutputStream out,
			String fileName, String excelTitle, ICellValueFormatter fmt,
			IExportListToExcel excel) throws Exception {

		ExcelUtils eu = new ExcelUtils(out);
		ExcelUtils.setWriteHeader(headers, eu, excelTitle);
		eu.importEnd();

		out.flush();
	}

	public static void main(String args[]) {
		List list = new ArrayList();
		List<List<ExpFieldBean>> headers = new ArrayList();
		List<ExpFieldBean> header = new ArrayList<ExpFieldBean>();
		ExpFieldBean id = new ExpFieldBean();
		id.setLabel("ID");
		id.setWidth(200);
		id.setName("ID");
		header.add(id);
		ExpFieldBean name = new ExpFieldBean();
		name.setLabel("中文名称");
		name.setWidth(400);
		name.setName("name");
		name.setColspan(2);
		header.add(name);
		headers.add(header);
		List<List<ExpFieldBean>> contents = new ArrayList();
		List<ExpFieldBean> content = new ArrayList<ExpFieldBean>();
		contents.add(content);
		List<List<ExpFieldBean>> footers = new ArrayList();
		List<ExpFieldBean> footer = new ArrayList<ExpFieldBean>();
		footers.add(footer);
		try {
			File fileWrite = new File("E:\\test.xls");

			fileWrite.createNewFile();
			OutputStream out = new FileOutputStream(fileWrite);
			ExcelUtils.exportListToExcel(list, headers, contents, footers, out,
					"测试测试", "测试测试", null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
