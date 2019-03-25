package com.java.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;
import org.junit.Test;

public class ZipUtil implements Serializable,Comparable<ZipUtil>{
	
	private static final long serialVersionUID = 1111L;
	public static final String Exm;
	
	static{
		Exm="test";
	}
	
	/** 
     * logger 
     */  
    protected static Logger logger = Logger.getLogger(ZipUtil.class);  
  public static void main(String[] args) {
	  File file = new File("E:/zip/333.zip");  
      System.out.println();  
      String temp =file.getParentFile().getPath();  
      try {  
          uncompressZip(file, temp);  
      } catch (Exception e) {  
          logger.error("��ѹʧ�ܣ�");  
          e.printStackTrace();  
      }  
}
    
    /** 
     * ��ѹzipѹ���� jdk1.5 
     * 
     * @param storeFile ����ѹ��zip�ļ�·�� ��D:\\TT\\rt.txt.zip  || D:/TT/rt.txt.zip 
     * @param tempPath  ��ѹ����ļ�·�� �磺D:\\TT\\ || D:/TT/ 
     * @throws Exception 
     */  
    public static void uncompressZip(File storeFile, String tempPath) throws Exception {  
        File file = storeFile;  
        String temp = tempPath;  
        FileInputStream fis = null;  
        ZipInputStream zins = null;  
  
        //��ѹ��  
        try {  
            fis = new FileInputStream(file);  
            zins = new ZipInputStream(fis);  
            ZipEntry ze = null;  
            byte[] ch = new byte[256];  
            while ((ze = zins.getNextEntry()) != null) {  
                File zfile = new File(temp + "/" + ze.getName());  
                File fpath = new File(zfile.getParentFile().getPath());  
                if (ze.isDirectory()) {  
                    if (!zfile.exists())  
                        zfile.mkdirs();
                    zins.closeEntry();  
                } else {  
                    if (!fpath.exists())  
                        fpath.mkdirs();  
                    FileOutputStream fouts = new FileOutputStream(zfile);  
                    int i;  
                    while ((i = zins.read(ch)) != -1)  
                        fouts.write(ch, 0, i);  
                    zins.closeEntry();  
                    fouts.close();  
                }  
            }  
            fis.close();  
            zins.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
            file.delete();  
            throw new RuntimeException("�ļ�" + file.toString() + "��ѹʧ�ܣ�ԭ��" + e.toString());  
        }  
    }  
	
    @Test  
    public void testTarFile() {  
        String filePath = "E:/zip/";  
        String[] fileName = {"111.txt", "222.txt"};  
        String tarName = "all.zip";  
  
        try {  
            tarFile(filePath, fileName, tarName);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
    }
    
    
    /** 
     * ������ļ�ѹ����һ��zip  jdk 1.5 
     * 
     * @param filePath Ҫѹ�����ļ����ڵ�·�� �磺D:/TT/ 
     * @param fileName Ҫѹ�����ļ�����      �磺ss.txt��st.txt 
     * @param tarName  ѹ�����ѹ���ļ�����   �磺all.zip 
     * @return 
     * @throws Exception 
     */  
    public static String tarFile(String filePath, String[] fileName, String tarName) throws Exception {  
  
        String[] filenames = fileName;  
  
        byte[] buf = new byte[1024];  
  
        String outFilename = tarName;  
  
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(filePath + File.separator + outFilename));  
  
        for (int i = 0; i < filenames.length; i++) {  
  
            FileInputStream in = new FileInputStream(filePath + File.separator + filenames[i]);  
  
            out.putNextEntry(new ZipEntry(filenames[i]));  
  
            int len = 0;  
  
            while ((len = in.read(buf)) > 0) {  
  
                out.write(buf, 0, len);  
            }  
  
            out.closeEntry();  
            in.close();  
        }  
  
        out.close();  
        return filePath;  
    }  
  
    
  
    /** 
     * �ڱ�Ҫ������´���ѹ���ļ����Ŀ¼,����ָ���Ĵ��·����û�б����� 
     * 
     * @param destParam ָ���Ĵ��·��,�п��ܸ�·����û�б����� 
     */  
    private static void createDestDirectoryIfNecessary(String destParam) {  
        File destDir = null;  
        if (destParam.endsWith(File.separator)) {  
            destDir = new File(destParam);  
        } else {  
            destDir = new File(destParam.substring(0, destParam.lastIndexOf(File.separator)));  
        }  
        if (!destDir.exists()) {  
            destDir.mkdirs();  
        }  
    }

	@Override
	public int compareTo(ZipUtil paramT) {
		// TODO Auto-generated method stub
		return 0;
	}  

}
