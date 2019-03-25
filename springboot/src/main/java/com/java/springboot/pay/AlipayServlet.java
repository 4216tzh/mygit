package com.java.springboot.pay;

import java.io.IOException;  
import java.io.UnsupportedEncodingException;  
import java.net.URLEncoder;  
import java.util.Date;  
import java.util.HashMap;  
import java.util.Map;  
  
import java.util.UUID;

import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  

import org.json.JSONException;  
import org.json.JSONObject;  
  

import com.alipay.api.AlipayApiException;  
import com.alipay.api.internal.util.AlipaySignature;  

@WebServlet("/pay")
public class AlipayServlet extends HttpServlet{
	
	 private static final long serialVersionUID = 1L;  
	    public AlipayServlet() {  
	        super();  
	    }  
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	      
	    }  
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	         String cardid = request.getParameter("cardid");  
	         String userid = request.getParameter("userid");  
	         String mess = getMess(cardid,userid);  
	         response.getWriter().print(mess);  
	    }  
	    private static String getMess(String cardid,String userid) {  
	        String uid = UUID.randomUUID().toString().replaceAll("-", "");//WxAndAliPayService.getuid(userid);  
	        JSONObject orderInfo = new JSONObject();//WxAndAliPayService.getOrderInfo(cardid);  
	        JSONObject jo=new JSONObject();  
	          
	        try {  
	        String cardname=orderInfo.getString("cardname");  
	        String cardprice=orderInfo.getString("cardprice");  
	        String cardtime=orderInfo.getString("cardtime");  
	        String orderNum = UtilDate.getOrderNum();  
	        //插入一条订单记录  
	       // WxAndAliPayService.insertOrderInfo(cardname,uid,orderNum,cardprice,"支付宝支付");  
	        //公共参数  
	        Map<String, String> map = new HashMap<String, String>();  
	        map.put("app_id", ApplyConfig.app_id);  
	        map.put("method", "alipay.trade.app.pay");  
	        map.put("format", "json");  
	        map.put("charset", "utf-8");  
	        map.put("sign_type", "RSA");  
	        map.put("timestamp", UtilDate.getFormatString());  
	        map.put("version", "1.0");  
	        map.put("notify_url", ApplyConfig.service);  
	        Map<String, String> m = new HashMap<String, String>();  
	        m.put("body", "购买会员过程");//对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。  
	        m.put("subject", "购买会员");//商品的标题/交易标题/订单标题/订单关键字等。  
	        m.put("out_trade_no",orderNum);//商户网站唯一订单号  
	        m.put("timeout_express", "30m");//设置超时时间  
	        m.put("total_amount", cardprice);//价格  
	        m.put("seller_id", ApplyConfig.partner);//收款商户支付宝id  
	        m.put("product_code", "QUICK_MSECURITY_PAY");  
	        JSONObject bizcontentJson=new  JSONObject(m);  
	  
	        map.put("biz_content", bizcontentJson.toString());  
	       //对未签名原始字符串进行签名         
	       String rsaSign=null;  
	       String json4=null;  
	            rsaSign = AlipaySignature.rsaSign(map, ApplyConfig.private_key, "utf-8");  
	        Map<String, String> map4 = new HashMap<String, String>();  
	  
	        map4.put("app_id", ApplyConfig.app_id);  
	        map4.put("method", "alipay.trade.app.pay");  
	        map4.put("format", "json");  
	        map4.put("charset", "utf-8");  
	        //map4.put("sign_type",ApplyConfig.sign_type );  
	         
	        map4.put("timestamp", URLEncoder.encode(UtilDate.getFormatString(),"UTF-8"));  
	          
	        map4.put("version", "1.0");  
	        map4.put("notify_url",  URLEncoder.encode(ApplyConfig.service,"UTF-8"));  
	        //最后对请求字符串的所有一级value（biz_content作为一个value）进行encode，编码格式按请求串中的charset为准，没传charset按UTF-8处理  
	        map4.put("biz_content", URLEncoder.encode(bizcontentJson.toString(), "UTF-8"));  
	        Map par = AlipayCore.paraFilter(map4); //除去数组中的空值和签名参数  
	        json4= AlipayCore.createLinkString(par); //拼接后的字符串  
	        json4=json4 +"&sign_type=RSA&sign=" + URLEncoder.encode(rsaSign, "UTF-8");  
	            jo.put("info", json4);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return jo.toString();  
	    }  

}
