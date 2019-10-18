package com.ljs.producer.topic;

import com.ljs.producer.sender.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @ClassName: TopicTest
 * @Description: TODO
 * @Author: lujinshan
 * @Date: 2019/10/16 16:32
 * @Version: 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicTest {
    @Resource
    private TopicSender topicSender;
    @Test
    public void contextLoads() {
    }

    @Test
    public void testSender(){
        topicSender.send();
        topicSender.send1();
        topicSender.send2();
    }
}
