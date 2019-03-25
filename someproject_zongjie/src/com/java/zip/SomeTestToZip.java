package com.java.zip;

import java.io.File;
import java.io.FileInputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class SomeTestToZip {
	
	public static void main(String[] args) {
		//StudyZid.getFilePath("H:\\��Ŀ\\", new File("H:\\��Ŀ\\�½��ı��ĵ�.txt"));
		compressFile("H:\\office ���߱༭\\","E:\\zip\\�Ҿ�����.zip");
	}
	
	
	/**
	 * ѹ���ļ�
	 * @param sourceDir Դѹ���ļ�(��ѹ���ļ���)
	 * @param targetDir Ŀ��ѹ���ļ�(���ɵ�ѹ���ļ�·��)
	 */
	public static void compressFile(String sourceDir,String targetFile){
		try{
			File sourceFile=new File(sourceDir);
			if(!sourceFile.isDirectory()){
				throw new Exception("�ⲻ��һ���ļ��л��߸��ļ��в�����!");
			}
			ZipOutputStream zos=new ZipOutputStream(new File(targetFile));
			dirToZip(sourceDir,zos,sourceFile);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static void dirToZip(String sourceDir,ZipOutputStream zos,File file) throws Exception{
		File[] files=file.listFiles();
		
		if(files.length==0){
			String path=getFilePath(sourceDir,file);
			ZipEntry entry=new ZipEntry(path);
			zos.putNextEntry(entry);
			zos.closeEntry();
		}else{
			for(File f:files){
				if(f.isFile()){
					fileToZip(sourceDir,zos,f);
				}else{
					dirToZip(sourceDir,zos,f);
				}
			}
		}
	}
	
	public static void fileToZip(String sourceDir,ZipOutputStream zos,File file) throws Exception{
		String path=getFilePath(sourceDir,file);
		ZipEntry entry=new ZipEntry(path);
		zos.putNextEntry(entry);
		
		FileInputStream fis=new FileInputStream(file);
		byte[] b=new byte[1024];
		int flag=0;
		while((flag=fis.read(b))!=-1){
			zos.write(b, 0, flag);
		}
		fis.close();
		zos.closeEntry();
		
	}
	
	public static String getFilePath(String sourceDir,File f){
		if(!sourceDir.endsWith(File.separator)){
			sourceDir+=File.separator;
		}
		
		String absPath=f.getAbsolutePath();
		if(f.isDirectory()){
			absPath+=File.separator;
		}
		int index=absPath.indexOf(sourceDir);
		return absPath.substring(index,absPath.length());
	}

}
