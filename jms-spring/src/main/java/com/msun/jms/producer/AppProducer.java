package com.msun.jms.producer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 赵建龙
 * @date 2018/7/26
 */
public class AppProducer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("producer.xml");
        ProducerService service = context.getBean(ProducerService.class);

        for (int i = 0; i < 100; i++) {
            service.sendMessage("queueTest-" + i);
        }

        context.close();
    }
}
