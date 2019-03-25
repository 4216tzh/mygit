package com.java.springboot.pay.wx;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.springboot.pay.wx.util.GenerateQrCodeUtil;
import com.java.springboot.pay.wx.util.HttpRequestUtil;
import com.java.springboot.pay.wx.util.PayConst;
import com.java.springboot.pay.wx.util.PayUtil;


@Controller
@RequestMapping("/pay")
public class Pay4WxAction {

	/**
	 * 生成二维码图片,用户扫码支付
	 * 
	 * @param code_url
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/Pay4Wx")
	@ResponseBody
	public void index(String code_url, HttpServletRequest request,
			HttpServletResponse response) {

		GenerateQrCodeUtil.encodeQrcode(code_url, response);
	}

	/**
	 * 调用微信接口生成订单
	 * 
	 * @param id
	 *            电子券id
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getorder", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String generateorder(String id, HttpServletRequest request) {
			
		/*String notify_url = PayUtil.getpath(request);
		HttpSession session=request.getSession(true);
		// System.out.println("notify_url>>>>>>>>" + notify_url);
		TCliCust tCliCust = (TCliCust) session
				.getAttribute(ActConst.CURRENT_USER);
		// 判断用户是否登录
		if (tCliCust == null) {
			return PConst.NO_USER;
		}
		TEltkt tEltkt = tEltktService.getTEltktById(id);
		if (tEltkt != null) {
			try {
				Integer totalfee = tEltkt.getEltktPrice().intValue();
				String out_trade_no = tCustLogService.paymentForEltkt(tCliCust,
						id);
				SortedMap<String, Object> map = new TreeMap<String, Object>();
				map.put("appid", PayConst.APPID);// 公众账号ID
				map.put("mch_id", PayConst.MCH_ID);// 商户号
				map.put("nonce_str", PayUtil.getRandomString(23));// 随机数
				map.put("body", tEltkt.getEltktTitle());// 商品详情,中文乱码
				map.put("notify_url", notify_url);// 回调地址
				map.put("out_trade_no", out_trade_no);// 订单号
				map.put("total_fee", String.valueOf(totalfee));// 支付金额
				map.put("spbill_create_ip", "123.12.12.123");//
				map.put("trade_type", PConst.TRADE_TYPE);
				// 一定要最后生成签名
				String sign = PayUtil.createSign(map);
				map.put("sign", sign);
				// 调用微信接口
				Map<String, Object> sMap = HttpRequestUtil.getMap(map,
						PayConst.ORDER_URL);
				// System.out.println((String) sMap.get("code_url"));
				String code_url = (String) sMap.get("code_url");
				// System.out.println(code_url);
				if (code_url != null) {
					return code_url;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		try{
			String notify_url = PayUtil.getpath(request);
			String out_trade_no=UUID.randomUUID().toString().replaceAll("-", "");
			SortedMap<String, Object> map = new TreeMap<String, Object>();
			map.put("appid", PayConst.APPID);// 公众账号ID
			map.put("mch_id", PayConst.MCH_ID);// 商户号
			map.put("nonce_str", PayUtil.getRandomString(23));// 随机数
			map.put("body", "this is test");// 商品详情,中文乱码
			map.put("notify_url", notify_url);// 回调地址
			map.put("out_trade_no", out_trade_no);// 订单号
			map.put("total_fee", String.valueOf(11));// 支付金额
			map.put("spbill_create_ip", "123.12.12.123");//
			map.put("trade_type", PConst.TRADE_TYPE);
			// 一定要最后生成签名
			String sign = PayUtil.createSign(map);
			map.put("sign", sign);
			// 调用微信接口
			Map<String, Object> sMap = HttpRequestUtil.getMap(map,
					PayConst.ORDER_URL);
			// System.out.println((String) sMap.get("code_url"));
			String code_url = (String) sMap.get("code_url");
			// System.out.println(code_url);
			if (code_url != null) {
				return code_url;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 常量
	 * 
	 * @author Administrator
	 * 
	 */
	private static class PConst {
		/**
		 * 交易类型
		 */
		public final static String TRADE_TYPE = "NATIVE";
		/**
		 * 用户未登录
		 */
		public final static String NO_USER = "nouser";
	}
}
