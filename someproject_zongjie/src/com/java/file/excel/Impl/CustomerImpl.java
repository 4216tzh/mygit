package com.java.file.excel.Impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import com.java.file.excel.Customer;
import com.java.file.excel.Excel;
import com.java.file.excel.ExcelUtil;

public class CustomerImpl implements Excel<Customer> {

	/**
	 * ����
	 */
	private static final String[] CLOWNAME = { "id", "����", "סַ", "�˺�", "�绰����",
			"�Ա�", "����", "����", "��ע", "�Ƿ��Ա" };
	/**
	 * 
	 */
	private static final String SHEETNAME = "��Ա�˿���Ϣ";

	@Override
	public void downLoad(List<Customer> list, String fileName) {
		if (list == null || list.size() == 0) {
			return;
		}
		HSSFWorkbook workbook = ExcelUtil.creatHead(SHEETNAME, CLOWNAME);
		HSSFSheet sheet = workbook.getSheet(SHEETNAME);

		for (int i = 0; i < list.size(); i++) {
			Customer customers = list.get(i);
			HSSFRow rows = sheet.createRow(i + 1);
			rows.createCell(0).setCellValue(customers.getId());
			rows.createCell(1).setCellValue(customers.getName());
			if (customers.getAddress() == null) {
				rows.createCell(2).setCellValue("");
			} else {
				rows.createCell(2).setCellValue(customers.getAddress());
			}
			rows.createCell(3).setCellValue(customers.getAccount());
			rows.createCell(4).setCellValue(customers.getTel());
			rows.createCell(5).setCellValue(customers.getSex());
			rows.createCell(6).setCellValue(customers.getAge());
			rows.createCell(7).setCellValue(customers.getHoppy());
			rows.createCell(8).setCellValue(customers.getRemark());
			rows.createCell(9).setCellValue((customers.getIsVip() ? "��" : "��"));
		}
		try {
			FileOutputStream fout = new FileOutputStream(Customer.PATH
					+ fileName + ".xls");
			workbook.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	@SuppressWarnings("unused")
	public List<Customer> upLoad(String file) {
		List<Customer> list = new ArrayList<Customer>();
		try {
			// 1.�õ�Excel���ö���
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
			// 2.�õ�Excel����������
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			// 3.�õ�Excel���������
			HSSFSheet sheet = workbook.getSheet(SHEETNAME);
			// ������
			int trLength = sheet.getLastRowNum();
			// 4.�õ�Excel���������
			HSSFRow row = sheet.getRow(0);
			// ������
			int tdLength = row.getLastCellNum();
			// 5.�õ�Excel������ָ���еĵ�Ԫ��
			HSSFCell cell = row.getCell(1);
			// 6.�õ���Ԫ����ʽ
			CellStyle cellStyle = cell.getCellStyle();

			for (int i = 0; i < trLength; i++) {
				Customer customer = new Customer();
				HSSFRow rows = sheet.getRow(i + 1);
				for (int j = 0; j < tdLength; j++) {
					HSSFCell cells = rows.getCell(j);
					if (cells != null) {
						cells.setCellType(Cell.CELL_TYPE_STRING);
					}
					if (j == 0) {
						customer.setId(cells.getStringCellValue());
					} else if (j == 1) {
						customer.setName(cells.getStringCellValue());
					} else if (j == 2) {
						customer.setAddress(cells.getStringCellValue());
					} else if (j == 3) {
						customer.setAccount(cells.getStringCellValue());
					} else if (j == 4) {
						customer.setTel(cells.getStringCellValue());
					} else if (j == 5) {
						customer.setSex(cells.getStringCellValue());
					} else if (j == 6) {
						customer.setAge(Integer.valueOf(cells
								.getStringCellValue()));
					} else if (j == 7) {
						customer.setHoppy(cells.getStringCellValue());
					} else if (j == 8) {
						customer.setRemark(cells.getStringCellValue());
					} else if (j == 9) {
						customer.setIsVip((cells.getStringCellValue().equals(
								"��") ? true : false));
					}

				}

				list.add(customer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return list;
	}
}
