package com.java.springboot.pay.wx.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class PayUtil {
	/**
	 * 得到回调地址
	 */
	public static String getpath(HttpServletRequest request) {
		String path = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ PayConst.NONTIFY_URL;
		return path;
	}

	/**
	 * 作为对微信的返回结果，告知微信已经接收到通知
	 * 
	 * @param str
	 * @return
	 */
	public static String getstring(String str) {
		if (StringUtils.isEmpty(str)) {
			return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
					+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
		} else {
			return "<xml>" + "<return_code><![CDATA[+" + str
					+ "+]]></return_code>"
					+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
		}

	}

	/**
	 * 创建签名
	 * 
	 * @param packageParams
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public static String createSign(SortedMap<String, Object> packageParams) {
		// 根据规则创建可排序的map集合

		StringBuffer sb = new StringBuffer();
		Set<Entry<String, Object>> es = packageParams.entrySet();// 字典序
		Iterator<Entry<String, Object>> it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			// 为空不参与签名、参数名区分大小写
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		// 第二步拼接key，key设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置
		sb.append("key=" + "79F01C27520E4D7F85B41CD9A12DE88F");
		String sign = MD5Util.MD5Encode(sb.toString(), "utf-8").toUpperCase();// MD5加密
		return sign;
	}

	/**
	 * 
	 * @Description：将请求参数转换为xml格式的string
	 * @paramparameters 请求参数
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public static String getRequestXml(SortedMap<String, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = "";
			try {
				v = (String) entry.getValue();
			} catch (Exception e) {
				v = entry.getValue() + "";
			}
			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k)
					|| "sign".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * 生成随机数
	 * 
	 * @param length
	 * 
	 * @return
	 */
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 将xml格式的字符串转换为map
	 * 
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map doXMLParse(String strxml) throws JDOMException,
			IOException {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
		if (null == strxml || "".equals(strxml)) {
			return null;
		}
		Map m = new HashMap();
		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = e.getValue();
			m.put(k, v);
		}
		// 关闭流
		in.close();
		return m;
	}

	/**
	 * 调用url并将返回的字符串转换为json对象
	 * 
	 * @param url
	 *            调用的url
	 * @return
	 */
	public static JSONObject getJsonObject(String url) {
		JSONObject js1 = null;
		try {
			URL urlpost = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) urlpost
					.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			InputStream inputStream;
			inputStream = conn.getInputStream();
			int size1 = inputStream.available();
			byte[] jsonBytes1 = new byte[size1];
			inputStream.read(jsonBytes1);
			String openid = new String(jsonBytes1, "UTF-8");
			js1 = (JSONObject) JSONObject.parse(openid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return js1;
	}
}
