package com.java.springboot.pay;

public class ApplyConfig {
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串  
    public static String partner = "";  
    // 商户的私钥,使用支付宝自带的openssl工具生成。  
    public static String private_key="";  
      
    // 支付宝的公钥，无需修改该值  
    public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";  
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";  
      
    public static String seller = "";  
  
    // 调试用，创建TXT日志文件夹路径  
    public static String log_path = "d:\\";  
  
    // 字符编码格式 目前支持 gbk 或 utf-8  
    public static String input_charset = "UTF-8";  
      
    // 签名方式 不需修改  
    public static String sign_type = "RSA";  
      
    // 接收通知的接口名(这里的域名或者ip需要填写外网可以访问的地址)  
    public static String service = "http://127.0.0.1:8080/AppServer/CallbackServlet";  
    //APPID  
    public static String app_id="";  

}
