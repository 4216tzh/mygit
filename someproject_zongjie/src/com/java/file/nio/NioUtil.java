package com.java.file.nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NioUtil {

	/**
	 * 
	 * @param filePath
	 */
	public static void readFile(String filePath) {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(filePath, "rws");
			// int len=(int)raf.length();
			byte[] b = new byte[1024];
			StringBuffer sb = new StringBuffer();
			while (raf.read(b) != -1) {
				String res = new String(b);
				sb.append(res);
			}
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeToFile(String content) {

	}

	public static void read(String filePath) {
		FileChannel channel=null;
		try {
			// ��ȡ�ļ�
			RandomAccessFile raf = new RandomAccessFile(filePath, "rws");
			// ��ȡ�ļ�ͨ��
			channel = raf.getChannel();
			Integer ii;
			System.out.println(Integer.MAX_VALUE);
			System.out.println("�ļ���С:" + raf.length());
			// ���û�����
			ByteBuffer buffer = ByteBuffer.allocate((int) raf.length());

			/*
			 * //ͨ��flip()������Buffer��дģʽ�л�����ģʽ���ڶ�ģʽ�£����Զ�ȡ֮ǰд�뵽buffer���������ݡ�
			 * buffer.flip(); //��ȡ������������ int data=channel.read(buffer);
			 * System.out.println("data��"+data);
			 */
			int data = channel.read(buffer);
			//System.out.println("��ȡ���ļ�����:" + new String(buffer.array()));
			// ��ջ�����������
			buffer.clear();
			
			//System.out.println("����󻺳���������:" + new String(buffer.array()));
			/*
			 * if(buffer.hasRemaining()){ String str=new String(buffer.array());
			 * System.out.println(str); }
			 */
			/*
			 * while((data=channel.read(buffer))!=-1){ buffer.flip();
			 * if(buffer.hasRemaining()){ String str=new String(buffer.array());
			 * System.out.println(str); } }
			 */

			// һ��һ�ж�ȡ�ļ��е�����
			String strs;
			StringBuffer sb=new StringBuffer();
			System.out.println(raf.readLine());
			while((strs=raf.readLine())!=null){
				sb.append(strs);
			}
			System.out.println("�ļ�����:"+sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(channel!=null){
				try {
					channel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * ���ļ��ĸ��ƣ�Ч�ʽϸ�
	 * @param filename
	 * @param srcpath
	 * @param destpath
	 * @throws Exception
	 */
	public void copyFile(String filename, String srcpath, String destpath)
			throws Exception {
		File source = new File(srcpath + "/" + filename);
		File dest = new File(destpath + "/" + filename);
		FileChannel in = null, out = null;
		try {
			in = new FileInputStream(source).getChannel();
			out = new FileOutputStream(dest).getChannel();
			long size = in.size();
			MappedByteBuffer buf = in.map(FileChannel.MapMode.READ_ONLY, 0,
					size);
			out.write(buf);
			/*FileLock lock=out.lock();
			lock.release();
			out.tryLock();*/
			
			source.delete();// �ļ�������ɺ�ɾ��Դ�ļ�
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
			out.close();
		}
	}

	public static void main(String[] args) throws Exception {
		// "H:/���/TortoiseSVN-1.8.0.24401-win32-svn-1.8.0.msi"
		//read("C:/Users/Administrator/Desktop/bug�޸�.txt");
		// readFile("C:/Users/Administrator/Desktop/bug�޸�.txt");
		File file = new File("C:/Users/Administrator/Desktop/trigger.txt");
		File files = new File("C:/Users/Administrator/Desktop/triggers.txt");
		/*
		 * if(file.exists()){ file.deleteOnExit(); } if(files.exists())
		 * files.delete();
		 */

		if (1 < (file.getFreeSpace() >> 10)) {
			int i=Integer.MAX_VALUE;
		}
		/*BufferedWriter writer=null;
		BufferedReader reader=null;
		try {
			 writer=Files.newBufferedWriter(Paths.get(""), StandardCharsets.UTF_8);
			 reader=Files.newBufferedReader(Paths.get(""),StandardCharsets.UTF_8);
			writer.write("�ȵ�������������ʿ�ٵ�");
			writer.flush();
			
			char[] c=new char[1024*1024*10];
			reader.read(c);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(writer!=null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}*/
		/*
		 * Byte b=-128; Byte b2=127; Integer iii=1111;
		 * System.out.println("ʮ������:"+Integer.toHexString(iii));
		 * System.out.println("������:"+Integer.toBinaryString(iii));
		 * System.out.println
		 * ("ʮ����:"+Integer.valueOf(Integer.toBinaryString(iii), 2));
		 */
		RandomAccessFile raf = new RandomAccessFile("C:/Users/Administrator/Desktop/bug�޸�.txt", "rws");
		String strs;
		StringBuffer sb=new StringBuffer();
		String str=new String(raf.readLine().getBytes("ISO-8859-1"),"gbk");
		System.out.println(str);
		while((strs=raf.readLine())!=null){
			sb.append(new String(strs.getBytes("ISO-8859-1"),"gbk")+"\r\n");
		}
		System.out.println("�ļ�����:"+sb.toString());
	}

}
