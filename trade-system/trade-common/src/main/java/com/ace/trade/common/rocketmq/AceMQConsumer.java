package com.ace.trade.common.rocketmq;

import com.ace.trade.common.exception.AceMQException;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 赵建龙
 * @date 2018/8/2
 */
public class AceMQConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AceMQConsumer.class);

    private String groupName;
    private String topic;
    private String tag = "*";
    private String namesrvAddr;
    private int consumeThreadMin = 20;
    private int consumeThreadMax = 64;

    private IMessageProcessor messageProcessor;

    private void init() throws AceMQException {
        if (StringUtils.isBlank(this.groupName)){
            throw new AceMQException("groupName is blank!");
        }
        if (StringUtils.isBlank(this.topic)){
            throw new AceMQException("topic is blank!");
        }
        if (StringUtils.isBlank(this.namesrvAddr)){
            throw new AceMQException("namesrvAddr is blank!");
        }

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(this.groupName);
        consumer.setNamesrvAddr(this.namesrvAddr);

        try {
            consumer.subscribe(this.topic, this.tag);
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.setConsumeThreadMin(this.consumeThreadMin);
            consumer.setConsumeThreadMax(this.consumeThreadMax);

            AceMessageListener aceMessageListener = new AceMessageListener();
            aceMessageListener.setMessageProcessor(this.messageProcessor);
            consumer.registerMessageListener(aceMessageListener);
            consumer.start();

            LOGGER.info(String.format("consumer is start!groupName:%1$s,topic:%2$s,namesrvAddr:%3$s", this.groupName, this.topic, this.namesrvAddr));
        } catch (MQClientException e) {
            LOGGER.error(String.format("consumer is error!groupName:%1$s,topic:%2$s,namesrvAddr:%3$s", this.groupName, this.topic, this.namesrvAddr), e);
            throw new AceMQException(e);
        }
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public void setConsumeThreadMin(int consumeThreadMin) {
        this.consumeThreadMin = consumeThreadMin;
    }

    public void setConsumeThreadMax(int consumeThreadMax) {
        this.consumeThreadMax = consumeThreadMax;
    }

    public void setMessageProcessor(IMessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor;
    }
}
