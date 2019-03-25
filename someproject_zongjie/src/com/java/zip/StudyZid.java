package com.java.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

/**
 * ѹ���ļ�UTIL
 * @author tzh
 *
 */
@SuppressWarnings("resource")
public class StudyZid {
	
	
	public static void main(String[] args) {
		//decompressionZip(new File("E:/zip/333.zip"));
		compressFile("H:\\office ���߱༭\\","E:\\zip\\����һ������.zip");
	}
	
	/**
	 * 
	 * @param dirPath ��ѹ���ļ���
	 * @param compressFileName ѹ���ļ���
	 * @return
	 */
	
	public static boolean compressFile(String dirPath,String compressFileName){
		Boolean flag=false;
		try{
			File sourceFile=new File(dirPath);
			
			File targetFile=new File(compressFileName);
			ZipOutputStream zos=new ZipOutputStream(targetFile);
			//��ֹ��������
			zos.setEncoding("GBK");
			if(!sourceFile.isDirectory()){
				throw new Exception("���ļ��в����ڻ����ⲻ��һ���ļ���");
			}
			dirToZip(sourceFile,zos,dirPath);
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	
	public static void dirToZip(File file,ZipOutputStream zos,String dirPath) throws Exception{
		File[] files=file.listFiles();
		if(files==null){
			return;
		}
		if(files.length==0){
			String name=getFilePath(dirPath,file);
			ZipEntry entry=new ZipEntry(name);
			zos.putNextEntry(entry);
			zos.closeEntry();
		}else{
			for(File f:files){
				if(f.isFile()){
					//���ļ�����ѹ���ļ���
					fileToZip(f,zos,dirPath);
				}else{
					dirToZip(f,zos,dirPath);
				}
			}
		}
		
	}
	
	public static void fileToZip(File file,ZipOutputStream zos,String dirPath){
		try{
			FileInputStream fis=new FileInputStream(file);
			byte[] b=new byte[1024];
			String name=getFilePath(dirPath,file);
			ZipEntry entry=new ZipEntry(name);
			zos.putNextEntry(entry);
			int index=0;
			while((index=fis.read(b))!=-1){
				zos.write(b, 0, index);
			}
			zos.closeEntry();
			fis.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String getFilePath(String path,File f){
		String absPath=f.getAbsolutePath();
		if(f.isDirectory()){
			absPath+=File.separator;
		}
		if(!path.endsWith(File.separator)){
			path+=File.separator;
		}
		int index=absPath.indexOf(path);
		//int len=absPath.length();
		return absPath.substring(index,absPath.length());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * ��ѹ�ļ�
	 * @param file ����ѹԴ�ļ�
	 */
	public static void decompressionZip(File file){
		ZipInputStream zis=null;
		InputStream is=null;
		String path=file.getParentFile().getPath();
		try{
			is=new FileInputStream(file);
			zis=new ZipInputStream(is);
			java.util.zip.ZipEntry ze=null;
			byte[] b=new byte[512];
			while((ze=zis.getNextEntry())!=null){
				File f=new File(path+"/"+ze.getName());
				File ff=new File(f.getParentFile().getPath());
				if(ze.isDirectory()){
					f.mkdirs();
					zis.closeEntry();
				}else{
					if(!ff.exists())
						ff.mkdir();
					FileOutputStream fos=new FileOutputStream(f);
					int x;
					while((x=zis.read(b))!=-1)
						fos.write(b, 0, x);
						zis.closeEntry();
						fos.close();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
			if(zis!=null){
				zis.close();
			}
			if(is!=null){
				is.close();
			}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
}
