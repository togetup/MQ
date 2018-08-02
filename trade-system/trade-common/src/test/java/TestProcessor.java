import com.ace.trade.common.rocketmq.IMessageProcessor;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author 赵建龙
 * @date 2018/8/2
 */
public class TestProcessor implements IMessageProcessor {
    public boolean handleMessage(MessageExt messageExt) {
        System.out.println("收到消息:" + messageExt.toString());
        return true;
    }
}
