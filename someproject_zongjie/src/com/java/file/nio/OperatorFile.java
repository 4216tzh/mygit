package com.java.file.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperatorFile {
	
	/**
	 * 一行一行读取文件
	 * @param file 文件路径
	 * @param charSet 字符编码
	 * @return
	 */
	public static List<String> read(String file,String charSet){
		RandomAccessFile raf=null;
		List<String> list=new ArrayList<String>();
		try {
			 raf=new RandomAccessFile(file, "r");
			 String str;
			 while((str=raf.readLine())!=null){
				 String res=new String(str.getBytes("ISO-8859-1"),charSet);
				 list.add(res);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(raf!=null){
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	/**
	 * 不适合读取太大文件
	 * @param file
	 * @return
	 */
	public static Object readByChannel(String file){
		RandomAccessFile raf=null;
		FileChannel channel=null;
		String res;
		try {
			raf=new RandomAccessFile(file, "r");
			channel=raf.getChannel();
			ByteBuffer buffer=ByteBuffer.allocate((int)raf.length());
			int data=channel.read(buffer);
			res=new String(buffer.array());
			System.out.println(res);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(raf!=null){
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(channel!=null){
				try {
					channel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 * 往文件中写入内容
	 * @param str 写入文件的内容
	 * @param file 文件路径
	 * @param isNext 是否换行
	 * 
	 */
	public static void write(String str,String file,Boolean isNext){
		RandomAccessFile raf=null;
		FileChannel channel=null;
		try {
			 raf=new RandomAccessFile(file, "rws");
			 //在文件末尾追加内容
			 raf.seek(raf.length());
			 channel= raf.getChannel();
			 if(isNext){
				 str+="\r\n";
			 }
			 ByteBuffer buffer=Charset.forName("utf-8").encode(str);
			 
			/* ByteBuffer buffer=ByteBuffer.allocate(1024*1024);
			 buffer.put(str.getBytes());
			 channel.write(buffer);*/
			 int len=0;
			 while((len=channel.write(buffer))!=0){
				 System.out.println(len);
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(raf!=null){
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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
	 * 将content写入文件中,并且是追加在文件内容的末尾
	 * @param content 写入内容
	 * @param path 文件路径
	 * @param isNext 是否换行
	 */
	public static void writeToFile(String content,String path,boolean isNext){
		RandomAccessFile raf=null;
		FileChannel channel=null;
		try {
			 raf=new RandomAccessFile(path, "rws");
			 //在文件末尾追加内容
			 raf.seek(raf.length());
			 channel= raf.getChannel();
			
			 if(isNext){
				 content+="\r\n";
			 }
			
			 
			 //第二个参数表示是否将内容content，写在文件内容的末尾(默认为false)
			 //new FileOutputStream("", true);
			 ByteBuffer sendBuffer=ByteBuffer.wrap(content.getBytes());
			 int data=channel.write(sendBuffer);
			 System.out.println("data:"+data);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(raf!=null){
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(channel!=null){
				try {
					channel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void runs(String filePath,String content) {
		 System.out.println(Thread.currentThread().getName());
        RandomAccessFile fout = null;
        FileChannel fcout = null;
        try {
            fout = new RandomAccessFile(filePath, "rw");
            long filelength = fout.length();//获取文件的长度
            fout.seek(filelength);//将文件的读写指针定位到文件的末尾
            fcout = fout.getChannel();//打开文件通道
            FileLock flout = null;
            while (true) {
                try {
                    flout = fcout.tryLock();//不断的请求锁，如果请求不到，等一秒再请求
                    break;
                } catch (Exception e) {
                	e.printStackTrace();
                    System.out.println("lock is exist ......");
                    Thread.sleep(1000);
                }
            }
            String str = " hello word!!!!\n";//需要写入的内容

            fout.write(content.getBytes());//将需要写入的内容写入文件
            flout.release();
            fcout.close();
            fout.close();

        } catch (IOException e1) {
            e1.printStackTrace();
            System.out.print("file no find ...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (fcout != null) {
                try {
                    fcout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    fcout = null;
                }
            }
            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    fout = null;
                }
            }
        }

    }
	
	
	public static void main(String[] args)  {
		//read("C:/Users/Administrator/Desktop/bug修复.txt","gbk");
		//write("阳光下的泡沫是彩色的，就像被骗的我，是脆弱的","C:/Users/Administrator/Desktop/111.txt",true);
		//writeToFile("我就是我，不一样的烟火","C:/Users/Administrator/Desktop/111.txt",true);
		Thread t1=new Thread(new Runnable() {
			int i=0;
			@Override
			public void run() {
				while(i<10){
					runs("C:/Users/Administrator/Desktop/111.txt","我就是我，不一样的烟火"+"\r\n");
					//writeToFile("我就是我，不一样的烟火","C:/Users/Administrator/Desktop/111.txt",true);
					i++;
				}
			}
		});
		Thread t=new Thread(new Runnable() {
			int i=0;
			@Override
			public void run() {
				while(i<10){
					//writeToFile("阳光下的泡沫是彩色的，就像被骗的我，是脆弱的","C:/Users/Administrator/Desktop/111.txt",true);
					runs("C:/Users/Administrator/Desktop/111.txt","阳光下的泡沫是彩色的，就像被骗的我，是脆弱的"+"\r\n");
					i++;
				}
			}
		});
		t.setName("烟火号");
		t1.setName("泡沫号");
		t.start();
		t1.start();
	}

}
