package com.msun.jms.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author 赵建龙
 * @date 2018/7/26
 */
public class ConsumerMessageListener implements MessageListener {
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("接收消息-" + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
