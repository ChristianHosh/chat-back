package com.chat.chatback.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    public ChatMessageDTO chat(long channelId, ChatMessageDTO message) {
        System.out.println("CHATTING ON CHANNEL => " + channelId);


        return message;
    }
}
