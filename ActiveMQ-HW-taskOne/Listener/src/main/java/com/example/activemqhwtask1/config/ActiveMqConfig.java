package com.example.activemqhwtask1.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import javax.jms.ConnectionFactory;

@Configuration
public class ActiveMqConfig {

    @Bean
    public DefaultJmsListenerContainerFactory jmsDurableTopicListenerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(new ActiveMQConnectionFactory());
        factory.setSubscriptionDurable(true);
        factory.setPubSubDomain(true);
        factory.setClientId("durable");
        return factory;
    }

}
