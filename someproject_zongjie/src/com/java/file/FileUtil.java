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
	 * ��byte����ʽ��ȡ�ļ����ݣ�����ȡ������һ����byteȡ�����������ת����byte�������ʽ����
	 * 
	 * @param path
	 */
	public static byte[] readFileByByte(String path) {
		/**
		 * ÿһ�ζ�ȡ�ֽڵĳ���
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
	 * ����װ���ֽ�����ת��Ϊ���������ֽ����飬��ת����Ľ������
	 * 
	 * @param src
	 *            Դ����
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
					// �����ƶ�ȡ�ļ���һ��һ��byteд���ļ�����ʹ���ַ���д���������ַ���������
					for (byte b : bytes) {
						dos.writeByte(b);
					}
					// ����
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
