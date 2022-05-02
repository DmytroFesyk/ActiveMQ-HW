package com.example.activemqhwtasktwo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;


@Configuration
public class ActiveMqConfig {

    @Bean
    public Queue responseQueue(ConnectionFactory connectionFactory) throws JMSException {
      return connectionFactory.createConnection().createSession().createTemporaryQueue();
    }
}
