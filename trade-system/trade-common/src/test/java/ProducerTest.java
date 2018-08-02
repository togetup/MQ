import com.ace.trade.common.exception.AceMQException;
import com.ace.trade.common.rocketmq.AceMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 赵建龙
 * @date 2018/8/2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:xml/spring-rocketmq-producer.xml")
public class ProducerTest {

    @Autowired
    private AceMQProducer aceMQProducer;

    @Test
    public void testProducer() throws AceMQException {
        SendResult sendResult = this.aceMQProducer.sendMessage("TestTopic", "order", "12345678", "this is order message");
        System.out.println(sendResult);
    }

}
