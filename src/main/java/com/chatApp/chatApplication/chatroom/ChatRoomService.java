package com.chatApp.chatApplication.chatroom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

     public Optional<String> getChatRoomId(
             String senderId,
             String receiverId,
             boolean createNewRoomIfNotExists) {

         return chatRoomRepository.findBySenderIdAndRecieverId(senderId, receiverId)
                 .map(ChatRoom::getChatId)
                 .or(()->{
             if (createNewRoomIfNotExists) {
                var ChatId = createChatId(senderId, receiverId);
                return Optional.of(ChatId);
             }
             return Optional.empty();
         });
     }

    private String createChatId(String senderId, String receiverId) {
         var chatId = String.format("%s-%s", senderId, receiverId);
         ChatRoom senderReceiver = ChatRoom.builder().
                 chatId(chatId).
                 senderId(senderId).
                 receiverId(receiverId).
                 build();

        ChatRoom receiverSender = ChatRoom.builder().
                chatId(chatId).
                senderId(receiverId).
                receiverId(senderId).
                build();

        chatRoomRepository.save(senderReceiver);
        chatRoomRepository.save(receiverSender); 
         return chatId;
    }
}
