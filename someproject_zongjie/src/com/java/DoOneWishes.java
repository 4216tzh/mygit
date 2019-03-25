package com.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class DoOneWishes {
	
	public static void main(String[] args) {
		compress("E:/zip/","H:/usr/����һ�β���.zip","GBK");
	}
	
	
	/**
	 * ���ļ����е������ļ�ѹ����ѹ���ļ�
	 * @param sourceDir Դ�ļ�Ŀ¼(Ҫѹ�����ļ�Ŀ¼)
	 * @param targetFile ���ɵ�ѹ���ļ�
	 * @param encoding ����
	 */
	public static void compress(String sourceDir,String targetFile,String encoding){
		ZipOutputStream zos=null;
		
		try{
			File sourceFile=new File(sourceDir);
			if(!sourceFile.exists()||!sourceFile.isDirectory()){
				throw new Exception("�ⲻ��һ���ļ��л��߸��ļ��в�����");
			}
			zos=new ZipOutputStream(new File(targetFile));
			zos.setEncoding(encoding);
			String path=sourceFile.getAbsolutePath();
			//ѹ���ļ�
			dirToZip(path,sourceFile,zos);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(zos!=null){
				try {
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * ��sourcePath�µ��ļ�ȫ��ѹ��
	 * @param sourcePath
	 * @param file 
	 * @param zos
	 * @throws Exception
	 */
	private static void dirToZip(String sourcePath,File file,ZipOutputStream zos) throws Exception{
		File[] files=file.listFiles();
		if(files.length==0){
			String path=getFilePath(sourcePath,file);
			ZipEntry entry=new ZipEntry(path);
			zos.putNextEntry(entry);
			zos.closeEntry();
		}else{
			for(File f:files){
				if(f.isFile()){
					fileToZip(sourcePath,f,zos);
				}else{
					dirToZip(sourcePath,f,zos);
				}
			}
		}
	}
	
	/**
	 * ���ļ�ѹ����ѹ���ļ�
	 * @param sourcePath ��ѹ���ļ���Ŀ¼
	 * @param file ��ѹ���ļ�
	 * @param zos ����ѹ���ļ���
	 * @throws Exception
	 */
	private static void  fileToZip(String sourcePath,File file,ZipOutputStream zos) throws Exception{
		String path=getFilePath(sourcePath,file);
		ZipEntry entry=new ZipEntry(path);
		zos.putNextEntry(entry);
		
		FileInputStream fis=new FileInputStream(file);
		byte[] b=new byte[1024];
		int index=0;
		while((index=fis.read(b))!=-1){
			zos.write(b, 0, index);
		}
		fis.close();	
		zos.closeEntry();
	}
	
	/**
	 * ��ȡ�ļ���
	 * @param sourcePath ��ѹ���ļ����ڸ�Ŀ¼
	 * @param file ��ѹ���ļ�
	 * @return
	 */
	private static String getFilePath(String sourcePath,File file){
		
		String absPath=file.getAbsolutePath();
		if(file.isDirectory()){
			absPath+=File.separator;
		}
		if(!sourcePath.endsWith(File.separator)){
			sourcePath+=File.separator;
		}
		int index=absPath.indexOf(sourcePath);
		return absPath.substring(index+sourcePath.length());
	}
	
	

}
