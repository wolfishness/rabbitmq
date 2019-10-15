package com.ljs.producer;

import com.ljs.producer.sender.HelloSender;
import com.ljs.producer.sender.HelloSender2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqProducerApplicationTests {

    @Autowired
    private HelloSender helloSender;

    @Autowired
    private HelloSender2 helloSender2;


    @Test
    public void contextLoads() {
    }

    @Test
    public void hello() throws Exception {
        helloSender.send(0);
    }

    @Test
    public void oneToMany() throws Exception {
        for (int i = 0; i < 50; i++) {
            helloSender.send(i);
        }
    }

    @Test
    public void manyToMany() throws Exception {
        for (int i = 0; i < 50; i++) {
            helloSender.send(i);
            helloSender2.send(i);
        }
    }
}
