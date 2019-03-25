package com.java.springboot.amq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Component
public class JmsConsumer {

	@JmsListener(destination=JmsConfig.TOPIC_CREATE,containerFactory="jmsListenerContainerTopic")
	public void JtAddTop(String msg) {
		JSONObject json=JSONObject.parseObject(msg);
		System.out.println(json.toString());
	}
	
	@JmsListener(destination=JmsConfig.QUEUE_CREATE,containerFactory="jmsListenerContainerQueue")
	public void JtAddQueue(String msg) {
		JSONObject json=JSONObject.parseObject(msg);
		System.out.println(json.toString());
	}
}
