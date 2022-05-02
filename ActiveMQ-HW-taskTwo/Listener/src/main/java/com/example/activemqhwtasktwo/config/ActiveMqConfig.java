package com.example.activemqhwtasktwo.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
public class ActiveMqConfig {

    @Bean
    public DefaultJmsListenerContainerFactory jmsQueueListenerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(new ActiveMQConnectionFactory());
        factory.setClientId("my-queue-listener");
        return factory;
    }

}
