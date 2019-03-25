package com.java.springboot.amq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping(value="/")
public class JmsProducer {
	
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	@RequestMapping(value="/topAdd")
	public String topAdd() {
		JSONObject json=new JSONObject();
		json.put("topAdd", "test1");
		jmsMessagingTemplate.convertAndSend(JmsConfig.TOPIC_CREATE, json.toJSONString());
		return "hello topAdd";
	}
	
	@RequestMapping(value="/queueAdd")
	public String queueAdd() {
		JSONObject json=new JSONObject();
		json.put("queueAdd", "test queue");
		jmsMessagingTemplate.convertAndSend(JmsConfig.QUEUE_CREATE, json.toJSONString());
		return "hello queueAdd";
	}

}
