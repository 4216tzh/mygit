package com.java.springboot.pay.wx.util;

public class PayConst {
	/**
	 * 微信：圆缘广告，APPID
	 */
	public static final String APPID = "wx59d10103d309d7b4";
	/**
	 * 微信统一下单接口
	 */
	public static final String ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	/**
	 * 商户号
	 */
	public static final String MCH_ID = "1370902102";
	/**
	 * 已付款
	 */
	public static final Integer PAY_YES = 1;
	/**
	 * 未付款
	 */
	public static final Integer PAY_NO = 0;
	/**
	 * 
	 */
	public static final String NONTIFY_URL = "/back/goDelivergoods";
}
