package com.example.activemqhwtaskThree.jms;

import com.example.activemqhwtaskThree.MyMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Slf4j
@Service
public class ActiveMqListener {

    private final ObjectMapper jsonObjectMapper;

    @Autowired
    public ActiveMqListener(ObjectMapper jsonObjectMapper) {
        this.jsonObjectMapper = jsonObjectMapper;
    }

    @JmsListener(destination = "Consumer.listenerOne.${listener.topic}")
    public void listenOne(Message inputMessage) throws JMSException, JsonProcessingException {
        TextMessage message = (TextMessage) inputMessage;
        MyMessage myMessage = jsonObjectMapper.readValue(message.getText(), MyMessage.class);
        log.info("Received message: " + myMessage + " from - " + inputMessage.getJMSDestination() + " by listener One");
    }

    @JmsListener(destination = "Consumer.listenerTwo.${listener.topic}")
    public void listenTwo(Message inputMessage) throws JMSException, JsonProcessingException {
        TextMessage message = (TextMessage) inputMessage;
        MyMessage myMessage = jsonObjectMapper.readValue(message.getText(), MyMessage.class);
        log.info("Received message: " + myMessage + " from - " + inputMessage.getJMSDestination() + " by listener Two");
    }


}
