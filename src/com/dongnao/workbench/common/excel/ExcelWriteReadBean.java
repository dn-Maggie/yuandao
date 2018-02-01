package com.dongnao.workbench.common.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

public class ExcelWriteReadBean {
	private Workbook readWorkbook;
	private WritableWorkbook writeWorkbook;
	private Sheet readSheet;
	private WritableSheet writeSheet;



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



	public ExcelWriteReadBean(OutputStream os) {
		try {
			this.writeWorkbook = Workbook.createWorkbook(os);
			this.writeSheet = this.writeWorkbook.createSheet("Sheet1", 0);

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
			wc = new jxl.write.Number(col, row, ((java.lang.Number) inputobj)
					.doubleValue());
		else if (inputobj instanceof Date)
			wc = new DateTime(col, row, (Date) inputobj);
		else {
			wc = new Label(col, row, inputobj.toString());
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
		this.writeSheet = null;
		this.writeWorkbook = null;
	}

	public ExcelWriteReadBean(String filepath, String sheetname) {
		try {
			this.readWorkbook = Workbook.getWorkbook(new File(filepath));
			this.readSheet = this.readWorkbook.getSheet(sheetname);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

	public void readEnd() {
		this.readWorkbook.close();
		this.readSheet = null;
		this.readWorkbook = null;
	}

	private boolean isMergeCell(Map<String, Integer> mergeMap, int row, int col) {
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
	
	public static void main(String[] ss) {
		
		try {
			File fileWrite = new File("E:\\test.xls");
			fileWrite.createNewFile();
			OutputStream out = new FileOutputStream(fileWrite);
			ExcelWriteReadBean eu = new ExcelWriteReadBean(out);
			eu.printCellText(0, 0, "测试测试", null);
			eu.importEnd();
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
