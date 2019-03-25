package com.java.springboot.pay;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.java.springboot.pay.wx.util.PayUtil;

@Controller
@RequestMapping("/aliPay")
public class AliPayController {
	private static final Logger logger = LoggerFactory.getLogger(AliPayController.class);
	
	/*@Autowired
	AlipayTradePagePayRequest alipayRequest;
	
	@RequestMapping(value="/pc")
	public void aliPayByPc(HttpServletRequest request,HttpServletResponse response){
		String returnUrl = "回调地址 http 自定义";
		String notify_url = PayUtil.getpath(request);
		alipayRequest.setReturnUrl(returnUrl);//前台通知
        alipayRequest.setNotifyUrl(notify_url);//后台回调
        JSONObject bizContent = new JSONObject();
        String out_trade_no=UUID.randomUUID().toString().replaceAll("-", "");
        bizContent.put("out_trade_no", out_trade_no);
        bizContent.put("total_amount", String.valueOf(111));//订单金额:元
        bizContent.put("subject","this is test");//订单标题
        bizContent.put("seller_id", "11111");//实际收款账号，一般填写商户PID即可
        bizContent.put("product_code", "QUICK_WAP_PAY");//手机网页支付
		bizContent.put("body", "两个苹果五毛钱");
		String biz = bizContent.toString().replaceAll("\"", "'");
        alipayRequest.setBizContent(biz);
        logger.info("业务参数:"+alipayRequest.getBizContent());
        String form = "fail";
        try {
            //form = AliPayConfig.getAlipayClient().pageExecute(alipayRequest).getBody();
        } catch (Exception e) {
        	logger.error("支付宝构造表单失败",e);
        }
	}*/

}
