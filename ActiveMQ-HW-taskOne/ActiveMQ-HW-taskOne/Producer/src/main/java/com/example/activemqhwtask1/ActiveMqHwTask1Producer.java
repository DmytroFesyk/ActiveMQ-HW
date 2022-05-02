package com.example.activemqhwtask1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ActiveMqHwTask1Producer {

    public static void main(String[] args) {
        SpringApplication.run(ActiveMqHwTask1Producer.class, args);
    }

}
