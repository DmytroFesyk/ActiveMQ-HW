package com.example.activemqhwtasktwo.jms;


import com.example.activemqhwtasktwo.MyMessage;
import com.example.activemqhwtasktwo.MyMessageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.TextMessage;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ActiveMqProducer {

    private final JmsTemplate queueJmsTemplate;

    private final ObjectMapper jsonObjectMapper;

    private final Queue responseQueue;

    @Value("${app.test-data.number-of-messages}")
    private int numberMessages;
    @Value("${app.test-data.delay-between-messages}")
    private Long delay;

    @Autowired
    public ActiveMqProducer(
            JmsTemplate queueJmsTemplate,
            ObjectMapper jsonObjectMapper,
            Queue responseQueue
    ) {
        this.queueJmsTemplate = queueJmsTemplate;
        this.jsonObjectMapper = jsonObjectMapper;
        this.responseQueue = responseQueue;
    }

    @Scheduled(fixedDelayString = "${app.test-data.fixed-producing-delay}", timeUnit = TimeUnit.SECONDS)
    public void produceTestData() throws InterruptedException, JsonProcessingException, JMSException {
        for (int i = 0; i < numberMessages; i++) {

            sendToQueueWithReply(new MyMessage(i + 1L, "DF-test message"));
            Thread.sleep(delay);
        }
    }

    public void sendToQueueWithReply(MyMessage myMessage) throws JsonProcessingException, JMSException {
        String jsonMessage = jsonObjectMapper.writeValueAsString(myMessage);
        log.info("Sending message " + jsonMessage + " to queue - " + queueJmsTemplate.getDefaultDestinationName());
        queueJmsTemplate.convertAndSend(jsonMessage, message -> {
            message.setJMSReplyTo(responseQueue);
            message.setStringProperty("JMSXGroupID", "GroupOne");
            return message;
        });
        TextMessage response = (TextMessage) queueJmsTemplate.receive(responseQueue);
        if (response !=null) {
            MyMessageResponse myMessageResponse = jsonObjectMapper.readValue(response.getText(), MyMessageResponse.class);
            log.info("Message " + myMessageResponse + "processed");
        }else {
            log.info("Timeout answer for message" + jsonMessage);
        }
    }
}
