package com.example.activemqhwtask1.jms;


import com.example.activemqhwtask1.MyMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ActiveMqProducer {

    private final JmsTemplate topicJmsTemplate;

    private final ObjectMapper jsonObjectMapper;

    @Value("${app.test-data.number-of-messages}")
    private int numberMessages;
    @Value("${app.test-data.delay-between-messages}")
    private Long delay;

    @Autowired
    public ActiveMqProducer(
            JmsTemplate topicJmsTemplate,
            ObjectMapper jsonObjectMapper
    ) {
        this.topicJmsTemplate = topicJmsTemplate;
        this.jsonObjectMapper = jsonObjectMapper;
    }

    @Scheduled(fixedDelayString = "${app.test-data.fixed-producing-delay}", timeUnit = TimeUnit.SECONDS)
    public void produceTestData() throws InterruptedException, JsonProcessingException {
        for (int i = 0; i < numberMessages; i++) {
            produceToTopic(new MyMessage(i + 1L, "DF-test message"));
            Thread.sleep(delay);
        }
    }

    public void produceToTopic(MyMessage message) throws JsonProcessingException {
        String jsonMessage = jsonObjectMapper.writeValueAsString(message);
        topicJmsTemplate.convertAndSend(jsonMessage);
        log.info("Sending message " + jsonMessage + " to topic - " + topicJmsTemplate.getDefaultDestinationName());
    }
}
