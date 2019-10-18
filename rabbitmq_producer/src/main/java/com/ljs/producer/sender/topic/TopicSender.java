package com.ljs.producer.sender.topic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: TopicSender
 * @Description: TODO
 * @Author: lujinshan
 * @Date: 2019/10/16 16:02
 * @Version: 1.0.0
 */
@Component
public class TopicSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 给不存在的队列发送消息
     */
    public void send() {
        String context = "hi, i am message all";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("exchange", "topic.123", context);
    }

    public void send1() {
        String context = "hi, i am message 1";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("exchange", "topic.message", context);
    }

    public void send2() {
        String context = "hi, i am messages 2";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("exchange", "topic.messages", context);
    }
}
