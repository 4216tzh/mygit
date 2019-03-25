package com.java.springboot.socket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * websocket消息发送控制,
 * @EnableScheduling开启定时任务支持
 * @author tzh
 *
 */
@Controller
@EnableScheduling
@RequestMapping(value="/socket")
public class SocketController {
	/**
	 * 类似jdbcTemplate功能
	 */
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@RequestMapping(value="/index")
	public String index(){
		return "index";
	}
	/**
	 * 发送消息
	 * @param message 发送内容
	 * @return
	 */
	@MessageMapping("/send")
	@SendTo("/topic/send")
	public SocketMessage send(SocketMessage message){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.date = df.format(new Date());
        return message;
	}
	
	/**
	 * 定时任务
	 * @return
	 * @throws Exception
	 */
	@Scheduled(fixedRate = 10000)
    @SendTo("/topic/callback")
    public Object callback() throws Exception {
        // 发现消息
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        messagingTemplate.convertAndSend("/topic/callback", df.format(new Date()));
        return "callback";
    }
	
	
}
