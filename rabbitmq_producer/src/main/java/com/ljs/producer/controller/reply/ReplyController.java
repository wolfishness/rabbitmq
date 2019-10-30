package com.ljs.producer.controller.reply;

import com.ljs.producer.sender.reply.ReplySender;
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
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplySender replySender;

    @RequestMapping("/sendAsync")
    public void sendAsync(){
        replySender.asyncSend("异步测试信息");
    }

    @RequestMapping("/sendSync")
    public void sendSync(){
        replySender.syncSend("同步测试信息");
    }
}
