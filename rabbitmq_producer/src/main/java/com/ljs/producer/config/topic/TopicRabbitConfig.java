package com.ljs.producer.config.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: TopicRabbitConfig
 * @Description: TODO
 * @Author: lujinshan
 * @Date: 2019/10/16 15:24
 * @Version: 1.0.0
 */
@Configuration
public class TopicRabbitConfig {

    private final static String MESSAGE = "topic.message";

    private final static String MESSAGES = "topic.messages";

    /**
     * 定义一个队列
     * Queue 可以有4个参数
     *      1.队列名
     *      2.durable       持久化消息队列 ,rabbitmq重启的时候不需要创建新的队列 默认true
     *      3.auto-delete   表示消息队列没有在使用时将被自动删除 默认是false
     *      4.exclusive     表示该消息队列是否只在当前connection生效,默认是false
     */

    @Bean
    public Queue queueMessage(){
        return new Queue(TopicRabbitConfig.MESSAGE);
    }

    @Bean
    public Queue queueMessages(){
        return new Queue(TopicRabbitConfig.MESSAGES);
    }

    /**
     * 声明交换机
     * @return
     */
    @Bean
    TopicExchange exchange(){
        return new TopicExchange("exchange");
    }

    /**
     * 队列绑定交换机
     */

    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange){
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange){
        //*表示一个词,#表示零个或多个词
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }

}
