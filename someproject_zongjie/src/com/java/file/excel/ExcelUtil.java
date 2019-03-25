package com.java.file.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Ӧ��POI��ȡexcel�ļ��Լ���excelд������
 * 
 * @author Administrator
 *
 */
public class ExcelUtil {

	/**
	 * ����workbook������(��һ��)
	 * 
	 * @param sheetName
	 * @param str
	 *            ����
	 */
	@SuppressWarnings("null")
	public static HSSFWorkbook creatHead(String sheetName, String... str) {
		if (str != null || str.length == 0) {
			return null;
		}
		// ��һ��������һ��webbook����Ӧһ��Excel�ļ�
		HSSFWorkbook workbook = new HSSFWorkbook();
		// �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet����һ��sheet����Ӧexcel�е�sheet
		HSSFSheet sheet = workbook.createSheet(sheetName);
		// ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short
		HSSFRow row = sheet.createRow(0);
		// ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// ����һ�����и�ʽ
		// cellStyle.setFillBackgroundColor(HSSFCellStyle.BIG_SPOTS);
		for (int i = 0; i < str.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(str[i]);
			cell.setCellStyle(cellStyle);
		}
		return workbook;

	}
}
