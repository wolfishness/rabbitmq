package com.ljs.consumer.consumer.topic;

/**
 * @ClassName: TopicReceiver
 * @Description: TODO
 * @Author: lujinshan
 * @Date: 2019/10/16 16:38
 * @Version: 1.0.0
 */

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiver {

    /**
     * 监听器监听指定的topic.message
     * @param str
     */
    @RabbitListener(queues="topic.message")
    @RabbitHandler
    public void process1(String str) {
        System.out.println("topic.message:"+str);
    }

    /**
     * 监听器监听指定的topic.messages
     * @param str
     */
    @RabbitListener(queues="topic.messages")
    @RabbitHandler
    public void process2(String str) {
        System.out.println("topic.messages:"+str);
    }
}

