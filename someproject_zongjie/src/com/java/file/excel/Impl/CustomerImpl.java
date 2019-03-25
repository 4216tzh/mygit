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
	 * 列名
	 */
	private static final String[] CLOWNAME = { "id", "姓名", "住址", "账号", "电话号码",
			"性别", "年龄", "爱好", "备注", "是否会员" };
	/**
	 * 
	 */
	private static final String SHEETNAME = "会员顾客信息";

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
			rows.createCell(9).setCellValue((customers.getIsVip() ? "是" : "否"));
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
			// 1.得到Excel常用对象
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
			// 2.得到Excel工作簿对象
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			// 3.得到Excel工作表对象
			HSSFSheet sheet = workbook.getSheet(SHEETNAME);
			// 总行数
			int trLength = sheet.getLastRowNum();
			// 4.得到Excel工作表的行
			HSSFRow row = sheet.getRow(0);
			// 总列数
			int tdLength = row.getLastCellNum();
			// 5.得到Excel工作表指定行的单元格
			HSSFCell cell = row.getCell(1);
			// 6.得到单元格样式
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
								"是") ? true : false));
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
