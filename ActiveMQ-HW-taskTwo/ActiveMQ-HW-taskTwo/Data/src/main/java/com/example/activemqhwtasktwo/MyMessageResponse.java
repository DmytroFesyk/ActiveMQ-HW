package com.example.activemqhwtasktwo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyMessageResponse {
    private Long messageId;
    private String messageText;

    public MyMessageResponse(Long messageId, String messageText) {
        this.messageId = messageId;
        this.messageText = messageText;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId='" + messageId + '\'' +
                ", text='" + messageText + '\'' +
                '}';
    }

}
