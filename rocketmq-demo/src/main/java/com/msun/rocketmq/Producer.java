package com.msun.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @author 赵建龙
 * @date 2018/8/1
 */
public class Producer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("my-group");
        producer.setNamesrvAddr("localhost:9876");
        producer.setInstanceName("rmq-instance");
        producer.start();
        try {
            Message message = new Message("demo-topic", "demo-tag", "这是一条测试消息".getBytes());
            producer.send(message);

            for (int i = 0; i < 10; i++) {
                String text = "你好众阳 - " + i;
                Message msg = new Message("demo-topic",
                        "demo-tag",
                        text.getBytes()
                );
                SendResult sendResult = producer.send(msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }
}
