package rsi.jms.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderSender {

    private Logger logger = LoggerFactory.getLogger(OrderSender.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendTopic(Order order){
        logger.info("sending with covertAndSend() to topic <" + order + ">");
        jmsTemplate.convertAndSend(ActiveMQConfig.ORDER_TOPIC, order);
    }
}
