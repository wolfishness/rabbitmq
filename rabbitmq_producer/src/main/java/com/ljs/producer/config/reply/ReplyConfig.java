package com.ljs.producer.config.reply;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 重试和死信队列配置类
 *
 * @ClassName: ReplyConfig
 * @Description: TODO
 * @Author: lujinshan
 * @Date: 2019/10/30 15:24
 * @Version: 1.0.0
 */
@Configuration
public class ReplyConfig {
    /**
     * 死信队列 交换机标识符
     */
    private static final String DEAD_LETTER_QUEUE_KEY = "x-dead-letter-exchange";
    /**
     * 死信队列交换机绑定键标识符
     */
    private static final String DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";
    /**
     * 死信队列里面消息的超时时间
     */
    private static final String X_MESSAGE_TTL = "x-message-ttl";

    /**
     * ##########################################   交换机   #####################################################
     */
    /**
     * 正常交换机
     */
    private static final String TEST_EXCHANGE = "test.hello";
    /**
     * 重试交换机
     */
    private static final String TEST_RETRY_EXCHANGE = "test.hello.retry";
    /**
     * 死信交换机
     */
    private static final String TEST_FAIL_EXCHANGE = "test.hello.failed";
    /**
     * ##########################################   队列   #####################################################
     */
    /**
     * 正常队列
     */
    private static final String TEST_QUEUE = "test@supply";
    /**
     * 重试队列
     */
    private static final String TEST_RETRY_QUEUE = "test@supply@retry";
    /**
     * 死信队列
     */
    private static final String TEST_FAILED_QUEUE = "test@supply@failed";
    /**
     * ##########################################    路由键   #####################################################
     */
    /**
     * 相当于是中间键，通过该路由判断消息发送是否成功，成功则发送到相应的队列
     */
    private static final String DIRECT_ROUTING_KEY = "direct_routing_key";
    /**
     * 路由键
     */
    private static final String TEST_ROUTING_KEY = "test_routing_key";


    /**
     * 声明交换机,支持持久化.
     * rabbitmq常用几种exchange,比如direct, fanout, topic,可根据具体业务需求配置
     * 命名规范参考 scm3.services,scm3.services.retry,scm3.services.failed
     *
     * @return the exchange
     */
    @Bean(TEST_EXCHANGE)
    public Exchange directExchange() {
        //.durable(true) exchange的持久化
        return ExchangeBuilder.directExchange(TEST_EXCHANGE).durable(true).build();
    }

    @Bean(TEST_RETRY_EXCHANGE)
    public Exchange retryDirectExchange() {
        return ExchangeBuilder.directExchange(TEST_RETRY_EXCHANGE).durable(true).build();
    }

    @Bean(TEST_FAIL_EXCHANGE)
    public Exchange failDirectExchange() {
        return ExchangeBuilder.directExchange(TEST_FAIL_EXCHANGE).durable(true).build();
    }
    /**
     * ##########################################供需关系服务-声明queue#####################################################
     */
    /**
     * 声明一个队列 .{供需关系主队列} 队列名称参考 【服务名称】@订阅服务标识 如
     * reply@供需关系,reply@供需关系@retry,reply@供需关系@failed
     *
     * @return the queue
     */
    @Bean(TEST_QUEUE)
    public Queue directQueue() {
        return QueueBuilder.durable(TEST_QUEUE).build();
    }

    /**
     * 供需关系 重试队列
     *
     * @return
     */
    @Bean(TEST_RETRY_QUEUE)
    public Queue retryDirectQueue() {
        // 将消息重新投递到exchange中
        //在队列中延迟30s后，消息重新投递到x-dead-letter-exchange对应的队列中,routingKey是自己配置的
        return QueueBuilder
                .durable(TEST_RETRY_QUEUE)
                .withArgument(DEAD_LETTER_QUEUE_KEY, TEST_EXCHANGE)
                .withArgument(DEAD_LETTER_ROUTING_KEY, TEST_QUEUE)
                .withArgument(X_MESSAGE_TTL, 30 * 1000)
                .build();

    }

    /**
     * 供需关系 失败队列
     *
     * @return
     */
    @Bean(TEST_FAILED_QUEUE)
    public Queue failDirectQueue() {
        return QueueBuilder.durable(TEST_FAILED_QUEUE).build();
    }

    /**
     * ###########################################供需关系结束###############################################
     */

    /**
     * 以下是消费者需要处理的 通过绑定键(rounting key) 将指定队列绑定到一个指定的交换机 .要求该消息与一个特定的路由键完全匹配
     * @param queue the queue
     * @param exchange the exchange
     * @return the binding
     */
    /**
     * ######################################供需关系绑定###################################################
     */
    /**
     * 转发
     *
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding directBinding(@Qualifier(TEST_QUEUE) Queue queue,
                                 @Qualifier(TEST_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DIRECT_ROUTING_KEY).noargs();
    }

    /**
     * 正常
     *
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding directQueueBinding(@Qualifier(TEST_QUEUE) Queue queue,
                                      @Qualifier(TEST_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(TEST_QUEUE).noargs();
    }

    /**
     * 重试
     *
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding retryDirectBinding(@Qualifier(TEST_RETRY_QUEUE) Queue queue,
                                      @Qualifier(TEST_RETRY_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(TEST_QUEUE).noargs();
    }

    /**
     * 失败
     *
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding failDirectBinding(@Qualifier(TEST_FAILED_QUEUE) Queue queue,
                                     @Qualifier(TEST_FAIL_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(TEST_QUEUE).noargs();
    }


}