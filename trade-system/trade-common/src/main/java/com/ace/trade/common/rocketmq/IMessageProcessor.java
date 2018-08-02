package com.ace.trade.common.rocketmq;

import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author 赵建龙
 * @date 2018/8/2
 */
public interface IMessageProcessor {

    /**
     * 处理消息
     * @param messageExt
     * @return
     */
    boolean handleMessage(MessageExt messageExt);
}
