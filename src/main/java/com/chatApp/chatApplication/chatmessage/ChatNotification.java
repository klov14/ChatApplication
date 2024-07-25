package com.chatApp.chatApplication.chatmessage;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ChatNotification {
    private String id;
    private String senderId;
    private String receiverId;
    private String content;
}
