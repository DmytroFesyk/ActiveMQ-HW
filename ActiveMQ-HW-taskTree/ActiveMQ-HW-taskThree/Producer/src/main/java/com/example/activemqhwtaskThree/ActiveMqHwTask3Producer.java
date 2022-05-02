package com.example.activemqhwtaskThree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ActiveMqHwTask3Producer {

    public static void main(String[] args) {
        SpringApplication.run(ActiveMqHwTask3Producer.class, args);
    }

}
