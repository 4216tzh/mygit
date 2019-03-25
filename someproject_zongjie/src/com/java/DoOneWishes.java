package com.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class DoOneWishes {
	
	public static void main(String[] args) {
		compress("E:/zip/","H:/usr/这是一次测试.zip","GBK");
	}
	
	
	/**
	 * 将文件夹中的所有文件压缩成压缩文件
	 * @param sourceDir 源文件目录(要压缩的文件目录)
	 * @param targetFile 生成的压缩文件
	 * @param encoding 编码
	 */
	public static void compress(String sourceDir,String targetFile,String encoding){
		ZipOutputStream zos=null;
		
		try{
			File sourceFile=new File(sourceDir);
			if(!sourceFile.exists()||!sourceFile.isDirectory()){
				throw new Exception("这不是一个文件夹或者该文件夹不存在");
			}
			zos=new ZipOutputStream(new File(targetFile));
			zos.setEncoding(encoding);
			String path=sourceFile.getAbsolutePath();
			//压缩文件
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
	 * 将sourcePath下的文件全部压缩
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
	 * 将文件压缩成压缩文件
	 * @param sourcePath 被压缩文件根目录
	 * @param file 被压缩文件
	 * @param zos 生成压缩文件流
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
	 * 获取文件名
	 * @param sourcePath 被压缩文件所在根目录
	 * @param file 被压缩文件
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
