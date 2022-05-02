package com.example.activemqhwtask1;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MyMessage {
    private Long messageId;
    private String messageText;

    private LocalDateTime dataAndTime;

    public MyMessage(Long messageId, String messageText) {
        this.messageId = messageId;
        this.messageText = messageText;
        this.dataAndTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId='" + messageId + '\'' +
                ", text='" + messageText + '\'' +
                ", publishTime='" + dataAndTime + '\'' +
                '}';
    }

}
