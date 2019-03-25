package com.java.annonation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * 利用注解和反射生成导出excel
 * @author tzh
 *
 */
public final class ExcelUtil {
	
	/**
	 * 获取表头内容
	 * @param clazz
	 * @return
	 */
	private static List<String> getHeader(Class<?> clazz){
		Field[] fileds=clazz.getDeclaredFields();
		List<String> header=new ArrayList<String>();
		for(Field f:fileds){
			f.setAccessible(true);
			AutoWire autoWire=f.getAnnotation(AutoWire.class);
			if(autoWire!=null){
				header.add(autoWire.description());
			}
		}
		return header;
	}
	
	/**
	 * 创建表头
	 * @param sheet
	 * @param clazz
	 */
	private static void createHeader(Sheet sheet,Class<?> clazz){
		Row row=sheet.createRow(0);
		List<String> headers=getHeader(clazz);
		int index=0;
		for(String str:headers){
			Cell cell=row.createCell(index);
			cell.setCellValue(str);
			index++;
		}
	}
	
	/**
	 * 写入excel的内容
	 * @param sheet
	 * @param list
	 */
	@SuppressWarnings("rawtypes")
	private static void writeExcel(Sheet sheet,List list,Class<?> clazz){
		try{
		int r=1;
		Field[] fileds=clazz.getDeclaredFields();
		for(int x=0;x<list.size();x++){
			Row rows=sheet.createRow(r);
			r++;
			int c=0;
			for(Field filed:fileds){
				filed.setAccessible(true);
				/*String methodName="get"+filed.getName().substring(0, 1).toUpperCase()+filed.getName().substring(1);
				Method m=model.getClass().getMethod(methodName, new Class[]{});
				Object obj=m.invoke(model, new Object[]{});*/
				Object obj=filed.get(list.get(x));
				Cell cell=rows.createCell(c);
				if(obj!=null){
				if(obj instanceof Date){
					cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(obj));
					c++;
					continue;
				}else if(obj  instanceof BigDecimal){
					BigDecimal bb=(BigDecimal)obj;
					bb.setScale(2,BigDecimal.ROUND_HALF_UP);
					cell.setCellValue(bb.doubleValue());
					c++;
					continue;
				}
				cell.setCellValue(obj.toString());
				c++;
				}else{
					cell.setCellValue("");
					c++;
				}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成excel文件
	 * @param clazz 对象类
	 * @param list 写入内容集合
	 * @param path 路径
	 * @param fileName 文件名
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public static void downLoadExcel(Class<?> clazz,List list,String path,String fileName){
		
		if(list==null||list.size()==0){
			return;
		}
		Workbook wb=null;
		if(fileName.toLowerCase().endsWith(".xlsx")){
			wb=new XSSFWorkbook();
		}else{
			wb=new HSSFWorkbook();
		}
		Sheet sheet=wb.createSheet();
		//创建表头
		createHeader(sheet,clazz);
		
		//写入excel内容
		writeExcel(sheet,list,clazz);
		
		FileOutputStream fos=null;
		try{
			 fos=new FileOutputStream(path+fileName);
			wb.write(fos);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		List<StuModel> list=new ArrayList<StuModel>();
		StuModel stu=new StuModel();
		stu.setAge(111);
		stu.setBirthday(new Date());
		stu.setName("bob");
		stu.setSex("man");
		stu.setMajor("english");
		stu.setMoney(new BigDecimal(11.222d));
		stu.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		list.add(stu);
		
		downLoadExcel(StuModel.class,list,"D:/Redis/","index.xlsx");
	}

}
