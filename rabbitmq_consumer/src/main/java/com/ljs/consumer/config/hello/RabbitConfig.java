package com.ljs.consumer.config.hello;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: RabbitConfig
 * @Description: TODO
 * @Author: lujinshan
 * @Date: 2019/10/15 14:08
 * @Version: 1.0.0
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue Queue() {
        return new Queue("hello");
    }

}
