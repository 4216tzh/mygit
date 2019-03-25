package com.java.file;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	/**
	 * 以byte的形式读取文件内容，将读取的内容一个个byte取出来，最后在转换成byte数组的形式返回
	 * 
	 * @param path
	 */
	public static byte[] readFileByByte(String path) {
		/**
		 * 每一次读取字节的长度
		 */
		if (path != null && !path.equals("")) {
			final Integer ReadOnceByteLenth = 256;
			File file = new File(path);
			List<Byte> list = new ArrayList<Byte>();
			DataInputStream dis = null;
			try {
				dis = new DataInputStream(new FileInputStream(file));
				byte[] once = new byte[ReadOnceByteLenth];
				int readLength = 0;
				while ((readLength = (dis.read(once))) != -1) {
					for (int i = 0; i < readLength; i++) {
						list.add(once[i]);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (dis != null) {
					try {
						dis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return toPrimitives(list.toArray(new Byte[0]));
		}
		return null;

	}

	/**
	 * 将包装类字节数组转化为基本类型字节数组，将转化后的结果返回
	 * 
	 * @param src
	 *            源数组
	 * @return
	 */
	public static byte[] toPrimitives(Byte[] src) {
		if (src == null || src.length <= 0) {
			return null;
		}
		byte[] tagret = new byte[src.length];
		for (int i = 0; i < src.length; i++) {
			tagret[i] = src[i];
		}
		return tagret;
	}

	/**
	 * 
	 * @param path
	 * @param list
	 */
	public static void writeByteTofile(String path, List<String> list) {
		if (list != null && list.size() > 0 && path != null && !path.trim().equals("")) {
			File file = new File(path);
			DataOutputStream dos = null;
			try {
				dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
				for (String str : list) {
					byte[] bytes = str.getBytes();
					// 二进制读取文件以一个一个byte写入文件，若使用字符串写入则会造成字符乱码问题
					for (byte b : bytes) {
						dos.writeByte(b);
					}
					// 换行
					for (byte b : "\r\n".getBytes()) {
						dos.writeByte(b);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (dos != null)
					try {
						dos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}
	}
}
