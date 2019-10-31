package com.ljs.producer.controller.hello;

import com.ljs.producer.sender.hello.HelloSender;
import com.ljs.producer.sender.hello.HelloSender2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ReplyController
 * @Description: TODO
 * @Author: lujinshan
 * @Date: 2019-10-30 16:06
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private HelloSender helloSender;

    @Autowired
    private HelloSender2 helloSender2;

    @RequestMapping("/send")
    public void send() {
        helloSender.send(1);
    }

    @RequestMapping("/send1")
    public void send1() {
        helloSender2.send(1);
    }

    @RequestMapping("/sendFor")
    public void sendFor() {
        for (int i = 0; i < 100; i++) {
            helloSender.send(i);
        }
    }

    @RequestMapping("/sendFor2")
    public void sendFor2() {
        for (int i = 0; i < 100; i++) {
            helloSender2.send(i);
        }
    }

}
