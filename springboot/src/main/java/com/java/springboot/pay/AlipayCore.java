package com.java.springboot.pay;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.methods.multipart.FilePartSource;
import org.apache.commons.httpclient.methods.multipart.PartSource;

public class AlipayCore {
	/** 
     * 除去数组中的空值和签名参数 
     *  
     * @param sArray 
     *            签名参数组 
     * @return 去掉空值与签名参数后的新签名参数组 
     */  
    public static Map<String, String> paraFilter(Map<String, String> sArray) {  
  
        Map<String, String> result = new HashMap<String, String>();  
  
        if (sArray == null || sArray.size() <= 0) {  
            return result;  
        }  
  
        for (String key : sArray.keySet()) {  
            String value = sArray.get(key);  
            if (value == null || value.equals("")  
                    || key.equalsIgnoreCase("sign")  
                    || key.equalsIgnoreCase("sign_type")) {  
                continue;  
            }  
            result.put(key, value);  
        }  
  
        return result;  
    }  
  
    /** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串 
     *  
     * @param params 
     *            需要排序并参与字符拼接的参数组 
     * @return 拼接后字符串 
     */  
    public static String createLinkString(Map<String, String> params) {  
  
        List<String> keys = new ArrayList<String>(params.keySet());  
        Collections.sort(keys);  
  
        String prestr = "";  
  
        for (int i = 0; i < keys.size(); i++) {  
            String key = keys.get(i);  
            String value = params.get(key);  
  
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符  
                prestr = prestr + key + "=" + value;  
            } else {  
                prestr = prestr + key + "=" + value + "&";  
            }  
        }  
  
        return prestr;  
    }  
  
    public static String sign(String content, String privateKey) {  
        try {  
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));  
            KeyFactory keyf = KeyFactory.getInstance(ApplyConfig.sign_type);  
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);  
  
            java.security.Signature signature = java.security.Signature.getInstance(ApplyConfig.SIGN_ALGORITHMS);  
  
            signature.initSign(priKey);  
            signature.update(content.getBytes(ApplyConfig.input_charset));  
  
            byte[] signed = signature.sign();  
  
            return Base64.encode(signed);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return null;  
    }  
  
    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库） 
     *  
     * @param sWord 
     *            要写入日志里的文本内容 
     */  
    public static void logResult(String sWord) {  
        FileWriter writer = null;  
        try {  
            writer = new FileWriter(ApplyConfig.log_path + "alipay_log_"  
                    + System.currentTimeMillis() + ".txt");  
            writer.write(sWord);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (writer != null) {  
                try {  
                    writer.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
  
    /** 
     * 生成文件摘要 
     *  
     * @param strFilePath 
     *            文件路径 
     * @param file_digest_type 
     *            摘要算法 
     * @return 文件摘要结果 
     */  
    public static String getAbstract(String strFilePath, String file_digest_type)  
            throws IOException {  
        PartSource file = new FilePartSource(new File(strFilePath));  
        if (file_digest_type.equals("MD5")) {  
            return DigestUtils.md5Hex(file.createInputStream());  
        } else if (file_digest_type.equals("SHA")) {  
            return DigestUtils.sha256Hex(file.createInputStream());  
        } else {  
            return "";  
        }  
    }  
  
    /** 
     * create the order info. 创建订单信息 
     *  
     */  
    public static String getOrderInfo(String subject, String body, String price) {  
        // 合作者身份ID  
        String orderInfo = "partner=" + "\"" + ApplyConfig.partner + "\"";  
  
        // 卖家支付宝账号  
        orderInfo += "&seller_id=" + "\"" + ApplyConfig.seller + "\"";  
  
        // 商户网站唯一订单号  
        orderInfo += "&out_trade_no=" + "\"" + UtilDate.getOrderNum() + "\"";  
  
        // 商品名称  
        orderInfo += "&subject=" + "\"" + subject + "\"";  
  
        // 商品详情  
        orderInfo += "&body=" + "\"" + body + "\"";  
  
        // 商品金额  
        orderInfo += "&total_fee=" + "\"" + price + "\"";  
  
        // 服务器异步通知页面路径  
        orderInfo += "¬ify_url=" + "\""  
                + "http://http://10.60.7.92:8080/AppServer/CallbackServlet" + "\"";  
  
        // 接口名称， 固定值  
        orderInfo += "&service=\"mobile.securitypay.pay\"";  
  
        // 支付类型， 固定值  
        orderInfo += "&payment_type=\"1\"";  
  
        // 参数编码， 固定值  
        orderInfo += "&_input_charset=\"utf-8\"";  
  
        // 设置未付款交易的超时时间  
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。  
        // 取值范围：1m～15d。  
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。  
        // 该参数数值不接受小数点，如1.5h，可转换为90m。  
        orderInfo += "&it_b_pay=\"1d\"";  
  
        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空  
        //orderInfo += "&return_url=\"http://www.tianjiandao.com\"";  
  
        // 调用银行卡支付，需配置此参数，参与签名， 固定值  
        // orderInfo += "&paymethod=\"expressGateway\"";  
  
        return orderInfo;  
    }  
  
    public static String getSignType() {  
        return "sign_type=\"RSA\"";  
    }  
  
}  