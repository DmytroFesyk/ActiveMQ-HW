package com.example.activemqhwtasktwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ActiveMqHwTask2Producer {

    public static void main(String[] args) {
        SpringApplication.run(ActiveMqHwTask2Producer.class, args);
    }

}
