package com.java.file.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 应用POI读取excel文件以及向excel写入内容
 * 
 * @author Administrator
 *
 */
public class ExcelUtil {

	/**
	 * 创建workbook的列名(第一行)
	 * 
	 * @param sheetName
	 * @param str
	 *            列名
	 */
	@SuppressWarnings("null")
	public static HSSFWorkbook creatHead(String sheetName, String... str) {
		if (str != null || str.length == 0) {
			return null;
		}
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet创建一个sheet，对应excel中的sheet
		HSSFSheet sheet = workbook.createSheet(sheetName);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 创建一个居中格式
		// cellStyle.setFillBackgroundColor(HSSFCellStyle.BIG_SPOTS);
		for (int i = 0; i < str.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(str[i]);
			cell.setCellStyle(cellStyle);
		}
		return workbook;

	}
}
