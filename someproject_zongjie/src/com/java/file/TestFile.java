package com.java.file;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestFile {
	public static void main(String[] args) {
		new HashMap<String,Object>();
		System.out.println(new String(readBybyte("C://Users//Administrator//Desktop//20171205(tzh)进项修改.txt")));
	}
	
	
	public static byte[] readBybyte(String path){
		//"C://Users//Administrator//Desktop//20171205(tzh)进项修改.txt"
				//字节流
				File file=new File(path);
				DataInputStream dis=null;
				try {
					 dis=new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
					int length=dis.available();
					byte[] b=new byte[length];
					List<Byte> list=new ArrayList<Byte>();
					int readLength;
					while ((readLength=(dis.read(b)))!=-1) {
						for(int x=0;x<readLength;x++){
							list.add(b[x]);
						}
					}
					Byte[] bb=list.toArray(new Byte[0]);
					byte[] res=new byte[bb.length];
					for(int y=0;y<bb.length;y++){
						res[y]=bb[y];
					}
					return res;
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					if(dis!=null){
						try {
							dis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
		return null;
	}
	
	/**
	 * 字符流
	 * @param path
	 */
	public static char[] readByChar(String path){
		
		Reader sr=null;
		char[] res=null;
		try {
			sr=new StringReader(path);
			char[] c=new char[2048];
			List<Character> list=new ArrayList<Character>();
			int length;
			while((length=(sr.read(c)))!=-1){
				for(int i=0;i<length;i++){
					list.add(c[i]);
				}
			}
			 res=new char[list.size()];
			for(int x=0;x<list.size();x++){
				res[x]=list.get(x);
			}
			return res;
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(sr!=null){
				try {
					sr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return res;
	}
	
}
