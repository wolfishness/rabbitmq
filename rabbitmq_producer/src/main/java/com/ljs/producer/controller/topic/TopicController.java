package com.ljs.producer.controller.topic;

import com.ljs.producer.sender.topic.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TopicController
 * @Description: TODO
 * @Author: lujinshan
 * @Date: 2019-10-31 16:06
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicSender topicSender;

    /**
     * 给所有队列发送消息
     */
    @RequestMapping("/send")
    public void send() {
        topicSender.send();
    }

    /**
     * 给topic.message发送消息
     */
    @RequestMapping("/send1")
    public void send1() {
        topicSender.send1();
    }

    /**
     * 给topic.messages发送消息
     */
    @RequestMapping("/send2")
    public void send2() {
        topicSender.send2();
    }
}
