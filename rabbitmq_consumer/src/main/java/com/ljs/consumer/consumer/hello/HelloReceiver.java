package com.ljs.consumer.consumer.hello;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: HelloReceiver
 * @Description: TODO
 * @Author: lujinshan
 * @Date: 2019/10/15 14:09
 * @Version: 1.0.0
 */
@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver1  : " + hello);
    }

}
