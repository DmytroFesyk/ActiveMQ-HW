package com.example.activemqhwtask1.jms;

import com.example.activemqhwtask1.MyMessage;
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

    @JmsListener(destination = "${listener.topic}", containerFactory = "jmsDurableTopicListenerFactory")
    public void listenDurable(Message inputMessage) throws JMSException, JsonProcessingException {
        TextMessage message = (TextMessage) inputMessage;
        MyMessage myMessage = jsonObjectMapper.readValue(message.getText(), MyMessage.class);
        log.info("Received message: " + myMessage + " from - " + inputMessage.getJMSDestination() + " by the durable subscriber.");
    }

    @JmsListener(destination = "${listener.topic}")
    public void listenNonDurable(Message inputMessage) throws JMSException, JsonProcessingException {
        TextMessage message = (TextMessage) inputMessage;
        MyMessage myMessage = jsonObjectMapper.readValue(message.getText(), MyMessage.class);
        log.info("Received message: " + myMessage + " from - " + inputMessage.getJMSDestination() + " by the non durable subscriber.");
    }

}
