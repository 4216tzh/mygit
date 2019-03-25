package com.java.springboot.pay.wx.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

public class HttpRequestUtil {
	/**
	 * 调用微信提供的接口
	 * 
	 * @param map
	 *            调用微信接口所需的参数
	 * @param url
	 *            微信提供的接口
	 * @return 调用微信接口返回的信息
	 */
	public static Map<String, Object> getMap(SortedMap<String, Object> map,
			String url) {
		Map sMap = new HashMap();
		try {

			HttpURLConnection conn = (HttpURLConnection) new URL(url)
					.openConnection();
			conn.setRequestMethod("POST");
			 // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			String string = PayUtil.getRequestXml(map);
			BufferedOutputStream buffOutStr = new BufferedOutputStream(
					conn.getOutputStream());
			buffOutStr.write(string.getBytes());// 写入数据
			buffOutStr.flush();
			buffOutStr.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
			System.out.println(sb.toString());
			sMap = PayUtil.doXMLParse(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sMap;
	}

}
