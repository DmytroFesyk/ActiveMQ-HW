package com.example.activemqhwtasktwo.jms;

import com.example.activemqhwtasktwo.MyMessage;
import com.example.activemqhwtasktwo.MyMessageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.JmsResponse;
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


    @JmsListener(destination = "${listener.topic}",containerFactory = "jmsQueueListenerFactory")
    public JmsResponse listenQueueAndResponse(Message inputMessage) throws JMSException, JsonProcessingException {
        TextMessage message = (TextMessage) inputMessage;
        MyMessage myMessage = jsonObjectMapper.readValue(message.getText(), MyMessage.class);
        log.info("Received message: " + myMessage + " from - " + inputMessage.getJMSDestination() + " by the queue.");
        String response = jsonObjectMapper.writeValueAsString(new MyMessageResponse(myMessage.getMessageId(), "successful"));
        return JmsResponse.forDestination(response, message.getJMSReplyTo());
    }

}
