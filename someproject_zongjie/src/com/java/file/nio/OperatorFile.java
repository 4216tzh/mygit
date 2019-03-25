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
	 * һ��һ�ж�ȡ�ļ�
	 * @param file �ļ�·��
	 * @param charSet �ַ�����
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
	 * ���ʺ϶�ȡ̫���ļ�
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
	 * ���ļ���д������
	 * @param str д���ļ�������
	 * @param file �ļ�·��
	 * @param isNext �Ƿ���
	 * 
	 */
	public static void write(String str,String file,Boolean isNext){
		RandomAccessFile raf=null;
		FileChannel channel=null;
		try {
			 raf=new RandomAccessFile(file, "rws");
			 //���ļ�ĩβ׷������
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
	 * ��contentд���ļ���,������׷�����ļ����ݵ�ĩβ
	 * @param content д������
	 * @param path �ļ�·��
	 * @param isNext �Ƿ���
	 */
	public static void writeToFile(String content,String path,boolean isNext){
		RandomAccessFile raf=null;
		FileChannel channel=null;
		try {
			 raf=new RandomAccessFile(path, "rws");
			 //���ļ�ĩβ׷������
			 raf.seek(raf.length());
			 channel= raf.getChannel();
			
			 if(isNext){
				 content+="\r\n";
			 }
			
			 
			 //�ڶ���������ʾ�Ƿ�����content��д���ļ����ݵ�ĩβ(Ĭ��Ϊfalse)
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
            long filelength = fout.length();//��ȡ�ļ��ĳ���
            fout.seek(filelength);//���ļ��Ķ�дָ�붨λ���ļ���ĩβ
            fcout = fout.getChannel();//���ļ�ͨ��
            FileLock flout = null;
            while (true) {
                try {
                    flout = fcout.tryLock();//���ϵ���������������󲻵�����һ��������
                    break;
                } catch (Exception e) {
                	e.printStackTrace();
                    System.out.println("lock is exist ......");
                    Thread.sleep(1000);
                }
            }
            String str = " hello word!!!!\n";//��Ҫд�������

            fout.write(content.getBytes());//����Ҫд�������д���ļ�
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
		//read("C:/Users/Administrator/Desktop/bug�޸�.txt","gbk");
		//write("�����µ���ĭ�ǲ�ɫ�ģ�����ƭ���ң��Ǵ�����","C:/Users/Administrator/Desktop/111.txt",true);
		//writeToFile("�Ҿ����ң���һ�����̻�","C:/Users/Administrator/Desktop/111.txt",true);
		Thread t1=new Thread(new Runnable() {
			int i=0;
			@Override
			public void run() {
				while(i<10){
					runs("C:/Users/Administrator/Desktop/111.txt","�Ҿ����ң���һ�����̻�"+"\r\n");
					//writeToFile("�Ҿ����ң���һ�����̻�","C:/Users/Administrator/Desktop/111.txt",true);
					i++;
				}
			}
		});
		Thread t=new Thread(new Runnable() {
			int i=0;
			@Override
			public void run() {
				while(i<10){
					//writeToFile("�����µ���ĭ�ǲ�ɫ�ģ�����ƭ���ң��Ǵ�����","C:/Users/Administrator/Desktop/111.txt",true);
					runs("C:/Users/Administrator/Desktop/111.txt","�����µ���ĭ�ǲ�ɫ�ģ�����ƭ���ң��Ǵ�����"+"\r\n");
					i++;
				}
			}
		});
		t.setName("�̻��");
		t1.setName("��ĭ��");
		t.start();
		t1.start();
	}

}
