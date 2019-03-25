package com.java.springboot.amq;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * 消息队列配置
 * @author tzh
 * 这里分别定义了queue 和topic 的增加，删除，修改的消息，分别用来接收调用服务的修改，添加，删除数据的对应的操作
 * （如果使用一个，那么就需要对数据进行判断到底是执行的什么操作。）
 */
@Configuration
public class JmsConfig {
	
	public final static String TOPIC_CREATE = "es.topic.create";
    public final static String QUEUE_CREATE = "es.queue.create";
    public final static String TOPIC_DELETE = "es.topic.delete";
    public final static String QUEUE_DELETE = "es.queue.delete";
    public final static String TOPIC_UPDATE = "es.topic.update";
    public final static String QUEUE_UPDATE = "es.queue.update";
    
    @Bean("esQueueCreate")
    public Queue esQueueCreate() {
        return new ActiveMQQueue(QUEUE_CREATE);
    }
    @Bean("esQueueDelete")
    public Queue esQueueDelete() {
        return new ActiveMQQueue(QUEUE_DELETE);
    }
    @Bean("esQueueUpdate")
    public Queue esQueueUpdate() {
        return new ActiveMQQueue(QUEUE_UPDATE);
    }

    @Bean("esTopicCreate")
    public Topic esTopicCreate() {
        return new ActiveMQTopic(TOPIC_CREATE);
    }
    @Bean("esTopicDelete")
    public Topic esTopicDelete() {
        return new ActiveMQTopic(TOPIC_DELETE);
    }
    @Bean("esTopicUpdate")
    public Topic esTopicUpdate() {
        return new ActiveMQTopic(TOPIC_UPDATE);
    }
    
    /**
     * topic模式的ListenerContainer
     * @param activeMQConnectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }

    /**
     * queue模式的ListenerContainer
     * @param activeMQConnectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }

}
